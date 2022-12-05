import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean spacePressed = false;
    public boolean spacereleased = true;
    public boolean rPressed = false;
    public boolean rReleased = true;
    public boolean pPressed = false;
    private boolean pReleased = true;

    @Override
    public void keyTyped(KeyEvent e) {
        // Empty because must have abstract method 'keyTyped(KeyEvent)' in 'KeyListener'
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE && !spacePressed){
            spacePressed = true;
        }

        if(key == KeyEvent.VK_R && rReleased){
            rReleased = false;
            rPressed = !rPressed;
        }

        if (key == KeyEvent.VK_P && pReleased){
            pReleased = false;
            pPressed = !pPressed;
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