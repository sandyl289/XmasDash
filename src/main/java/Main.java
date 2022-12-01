import javax.swing.*;

class Main{
    public static void main(String args[]){
        MusicHelper.playBackgroundMusic();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("XmasDash!");

        ImageIcon dinoIcon = new ImageIcon("src/files/dino1.png");
        window.setIconImage(dinoIcon.getImage());

        GamePanel panel = new GamePanel();
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}