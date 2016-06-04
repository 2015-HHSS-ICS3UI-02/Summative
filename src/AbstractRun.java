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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author tatad6701
 */
public class AbstractRun extends JComponent implements KeyListener, MouseListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // Create player(s) 
    Rectangle P1 = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 30, 30);
    // Ball control and speed
    int[] moveX = {1, -1, 1, -1};
    int[] moveY = {1, 1, -1, -1};
    int speed = 15;
    // Create the minions
    Rectangle minion = new Rectangle(300, 150, 10, 10);
    Rectangle minion2 = new Rectangle(600, 500, 10, 10);
    Rectangle minion3 = new Rectangle(100, 300, 10, 10);
    Rectangle minion4 = new Rectangle(2, 600, 10, 10);
    // Create array for minions to be easily drawn
    Rectangle[] minions = {minion, minion2, minion3, minion4};
    // Player controls
    boolean p1Up = false;
    boolean p1Down = false;
    boolean p1Right = false;
    boolean p1Left = false;
    // Starting the game 
    boolean alt = false;
    // Display the players health
    int health = 100;
    // Health square integers
    Rectangle health1 = new Rectangle(6, 5, 20, 20);
    Rectangle health2 = new Rectangle(26, 5, 20, 20);
    Rectangle health3 = new Rectangle(46, 5, 20, 20);
    Rectangle health4 = new Rectangle(66, 5, 20, 20);
    // Game Font
    Font gameFont = new Font("Arial", Font.PLAIN, 14);
    // Create background image for the player and game itself
    BufferedImage Triangles = ImageHelper.loadImage("Abstract 1.jpg");
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        // Draw background image
        g.drawImage(Triangles, 0, 0, 800, 600, null);
        // Menu options at the beginning of the game
        if (alt) {
            // Game modes option
            g.setColor(Color.DARK_GRAY);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(340, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Game Over! ", 376, 300);
            // 'Game Modes' stamps
            g.fillOval(340, 280, 7, 7);
            g.fillOval(472, 280, 7, 7);
            g.fillOval(340, 302, 7, 7);
            g.fillOval(472, 302, 7, 7);
            // 'Options'
            g.setColor(Color.DARK_GRAY);
            g.fillRect(569, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Options", 615, 300);
            // 'Options' stamps
            g.fillOval(569, 280, 7, 7);
            g.fillOval(701, 280, 7, 7);
            g.fillOval(569, 302, 7, 7);
            g.fillOval(701, 302, 7, 7);
            // 'Game Controls'
            g.setColor(Color.DARK_GRAY);
            g.fillRect(110, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Game Controls", 135, 300);
            // 'Game Controls' stamps
            g.fillOval(110, 280, 7, 7);
            g.fillOval(242, 280, 7, 7);
            g.fillOval(110, 302, 7, 7);
            g.fillOval(242, 302, 7, 7);
        } else {
            if (health > 0) {
                // Create player ball
                g.setColor(Color.DARK_GRAY);
                g.fillOval(P1.x, P1.y, P1.width, P1.height);

                // Create first minion
                g.setColor(Color.white);
                for (int i = 0; i < minions.length; i++) {
                    g.fill3DRect(minions[i].x, minions[i].y, minions[i].width, minions[i].height, true);
                }

                // Draw health
                // Health background rectangle
                // First health square
                g.setColor(Color.red);
                g.fillRect(6, 5, 20, 20);
                // Second health square
                g.setColor(Color.red);
                g.fillRect(26, 5, 20, 20);
                // Third health square
                g.setColor(Color.red);
                g.fillRect(46, 5, 20, 20);
                // Fourth health square
                g.setColor(Color.red);
                g.fillRect(66, 5, 20, 20);
                g.setColor(Color.white);
                g.setFont(gameFont);
                g.drawString("Health: " + health, 10, 20);
            } else if (health <= 0) {
                // What happens at the end of the game
                // You died! Game Over!
                g.setColor(Color.DARK_GRAY);
                g.fillRect(340, 280, 140, 30);
                g.setColor(Color.cyan);
                g.setFont(gameFont);
                g.drawString("Game Over! ", 376, 300);
                // Play again?
                g.setColor(Color.DARK_GRAY);
                g.fillRect(110, 280, 140, 30);
                g.setColor(Color.cyan);
                g.setFont(gameFont);
                g.drawString("Play Again", 150, 300);
                // Game Modes
                g.setColor(Color.DARK_GRAY);
                g.fillRect(569, 280, 140, 30);
                g.setColor(Color.cyan);
                g.setFont(gameFont);
                g.drawString("Game Modes", 600, 300);

                //Stamps for design on each option at the end of the game
                // 'Play Again' stamps
                g.setColor(Color.CYAN);
                g.fillOval(110, 280, 7, 7);
                g.fillOval(242, 280, 7, 7);
                g.fillOval(110, 302, 7, 7);
                g.fillOval(242, 302, 7, 7);
                // 'Game Over' stamps
                g.fillOval(340, 280, 7, 7);
                g.fillOval(472, 280, 7, 7);
                g.fillOval(340, 302, 7, 7);
                g.fillOval(472, 302, 7, 7);
                // 'Game Modes' stamps
                g.fillOval(568, 280, 7, 7);
                g.fillOval(701, 280, 7, 7);
                g.fillOval(568, 302, 7, 7);
                g.fillOval(701, 302, 7, 7);

            }
        }
        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // The main game loop section
        // Game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // GAME LOGIC STARTS HERE 
            // menu logic
            if (alt) {
            } else {

                // Array that goes through all minions and collisions with screen
                for (int i = 0; i < minions.length; i++) {
                    minions[i].x = minions[i].x + moveX[i] * speed;
                    minions[i].y = minions[i].y + moveY[i] * speed;
                    // ball world collisions
                    // did bottom of ball hit bottom of screen?
                    if (minions[i].y + minions[i].height > HEIGHT) {
                        moveY[i] = -1;
                    }
                    // ball hit top of screen?
                    if (minions[i].y < 0) {
                        moveY[i] = 1;
                    }
                    // did right of ball hit right of screen?
                    if (minions[i].x + minions[i].width > WIDTH) {
                        moveX[i] = -1;
                    }
                    // ball hit left of screen?
                    if (minions[i].x < 0) {
                        moveX[i] = 1;
                    }
                }

                // make player ball move
                // if W pressed, move p1 up
                if (p1Up && P1.y > 0) {
                    P1.y = P1.y - speed;
                    // If S is pressed, move p1 down
                } else if (p1Down && P1.y + P1.height < HEIGHT) {
                    P1.y = P1.y + speed;
                }

                // If D pressed, move p1 right
                if (p1Left && P1.x > 0) {
                    P1.x = P1.x - speed;
                } else if (p1Right && P1.x + P1.width < WIDTH) {
                    P1.x = P1.x + speed;
                }

                // For loop used to interact when minions hit the player 
                for (int i = 0; i < minions.length; i++) {
                    if (minions[i].intersects(P1)) {
                        health = health - 25;
                        // If player health is 0, game ends 
                        if (health == -25) {
                            break;
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
        AbstractRun game = new AbstractRun();
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
        // Add key listener
        frame.addKeyListener(game);
        // Add mouse listener
        game.addMouseListener(game);

        // Starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Grab the keycode from the event
        int key = e.getKeyCode();

        // Figure out what key is pressed
        // If the W key being pressed
        if (key == KeyEvent.VK_W) {
            p1Up = true;
            // If the S key being pressed
        } else if (key == KeyEvent.VK_S) {
            p1Down = true;
            // If the D key being pressed
        } else if (key == KeyEvent.VK_D) {
            p1Right = true;
            // If the A key being pressed
        } else if (key == KeyEvent.VK_A) {
            p1Left = true;
        }
        // If the alt key is pressed
        if (key == KeyEvent.VK_ALT) {
            alt = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Grab the keycode from the event
        int key = e.getKeyCode();
        // Figure out what key is pressed
        // If the W key being pressed
        if (key == KeyEvent.VK_W) {
            p1Up = false;
            // If the S key being pressed
        } else if (key == KeyEvent.VK_S) {
            p1Down = false;
            // If the D key being pressed
        } else if (key == KeyEvent.VK_D) {
            p1Right = false;
            // If the A key being pressed
        } else if (key == KeyEvent.VK_A) {
            p1Left = false;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        // Create new integer for the mouse clicks
        int button = e.getButton();
        // Recognize the mouse click (BUTTON1 = Left click)
        if (button == MouseEvent.BUTTON1) {
            System.out.println("Button 1");
        }
        // Recognize the mouse click (BUTTON1 = Right click)
        if (button == MouseEvent.BUTTON3) {
            System.out.println("Button 3");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
