import javax.swing.*;

class Main{
    public static void main(String args[]){

        MusicHelper musicHelper = new MusicHelper();
        musicHelper.playBackgroundMusic();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("XmasDash!");

        ImageIcon dinoIcon = new ImageIcon("src/files/dino1.png");
        window.setIconImage(dinoIcon.getImage());

        GamePanel panel = new GamePanel(900, 700);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //GameEngine engine = new GameEngine(800, 500);
        //engine.spawnGameThread();
    }
}