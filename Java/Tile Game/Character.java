
import java.awt.Image;
import java.awt.Toolkit;


public class Character {
    private int moves,speed,attacks,attacksMax,health,damage,range,blastRange,x,y,team;
    private String name;
    private Image picture;
    private Image grave=Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\grave.png");
    public Character(int sp,int aM,int hp,int dmg,int rng,int BR,int ex,int eye,int side,String n,Image pic){
        speed=sp;
        attacksMax=aM;
        health=hp;
        damage=dmg;
        range=rng;
        blastRange=BR;
        name=n;
        picture=pic;
        x=ex;
        y=eye;
        team=side;
    }
    public void startTurn(){
        moves=speed;
        attacks=attacksMax;
    }
    public int getTeam(){
        return team;
    }
    public Image getPicture(){
        return picture;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getMoves(){
        return moves;
    }
    public int getRange(){
        return range;
    }
    public int getBlastRange(){
        return blastRange;
    }
    public int getAttacks(){
        return attacks;
    }
    public int getDamage(){
        return damage;
    }
    public int getHealth(){
        return health;
    }
    public int doDamaged(int harm){
        health-=harm;
        if(health<1){
            if(health<-3){
                picture=null;
                return 2;
            }
            picture=grave;
            return 1;
        }
        return 0;
    }
    
    public void setX(int ex){
        x=ex;
    }
    public void setY(int eye){
        y=eye;
    }
    public void spendMoves(int cost){
        moves-=cost;
    }
    public void spendAttacks(){
        attacks--;
    }
}
