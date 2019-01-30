
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ContentPanel extends Panel {
    JPanel map,options,info,buttons;
    JButton b1,b2,b3,b4,cancel;
    int state,turnCounter=0,team1=0,team2=0;
    Character[] characterList;
    Tile[][] tileGrid=new Tile[10][10];
    TileListener tileL;
    DamageListener tileD;
    AttackAnimation AA;
    class ChoiceListener implements MouseListener{
        public void mouseClicked(MouseEvent me) {
            buttons.removeAll();
            switch(state){
                case 0:buttons.add(cancel);state=1;break;
                case 1://b2.setText("Attack : "+characterList[turnCounter].getAttacks());  //Does not change per character
                    buttons.add(b1);buttons.add(b2);buttons.add(b3);buttons.add(b4);state=0;break;   
                    //Good Choice? if(characterList[turnCounter].getMoves()>0)
            }
            buttons.validate();
            //System.out.println("hillo");
            buttons.repaint();
                
        }
            public void mousePressed(MouseEvent me) {}
            public void mouseReleased(MouseEvent me) {}
            public void mouseEntered(MouseEvent me) {}
            public void mouseExited(MouseEvent me) {} 
        }
        /* Class to every tile
        View tile
        Tile tempTile=new Tile //outside method//Text area
        tempTile=tileGrid[][]get hover over
        if(tempTile.occupied!=0){
            Textarea.setText=toString similar to attack
    
        */
        class TileListener extends CancelListener{
            public void mouseClicked(MouseEvent me) {
                tileGrid[(me.getComponent().getY()+(me.getComponent().getY()*3/50))/50][me.getComponent().getX()/50].calculateMoveCost(characterList[turnCounter].getX(),characterList[turnCounter].getY());
                tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].eraseCharacter();
                characterList[turnCounter].setY(me.getComponent().getX()/50);
                //System.out.println(characterList[turnCounter].getX());
                characterList[turnCounter].setX((me.getComponent().getY()+(me.getComponent().getY()*3/50))/50);
                //System.out.println(characterList[turnCounter].getY());
                characterList[turnCounter].spendMoves(tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].moveDistance);
                tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].drawCharacter(characterList[turnCounter].getPicture(),characterList[turnCounter].getTeam());
                tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].turnCharacter();
                //System.out.println(tileGrid[(me.getComponent().getY()+(me.getComponent().getY()*3/50))/50][me.getComponent().getX()/50].occupied);
                super.mouseClicked(me);
            }
        }
        class DamageListener extends CancelListener{
            public void mouseClicked(MouseEvent me) {
                int tempY=me.getComponent().getX()/50;
                int tempX=(me.getComponent().getY()+(me.getComponent().getY()*3/50))/50;
                /*for(int r=0;r<characterList[turnCounter].getBlastRange;r++){
                    for(int j=0;j<characterList[turnCounter].getBlastRange-r;j++){
                */
                /*
                
                */
                flowerDamage(characterList[turnCounter].getBlastRange()-1,tempX,tempY);
                System.out.println("attacked");
                characterList[turnCounter].spendAttacks();
                super.mouseClicked(me);
            }
            public void mouseEntered(MouseEvent me) {
                drawFlower(characterList[turnCounter].getBlastRange(),(me.getComponent().getY()+(me.getComponent().getY()*3/50))/50,me.getComponent().getX()/50,1);
            }
            public void mouseExited(MouseEvent me) {
                drawFlower(characterList[turnCounter].getBlastRange(),(me.getComponent().getY()+(me.getComponent().getY()*3/50))/50,me.getComponent().getX()/50,5);
            } 
        }
        class MoveListener extends ChoiceListener{
            public void mouseClicked(MouseEvent me) {
                
                moveMap(characterList[turnCounter].getMoves(),characterList[turnCounter].getX(),characterList[turnCounter].getY());
                for(int x=0;x<10;x++){
                    for(int y=0;y<10;y++){
                        if(tileGrid[x][y].mapValue==-1){
                            tileGrid[x][y].drawTarget(0);
                        }
                    }
                }
                
                /*
                for(int x=0;x<10;x++){
                    for(int y=0;y<10;y++){
                        if(tileGrid[x][y].drawMove(characterList[turnCounter].getMoves(),characterList[turnCounter].getX(),characterList[turnCounter].getY())){
                            tileGrid[x][y].addMouseListener(tileL);
                        }
                    }
                }
*/
                //
                super.mouseClicked(me);
            }
        }
        class AttackListener extends ChoiceListener{
            public void mouseClicked(MouseEvent me) {
                if(characterList[turnCounter].getAttacks()>0){
                    if(characterList[turnCounter].getBlastRange()>1){
                        for(int x=0;x<10;x++){
                            for(int y=0;y<10;y++){
                                tileGrid[x][y].drawAttack(characterList[turnCounter].getRange(),characterList[turnCounter].getX(),characterList[turnCounter].getY(),characterList[turnCounter].getTeam());
                                if(tileGrid[x][y].moveDistance<=characterList[turnCounter].getRange())
                                    tileGrid[x][y].addMouseListener(tileD);
                            }
                        }
                    }
                    else{
                        for(int x=0;x<10;x++){
                            for(int y=0;y<10;y++){
                                if(tileGrid[x][y].drawAttack(characterList[turnCounter].getRange(),characterList[turnCounter].getX(),characterList[turnCounter].getY(),characterList[turnCounter].getTeam())){
                                    tileGrid[x][y].addMouseListener(tileD);
                                }
                            }
                        }
                    }
                }
                else{
                    for(int x=0;x<10;x++){
                        for(int y=0;y<10;y++){
                            if(characterList[turnCounter].getX()==x&&characterList[turnCounter].getY()==y){
                                
                            }
                            else{
                                tileGrid[x][y].drawTarget(0);
                            }
                        }
                    }
                }
                
                super.mouseClicked(me);
            }
        }
        
        class ItemsListener extends ChoiceListener{
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
            }
        }
        class CancelListener extends ChoiceListener{
            public void mouseClicked(MouseEvent me) {
                for(int x=0;x<10;x++){
                    for(int y=0;y<10;y++){
                        tileGrid[x][y].restore();
                        tileGrid[x][y].removeMouseListener(tileL);
                        tileGrid[x][y].removeMouseListener(tileD);
                    }
                }
                super.mouseClicked(me);
            }
        }
        class PassListener extends ChoiceListener{
            public void mouseClicked(MouseEvent me) {
                tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].drawPass();
                //edit
                
                
                do{
                    
                    if(turnCounter==characterList.length-1){
                        //game over conditions
                        if(team1==0){
                            System.out.println("Evil Wins!");
                        }
                        if(team2==0){
                            System.out.println("Good Wins!");
                        }
                        for(int x=0;x<characterList.length;x++){
                            tileGrid[characterList[x].getX()][characterList[x].getY()].turnReset();
                        }
                        team1=0;team2=0;
                    }
                    turnCounter=(turnCounter+1)%characterList.length;
                }while(characterList[turnCounter].getHealth()<1);
                if(characterList[turnCounter].getTeam()==1)
                    team1++;
                else
                    team2++;
                //System.out.println(team1+","+team2);
                tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].turnCharacter();
                characterList[turnCounter].startTurn();
            }
        }
        
        
    ContentPanel(){
        
        tileL=new TileListener();
        tileD=new DamageListener();
        setLayout(new GridLayout(2,1));
        options=new JPanel();
        map=new JPanel();
        buttons=new JPanel();
        info=new JPanel();
        map.setLayout(new GridLayout(10,10));
        addTiles();
        buttons.setLayout(new GridLayout(1,4));
        options.setLayout(new GridLayout(2,1));
        add(map);
        b1=new JButton("Move");
        b1.addMouseListener(new MoveListener());
        buttons.add(b1);
        b2=new JButton("Attack");
        b2.addMouseListener(new AttackListener());
        buttons.add(b2);
        b3=new JButton("Items");
        b3.addMouseListener(new ItemsListener());
        buttons.add(b3);
        b4=new JButton("Pass");
        b4.addMouseListener(new PassListener());
        buttons.add(b4);
        cancel=new JButton("Cancel");
        cancel.addMouseListener(new CancelListener());
        state=0;
        options.add(info);
        options.add(buttons);
        add(options);
        
        
        AA=new AttackAnimation();
        //create Characters
        characterList=new Character[12];//edit
        //(speed,#ofattacks,health,damage,range,blastRange,x,y,side,name,image)
        characterList[0]=new Character(4,4,3,1,7,3,0,1,1,"Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\Hero1Forward.png"));
        characterList[1]=new Character(4,2,3,1,3,4,0,8,-1,"Evil Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\EvilHero1Forward.png"));
        //
        characterList[2]=new Character(4,2,3,1,1,1,1,1,1,"Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\Hero1Forward.png"));
        characterList[3]=new Character(4,2,3,1,1,1,0,9,-1,"Evil Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\EvilHero1Forward.png"));
        characterList[4]=new Character(4,2,3,1,1,1,2,1,1,"Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\Hero1Forward.png"));
        characterList[5]=new Character(4,2,3,1,1,1,2,8,-1,"Evil Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\EvilHero1Forward.png"));
        characterList[6]=new Character(4,2,3,1,1,1,3,1,1,"Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\Hero1Forward.png"));
        characterList[7]=new Character(4,2,3,1,1,1,1,8,-1,"Evil Steve",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\EvilHero1Forward.png"));
        //
        characterList[8]=new Character(8,1,2,4,1,1,2,0,1,"Assassin",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\Assassin1.png"));
        characterList[9]=new Character(8,1,2,4,1,1,2,9,-1,"Evil Assassin",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\EvilAssassin1.png"));
        characterList[10]=new Character(2,1,2,1,3,1,1,0,1,"Elf",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\elf.png"));
        characterList[11]=new Character(2,1,2,1,3,1,1,9,-1,"Evil Elf",Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\EvilElf.png"));
        
        for(int x=0;x<characterList.length;x++){
            tileGrid[characterList[x].getX()][characterList[x].getY()].drawCharacter(characterList[x].getPicture(),characterList[x].getTeam());
        }
        
        
        //start turns
        /*
        if(characterList[turnCounter].getTeam()==1)
            team1++;
        else
            team2++;
*/
        
        tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].turnCharacter();
        characterList[turnCounter].startTurn();
        //continues turns in passListener
    }
    private void addTiles(){
        for(int x=0;x<10;x++){
            for(int y=0;y<10;y++){
                tileGrid[x][y]=new Tile(x,y);
                map.add(tileGrid[x][y]);
            }
        }
    }
    private void turn(){
        
    }
    public void moveMap(int num,int gx,int gy){//recursivly tries all move options in a flower
        if(tileGrid[gx][gy].getMouseListeners().length==0){
            tileGrid[gx][gy].addMouseListener(tileL);
        }
        if(tileGrid[gx][gy].mapValue<num){
            tileGrid[gx][gy].mapValue=num;
            if(gx>0&&num>0){
                if(tileGrid[gx-1][gy].validMove())
                    moveMap(num-1,gx-1,gy);
            }
            if(gx<9&&num>0){//based on current size
                if(tileGrid[gx+1][gy].validMove())
                    moveMap(num-1,gx+1,gy);
            }
            if(gy>0&&num>0){
                if(tileGrid[gx][gy-1].validMove())
                    moveMap(num-1,gx,gy-1);
            }
            if(gy<9&&num>0){
                if(tileGrid[gx][gy+1].validMove())
                    moveMap(num-1,gx,gy+1);
            }
        }
        
    }
    public void drawFlower(int rng,int gx,int gy,int pic){//editing
        for(int x=0;x<rng;x++){
            for(int y=0;y<rng-x;y++){
                if(gx+x<10&&gy+y<10){//quad 1
                    tileGrid[gx+x][gy+y].drawTarget(pic);
                }
                if(gx-x>=0&&gy+y<10){//quad 2
                    tileGrid[gx-x][gy+y].drawTarget(pic);
                }
                if(gx+x<10&&gy-y>=0){//quad 4
                    tileGrid[gx+x][gy-y].drawTarget(pic);
                }
                if(gx-x>=0&&gy-y>=0){//quad 3
                    tileGrid[gx-x][gy-y].drawTarget(pic);
                }
            }
        }
    }
    
    public void flowerDamage(int rng, int gx, int gy){
        int x=0,c=0,v=0,s=0,r=0;
        for(x=0;x<=rng;x++){
            if(x==0){
                hurtTile(gx-x,gy);
                //tileGrid[gx-x][gy].drawTarget(0);
                System.out.println(gx-x+","+gy);
            }

            for(c=0;c<x;c++){
                if(gx-x+c>=0&&gx-x+c<10&&gy-c>=0&&gy-c<10){
                    hurtTile(gx-x+c,gy-c);
                    //tileGrid[gx-x+c][gy-c].drawTarget(0);
                    System.out.println(gx-x+c+","+(gy-c));
                }
            }
            for(v=0;v<x;v++){
                if(gx-x+c+v>=0&&gx-x+c+v<10&&gy-c+v>=0&&gy-c+v<10){
                    hurtTile(gx-x+c+v,gy-c+v);
                    //tileGrid[gx-x+c+v][gy-c+v].drawTarget(0);
                    System.out.println(gx-x+c+v+","+(gy-c+v));
                }
            }
            for(s=0;s<x;s++){
                if(gx-x+c+v-s>=0&&gx-x+c+v-s<10&&gy-c+v+s>=0&&gy-c+v+s<10){
                    hurtTile(gx-x+c+v-s,gy-c+v+s);
                    //tileGrid[gx-x+c+v-s][gy-c+v+s].drawTarget(0);
                    System.out.println(gx-x+c+v-s+","+(gy-c+v+s));
                }
            }
            for(r=0;r<x;r++){
                if(gx-x+c+v-s-r>=0&&gx-x+c+v-s-r<10&&gy-c+v+s-r>=0&&gy-c+v+s-r<10){
                    hurtTile(gx-x+c+v-s-r,gy-c+v+s-r);
                    //tileGrid[gx-x+c+v-s-r][gy-c+v+s-r].drawTarget(0);
                    System.out.println(gx-x+c+v-s-r+","+(gy-c+v+s-r));
                }
            }
        }
    }

    public void hurtTile(int tx,int ty){
        if(tileGrid[tx][ty].occupied!=0){
            for(int a=0;a<characterList.length;a++){
                if(characterList[a].getX()==tx&&characterList[a].getY()==ty){
                    //battleAnime
                    AA.addBattlers(characterList[turnCounter].getPicture(),characterList[a].getPicture(),characterList[turnCounter].getDamage(),tileGrid[characterList[turnCounter].getX()][characterList[turnCounter].getY()].getBack(),tileGrid[tx][ty].getBack());
                    switch(characterList[a].doDamaged(characterList[turnCounter].getDamage())){
                        case 0:break;
                        case 1:tileGrid[tx][ty].drawCharacter(characterList[a].getPicture(),characterList[a].getTeam());break;//repaint if dead
                        case 2:tileGrid[tx][ty].drawCharacter(characterList[a].getPicture(),0);
                        tileGrid[tx][ty].drawTarget(4);break;//clear tile if overkill
                    } 
                }
            }
        }
    }
    
}
