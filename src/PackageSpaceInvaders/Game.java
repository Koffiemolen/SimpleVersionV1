package PackageSpaceInvaders;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.image.*;


public class Game extends JPanel implements Runnable, MouseListener
{
    // Keep track of game state
    boolean inGame = true;
    // Define playing field
    private Dimension dimension;
    int BOARD_WIDTH = 500;
    int BOARD_HEIGHT = 500;
    BufferedImage img;
    String message = "Click Game to Start";
    private Thread animator;

    // Creating game objects
    Player player;
    // For test purposes Array
    // Todo: Convert to ArrayList
    Alien[] alienArmada = new Alien[10];


    public Game()
    {
        addKeyListener(new TAdapter());
        addMouseListener(this);
        setFocusable(true);
        // Create objects
        dimension = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        player = new Player(BOARD_WIDTH/2, BOARD_HEIGHT-60, 5);

        // Setting alien army start position
        int alienStartPositionX = 10;
        int alienStartPositionY = 10;

        // Generate aliens with corresponding positions of aliens in armada on screen
        /*
           x x x x x
           x x x x x
           x x x x x
        */
        for(int i = 0; i < alienArmada.length; i++){
            alienArmada[i] = new Alien(alienStartPositionX,alienStartPositionY, 5);
            alienStartPositionX += 40;
            if(i == 4){
                alienStartPositionX = 10;
                alienStartPositionY += 40;
            }
        }

        //
        if (animator == null || !inGame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        // Background settings
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, dimension.width, dimension.height);

        // Represents player
        graphics.setColor(Color.red);
        graphics.fillRect(player.getX(), player.getY(), 20, 20);

        // Controls for player
        if(player.isMoveRight() == true){
            player.setX(player.getX() + player.getSpeed());
        }
        if(player.isMoveLeft() == true){
            player.setX(player.getX() - player.getSpeed());
        }

        // Set alien armada in motion
        moveAlienArmada();

        // Drawing armada
        for(int i = 0; i < alienArmada.length; i++){
            graphics.setColor(Color.green);
            graphics.fillRect(alienArmada[i].getX(), alienArmada[i].getY(),30,30);

        }

        // Text in window
        Font small = new Font("Helvetica", Font.BOLD, 14);
        graphics.setColor(Color.white);
        graphics.setFont(small);
        graphics.drawString(message, 10, dimension.height-60);

        // TODO: Build some nice features
        if (inGame) {
        }
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    // Method for moving aliens
    // TODO: Keep all aliens within screen
    // TODO: When aliens reach bottom end game
    // TODO: Shoot back
    // TODO: Increase speed when aliens come closer to player
    // TODO: Replace x coordinate speed by variable for difficulty level
    // TODO: Replace y coordinate speed by variable for difficulty level
    public void moveAlienArmada(){
        // To keep all aliens in sync and move them at the same time
        for(int i = 0; i < alienArmada.length; i++){
            // When aliens move to left, move them with the same increment
            if(alienArmada[i].isMoveLeft() == true){
                // Move x coordinate of alien
                alienArmada[i].setX(alienArmada[i].getX() - 2);
            }
            // When aliens
            if(alienArmada[i].isMoveRight() == true){
                alienArmada[i].setX(alienArmada[i].getX() + 2);
            }
        }
        // When the far most right or left alien reaches the border the
        // whole armada should drop 1 row
        // TODO: take alien size in account (alienArmada[i].x + 40) > BOARD_WIDTH -> later on replace by variable
        // TODO: duplicate code detected, convert to method?
        for(int i = 0; i < alienArmada.length; i++) {
            // Verify if armada has reached the left side
            if ((alienArmada[i].getX() +40 )> BOARD_WIDTH) {
                // Lower each alien accordingly
                for (int j = 0; j < alienArmada.length; j++) {
                    alienArmada[j].setMoveLeft(true);
                    alienArmada[j].setMoveRight(false);
                    alienArmada[j].setY(alienArmada[j].getY() + 2);
                }
            }

            // Verify if armada has reached the right side
            if (alienArmada[i].getX() < 0) {
                // Lower each alien accordingly
                for (int j = 0; j < alienArmada.length; j++) {
                    alienArmada[j].setMoveRight(true);
                    alienArmada[j].setMoveLeft(false);
                    alienArmada[j].setY(alienArmada[j].getY() + 2);
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            player.setMoveRight(false);
            player.setMoveLeft(false);
        }

        public void keyPressed(KeyEvent e) {
            //System.out.println( e.getKeyCode());
            // message = "Key Pressed: " + e.getKeyCode();
            int key = e.getKeyCode();
            if(key==39){
                player.setMoveRight(true);
            }
            if(key==37){
                player.setMoveLeft(true);
            }
        }
    }

    // Todo: Mouse control
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void run() {
        int animationDelay = 15;
        long time = System.currentTimeMillis();
        while (true) {//infinite loop
            // spriteManager.update();
            repaint();
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0,time -
                        System.currentTimeMillis()));
            }catch (InterruptedException e) {
                System.out.println(e);
            }//end catch
        }//end while loop
    }//end of run
}//end of class
