

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
 * @author voigr4865
 */
public class Game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 700;
    //image
    BufferedImage Ship = ImageHelper.loadImage("Ship.png");
    BufferedImage Star = ImageHelper.loadImage("StarNight1.jpg");
    BufferedImage Title = ImageHelper.loadImage("myPixelArt.png");
    BufferedImage GameOver = ImageHelper.loadImage("GameOve.jpg");
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;  
    
    //player moving
    boolean playerUp = false;
    boolean playerDown = false;
    boolean reset = false;
    //player control
    int speed = 9;
    int count = 0;
    int enter = 0;
    int screen = 0;
    int move = 2000;
    int score = 0;
    int R = 0;
    int randNum = (int) (Math.random() * (550 - 1 + 10)) + 1;
    Font gameFont = new Font("Comic Sans", Font.PLAIN, 30);
    //player
    Rectangle player = new Rectangle(150, HEIGHT / 2 - 15, 65, 65);
    Rectangle block = new Rectangle(2000, 0, 90, randNum);
    Rectangle block2 = new Rectangle(2000, randNum + 150, 90, 700);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {

        // always clear the screen first!
        g.clearRect(0, 0, 1200, 800);

        // GAME DRAWING GOES HERE
        if (enter == 0) {
            g.drawImage(Title, 0, 0, WIDTH, HEIGHT, null);
            
        }
        if (enter == 1) {


            //black background
            g.setColor(Color.BLACK);
            g.drawImage(Star, count, 0, WIDTH, HEIGHT, null);
            g.drawImage(Star, count + WIDTH, 0, WIDTH, HEIGHT, null);
            //player
            g.setColor(Color.white);
            g.drawImage(Ship, player.x, player.y, player.width, player.height, null);

            //walls
            g.fillRect(block.x, block.y, block.width, block.height);
            g.fillRect(block2.x, block2.y, block2.width, block2.height);
            
            //game score
            g.setColor(Color.YELLOW);
            g.setFont(gameFont);
            g.drawString("Score:" + score, 30, 30);
       
        
        }
        if(enter == 2){
            g.drawImage(GameOver, 0, 0, WIDTH, HEIGHT, null);
            
            
            
         
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

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE
            if (enter == 1) {

                //make background move
                if (count < -1000) {
                    count = 0;
                }
                //make blocks move
                if (move < -100) {
                    move = WIDTH;
                    score = score + 100;
                    randNum = (int) (Math.random() * (550 - 1 + 10)) + 1;
                    block.height = randNum;
                    block2.y = randNum + 150;
                }

                //collisions for screen
                if (playerUp && player.y > 0) {
                    player.y = player.y - speed;
                } else if (playerDown && player.y + player.height < HEIGHT) {
                    player.y = player.y + speed;
                }

                count = count - 12;
                move = move - 12;
                
                block.x = move;
                block2.x = move;
                
            
                if(player.intersects(block)){
                    player.y = player.y - speed;
                    enter = 2;
                 
                    
                } else if(player.intersects(block2)){
                    player.y = player.y - speed;                   
                    enter = 2;
                    
                }
                
                    }
                    
                
                
            if(reset == true){   
                playerUp = false;
                playerDown = false;
                speed = 7;
                count = 0;
                enter = 0;
                move = 2000;
                score = 0;
                randNum = (int) (Math.random() * (550 - 1 + 10)) + 1;
                gameFont = new Font("Comic Sans", Font.PLAIN, 30);
                player = new Rectangle(150, HEIGHT / 2 - 15, 65, 65);
                block = new Rectangle(2000, 0, 90, randNum);
                block2 = new Rectangle(2000, randNum + 150, 90, 700);
                reset = false;
            
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

        //make the game listen for keyboard
        frame.addKeyListener(game);


        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //grab key code from the event
        int key = e.getKeyCode();
        //figure out what key is pressed
        //is W being pressed
        if (key == KeyEvent.VK_S) {
            playerDown = true;
        } else if (key == KeyEvent.VK_W) {
            playerUp = true;
        }
        if (key == KeyEvent.VK_ENTER) {
            enter = 1;
        }
        
        if (key == KeyEvent.VK_R){
            reset = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //grab key code from the event
        int key = e.getKeyCode();
        //figure out what key is pressed
        //is W being pressed
        if (key == KeyEvent.VK_S) {
            playerDown = false;
        } else if (key == KeyEvent.VK_W) {
            playerUp = false;
        }

    }
}
