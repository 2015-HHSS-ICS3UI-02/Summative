/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author dhalt0019
 */
public class Game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // player position
    int x = 100;
    int y = 400;
    // mouse variables
    int mousex = 0;
    int mousey = 0;
    boolean keypressed = false;
    // blocks
    ArrayList<Rectangle> blocks = new ArrayList<>();
    // back background
    Rectangle screen = new Rectangle(800, 600, 50, 50);
    // player
    Rectangle android = new Rectangle(200, 200, 75, 75);
    int movex = 0;
    int movey = 0;
    boolean air = false;
    int gravity = 1;
    int count = 0;
    //keyboard variables
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean jump = false;
    boolean backjump = false;
    //score
    int score1 = 0;
    
    // android pic
    Rectangle player = new Rectangle(200, 200, 75, 75);
    BufferedImage guy = ImageHelper.loadImage("droid-logo-red.png");
    
    //marshmellow pic
    Rectangle bigblock = new Rectangle(450, 360, 200, 350);
    BufferedImage marsh = ImageHelper.loadImage("unnamed.png");
    
    //font
    Font gameFont = new Font("Times New Roman", Font.PLAIN, 40);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        g.setColor(Color.black);
        g.fillRect(x, y, 1000, 1000);
        g.fillRect(screen.x, screen.y, screen.width, screen.height);
        
        g.setColor(Color.black);
        for (Rectangle block : blocks) {

        g.fillRect(block.x, block.y, block.width, block.height);
        
        }

        g.setColor(Color.BLACK);
        g.fillRect(x, y, 50, 50);
        g.fillRect(android.x, android.y, android.width, android.height);

        if (keypressed) {
        g.setColor(Color.RED);
        g.fillRect(300, 300, 100, 100);
        
        }
        
        // score
        g.setColor(Color.BLACK);
        g.setFont(gameFont);
        g.drawString("Score: " + score1, WIDTH / 2 - 100, 50);
        
        //marshmellow and android pictures
        g.drawImage(marsh, bigblock.x, bigblock.y, bigblock.width,bigblock.height, null);
        g.drawImage(guy, player.x, player.y, player.width, player.height, null);

        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {

        blocks.add(new Rectangle(450, 400, 200, 350));  

        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            x = mousex;
            y = mousey;

            if (left) {

                movex = - 2;

            } else if (right) {

                movex = 2;

            } else {

                movex = 0;

                count++;
            }

            if (count >= 1) {

                movey = movey + gravity;
                count = 0;
            }

            //jump
            if (jump && !backjump && !air) {

                movey = -20;
                air = true;
            }
            // tracks the jumps
            backjump = jump;
            // player movement
            android.x = android.x + movex;
            android.y = android.y + movey;

            // if player becomes lower than the ground (bottom of screen)
            if (android.y + android.height > HEIGHT) {

                android.y = HEIGHT - android.height;
                movey = 0;
                air = false;
            }
            for (Rectangle block : blocks) {

                if (android.intersects(block)) {

                    Rectangle intersection = android.intersection(block);

                    // fix the X movement 
                    if (intersection.width < intersection.height) {

                        if (android.x < block.x) {

                            android.x = android.x - intersection.width;

                        } else {
                            android.x = android.x + intersection.width;
                        }
                    } else {
                        // fix the Y movement
                        if (android.y < block.y) {

                            android.y = android.y + intersection.height;
                            movey = 0;

                        } else {

                            android.y = android.y - intersection.height;
                            movey = 0;
                            air = false;
                        }
                    }
                }
            }
            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
        // creates an instance of my game
        Game game = new Game();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            right = true;

        } else if (key == KeyEvent.VK_LEFT) {
            left = true;

        } else if (key == KeyEvent.VK_SPACE) {
            jump = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            right = false;

        } else if (key == KeyEvent.VK_LEFT) {
            left = false;

        } else if (key == KeyEvent.VK_SPACE) {
            jump = false;
        } 
    }
}
