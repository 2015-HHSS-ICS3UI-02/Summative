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
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author kangh4484
 */
public class game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 700;
    static final int HEIGHT = 700;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //page
    int page = 0;
    //size of the rock
    int w = 20;
    int h = 20;
    //size for 1 cube
    int x = 30;
    int y = 30;
    //black or white win
    boolean blackwin = false;
    boolean whitewin = false;
    //check maximun rocks in a row
    int largest = 0;
    //boolean for whether any of them won
    boolean win = false;
    //font for string input
    Font gamefont = new Font("Arial", Font.ITALIC, 40);
    Font gamefont1 = new Font("Arial", Font.PLAIN, 20);
    //rock
    Rectangle rock = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, w, h);
    //number of rock in screen
    int numBlack = 0;
    int numWhite = 0;
    //array to store rocks
    Rectangle[] black = new Rectangle[220];
    Rectangle[] white = new Rectangle[220];
    //score
    int blackscore = 0;
    int whitescore = 0;
    //turn
    int turn = 0;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        //if page is 0 which is introduction screen
        if (page == 0) {
            //draw a border
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.black);
            g.drawRect(50, 50, WIDTH - 100, HEIGHT - 100);
            //input introduction and controls
            g.setFont(gamefont);
            g.drawString("Omok is game that is played", 100, 100);
            g.drawString("by 2 players which divided", 100, 150);
            g.drawString("into Black and White.", 100, 200);
            g.drawString("Black or White have to have", 100, 250);
            g.drawString("5 stones straight or diagonally", 100, 300);
            g.drawString("in order to Win.", 100, 350);
            g.drawString("control:", WIDTH - 400, 400);
            g.drawString("Black", 100, 450);
            g.drawString("White", WIDTH / 2, 450);
            g.setFont(gamefont1);
            g.drawString("W - up", 110, 475);
            g.drawString("A - left", 110, 500);
            g.drawString("S - down", 110, 525);
            g.drawString("D - right", 110, 550);
            g.drawString("Space bar - place stone", 110, 575);
            g.drawString("arrow up - up", WIDTH / 2 + 10, 475);
            g.drawString("arrow left - left", WIDTH / 2 + 10, 500);
            g.drawString("arrow down - down", WIDTH / 2 + 10, 525);
            g.drawString("arrow right - right", WIDTH / 2 + 10, 550);
            g.drawString("Space bar - place stone", WIDTH / 2 + 10, 575);
            g.setFont(gamefont1);
            g.drawString("Please Press ENTER to Start", WIDTH / 2 - 150, 625);
        }
        //if page is 1 which is game screen
        if (page == 1) {
            //background
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            //board
            g.setColor(Color.black);
            g.drawRect(50, 50, WIDTH - 100, HEIGHT - 100);
            g.drawLine(80, 50, 80, HEIGHT - 50);
            g.drawLine(110, 50, 110, HEIGHT - 50);
            g.drawLine(140, 50, 140, HEIGHT - 50);
            g.drawLine(170, 50, 170, HEIGHT - 50);
            g.drawLine(200, 50, 200, HEIGHT - 50);
            g.drawLine(230, 50, 230, HEIGHT - 50);
            g.drawLine(260, 50, 260, HEIGHT - 50);
            g.drawLine(290, 50, 290, HEIGHT - 50);
            g.drawLine(320, 50, 320, HEIGHT - 50);
            g.drawLine(350, 50, 350, HEIGHT - 50);
            g.drawLine(380, 50, 380, HEIGHT - 50);
            g.drawLine(410, 50, 410, HEIGHT - 50);
            g.drawLine(440, 50, 440, HEIGHT - 50);
            g.drawLine(470, 50, 470, HEIGHT - 50);
            g.drawLine(500, 50, 500, HEIGHT - 50);
            g.drawLine(530, 50, 530, HEIGHT - 50);
            g.drawLine(560, 50, 560, HEIGHT - 50);
            g.drawLine(590, 50, 590, HEIGHT - 50);
            g.drawLine(620, 50, 620, HEIGHT - 50);
            g.drawLine(50, 80, WIDTH - 50, 80);
            g.drawLine(50, 110, WIDTH - 50, 110);
            g.drawLine(50, 140, WIDTH - 50, 140);
            g.drawLine(50, 170, WIDTH - 50, 170);
            g.drawLine(50, 200, WIDTH - 50, 200);
            g.drawLine(50, 230, WIDTH - 50, 230);
            g.drawLine(50, 260, WIDTH - 50, 260);
            g.drawLine(50, 290, WIDTH - 50, 290);
            g.drawLine(50, 320, WIDTH - 50, 320);
            g.drawLine(50, 350, WIDTH - 50, 350);
            g.drawLine(50, 380, WIDTH - 50, 380);
            g.drawLine(50, 410, WIDTH - 50, 410);
            g.drawLine(50, 440, WIDTH - 50, 440);
            g.drawLine(50, 470, WIDTH - 50, 470);
            g.drawLine(50, 500, WIDTH - 50, 500);
            g.drawLine(50, 530, WIDTH - 50, 530);
            g.drawLine(50, 560, WIDTH - 50, 560);
            g.drawLine(50, 590, WIDTH - 50, 590);
            g.drawLine(50, 620, WIDTH - 50, 620);

            //print out scores at top 
            g.setFont(gamefont1);
            g.drawString("BLACK: " + blackscore, WIDTH / 2 - 200, 25);
            g.drawString("WHITE: " + whitescore, WIDTH / 2 + 100, 25);


            //draw rocks
            g.setColor(Color.black);
            for (int i = 0; i < numBlack; i++) {
                g.fillOval(black[i].x, black[i].y, black[i].width, black[i].height);

            }
            g.setColor(Color.white);
            for (int i = 0; i < numWhite; i++) {
                g.fillOval(white[i].x, white[i].y, white[i].width, white[i].height);

            }

            //color of rocks for each turn
            if (turn == 0) {
                g.setColor(Color.black);
            }
            if (turn == 1) {
                g.setColor(Color.white);
            }

            //shadow to tell user where the rock currently is
            g.fillOval(rock.x, rock.y, rock.width, rock.height);

            //if black or white win then print out who won
            g.setFont(gamefont);
            if (blackwin == true && largest == 5) {
                g.setColor(Color.BLACK);
                g.drawString("BLACK WIN!!!", WIDTH / 2 - 100, HEIGHT / 2);
                g.setFont(gamefont1);
                g.drawString("Press ENTER to Restart", WIDTH / 2 - 100, HEIGHT / 2 + 200);

            } else if (whitewin == true && largest == 5) {
                g.setColor(Color.WHITE);
                g.drawString("WHITE WIN!!!", WIDTH / 2 - 100, HEIGHT / 2);
                g.setFont(gamefont1);
                g.setColor(Color.BLACK);
                g.drawString("Press ENTER to Restart", WIDTH / 2 - 100, HEIGHT / 2 + 200);

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
            //page 1
            if (page == 1) {
                //if nobody won yet
                if (!win) {
                    //check black's stone in a row if  white's turn starts
                    if (turn == 1) {
                        //count rock in a row(right side)
                        for (int a = 0; a < numBlack; a++) {
                            //distance between rocks
                            int spacing = 30;
                            //number of stone in a row
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                //if block is in a row
                                if (black[a].x + spacing == black[b].x && black[a].y == black[b].y) {
                                    //add number 1 to number of stone in a row
                                    number++;
                                    //add 30 to spacing to check stone next to it
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                //if number of stone is bigger then maximun stone in a row change the maximun to number of stone in a row
                                largest = number;

                            }
                            //if number of stone in a row is 5 make black win = true
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        //count rock in a row(left side)
                        for (int a = 0; a < numBlack; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                if (black[a].x - spacing == black[b].x && black[a].y == black[b].y) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        //count rock in a row(up)
                        for (int a = 0; a < numBlack; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                if (black[a].y + spacing == black[b].y && black[a].x == black[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;

                            }
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        //count rock in a row(down)
                        for (int a = 0; a < numBlack; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                if (black[a].y - spacing == black[b].y && black[a].x == black[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        //count rock in a row(right down diagonally)
                        for (int a = 0; a < numBlack; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                if (black[a].y + spacing == black[b].y && black[a].x + spacing == black[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;

                            }
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        //count rock in a row(left up diagonally)
                        for (int a = 0; a < numBlack; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                if (black[a].y - spacing == black[b].y && black[a].x - spacing == black[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        //count rock in a row(left down diagonally)
                        for (int a = 0; a < numBlack; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                if (black[a].y + spacing == black[b].y && black[a].x - spacing == black[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;

                            }
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        //count rock in a row(right up diagonally)
                        for (int a = 0; a < numBlack; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numBlack; b++) {
                                if (black[a].y - spacing == black[b].y && black[a].x + spacing == black[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN BLACK! 
                                win = true;
                                blackwin = true;
                            }

                        }
                        System.out.println("Black's Largest Line: " + largest);



                    }
                    //check white's stone in a row if  black's turn starts
                    if (turn == 0) {
                        //count rock in a row(right)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].x + spacing == white[b].x && white[a].y == white[b].y) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;

                            }
                            if (number == 5) {
                                //YAY WIN WHITE! 
                                win = true;
                                whitewin = true;
                            }

                        }
                        //count rock in a row(left)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].x - spacing == white[b].x && white[a].y == white[b].y) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN WHITE! 
                                win = true;
                                whitewin = true;
                            }

                        }
                        //count rock in a row(up)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].y + spacing == white[b].y && white[a].x == white[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;

                            }
                            if (number == 5) {
                                //YAY WIN WHITE!  
                                win = true;
                                whitewin = true;
                            }

                        }
                        //count rock in a row(down)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].y - spacing == white[b].y && white[a].x == white[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN WHITE! 
                                win = true;
                                whitewin = true;
                            }

                        }
                        //count rock in a row(right down diagonally)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].y + spacing == white[b].y && white[a].x + spacing == white[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;

                            }
                            if (number == 5) {
                                //YAY WIN WHITE! 
                                win = true;
                                whitewin = true;
                            }

                        }
                        //count rock in a row(left up diagonally)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].y - spacing == white[b].y && white[a].x - spacing == white[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN WHITE! 
                                win = true;
                                whitewin = true;
                            }

                        }
                        //count rock in a row(left down diagonally)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].y + spacing == white[b].y && white[a].x - spacing == white[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;

                            }
                            if (number == 5) {
                                //YAY WIN WHITE!  
                                win = true;
                                whitewin = true;
                            }

                        }
                        //count rock in a row(up right diagonally)
                        for (int a = 0; a < numWhite; a++) {
                            int spacing = 30;
                            int number = 1;
                            for (int b = 0; b < numWhite; b++) {
                                if (white[a].y - spacing == white[b].y && white[a].x + spacing == white[b].x) {
                                    number++;
                                    spacing = spacing + 30;
                                    b = 0;
                                }
                            }
                            if (number > largest) {
                                largest = number;
                            }
                            if (number == 5) {
                                //YAY WIN WHITE!  
                                win = true;
                                whitewin = true;
                            }

                        }
                        System.out.println("White's Largest Line: " + largest);



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
        game game = new game();
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
        //if page is 0 make page into 1 by pressing enter
        if (page == 0) {
            if (key == KeyEvent.VK_ENTER) {
                page = page + 1;
            }
        }
        //if page is 1
        if (page == 1) {
            //and nobody won yet
            if (!win) {
                //control for black
                if (turn == 0) {
                    //w to move up
                    if (key == KeyEvent.VK_W) {
                        if (rock.y > 50) {
                            rock.y = rock.y - 30;
                        }
                    }
                    //s to move down
                    if (key == KeyEvent.VK_S) {
                        if (rock.y < HEIGHT - 80) {
                            rock.y = rock.y + 30;
                        }
                    }
                    //d to move right
                    if (key == KeyEvent.VK_D) {
                        if (rock.x < WIDTH - 80) {
                            rock.x = rock.x + 30;
                        }
                    }
                    //a to move left
                    if (key == KeyEvent.VK_A) {
                        if (rock.x > 50) {
                            rock.x = rock.x - 30;
                        }
                    }
                    //space bar to put stone
                    if (key == KeyEvent.VK_SPACE) {
                        boolean safe = true;
                        for (int i = 0; i < black.length; i++) {
                            //if there is rock in same position
                            if (black[i] != null && black[i].equals(rock) || white[i] != null && white[i].equals(rock)) {
                                //make boolean safe == false
                                safe = false;
                                break;
                            }
                        }
                        //if its safe
                        if (safe) {
                            //make it white's turn
                            turn = 1;
                            //clone stone 
                            black[numBlack] = (Rectangle) rock.clone();
                            //add 1 to number of black stone
                            numBlack++;


                        }
                    }
                    // if its white's turn
                } else if (turn == 1) {
//arrow up to move up
                    if (key == KeyEvent.VK_UP) {
                        if (rock.y > 50) {
                            rock.y = rock.y - 30;
                        }
                    }
                    //arrow down to move down
                    if (key == KeyEvent.VK_DOWN) {
                        if (rock.y < HEIGHT - 80) {
                            rock.y = rock.y + 30;
                        }
                    }
                    //arrow right to move right
                    if (key == KeyEvent.VK_RIGHT) {
                        if (rock.x < WIDTH - 80) {
                            rock.x = rock.x + 30;
                        }
                    }
                    //arrow left to move left
                    if (key == KeyEvent.VK_LEFT) {
                        if (rock.x > 50) {
                            rock.x = rock.x - 30;
                        }
                    }
                    //enter to place the stone
                    if (key == KeyEvent.VK_ENTER) {
                        boolean safe = true;
                        for (int i = 0; i < black.length; i++) {
                            //check whether there is other stone on spot
                            if (black[i] != null && black[i].equals(rock) || white[i] != null && white[i].equals(rock)) {
                                safe = false;
                                break;
                            }
                        }
                        if (safe) {
                            //change to black's turn
                            turn = 0;
                            //clont white rock to place
                            white[numWhite] = (Rectangle) rock.clone();
                            //add 1 to number of white stone
                            numWhite++;

                        }
                    }
                }

            } //if someone win
            else if (win) {
                //press enter to reset all the value and add 1 to winner's score
                if (key == KeyEvent.VK_ENTER) {
                    if (blackwin) {
                        blackscore++;
                        blackwin = false;
                    } else if (whitewin) {
                        whitescore++;
                        whitewin = false;
                    }
                    win = false;
                    numBlack = 0;
                    numWhite = 0;
                    for (int i = 0; i < black.length; i++) {
                        black[i] = null;
                        white[i] = null;
                    }
                    largest = 0;
                    rock.x = WIDTH / 2 - 10;
                    rock.y = HEIGHT / 2 - 10;
                    turn = 0;


                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
