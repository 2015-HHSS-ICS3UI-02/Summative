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
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author singk4158
 */
public class Brickbreaker extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // draw game ball  
    Rectangle ball = new Rectangle(WIDTH / 2 - 123, HEIGHT / 2 - 30, 20, 20);
    // make ball control
    int ballspeed = 4;
    int paddlespeed = 5;
    boolean enter = false;
    int screen = 0;
    // paddle controls
    boolean p1left = false;
    boolean p1right = false;
    BufferedImage Space = ImageHelper.loadImage("space.jpg");
    BufferedImage Spaceship = ImageHelper.loadImage("REALSPACESHIP_edited-1.png");

    // First tier
    Rectangle ball71 = new Rectangle(WIDTH / 2 - 10, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball1 = new Rectangle(WIDTH / 2 - 110, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball2 = new Rectangle(WIDTH / 2 - 80, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball3 = new Rectangle(WIDTH / 2 - 40, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball4 = new Rectangle(WIDTH / 2 - -20, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball5 = new Rectangle(WIDTH / 2 - -50, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball6 = new Rectangle(WIDTH / 2 - -80, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball7 = new Rectangle(WIDTH / 2 - -110, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball8 = new Rectangle(WIDTH / 2 - 140, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball9 = new Rectangle(WIDTH / 2 - 180, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball10 = new Rectangle(WIDTH / 2 - 210, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball11 = new Rectangle(WIDTH / 2 - 240, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball12 = new Rectangle(WIDTH / 2 - -140, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball13 = new Rectangle(WIDTH / 2 - -180, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball14 = new Rectangle(WIDTH / 2 - -210, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball15 = new Rectangle(WIDTH / 2 - -240, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball16 = new Rectangle(WIDTH / 2 - 310, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball17 = new Rectangle(WIDTH / 2 - 340, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball18 = new Rectangle(WIDTH / 2 - -380, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball19 = new Rectangle(WIDTH / 2 - 480, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball20 = new Rectangle(WIDTH / 2 - 400, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball21 = new Rectangle(WIDTH / 2 - 280, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball22 = new Rectangle(WIDTH / 2 - 368, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball23 = new Rectangle(WIDTH / 2 - -350, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball24 = new Rectangle(WIDTH / 2 - -320, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball25 = new Rectangle(WIDTH / 2 - -318, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball26 = new Rectangle(WIDTH / 2 - -290, HEIGHT / 6 - 10, 20, 20);
    Rectangle ball27 = new Rectangle(WIDTH / 2 - -265, HEIGHT / 6 - 10, 20, 20);
    // Second tier
    Rectangle ball28 = new Rectangle(WIDTH / 2 - 10, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball29 = new Rectangle(WIDTH / 2 - 110, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball30 = new Rectangle(WIDTH / 2 - 80, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball31 = new Rectangle(WIDTH / 2 - 40, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball32 = new Rectangle(WIDTH / 2 - -20, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball33 = new Rectangle(WIDTH / 2 - -50, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball34 = new Rectangle(WIDTH / 2 - -80, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball35 = new Rectangle(WIDTH / 2 - -110, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball36 = new Rectangle(WIDTH / 2 - 140, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball37 = new Rectangle(WIDTH / 2 - 180, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball38 = new Rectangle(WIDTH / 2 - 210, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball39 = new Rectangle(WIDTH / 2 - 240, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball40 = new Rectangle(WIDTH / 2 - -140, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball41 = new Rectangle(WIDTH / 2 - -180, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball42 = new Rectangle(WIDTH / 2 - -210, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball43 = new Rectangle(WIDTH / 2 - -240, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball44 = new Rectangle(WIDTH / 2 - 310, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball45 = new Rectangle(WIDTH / 2 - 340, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball46 = new Rectangle(WIDTH / 2 - -380, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball47 = new Rectangle(WIDTH / 2 - 480, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball48 = new Rectangle(WIDTH / 2 - 400, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball49 = new Rectangle(WIDTH / 2 - 280, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball50 = new Rectangle(WIDTH / 2 - 368, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball51 = new Rectangle(WIDTH / 2 - -350, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball52 = new Rectangle(WIDTH / 2 - -320, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball53 = new Rectangle(WIDTH / 2 - -318, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball54 = new Rectangle(WIDTH / 2 - -290, HEIGHT / 4 - 10, 20, 20);
    Rectangle ball55 = new Rectangle(WIDTH / 2 - -265, HEIGHT / 4 - 10, 20, 20);
    //Third tier
    Rectangle ball56 = new Rectangle(WIDTH / 2 - 90, HEIGHT / 3 - 10, 20, 20);
    Rectangle ball57 = new Rectangle(WIDTH / 2 - 40, HEIGHT / 3 - 10, 20, 20);
    Rectangle ball58 = new Rectangle(WIDTH / 2 - -10, HEIGHT / 3 - 10, 20, 20);
    Rectangle ball59 = new Rectangle(WIDTH / 2 - 140, HEIGHT / 3 - 10, 20, 20);
    Rectangle ball60 = new Rectangle(WIDTH / 2 - -60, HEIGHT / 3 - 10, 20, 20);
    Rectangle ball61 = new Rectangle(WIDTH / 2 - -110, HEIGHT / 3 - 10, 20, 20);
    //Fourth tier
    Rectangle ball62 = new Rectangle(WIDTH / 2 - 90, HEIGHT / 10 - 10, 20, 20);
    Rectangle ball63 = new Rectangle(WIDTH / 2 - 40, HEIGHT / 10 - 10, 20, 20);
    Rectangle ball64 = new Rectangle(WIDTH / 2 - -10, HEIGHT / 10 - 10, 20, 20);
    Rectangle ball65 = new Rectangle(WIDTH / 2 - 140, HEIGHT / 10 - 10, 20, 20);
    Rectangle ball66 = new Rectangle(WIDTH / 2 - -60, HEIGHT / 10 - 10, 20, 20);
    Rectangle ball67 = new Rectangle(WIDTH / 2 - -110, HEIGHT / 10 - 10, 20, 20);
    //Fifth tier  
    Rectangle ball68 = new Rectangle(WIDTH / 2 - -10, HEIGHT / 2 - 70, 20, 20);
    Rectangle ball69 = new Rectangle(WIDTH / 2 - 40, HEIGHT / 2 - 70, 20, 20);
    Rectangle ball70 = new Rectangle(WIDTH / 2 - 13, HEIGHT / 2 - 30, 20, 20);
    // make array for all the blocks
    Rectangle[] balls = {ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8, ball9, ball10, ball11, ball12, ball13, ball14, ball14, ball15, ball16, ball17, ball18, ball19, ball20, ball21, ball22, ball23, ball24, ball25, ball26, ball27, ball28, ball29, ball30, ball31, ball32, ball33, ball34, ball35, ball36, ball37, ball38, ball39, ball40, ball41, ball42, ball43, ball44, ball45, ball46, ball47, ball48, ball49, ball50, ball51, ball52, ball53, ball54, ball55, ball56, ball57, ball58, ball59, ball60, ball61, ball62, ball63, ball64, ball65, ball66, ball67, ball68, ball69, ball70, ball71};
    int moveX = 10;
    int moveY = 1;
    //draw paddel
    Rectangle p1 = new Rectangle(250, 490, 110, 110);
    int movey = 200;
   
     BufferedImage deathScreen = ImageHelper.loadImage("Death Screen.jpg");
     BufferedImage pauseScreen = ImageHelper.loadImage("pausedscreen.png");
     BufferedImage welcome = ImageHelper.loadImage("Start Menu.jpg");

     // Add haze background for the 'Paused' screen
    Color haze = new Color(255, 255, 255, 100);
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
         // Drawings for screen 0, 'Start Menu' screen
        if (screen == 0) {
            // Welcome to Minion Run! (Width is set to 816 since image has little white stuff at top right corner :))
            g.drawImage(welcome, 0, 0, 816, 600, null);


        // Draw background image
        g.drawImage(Space, 0, 0, 800, 600, null);
        // draw paddel
      
        //g.fillRect(p1.x, p1.y, p1.width, p1.height);ada awd
        g.drawImage(Spaceship, p1.x,p1.y, p1.width, p1.height, null);

        //draw paddel
        g.setColor(Color.white);

         // Game Font
        Font gameFont = new Font("Arial", Font.PLAIN, 14);
        
       // Drawings for screen 7, 'Death Screen'
        if (screen == 2) {
            // Draw background image including the designs created with photoshop
            g.drawImage(deathScreen, 0, 0, 800, 600, null);
            // What happens at the end of the game
            // You died! Game Over!
            g.setColor(Color.DARK_GRAY);
            g.fillRect(200, 280, 140, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Game Over! ", 234, 300);

           
            // Drawings for 'Pause Screen'
        if (enter) {
            // Draw background haze
            g.setColor(haze);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            // Draw the background text ("Paused... Press alt to resume!"
            g.drawImage(pauseScreen, 0, 0, 800, 600, null);

        // draw the ball
        g.setColor(Color.WHITE);
        g.fillRect(ball.x, ball.y, ball.width, ball.height);

        // 'Back' option
            g.setColor(Color.DARK_GRAY);
            g.fillRect(580, 500, 110, 30);
            g.setColor(Color.white);
            g.setFont(gameFont);
            g.drawString("Back", 618, 520);
            g.setColor(Color.white);

            // 'Back' option stamps (For design purposes)
            g.fillOval(580, 500, 7, 7);
            g.fillOval(682, 521, 7, 7);
            g.fillOval(682, 500, 7, 7);
            g.fillOval(580, 521, 7, 7);
        }

        g.setColor(Color.WHITE);
        for(int i = 0; i < balls.length; i++){
            if(balls[i] != null){
                g.fillRect(balls[i].x, balls[i].y, balls[i].width, balls[i].height);
            }
        }


        // GAME DRAWING ENDS HERE
    
        
    // The main game loop
    // In here is where all the logic for my game will go
    
    public void run(){
 
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
            // make ball move
            ball.x = ball.x + moveX * ballspeed;
            ball.y = ball.y + moveY * ballspeed;

            // make paddles move
            // if up pressed, left p1 right
            if (p1left && p1.x > 0) {
                p1.x = p1.x - paddlespeed;
            } else if (p1right && p1.x + p1.width < WIDTH) {
                p1.x = p1.x + paddlespeed;
            }

            if (ball.intersects(p1)) {
                moveY = -moveY;
            }

// For loop used to interact when bricks hit the player to bounce off
            for (int i = 0; i < balls.length; i++) {
                if (balls[i] != null && ball.intersects(balls[i])) {
                    balls[i] = null;
                    moveY = -moveY;
                }
            }
                 

   
            // ball hit top of screen?
            if (ball.y < 0) {
                moveY = 1;
            }

            // did right of ball hit right of screen?
            if (ball.x + ball.width > WIDTH) {
                moveX = -1;

            }
            // ball hit left of screen?
            if (ball.x < 0) {
                moveX = 1;

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
        Brickbreaker game = new Brickbreaker();
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

        // add key listener
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
        // is the W key being pressed
        if (key == KeyEvent.VK_A) {
            p1left = true;
        } else if (key == KeyEvent.VK_D) {
            p1right = true;
            
        }
        if (key == KeyEvent.VK_ENTER){
            enter = !enter;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // grab the keycode from the event
        int key = e.getKeyCode();
        // figure out what key is pressed
        // is the W key being pressed
        if (key == KeyEvent.VK_A) {
            p1left = false;
        } else if (key == KeyEvent.VK_D) {
            p1right = false;

        }
    }
}


