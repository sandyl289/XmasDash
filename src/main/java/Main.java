import javax.swing.JFrame;

class Main{
    public static void main(String args[]){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("XmasDash!");
        window.setLocationRelativeTo(null);
        GamePanel panel = new GamePanel(900, 700);
        window.add(panel);
        window.pack();
        window.setVisible(true);
        //GameEngine engine = new GameEngine(800, 500);
        //engine.spawnGameThread();
    }
}