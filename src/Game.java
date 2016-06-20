/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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
    
    BufferedImage satellite = ImageHelper.loadImage("satellite.png");
    BufferedImage sunflare = ImageHelper.loadImage("sunflare.png");       
    BufferedImage checkpoint = ImageHelper.loadImage("checkpoint.png");
    BufferedImage checkpointvert = ImageHelper.loadImage("checkpointvert.png");  
    BufferedImage space = ImageHelper.loadImage("space.jpg");  
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 120;
    long desiredTime = (1000)/desiredFPS;
    
    String objective = "";                                                             //states the mission objective
    
    double xpos = 0, ypos = 0;                                                          //player x and y positions
    double fuel = 100;                                                                  //player fuel for acceleration
    double xspeed = 0, yspeed = 0;                                                      //player x and y velocities
    double xpos1 = 500, ypos1 = 400;                                                    //object 1 x and y positions
    double mass1 = 0;                                                                   //mass of object 1
    double dist1 = 0, angle1 = 0, gforce1 = 0;                                          //player distance, angle and gforce from object 1
    double xpos2 = -100, ypos2 = -100;                                                    //object 2 x and y positions
    double mass2 = 0;                                                                   //mass of object 2
    double dist2 = 0, angle2 = 0, gforce2 = 0;                                          //player distance, angle and gforce from object 1
    
    Rectangle m_end = new Rectangle(0, 0, 0, 0);                                        //mission end rectangle
                                   
    int[] counts = new int[2];                                                          //creates array for two different counts
    int mission = 0;                                                                    //each int is a mission
    
    boolean won = false;                                                                 //boolean true
    boolean pause = false;                                                              //boolean for game paused
    boolean reset = true;                                                               //boolean to reset game
    
    boolean ynegative_accel = false;
    boolean ypositive_accel = false;
    boolean xnegative_accel = false;
    boolean xpositive_accel = false;
    Font gameFont = new Font("Arial", Font.PLAIN, 14);
    Font bigFont = new Font("Arial", Font.PLAIN, 26);
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        // always clear the screen first!
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // GAME DRAWING GOES HERE
       
            //Basic graphics
        
        g.drawImage(space,-200, 0, WIDTH + 400, HEIGHT,null);
        g.setColor(Color.WHITE);
        g.setFont(gameFont);
        g.drawImage(satellite, (int)xpos - 4,(int)ypos - 4,30,30, null);
        g.drawRect(840, 0, 159, 90);
        g.drawRect(1, 1, 300, 50);
        
            //Displaying information
        
        if(mission > 0){                                                                     //Unless on the title screen
            g.drawString(("Distance 1: " + (int)dist1) + " units",850,20);                      //Displays distance
            g.drawString(("Fuel:" + fuel),850,40);                                              //Displays fuel
            g.drawString(("M.E.T.:" + (int)(counts[0] / 120) + " seconds"),850,60);                 //Displays total time
            g.drawString(("Mission " + mission),10,20);                                         //Displays mission number
            if(mission == 5 || mission == 6 || mission == 7){                                                                   //Displays distance from object two
                g.drawString(("Distance 2: " + (int)dist2) + " units",850,80);
            }
            g.drawString(objective,10,40);                                                      //Displays objective
        }else{                                                                                  //If on title screen
            g.setFont(bigFont);                                                                 //Game title in large font
            g.drawString(("ORBIT"),470,40);  
            g.drawString(("Press enter to begin!"),380,200);  
            g.setFont(gameFont);                                                                //States controls to the player
            g.drawString(("Controls:"),40,680);                                             
            g.drawString(("W-A-S-D - Thrusters"),40,700);
            g.drawString(("SPACE - Pause"),40,740);   
            g.drawString(("R - Reset Mission"),40,720);
            g.drawString(("T - While Paused, Reset to Title"),40,760);
            g.drawString(("<- Ship Control Panel ->"),845,45);
            g.drawString(("< - Mission Number and Objective - >"),30, 30);
        }

            //Object effects

        g.drawImage(sunflare,(int)xpos1 - 100,(int)ypos1 - 100,200,200, null);          //Sunflare graphic for the following missions
        g.drawImage(sunflare,(int)xpos2 - 100,(int)ypos2 - 100,200,200, null);

        if((mission == 1)){                                                                 //Checkpoint graphic for the following missions
            g.drawImage(checkpointvert,m_end.x, m_end.y, m_end.width, m_end.height,null);   
        }
        if((mission == 3 || mission == 7)){
            g.drawImage(checkpoint,m_end.x, m_end.y, m_end.width, m_end.height,null);
        }
        if(won == true){                                                                   
            if(mission == 7){
                g.drawString("You have won the game! Reset to the title screen.",360,780);
            }else{
                g.drawString("Mission complete, reset to continue to next mission.",360,780);
            }
        }
        
            //Pause message
        
        if(pause == true){
            g.setFont(bigFont);                                                              
            g.drawString(("PAUSED"),455,500);                                               //Paused in large font
            g.setFont(gameFont); 
            if(mission > 0){
                g.drawString(("Press T to return to title screen"),410,760);                //explains how to return to title screen
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
            
                //Missions
            
            if(pause == false){                                                 //first check if game is paused
            if(mission == 0){
                if(reset == true){                                              //title screen presets            
                    won = false;                            
                    xpos = 499;                                                                  
                    ypos = 400;                                                              
                    fuel = 100000;                                                               
                    xspeed = 0;                                                                
                    yspeed = 0;                                                                  
                    xpos1 = -100;                                                                
                    ypos1 = -100;                                                                
                    mass1 = 0;                                                                  
                    xpos2 = -100;                                                                
                    ypos2 = -100;                                                                
                    mass2 = 0;
                    m_end = new Rectangle(590, 350, 10, 100);
                    reset = false;                                                                   
                    counts[0] = 0;
                }
            }
            if(mission == 1){
                if(reset == true){                                                             
                    won = false;                                                //mission presets, if mission is loaded or reset      
                    xpos = 300;                                                                  
                    ypos = 400;                                                              
                    fuel = 20;                                                               
                    xspeed = 0;                                                                
                    yspeed = 0.2;                                                                  
                    xpos1 = 500;                                                                
                    ypos1 = 400;                                                                
                    mass1 = 50;                                                                  
                    m_end = new Rectangle(590, 350, 10, 100);
                    reset = false;
                    objective = "Fly the spacecraft through the zone";                                                                    
                    counts[0] = 0;
                }                                                               //Determining if the player has succeeded or failed
                if(ypos <= (m_end.y + m_end.height) && ypos >= (m_end.y) && xpos >= (m_end.x) && xpos <= (m_end.x + m_end.width)){
                    won = true;
                    mission = mission + 1;
                }
                if(dist1 <= 7){
                    reset = true;
                }
            }
            if(mission == 2){
                if(reset == true){                                                             
                    won = false;                                                //mission presets, if mission is loaded or reset      
                    xpos = 460;                                                                  
                    ypos = 400;                                                              
                    fuel = 65;                                                               
                    xspeed = 0;                                                                
                    yspeed = 1.58;                                                                  
                    xpos1 = 500;                                                                
                    ypos1 = 400;                                                                
                    mass1 = 100;                                
                    reset = false;
                    objective = "Achieve a distance of over 500 units";
                    counts[0] = 0;
                    }                                                           //Determining if the player has succeeded or failed
                    if(dist1 >= 500){
                        won = true;
                        mission = mission + 1;
                    }
                    if(dist1 <= 7){
                        reset = true;
                    }
            }
            if(mission == 3){
                if(reset == true){                                              //mission presets, if mission is loaded or reset                     
                    won = false;
                    xpos = 470;                                                                  
                    ypos = 400;                                                              
                    fuel = 30;                                                               
                    xspeed = 0;                                                                
                    yspeed = -2.5;                                                                  
                    xpos1 = 500;                                                                
                    ypos1 = 400;                                                                
                    mass1 = 100;                                                                  
                    m_end = new Rectangle(450, 200, 100, 10);
                    reset = false;
                    objective = "Fly the spacecraft through the zone";
                    counts[0] = 0;
                    }                                                               //Determining if the player has succeeded or failed
                    if(ypos <= (m_end.y + m_end.height) && ypos >= (m_end.y) && xpos >= (m_end.x) && xpos <= (m_end.x + m_end.width)){
                        won = true;
                        mission = mission + 1;
                    }   
                    if(dist1 <= 7){
                        reset = true;
                    }
            }
            if(mission == 4){
                if(reset == true){                                               //mission presets, if mission is loaded or reset                    
                    won = false;
                    xpos = -100;                                                                  
                    ypos = 240;                                                              
                    fuel = 30;                                                               
                    xspeed = 0.75;                                                                
                    yspeed = 0.35;                                                                  
                    xpos1 = 500;                                                                
                    ypos1 = 400;                                                                
                    mass1 = 100;                                                                  
                    reset = false;
                    objective = "Achieve an orbit consistenly below 400 units";
                    counts[0] = 0;
                    }                                                           //Determining if the player has succeeded or failed
                    if(dist1 <= 7){
                        reset = true;
                    }
                    if(dist1 >= 400 && counts[0] >= 300){
                        reset = true;
                    }
                    if(counts[0] >= 4500){
                        won = true;
                        mission = mission + 1;
                    }
            }
            if(mission == 5){
                if(reset == true){                                              //mission presets, if mission is loaded or reset                    
                    won = false;
                    xpos = 500;                                                                  
                    ypos = 660;                                                              
                    fuel = 100;                                                               
                    xspeed = 0.8;                                                                
                    yspeed = 0;                                                                  
                    xpos1 = 500;                                                                
                    ypos1 = 700;                                                                
                    mass1 = 30;     
                    xpos2 = 500;                                                                
                    ypos2 = 100;                                                                
                    mass2 = 30;
                    reset = false;
                    objective = "Achieve an orbit below 100 around Star 2";
                    counts[0] = 0;
                    }                                                           //Determining if the player has succeeded or failed
                if(dist1 <= 7){                                              
                    reset = true;
                }
                if(dist2 <= 7){
                    reset = true;
                }
                if(dist2 <= 100){
                    counts[1] = counts[1] + 1;
                }else{
                    counts[1] = 0;
                }
                if(counts[1] >= 1000){
                    won = true;
                    mission = mission + 1;
                }
            }
                if(mission == 6){
                if(reset == true){                                                             
                    won = false;                                                //mission presets, if mission is loaded or reset      
                    xpos = 0;                                                                  
                    ypos = 50;                                                              
                    fuel = 200;                                                               
                    xspeed = 2;                                                                
                    yspeed = 0;                                                                  
                    xpos1 = 500;                                                                
                    ypos1 = 700;                                                                
                    mass1 = 30;     
                    xpos2 = 500;                                                                
                    ypos2 = 100;                                                                
                    mass2 = 30;
                    reset = false;
                    objective = "Achieve an orbit below 100 around Star 1";
                    counts[0] = 0;
                }                                                               //Determining if the player has succeeded or failed
                if(dist1 <= 7){                                                 
                    reset = true;
                }
                if(dist2 <= 7){
                    reset = true;
                }
                if(dist1 <= 100){
                    counts[1] = counts[1] + 1;
                }else{
                    counts[1] = 0;
                }
                if(counts[1] >= 1000){
                    won = true;
                    mission = mission + 1;
                }
            }
                if(mission == 7){
                if(reset == true){                                                             
                    won = false;                                                //mission presets, if mission is loaded or reset      
                    xpos = 500;                                                                  
                    ypos = 400;                                                              
                    fuel = 78;                                                               
                    xspeed = 0;                                                                
                    yspeed = 0;                                                                  
                    xpos1 = 150;                                                                
                    ypos1 = 400;                                                                
                    mass1 = 300;     
                    xpos2 = 850;                                                                
                    ypos2 = 400;                                                                
                    mass2 = 300;
                    m_end = new Rectangle(450, 150, 100, 10);
                    reset = false;
                    objective = "Fly the spacecraft through the zone";
                    counts[0] = 0;
                }                                                               //Determining if the player has succeeded or failed
                if(dist1 <= 7){                                                 
                    reset = true;
                }
                if(dist2 <= 7){
                    reset = true;
                }
                if(ypos <= (m_end.y + m_end.height) && ypos >= (m_end.y) && xpos >= (m_end.x) && xpos <= (m_end.x + m_end.width)){
                    won = true;
                    mission = 0;
                    reset = true;
                }   
            }
                //Calculating gravitation
            
            dist1 = distance(xpos,xpos1,ypos,ypos1);                                         //Finds distance to object 1
            gforce1 = mass1 / Math.pow(dist1, 2);                                            //Finds gforce of object 1
            angle1 = angle(ypos,ypos1,xpos,xpos1);                                           //finds angle to object 2
            xspeed = xspeed - gforce1 * Math.cos(angle1);                                    //Adds components of gforce onto player
            yspeed = yspeed - gforce1 * Math.sin(angle1);
            
            dist2 = distance(xpos,xpos2,ypos,ypos2);                                         //Finds distance to object 2
            gforce2 = mass2 / Math.pow(dist2, 2);                                            //Finds gforce of object 2
            angle2 = angle(ypos,ypos2,xpos,xpos2);                                           //finds angle to object 2
            xspeed = xspeed - gforce2 * Math.cos(angle2);                                    //Adds components of gforce onto player
            yspeed = yspeed - gforce2 * Math.sin(angle2);
            
                //Player acceleration
            
            if(ynegative_accel == true && fuel > 0){                                         //Accelerates up if key is pressed
                yspeed = yspeed - 0.005;
                fuel = fuel - 0.5;
            }
            if(ypositive_accel == true && fuel > 0){                                         //Accelerates down if key is pressed
                yspeed = yspeed + 0.005;
                fuel = fuel - 0.5;
            }
            if(xnegative_accel == true && fuel > 0){                                         //Accelerates left if key is pressed
                xspeed = xspeed - 0.005;
                fuel = fuel - 0.5;
            }
            if(xpositive_accel == true && fuel > 0){                                         //Accelerates right if key is pressed
                xspeed = xspeed + 0.005;
                fuel = fuel - 0.5;
            }
            
            xpos = xpos + xspeed;                                                              //adding x and y speed to position
            ypos = ypos + yspeed;
            
            counts[0] = counts[0] + 1;                                                     //counts frames for multiple purposes
            
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

    public static double distance(double xposplayer,double xposobject,double yposplayer,double yposobject) {        //distance formula
        double dist = Math.sqrt(Math.pow((xposplayer - xposobject), 2) + Math.pow((yposplayer - yposobject),2));
        return dist;
    }
    public static double angle(double yposplayer,double yposobject,double xposplayer,double xposobject) {           //angle formula
        double angle = Math.atan2((yposplayer - yposobject),(xposplayer - xposobject));                              
        return angle;
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
       } else if(key == KeyEvent.VK_R){
           reset = true;
       } else if(key == KeyEvent.VK_SPACE && pause == false){
           pause = true;
       } else if(key == KeyEvent.VK_SPACE && pause == true){
           pause = false;
       } else if(key == KeyEvent.VK_T && pause == true){
           mission = 0;
           reset = true;
        } else if(key == KeyEvent.VK_ENTER && mission == 0){
           mission = 1;
           reset = true;
        }else if(key == KeyEvent.VK_P){                                         //debug button to automatically win mission
           won = true;
           mission = mission + 1;
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
