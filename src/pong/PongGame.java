package pong;


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
 * @author fostp4040
 */


public class PongGame extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH=800;
    static final int HEIGHT=600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS=60;
    long desiredTime=(1000)/desiredFPS;
    //ball dimensions
    Rectangle ball=new Rectangle(WIDTH/2-10,HEIGHT/2-10,20,20);
    //player1 dimensions
    Rectangle p1Goalie=new Rectangle(50,HEIGHT/2-40,25,80);
    Rectangle p1Defender=new Rectangle(150,HEIGHT/2-40,25,80);
    //player2 dimensions
    Rectangle p2Goalie=new Rectangle(WIDTH-75,HEIGHT/2-40,25,80);
    Rectangle p2Defender=new Rectangle(WIDTH-175,HEIGHT/2-40,25,80);
    boolean p1Up=false;
    boolean p1Down=false;
    boolean p2Up=false;
    boolean p2Down=false;   
    Font gameFont=new Font("Arial", Font.PLAIN,40);
    int score1=0;
    int score2=0; 
    //movement
    int moveX=1;
    int moveY=1;
    int ballSpeed=3;
    int goalieSpeed=3;
    int defenderSpeed=4;
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE
        //making the field
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //making the lines on the field
        g.setColor(Color.WHITE);
        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
        g.drawOval(WIDTH/2-50, HEIGHT/2-50, 100, 100);
        g.fillOval(WIDTH/2-2, HEIGHT/2-2, 4, 4);
        g.fillOval(150, HEIGHT/2, 4, 4);
        g.fillOval(WIDTH-154, HEIGHT/2, 4, 4);
        g.drawRect(0, HEIGHT/2-100, 100, 200);
        g.drawRect(WIDTH-100, HEIGHT/2-100, 100, 200);
        g.drawRect(0, HEIGHT/2-200, 200, 400);
        g.drawRect(WIDTH-200, HEIGHT/2-200, 200, 400);
        //making the ball
        g.fillOval(ball.x, ball.y, ball.width, ball.height);
        //making P1's team
        g.setColor(Color.red);
        g.fillRect(p1Goalie.x, p1Goalie.y, p1Goalie.width, p1Goalie.height);
        g.fillRect(p1Defender.x, p1Defender.y, p1Defender.width, p1Defender.height);
        //making P2's team
        g.setColor(Color.BLUE);
        g.fillRect(p2Goalie.x, p2Goalie.y, p2Goalie.width, p2Goalie.height);
        g.fillRect(p2Defender.x, p2Defender.y, p2Defender.width, p2Defender.height);
        //making the 'scoreboard'
        g.setFont(gameFont);
        g.setColor(Color.white);
        g.drawString("Player 1: "+score1, WIDTH/2-250, 100);
        g.drawString("Player 2: "+score2, WIDTH/2+75, 100);
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
        // game will end if you set done = true;
        boolean done=false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime=System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            //ball movement
            ball.x=ball.x+moveX*ballSpeed;
            ball.y=ball.y+moveY*ballSpeed;
            
            //ball colisions with bounderies
            if(ball.y+ball.height>HEIGHT){
                moveY=-1;
            }
            if(ball.y<0){
                moveY=1;
            }
            if(ball.x+ball.width>WIDTH){
                moveX=-1;
            }
            if(ball.x<0){
                moveX=1;
            }
            //scoring conditions
            if(ball.x+ball.width>WIDTH&&ball.y+ball.height>HEIGHT/2-100&&ball.y+ball.height<HEIGHT/2+100){
                score1++;
            }
            if(ball.x<0&&ball.y+ball.height>HEIGHT/2-100&&ball.y+ball.height<HEIGHT/2+100){
                score2++;
            }
            //ball colisions with players
            if(ball.intersects(p1Goalie)){
                moveX=1;
            }else if(ball.intersects(p2Goalie)){
                moveX=-1;
            }else if(ball.intersects(p1Defender)){
                moveX=1;
            }else if(ball.intersects(p2Defender)){
                moveX=-1;
            }
            //end game conditions
            if(score1==10||score2==10){
                done=true;
            }
            // Player 1 goalie movement
            if(p1Up&&p1Goalie.y>0){
                p1Goalie.y=p1Goalie.y-goalieSpeed;
            }else if(p1Down&&p1Goalie.y+p1Goalie.height<HEIGHT){
                p1Goalie.y=p1Goalie.y+goalieSpeed;
            }
            // Player 1 defender movement
            if(p1Up&&p1Defender.y>0){
                p1Defender.y=p1Defender.y-defenderSpeed;
            }else if(p1Down&&p1Defender.y+p1Defender.height<HEIGHT){
                p1Defender.y=p1Defender.y+defenderSpeed;
            }
            // Player 2 goalie movement
            if(p2Up&&p2Goalie.y>0){
                p2Goalie.y=p2Goalie.y-goalieSpeed;
            }else if(p2Down&&p2Goalie.y+p2Goalie.height<HEIGHT){
                p2Goalie.y=p2Goalie.y+goalieSpeed;
            }
            // Player 2 defender movement
            if(p2Up&&p2Defender.y>0){
                p2Defender.y=p2Defender.y-defenderSpeed;
            }else if(p2Down&&p2Defender.y+p2Defender.height<HEIGHT){
                p2Defender.y=p2Defender.y+defenderSpeed;
            }
            // GAME LOGIC ENDS HERE
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime=System.currentTimeMillis()-startTime;
            if(deltaTime>desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime-deltaTime);
                }catch(Exception e){};
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame=new JFrame("My Game");
       
        // creates an instance of my game
        PongGame game=new PongGame();
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
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_W){
            p1Up=true;
        }else if(key==KeyEvent.VK_S){
            p1Down=true;
        }else if(key==KeyEvent.VK_UP){
            p2Up=true;
        }else if(key==KeyEvent.VK_DOWN){
            p2Down=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_W){
            p1Up=false;
        }else if(key==KeyEvent.VK_S){
            p1Down=false;
        }else if(key==KeyEvent.VK_UP){
            p2Up=false;
        }else if(key==KeyEvent.VK_DOWN){
            p2Down=false;
        }
    }
}