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
 * @author farrb0382
 */


public class Game extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 640;
    
    BufferedImage west = ImageHelper.loadImage("WildWest2.0.jpg");
    BufferedImage west2 = ImageHelper.loadImage("WildWest3.jpg");
    BufferedImage character = ImageHelper.loadImage("FlyingGuy.png");
    BufferedImage title = ImageHelper.loadImage("TitleScreen2.jpg");   
    BufferedImage spikes = ImageHelper.loadImage("spikes.png");   
    BufferedImage gameover = ImageHelper.loadImage("GameOver2.jpg");
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    int moveX = 1;      // moving on x axis 
    int moveY = 1;      // moving on y axis
    int dy = 0;
    
    int score = 0;
    int speed = 3;
    int speedX = 3;     // the speed 
    int speedY = 3;
    int count = 0;
    int count2 = 1000;
    int count3 = 1500;
    int bulletSpeed;
    int bulletSpeed2;
    int randNum = (int) (Math.random() * (300 - 1 + 20) + 1);
    int randNum2 = (int) (Math.random() * (200 - 1 + 15) + 1);
    
    boolean pLEFT = false;
    boolean pRIGHT = false;
    boolean pJUMP = false;
    boolean reset = false;
    int enterKey = 0;
    
    int gravity = 1;
    
    Rectangle player = new Rectangle(25, 1, 140, 140);
    Rectangle spikes1 = new Rectangle(500, randNum, 250, 100);
    Rectangle spikes2 = new Rectangle(500, randNum2, 250, 100);
    
    Font gameFont = new Font("Agency FB", Font.PLAIN, 40);
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        
        
        if(enterKey == 0){
            g.drawImage(title, 0, 0, WIDTH, HEIGHT, null);
        }
        if(enterKey == 1){
        
        g.drawImage(west, count, 0, WIDTH, HEIGHT, null);
        g.drawImage(west, count + 1000, 0, WIDTH, HEIGHT, null);
        g.drawImage(character, player.x, player.y, player.width, player.height, null);
        g.drawImage(spikes, spikes1.x, spikes1.y, 500, 200, null);
        g.drawImage(spikes, spikes2.x, spikes2.y, 500, 200, null);
        }    
        
        g.setFont(gameFont);
        g.drawString("Score: " + score, 850, 40);
        
        if(enterKey == 2){
            g.drawImage(gameover, 0, 0, WIDTH, HEIGHT, null);    
        }
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
        boolean done = false; 
        while(!done){
        
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            if(enterKey == 1){      // if the title screen is pressed run the game
            
            // boundries
            if(player.x + player.width > WIDTH){
               player.x = WIDTH - player.width;
            }
            // boundries
            if(player.x < 0){
               player.x = 0;
            }
            // boundries
            if(player.y < 0){
                player.y = 0;
            }
            
            if(player.y + player.height > HEIGHT){
                enterKey = 2;
            }
            // make background move
            if(count < -1000){
                count = 0;
            }      
            
            if(count2 < -1000){
                count2 = WIDTH;
                randNum = (int) (Math.random() * (300 - 1 + 20) + 1);
                spikes1.y = randNum;
                score++;
            }
            
            if(count3 < -1500){
                count3 = WIDTH;
                randNum2 = (int) (Math.random() * (150 - 1 + 10) + 1);
                spikes2.y = randNum2;
                score++;
            }
            // make character move
            if (pLEFT){
                player.x = player.x - speedX;
            }if (pRIGHT){
                player.x = player.x + speedX; 
            }if (pJUMP){
                dy = -10;
            } 

            dy = dy + gravity;
            player.y = player.y + dy;

            // hits the floor
            if(player.y > 485){
                player.y = 485;
                dy = 0;
            }

            if(player.y < HEIGHT){
                player.y = player.y + speedY;
            }
            
            
            // speed of which screen scrolls
            count = count - 4;
            count2 = count2 - 10;
            count3 = count3 - 6;
            
            spikes1.x = count2;
            spikes2.x = count3;
            
            if(player.intersects(spikes1)){
                enterKey = 2;
            }
            
            if(player.intersects(spikes2)){
                enterKey = 2;
            }
           
            if(reset == true){
                int moveX = 1;      // moving on x axis 
                int moveY = 1;      // moving on y axis
                int dy = 0;
    
                score = 0;
                speed = 3;
                speedX = 3;     // the speed 
                speedY = 3;
                count = 0;
                count2 = 1000;
                count3 = 1500;
                randNum = (int) (Math.random() * (300 - 1 + 20) + 1);
                randNum2 = (int) (Math.random() * (200 - 1 + 15) + 1);
                pLEFT = false;
                pRIGHT = false;
                pJUMP = false;
                enterKey = 0;
                gravity = 1;
                player = new Rectangle(25, 1, 140, 140);
                spikes1 = new Rectangle(500, randNum, 250, 100);
                spikes2 = new Rectangle(500, randNum2, 250, 100);
                Font gameFont = new Font("Agency FB", Font.PLAIN, 40);
                reset = false;
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
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            pLEFT = true;
        } else if (key == KeyEvent.VK_D) {
            pRIGHT = true; 
        } else if (key == KeyEvent.VK_W){
            pJUMP = true;
        } else if (key == KeyEvent.VK_ENTER){
            enterKey = 1;
        } else if (key == KeyEvent.VK_R){
            reset = true;
        }  
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();   
        if (key == KeyEvent.VK_A) {
            pLEFT = false;
        } else if (key == KeyEvent.VK_D) {
            pRIGHT = false; 
        } else if (key == KeyEvent.VK_W){
            pJUMP = false;
        }
    }
}