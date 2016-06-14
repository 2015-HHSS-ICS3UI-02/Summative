/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author dhalt0019
 */
public class Game extends JComponent{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // player position
    int x = 100;
    int y = 500;
    // mouse variables
    int mouseX = 0;
    int mouseY = 0;
    boolean keypressed = false;
    // blocks
    ArrayList<Rectangle> blocks = new ArrayList<>();
    // player
    Rectangle android = new Rectangle(200, 200, 50, 50);
    int moveX = 0;
    int moveY = 0;
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
        g.setColor(Color.BLACK);
        for (Rectangle block : blocks) {

            g.fillRect(block.x, block.y, block.width, block.height);
        }

        g.setColor(Color.GREEN);
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

        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {

        blocks.add(new Rectangle(400, 450, 100, 50));
        blocks.add(new Rectangle(500, 400, 50, 50));
        blocks.add(new Rectangle(300, 400, 50, 50));

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

            x = mouseX;
            y = mouseY;

            if (left) {

                moveX = - 2;

            } else if (right) {

                moveX = 2;

            } else {

                moveX = 0;

                count++;
            }

            if (count >= 1) {

                moveY = moveY + gravity;
                count = 0;
            }

            //jumping
            
            if(jump && !backjump && !air){
                
                moveY = -20;
                air = true;
            }
            // tracks the jumps
            backjump = jump;
            // player movement
            android.x = android.x + moveX;
            android.y = android.y + moveY;

            // if player becomes lower than the ground (bottom of screen)
            
            if(android.y + android.height > HEIGHT){
            
            android.y = HEIGHT - android.height;
            moveY= 0;
            air = false;
        }
            for(Rectangle block: blocks){
            
                if(android.intersects (block)){
                
                Rectangle intersection = android.intersection (block);
                
                // fix the X movement 
                if (intersection.width < intersection.height){
                    
                    if(android.x < block.x){
                        
                        android.x = android.x - intersection.width;
                        
                    }else{
                        android.x = android.x + intersection.width;
                    }
                }else{  
                    // fix the Y movement
                    
                    if (android.y < block.y){
                        
                        android.y = android.y + intersection.height;
                        moveY = 0;
                        
                    }else{
                        
                        android.y = android.y - intersection.height;
                        moveY = 0;
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
}
