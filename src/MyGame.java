/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author coulh9904
 */
public class MyGame extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 1200;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    Rectangle RUNNER = new Rectangle(40, 90, 50, 50);
    //Generates Random Number
    Random rand = new Random();
    int Random = rand.nextInt(590) + 1;
    int moveX = 1;
    int moveY = 1;
    int speed = 5;
    int Count = 0;
    int CountSave = 0;
    int DownSpeed = 10;
    int UpSpeed = 10;
    int Object1Speed = 8;
    int Object1TopSpeed = 12;
    int Object2Speed = 8;
    int Object2TopSpeed = 10;
    int Object3Speed = 0;
    int Object3TopSpeed = 10;
    int Object4Speed = 0;
    int Object4TopSpeed = 0;
    int RunnerHealth = 100;
    int RunnerHealthCounter = 5;
    boolean RunnerUP = false;
    boolean RunnerDOWN = false;
    boolean RunnerLEFT = false;
    boolean RunnerRIGHT = false;
    boolean TitleScreen = true;
    boolean DeathScreen = false;
    //Create borders
    //The thickness is so that there is no chance of the player glitching past.
    Rectangle LeftBorder = new Rectangle(0, 0, 4, HEIGHT);
    Rectangle RightBorder = new Rectangle(WIDTH - 4, 0, 10, HEIGHT);
    Rectangle TopBorder = new Rectangle(-20, -66, WIDTH + 40, 70);
    Rectangle BottomBorder = new Rectangle(0, 580, WIDTH, 600);
    Rectangle Object1 = new Rectangle(1200, Random, 30, 10);
    Rectangle Object2 = new Rectangle(1200, Random, 30, 10);
    Rectangle Object3 = new Rectangle(1200, Random, 30, 10);
    Rectangle Object4 = new Rectangle(1200, Random, 30, 10);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 

        //Black Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //Generate runner
        g.setColor(Color.WHITE);
        g.fillRect(RUNNER.x, RUNNER.y, RUNNER.width, RUNNER.height);
        //Red GAME OVER (Deathscreen
        if (DeathScreen == true) {
            g.setColor(Color.RED);
            g.drawString(("YOU"), 590, 150);
            g.drawString(("(D.E.D. - R.I.P.)"), 560, 450);
            g.drawString(("YOUR SCORE: " + Count), 552, 470);
            //D
            g.fillRect(340, 180, 25, 220);
            g.fillRect(340, 185, 140, 25);
            g.fillRect(340, 370, 140, 25);
            g.fillRect(480, 185, 25, 210);
            //I
            g.fillRect(530, 180, 25, 220);
            //E
            g.fillRect(580, 180, 25, 220);
            g.fillRect(580, 185, 140, 25);
            g.fillRect(580, 370, 140, 25);
            g.fillRect(580, 270, 140, 25);
            //D
            g.fillRect(740, 180, 25, 220);
            g.fillRect(740, 185, 140, 25);
            g.fillRect(740, 370, 140, 25);
            g.fillRect(880, 185, 25, 210);
        }

        //White Border
        g.setColor(Color.WHITE);
        g.fillRect(LeftBorder.x, LeftBorder.y, LeftBorder.width, LeftBorder.height);
        g.fillRect(RightBorder.x, RightBorder.y, RightBorder.width, RightBorder.height);
        g.fillRect(BottomBorder.x, BottomBorder.y, BottomBorder.width, BottomBorder.height);
        g.fillRect(TopBorder.x, TopBorder.y, TopBorder.width, TopBorder.height);
        //Generate the 'bullets'
        g.setColor(Color.ORANGE);
        g.fillRect(Object1.x, Object1.y, Object1.width, Object1.height);
        g.setColor(Color.BLUE);
        g.fillRect(Object2.x, Object2.y, Object2.width, Object2.height);
        g.fillRect(Object3.x, Object3.y, Object3.width, Object3.height);
        g.setColor(Color.GREEN);
        g.fillRect(Object4.x, Object4.y, Object4.width, Object4.height);
        g.setColor(Color.WHITE);

        //Print on scrren Player health
        g.drawString(("Current Health: " + RunnerHealth), 800, 40);
        g.drawString(("Time Elapsed: " + Count / 60 + " seconds"), 1000, 40);
        // GAME DRAWING ENDS HERE

        if (TitleScreen == true) {
            //Essential information on homescreen
            g.drawString(("Press Enter to Begin"), 50, 40);
            g.drawString(("<---- Your player has 100 health"), 140, 120);
            g.drawString(("The objects you are to dodge come from here ---->"), 870, 120);
            //Print damage values on titlescreen for user reference
            g.drawString(("The ORANGE object will deal 60 DAMAGE to you."), 250, 250);
            g.drawString(("The BLUE objects will deal 45 DAMAGE to you."), 250, 265);
            g.drawString(("The GREEN object will deal 20 DAMAGE to you."), 250, 280);
            Font GameFont = new Font("Arial", Font.PLAIN, 10);
            g.drawString(("THE DODGE GAME"), 500, 470);
        }
    }
    // The main game loop
    // In here is where all the logic for my game will go

    public void run() {
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
            //Runner Health doesn't go below 0
            if (RunnerHealth < 1) {
                RunnerHealth = 0;
            }
            //When runner runs out of health, deathscreen appears
            if (RunnerHealth == 0) {
                DeathScreen = true;
            }
            //Regenerate Health every 15 seconds
            if (RunnerHealthCounter == Count) {
                RunnerHealth = RunnerHealth + 15;
                RunnerHealthCounter = Count + 900;
            }
            //Doesn't allow health to regenerate past the full 100% HP
            if (RunnerHealth >= 100) {
                RunnerHealth = 100;
            }
            //Resets the scaling speeds if the game is reset
            if (Count == 0) {
                Object1TopSpeed = 12;
                Object3TopSpeed = 10;
                Object4TopSpeed = 0;
            }
            
            if (Count == 300) {
                CountSave = Count + 900;
                Random ObjectSpeed = new Random();
                Object1Speed = ObjectSpeed.nextInt(Object1TopSpeed) + 8;
                Object2Speed = ObjectSpeed.nextInt(10) + 8;
                Object2.x = Object2.x - Object2Speed;
                
            }
            if (Count == CountSave) {
                CountSave = Count + 900;

                Object1TopSpeed = Object1TopSpeed + 2;
                Object2TopSpeed = Object2TopSpeed + 1;
                Object3TopSpeed = Object3TopSpeed + 3;
                Object4TopSpeed = Object4TopSpeed + 4;
                Random Object1RandSpeed = new Random();
                Object1Speed = Object1RandSpeed.nextInt(Object1TopSpeed) + 8;
                Random Object2RandSpeed = new Random();
                Object2Speed = Object2RandSpeed.nextInt(Object2TopSpeed) + 8;
                Random Object3RandSpeed = new Random();
                Object3Speed = Object3RandSpeed.nextInt(Object3TopSpeed) + 5;
                Random Object4RandSpeed = new Random();
                Object4Speed = Object4RandSpeed.nextInt(Object4TopSpeed) + 16;
                
            }
            //Triggers Object4 at 25 seconds in
            if (Count >= 1500) {
                if (Count == CountSave) {
                    Object4TopSpeed = Object4TopSpeed + 4;
                    Random Object4RandSpeed = new Random();
                    Object4Speed = Object4RandSpeed.nextInt(Object4TopSpeed) + 16;
                }
            }

            //Reset position to right side of screen
            if (Object1.x < -100) {
                Object1.x = 1200;
                //Generate new Random number for Y on X position reset
                Random rand = new Random();
                Random = rand.nextInt(590) + 1;
                Object1.y = Random;
            }
            //Repeated for Object 2
            if (Object2.x < -100) {
                Object2.x = 1200;
                Random rand = new Random();
                Random = rand.nextInt(590) + 1;
                Object2.y = Random;
            }
            if (Object3.x < -100) {
                Object3.x = 1200;
                //Generate new Random number for Y on X position reset
                Random rand = new Random();
                Random = rand.nextInt(590) + 1;
                Object3.y = Random;
            }
            if (Object4.x < -100) {
                Object4.x = 1200;
                //Generate new Random number for Y on X position reset
                Random rand = new Random();
                Random = rand.nextInt(590) + 1;
                Object4.y = Random;
            }
            if (DeathScreen != true) {
                Object1.x = Object1.x - Object1Speed;
                Object2.x = Object2.x - Object2Speed;
                Object3.x = Object3.x - Object3Speed;
                Object4.x = Object4.x - Object4Speed;
                Count = Count + 1;
            }

            while (TitleScreen == true) {
            }
            if (DeathScreen == true) {
                //Remove plsyer and NPCs from game screen
                Object1.x = 1200;
                Object2.x = 1200;
                Object3.x = 1200;
                Object4.x = 1200;
                //Too lazy to remove Runner control, so just going to teleport it to nowhere land
                RUNNER.x = 1500000;
                Count = 0;
                CountSave = 0;
            }


            if (RUNNER.y + RUNNER.height > HEIGHT) {
                moveY = -1;
            }
            if (RUNNER.y < 0) {
                moveY = 1;
            }
            if (RUNNER.intersects(BottomBorder)) {
                moveX = -1;
            }
            if (RUNNER.x < 0) {
                moveX = 1;
            }

            //If a 'bullet' hits the object, its X postion is reset to start, and the player loses health
            if (RUNNER.intersects(Object1)) {
                Object1.x = 1200;
                RunnerHealth = RunnerHealth - 60;
            }
            if (RUNNER.intersects(Object2)) {
                Object2.x = 1200;
                RunnerHealth = RunnerHealth - 45;
            }
            if (RUNNER.intersects(Object3)) {
                Object3.x = 1200;
                RunnerHealth = RunnerHealth - 45;
            }
            if (RUNNER.intersects(Object4)) {
                Object4.x = 1200;
                RunnerHealth = RunnerHealth - 20;
            }

            if (RunnerUP == true) {
                if (RUNNER.intersects(TopBorder)) {
                    RunnerUP = false;
                    RUNNER.y = TopBorder.y + TopBorder.height;
                } else {
                    RUNNER.y = RUNNER.y - (UpSpeed);
                }
            }

            if (RunnerLEFT == true) {
                if (RUNNER.intersects(LeftBorder)) {
                    RunnerLEFT = false;
                    RUNNER.x = LeftBorder.x + LeftBorder.width;
                } else {
                    RUNNER.x = RUNNER.x - (5);
                }
            }
            if (RunnerDOWN == true) {
                if (RUNNER.intersects(BottomBorder)) {
                    RunnerDOWN = false;
                    RUNNER.y = BottomBorder.y - 40;
                } else {
                    RUNNER.y = RUNNER.y + (DownSpeed);
                }
            }
            if (RunnerRIGHT == true) {
                if (RUNNER.intersects(RightBorder)) {
                    RunnerRIGHT = false;
                    RUNNER.x = RightBorder.x - 40;
                } else {
                    RUNNER.x = RUNNER.x + (10);
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
        MyGame game = new MyGame();
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

        frame.addKeyListener(game);

        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
        //Figure out what key is pressed
        if (Key == KeyEvent.VK_UP) {
            RunnerUP = true;
        } else if (Key == KeyEvent.VK_LEFT) {
            RunnerLEFT = true;
        } else if (Key == KeyEvent.VK_RIGHT) {
            RunnerRIGHT = true;
        } else if (Key == KeyEvent.VK_DOWN) {
            RunnerDOWN = true;
        } else if (Key == KeyEvent.VK_ENTER) {
            TitleScreen = false;
        } else if (Key == KeyEvent.VK_W) {
            RunnerUP = true;
        } else if (Key == KeyEvent.VK_A) {
            RunnerLEFT = true;
        } else if (Key == KeyEvent.VK_S) {
            RunnerRIGHT = true;
        } else if (Key == KeyEvent.VK_D) {
            RunnerDOWN = true;
        }else if (Key == KeyEvent.VK_R) {
            DeathScreen = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int Key = e.getKeyCode();
        //Figure out what key is pressed
        if (Key == KeyEvent.VK_UP) {
            RunnerUP = false;
        } else if (Key == KeyEvent.VK_LEFT) {
            RunnerLEFT = false;
        } else if (Key == KeyEvent.VK_RIGHT) {
            RunnerRIGHT = false;
        } else if (Key == KeyEvent.VK_DOWN) {
            RunnerDOWN = false;
        } else if (Key == KeyEvent.VK_W) {
            RunnerUP = false;
        } else if (Key == KeyEvent.VK_A) {
            RunnerLEFT = false;
        } else if (Key == KeyEvent.VK_S) {
            RunnerRIGHT = false;
        } else if (Key == KeyEvent.VK_D) {
            RunnerDOWN = false;
        }
    }
}
