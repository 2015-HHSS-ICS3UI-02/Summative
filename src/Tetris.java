/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author rahmf8586
 */
public class Tetris extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 600;
    static final int HEIGHT = 800;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //make an array for the grid
    Color[][] grid = new Color[16][10];
    //create the different game pieces
    boolean[][] Jshape = {
        {true, false, false},
        {true, true, true}
    };
    boolean[][] Zshape = {
        {true, true, false},
        {false, true, true}
    };
    boolean[][] Oshape = {
        {true, true},
        {true, true}
    };
    boolean[][] Tshape = {
        {false, true, false},
        {true, true, true}
    };
    boolean[][] Sshape = {
        {false, true, true},
        {true, true, false}
    };
    boolean[][] Ishape = {
        {true, true, true, true}
    };
    boolean[][] Lshape = {
        {false, false, true},
        {true, true, true}
    };
    //put all the piece shapes into one array
    boolean[][][] shapes = {
        Sshape, Ishape, Jshape, Lshape, Zshape, Oshape, Tshape
    };
    //set the variables for the 3D boolean
    int x = 4 * WIDTH / 10;
    int y = 0;
    int piece = 6;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        //create large square (background), colour background
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //create grid lines, colour grid lines a different colour
        g.setColor(Color.black);
        for (int i = 0; i <= WIDTH; i = i + WIDTH / 10) {
            g.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = 0; i <= HEIGHT; i = i + HEIGHT / 16) {
            g.drawLine(0, i, WIDTH, i);
        }

        //colour the part of the grid with the piece occupying that space
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] != null) {
                    g.setColor(grid[row][col]);
                    g.fillRect(col * WIDTH / 10, row * HEIGHT / 16, WIDTH / 10, HEIGHT / 16);
                }
            }
        }

        //colour the different pieces based on their location in the boolean
        if (piece == 0) {
            g.setColor(Color.red);

        } else if (piece == 1) {
            g.setColor(Color.blue);

        } else if (piece == 2) {
            g.setColor(Color.green);

        } else if (piece == 3) {
            g.setColor(Color.black);

        } else if (piece == 4) {
            g.setColor(Color.magenta);

        } else if (piece == 5) {
            g.setColor(Color.cyan);

        } else if (piece == 6) {
            g.setColor(Color.gray);
        }

        //put the shape in the top centre of the grid
        for (int row = 0; row < shapes[piece].length; row++) {
            for (int col = 0; col < shapes[piece][row].length; col++) {
                if (shapes[piece][row][col]) {
                    g.fillRect(x + col * (WIDTH / 10), y + row * (HEIGHT / 16), WIDTH / 10, HEIGHT / 16);
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

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            //make the piece fall down the screen
            y++;

            //collisions
            if (x < 0 || x >= (WIDTH / 10) * 10 - shapes.length || y < 0) {
                y = y + 0;
            }

            for (int row = 0; row < shapes[piece].length; row++) {
                for (int col = 0; col < shapes[piece][row].length; col++) {
                    if (shapes[piece][row][col] && grid[y / (HEIGHT / 16) + row][x / (WIDTH / 10) + col] != null) {
                        System.out.println("hit");
                        
                    }
                }
            }

            if (y >= (HEIGHT / 16) * 14) {
                if (piece == 0) {
                    //g.setColor(Color.red);
                } else if (piece == 1) {
                    //g.setColor(Color.blue);
                } else if (piece == 2) {
                    //g.setColor(Color.green);
                } else if (piece == 3) {
                    //g.setColor(Color.black);
                } else if (piece == 4) {
                    //colour grid where the piece fell, same colour as piece
                    for (int row = 0; row < shapes[piece].length; row++) {
                        for (int col = 0; col < shapes[piece][0].length; col++) {
                            if (shapes[piece][row][col]) {
                                grid[y / (HEIGHT / 16) + row][x / (WIDTH / 10) + col] = Color.magenta;
                                System.out.println("r: " + (y / (HEIGHT / 16) + row) + "   c: " + (x / (WIDTH / 10) + col));
                            }
                        }
                    }
                    //set a new random piece back at the top
                    y = 0;
                    int randNum = (int) (Math.random() * (6 - 0 + 0)) + 1;
                    piece = randNum;

                } else if (piece == 5) {
                    //colour grid where the piece fell, same colour as piece
                    for (int row = 0; row < shapes[piece].length; row++) {
                        for (int col = 0; col < shapes[piece][0].length; col++) {
                            if (shapes[piece][row][col]) {
                                grid[y / (HEIGHT / 16) + row][x / (WIDTH / 10) + col] = Color.cyan;
                                System.out.println("r: " + (y / (HEIGHT / 16) + row) + "   c: " + (x / (WIDTH / 10) + col));
                            }
                        }
                    }
                    //set a new random piece back at the top
                    y = 0;
                    int randNum = (int) (Math.random() * (6 - 0 + 0)) + 1;
                    piece = randNum;

                } else if (piece == 6) {
                    //colour grid where the piece fell, same colour as piece
                    for (int row = 0; row < shapes[piece].length; row++) {
                        for (int col = 0; col < shapes[piece][0].length; col++) {
                            if (shapes[piece][row][col]) {
                                grid[y / (HEIGHT / 16) + row][x / (WIDTH / 10) + col] = Color.gray;
                                System.out.println("r: " + (y / (HEIGHT / 16) + row) + "   c: " + (x / (WIDTH / 10) + col));
                            }
                        }
                    }
                    //set a new random piece back at the top
                    y = 0;
                    int randNum = (int) (Math.random() * (6 - 0 + 0)) + 1;
                    piece = randNum;
                }
            }

            if (piece == 0) {
                for (int i = 0; i < 4; i++) {
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
        Tetris game = new Tetris();
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
        int key = e.getKeyCode();
        //make the piece move based on what key is pressed
        if (key == KeyEvent.VK_RIGHT) {
            x = x + WIDTH / 10;
        }
        if (key == KeyEvent.VK_LEFT) {
            x = x - WIDTH / 10;
        }
        if (key == KeyEvent.VK_DOWN) {
            y = y + HEIGHT / 16;
        }
        if (key == KeyEvent.VK_UP) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}