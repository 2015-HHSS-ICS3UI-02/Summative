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
import static java.awt.image.ImageObserver.ERROR;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author halll7908
 */
public class OnlyTonight extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 610;
    static final int HEIGHT = 610;
    // images
    // map
    BufferedImage background = ImageHelper.loadImage("Game Map.jpg");
    // theo
    BufferedImage theoStandRight = ImageHelper.loadImage("Theo.Stand.Right.png");
    BufferedImage theoStandLeft = ImageHelper.loadImage("Theo.Stand.Left.png");
    BufferedImage theoStandUp = ImageHelper.loadImage("Theo.Stand.Up.png");
    BufferedImage theoStandDown = ImageHelper.loadImage("Theo.Stand.Down.png");
    BufferedImage dialogwai = ImageHelper.loadImage("Dialog.WhereAmI.png");
    BufferedImage dialogexclm = ImageHelper.loadImage("Dialog.ExclamationMark.png");
    BufferedImage dialogqm = ImageHelper.loadImage("Dialog.QuestionMark.png");
    BufferedImage dialogF = ImageHelper.loadImage("Dialog.Finally.png");
    BufferedImage typeWriterScreen = ImageHelper.loadImage("TypeWriter.jpg");
    BufferedImage letterScreen = ImageHelper.loadImage("Letter.jpg");
    BufferedImage safeScreen = ImageHelper.loadImage("SafeCodePad.jpg");
    BufferedImage titleScreen = ImageHelper.loadImage("OT.TitleScreen.jpg");
    BufferedImage helpScreen = ImageHelper.loadImage("OT.HelpScreen.jpg");
    BufferedImage credits = ImageHelper.loadImage("Credits.jpg");
    BufferedImage story1 = ImageHelper.loadImage("Story1.jpg");
    BufferedImage story2 = ImageHelper.loadImage("Story2.jpg");
    BufferedImage story3 = ImageHelper.loadImage("Story3.jpg");
    BufferedImage story4 = ImageHelper.loadImage("Story4.jpg");
    BufferedImage story5 = ImageHelper.loadImage("Story5.jpg");
    BufferedImage story6 = ImageHelper.loadImage("Story6.jpg");
    BufferedImage story7 = ImageHelper.loadImage("Story7.jpg");
    BufferedImage story8 = ImageHelper.loadImage("Story8.jpg");
    BufferedImage story9 = ImageHelper.loadImage("Story9.jpg");
    BufferedImage story10 = ImageHelper.loadImage("Story10.jpg");
    BufferedImage OD1 = ImageHelper.loadImage ("OpeningDialog1.jpg");
    BufferedImage OD2 = ImageHelper.loadImage ("OpeningDialog2.jpg");
    BufferedImage OD3 = ImageHelper.loadImage ("OpeningDialog3.jpg");
    BufferedImage OD4 = ImageHelper.loadImage ("OpeningDialog4.jpg");
    BufferedImage OD5 = ImageHelper.loadImage ("OpeningDialog5.jpg");
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // Title Screen                   (x, y, top, side)
    Rectangle selectPlay = new Rectangle(72, 385, 165, 55);
    Rectangle selectHelp = new Rectangle(342, 385, 165, 55);
    Rectangle selectStory = new Rectangle(72, 471, 165, 55);
    Rectangle selectCredits = new Rectangle(342, 471, 165, 55);
    // room                       
    Rectangle room = new Rectangle(31, 31, 547, 547);
    // wall 1
    Rectangle wall1 = new Rectangle(60, 342, 250, 12);
    // wall 2
    Rectangle wall2 = new Rectangle(300, 68, 12, 200);
    // wall 3
    Rectangle wall3 = new Rectangle(383, 240, 160, 12);
    // pong table
    Rectangle pongTable = new Rectangle(399, 349, 90, 136);
    Rectangle pongTrigger = new Rectangle(389, 343, 110, 156);
    // table 1
    Rectangle table1 = new Rectangle(62, 238, 69, 63);
    Rectangle table1Trigger = new Rectangle(131, 238, 10, 63);
    // table 2
    Rectangle table2 = new Rectangle(94, 68, 31, 34);
    // table 3
    Rectangle table3 = new Rectangle(246, 68, 31, 34);
    // bed
    Rectangle bed = new Rectangle(138, 68, 90, 139);
    // desk
    Rectangle desk = new Rectangle(322, 68, 90, 54);
    Rectangle deskTrigger = new Rectangle(322, 121, 90, 10);
    // safe
    Rectangle safe = new Rectangle(466, 68, 74, 69);
    Rectangle safeTrigger = new Rectangle(466, 137, 74, 10);
    // movement
    int speed = 2;
    // theo
    Rectangle theo = new Rectangle(80, 425, 38, 53);
    // theo controls
    boolean theoUp = false;
    boolean theoDown = false;
    boolean theoLeft = false;
    boolean theoRight = false;
    boolean spaceBar = false;
    boolean backspace = false;
    int enterKey = 0;
    int page = 0;
    char dir = 'r';
    int menuSelection = 0;
    // custom colours
    Color brown = new Color(59, 37, 9);
    Color darkBrown = new Color(51, 25, 0);
    Color redOrange = new Color(255, 100, 0);
    // countdown meter
    int countdown = 720;
    // game font
    Font otType = new Font("Arial", Font.BOLD, 40);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        // titlescreen code
        if (enterKey == 0) {
            g.drawImage(titleScreen, 0, 0, WIDTH, HEIGHT, null);
            g.setColor(redOrange);
            if (menuSelection == 1) {
                g.drawRect(selectHelp.x, selectHelp.y, selectHelp.width, selectHelp.height);
            } else if (menuSelection == 2) {
                g.drawRect(selectStory.x, selectStory.y, selectStory.width, selectStory.height);
            } else if (menuSelection == 0) {
                g.drawRect(selectPlay.x, selectPlay.y, selectPlay.width, selectPlay.height);
            } else if (menuSelection == 3) {
                g.drawRect(selectCredits.x, selectCredits.y, selectCredits.width, selectCredits.height);
            }
        }

        // game code
        if (menuSelection == 0 && enterKey == 1) {
            // background colour
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            // room
            g.setColor(brown);
            g.drawImage(background, room.x, room.y, room.width, room.height, null);

            // Theo
            if (dir == 'r') {
                g.drawImage(theoStandRight, theo.x, theo.y, theo.width, theo.height, null);
            } else if (dir == 'u') {
                g.drawImage(theoStandUp, theo.x, theo.y, 53, 38, null);
            } else if (dir == 'd') {
                g.drawImage(theoStandDown, theo.x, theo.y, 53, 38, null);
            } else if (dir == 'l') {
                g.drawImage(theoStandLeft, theo.x, theo.y, theo.width, theo.height, null);
            }
            //dialog
            if (theo.x == 150 && theo.y == 425) {
                g.drawImage(dialogwai, theo.x, theo.y, 50, 25, null);
            }
            // minigame screens
            if (spaceBar == true && theo.intersects(safeTrigger)) {
                g.drawImage(safeScreen, 0, 0, null);
            }
            if (spaceBar == true && theo.intersects(deskTrigger)) {
                g.drawImage(letterScreen, 0, 0, null);
            }
            if (spaceBar == true && theo.intersects(table1Trigger)) {
                g.drawImage(typeWriterScreen, 0, 0, null);
            }
            if (spaceBar == true && theo.intersects(pongTrigger)) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WIDTH, HEIGHT);
            }
            // countdown
            g.setColor(Color.WHITE);
            g.setFont(otType);
            g.drawString(countdown + " s", 500, 45);
            
            // help screen
        } else if (menuSelection == 1 && enterKey == 1) {
            g.drawImage(helpScreen, 0, 0, WIDTH, HEIGHT, null);
            if (backspace) {
                enterKey = 0;
            }
        } else if (menuSelection == 2 && enterKey == 1) {
            g.drawImage(story1, 0, 0, WIDTH, HEIGHT, null);
            // continue to next pic
            if (theoRight) {
                page = page + 1;
                theoRight = false;
            } else if (theoLeft) {
                page = page - 1;
                theoLeft = false;
            }
            // show pic per int
            if (page == 0) {
                g.drawImage(story1, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 1) {
                g.drawImage(story2, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 2) {
                g.drawImage(story3, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 3) {
                g.drawImage(story4, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 4) {
                g.drawImage(story5, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 5) {
                g.drawImage(story6, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 6) {
                g.drawImage(story7, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 7) {
                g.drawImage(story8, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 8) {
                g.drawImage(story9, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 9) {
                g.drawImage(story10, 0, 0, WIDTH, HEIGHT, null);
            } else if (page == 10) {
                enterKey = 0;
                page = 0;
            } else if (page < 0) {
                enterKey = 0;
                page = 0;
            }
        } else if (menuSelection == 3 && enterKey == 1) {
            g.drawImage(credits, 0, 0, WIDTH, HEIGHT, null);
            if (backspace) {
                enterKey = 0;
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

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            // theo movement
            if (enterKey == 1) {
                if (theoUp && theo.y > 72) {
                    theo.y = theo.y - speed;
                    dir = 'u';
                } else if (theoDown && theo.y + theo.height < 539) {
                    theo.y = theo.y + speed;
                    dir = 'd';
                } else if (theoLeft && theo.x + theo.width > 111) {
                    theo.x = theo.x - speed;
                    dir = 'l';
                } else if (theoRight && theo.x + theo.width < 530) {
                    theo.x = theo.x + speed;
                    dir = 'r';
                }

                // collisions 
                // pongTable
                if (theo.intersects(pongTable)) {
                    Rectangle intersection = theo.intersection(pongTable);
                    if (intersection.width < intersection.height) {
                        if (theo.x < pongTable.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < pongTable.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // wall 1
                if (theo.intersects(wall1)) {
                    Rectangle intersection = theo.intersection(wall1);
                    if (intersection.width < intersection.height) {
                        if (theo.x < wall1.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < wall1.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // wall 2
                if (theo.intersects(wall2)) {
                    Rectangle intersection = theo.intersection(wall2);
                    if (intersection.width < intersection.height) {
                        if (theo.x < wall2.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < wall2.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // wall 3
                if (theo.intersects(wall3)) {
                    Rectangle intersection = theo.intersection(wall3);
                    if (intersection.width < intersection.height) {
                        if (theo.x < wall3.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < wall3.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // table 1
                if (theo.intersects(table1)) {
                    Rectangle intersection = theo.intersection(table1);
                    if (intersection.width < intersection.height) {
                        if (theo.x < table1.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < table1.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // table 2
                if (theo.intersects(table2)) {
                    Rectangle intersection = theo.intersection(table2);
                    if (intersection.width < intersection.height) {
                        if (theo.x < table2.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < table2.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // table 3
                if (theo.intersects(table3)) {
                    Rectangle intersection = theo.intersection(table3);
                    if (intersection.width < intersection.height) {
                        if (theo.x < table3.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < table3.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // desk
                if (theo.intersects(desk)) {
                    Rectangle intersection = theo.intersection(desk);
                    if (intersection.width < intersection.height) {
                        if (theo.x < desk.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < desk.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // table 2
                if (theo.intersects(safe)) {
                    Rectangle intersection = theo.intersection(safe);
                    if (intersection.width < intersection.height) {
                        if (theo.x < safe.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < safe.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // table 2
                if (theo.intersects(bed)) {
                    Rectangle intersection = theo.intersection(bed);
                    if (intersection.width < intersection.height) {
                        if (theo.x < bed.x) {
                            theo.x = theo.x - intersection.width;
                        } else {
                            theo.x = theo.x + intersection.width;
                        }
                    } else {
                        if (theo.y < bed.y) {
                            theo.y = theo.y - intersection.height;
                        } else {
                            theo.y = theo.y + intersection.height;
                        }
                    }
                }
                // menu screen
            } else if (enterKey == 0) {
                if (theoUp) {
                    if (menuSelection == 2) {
                        menuSelection = 0;
                        theoUp = false;
                    }
                }
                if (theoDown) {
                    if (menuSelection == 0) {
                        menuSelection = 2;
                        theoDown = false;
                    }
                    if (menuSelection == 1) {
                        menuSelection = 3;
                        theoDown = false;
                    }
                }
                if (theoRight) {
                    if (menuSelection == 0) {
                        menuSelection = 1;
                        theoRight = false;
                    } else if (menuSelection == 2) {
                        menuSelection = 3;
                        theoRight = false;
                    }
                }
                if (theoLeft) {
                    if (menuSelection == 1) {
                        menuSelection = 0;
                        theoLeft = false;
                    } else if (menuSelection == 3) {
                        menuSelection = 2;
                        theoLeft = false;
                    }
                }
            }
            // minigames

            // countdown

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
                } catch (Exception e) {};
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
        OnlyTonight game = new OnlyTonight();
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

        // make the game listen for keyboard
        frame.addKeyListener(game);

        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // get key code
        int key = e.getKeyCode();
        // figure out what key is pressed
        if (key == KeyEvent.VK_UP) {
            theoUp = true;
        } else if (key == KeyEvent.VK_DOWN) {
            theoDown = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            theoRight = true;
        } else if (key == KeyEvent.VK_LEFT) {
            theoLeft = true;
        } else if (key == KeyEvent.VK_SPACE) {
            spaceBar = true;
        } else if (key == KeyEvent.VK_ENTER) {
            enterKey = 1;
        } else if (key == KeyEvent.VK_BACK_SPACE) {
            backspace = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // get key code
        int key = e.getKeyCode();
        // figure out what key is pressed
        if (key == KeyEvent.VK_UP) {
            theoUp = false;
        } else if (key == KeyEvent.VK_DOWN) {
            theoDown = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            theoRight = false;
        } else if (key == KeyEvent.VK_LEFT) {
            theoLeft = false;
        } else if (key == KeyEvent.VK_SPACE) {
            spaceBar = false;
        } else if (key == KeyEvent.VK_BACK_SPACE) {
            backspace = false;
        }
    }
}