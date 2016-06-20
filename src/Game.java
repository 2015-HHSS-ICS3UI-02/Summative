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
    static final int WIDTH = 1000;      // width of the game when running 
    static final int HEIGHT = 640;      // height of the game when running
    
    BufferedImage west = ImageHelper.loadImage("WildWest2.0.jpg");      // image of wild west 
    BufferedImage character = ImageHelper.loadImage("FlyingGuy.png");       // character's png
    BufferedImage title = ImageHelper.loadImage("TitleScreen2.jpg");        // title screen picture
    BufferedImage spikes = ImageHelper.loadImage("spikes.png");         // obstacles in the game
    BufferedImage gameover = ImageHelper.loadImage("GameOver2.jpg");        // game over screen
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;       
    long desiredTime = (1000)/desiredFPS;
    
    int moveX = 1;      // moving on x axis 
    int moveY = 1;      // moving on y axis
    int dy = 0;         // integer for gravity 
    
    int score = 0;      // score int is initially set to be 0 
    int speed = 3;      // the speed of character
    int speedX = 3;     // the speed on the x axis
    int speedY = 3;     // the speed on the y axis
    int count = 0;      // the integer to make the background scroll
    int count2 = 1000;  // the integer for the first set of spikes -- spawns in at 1000
    int count3 = 1500;  // the integer for the second set of spikes -- spawns in at 1500
    int randNum = (int) (Math.random() * (300 - 1 + 20) + 1);   // integer for the position on the y axis the spikes will spawn in (between 300 and 20)
    int randNum2 = (int) (Math.random() * (200 - 1 + 15) + 1);  // integer for the position on the y axis the spikes will spawn in (between 200 and 15)
    
    boolean pLEFT = false;      // boolean for moving to the left 
    boolean pRIGHT = false;     // boolean for moving to the right 
    boolean pJUMP = false;      // boolean for going up
    boolean reset = false;      // boolean for resetting the game
    int enterKey = 0;           // boolean for pressing enter on main menu
    
    int gravity = 1;            // integer for the gravity in the game 
    
    Rectangle player = new Rectangle(25, 1, 140, 140);      // the players hitbox 
    Rectangle spikes1 = new Rectangle(500, randNum, 250, 100);      // the first set of spikes hitbox 
    Rectangle spikes2 = new Rectangle(500, randNum2, 250, 100);     // the second set of spikes hitbox
    
    Font gameFont = new Font("Agency FB", Font.PLAIN, 40);      // the font the score will appear in
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        
        
        if(enterKey == 0){      // when the integer enterKey is 0, the game will be on the title screen
            g.drawImage(title, 0, 0, WIDTH, HEIGHT, null);
        }
        if(enterKey == 1){      // when the integer enterKey is 1, the game will be playable
        
        g.drawImage(west, count, 0, WIDTH, HEIGHT, null);       // drawing for the background of wild west 
        g.drawImage(west, count + 1000, 0, WIDTH, HEIGHT, null);        // drawing for the second west that will alternate between the original as the screen scrolls
        g.drawImage(character, player.x, player.y, player.width, player.height, null);      // drawing for the character in the game 
        g.drawImage(spikes, spikes1.x, spikes1.y, 500, 200, null);      // drawing for the first set of spikes
        g.drawImage(spikes, spikes2.x, spikes2.y, 500, 200, null);      // drawing for the second set of spikes
        }    
        
        g.setFont(gameFont);        // the game font 
        g.drawString("Score: " + score, 850, 40);       // the score will appear in top right and will say Score: + whatever the actual score is 
        
        if(enterKey == 2){      // when the integer enterKey is equal to 2, the game will display the game over screen
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
            // boundries
            if(player.y + player.height > HEIGHT){
                enterKey = 2;
            }
            // make background move
            if(count < -1000){
                count = 0;
            }      
            // make the first set of spikes move along with the screen
            if(count2 < -1000){
                count2 = WIDTH;
                randNum = (int) (Math.random() * (300 - 1 + 20) + 1);
                spikes1.y = randNum;
                score++;
            }
            // make the second set of spikes move along with the screen
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
            // gravity code, what goes up comes back down
            dy = dy + gravity;
            player.y = player.y + dy;

            // when player hits the floor
            if(player.y > 485){
                player.y = 485;
                dy = 0;
            }
            // boundries
            if(player.y < HEIGHT){
                player.y = player.y + speedY;
            }
            
            // speed of which the screen and spikes scroll 
            count = count - 4;
            count2 = count2 - 10;
            count3 = count3 - 6;
            
            // the spikes x positions are equal to the count integers
            spikes1.x = count2;
            spikes2.x = count3;
            
            // if the player intersects with the first set of spikes, the game over screen will be displayed
            if(player.intersects(spikes1)){
                enterKey = 2;
            }
            // if the player intersects with the second set of spikes, the game over screen will be displayed
            if(player.intersects(spikes2)){
                enterKey = 2;
            }
           // if the reset button is pressed, everything will be set back to 0
            if(reset == true){
                int moveX = 1;      // moving on x axis 
                int moveY = 1;      // moving on y axis
                int dy = 0;         // for gravity 
    
                score = 0;          // score is set back to 0
                speed = 3;          // speed is set back to 3
                speedX = 3;         // the speed on x axis is set back to 3
                speedY = 3;         // the speed on y axis is set back to 3
                count = 0;          // the int count is set back to 0
                count2 = 1000;      // the int count2 is set back to 0
                count3 = 1500;      // the int count3 is set back to 0
                randNum = (int) (Math.random() * (300 - 1 + 20) + 1);       // the random y positions are back to the original positions
                randNum2 = (int) (Math.random() * (200 - 1 + 15) + 1);      // the random y positions are back to the original positions
                pLEFT = false;      // the left is false 
                pRIGHT = false;     // the right is false
                pJUMP = false;      // the up is false
                enterKey = 0;       // the enter key is 0
                gravity = 1;        // the gravity is back to 1
                player = new Rectangle(25, 1, 140, 140);        // the rectangles are back to the original spots 
                spikes1 = new Rectangle(500, randNum, 250, 100);    // the rectangles are back to the original spots       
                spikes2 = new Rectangle(500, randNum2, 250, 100);   // the rectangles are back to the original spots 
                Font gameFont = new Font("Agency FB", Font.PLAIN, 40);  // the font is reset 
                reset = false;      // reset is equal to false 
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
        if (key == KeyEvent.VK_A) { // when the key a is pressed, the player will move left 
            pLEFT = true;
        } else if (key == KeyEvent.VK_D) {
            pRIGHT = true;          // when the key d is pressed, the player will move right
        } else if (key == KeyEvent.VK_W){
            pJUMP = true;           // when the key w is pressed, the player will move up
        } else if (key == KeyEvent.VK_ENTER){
            enterKey = 1;           // when the key Enter is pressed, the game will start
        } else if (key == KeyEvent.VK_R){
            reset = true;           // when the key r is pressed, the game will reset 
        }  
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();   
        if (key == KeyEvent.VK_A) {// when the key a isn't pressed, the player wont move left  
            pLEFT = false;
        } else if (key == KeyEvent.VK_D) {
            pRIGHT = false;         // when the key d isn't pressed, the player wont move right
        } else if (key == KeyEvent.VK_W){
            pJUMP = false;          // when the key w isn't pressed, the player wont move up
        }
    }
}