/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author pintm1551
 */


public class Game extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 800;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 144;
    long desiredTime = (1000)/desiredFPS;
    
    double xpos = 0;                                                                    //player x position
    double ypos = 0;                                                                    //player y position
    double xspeed = 0;                                                                  //player x velocity
    double yspeed = 0;                                                                  //player y velocity
    double xpos1 = 500;                                                                 //object 1 x position
    double ypos1 = 400;                                                                 //object 1 y position
    double mass1 = 1000;                                                                   //mass of object 1
    double dist1 = 0;                                                                   //player distance from object 1
    double angle1 = 0;                                                                  //player angle from object 1
    double gforce1 = 0;                                                                 //player gforce from object 1
    
    
    
    int count = 0;
    
    boolean ynegative_accel = false;
    boolean ypositive_accel = false;
    boolean xnegative_accel = false;
    boolean xpositive_accel = false;
    Font gameFont = new Font("Arial", Font.PLAIN, 14);
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setFont(gameFont);
        g.fillRect((int)xpos, (int)ypos, 2, 2);
        g.fillOval(495, 395, 10, 10);
        g.drawString(("Distance:" + dist1),760,20);
        g.drawString(("Angle:" + angle1),760,40);
        g.drawString(("Gravitation:" + gforce1),760,60);
        
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
            
            Font gameFont = new Font("Arial", Font.PLAIN, 40);                              //Sets font          
            
            dist1 = Math.sqrt(Math.pow((xpos - xpos1), 2) + Math.pow((ypos - ypos1),2));    //Finds distance to object 1
            
            gforce1 = mass1 / Math.pow(dist1, 2);                                              //Finds gforce of object 1
            
            angle1 = Math.atan2((ypos - ypos1),(xpos - xpos1));                             //finds angle to object 1
            
            xspeed = xspeed - gforce1 * Math.cos(angle1);                                   //Adds components of gforce onto player
            yspeed = yspeed - gforce1 * Math.sin(angle1);
            
            if(ynegative_accel == true){                                                    //Engages acceleration based on player keystrokes
                yspeed = yspeed - 0.01;
            }
            if(ypositive_accel == true){
                yspeed = yspeed + 0.01;
            }
            if(xnegative_accel == true){
                xspeed = xspeed - 0.01;
            }
            if(xpositive_accel == true){
                xspeed = xspeed + 0.01;
            }
            
            xpos = xpos + xspeed;
            ypos = ypos + yspeed;
            
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
       if(key == KeyEvent.VK_W){
           ynegative_accel = true;
       } else if(key == KeyEvent.VK_S){
           ypositive_accel = true;
       } else if(key == KeyEvent.VK_A){
           xnegative_accel = true;
       } else if(key == KeyEvent.VK_D){
           xpositive_accel = true;
       }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
       int key = e.getKeyCode();
       if(key == KeyEvent.VK_W){
           ynegative_accel = false;
       } else if(key == KeyEvent.VK_S){
           ypositive_accel = false;
       } else if(key == KeyEvent.VK_A){
           xnegative_accel = false;
       } else if(key == KeyEvent.VK_D){
           xpositive_accel = false;
       }
    }
}
