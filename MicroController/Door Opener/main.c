//'!' is intentional null  
//'@' is no value given
#include "project.h"

#define maxUsers 5
#define passwordSize 5
#define maxName 12

struct user{
    char name[maxName];
    char password[5];
};
struct user users[maxUsers];

void printMessage(char* s){
    LCD_Char_1_ClearDisplay();
    LCD_Char_1_Position(0,0);
    LCD_Char_1_PrintString(s);
}
void print2Message(char* s,char* s2){
    LCD_Char_1_ClearDisplay();
    LCD_Char_1_Position(0,0);
    LCD_Char_1_PrintString(s);
    LCD_Char_1_Position(1,0);
    LCD_Char_1_PrintString(s2);
}

char checkInput(){
    KEY_COL1_Write(255);
    CyDelay(5);
    if(KEY_ROW1_Read()>0)
        return '1';
    if(KEY_ROW2_Read()>0)
        return '4';
    if(KEY_ROW3_Read()>0)
        return '7';
    if(KEY_ROW4_Read()>0)
        return '*';
    KEY_COL1_Write(0);
    KEY_COL2_Write(255);
    CyDelay(5);
    if(KEY_ROW1_Read()>0)
        return '2';
    if(KEY_ROW2_Read()>0)
        return '5';
    if(KEY_ROW3_Read()>0)
        return '8';
    if(KEY_ROW4_Read()>0)
        return '0';
    KEY_COL2_Write(0);
    KEY_COL3_Write(255);
    CyDelay(5);
    if(KEY_ROW1_Read()>0)
        return '3';
    if(KEY_ROW2_Read()>0)
        return '6';
    if(KEY_ROW3_Read()>0)
        return '9';
    if(KEY_ROW4_Read()>0)
        return '#';
    KEY_COL3_Write(0);
    return '@';
}

char readInputCharacter(){
    char output='@';
    CyDelay(500);
    output=checkInput();
    while(output=='@'){
        CyDelay(500);
        output=checkInput();
    }
    return output;
}
    
char readCharacter(){
    switch(readInputCharacter()){
        case '1':return readInputCharacter()=='*'? 'A':readInputCharacter()=='*'?'B':'C';break;
        case '2':return readInputCharacter()=='*'? 'D':readInputCharacter()=='*'?'E':'F';break;
        case '3':return readInputCharacter()=='*'? 'G':readInputCharacter()=='*'?'H':'I';break;
        case '4':return readInputCharacter()=='*'? 'J':readInputCharacter()=='*'?'K':'L';break;
        case '5':return readInputCharacter()=='*'? 'M':readInputCharacter()=='*'?'N':'O';break;
        case '6':return readInputCharacter()=='*'? 'P':readInputCharacter()=='*'?'Q':'R';break;
        case '7':return readInputCharacter()=='*'? 'S':readInputCharacter()=='*'?'T':'U';break;
        case '8':return readInputCharacter()=='*'? 'V':readInputCharacter()=='*'?'W':'X';break;
        case '9':return readInputCharacter()=='*'? 'Y':readInputCharacter()=='*'?'Z':' ';break;
        case '0':return readInputCharacter()=='*'? '-':readInputCharacter()=='*'?',':'.';break;
        case '#':return '#';break;
        case '*':return '#';break;
    }
    return '@';
}

void writePassword(int index,char c1,char c2,char c3,char c4, char c5){
    users[index].password[0]=c1;
    users[index].password[1]=c2;
    users[index].password[2]=c3;
    users[index].password[3]=c4;
    users[index].password[4]=c5;
}

void resetUsers(){
    for(int x=0;x<maxUsers;x++){
        users[x].name[0]='!';
    }
    users[0].name[0]='J';
    writePassword(0,'1','1','1','1','1');
    writePassword(1,'2','2','2','2','2');
    writePassword(2,'3','3','3','3','3');
    writePassword(3,'4','4','4','4','4');
    writePassword(4,'5','5','5','5','5');
}

int addUser(){
    int replace=-1;
    for(int x=0;x<maxUsers;x++){
        if(users[x].name[0]=='!'){
            replace=x;
            break;
        }
    }
    if(replace==-1){
        printMessage("No Empty Users");
        CyDelay(1000);
        return 0;
    }
    printMessage("Enter name");//String "Enter name"
    //reading name
    int c=0;
    char read;
    char name[maxName];
    while(c<maxName){
        print2Message("Enter Name",name);
        read=readCharacter();
        if(read=='#')break;
        else{
            name[c]=read;
        }
        c++;
    }
    for(int x=0;x<c;x++){
        users[replace].name[0]=name[0];
    }
    //password
    print2Message("Password Confirm",users[replace].password);
    readInputCharacter();
    return 1;
}

int removeUser(){
    int c=0;
    //reading
    char read='!';
    while(1){
        print2Message("Remove User?",users[c%maxUsers].name);
        read=readInputCharacter();
        if(read=='#')break;
        c++;
    }
    users[c].name[0]='!';
    print2Message("Removed ",users[c].name);
    CyDelay(500);
    c=-1; //any users?
    for(int x=0;x<maxUsers;x++){
        if(users[x].name[0]!='!'){
            c=x;
            break;
        }
    }
    if(c==-1){
        resetUsers();
    }
    return 1;
}

int averageBB(){
    int temp=0;
    temp=temp+BreakBeam_Read();
    CyDelay(50);
    temp=temp+BreakBeam_Read();
    CyDelay(50);
    temp=temp+BreakBeam_Read();
    CyDelay(50);
    temp=temp/3;
    return temp; 
}

int openDoor(int specificUser){//assums door starts closed
    //unlock servo 1
    MOS_1_Write(1);
    PWM_1_WriteCompare1(4900);
    CyDelay(1000);//test
    MOS_1_Write(0);
    //unlock servo 2
    MOS_2_Write(1);
    PWM_1_WriteCompare2(4900);
    CyDelay(1000);//test
    MOS_2_Write(0);
    if(specificUser>=0){
        print2Message("Hello",users[specificUser].name);
    }
    else{
        if(specificUser==-1)printMessage("Hello Button");
        else printMessage("Hello Phone");
    }
    while(averageBB()>=1){//door closed
        CyDelay(500);//test
    }
    CyDelay(5000);//door open time
    while(averageBB()<1){//door open
        Speaker_Write(255);
        CyDelay(500);//test
    }
    Speaker_Write(0);
    //lock servo 1
    MOS_1_Write(255);
    PWM_1_WriteCompare1(1900);
    CyDelay(3000);//test
    MOS_1_Write(0);
    CyDelay(1500);//test
    //lock servo 2
    MOS_2_Write(255);
    PWM_1_WriteCompare2(1900);
    CyDelay(3000);//test
    MOS_2_Write(0);
    return 1;
}

int checkPassword(char* a){
    int found=0;
    for(int x=0;x<maxUsers;x++){
        if(users[x].name[0]!='!'){
            for(int y=0;y<passwordSize;y++){
                
                if(users[x].password[y]!=a[y]){
                    break;
                }
                if(y==passwordSize-1){
                    found=1;
                }
            }
            if(found){
                return x;
            }
        }
    }
    return -1;
}

void setupMenue(){
    char read;
    do{
        print2Message("1 reset 2 ADD","3 Remove 4 Quit");
        read=readInputCharacter();
        switch(read){
            case '1':resetUsers();break;
            case '2':addUser();break;
            case '3':removeUser();break;
        }
    }while(read!='4');
}

void checkInputs(){
    
}
    
uint8 red_State=0;
void updateLed()
{
    CYBLE_GATTS_HANDLE_VALUE_NTF_T 	tempHandle;
    
    if(CyBle_GetState() != CYBLE_STATE_CONNECTED)
        return;
    
    tempHandle.attrHandle = CYBLE_DOORBUTTON_LED_CHAR_HANDLE;
  	tempHandle.value.val = (uint8 *) &red_State;
    tempHandle.value.len = 1;
    CyBle_GattsWriteAttributeValue(&tempHandle,0,&cyBle_connHandle,CYBLE_GATT_DB_LOCALLY_INITIATED);  
}
void BleCallBack(uint32 event, void* eventParam)
{
    CYBLE_GATTS_WRITE_REQ_PARAM_T *wrReqParam;

    switch(event)
    {
        /* if there is a disconnect or the stack just turned on from a reset then start the advertising and turn on the LED blinking */
        case CYBLE_EVT_STACK_ON:
        case CYBLE_EVT_GAP_DEVICE_DISCONNECTED:
            CyBle_GappStartAdvertisement(CYBLE_ADVERTISING_FAST);
        break;
        
        /* when a connection is made, update the LED and Capsense states in the GATT database and stop blinking the LED */    
        case CYBLE_EVT_GATT_CONNECT_IND:
            updateLed();
		break;

        /* handle a write request */
        case CYBLE_EVT_GATTS_WRITE_REQ:
            wrReqParam = (CYBLE_GATTS_WRITE_REQ_PARAM_T *) eventParam;
			      
            /* request write the LED value */
            if(wrReqParam->handleValPair.attrHandle == CYBLE_DOORBUTTON_LED_CHAR_HANDLE)
            {
                /* only update the value and write the response if the requested write is allowed */
                if(CYBLE_GATT_ERR_NONE == CyBle_GattsWriteAttributeValue(&wrReqParam->handleValPair, 0, &cyBle_connHandle, CYBLE_GATT_DB_PEER_INITIATED))
                {
                    //red_State=(!wrReqParam->handleValPair.value.val[0]);
                    red_State=1;
                    //openDoor(-2);
                    CyBle_GattsWriteRsp(cyBle_connHandle);
                }
            }	
			break;  
        
        default:
            break;
    }
} 
int main(void)
{
    
    LCD_Char_1_Start();
    
//    for(;;){
//    LCD_Char_1_ClearDisplay();
//    LCD_Char_1_Position(1,0);
//    LCD_Char_1_PrintString("HELLO world");
//    CyDelay(1000);
//    }
    PWM_1_Start();
    CyGlobalIntEnable;
    CyBle_Start(BleCallBack);
    resetUsers();
    char readVal;
    char password[5];
    int count=0;
    int pwResult;
    for(;;)
    {
        printMessage("waiting 4 input");
//        CyDelay(500);
        readVal=checkInput();
//        printMessage("checking key");
//        CyDelay(500);
//        LCD_Char_1_ClearDisplay();
//    LCD_Char_1_Position(0,0);
//    LCD_Char_1_PrintNumber(readVal);
//    LCD_Char_1_Position(1,0);
//    LCD_Char_1_PrintNumber(DoorButton_Read());
//        CyDelay(500);
        if(readVal!='@'){
            count=0;
            while(readVal!='#'&&readVal!='*'){
                //printMessage("reading password");
                LCD_Char_1_Position(1,count*2);
                //LCD_Char_1_PrintNumber(readVal); 
                LCD_Char_1_PrintString("*");
                password[count]=readVal;
                
                readVal=readInputCharacter();
                count++;
                if(count>5){printMessage("countover");CyDelay(500);break;}
            }
            pwResult=checkPassword(password);
            if(pwResult!=-1){
                
                if(readVal=='#'){
                    openDoor(pwResult);
                }
                if(readVal=='*'){
                    setupMenue();
                }
            }
            else{
                printMessage("Incorrect Password");
                Speaker_Write(255);
                CyDelay(500);
                Speaker_Write(0);
            }
        }
//        printMessage("checking button");
//        CyDelay(500);
//        LCD_Char_1_PrintNumber(DoorButton_Read());
//        CyDelay(500);
        if(DoorButton_Read()==0){
            openDoor(-1);
        }
//        printMessage("checking blue");
//        CyDelay(500);
        if(red_State){
            openDoor(-2);
            red_State=0;
        }
        CyBle_ProcessEvents();
    }
}
