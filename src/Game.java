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
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author howen2217
 */
public class Game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    //set speed of player and boxs to 7
    int speed = 7;
    //set gravity to a speed of 1
    int gravity = 1;
    //set change in y to 0 so that the player isn't falling while on a box
    int changeY = 0;
    //setting a value for max jump
    int maxJump = 20;
    //setting up the different screens
    int screen = 0;
    //telling whether character can jump or not
    boolean canJump = false;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //inputting picture for background use
    BufferedImage background = ImageHelper.loadImage("Lava1.gif");
    BufferedImage instructions = ImageHelper.loadImage("instructions2.png");
    //created new rectangle to use as player
    Rectangle player = new Rectangle(50, 400, 70, 90);
    //player controls
    boolean p1Up = false;
    boolean p1Right = false;
    boolean p1Left = false;
    //game controls
    boolean end = false;
    boolean trigger = false;
    boolean dead = false;
    boolean spacepressed = false;
    //loading in images
    BufferedImage character = ImageHelper.loadImage("character.png");
    BufferedImage path = ImageHelper.loadImage("path.jpg");
    //setting 100 boxes to spawn
    Rectangle[] box = new Rectangle[100];
    int[] boxX = new int[100];
    int[] boxY = new int[100];
    //setting the intial box
    Rectangle box1 = new Rectangle(0, 488, 300, 30);
    //setting a triggered time
    long triggeredTime = System.currentTimeMillis();
    //setting delay
    long delay = 2000;
    //font setup
    Font getReady = new Font("Chiller", Font.BOLD, 100);
    Font space = new Font("Chiller", Font.BOLD, 60);
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        //drawing the menu screen
        if (screen == 0) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            g.setFont(getReady);
            g.setColor(Color.red);
            g.drawString("LAVA RUNNER", 120, 200);
            g.setFont(space);
            g.setColor(Color.BLACK);
            g.drawString("Press i for instructions", 155, 475);
            g.drawString("Press the spacebar to continue", 75, 550);
        }
        //drawing the instruction screen
        if (screen == 1) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            g.drawImage(instructions, 0, 0, WIDTH, HEIGHT, null);
        }
        //drawing the game screen
        if (screen == 3) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            g.drawImage(character, player.x, player.y, player.width, player.height, null);
            for (int i = 0; i < box.length; i++) {
                g.drawImage(path, box[i].x, box[i].y, box[i].width, box[i].height, null);
            }
            //setting timing for the ready set go
            g.drawImage(path, box1.x, box1.y, box1.width, box1.height, null);
            g.setColor(Color.RED);
            if (System.currentTimeMillis() - triggeredTime < 1000) {
                g.setFont(getReady);
                g.drawString("Ready", WIDTH / 2 - 75, 100);
            }
            if (System.currentTimeMillis() - triggeredTime > 1000 && System.currentTimeMillis() - triggeredTime < delay) {
                g.setFont(getReady);
                g.drawString("Set", WIDTH / 2 - 60, 100);
            }
            if (System.currentTimeMillis() - triggeredTime >= delay && System.currentTimeMillis() - triggeredTime < 3000) {
                g.setFont(getReady);
                g.drawString("Go!", WIDTH / 2 - 50, 100);
            }
        }
        //gameover screen
        if (screen == 4) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            g.setFont(getReady);
            g.setColor(Color.red);
            g.drawString("GAME OVER", 200, 200);
            g.setColor(Color.BLACK);
            g.setFont(space);
            g.drawString("Press ESC return to main menu", 75, 475);

        }
        //YOU WIN SCREEN
        if (screen == 2) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            g.setFont(getReady);
            g.setColor(Color.red);
            g.drawString("YOU WIN!", 200, 200);
            g.setColor(Color.BLACK);
            g.setFont(space);
            g.drawString("Press ESC return to main menu", 75, 475);

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
        //setting 2 ints for the box randomizer
        int count = 300;
        int count2 = 488;
        //box randomization
        for (int i = 0; i < box.length; i++) {
            boxX[i] = (int) (Math.random() * ((count + 300) - (count + 50) + 1)) + (count + 50);
            boxY[i] = (int) (Math.random() * (488 - (count2) + 1)) + (count2);
            //create boxes
            box[i] = new Rectangle(boxX[i], boxY[i], 300, 30);

            count = boxX[i] + 300;
            count2 = boxY[i] - 200;
            //setting limits for the boxes within the screen
            if (count2 < 100) {
                count2 = 100;
            }

        }

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            //restarting screen 3
            startTime = System.currentTimeMillis();
            if (dead && spacepressed) {
                box1 = new Rectangle(0, 488, 300, 30);
                player = new Rectangle(50, 400, 70, 90);
                dead = false;

                count = 300;
                count2 = 488;
                for (int i = 0; i < box.length; i++) {
                    boxX[i] = (int) (Math.random() * ((count + 300) - (count + 50) + 1)) + (count + 50);
                    boxY[i] = (int) (Math.random() * (488 - (count2) + 1)) + (count2);
                    box[i] = new Rectangle(boxX[i], boxY[i], 300, 30);

                    count = boxX[i] + 300;
                    count2 = boxY[i] - 200;
                    if (count2 < 100) {
                        count2 = 100;
                    }
                }
            }
            if (screen == 3) {
                // all your game rules and move is done in here
                // GAME LOGIC STARTS HERE 
                //if d is pushed add speed to players x to move right
                if (p1Right && player.x < WIDTH - player.width) {
                    player.x = player.x + speed;
                    //if a is pressed move player left
                } else if (p1Left && player.x > 0) {
                    player.x = player.x - speed;
                }
                //determining whether the player can jump or not
                if (p1Up && canJump) {
                    changeY = -maxJump;
                    canJump = false;
                }
                //setting up gravity
                changeY = changeY + gravity;
                player.y = player.y + changeY;
                //setting the boxes to move after the delay
                for (int i = 0; i < box.length; i++) {
                    if (System.currentTimeMillis() - triggeredTime > delay && screen == 3) {
                        box[i].x -= speed;
                    }
                    //box intersection
                    if (player.intersects(box[i])) {
                        Rectangle intersection = player.intersection(box[i]);
                        if (intersection.width < intersection.height) {
                            if (player.x < box[i].x) {
                                player.x = box[i].x - player.width;
                            } else {
                                player.x = box[i].x + box[i].width;
                            }
                        } else {
                            if (changeY >= 0) {
                                player.y = box[i].y - player.height;
                                canJump = true;
                                changeY = 0;
                            } else {
                                player.y = box[i].y + box[i].height;
                            }
                        }
                    }
                }
                //setting intial platform to move after delay
                if (System.currentTimeMillis() - triggeredTime > delay && screen == 3) {
                    box1.x = box1.x - speed;
                }
                //setting up intersection with box1
                if (player.intersects(box1)) {
                    Rectangle intersection = player.intersection(box1);
                    if (intersection.width < intersection.height) {
                        if (player.x < box1.x) {
                            player.x = box1.x - player.width;
                        } else {
                            player.x = box1.x + box1.width;
                        }
                    } else {
                        if (changeY >= 0) {
                            player.y = box1.y - player.height;
                            canJump = true;
                            changeY = 0;
                        } else {
                            player.y = box1.y + box1.height;
                        }
                    }
                }
                //game over if player is below the bottom of the screen
                if (player.y > HEIGHT) {
                    screen = 4;
                    dead = true;
                }
                //you win
                if (player.x > box[99].x) {
                    screen = 2;
                    dead = true;
                }
            }
            //RESTART   

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
        //make game listen for key listener
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //get the key code from the event
        int key = e.getKeyCode();
        //figure out what key is pressed
        //setting the w, a, d controls
        if (key == KeyEvent.VK_W) {
            p1Up = true;
        } else if (key == KeyEvent.VK_D) {
            p1Right = true;
        } else if (key == KeyEvent.VK_A) {
            p1Left = true;
            //setting spacebar controls
        } else if (key == KeyEvent.VK_SPACE && screen == 0) {
            triggeredTime = System.currentTimeMillis();
            screen = 3;
            spacepressed = true;
            //setting i controls
        } else if (key == KeyEvent.VK_I && screen == 0) {
            screen = 1;
//setting esc for all screens
        } else if (key == KeyEvent.VK_ESCAPE && screen == 1) {
            screen = 0;
        } else if (key == KeyEvent.VK_ESCAPE && screen == 4) {
            screen = 0;
        } else if (key == KeyEvent.VK_ESCAPE && screen == 2) {
            screen = 0;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //get the key code from the event
        int key = e.getKeyCode();
        //figure out what key is pressed
        //setting the released for a,w,d
        if (key == KeyEvent.VK_W) {
            p1Up = false;
        } else if (key == KeyEvent.VK_D) {
            p1Right = false;
        } else if (key == KeyEvent.VK_A) {
            p1Left = false;
            //setting release for space
        } else if (key == KeyEvent.VK_SPACE) {
            spacepressed = false;
        }
    }
}
