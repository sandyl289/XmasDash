import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;


public class KeyHandler implements KeyListener{

    public boolean spacePressed = false;
    public boolean spacereleased = true;
    public boolean rPressed = false;
    public boolean rReleased = true;
    public boolean pPressed = false;
    private boolean pReleased = true;
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE && !spacePressed){
            spacePressed = true;
        }

        if(key == KeyEvent.VK_R && rReleased){
            rReleased = false;
            if (rPressed == true){
                rPressed = false;
            }
            else{
                rPressed = true;
            }
        }

        if (key == KeyEvent.VK_P && pReleased){
            pReleased = false;
            if (pPressed == true){
                pPressed = false;
            }
            else{
                pPressed = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE){
            spacePressed = false;
            spacereleased = true;

        }

        if (key == KeyEvent.VK_P){
            pReleased = true;
        }

        if (key == KeyEvent.VK_R){
            rReleased = true;
        }
    }
}