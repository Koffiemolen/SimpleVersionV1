package PackageSpaceInvaders;

import javax.swing.JFrame;

// TODO: import size of board from constant class
public class Starter extends JFrame {
    public Starter()
    {
        // Create a game
        add(new Game());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new Starter();
    }
}