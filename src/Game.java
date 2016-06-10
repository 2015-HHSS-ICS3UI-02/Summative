
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author guanv6321
 */
public class Game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 800;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    Rectangle main = new Rectangle(0, 0, 50, 50);
    int moveX = 1;
    int moveY = 1;
    int speed = 5;
    boolean mainUp = false;
    boolean mainDown = false;
    boolean mainLeft = false;
    boolean mainRight = false;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.white);
        g.fillRect(main.x, main.y, main.width, main.height);
        g.fillOval(125, 15, 25, 25);
        g.fillOval(175, 15, 25, 25);
        g.fillOval(225, 15, 25, 25);
        g.fillOval(275, 15, 25, 25);
        g.fillOval(325, 15, 25, 25);
        g.fillOval(375, 15, 25, 25);
        g.fillOval(425, 15, 25, 25);
        g.fillOval(475, 15, 25, 25);
        g.fillOval(525, 15, 25, 25);
        g.fillOval(575, 15, 25, 25);
        g.fillOval(625, 15, 25, 25);
        g.fillOval(675, 15, 25, 25);
        g.setColor(Color.blue);
        g.drawRect(50, 50, 200, 100);
        g.drawRect(WIDTH - 200, HEIGHT - 200, 100, 100);
        g.setColor(Color.red);
        g.drawRect(WIDTH/2-100, HEIGHT/2-100, 200, 200);
        g.drawRect(WIDTH-200, 50, 100, 100);
        g.setColor(Color.yellow);
        g.drawRect(50, HEIGHT-100, 100, 50);
        g.drawRect(250, HEIGHT-150, 300, 100);
        g.drawRect(350, 50, 300, 100);
        g.setColor(Color.green);
        g.drawRect(50, 300, 100, 200);
        g.drawRect(WIDTH-200, 300, 100, 200);
        
        // GAME DRAWING ENDS HERE
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
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if (mainDown == true && main.y < HEIGHT-main.height) {
                main.y++;               
            }
            if (mainUp == true && main.y > 0) {
                main.y--;
            }
            if (mainRight == true && main.x < WIDTH-main.width) {
                main.x++;
            }
            if (mainLeft == true && main.x > 0) {
                main.x--;
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
        Game game = new Game();
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
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // grab the keycode from the event
        int key = e.getKeyCode();
        // figure out what key is pressed
        if (key == KeyEvent.VK_UP) {
            mainUp = true;
        } else if (key == KeyEvent.VK_DOWN) {
            mainDown = true;
        } else if (key == KeyEvent.VK_LEFT) {
            mainLeft = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            mainRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // grab the keycode from the event
        int key = e.getKeyCode();
        // figure out what key is pressed
        if (key == KeyEvent.VK_UP) {
            mainUp = false;
        } else if (key == KeyEvent.VK_DOWN) {
            mainDown = false;
        } else if (key == KeyEvent.VK_LEFT) {
            mainLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            mainRight = false;
        }
    }
}
