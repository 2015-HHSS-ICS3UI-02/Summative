/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author kangh4484
 */
public class FinalProject extends JComponent implements KeyListener {

    // Height and Width of our game
    //10colume, 25 row
    static final int WIDTH = 400;
    static final int HEIGHT = 850;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int speed = 1;
    //boolean 2D array
    boolean[][][] T = new boolean[4][4][4];
    boolean[][][] I = new boolean[4][4][4];
    boolean[][][] Z = new boolean[4][4][4];
    boolean[][][] S = new boolean[4][4][4];
    boolean[][][] L = new boolean[4][4][4];
    boolean[][][] J = new boolean[4][4][4];
    boolean[][][] O = new boolean[4][4][4];
    boolean[][][][] shapes = {T, I, Z, S, L, J, O};
    boolean[][] screen = new boolean[10][25];
    int x = 165;
    int y = 75;
    int w = 30;
    int h = 30;
    int r = 0;
    int down = 30;
    int side = 30;
    int randnum = (int) (Math.random() * (7));
    //score
    int score = 0;
    //game font
    Font gameFont = new Font("Arial", Font.PLAIN, 40);
    // block speed
    long lastMove = 0; // when the last time block moved down
    long delay = 1000; // 1000 millis = 1 second

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 


        //background color
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //tetris box
        g.setColor(Color.BLACK);
        g.fillRect(75, 75, WIDTH - 100, HEIGHT - 100);


//        if (randnum == 1) {
//            g.setColor(Color.yellow);
//            for (int row = 0; row < T.length; row++) {
//                for (int col = 0; col < T[0].length; col++) {
//                    if (T[row][col][r]) {
//                        g.fillRect(x + w * col, y + h * row - 30, w, h);
//                    }
//
//                }
//            }
//        } else if (randnum == 2) {
//            g.setColor(Color.blue);
//            for (int row = 0; row < I.length; row++) {
//                for (int col = 0; col < I[0].length; col++) {
//                    if (I[row][col][r]) {
//                        g.fillRect(x + w * col, y + h * row - 30, w, h);
//                    }
//                }
//            }
//        } else if (randnum == 3) {
//            g.setColor(Color.red);
//            for (int row = 0; row < Z.length; row++) {
//                for (int col = 0; col < Z[0].length; col++) {
//                    if (Z[row][col][r]) {
//                        g.fillRect(x + w * col, y + h * row - 30, w, h);
//                    }
//                }
//            }
//        } else if (randnum == 4) {
//            g.setColor(Color.green);
//            for (int row = 0; row < S.length; row++) {
//                for (int col = 0; col < S[0].length; col++) {
//                    if (S[row][col][r]) {
//                        g.fillRect(x + w * col, y + h * row - 30, w, h);
//                    }
//                }
//            }
//        } else if (randnum == 5) {
//            g.setColor(Color.orange);
//            for (int row = 0; row < L.length; row++) {
//                for (int col = 0; col < L[0].length; col++) {
//                    if (L[row][col][r]) {
//                        g.fillRect(x + w * col, y + h * row - 30, w, h);
//                    }
//                }
//            }
//        } else if (randnum == 6) {
//            g.setColor(Color.cyan);
//            for (int row = 0; row < J.length; row++) {
//                for (int col = 0; col < J[0].length; col++) {
//                    if (J[row][col][r]) {
//                        g.fillRect(x + w * col, y + h * row - 30, w, h);
//                    }
//                }
//            }
//        } else if (randnum == 7) {
//            g.setColor(Color.pink);
//            for (int row = 0; row < O.length; row++) {
//                for (int col = 0; col < O[0].length; col++) {
//                    if (O[row][col][r]) {
//                        g.fillRect(x + w * col, y + h * row - 30, w, h);
//                    }
//                }
//            }
//        }

        if (randnum == 0) {
            g.setColor(Color.yellow);
        } else if (randnum == 1) {
            g.setColor(Color.blue);
        } else if (randnum == 2) {
            g.setColor(Color.red);
        } else if (randnum == 3) {
            g.setColor(Color.green);
        } else if (randnum == 4) {
            g.setColor(Color.orange);
        } else if (randnum == 5) {
            g.setColor(Color.cyan);
        } else if (randnum == 6) {
            g.setColor(Color.pink);
        }

        for (int row = 0; row < shapes[randnum].length; row++) {
            for (int col = 0; col < shapes[randnum][0].length; col++) {
                if (shapes[randnum][row][col][r]) {
                    g.fillRect(x + w * col, y + h * row - 30, w, h);
                }

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


        //T block
        //rotation 1
        T[0][0][0] = false;
        T[0][1][0] = true;
        T[0][2][0] = false;
        T[1][0][0] = true;
        T[1][1][0] = true;
        T[1][2][0] = true;
        T[2][0][0] = false;
        T[2][1][0] = false;
        T[2][2][0] = false;

        //rotation 2
        T[0][0][1] = false;
        T[0][1][1] = true;
        T[0][2][1] = false;
        T[1][0][1] = true;
        T[1][1][1] = true;
        T[1][2][1] = false;
        T[2][0][1] = false;
        T[2][1][1] = true;
        T[2][2][1] = false;

        //rotation 3
        T[0][0][2] = false;
        T[0][1][2] = false;
        T[0][2][2] = false;
        T[1][0][2] = true;
        T[1][1][2] = true;
        T[1][2][2] = true;
        T[2][0][2] = false;
        T[2][1][2] = true;
        T[2][2][2] = false;

        // rotation 4
        T[0][0][3] = false;
        T[0][1][3] = true;
        T[0][2][3] = false;
        T[1][0][3] = false;
        T[1][1][3] = true;
        T[1][2][3] = true;
        T[2][0][3] = false;
        T[2][1][3] = true;
        T[2][2][3] = false;

        //I block
        //rotation 1
        I[0][0][0] = false;
        I[0][1][0] = true;
        I[0][2][0] = false;
        I[0][3][0] = false;
        I[1][0][0] = false;
        I[1][1][0] = true;
        I[1][2][0] = false;
        I[1][3][0] = false;
        I[2][0][0] = false;
        I[2][1][0] = true;
        I[2][2][0] = false;
        I[2][3][0] = false;
        I[3][0][0] = false;
        I[3][1][0] = true;
        I[3][2][0] = false;
        I[3][3][0] = false;


        //rotation 2
        I[0][0][1] = false;
        I[0][1][1] = false;
        I[0][2][1] = false;
        I[0][3][1] = false;
        I[1][0][1] = true;
        I[1][1][1] = true;
        I[1][2][1] = true;
        I[1][3][1] = true;
        I[2][0][1] = false;
        I[2][1][1] = false;
        I[2][2][1] = false;
        I[2][3][1] = false;

        //rotation 3
        I[0][0][2] = false;
        I[0][1][2] = true;
        I[0][2][2] = false;
        I[0][3][2] = false;
        I[1][0][2] = false;
        I[1][1][2] = true;
        I[1][2][2] = false;
        I[1][3][2] = false;
        I[2][0][2] = false;
        I[2][1][2] = true;
        I[2][2][2] = false;
        I[2][3][2] = false;
        I[3][0][2] = false;
        I[3][1][2] = true;
        I[3][2][2] = false;
        I[3][3][2] = false;

        //rotation 4
        I[0][0][3] = false;
        I[0][1][3] = false;
        I[0][2][3] = false;
        I[0][3][3] = false;
        I[1][0][3] = true;
        I[1][1][3] = true;
        I[1][2][3] = true;
        I[1][3][3] = true;
        I[2][0][3] = false;
        I[2][1][3] = false;
        I[2][2][3] = false;
        I[2][3][3] = false;

        //Z block
        //rotation 1
        Z[0][0][0] = false;
        Z[0][1][0] = false;
        Z[0][2][0] = true;
        Z[1][0][0] = false;
        Z[1][1][0] = true;
        Z[1][2][0] = true;
        Z[2][0][0] = false;
        Z[2][1][0] = true;
        Z[2][2][0] = false;

        //rotation 2
        Z[0][0][1] = false;
        Z[0][1][1] = false;
        Z[0][2][1] = false;
        Z[1][0][1] = true;
        Z[1][1][1] = true;
        Z[1][2][1] = false;
        Z[2][0][1] = false;
        Z[2][1][1] = true;
        Z[2][2][1] = true;

        //rotation 3
        Z[0][0][2] = false;
        Z[0][1][2] = false;
        Z[0][2][2] = true;
        Z[1][0][2] = false;
        Z[1][1][2] = true;
        Z[1][2][2] = true;
        Z[2][0][2] = false;
        Z[2][1][2] = true;
        Z[2][2][2] = false;

        //rotation 4
        Z[0][0][3] = false;
        Z[0][1][3] = false;
        Z[0][2][3] = false;
        Z[1][0][3] = true;
        Z[1][1][3] = true;
        Z[1][2][3] = false;
        Z[2][0][3] = false;
        Z[2][1][3] = true;
        Z[2][2][3] = true;


        //S block
        //rotation 1
        S[0][0][0] = false;
        S[0][1][0] = true;
        S[0][2][0] = false;
        S[1][0][0] = false;
        S[1][1][0] = true;
        S[1][2][0] = true;
        S[2][0][0] = false;
        S[2][1][0] = false;
        S[2][2][0] = true;


        //rotation 2
        S[0][0][1] = false;
        S[0][1][1] = true;
        S[0][2][1] = true;
        S[1][0][1] = true;
        S[1][1][1] = true;
        S[1][2][1] = false;
        S[2][0][1] = false;
        S[2][1][1] = false;
        S[2][2][1] = false;

        //rotation 3
        S[0][0][2] = false;
        S[0][1][2] = true;
        S[0][2][2] = false;
        S[1][0][2] = false;
        S[1][1][2] = true;
        S[1][2][2] = true;
        S[2][0][2] = false;
        S[2][1][2] = false;
        S[2][2][2] = true;


        //rotation 4
        S[0][0][3] = false;
        S[0][1][3] = true;
        S[0][2][3] = true;
        S[1][0][3] = true;
        S[1][1][3] = true;
        S[1][2][3] = false;
        S[2][0][3] = false;
        S[2][1][3] = false;
        S[2][2][3] = false;


        //L block
        //rotation 1
        L[0][0][0] = false;
        L[0][1][0] = false;
        L[0][2][0] = true;
        L[1][0][0] = true;
        L[1][1][0] = true;
        L[1][2][0] = true;
        L[2][0][0] = false;
        L[2][1][0] = false;
        L[2][2][0] = false;

        //rotation 2
        L[0][0][1] = false;
        L[0][1][1] = true;
        L[0][2][1] = false;
        L[1][0][1] = false;
        L[1][1][1] = true;
        L[1][2][1] = false;
        L[2][0][1] = false;
        L[2][1][1] = true;
        L[2][2][1] = true;

        //rotation 3
        L[0][0][2] = false;
        L[0][1][2] = false;
        L[0][2][2] = false;
        L[1][0][2] = true;
        L[1][1][2] = true;
        L[1][2][2] = true;
        L[2][0][2] = true;
        L[2][1][2] = false;
        L[2][2][2] = false;

        //rotation 4
        L[0][0][3] = true;
        L[0][1][3] = true;
        L[0][2][3] = false;
        L[1][0][3] = false;
        L[1][1][3] = true;
        L[1][2][3] = false;
        L[2][0][3] = false;
        L[2][1][3] = true;
        L[2][2][3] = false;


        //J block
        //rotation 1
        J[0][0][0] = true;
        J[0][1][0] = false;
        J[0][2][0] = false;
        J[1][0][0] = true;
        J[1][1][0] = true;
        J[1][2][0] = true;
        J[2][0][0] = false;
        J[2][1][0] = false;
        J[2][2][0] = false;

        //rotation 2
        J[0][0][1] = false;
        J[0][1][1] = true;
        J[0][2][1] = true;
        J[1][0][1] = false;
        J[1][1][1] = true;
        J[1][2][1] = false;
        J[2][0][1] = false;
        J[2][1][1] = true;
        J[2][2][1] = false;

        //rotation 3
        J[0][0][2] = false;
        J[0][1][2] = false;
        J[0][2][2] = false;
        J[1][0][2] = true;
        J[1][1][2] = true;
        J[1][2][2] = true;
        J[2][0][2] = false;
        J[2][1][2] = false;
        J[2][2][2] = true;

        //rotation 4
        J[0][0][3] = false;
        J[0][1][3] = true;
        J[0][2][3] = false;
        J[1][0][3] = false;
        J[1][1][3] = true;
        J[1][2][3] = false;
        J[2][0][3] = true;
        J[2][1][3] = true;
        J[2][2][3] = false;

        //O block
        //rotation 1
        O[0][1][0] = true;
        O[0][2][0] = true;
        O[1][1][0] = true;
        O[1][2][0] = true;


        //rotation 2
        O[0][1][1] = true;
        O[0][2][1] = true;
        O[1][1][1] = true;
        O[1][2][1] = true;


        //rotation 3
        O[0][1][2] = true;
        O[0][2][2] = true;
        O[1][1][2] = true;
        O[1][2][2] = true;


        //rotation 4
        O[0][1][3] = true;
        O[0][2][3] = true;
        O[1][1][3] = true;
        O[1][2][3] = true;


        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if (System.currentTimeMillis() - lastMove > delay) {
                y = y + down * speed;
                lastMove =stem.currentTimeMillis() System.currentTimeMillis();
            }


            boolean hit = false;
            for (int row = shapes[randnum].length - 1; row >= 0 && !hit; row--) {
                for (int col = 0; col < shapes[randnum][0].length; col++) {
                    if (shapes[randnum][row][col][r] == true) {

                        if (y + h * row - 30 + h == HEIGHT - 25) {
                            speed = 0;
                            hit = true;
                            break;
                        }else{
                            speed = 1;
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
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                };
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
        FinalProject game = new FinalProject();
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
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            r = (r + 1) % 4;
        }
        if (key == KeyEvent.VK_RIGHT) {
            x = x + 30;
        }
        if (key == KeyEvent.VK_LEFT) {
            x = x - 30;
        }
        if (key == KeyEvent.VK_DOWN) {
            y = y + 30;
            lastMove = System.currentTimeMillis();
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }
}