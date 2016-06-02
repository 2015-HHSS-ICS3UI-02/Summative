/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author lamon
 */
public class Pong extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;

    Rectangle ball = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
    // ball control
    int moveX = 1;
    int moveY = 1;
    int speed = 10;

    // paddles
    Rectangle p1 = new Rectangle(50, HEIGHT / 2 - 40, 25, 80);
    Rectangle p2 = new Rectangle(WIDTH - 75, HEIGHT / 2 - 40, 25, 80);

    //ai on/off
    boolean ai1 = false;
    boolean ai2 = true;

    // paddle controls
    boolean p1Up = false;
    boolean p1Down = false;
    boolean p2Up = false;
    boolean p2Down = false;

    //score
    int score1 = 0;
    int score2 = 0;

    // Game Font
    Font gameFont = new Font("Arial", Font.PLAIN, 40);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        // drawing a black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // draw the ball
        g.setColor(Color.WHITE);
        g.fillRect(ball.x, ball.y, ball.width, ball.height);

        // draw paddle1
        g.fillRect(p1.x, p1.y, p1.width, p1.height);
        g.fillRect(p2.x, p2.y, p2.width, p2.height);

        // draw scores
        g.setFont(gameFont);
        g.drawString("P1: " + score1, WIDTH / 2 - 100, 100);
        g.drawString("P2: " + score2, WIDTH / 2 + 100, 100);

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
            // make ball move
            ball.x = ball.x + moveX * speed;
            ball.y = ball.y + moveY * speed;

            // ball world collisions
            // did bottom of ball hit bottom of screen?
            if (ball.y + ball.height > HEIGHT) {
                moveY = -1;
            }
            // ball hit top of screen?
            if (ball.y < 0) {
                moveY = 1;
            }

            // did right of ball hit right of screen?
            if (ball.x + ball.width > WIDTH) {
                moveX = -1;
                score1++;
            }
            // ball hit left of screen?
            if (ball.x < 0) {
                moveX = 1;
                score2++;
            }

            // end game after 10 points
            if (score1 == 10 || score2 == 10) {
                done = true;
            }

            // make paddles move
            // if up pressed, move p1 up
            if (p1Up && p1.y > 0) {
                p1.y = p1.y - speed;
            } else if (p1Down && p1.y + p1.height < HEIGHT) {
                p1.y = p1.y + speed;
            }

            // if up pressed, move p2 up
            if (p2Up) {
                p2.y = p2.y - speed;
            } else if (p2Down) {
                p2.y = p2.y + speed;
            }

            // does ball hit player 1
            if (ball.intersects(p1)) {
                moveX = 1;
            } else if (ball.intersects(p2)) {
                moveX = -1;
            }

            //p2 ai
            if (ai2) {
                // my paddle is above the ball.
                if (p2.y + p2.height / 2 < ball.y) {
                    p2Up = false;
                    p2Down = true;
                    // my paddle is below to ball
                } else if (p2.y + p2.height / 2 > ball.y) {
                    p2Down = false;
                    p2Up = true;
                } else {
                    p2Up = false;
                    p2Down = false;
                }
            }

            //p1 ai
            if (ai1) {
                // my paddle is above the ball.
                if (p1.y + p1.height / 2 < ball.y) {
                    p1Up = false;
                    p1Down = true;
                    // my paddle is below to ball
                } else if (p1.y + p1.height / 2 > ball.y) {
                    p1Down = false;
                    p1Up = true;
                } else {
                    p1Up = false;
                    p1Down = false;
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
        Pong game = new Pong();
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
        // grab the keycode from the event
        int key = e.getKeyCode();
        // figure out what key is pressed
        // is the W key being pressed
        if (key == KeyEvent.VK_W) {
            p1Up = true;
        } else if (key == KeyEvent.VK_S) {
            p1Down = true;
        } else if (key == KeyEvent.VK_UP) {
            p2Up = true;
        } else if (key == KeyEvent.VK_DOWN) {
            p2Down = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // grab the keycode from the event
        int key = e.getKeyCode();
        // figure out what key is pressed
        // is the W key being pressed
        if (key == KeyEvent.VK_W) {
            p1Up = false;
        } else if (key == KeyEvent.VK_S) {
            p1Down = false;
        } else if (key == KeyEvent.VK_UP) {
            p2Up = false;
        } else if (key == KeyEvent.VK_DOWN) {
            p2Down = false;
        }
    }
}
