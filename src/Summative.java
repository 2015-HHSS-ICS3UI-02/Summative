
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;



/**
 *
 * @author Ajay
 */


public class Summative extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)   /desiredFPS;
    //making all the players and the ball
    
    Rectangle SB = new Rectangle(WIDTH/2, HEIGHT/2, 20, 20);
        int moveX = 3;
        int moveY = 3;
        int speed = 3;    
        
    Rectangle red = new Rectangle (250, HEIGHT/2 - 25, 25, 25);
    Rectangle blue = new Rectangle (550, HEIGHT/2 - 25, 25, 25);
    Rectangle red2 = new Rectangle (250, HEIGHT/2 + 25, 25, 25);
    Rectangle blue2 = new Rectangle (550, HEIGHT/2 + 25, 25, 25);
    Rectangle lg = new Rectangle (27, 255, 12, 100); 
    Rectangle rg = new Rectangle (768, 255, 12, 100);
    //making the movments for the players
    boolean redUP = false;
    boolean redDOWN = false;
    boolean redLEFT = false;
    boolean redRIGHT = false;
    boolean red2UP = false;
    boolean red2DOWN = false;
    boolean red2LEFT = false;
    boolean red2RIGHT = false;
    boolean blueUP = false;
    boolean blueDOWN = false;
    boolean blueLEFT = false;
    boolean blueRIGHT = false;
    boolean blue2UP = false;
    boolean blue2DOWN = false;
    boolean blue2LEFT = false;
    boolean blue2RIGHT = false;
    
    BufferedImage field = ImageHelper.loadImage("field.png");

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.drawImage(field, moveX, moveY, WIDTH, HEIGHT, null);
        g.setColor(Color.white);
        g.fillRect(lg.x, lg.y, lg.width, lg.height);
        g.fillRect(rg.x, rg.y, rg.width, rg.height);
        g.fillRect(SB.x, SB.y, SB.width, SB.height);
        g.setColor(Color.red);
        g.fillRect(red.x, red.y, red.width, red.height);
        g.fillRect(red2.x, red2.y, red2.width, red2.height);
        g.setColor(Color.blue);
        g.fillRect(blue.x, blue.y, blue.width, blue.height);
        g.fillRect(blue2.x, blue2.y, blue2.width, blue2.height);
        
        
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
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if(red.y < 32){
                red.y = 32;
            }
            if(red2.y < 32){
                red2.y = 32;
            }
            if(blue.y < 32){
                blue.y = 32;
            }
            if(blue2.y < 32){
                blue2.y = 32;
              
            }
            if(SB.y < 32) {
                SB.y = SB.y + moveY*speed;
            }  
                
            
            if(red.y > 545){
                red.y = 545;
            }
            if(red2.y > 545){
                red2.y = 545;
            }
            if(blue.y > 545){
                blue.y = 545;
            }
            if(blue2.y > 545){
                blue2.y = 545;
             
            }
                if(SB.y > 545){
                SB.y = SB.y - moveY*speed*speed;
            } 
                
            
            
            if(red.x < 35){
                red.x = 35;
            }
            if(red2.x < 35){
                red2.x = 35;
            }
            if(blue.x < 35){
                blue.x = 35;
            }
            if(blue2.x < 35){
                blue2.x = 35;
            }
            
            if(SB.x < 35){
                SB.x = SB.x + moveX*speed;
            }
            
            if(red.x > 745){
                red.x = 745;
            }
            if(red2.x > 745){
                red2.x = 745;
            }
            if(blue.x > 745){
                blue.x = 745;
            }
            if(blue2.x > 745){
                blue2.x = 745;
            }
            
            if(SB.x > 745){
                SB.x = SB.x - moveX*speed;
            }
            
            if(redUP){
                red.y = red.y - speed*2;
            } else if (redDOWN){
                red.y = red.y + speed*2;
            }
            if(redLEFT){
                red.x = red.x - speed*2;
            } else if (redRIGHT){
               red.x = red.x + speed*2;
            }
            if(red2UP){
                red2.y = red2.y - speed*2;
            } else if (red2DOWN){
                red2.y = red2.y + speed*2;
            }
            if(red2LEFT){
                red2.x = red2.x - speed*2;
            } else if (red2RIGHT){
               red2.x = red2.x + speed*2;
            }
            if(blueUP){
                blue.y = blue.y - speed*2;
            } else if (blueDOWN){
                blue.y = blue.y + speed*2;
            }
            if(blueLEFT){
                blue.x = blue.x - speed*2;
            } else if (blueRIGHT){
               blue.x = blue.x + speed*2;
            }
            if(blue2UP){
                blue2.y = blue2.y - speed*2;
            } else if (blue2DOWN){
                blue2.y = blue2.y + speed*2;
            }
            if(blue2LEFT){
                blue2.x = blue2.x - speed*2;
            } else if (blue2RIGHT){
               blue2.x = blue2.x + speed*2;
            }
            
            if(SB.intersects(red)){
            
                if(SB.y <= red.y - (red.height/2)){
             SB.y = SB.y - moveY*moveY;   
            }
                else if(SB.y >= red.y + (red.height/2)){
              SB.y = SB.y + moveY*moveY;  
            }
            if(SB.x < red.x){
              SB.x = SB.x - moveX*moveX;  
            }
            else if(SB.x > red.x){
              SB.x = SB.x + moveX*moveX;  
            }
            }
            
            if(SB.intersects(red2)){
            
                if(SB.y <= red2.y - (red2.height/2)){
             SB.y = SB.y - moveY*moveY;   
            }
                else if(SB.y >= red2.y + (red2.height/2)){
              SB.y = SB.y + moveY*moveY;  
            }
            if(SB.x < red2.x){
              SB.x = SB.x - moveX*moveX;  
            }
            else if(SB.x > red2.x){
              SB.x = SB.x + moveX*moveX;  
            }
            }
            
            if(SB.intersects(blue)){
            
                if(SB.y <= blue.y - (blue.height/2)){
             SB.y = SB.y - moveY*moveY;   
            }
                else if(SB.y >= blue.y + (blue.height/2)){
              SB.y = SB.y + moveY*moveY;  
            }
            if(SB.x < blue.x){
              SB.x = SB.x - moveX*moveX;  
            }
            else if(SB.x > blue.x){
              SB.x = SB.x + moveX*moveX;  
            }
            }
            
            if(SB.intersects(blue2)){
            
                if(SB.y <= blue2.y - (blue2.height/2)){
             SB.y = SB.y - moveY*moveY;   
            }
                else if(SB.y >= blue2.y + (blue2.height/2)){
              SB.y = SB.y + moveY*moveY;  
            }
            if(SB.x < blue2.x){
              SB.x = SB.x - moveX*moveX;  
            }
            else if(SB.x > blue2.x){
              SB.x = SB.x + moveX*moveX;  
            }
            }
            
            if(SB.intersects(rg)){
                SB.y = HEIGHT/2;
                SB.x = WIDTH/2;
            }
            
            if(SB.intersects(lg)){
                SB.y = HEIGHT/2;
                SB.x = WIDTH/2;
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
        Summative game = new Summative();
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
        
        //make the game listen for keys
        frame.addKeyListener((KeyListener) game);
        
        // starts my game loop
        game.run();
    }
    
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    //get the key code
        int key = e.getKeyCode();
        //if the w key is being pressed
        if (key == KeyEvent.VK_W){
           redUP = true;
        }
           else if (key == KeyEvent.VK_S){
             redDOWN = true;  
           }
            if (key == KeyEvent.VK_A){
           redLEFT = true;
        }
           else if (key == KeyEvent.VK_D){
             redRIGHT = true;  
           }   
        
        if (key == KeyEvent.VK_I){
           red2UP = true;
        }
           else if (key == KeyEvent.VK_K){
             red2DOWN = true;  
           }
            if (key == KeyEvent.VK_J){
           red2LEFT = true;
        }
           else if (key == KeyEvent.VK_L){
             red2RIGHT = true;  
           } 
            
        if (key == KeyEvent.VK_UP){
           blueUP = true;
        }
           else if (key == KeyEvent.VK_DOWN){
             blueDOWN = true;  
           }
            if (key == KeyEvent.VK_LEFT){
           blueLEFT = true;
        }
           else if (key == KeyEvent.VK_RIGHT){
             blueRIGHT = true;  
           }   
        
        if (key == KeyEvent.VK_NUMPAD5){
           blue2UP = true;
        }
           else if (key == KeyEvent.VK_NUMPAD2){
             blue2DOWN = true;  
           }
            if (key == KeyEvent.VK_NUMPAD1){
           blue2LEFT = true;
        }
           else if (key == KeyEvent.VK_NUMPAD3){
             blue2RIGHT = true;  
           }     
    
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_W){
           redUP = false;
        }
           else if (key == KeyEvent.VK_S){
             redDOWN = false;  
           }
            if (key == KeyEvent.VK_A){
           redLEFT = false;
        }
           else if (key == KeyEvent.VK_D){
             redRIGHT = false;  
           }  
        if (key == KeyEvent.VK_I){
           red2UP = false;
        }
           else if (key == KeyEvent.VK_K){
             red2DOWN = false;  
           }
            if (key == KeyEvent.VK_J){
           red2LEFT = false;
        }
           else if (key == KeyEvent.VK_L){
             red2RIGHT = false;
           }
        
             if (key == KeyEvent.VK_UP){
           blueUP = false;
        }
           else if (key == KeyEvent.VK_DOWN){
             blueDOWN = false;  
           }
            if (key == KeyEvent.VK_LEFT){
           blueLEFT = false;
        }
           else if (key == KeyEvent.VK_RIGHT){
             blueRIGHT = false;  
           }  
       
            if (key == KeyEvent.VK_NUMPAD5){
           blue2UP = false;
        }
           else if (key == KeyEvent.VK_NUMPAD2){
             blue2DOWN = false;  
           }
            if (key == KeyEvent.VK_NUMPAD1){
           blue2LEFT = false;
        }
           else if (key == KeyEvent.VK_NUMPAD3){
             blue2RIGHT = false;  
           }    
    }
}
