import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{

    public boolean spacePressed = false;
    public boolean spacereleased = true;
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE && !spacePressed){
            spacePressed = true;
            System.out.println(spacePressed);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE){
            spacePressed = false;
            spacereleased = true;
            System.out.println(spacePressed);

        }
        
    }
}