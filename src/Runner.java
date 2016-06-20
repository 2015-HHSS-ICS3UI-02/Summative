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
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author yuk4142
 */
public class Runner extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 1275;
    static final int HEIGHT = 935;
    // sets the framerate and delay for our game
    // select appropriate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int score = 0;
    int bosstimer = 0;
    //make move speed variables for enemies
    int moveX1 = 1;
    int moveX2 = 1;
    int moveX3 = 1;
    int moveX4 = 1;
    int moveX5 = 1;
    int moveX6 = 1;
    int moveX7 = 1;
    int moveX8 = 1;
    int moveboss = 2;
    int moveY = 1;
    //main character move speed
    int speed = 3;
    // player animation frame
    float frame = 0;
    //draw rectangles for characters(sonic, bullets and boss)
    Rectangle player = new Rectangle(50, HEIGHT / 2 - 93, 59, 60);
    Rectangle enemy1 = new Rectangle(0 - 198, HEIGHT / 2 - 40, 198, 89);
    Rectangle enemy2 = new Rectangle(0 - 198, HEIGHT - 200, 198, 89);
    Rectangle enemy3 = new Rectangle(0 - 198, HEIGHT - 400, 198, 89);
    Rectangle enemy4 = new Rectangle(0 - 198, HEIGHT - 800, 198, 89);
    Rectangle enemy5 = new Rectangle(0 - 198, HEIGHT / 2 - 40, 198, 89);
    Rectangle enemy6 = new Rectangle(0 - 198, HEIGHT - 200, 198, 89);
    Rectangle enemy7 = new Rectangle(0 - 198, HEIGHT - 400, 198, 89);
    Rectangle enemy8 = new Rectangle(0 - 198, HEIGHT - 800, 198, 89);
    Rectangle bossrec = new Rectangle(1275, HEIGHT - 800, 608, 599);
    //set done boolean as false
    boolean done = false;
    //set player movement boolean's as false
    boolean playerUp = false;
    boolean playerDown = false;
    boolean playerRight = false;
    boolean playerLeft = false;
    //set level up as false
    boolean levelUp = false;
    //set start as false
    boolean start = false;
    //set reset as false
    boolean reset = false;
    //create boolean for timing score increase rate
    long scoretime = System.currentTimeMillis();
    //however long interval is between score ++
    long scoredelay = 500;
    //image for background image
    BufferedImage Terrain = ImageHelper.loadImage("Terrain.jpg");
    //image for game over page
    BufferedImage Gameover = ImageHelper.loadImage("Game over.png");
    //image for non-moving sonic
    BufferedImage Sonic = ImageHelper.loadImage("Sonic Stationary.png");
    //image for start page
    BufferedImage startPage = ImageHelper.loadImage("Opening page.png");
    //image for mario
    BufferedImage boss = ImageHelper.loadImage("Boss.png");
    //image for bullet
    BufferedImage Bullet1 = ImageHelper.loadImage("BulletMario.png");
    //array for sonic animation
    BufferedImage[] Sanimation = new BufferedImage[4];
    //import sound for game ending "bomb" noise
    Sound fin = new Sound("Bomb.wav");
    //import sound for background music
    Sound background = new Sound("bg.wav");
    //import sound to warn player of boss
    Sound buzzer = new Sound("buzzer.wav");
    ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
    //set dead boolean to false
    boolean dead = false;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //if start boolean is false, that means up arrow not pressed
        if (start != true) {
            //draw the image 
            g.drawImage(startPage, 0, 0, this);

        } else {
            g.setColor(Color.WHITE);

            //draw background
            g.drawImage(Terrain, 0, 0, this);
            //draw main character

            //if character is still, input stationary picture
            if (!playerDown && playerUp == false && playerRight == false && playerLeft == false) {

                //draw sonic character
                g.drawImage(Sonic, player.x, player.y, this);

            }
            //draw mario bullet enemies
            g.drawImage(Bullet1, enemy1.x, enemy1.y, this);
            g.drawImage(Bullet1, enemy2.x, enemy2.y, this);
            g.drawImage(Bullet1, enemy3.x, enemy3.y, this);
            g.drawImage(Bullet1, enemy4.x, enemy4.y, this);

            //draw boss image
            g.drawImage(boss, bossrec.x, bossrec.y, this);

            //game font
            Font gameFont = new Font("Arial", Font.PLAIN, 40);

            //draw score
            g.setFont(gameFont);
            g.drawString("Score:" + score, WIDTH / 2 - 100, 100);

            //if bosstimer is bigger than 17
            if (bosstimer > 17) {
                Font warning = new Font("Arial", Font.PLAIN, 70);
                //draw warning text
                g.setFont(warning);
                g.drawString("Watch out!", WIDTH - 400, HEIGHT / 2);

            }
            //draw level up sign
            if (score > 25 && score < 28) {
                //make and set new font for level up sign
                Font levelFont = new Font("Arial", Font.PLAIN, 60);
                g.setFont(levelFont);
                //change colour of font
                g.setColor(Color.yellow);
                g.drawString("Level Up!", WIDTH / 2 - 150, 300);
                //set level up boolean as true
                levelUp = true;
            }

            //load images for sonic movement
            Sanimation[0] = ImageHelper.loadImage("Sonic Ball1.png");
            Sanimation[1] = ImageHelper.loadImage("Sonic Ball2.png");
            Sanimation[2] = ImageHelper.loadImage("Sonic Ball3.png");
            Sanimation[3] = ImageHelper.loadImage("Sonic Ball4.png");
            //load images for bullet movement
            if (playerDown || playerUp || playerRight || playerLeft) {

                //draw animation of sonic ball
                g.drawImage(Sanimation[(int) frame], player.x, player.y, this);

            }

            //if sonic is hit by bullet
            if (dead) {
                //print game over screen with sad sonic
                g.drawImage(Gameover, 0, 0, this);

                //make and set new font for final score
                Font EndgameFont = new Font("Arial", Font.PLAIN, 90);
                g.setFont(EndgameFont);
                //print final score
                g.drawString("Final Score:" + score, WIDTH / 2 - 175, HEIGHT / 2);

                //if reset button "r", has been pressed
                if (reset) {
                    //set start boolean to false
                    start = false;
                    //set score to 0
                    score = 0;
                    //set boss timer to 0
                    bosstimer = 0;
                    //set 4 enemy's x coord to out of the page
                    enemy1.x = 1275;
                    enemy2.x = 1275;
                    enemy3.x = 1275;
                    enemy4.x = 1275;
                    //set boss x coord to out of the page
                    bossrec.x = 1275;
                    //set level up as false, to wait for score to hit a certain point before turning true again
                    levelUp = false;
                    //make dead boolean equal false, as character will become alive again
                    dead = false;
                    //set all speed's for enemies to be 1(standard)
                    moveX1 = 1;
                    moveX2 = 1;
                    moveX3 = 1;
                    moveX4 = 1;


                }
            }




            // GAME DRAWING ENDS HERE
        }
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
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            background.setLoop(true);
            //if background music is not playing
            if (!background.isPlaying()) {
                //play background music
                background.play();
            }
            //if start is true and dead is false
            if (start && !dead) {
                //add a timer to activate when boss moves
                if (!done && System.currentTimeMillis() - scoretime > scoredelay) {
                    score++;
                    bosstimer++;
                    scoretime = System.currentTimeMillis();
                }
                //add a timer to keep track of the score
                if (!done && System.currentTimeMillis() - scoretime > scoredelay) {
                    bosstimer++;
                    scoretime = System.currentTimeMillis();
                }
                //BOSS
                //if bosstimer is bigger than 20
                if (bosstimer > 20) {
                    //if buzzer isn't playing already, play the buzzer to warn player of boss
                    if (!buzzer.isPlaying()) {
                        buzzer.play();
                    }
                    //move boss
                    bossrec.x = bossrec.x - moveboss * 10;
                    //if boss moves to far left of screen, move it to right side, and set speed as 0
                    if (bossrec.x + 608 < 0) {
                        bossrec.x = 1275;
                        bosstimer = 0;
                    }
                }

                //make bullet 1 move
                enemy1.x = enemy1.x - moveX1 * 10;

                //make bullet 2 move
                enemy2.x = enemy2.x - moveX2 * 10;

                //make bullet 3 move
                enemy3.x = enemy3.x - moveX3 * 10;

                //make bullet 4 move
                enemy4.x = enemy4.x - moveX4 * 10;


                //generate random speed number up to 3
                int randspeed = (int) (Math.random() * 2) + 1;
                //if enemy is off the screen
                if (enemy1.x + 196 < 0) {

                    //set bullet to far right   
                    if (bosstimer < 16) {
                        enemy1.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    //input random y coord
                    enemy1.y = rand;
                    //if enemy coord intersects with any other bullet at y coord,re-random the y coord

                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy1.y > enemy2.y - 89 && enemy1.y < enemy2.y + 89 || enemy1.y > enemy3.y - 89 && enemy1.y < enemy3.y + 89 && enemy1.y > enemy4.y - 89 && enemy1.y < enemy4.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        //input random y coord
                        enemy1.y = rand2;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        //stop movement of bullet
                        moveX1 = 0;
                    }
                    //if level up booleen is true(time has past a certain point)
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }

                //if enemy is off the screen
                if (enemy2.x + 196 < 0) {

                    //set bullet to far right  
                    if (bosstimer < 16) {
                        //set bullet to far right
                        enemy2.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    //input random y coord
                    enemy2.y = rand;
                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy2.y > enemy1.y - 89 && enemy2.y < enemy1.y + 89 || enemy2.y > enemy3.y - 89 && enemy2.y < enemy3.y + 89 && enemy2.y > enemy4.y - 89 && enemy2.y < enemy4.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        //input random y coord
                        enemy2.y = rand2;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX2 = 0;
                    }
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX2 = randspeed;
                        }
                    }
                }

                //if enemy is off the screen
                if (enemy3.x + 196 < 0) {

                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy3.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    //input random y coord
                    enemy3.y = rand;
                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy3.y > enemy1.y - 89 && enemy3.y < enemy1.y + 89 || enemy3.y > enemy2.y - 89 && enemy3.y < enemy2.y + 89 && enemy3.y > enemy4.y - 89 && enemy3.y < enemy4.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        //input random y coord
                        enemy2.y = rand2;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX3 = randspeed;
                        }
                    }
                }

                //if enemy is off the screen
                if (enemy4.x + 196 < 0) {

                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy4.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    enemy4.y = rand;
                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy4.y > enemy1.y - 89 && enemy4.y < enemy1.y + 89 || enemy4.y > enemy3.y - 89 && enemy4.y < enemy3.y + 89 && enemy4.y > enemy2.y - 89 && enemy4.y < enemy2.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        enemy2.y = rand2;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX4 = randspeed;
                        }
                    }
                }
                //if enemy is under the screen(y = HEIGHT), random the y coordinate
                if (enemy1.y > HEIGHT - 89) {

                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    enemy1.y = rand;
                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy1.y > enemy2.y - 89 && enemy1.y < enemy2.y + 89 || enemy1.y > enemy3.y - 89 && enemy1.y < enemy3.y + 89 && enemy1.y > enemy4.y - 89 && enemy1.y < enemy4.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        enemy1.y = rand2;
                    }
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy1.x = 1275;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }


                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                //if enemy is under the screen(y = HEIGHT), random the y coordinate
                if (enemy2.y > HEIGHT - 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy2.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    enemy2.y = rand;
                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy2.y > enemy1.y - 89 && enemy2.y < enemy1.y + 89 || enemy2.y > enemy3.y - 89 && enemy2.y < enemy3.y + 89 && enemy2.y > enemy4.y - 89 && enemy2.y < enemy4.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        enemy2.y = rand2;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }

                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                if (enemy3.y > HEIGHT - 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy3.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    enemy3.y = rand;
                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy3.y > enemy1.y - 89 && enemy3.y < enemy1.y + 89 || enemy3.y > enemy2.y - 89 && enemy3.y < enemy2.y + 89 && enemy3.y > enemy4.y - 89 && enemy3.y < enemy4.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        enemy2.y = rand2;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                if (enemy4.y > HEIGHT - 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy4.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    enemy4.y = rand;
                    //if the spawned y position overlaps with another bullet, reassign it to another y coordinate
                    if (enemy4.y > enemy1.y - 89 && enemy4.y < enemy1.y + 89 || enemy4.y > enemy3.y - 89 && enemy4.y < enemy3.y + 89 && enemy4.y > enemy2.y - 89 && enemy4.y < enemy2.y + 89) {
                        int rand2 = (int) (Math.random() * 1051);
                        enemy2.y = rand2;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                //if any of bullets intersect, random them again to make them not intersect\
                //if enemy 1 y coord is in range of enemy 2


                if (enemy1.y > enemy2.y - 89 && enemy1.y < enemy2.y + 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy1.x = 1275;
                    }
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    enemy1.y = rand;
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                if (enemy1.y > enemy3.y - 89 && enemy1.y < enemy3.y + 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy1.x = 1275;
                    }
                    int rand = (int) (Math.random() * 1051);
                    enemy1.y = rand;
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                if (enemy1.y > enemy4.y - 89 && enemy1.y < enemy4.y + 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy1.x = 1275;
                    }
                    int rand = (int) (Math.random() * 1051);
                    enemy1.y = rand;
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                if (enemy2.y > enemy3.y - 89 && enemy2.y < enemy3.y + 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy2.x = 1275;
                    }
                    int rand = (int) (Math.random() * 1051);
                    enemy2.y = rand;
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                if (enemy2.y > enemy4.y - 89 && enemy2.y < enemy4.y + 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy2.x = 1275;
                    }
                    //create random number between 1051 and 0, and input it to y coord of enemy
                    int rand = (int) (Math.random() * 1051);
                    enemy2.y = rand;
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                if (enemy3.y > enemy4.y - 89 && enemy3.y < enemy4.y + 89) {
                    //set bullet to far right  
                    if (bosstimer < 16) {
                        enemy3.x = 1275;
                    }
                    int rand = (int) (Math.random() * 1051);
                    enemy3.y = rand;
                    //if boss is on the screen set bullet speed to 0
                    if (bossrec.x < 1275) {
                        moveX1 = 0;
                    }
                    //if it does not generate a 0(no speed), change the speed of enemy
                    if (levelUp == true) {
                        //if it does not generate a 0(no speed), change the speed of enemy to make it go faster
                        if (randspeed != 0) {
                            moveX1 = randspeed;
                        }
                    }
                }
                //player boundaries to move inside of, if player moves out of screen, they are teleported back to the "border"
                if (player.y + player.height > HEIGHT) {

                    player.y = 875;
                }
                if (player.y < 0) {

                    player.y = 10;
                }
                if (player.x < 0) {

                    player.x = 10;
                }
                if (player.x + player.width > WIDTH) {

                    player.x = WIDTH - 100;
                }



                //if wasd key is pressed, move the player to the direction according to the key pressed
                if (playerUp) {
                    player.y = player.y - 15;
                } else if (playerDown) {
                    player.y = player.y + 15;
                } else if (playerRight) {
                    player.x = player.x + 13;
                } else if (playerLeft) {
                    player.x = player.x - 13;
                }
                //if player presses wasd key
                if (playerUp || playerDown || playerRight || playerLeft) {
                    //play sonic ball moving animation
                    frame = (frame + 1) % Sanimation.length;
                } else {
                    //else do not play, with sonic stationary as picture
                    frame = 0;
                }
                //if player intersects bullets/boss
                if (player.intersects(enemy1) || player.intersects(enemy2) || player.intersects(enemy3) || player.intersects(enemy4) || player.intersects(bossrec)) {
                    //end game boolean set as true
                    dead = true;
                    //play dead sound
                    fin.play();
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
                    Thread.sleep(0);
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
        JFrame frame = new JFrame("Running Man Bullet Avoiding Game");

        // creates an instance of my game
        Runner game = new Runner();
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
//grab key code
        int key = e.getKeyCode();
        //figure out what key is pressed
        //is W key bring pressed, set playerUp as true
        if (key == KeyEvent.VK_W) {
            playerUp = true;
        } //is S key bring pressed, set playerDown as true
        else if (key == KeyEvent.VK_S) {
            playerDown = true;
        } //is D key bring pressed, set playerRight as true
        else if (key == KeyEvent.VK_D) {
            playerRight = true;
        } //is A key bring pressed, set playerLeft as true
        else if (key == KeyEvent.VK_A) {
            playerLeft = true;
        }
        //is up arrow key bring pressed, set start as true
        if (key == KeyEvent.VK_UP) {
            start = true;
        }
        //is R key bring pressed, set reset as true
        if (key == KeyEvent.VK_R) {
            reset = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //basically if the same keys arn't being pressed, they are still set as false
        if (key == KeyEvent.VK_W) {
            playerUp = false;
        } else if (key == KeyEvent.VK_S) {
            playerDown = false;
        } else if (key == KeyEvent.VK_D) {
            playerRight = false;
        } else if (key == KeyEvent.VK_A) {
            playerLeft = false;
        }
        if (key == KeyEvent.VK_R) {
            reset = false;
        }
    }
}
