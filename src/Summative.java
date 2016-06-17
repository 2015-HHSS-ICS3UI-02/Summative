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
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author RARES
 */
public class Summative extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;

    //Import an image and set it as the background to cover the entire screen
    BufferedImage background = ImageHelper.loadImage("SpaceBackground.jpg");

    //Create an invisible rectangle, place its coordinates
    Rectangle main = new Rectangle(350, HEIGHT / 2 - 50, 100, 75);
    //For obstacle 1-6, create an invisible rectangle, place its coordinates
    Rectangle obstacle1 = new Rectangle(100, 100, 50, 50);
    Rectangle obstacle2 = new Rectangle(100, 300, 50, 50);
    Rectangle obstacle3 = new Rectangle(100, 500, 50, 50);
    Rectangle obstacle4 = new Rectangle(600, 100, 50, 50);
    Rectangle obstacle5 = new Rectangle(600, 300, 50, 50);
    Rectangle obstacle6 = new Rectangle(600, 500, 50, 50);

    //Create 3 fonts to be used on the menu and reststart screen
    Font generic = new Font("Bauhaus 93", Font.BOLD, 18);
    Font titles = new Font("Bauhaus 93", Font.BOLD, 100);
    Font titleinstructions = new Font("Bauhaus 93", Font.BOLD, 50);
    Font score = new Font("Bauhaus 93", Font.BOLD, 30);

    //Import an image into the game via files (This will be the character the user controls)
    BufferedImage mainCharacter = ImageHelper.loadImage("UFO.png");
    //For asteroid 1-6, import an image into the game via files (These will be the obstacles) 
    BufferedImage asteroid1 = ImageHelper.loadImage("Others.png");
    BufferedImage asteroid2 = ImageHelper.loadImage("Others.png");
    BufferedImage asteroid3 = ImageHelper.loadImage("Others.png");
    BufferedImage asteroid4 = ImageHelper.loadImage("Others.png");
    BufferedImage asteroid5 = ImageHelper.loadImage("Others.png");
    BufferedImage asteroid6 = ImageHelper.loadImage("Others.png");
    //Import an image into the game via files (This will be a breif explosion after the collision occurs)
    BufferedImage explosion = ImageHelper.loadImage("Explode.png");
    //Import an image into the game via files (This will be the image outputed on the restart screen)
    BufferedImage gameOver = ImageHelper.loadImage("Upset.png");

    //Sound variables to be used when the sound will be played in the game logic
    Sound gameOverSound = new Sound("Explosion.wav");
    Sound ambient = new Sound("SpaceMusic.wav");

    //Create X and Y integers to direct the movement of the rectangles (Up/Down, Left/Right)
    int moveX1 = -1;
    int moveY1 = 1;
    int moveX2 = -1;
    int moveY2 = 1;
    int moveX3 = -1;
    int moveY3 = 10;
    int moveX4 = 1;
    int moveY4 = 1;
    int moveX5 = 1;
    int moveY5 = 1;
    int moveX6 = 1;
    int moveY6 = -1;

    //Create a speed variable for the obstacles
    int speed = 5;
    //Create a second speed variable for the main character (Slightly faster than the others)
    int speedMain = 10;
    //Create a variable tp set all the screens to 0 to have seperate screens (ie Menu, Instructions, Restart, etc.)
    int screen = 0;

    //Create a boolean to make all the directions of movement false
    boolean Up = false;
    boolean Down = false;
    boolean Left = false;
    boolean Right = false;
    //Create a boolean to make the collision between the character and asteroids false.
    boolean mainHit = false;
    //Create a variable to make the time of the collision = 0
    long hitTime = 0;
    //Create a 'delay' variable which will count to 1000 miliseconds after the collision.
    long delay = 2500;

    int time = 0;
    long scoretime = 0;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        // GAME DRAWING GOES HERE 

        //When the user is on the first screen (0 - Main Menu), draw the following...
        if (screen == 0) {
            //Set the background to cover the entire screen 
            g.drawImage(background, 0, 0, 800, 650, null);
            //Set the colour to green
            g.setColor(Color.GREEN);
            //Set the font to the specified sizes that pertain to the variable created above 'generic'
            g.setFont(generic);
            //Tell the program what and where you want to draw a sentence
            g.drawString("PRESS SPACE TO START", 290, 450);
            //Set the colour to wwhite
            g.setColor(Color.WHITE);
            //Tell the program what and where you want to draw a sentence
            g.drawString("PRESS 'I' FOR INSTRUCTIONS", 265, 400);
            //Set the colour back to green
            g.setColor(Color.GREEN);
            //Set the font to the specified sizes that pertain to the variable created above 'titles'
            g.setFont(titles);
            //Tell the program what and where you want to draw a sentence
            g.drawString("SPACE RUMBLE", 50, 210);

            //If the user is on the second sceen (1 - Instructions), draw the following...
        } else if (screen == 1) {
            //Set the background to cover the entire screen
            g.drawImage(background, 0, 0, 800, 650, null);
            //Set the colour to green
            g.setColor(Color.GREEN);
            //Set the font to the specified sizes that pertain to the variable created above 'titleinstructions'
            g.setFont(titleinstructions);
            //Tell the program what and where you want to draw a sentence
            g.drawString("INSTRUCTIONS:", 100, 150);
            //Set the font to the specified sizes that pertain to the variable created above 'generic'
            g.setFont(generic);
            //Tell the program what and where you want to draw a sentence
            g.drawString("Use the arrow keys to maneuver and avoid the asteroids. GOOD LUCK!", 100, 200);
            //Set the colour to white
            g.setColor(Color.white);
            //Tell the program what and where you want to draw a sentence
            g.drawString("PRESS 'ESCAPE' TO RETURN TO THE MAIN MENU", 175, 500);

            //If the user is on the third sceen (2 - Game Screen), draw the following...
        } else if (screen == 2) {
            //Set the background to cover the entire screen
            g.drawImage(background, 0, 0, 800, 650, null);
            //Draw the buffered image with the coordinates of its given rectangle
            g.drawImage(mainCharacter, main.x, main.y, main.width, main.height, null);
            //For Asteroid 1-6, draw the image with the coordinates of its given rectangle
            g.drawImage(asteroid1, obstacle1.x, obstacle1.y, obstacle1.width, obstacle1.height, null);
            g.drawImage(asteroid2, obstacle2.x, obstacle2.y, obstacle2.width, obstacle2.height, null);
            g.drawImage(asteroid3, obstacle3.x, obstacle3.y, obstacle3.width, obstacle3.height, null);
            g.drawImage(asteroid4, obstacle4.x, obstacle4.y, obstacle4.width, obstacle4.height, null);
            g.drawImage(asteroid5, obstacle5.x, obstacle5.y, obstacle5.width, obstacle5.height, null);
            g.drawImage(asteroid6, obstacle6.x, obstacle6.y, obstacle6.width, obstacle6.height, null);
            //If a collision occurs between the main character and any of the six asteroids...
            if (mainHit) {
                //Draw an image with the same coordinates as the UFO to make it look like it exploded
                g.drawImage(explosion, main.x, main.y, main.width, main.height, null);
            }

            //If the user is on the last screen (3 - Game Over)
        } else if (screen == 3) {
            //Set the background to cover the entire screen
            g.drawImage(background, 0, 0, 800, 650, null);
            //Draw the buffered image 'gameOver' with the coordinates of its given rectangle
            g.drawImage(gameOver, 225, 210, 350, 300, null);
            //Set the font to the specified sizes that pertain to the variable created above 'titles'
            g.setFont(titles);
            //Set the color to green
            g.setColor(Color.GREEN);
            //Tell the program what and where you want to draw a sentence
            g.drawString("GAME OVER", 125, 130);
            //Set the color to white
            g.setColor(Color.WHITE);
            //Set the font to the specified sizes that pertain to the variable created above 'generic'
            g.setFont(generic);
            //Tell the program what and where you want to draw a sentence
            g.drawString("PRESS 'SHIFT' TO RESTART", 290, 550);
            //Tell the program what and where you want to draw a sentence
            g.drawString("PRESS 'ESCAPE' TO RETURN TO THE MAIN MENU", 200, 575);
            //Set the font to the specified sizes that pertain to the variable created above 'score'
            g.setFont(score);
            g.drawString("Your score was " + time + " seconds", 210, 185);

        }

// GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        //Play the sound file when the game opens
        ambient.play();
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
            //If the user is on the third screen (2 - Game Screen) do the following movements
            if (screen == 2) {
                if (scoretime == 0) {
                    //Add 1 second to the score each time
                    scoretime = System.currentTimeMillis() + 1000;
                }
                //If the current time is greater than the time the game ran and there is no collision, add one to it
                if (System.currentTimeMillis() > scoretime && !mainHit) {
                    time++;
                    //Add 1 secord to the score each time
                    scoretime = System.currentTimeMillis() + 1000;
                }

                //If there is not yet a collision amongst the main character and the asteroids, do the following...
                if (!mainHit) {

                    //For Obstacle 1-6, make the rectangle move either up or down and set the speed
                    obstacle1.x = obstacle1.x - moveX1 * speed;
                    obstacle1.y = obstacle1.y + moveY1 * speed;

                    obstacle2.x = obstacle2.x - moveX2 * speed;
                    obstacle2.y = obstacle2.y - moveY2 * speed;

                    obstacle3.x = obstacle3.x - moveX3 * speed;
                    obstacle3.y = obstacle3.y + moveY3 * speed;

                    obstacle4.x = obstacle4.x + moveX4 * speed;
                    obstacle4.y = obstacle4.y + moveY4 * speed;

                    obstacle5.x = obstacle5.x + moveX5 * speed;
                    obstacle5.y = obstacle5.y - moveY5 * speed;

                    obstacle6.x = obstacle6.x + moveX6 * speed;
                    obstacle6.y = obstacle6.y + moveY6 * speed;

                }
                //(Wall Collisions) If there is not yet a collision amongst the main character and the asteroids, do the following...
                if (!mainHit) {

                    //If the height and y coordinate is greater than the height
                    if (obstacle1.y + obstacle1.height > HEIGHT) {
                        //Move the asteroid in the opposing direction
                        moveY1 = -1;
                    }

                    //If the y coordinate is less than zero
                    if (obstacle1.y < 0) {
                        //Move the asteroid in the opposing direction
                        moveY1 = 1;
                    }

                    //If the width and the x coordinate is greater than the width
                    if (obstacle1.x + obstacle1.width > WIDTH) {
                        //Move the asteroid in the opposing direction
                        moveX1 = 1;
                    }

                    //If the x coordinate is less than zero
                    if (obstacle1.x < 0) {
                        //Move the asteroid in the oppsosing direction
                        moveX1 = -1;
                    }

                    //If the height and y coordinate is greater than the height
                    if (obstacle2.y + obstacle2.height > HEIGHT) {
                        //move the asteroid in the oppsosing direction
                        moveY2 = 1;
                    }

                    //If the y coordinate is less than zero
                    if (obstacle2.y < 0) {
                        //Move the asteroid in the opposing direction
                        moveY2 = -1;
                    }

                    //If the width and the x coordinate is greater than the width
                    if (obstacle2.x + obstacle2.width > WIDTH) {
                        //Move the asteroid in the opposing direction
                        moveX2 = 1;
                    }

                    //If the x coordinate is less than zero
                    if (obstacle2.x < 0) {
                        //Move the asteroid in the opposing direction
                        moveX2 = -1;
                    }

                    //If the height and y coordinate is greater than the height
                    if (obstacle3.y + obstacle3.height > HEIGHT) {
                        //Move the asteroid in the opposing direction
                        moveY3 = -1;
                    }
                    //If the y coordinate is less than zero
                    if (obstacle3.y < 0) {
                        //Move the asteroid in the opposing direction
                        moveY3 = 1;
                    }
                    //If the width and the x coordinate is greater than the width
                    if (obstacle3.x + obstacle3.width > WIDTH) {
                        //Move the asteroid in the opposing direction
                        moveX3 = 1;
                    }
                    //If the x coordinate is less than zero
                    if (obstacle3.x < 0) {
                        //Move the asteroid in the opposing direction
                        moveX3 = -1;
                    }
                    //If the height and y coordinate is greater than the height
                    if (obstacle4.y + obstacle4.height > HEIGHT) {
                        //Move the asteroid in the opposing direction
                        moveY4 = -1;
                    }
                    //If the y coordinate is less than zero
                    if (obstacle4.y < 0) {
                        //Move the asteroid in the opposing direction
                        moveY4 = 1;
                    }
                    //If the x coordinate is less than zero
                    if (obstacle4.x + obstacle4.width > WIDTH) {
                        //Move the asteroid in the opposing direction
                        moveX4 = -1;
                    }
                    //If the x coordinate is less than zero
                    if (obstacle4.x < 0) {
                        //Move the asteroid in the opposing direction
                        moveX4 = 1;
                    }
                    //If the height and y coordinate is greater than the height
                    if (obstacle5.y + obstacle5.height > HEIGHT) {
                        //Move the asteroid in the opposing direction
                        moveY5 = 1;
                    }
                    //If the y coordinate is less than zero
                    if (obstacle5.y < 0) {
                        //Move the asteroid in the opposing direction
                        moveY5 = -1;
                    }
                    //If the x coordinate is less than zero
                    if (obstacle5.x + obstacle5.width > WIDTH) {
                        //Move the asteroid in the opposing direction
                        moveX5 = -1;
                    }
                    //If the x coordinate is less than zero
                    if (obstacle5.x < 0) {
                        //Move the asteroid in the opposing direction
                        moveX5 = 1;
                    }
                    //If the height and y coordinate is greater than the height
                    if (obstacle6.y + obstacle6.height > HEIGHT) {
                        //Move the asteroid in the opposing direction
                        moveY6 = -1;
                    }
                    //If the y coordinate is less than zero
                    if (obstacle6.y < 0) {
                        //Move the asteroid in the opposing direction
                        moveY6 = 1;
                    }
                    //If the x coordinate is less than zero
                    if (obstacle6.x + obstacle6.width > WIDTH) {
                        //Move the asteroid in the opposing direction
                        moveX6 = -1;
                    }
                    //If the x coordinate is less than zero
                    if (obstacle6.x < 0) {
                        //Move the asteroid in the opposing direction
                        moveX6 = 1;
                    }

                    //Controlling the main character, allowing movements for up and down
                    if (Up && main.y > 0) {
                        main.y = main.y - speedMain;
                    } else if (Down && main.y + main.height < HEIGHT) {
                        main.y = main.y + speedMain;
                    }

                    //Controlling the main character, allowing movements for left and right
                    if (Left && main.x > 0) {
                        main.x = main.x - speedMain;
                    } else if (Right && main.x + main.width < WIDTH) {
                        main.x = main.x + speedMain;
                    }
                }

                //(Collisions to stop the game) When one of the obstacles touch the main character
                if ((collides(obstacle1, main)
                        || collides(obstacle2, main)
                        || collides(obstacle3, main)
                        || collides(obstacle4, main)
                        || collides(obstacle5, main)
                        || collides(obstacle6, main)) && !mainHit) {
                    //If the collision is true
                    mainHit = true;
                    //Delay the next screen 
                    hitTime = System.currentTimeMillis();
                    //Play the explosion sound after the collision occurs
                    gameOverSound.play();
                }

                //If the collision occurs and the delayed time switch to screen 3 (restsrt screen)
                if (mainHit && System.currentTimeMillis() > hitTime + delay) {
                    screen = 3;
                }

                //If a collision occurs with obstacle 1 and any of the other obstacle
                if (collides(obstacle2, obstacle1)
                        || collides(obstacle3, obstacle1)
                        || collides(obstacle4, obstacle1)
                        || collides(obstacle5, obstacle1)
                        || collides(obstacle6, obstacle1)) {
                    //Move the asteroid in the opposing direction
                    moveX1 = -1 * moveX1;
                }

                //If a collision occurs with obstacle 2 and any of the other obstacle
                if (collides(obstacle1, obstacle2)
                        || collides(obstacle3, obstacle2)
                        || collides(obstacle4, obstacle2)
                        || collides(obstacle5, obstacle2)
                        || collides(obstacle6, obstacle2)) {
                    moveX2 = -1 * moveX2;
                }

                //If a collision occurs with obstacle 3 and any of the other obstacle
                if (collides(obstacle1, obstacle3)
                        || collides(obstacle2, obstacle3)
                        || collides(obstacle4, obstacle3)
                        || collides(obstacle5, obstacle3)
                        || collides(obstacle6, obstacle3)) {
                    moveX3 = -1 * moveX3;
                }

                //If a collision occurs with obstacle 4 and any of the other obstacle
                if (collides(obstacle1, obstacle4)
                        || collides(obstacle2, obstacle4)
                        || collides(obstacle3, obstacle4)
                        || collides(obstacle5, obstacle4)
                        || collides(obstacle6, obstacle4)) {
                    //Move the asteroid in the opposing direction
                    moveX4 = -1 * moveX4;
                }

                //If a collision occurs with obstacle 5 and any of the other obstacle
                if (collides(obstacle1, obstacle5)
                        || collides(obstacle2, obstacle5)
                        || collides(obstacle3, obstacle5)
                        || collides(obstacle4, obstacle5)
                        || collides(obstacle6, obstacle5)) {
                    //Move the asteroid in the opposing direction
                    moveX5 = -1 * moveX5;
                }

                //If a collision occurs with obstacle 6 and any of the other obstacle
                if (collides(obstacle1, obstacle6)
                        || collides(obstacle2, obstacle6)
                        || collides(obstacle3, obstacle6)
                        || collides(obstacle4, obstacle6)
                        || collides(obstacle5, obstacle6)) {
                    //Move the asteroid in the opposing direction
                    moveX6 = -1 * moveX6;
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
        Summative game = new Summative();
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

        //make the game respond to the keyboard 
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
        //If the up arrow key is pressed allow the character to move in that direction
        if (key == KeyEvent.VK_UP) {
            Up = true;
            //If the down arrow key is pressed allow the character to move in that direction
        } else if (key == KeyEvent.VK_DOWN) {
            Down = true;
            //If the left arrow key is pressed allow the character to move in that direction
        } else if (key == KeyEvent.VK_LEFT) {
            Left = true;
            //If the right arrow key is pressed allow the character to move in that direction
        } else if (key == KeyEvent.VK_RIGHT) {
            Right = true;
        }
        //If the space key is pressed, switch to the game screen
        if (key == KeyEvent.VK_SPACE && screen == 0) {
            screen = 2;
        }
        //If the I key is pressed, switch to the instructions screen
        if (key == KeyEvent.VK_I && screen == 0) {
            screen = 1;
        }
        //If the escape key is pressed on the instructions screen, take the user back to the main menu
        if (key == KeyEvent.VK_ESCAPE && screen == 1) {
            screen = 0;
        }
        //If the escape key is pressed on the restart screen, take the user back to the main menu
        if (key == KeyEvent.VK_ESCAPE && screen == 3) {
            screen = 0;

            //Place all the objects in their initial place when the game is over in order to reloop the game
            main.x = 350;
            main.y = HEIGHT / 2 - 50;
            main.width = 100;
            main.height = 75;

            obstacle1.x = 100;
            obstacle1.y = 100;
            obstacle1.width = 50;
            obstacle1.height = 50;

            obstacle2.x = 100;
            obstacle2.y = 300;
            obstacle2.width = 50;
            obstacle2.height = 50;

            obstacle3.x = 100;
            obstacle3.y = 500;
            obstacle3.width = 50;
            obstacle3.height = 50;

            obstacle4.x = 600;
            obstacle4.y = 100;
            obstacle4.width = 50;
            obstacle4.height = 50;

            obstacle5.x = 600;
            obstacle5.y = 300;
            obstacle5.width = 50;
            obstacle5.height = 50;

            obstacle6.x = 600;
            obstacle6.y = 500;
            obstacle6.width = 50;
            obstacle6.height = 50;

            time = 0;
            mainHit = false;
        }
        //If the shift key is pressed on the restart screen, restart the game
        if (key == KeyEvent.VK_SHIFT && screen == 3) {
            //Reloop the music when the game restarts
            ambient.play();
            screen = 2;

            //Place all the objects in their initial place when the game is over in order to reloop the game
            main.x = 350;
            main.y = HEIGHT / 2 - 50;
            main.width = 100;
            main.height = 75;

            obstacle1.x = 100;
            obstacle1.y = 100;
            obstacle1.width = 50;
            obstacle1.height = 50;

            obstacle2.x = 100;
            obstacle2.y = 300;
            obstacle2.width = 50;
            obstacle2.height = 50;

            obstacle3.x = 100;
            obstacle3.y = 500;
            obstacle3.width = 50;
            obstacle3.height = 50;

            obstacle4.x = 600;
            obstacle4.y = 100;
            obstacle4.width = 50;
            obstacle4.height = 50;

            obstacle5.x = 600;
            obstacle5.y = 300;
            obstacle5.width = 50;
            obstacle5.height = 50;

            obstacle6.x = 600;
            obstacle6.y = 500;
            obstacle6.width = 50;
            obstacle6.height = 50;

            time = 0;
            mainHit = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //If the up key is no longer pressed, stop the character from moving in that direction
        if (key == KeyEvent.VK_UP) {
            Up = false;
            //If the down key is no longer pressed, stop the character from moving in that direction
        } else if (key == KeyEvent.VK_DOWN) {
            Down = false;
            //If the left key is no longer pressed, stop the character from moving in that direction
        } else if (key == KeyEvent.VK_LEFT) {
            Left = false;
            //If the right key is no longer pressed, stop the character from moving in that direction
        } else if (key == KeyEvent.VK_RIGHT) {
            Right = false;
        }

    }

    //Create a method to calculate the centre and take the radius of both circles of the circle, called the method collides
    //To be used in the game logic
    boolean collides(Rectangle r1, Rectangle r2) {
        if ((r1.getCenterX() - r2.getCenterX()) * (r1.getCenterX() - r2.getCenterX()) + (r1.getCenterY() - r2.getCenterY()) * (r1.getCenterY() - r2.getCenterY()) < (r1.width / 2 + r2.width / 2) * (r1.width / 2 + r2.width / 2)) {
            return true;
        }
        return false;
    }

}
