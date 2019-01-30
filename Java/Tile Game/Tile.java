
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Tile extends JPanel {
    private boolean enterable=true;
    int occupied=0,mapValue=-1;
    private final int x, y;
    public int moveDistance;
    private Image back,extra,person,valid,target,spent;
    private Image plains = Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\GrassLand.png");
    private Image dirt = Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\dirt.png");
    private Image mountain = Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\mountain.png");
    private Image invalid = Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\invalid.png");
    private Image aim=Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\act.png");
    private Image burst=Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\evilEye.png");
    private Image tired=Toolkit.getDefaultToolkit().getImage("C:\\Users\\theke\\Desktop\\music\\tired.png");

    Tile(int a, int b) {
        switch (a) {
            case 0:
                switch (b) {
                    case 0:
                        back = dirt;
                        break;
                    case 1:
                        back = dirt;
                        break;
                    case 2:
                        back = dirt;
                        break;
                    case 3:
                        back = dirt;
                        break;
                    case 4:
                        back = dirt;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }break;
            case 1:
                switch (b) {
                    case 0:
                        back = dirt;
                        break;
                    case 1:
                        back = dirt;
                        break;
                    case 2:
                        back = dirt;
                        
                        break;
                    case 3:
                        back = dirt;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        extra=mountain;
                        enterable=false;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }
                break;
            case 2:
                switch (b) {
                    case 0:
                        back = dirt;
                        break;
                    case 1:
                        back = dirt;
                        break;
                    case 2:
                        back = dirt;

                        break;
                    case 3:
                        back = dirt;
                        break;
                    case 4:
                        back = dirt;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }
                break;
            case 3:
                switch (b) {
                    case 0:
                        back = dirt;
                        break;
                    case 1:
                        back = dirt;
                        break;
                    case 2:
                        back = dirt;

                        break;
                    case 3:
                        back = plains;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }break;
            case 4:
                switch (b) {
                    case 0:
                        back = dirt;
                        break;
                    case 1:
                        back = plains;
                        break;
                    case 2:
                        back = plains;

                        break;
                    case 3:
                        back = plains;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }break;
            case 5:
                switch (b) {
                    case 0:
                        back = plains;
                        break;
                    case 1:
                        back = plains;
                        break;
                    case 2:
                        back = plains;

                        break;
                    case 3:
                        back = plains;
                        extra=mountain;
                        enterable=false;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }break;
                case 6:
                switch (b) {
                    case 0:
                        back = plains;
                        break;
                    case 1:
                        back = plains;
                        break;
                    case 2:
                        back = plains;

                        break;
                    case 3:
                        back = plains;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }break;
                case 7:
                switch (b) {
                    case 0:
                        back = plains;
                        break;
                    case 1:
                        back = plains;
                        break;
                    case 2:
                        back = plains;

                        break;
                    case 3:
                        back = plains;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }break;
                case 8:
                switch (b) {
                    case 0:
                        back = plains;
                        break;
                    case 1:
                        back = plains;
                        break;
                    case 2:
                        back = plains;

                        break;
                    case 3:
                        back = plains;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }break;
                case 9:
                switch (b) {
                    case 0:
                        back = plains;
                        break;
                    case 1:
                        back = plains;
                        break;
                    case 2:
                        back = plains;

                        break;
                    case 3:
                        back = plains;
                        break;
                    case 4:
                        back = plains;
                        break;
                    case 5:
                        back = plains;
                        break;
                    case 6:
                        back = plains;
                        break;
                    case 7:
                        back = plains;
                        break;
                    case 8:
                        back = plains;
                        break;
                    case 9:
                        back = plains;
                        break;
                }
                break;
        }
        setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black)));
        x = a;
        y = b;
        repaint();
    }

    public void drawTile() {
        
    }
    public void drawCharacter(Image pic,int team) {
        occupied=team;
        person=pic;
        repaint();
    }
    public void turnCharacter(){
        setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.RED)));
        repaint();
    }
    public void drawPass(){
        setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black)));
        spent=tired;
    }
    public boolean drawMove(int speed, int charX,int charY){  
        moveDistance=Math.abs(x-charX)+Math.abs(y-charY);
        if(!enterable||(occupied!=0&&moveDistance!=0)||moveDistance>speed){
            valid=invalid;
            repaint();
            return false;
        }
        return true;
    }
    public void eraseCharacter(){
        person=null;
        occupied=0;
        setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black)));
        repaint();
    }
    public void restore(){
        valid=null;
        mapValue=-1;
        target=null;
        repaint();
    }
    public boolean drawAttack(int rng, int charX,int charY,int team){
        moveDistance=Math.abs(x-charX)+Math.abs(y-charY);
        if(moveDistance>rng){
            valid=invalid;
            repaint();
            return false;
        }
        if((occupied==-team&&moveDistance!=0)){//edit for enemies
            target=aim;
            repaint();
            return true;
        }
        repaint();
        return false;
    }
    public boolean validMove(){
        return enterable&&occupied==0;
    }
    public void drawTarget(int type){
        switch(type){
            case 0:valid=invalid;break;
            case 1:target=burst;break;
            case 2:spent=tired;break;
            case 4:spent=null;
            case 5:target=null;break;
        }
        repaint();
    }
    public void makeinvalid(){
        valid=invalid;
        repaint();
    }
    public Image getBack(){
        return back;
    }
    public void calculateMoveCost(int charX,int charY){
        moveDistance=Math.abs(x-charX)+Math.abs(y-charY);
    }
    public void turnReset(){
        spent=null;
        repaint();
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        page.drawImage(back, /*x*/0, /*y*/0, 50, 50, this);
        page.drawImage(extra, /*x*/0, /*y*/0, 50, 50, this);
        page.drawImage(person, /*x*/0, /*y*/0, 50, 50, this);
        page.drawImage(spent, /*x*/0, /*y*/0, 50, 50, this);
        page.drawImage(valid, /*x*/0, /*y*/0, 50, 50, this);
        page.drawImage(target, /*x*/0, /*y*/0, 50, 50, this);
    }

}
