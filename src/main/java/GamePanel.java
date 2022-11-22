import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel{

    int width;
    int height;

    public GamePanel(int w, int h){
        this.width = w;
        this.height = h;

        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
    }

}