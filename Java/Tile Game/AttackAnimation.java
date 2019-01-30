
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AttackAnimation {

    private static final int RECTANGLE_WIDTH = 50;
    private static final int RECTANGLE_HEIGHT = 50;

    private boolean exit;
    private int xLeft;
    private int yLeft;
    private int OxLeft=335;
    private int OyTop=200;
    private String winner;
    private JFrame AA;
    private JPanel AAnime;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private Timer t;
    private Image attacker,defender,defenderBack,attackerBack;
    private Font f=new Font("Serif", Font.BOLD,40);

    public AttackAnimation(){
        AA = new JFrame();
        AA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AA.setVisible(false);
        AA.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        AAnime=new AAnimation();
        AAnime.addMouseListener(new LeaveListener());
        AA.add(AAnime);
        //AA.setFont(new Font("Serif", Font.BOLD, 24));
        //AA.getGraphics().setFont(new Font("Serif", Font.BOLD, 24));
        t = new Timer(10, new TimerListener());
    }
    public boolean animate() {
        return !t.isRunning();  
    }
    public void addBattlers(Image atk,Image def,int dmg,Image aBack,Image dBack){
        AA.setVisible(true);
        
        t.start();
        attacker=atk;
        defender=def;
        xLeft=0;
        yLeft=150;
        winner=dmg+" Points of Damage!";
        defenderBack=dBack;
        attackerBack=aBack;
        exit=false;
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!moveRectangleBy(1, 0)||exit) {
                t.stop();
                AA.dispose();
            }
        }
    }

    /**
     * Moves the rectangle by a given amount.
     *
     * @param dx the amount to move in the x-direction
     * @param dy the amount to move in the y-direction
     */
    public boolean moveRectangleBy(int dx, int dy) {
        if (xLeft + RECTANGLE_WIDTH < OxLeft+60) {
            xLeft = xLeft + dx;
            yLeft = ((xLeft*xLeft)/400)-(xLeft)+200;
            AA.repaint();
            return true;
        } else {
            return false;
            //dispose
        }
    }
    public class LeaveListener implements MouseListener{
        public void mouseClicked(MouseEvent me) {
            exit=true;
        }
        public void mousePressed(MouseEvent me) {}
        public void mouseReleased(MouseEvent me) {}
        public void mouseEntered(MouseEvent me) {}
        public void mouseExited(MouseEvent me) {}
    }
    public class AAnimation extends JPanel{
        public void paintComponent(Graphics page) {
            page.setFont(f);
            
            switch(xLeft%2){
                case 0:page.setColor(Color.RED);break;
                case 1:page.setColor(Color.YELLOW);
            }
            
            page.drawImage(attackerBack,0,0,FRAME_WIDTH/2,FRAME_HEIGHT,AA);
            page.drawImage(defenderBack,FRAME_WIDTH/2,0,FRAME_WIDTH/2,FRAME_HEIGHT,AA);
            page.drawImage(defender, /*x*/OxLeft, OyTop, 50, 50, AA);
            page.drawString(winner, 20, 110);
            page.drawImage(attacker, xLeft, yLeft, 50, 50, AA);
            
        }
    }
}
