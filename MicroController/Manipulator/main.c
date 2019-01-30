
#include "project.h"
#include "math.h"


int Theta1(float Angle){
    int Compare;
    int min_comp=1500,max_comp=6000,min_angle=0,max_angle=180;
    Compare=((max_comp-min_comp)/(max_angle-min_angle))*(Angle-min_angle)+min_comp;
    return Compare;
}

int Theta2(float Angle){
    int Compare;
    int min_comp=1500,max_comp=6000,min_angle=-90,max_angle=90;
    Compare=((max_comp-min_comp)/(max_angle-min_angle))*(Angle-min_angle)+min_comp;
    return Compare;
}
int main(void)
{
//    for(;;){
//        LCD_Char_1_ClearDisplay();
//        LCD_Char_1_Position(1,0);
//        LCD_Char_1_PrintString("on");
//        magnet_Write(1);
//        CyDelay(3000);
//        LCD_Char_1_ClearDisplay();
//        LCD_Char_1_Position(1,0);
//        LCD_Char_1_PrintString("off");
//        magnet_Write(0);
//        CyDelay(3000);
//        
//    }
 LCD_Char_1_Start();
    QuadDec_1_Start();
    PWM_1_Start();
    UART_1_Start();
    uint8 recieve1,recieve2;
    
    
    
    //PWM_1_Start();
    PWM_2_Start();
    //for(;;)
    {
//        //get from Computer
        LCD_Char_1_ClearDisplay();
        LCD_Char_1_Position(0,0);
        LCD_Char_1_PrintString("waiting1");
        recieve1=UART_1_GetChar();
        while(recieve1==0){
            recieve1=UART_1_GetChar();
        }
        LCD_Char_1_ClearDisplay();
        LCD_Char_1_Position(0,0);
        LCD_Char_1_PrintString("waiting2");
        recieve2=UART_1_GetChar();
        while(recieve2==0){
            recieve2=UART_1_GetChar();
        }
        float x=recieve1;
        float y=recieve2;
        LCD_Char_1_ClearDisplay();
        LCD_Char_1_Position(0,0);
        LCD_Char_1_PrintNumber(x);
        LCD_Char_1_Position(1,0);
        LCD_Char_1_PrintNumber(y);
        x=x/10.0;
        y=y/10.0;
        x=fabsf(x);
        y=fabsf(y);
        CyDelay(500);
        //inverse kinematics
//        float x=6,y=4;
        float a2=6.0,a4=5.5;
        float r1=sqrt(x*x+y*y); //Eq1
        float phi1=acos(((a4*a4)-(a2*a2)-(r1*r1))/(-2.0*a2*r1));
        float phi2=atan(y/x);//Eq3
        float T1=phi2-phi1;//rad Eq4
        float phi3=acos(((r1*r1)-(a2*a2)-(a4*a4))/(-2.0*a2*a4));
        float T2=3.14159-phi3; //EQ6 
        //Tape means top
                LCD_Char_1_ClearDisplay();
        LCD_Char_1_Position(0,0);
        LCD_Char_1_PrintString("move serv1");
        PWM_2_WriteCompare1(Theta1((T1/3.14159)*180));//clockwise
        //CyDelay(2000);
        PWM_2_WriteCompare2(Theta2((T2/3.14159)*180));//clockwise
        CyDelay(5000);
//        PWM_1_WriteCompare1(Theta1(180.0));//counterclock
//        CyDelay(2000);
//        
//        PWM_1_WriteCompare2(Theta2(-90.0));//clockwise
//        CyDelay(2000);
//        PWM_1_WriteCompare2(Theta2(90.0));
//        CyDelay(2000);
        
        
        //up ad down
        
        //up
        PWM_1_WriteCompare1(83);
        PWM_1_WriteCompare2(0);
        CyDelay(500);
        PWM_1_WriteCompare1(0);
        PWM_1_WriteCompare2(0);
        CyDelay(500);
        //down
        PWM_1_WriteCompare1(0);
        PWM_1_WriteCompare2(83);
        CyDelay(380);
        PWM_1_WriteCompare1(0);
        PWM_1_WriteCompare2(0);
        //maget on
        magnet_Write(1);
        CyDelay(900);
        //up
        PWM_1_WriteCompare1(83);
        PWM_1_WriteCompare2(0);
        CyDelay(380);
        PWM_1_WriteCompare1(0);
        PWM_1_WriteCompare2(0);
//        int count,time;
//    int Target_Count1=300,Target_Count2=1500;//test these
//        
//        int compare=0,count1=0,count2=0,countDif=0;
//    float cpr=814.0;
//    int Error;
//    int speed;
//    float Kp=0.14;
//    uint8 val1,val2;
//        time=0;
//        //changes motor speeds
//        while(time<1000){
//            count=QuadDec_1_GetCounter();
//            Error=Target_Count1-count;
//            if(Error>0){
//                speed=Kp*Error;
//                if(speed>100){
//                    speed=100;
//                }
//                PWM_1_WriteCompare1(speed);
//                PWM_1_WriteCompare2(0);
//            }
//            else{
//                speed=-Kp*Error;
//                if(speed>100){
//                    speed=100;
//                }
//                PWM_1_WriteCompare1(0);
//                PWM_1_WriteCompare2(speed);
//            }
//            
//            LCD_Char_1_ClearDisplay();
//            LCD_Char_1_Position(0,0);
//            val1=count/256;
//            val2=count-(val1*256);
//            LCD_Char_1_PrintNumber(count);
//            UART_1_PutChar(val1);
//            CyDelay(10);
//            UART_1_PutChar(val2);
//            time+=10;
//        }
//        
//        time=0;
//        //changes motor speeds
//        while(time<1000){
//            count=QuadDec_1_GetCounter();
//            Error=Target_Count2-count;
//            if(Error>0){
//                speed=Kp*Error;
//                if(speed>100){
//                    speed=100;
//                }
//                PWM_1_WriteCompare1(speed);
//                PWM_1_WriteCompare2(0);
//            }
//            else{
//                speed=-Kp*Error;
//                if(speed>100){
//                    speed=100;
//                }
//                PWM_1_WriteCompare1(0);
//                PWM_1_WriteCompare2(speed);
//            }
//
//            LCD_Char_1_ClearDisplay();
//            LCD_Char_1_Position(0,0);
//            val1=count/256;
//            val2=count-(val1*256);
//            LCD_Char_1_PrintNumber(count);
//            UART_1_PutChar(val1);
//            CyDelay(10);
//            UART_1_PutChar(val2);
//            time+=10;
//        }
        
        //move back
        float turn1=Theta1((T1/3.14159)*180);
        float turn2=Theta2((T2/3.14159)*180);
        while(turn1<Theta1(180)){
            turn1++;
        PWM_2_WriteCompare1(turn1);//clockwise
        CyDelay(2);
        }
        //CyDelay(2000);
        while(turn2<Theta2(180)){
            turn2++;
        PWM_2_WriteCompare2(turn2);//clockwise
        CyDelay(2);
        }
        //PWM_2_WriteCompare2(Theta2(180));//clockwise
        magnet_Write(0);
        
        
    }
}
