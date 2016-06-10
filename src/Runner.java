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


public class Runner extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 1275;
    static final int HEIGHT = 935;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    

    int moveX = 1;
    int moveY = 1;
    int speed = 3;
   
     int score = 0;
     
    Rectangle player = new Rectangle(50,HEIGHT/2 - 93, 59,60);
    Rectangle enemy = new Rectangle(100,HEIGHT/2 - 40, 198,89);
        
        boolean playerUp = false;
        boolean playerDown = false;
        boolean playerRight = false;
        boolean playerLeft = false;
        
        long scoretime = System.currentTimeMillis();
        //however long interval is between score ++
        long scoredelay = 500;
        //image for background image
        BufferedImage Terrain = ImageHelper.loadImage("Terrain.jpg");
        
        BufferedImage Sonic = ImageHelper.loadImage("Sonic Stationary.png");
        
        BufferedImage[] Bullet = new BufferedImage[8];
        BufferedImage Bullet1 = ImageHelper.loadImage("BulletMario.png");
        BufferedImage[] Sanimation = new BufferedImage[4];
        ArrayList<Rectangle>blocks = new ArrayList<Rectangle>();
     
          
         
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        
        // GAME DRAWING GOES HERE 
      
        g.setColor(Color.GRAY);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        
        g.setColor(Color.WHITE);
        
        
        

        
           
        
        //draw background
       g.drawImage(Terrain, 0, 0, this);
       //draw main character
       
       //if character is still, input stationary picture
       if(!playerDown  && playerUp == false && playerRight == false && playerLeft == false){
    
    //draw sonic character
        g.drawImage(Sonic, player.x, player.y, this);
        
        } 
       g.drawImage(Bullet1,enemy.x , enemy.y, this);
        
        
        
       
        //game font
     
        Font gameFont = new Font("Arial", Font.PLAIN, 40);
        //draw scores
       g.setFont(gameFont);
       g.drawString("Score:" + score, WIDTH/2 - 100, 100);

       //load images for sonic movement
         Sanimation[0] = ImageHelper.loadImage("Sonic Ball1.png");   
         Sanimation[1] = ImageHelper.loadImage("Sonic Ball2.png");
         Sanimation[2] = ImageHelper.loadImage("Sonic Ball3.png");
         Sanimation[3] = ImageHelper.loadImage("Sonic Ball4.png");
    //load images for zombie movement
     if(playerDown || playerUp || playerRight || playerLeft){

            for(int i = 0; i < 4; i ++){
           g.drawImage(Sanimation[i], player.x , player.y, this);
              if( System.currentTimeMillis() - scoretime > scoredelay){
               
                scoretime = System.currentTimeMillis();
            }
         
       }
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
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if(!done && System.currentTimeMillis() - scoretime > scoredelay){
                score ++;
                scoretime = System.currentTimeMillis();
            }
            //if player model touches bullet model, on either side
            //if it touches
        if(player.x + 59  == enemy.x && player.y - 60 == enemy.y){
            break;
        }
            if(player.y + player.height > HEIGHT){
                
              player.y  = 825;
            }
            if(player.y < 0){
                
              player.y = 10;
            }
            if(player.x < 0){
                
              player.x = 10;
            }
            if(player.x + player.width > WIDTH){
                
              player.x = WIDTH - 100;
            }
            //make character  move
            if(playerUp){
                player.y = player.y- 15;
            }else if(playerDown){
                player.y = player.y + 15;
            } else if(playerRight){
                player.x = player.x + 13;
            }else if(playerLeft){
                player.x = player.x - 13;
            } 
            
            
           // if(player.x)
//            for (Rectangle block:blocks){
//                if(Bullet1.intersects(block)){
//                    Rectangle
//                }
//            }
           
 
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
                     Thread.sleep(0);
                }catch(Exception e){};
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
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
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
        //is W key bring pressed
        if(key == KeyEvent.VK_W){
            playerUp = true;
        }else if(key == KeyEvent.VK_S){
            playerDown = true;
        }else if(key == KeyEvent.VK_D){
            playerRight = true;
        }else if(key == KeyEvent.VK_A){
            playerLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
  int key = e.getKeyCode();
        //figure out what key is pressed
        //is W key bring pressed
        if(key == KeyEvent.VK_W){
            playerUp = false;
        }else if(key == KeyEvent.VK_S){
            playerDown = false;
        }else if(key == KeyEvent.VK_D){
            playerRight = false;
        }else if(key == KeyEvent.VK_A){
            playerLeft = false;
        }
     //method that activates walking animation
    }
    }
