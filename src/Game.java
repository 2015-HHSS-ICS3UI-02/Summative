
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
 * @author guanv6321
 */
public class Game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 800;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // variable for main block
    Rectangle main = new Rectangle(0, 600, 50, 50);
    // variable for the enemies
    Rectangle ghost1 = new Rectangle(500, 0, 50, 50);
    Rectangle ghost2 = new Rectangle(50, 200, 50, 50);
    Rectangle ghost3 = new Rectangle(0, 525, 50, 50);
    Rectangle ghost4 = new Rectangle(0, 0, 50, 50);
    // sets how fast main block and enemies move move
    int speed = 3;
    // sets direction the enemies move in
    int moveX = 1;
    int moveX2 = 1;
    int moveX3 = 1;
    int moveX4 = 1;
    // score
    int score = 0;
    // amount of time 
    int time = 3000;
    // sets which screen it is on
    int screen = 1;  
    boolean mainUp = false;
    boolean mainDown = false;
    boolean mainLeft = false;
    boolean mainRight = false;
    boolean dead = false;
    boolean start = false;
    // upload the image of title page
    BufferedImage title = ImageHelper.loadImage("TitleScreen.jpg");
    // uploads the game over screen
    BufferedImage end = ImageHelper.loadImage("GameOver.jpg");
    // sets font
    Font gameFont = new Font("Arial", Font.PLAIN, 70);
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        // if on first screen
        if (screen == 1) {
            g.setColor(Color.black);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            // draw the image of title screen
            g.drawImage(title, 0, 0, this);
        }
        // if on second screen
        if (screen == 2 ) {
            // clears screen
            g.clearRect(0, 0, WIDTH, HEIGHT);
            // sets colour to black
            g.setColor(Color.black);
            // cover entire screen with black background
            g.fillRect(0, 0, WIDTH, HEIGHT);
            // sets colour to white
            g.setColor(Color.white);
            // draws main block
            g.fillRect(main.x, main.y, main.width, main.height);
            // draws score
            g.drawString("Score: " + score, WIDTH / 2 - 100, 100);
            // draws time
            g.drawString("Time: " + time / 100, WIDTH / 2, HEIGHT / 2);
            // sets colour to cyan
            g.setColor(Color.CYAN);
            // draws the zone you must occupy
            g.fillOval(400, 15, 25, 25);
            // sets colour to blue
            g.setColor(Color.blue);
            // draws enemy block
            g.fillRect(ghost1.x, ghost1.y, ghost1.width, ghost1.height);
            // draws walls
            g.drawRect(50, 50, 200, 100);
            g.drawRect(WIDTH - 200, HEIGHT - 200, 100, 100);
            g.drawRect(250, 300, 50, 200);
            // set colour to red
            g.setColor(Color.red);
            // draws more walls
            g.drawRect(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200);
            g.drawRect(WIDTH - 200, 50, 100, 100);
            g.drawRect(WIDTH - 350, HEIGHT - 200, 50, 100);
            // draws another enemy block
            g.fillRect(ghost3.x, ghost3.y, ghost3.width, ghost3.height);
            // sets colour to yellow
            g.setColor(Color.yellow);
            // draws more walls
            g.drawRect(50, HEIGHT - 200, 100, 150);
            g.drawRect(250, HEIGHT - 150, 300, 100);
            g.drawRect(350, 50, 300, 100);
            g.drawRect(WIDTH - 325, 300, 50, 200);
            // draws another enemy block
            g.fillRect(ghost2.x, ghost2.y, ghost2.width, ghost2.height);
            // sets colour to green
            g.setColor(Color.green);
            // draws more walls
            g.drawRect(50, 300, 100, 200);
            g.drawRect(WIDTH - 200, 300, 100, 200);
            // draws another enemy block
            g.fillRect(ghost4.x, ghost4.y, ghost4.width, ghost4.height);
        }
        // if on third screen
        if (screen == 3) {
            // clears screen
            g.clearRect(0, 0, WIDTH, HEIGHT);
            // sets colour to black
            g.setColor(Color.black);
            // fills entire screen black
            g.fillRect(0, 0, WIDTH, HEIGHT);
            // draws game over screen
            g.drawImage(end, 0, 0, this);
            // sets colour to red
            g.setColor(Color.red);          
            // draws final score
             g.setFont(gameFont);
            g.drawString( "" + score, WIDTH/2, HEIGHT/2);
        }
//         GAME DRAWING ENDS HERE
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
            // if on first screen
            if (screen == 1) {
                // if up key is pressed
                if (mainUp == true) {
                    // changes to second screen
                    screen = 2;
                    // starts game
                    start = true;
                }
            }
            // if on second screen and game has started
            if(screen == 2 && start == true) {
                // decreases time
                time--;
                // if time hits 0
                if (time == 0) {
                    // changes to third screen
                    screen=3;
                    // finishes game
                    done=true;
                }
                // if down key is pressed and main block is above the bottom of screen
                if (mainDown == true && main.y < HEIGHT - main.height) {
                    // moves main block down
                    main.y = main.y + speed;
                    // if main block hits any wall
                    if (main.intersects(50, 50, 200, 100)) {
                        main.y = 0;
                    }
                    if (main.intersects(350, 50, 300, 100)) {
                        main.y = 0;
                    }
                    if (main.intersects(WIDTH - 200, 50, 100, 100)) {
                        main.y = 0;
                    }
                    if (main.intersects(50, 300, 100, 200)) {
                        main.y = 250;
                    }
                    if (main.intersects(250, 300, 50, 200)) {
                        main.y = 250;
                    }
                    if (main.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                        main.y = 250;
                    }
                    if (main.intersects(WIDTH - 325, 300, 50, 200)) {
                        main.y = 250;
                    }
                    if (main.intersects(WIDTH - 200, 300, 100, 200)) {
                        main.y = 250;
                    }
                    if (main.intersects(50, HEIGHT - 200, 100, 150)) {
                        main.y = HEIGHT - 250;
                    }
                    if (main.intersects(250, HEIGHT - 150, 300, 100)) {
                        main.y = HEIGHT - 200;
                    }
                    if (main.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                        main.y = HEIGHT - 250;
                    }
                    if (main.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                        main.y = HEIGHT - 250;
                    }
                }
                // if up key is pressed and main block is below top of screen
                if (mainUp == true && main.y > 0) {
                    // moves main block up
                    main.y = main.y - speed;
                    // if main block hits any wall
                    if (main.intersects(50, 50, 200, 100)) {
                        main.y = 150;
                    }
                    if (main.intersects(350, 50, 300, 100)) {
                        main.y = 150;
                    }
                    if (main.intersects(WIDTH - 200, 50, 100, 100)) {
                        main.y = 150;
                    }
                    if (main.intersects(50, 300, 100, 200)) {
                        main.y = 500;
                    }
                    if (main.intersects(250, 300, 50, 200)) {
                        main.y = 500;
                    }
                    if (main.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                        main.y = 500;
                    }
                    if (main.intersects(WIDTH - 325, 300, 50, 200)) {
                        main.y = 500;
                    }
                    if (main.intersects(WIDTH - 200, 300, 100, 200)) {
                        main.y = 500;
                    }
                    if (main.intersects(50, HEIGHT - 200, 100, 150)) {
                        main.y = HEIGHT - 50;
                    }
                    if (main.intersects(250, HEIGHT - 150, 300, 100)) {
                        main.y = HEIGHT - 50;
                    }
                    if (main.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                        main.y = HEIGHT - 100;
                    }
                    if (main.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                        main.y = HEIGHT - 100;
                    }
                }
                // if right key is pressed and main block is left of right side of screen
                if (mainRight == true && main.x < WIDTH - main.width) {
                    // main block moves right
                    main.x = main.x + speed;
                    // if main block hits any wall
                    if (main.intersects(50, 50, 200, 100)) {
                        main.x = 0;
                    }
                    if (main.intersects(50, 300, 100, 200)) {
                        main.x = 0;
                    }
                    if (main.intersects(50, HEIGHT - 200, 100, 150)) {
                        main.x = 0;
                    }
                    if (main.intersects(250, 300, 50, 200)) {
                        main.x = 200;
                    }
                    if (main.intersects(250, HEIGHT - 150, 300, 100)) {
                        main.x = 200;
                    }
                    if (main.intersects(350, 50, 300, 100)) {
                        main.x = 300;
                    }
                    if (main.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                        main.x = WIDTH / 2 - 150;
                    }
                    if (main.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                        main.x = WIDTH - 400;
                    }
                    if (main.intersects(WIDTH - 325, 300, 50, 200)) {
                        main.x = WIDTH - 375;
                    }
                    if (main.intersects(WIDTH - 200, 50, 100, 100)) {
                        main.x = WIDTH - 250;
                    }
                    if (main.intersects(WIDTH - 200, 300, 100, 200)) {
                        main.x = WIDTH - 250;
                    }
                    if (main.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                        main.x = WIDTH - 250;
                    }
                }
                // if left key is pressed and main is right of left side of screen
                if (mainLeft == true && main.x > 0) {
                    // main block moves left
                    main.x = main.x - speed;
                    // if main block hits any wall
                    if (main.intersects(50, 50, 200, 100)) {
                        main.x = 250;
                    }
                    if (main.intersects(350, 50, 300, 100)) {
                        main.x = 650;
                    }
                    if (main.intersects(WIDTH - 200, 50, 100, 100)) {
                        main.x = WIDTH - 100;
                    }
                    if (main.intersects(50, 300, 100, 200)) {
                        main.x = 150;
                    }
                    if (main.intersects(250, 300, 50, 200)) {
                        main.x = 300;
                    }
                    if (main.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                        main.x = WIDTH / 2 + 100;
                    }
                    if (main.intersects(WIDTH - 325, 300, 50, 200)) {
                        main.x = WIDTH - 275;
                    }
                    if (main.intersects(WIDTH - 200, 300, 100, 200)) {
                        main.x = WIDTH - 100;
                    }
                    if (main.intersects(50, HEIGHT - 200, 100, 150)) {
                        main.x = 150;
                    }
                    if (main.intersects(250, HEIGHT - 150, 300, 100)) {
                        main.x = 550;
                    }
                    if (main.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                        main.x = WIDTH - 300;
                    }
                    if (main.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                        main.x = WIDTH - 100;
                    }
                }
                // blue enemy block AI
                ghost1.x = ghost1.x + moveX * speed;
                // if blue enemy block hits right of screen
                if (ghost1.x + ghost1.width > WIDTH) {
                    // blue enemy block moves left 
                    moveX = -1;
                    // if score is greater than 100
                    if (score > 100) {
                        // speeds up the blue enemy block
                        moveX = -2;
                    }
                }
                 // if blue enemy block hits left of screen
                if (ghost1.x < 0) {
                    // blue enemy block moves right 
                    moveX = 1;
                    // if score is greater than 100
                    if (score > 100) {
                        // speeds up blue enemy block
                        moveX = 2;
                    }
                }           
                // yellow enemy block AI
                ghost2.x = ghost2.x + moveX2 * speed;
                // if yellow enemy block hits left of screen
                if (ghost2.x + ghost2.width > WIDTH) {
                    moveX2 = -1;
                }
                if (ghost2.x < 0) {
                    moveX2 = 1;
                }
                // ghost 3 ai
                ghost3.x = ghost3.x + moveX3 * speed;
                // if red enemy block hits left of screen
                if (ghost3.x + ghost3.width > WIDTH) {
                    moveX3 = -1;
                }
                if (ghost3.x < 0) {
                    moveX3 = 1;
                }
                ghost4.x = ghost4.x + moveX4 * speed;
                // if green enemy block hits left of screen
                if (ghost4.x + ghost4.width > WIDTH) {
                    moveX4 = -1;
                }
                if (ghost4.x < 0) {
                    moveX4 = 1;
                }
                // if hit by any enemy block
                if (main.intersects(ghost1) || main.intersects(ghost2) || main.intersects(ghost3) || main.intersects(ghost4)) {
                    // changes to third screen
                    screen=3;
                    // finishes game
                    done=true;
                }
                // if main block is on the cyan circle
                if (main.intersects(400, 15, 25, 25)) {
                    // increases score
                    score++;
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
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // grab the keycode from the event
        int key = e.getKeyCode();
        // figure out what key is pressed
        if (key == KeyEvent.VK_UP) {
            mainUp = true;
        } else if (key == KeyEvent.VK_DOWN) {
            mainDown = true;
        } else if (key == KeyEvent.VK_LEFT) {
            mainLeft = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            mainRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // grab the keycode from the event
        int key = e.getKeyCode();
        // figure out what key is pressed
        if (key == KeyEvent.VK_UP) {
            mainUp = false;
        } else if (key == KeyEvent.VK_DOWN) {
            mainDown = false;
        } else if (key == KeyEvent.VK_LEFT) {
            mainLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            mainRight = false;
        }
    }
}
