/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import sun.audio.*;
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
    // Sets the framerate and delay for our game
    // You just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // Create player(s) 
    Rectangle P1 = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 35, 35);
    // Ball control and speed
    int[] moveX = {1, -1, 1, -1};
    int[] moveY = {1, 1, -1, -1};
    int speed = 5;
    // Screen setting :  7 Screens in total
    int screen = 0;
    // Create the minions
    Rectangle enemy = new Rectangle(70, 150, 10, 10);
    Rectangle enemy2 = new Rectangle(40, 400, 10, 10);
    Rectangle enemy3 = new Rectangle(50, HEIGHT / 2 - 5, 10, 10);
    Rectangle enemy4 = new Rectangle(5, HEIGHT / 2 - 20, 10, 10);
    // Create array for minions to be easily drawn
    Rectangle[] enemies = {enemy, enemy2, enemy3, enemy4};
    // Player controls
    boolean p1Up = false;
    boolean p1Down = false;
    boolean p1Right = false;
    boolean p1Left = false;
    // Create boolean for mouse click
    boolean button1 = false;
    // Mouse X and Y integers
    int mouseX = 0;
    int mouseY = 0;
    // Starting the game 
    boolean alt = false;
    // Display the players health
    int health = 100;
    // Game Font
    Font gameFont = new Font("Arial", Font.PLAIN, 14);
    // Create background images for the game screens and player!
    BufferedImage Triangles = ImageHelper.loadImage("Abstract 1.jpg");
    BufferedImage PlayerMinion = ImageHelper.loadImage("minion.png");
    BufferedImage StartMenu = ImageHelper.loadImage("Abstract 5.png");
    BufferedImage Abstract4 = ImageHelper.loadImage("Abstract 4.jpg");
    BufferedImage Welcome = ImageHelper.loadImage("Start Menu.jpg");
    BufferedImage AboutGame = ImageHelper.loadImage("About Game (GOOD)_1.jpg");
    BufferedImage GameControls = ImageHelper.loadImage("Game Controls.jpg");
    BufferedImage GameModes = ImageHelper.loadImage("Game Modes_1.jpg");
    BufferedImage DeathScreen = ImageHelper.loadImage("Death Screen.jpg");

    // Drawing of the game happens in here
    // We use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // Always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        // Menu options at the beginning of the game
        if (screen == 0) {
            // Welcoome to Minion Run!
            g.drawImage(Welcome, 0, 0, 800, 600, null);
            // Game modes option
            g.setColor(Color.DARK_GRAY);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(340, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Game Modes! ", 370, 300);

            // 'Game Modes' stamps
            g.fillOval(340, 280, 7, 7);
            g.fillOval(472, 280, 7, 7);
            g.fillOval(340, 302, 7, 7);
            g.fillOval(472, 302, 7, 7);

            // 'About Game'
            g.setColor(Color.DARK_GRAY);
            g.fillRect(569, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("About Game", 604, 300);

            // 'About Game' stamps
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
        }

        // Drawings for screen 1, 'About Game' screen
        if (screen == 1) {
            g.drawImage(AboutGame, 0, 0, 820, 600, null);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(550, 525, 110, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Back", 590, 545);
            g.setColor(Color.white);
            // 'Back' Stamps
            g.fillOval(550, 525, 7, 7);
            g.fillOval(652, 547, 7, 7);
            g.fillOval(652, 525, 7, 7);
            g.fillOval(550, 547, 7, 7);
        }

        // Drawings for screen 2, 'Game Controls' screen
        if (screen == 2) {
            // Draw background image of the minions/designs
            g.drawImage(GameControls, 0, 0, 800, 600, null);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(550, 525, 110, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Back", 590, 545);
            g.setColor(Color.white);
            // 'Back' Stamps
            g.fillOval(550, 525, 7, 7);
            g.fillOval(652, 547, 7, 7);
            g.fillOval(652, 525, 7, 7);
            g.fillOval(550, 547, 7, 7);
        }

        // Drawings for screen 3, 'Game Modes' screen
        if (screen == 3) {
            // Draw background image of the minions/designs
            g.drawImage(GameModes, 0, 0, 800, 600, null);

            // 'Medium' option
            g.setColor(Color.DARK_GRAY);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(340, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Medium", 387, 300);

            // 'Medium' stamps
            g.fillOval(340, 280, 7, 7);
            g.fillOval(472, 280, 7, 7);
            g.fillOval(340, 302, 7, 7);
            g.fillOval(472, 302, 7, 7);

            // 'Insane' option
            g.setColor(Color.DARK_GRAY);
            g.fillRect(569, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Insane", 620, 300);

            // 'Insane' stamps
            g.fillOval(569, 280, 7, 7);
            g.fillOval(701, 280, 7, 7);
            g.fillOval(569, 302, 7, 7);
            g.fillOval(701, 302, 7, 7);

            // 'Easy' option
            g.setColor(Color.DARK_GRAY);
            g.fillRect(110, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Easy", 161, 300);

            // 'Easy' stamps
            g.fillOval(110, 280, 7, 7);
            g.fillOval(242, 280, 7, 7);
            g.fillOval(110, 302, 7, 7);
            g.fillOval(242, 302, 7, 7);

            // 'Back' option
            g.setColor(Color.DARK_GRAY);
            g.fillRect(580, 500, 110, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Back", 618, 520);
            g.setColor(Color.white);

            // 'Back' Stamps
            g.fillOval(580, 500, 7, 7);
            g.fillOval(682, 521, 7, 7);
            g.fillOval(682, 500, 7, 7);
            g.fillOval(580, 521, 7, 7);
        }

        // 'Easy' mode in game
        if (screen == 4) {
            // Draw background image
            g.drawImage(Triangles, 0, 0, 800, 600, null);

        }
        // 'Medium' mode in game
        if (screen == 5) {
            // Draw background image
            g.drawImage(Abstract4, 0, 0, 800, 600, null);

        }
        // 'Insane' mode in game
        if (screen == 6) {
            // Draw background image
            g.drawImage(StartMenu, 0, 0, 800, 600, null);
            health = 200;
        }

        // All of the game content if screen is greater than and or equal to 4 and if screen is less than or equal to 6
        if (screen >= 4 && screen <= 6) {
            // Create player ball (the minion character
            g.setColor(Color.DARK_GRAY);
            g.fillOval(P1.x, P1.y, P1.width, P1.height);
            g.drawImage(PlayerMinion, P1.x, P1.y, P1.width, P1.height, null);

            // Array to go through each enemy draw their designs to be the same
            g.setColor(Color.white);
            for (int i = 0; i < enemies.length; i++) {
                g.fill3DRect(enemies[i].x, enemies[i].y, enemies[i].width, enemies[i].height, true);
            }
            // If health is O, then switch screens to screen 7. the death screen
            if (health == 0) {
                screen = 7;
            }

            // Draw health bar
            g.setColor(Color.red);
            // Health background rectangle
            g.fillRect(6, 5, 80 * health / 100, 20);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Health: " + health, 10, 20);
        }
        // Drawings for screen 7, 'Death Screen'
        if (screen == 7) {
            // Draw background image including the designs created with photoshop
            g.drawImage(DeathScreen, 0, 0, 800, 600, null);
            // What happens at the end of the game
            // You died! Game Over!
            g.setColor(Color.DARK_GRAY);
            g.fillRect(340, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Game Over! ", 376, 300);

            // Play again?
            g.setColor(Color.DARK_GRAY);
            g.fillRect(110, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Play Again", 150, 300);

            // Game Modes
            g.setColor(Color.DARK_GRAY);
            g.fillRect(569, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Game Modes", 600, 300);

            // Stamps for design on each option on screen 7
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

        // Drawings for screen 8, 'Pause Screen'
        if (screen == 8) {
            // Draw background image including the designs created with photoshop
            g.drawImage(DeathScreen, 0, 0, 800, 600, null);
            // Resume!
            g.setColor(Color.DARK_GRAY);
            g.fillRect(460, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Resume! ", 505, 300);

            // 'Resume' stamps
            g.setColor(Color.white);
            g.fillOval(460, 280, 7, 7);
            g.fillOval(592, 280, 7, 7);
            g.fillOval(460, 302, 7, 7);
            g.fillOval(592, 302, 7, 7);

            // 'Game Controls'
            g.setColor(Color.DARK_GRAY);
            g.fillRect(230, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Game Controls", 255, 300);

            // 'Game Controls' stamps
            g.setColor(Color.white);
            g.fillOval(230, 280, 7, 7);
            g.fillOval(362, 280, 7, 7);
            g.fillOval(230, 302, 7, 7);
            g.fillOval(362, 302, 7, 7);
        }
    }

    // GAME DRAWING ENDS HERE
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
            // If 'Game Controls' was clicked
            if (screen == 0) {
                // If 'About Game' is clicked on, the screen then becomes screen 1
                if (button1) {
                    if (mouseX > 569 && mouseX < 709 && mouseY > 280 && mouseY < 310) { //(569, 280, 140, 30)
                        screen = 1;
                    }
                }
                // If 'Game Controls' was clicked on, screen 0 changes to screen 
                if (button1) {
                    if (mouseX > 110 && mouseX < 250 && mouseY > 280 && mouseY < 310) {   //(110, 280, 140, 30)
                        screen = 2;
                    }
                }

                // If 'Game Modes' is clicked on, the screen then becomes screen 3, showing the user the game modes
                if (button1) {
                    if (mouseX > 340 && mouseX < 480 && mouseY > 280 && mouseY < 310) { // (340, 280, 140, 30)
                        screen = 3;
                    }
                    button1 = false;
                }

                // If 'Back' in 'About Game' is clicked 
            } else if (screen == 1) {
                if (button1) {
                    if (mouseX > 550 && mouseX < 660 && mouseY > 525 && mouseY < 555) {
                        screen = 0;
                    }
                } // If 'Back in 'Game Controls' is clicked
            } else if (screen == 2) {
                if (button1) {
                    if (mouseX > 550 && mouseX < 660 && mouseY > 525 && mouseY < 555) {
                        screen = 0;
                    }
                }
            } else if (screen == 3) {
                // If the 'Back' in 'Game Modes' was selected
                if (button1) {
                    if (mouseX > 580 && mouseX < 690 && mouseY > 500 && mouseY < 530) {
                        screen = 0;
                    }
                }

                // If 'Easy' mode was clicked
                if (button1) {
                    if (mouseX > 110 && mouseX < 250 && mouseY > 280 && mouseY < 310) {
                        screen = 4;
                        speed = 3;
                    }

                    // If 'Medium' mode is clicked
                    if (mouseX > 340 && mouseX < 480 && mouseY > 280 && mouseY < 310) {
                        screen = 4;
                        speed = 8;
                    }

                    // If 'Insane' mode is clicked 
                    if (mouseX > 569 && mouseX < 709 && mouseY > 280 && mouseY < 310) {
                        screen = 4;
                        health = 200;
                        speed = 20;
                    }
                    button1 = false;
                }

                // If 'Game Modes' is clicked GLICHES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            } else if (screen == 7) {
                if (button1) {
                    if (mouseX > 569 && mouseX < 709 && mouseY > 140 && mouseY < 310) {
                        screen = 3;
                    }
                    button1 = false;
                }
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            } else {
                // The game itself (player and enemy movement
                // Array that goes through all minions and collisions with screen
                for (int i = 0; i < enemies.length; i++) {
                    enemies[i].x = enemies[i].x + moveX[i] * speed;
                    enemies[i].y = enemies[i].y + moveY[i] * speed;
                    // Ball world collisions
                    // Did bottom of ball hit bottom of screen?
                    if (enemies[i].y + enemies[i].height > HEIGHT) {
                        moveY[i] = -1;
                    }
                    // Ball hit top of screen?
                    if (enemies[i].y < 0) {
                        moveY[i] = 1;
                    }
                    // Did right of ball hit right of screen?
                    if (enemies[i].x + enemies[i].width > WIDTH) {
                        moveX[i] = -1;
                    }
                    // Ball hit left of screen?
                    if (enemies[i].x < 0) {
                        moveX[i] = 1;
                    }
                }

                // Make player minion move
                // If W pressed, move p1 up
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
                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i].intersects(P1)) {
                        health = health - 25;
                        // If player health is 0, game ends 
                        if (health == -25) {
                            screen = 7;
                        }
                    }
                }

                // For loop used to interact when minions hit the player to bounce off
                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i].intersects(P1)) {
                        moveX[i] = -moveX[i];
                        moveY[i] = -moveY[i];
                    }
                }

                // If alt is pressed, screen changes to screen 8
                if (screen >= 4 && screen <= 6) {
                    if (alt) {
                        screen = 8;
                        // If 'Game Controls' is clicked
                        if (mouseX > 230 && mouseX < 370 && mouseY > 140 && mouseY < 310) { //(230, 280, 140, 30)
                            screen = 2;
                        }
                        // If 'Resume' is clicked!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        if (mouseX > 460 && mouseX < 600 && mouseY > 140 && mouseY < 310) {
                            screen = 5;
                        }
                    }else if (!alt){
                        
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
        // Creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // Creates an instance of my game
        AbstractRun game = new AbstractRun();
        // Sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // Adds the game to the window
        frame.add(game);

        // Sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // Shows the window to the user
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
        mouseX = e.getX();
        mouseY = e.getY();
        // Create new integer for the mouse clicks
        int button = e.getButton();
        // Recognize the mouse click (BUTTON1 = Left click)
        if (button == MouseEvent.BUTTON1) {
            System.out.println("Button1");
            button1 = true;
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
