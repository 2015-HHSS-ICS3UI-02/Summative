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
 * @author pawar5658
 */


public class GameAssignment extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    //slimer controls
    int moveX = 1;
    int moveY = 1;
    int speed = 2;
    
    //main player
    BufferedImage slimer = ImageHelper.loadImage("Slimer.png");
    
    //Stars to pick up
    BufferedImage item = ImageHelper.loadImage("star.png");
    
    //slimer directions
    boolean slimerup = false;
    boolean slimerdown = false;
    boolean slimerleft = false;
    boolean slimerright = false;
    
    Rectangle player = new Rectangle(40, 40, 25, 40);
    
    Rectangle star1 = new Rectangle(85, 25, 85, 50);
    Rectangle star2 = new Rectangle(200, 515, 85, 50);
    Rectangle star3 = new Rectangle(700, 40, 85, 50);
    
    boolean drawstar1 = true;
    boolean drawstar2 = true;
    boolean drawstar3 = true;
    
    //number of stars
    int stars = 0;
    
    //game font
    Font gameFont = new Font("Arial", Font.PLAIN, 40);
    
    //game over font
    Font gameover = new Font("Arial", Font.BOLD, 100);

    
    boolean done = false; 
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //walls
        //borders
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 800, 25);
        g.fillRect(0, 0, 25, 800);
        g.fillRect(0, 575, 800, 25);
        g.fillRect(775, 0, 25, 600);
        
        //maze walls
        g.fillRect(75, 0, 25, 200);
        g.fillRect(75, 200, 200, 25);
        g.fillRect(75, 400, 200, 25);
        g.fillRect(75, 400, 25, 100);
        g.fillRect(0, 300, 225, 25);
        g.fillRect(275, 200, 25, 225);
        g.fillRect(165, 500, 25, 100);
        g.fillRect(165, 480, 200, 25);
        g.fillRect(340, 300, 25, 200);
        g.fillRect(300, 200, 100, 25);
        g.fillRect(350, 300, 100, 25);
        g.fillRect(425, 385, 100, 125);
        g.fillRect(425, 400, 25, 100);
        g.fillRect(500, 200, 200, 25);
        g.fillRect(500, 200, 25, 210);
        g.fillRect(600, 300, 25, 300);
        g.fillRect(675, 0, 25, 500);
        
        
        //scoreboard
        g.setColor(Color.WHITE);
        g.fillRect(650, 25, 25, 115);
        g.fillRect(400, 120, 275, 25);
        g.fillRect(150, 120, 250, 25);
        g.fillRect(150, 25, 25, 115);
        
        
        
        
        g.drawImage(slimer, player.x, player.y, player.width, player.height, null);
        if(drawstar1){
            g.drawImage(item, star1.x, star1.y, star1.width, star1.height, null);
        }
        if(drawstar2){
        g.drawImage(item, star2.x, star2.y, star2.width, star2.height, null);
        }
        if(drawstar3){
        g.drawImage(item, star3.x, star3.y, star3.width, star3.height, null);
        }
        
        if(done){
        g.setFont(gameover);
        g.drawString("Game Over", 150, 300);
        player.x = 40;
        player.y = 40;
        }
        
        //draw the number of stars(score)
        g.setFont(gameFont);
        g.drawString("Stars: " + stars, 300, 85);
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
                        
            //make the slimer move up
            if(slimerup){
                player.y = player.y - speed*2;
            } else if(slimerdown){
                player.y = player.y + speed*2;
            } else if(slimerleft){
                player.x = player.x - speed*2;
            } else if(slimerright){
                player.x = player.x + speed*2;
            }
            
            //slimer world collisions
            //slimer border wall collisions
            if(player.y > 530){
                //player.y = 530;
                player.x = 40;
                player.y = 40;
            } else if(player.y < 25){
                //player.y = 25;
                player.x = 40;
                player.y = 40;
            } else if(player.x < 25){
                //player.x = 25;
                player.x = 40;
                player.y = 40;
            } else if(player.x > 750){
                //player.x = 750;
                player.x = 40;
                player.y = 40;
            }
            
            //slimer maze wall collisions
            if(player.x + player.width > 75 && player.x < 100 && player.y + player.height > 0 && player.y < 200){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 75  && player.x < 275 && player.y + player.height > 200  && player.y < 225  ){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 75 && player.x < 275 && player.y + player.height > 400 && player.y < 425){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 75 && player.x < 100 && player.y + player.height > 400 && player.y < 500){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 0  && player.x < 225 && player.y + player.height > 300 && player.y < 325){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 275 && player.x < 300 && player.y + player.height > 200 && player.y < 425){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 165 && player.x < 190 && player.y + player.height > 500 && player.y < 600){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 165 && player.x < 365 && player.y + player.height > 480 && player.y < 505){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 340 && player.x < 365 && player.y + player.height > 300 && player.y < 500){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 300 && player.x < 400 && player.y + player.height > 200 && player.y < 225){
                player.x = 40;
                player.y = 40;
            } else if(player.x + player.width > 350 && player.x < 450 && player.y + player.height > 300 && player.y < 325){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 425 && player.x < 525 && player.y + player.height > 385 && player.y < 510){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 425 && player.x < 450 && player.y + player.height > 400 && player.y < 500){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 500 && player.x < 700 && player.y + player.height > 200 && player.y < 225){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 500 && player.x < 525 && player.y + player.height > 200 && player.y < 410){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 600 && player.x < 625 && player.y + player.height > 300 && player.y < 600){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 675 && player.x < 700 && player.y + player.height > 0 && player.y < 500){
                player.x = 40;
                player.y = 40;
            }
            
            //scoreboard wall collisions
            if(player.x + player.width > 650 && player.x < 675 && player.y + player.height > 25 && player.y < 140){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 400 && player.x < 675 && player.y + player.height > 120 && player.y < 145){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 150 && player.x < 400 && player.y + player.height > 120 && player.y < 145){
                player.x = 40; 
                player.y = 40;
            } else if(player.x + player.width > 150 && player.x < 175 && player.y + player.height > 25 && player.y < 140){
                player.x = 40; 
                player.y = 40;
            }
            
            //picking up the stars
            if(player.intersects(star1) && drawstar1){
                 drawstar1 = false;
                 stars++;
            }
            if(player.intersects(star2) && drawstar2){
                 drawstar2 = false;
                 stars++;
            }
            if(player.intersects(star3) && drawstar3){
                 drawstar3 = false;
                 stars++;
            }
            
            //end game
            if(stars == 3){
                done = true;
                player.x = 40;
                player.y = 40;
            }
            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime > desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime - deltaTime);
                }catch(Exception e){};
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
        GameAssignment game = new GameAssignment();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
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
        //grab the keycode from the event
        int key = e.getKeyCode();
        //figure out what key is pressed
        //is the up arrow being pressed?
        if(key == KeyEvent.VK_UP){
            slimerup = true;
        } else if(key == KeyEvent.VK_DOWN){
            slimerdown = true;
        } else if(key == KeyEvent.VK_LEFT){
            slimerleft = true;
        } else if(key == KeyEvent.VK_RIGHT){
            slimerright = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //grab the keycode from the event
        int key = e.getKeyCode();
        //figure out what key is pressed
         //is the up arrow being pressed?
        if(key == KeyEvent.VK_UP){
            slimerup = false;
        } else if(key == KeyEvent.VK_DOWN){
            slimerdown = false;
        } else if(key == KeyEvent.VK_LEFT){
            slimerleft = false;
        } else if(key == KeyEvent.VK_RIGHT){
            slimerright = false;
        }
    }
}
