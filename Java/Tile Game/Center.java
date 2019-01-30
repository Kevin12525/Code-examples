

import javax.swing.JFrame;


public class Center {
    public static void main(String[] agrs){
        JFrame frame=new JFrame();
        frame.setSize(520, 1000);
        frame.setResizable(false);
        frame.add(new ContentPanel());
        frame.setTitle("My first frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
