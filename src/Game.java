
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
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
    Rectangle main = new Rectangle(600, 600, 50, 50);
    Rectangle ghost1 = new Rectangle(250, 200, 50, 50);
    //Food particles
    ArrayList<Rectangle> food = new ArrayList<Rectangle>();
    int speed = 3;
    int score = 0;
    boolean mainUp = false;
    boolean mainDown = false;
    boolean mainLeft = false;
    boolean mainRight = false;
    
    BufferedImage foods = ImageHelper.loadImage("PacmanFOOD.jpg");

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
        g.setColor(Color.CYAN);

//        g.fillOval(325, 15, 25, 25);
//        g.fillOval(425, 15, 25, 25);
//        g.fillOval(525, 15, 25, 25);
//        g.fillOval(625, 15, 25, 25);
//        g.fillOval(725, 15, 25, 25);
//        g.fillOval(825, 15, 25, 25);
//        g.fillOval(925, 15, 25, 25);
//        g.fillOval(125, 165, 25, 25);
//        g.fillOval(225, 165, 25, 25);
//        g.fillOval(325, 165, 25, 25);
//        g.fillOval(425, 165, 25, 25);
//        g.fillOval(525, 165, 25, 25);
//        g.fillOval(625, 165, 25, 25);
//        g.fillOval(725, 165, 25, 25);
//        g.fillOval(825, 165, 25, 25);
//        g.fillOval(925, 165, 25, 25);
        g.setColor(Color.blue);
        g.fillRect(ghost1.x, ghost1.y, ghost1.width, ghost1.height);
        g.drawRect(50, 50, 200, 100);
        g.drawRect(WIDTH - 200, HEIGHT - 200, 100, 100);
        g.drawRect(250, 300, 50, 200);
        g.setColor(Color.red);
        g.drawRect(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200);
        g.drawRect(WIDTH - 200, 50, 100, 100);
        g.drawRect(WIDTH - 350, HEIGHT - 200, 50, 100);
        g.setColor(Color.yellow);
        g.drawRect(50, HEIGHT - 200, 100, 150);
        g.drawRect(250, HEIGHT - 150, 300, 100);
        g.drawRect(350, 50, 300, 100);
        g.drawRect(WIDTH - 325, 300, 50, 200);
        g.setColor(Color.green);
        g.drawRect(50, 300, 100, 200);
        g.drawRect(WIDTH - 200, 300, 100, 200);

        for (Rectangle block : food) {
            g.drawImage(foods, block.x, block.y, this);
        }
        food.add(new Rectangle(325, 15, 25, 25));
        food.add(new Rectangle(425, 15, 25, 25));
        food.add(new Rectangle(525, 15, 25, 25));


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
       
            if (mainDown == true && main.y < HEIGHT - main.height) {
                main.y = main.y + speed;
                if (main.x > 0 && main.x < 250 && main.y < 50) {
                    main.y = 0;
                }
                if (main.x > 300 && main.x < 650 && main.y < 50) {
                    main.y = 0;
                }
                if (main.x > WIDTH - 250 && main.x < WIDTH - 100 && main.y < 50) {
                    main.y = 0;
                }
                if (main.x > 0 && main.x < 150 && main.y < 300 && main.y > 250) {
                    main.y = 250;
                }
                if (main.x > 200 && main.x < 300 && main.y < 300 && main.y > 250) {
                    main.y = 250;
                }
                if (main.x > WIDTH / 2 - 150 && main.x < WIDTH / 2 + 100 && main.y < 300 && main.y > 250) {
                    main.y = 250;
                }
                if (main.x > WIDTH - 375 && main.x < WIDTH - 275 && main.y < 300 && main.y > 250) {
                    main.y = 250;
                }
                if (main.x > WIDTH - 250 && main.x < WIDTH - 100 && main.y < 300 && main.y > 250) {
                    main.y = 250;
                }
                if (main.x > 0 && main.x < 150 && main.y < HEIGHT - 200 && main.y > HEIGHT - 250) {
                    main.y = HEIGHT - 250;
                }
                if (main.x > 200 && main.x < 550 && main.y < HEIGHT - 150 && main.y > HEIGHT - 200) {
                    main.y = HEIGHT - 200;
                }
                if (main.x > WIDTH - 400 && main.x < WIDTH - 300 && main.y < HEIGHT - 200 && main.y > HEIGHT - 250) {
                    main.y = HEIGHT - 250;
                }
                if (main.x > WIDTH - 250 && main.x < WIDTH - 100 && main.y < HEIGHT - 200 && main.y > HEIGHT - 250) {
                    main.y = HEIGHT - 250;
                }
            }
            if (mainUp == true && main.y > 0) {
                main.y = main.y - speed;
                if (main.intersects(50, 50, 200, 100)) {
                    main.y = 150;
                }
                if (main.intersects(350, 50, 300, 100)) {
                    main.y = 150;
                }
                if (main.intersects(WIDTH - 200, 50, 100, 100)) {
                    main.y = 150;
                }
                if (main.intersects(50, 300, 100, 200)) {
                    main.y = 500;
                }
                if (main.intersects(250, 300, 50, 200)) {
                    main.y = 500;
                }
                if (main.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                    main.y = 500;
                }
                if (main.intersects(WIDTH - 325, 300, 50, 200)) {
                    main.y = 500;
                }
                if (main.intersects(WIDTH - 200, 300, 100, 200)) {
                    main.y = 500;
                }
                if (main.intersects(50, HEIGHT - 200, 100, 150)) {
                    main.y = HEIGHT - 50;
                }
                if (main.intersects(250, HEIGHT - 150, 300, 100)) {
                    main.y = HEIGHT - 50;
                }
                if (main.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                    main.y = HEIGHT - 100;
                }
                if (main.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                    main.y = HEIGHT - 100;
                }
            }
            if (mainRight == true && main.x < WIDTH - main.width) {
                main.x = main.x + speed;
                if (main.y > 0 && main.y < 150 && main.x < 50) {
                    main.x = 0;
                }
                if (main.y > 250 && main.y < 500 && main.x < 50) {
                    main.x = 0;
                }
                if (main.y > HEIGHT - 250 && main.y < HEIGHT - 50 && main.x < 50) {
                    main.x = 0;
                }
                if (main.y > 250 && main.y < 500 && main.x > 200 && main.x < 250) {
                    main.x = 200;
                }
                if (main.y > HEIGHT - 200 && main.y < HEIGHT - 50 && main.x > 200 && main.x < 250) {
                    main.x = 200;
                }
                if (main.y > 0 && main.y < 150 && main.x > 300 && main.x < 350) {
                    main.x = 300;
                }
                if (main.y > HEIGHT / 2 - 150 && main.y < HEIGHT / 2 + 100 && main.x > WIDTH / 2 - 150 && main.x < WIDTH / 2 - 100) {
                    main.x = WIDTH / 2 - 150;
                }
                if (main.y > HEIGHT - 250 && main.y < HEIGHT - 100 && main.x > WIDTH - 400 && main.x < WIDTH - 350) {
                    main.x = WIDTH - 400;
                }
                if (main.y > 250 && main.y < 500 && main.x > WIDTH - 375 && main.x < WIDTH - 325) {
                    main.x = WIDTH - 375;
                }
                if (main.y > 0 && main.y < 150 && main.x > WIDTH - 250 && main.x < WIDTH - 200) {
                    main.x = WIDTH - 250;
                }
                if (main.y > 250 && main.y < 500 && main.x > WIDTH - 250 && main.x < WIDTH - 200) {
                    main.x = WIDTH - 250;
                }
                if (main.y > HEIGHT - 250 && main.y < HEIGHT - 100 && main.x > WIDTH - 250 && main.x < WIDTH - 200) {
                    main.x = WIDTH - 250;
                }
            }
            if (mainLeft == true && main.x > 0) {
                main.x = main.x - speed;
                if (main.intersects(50, 50, 200, 100)) {
                    main.x = 250;
                }
                if (main.intersects(350, 50, 300, 100)) {
                    main.x = 650;
                }
                if (main.intersects(WIDTH - 200, 50, 100, 100)) {
                    main.x = WIDTH - 100;
                }
                if (main.intersects(50, 300, 100, 200)) {
                    main.x = 150;
                }
                if (main.intersects(250, 300, 50, 200)) {
                    main.x = 300;
                }
                if (main.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                    main.x = WIDTH / 2 + 100;
                }
                if (main.intersects(WIDTH - 325, 300, 50, 200)) {
                    main.x = WIDTH - 275;
                }
                if (main.intersects(WIDTH - 200, 300, 100, 200)) {
                    main.x = WIDTH - 100;
                }
                if (main.intersects(50, HEIGHT - 200, 100, 150)) {
                    main.x = 150;
                }
                if (main.intersects(250, HEIGHT - 150, 300, 100)) {
                    main.x = 550;
                }
                if (main.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                    main.x = WIDTH - 300;
                }
                if (main.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                    main.x = WIDTH - 100;
                }
            }
            // ghost 1 ai
            if(main.y < ghost1.y){
                ghost1.y = ghost1.y - speed;
                if (ghost1.intersects(50, 50, 200, 100)) {
                    ghost1.x = 250;
                }
                if (ghost1.intersects(350, 50, 300, 100)) {
                    ghost1.x = 650;
                }
                if (ghost1.intersects(WIDTH - 200, 50, 100, 100)) {
                    ghost1.x = WIDTH - 100;
                }
                if (ghost1.intersects(50, 300, 100, 200)) {
                    ghost1.x = 150;
                }
                if (ghost1.intersects(250, 300, 50, 200)) {
                    ghost1.x = 300;
                }
                if (ghost1.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                    ghost1.x = WIDTH / 2 + 100;
                }
                if (ghost1.intersects(WIDTH - 325, 300, 50, 200)) {
                    ghost1.x = WIDTH - 275;
                }
                if (ghost1.intersects(WIDTH - 200, 300, 100, 200)) {
                    ghost1.x = WIDTH - 100;
                }
                if (ghost1.intersects(50, HEIGHT - 200, 100, 150)) {
                    ghost1.x = 150;
                }
                if (ghost1.intersects(250, HEIGHT - 150, 300, 100)) {
                    ghost1.x = 550;
                }
                if (ghost1.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                    ghost1.x = WIDTH - 300;
                }
                if (ghost1.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                    ghost1.x = WIDTH - 100;
                }
            }
            if(main.y > ghost1.y){
                ghost1.y = ghost1.y + speed;
                if (ghost1.y > 0 && ghost1.y < 150 && ghost1.x < 50) {
                    ghost1.x = 0;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x < 50) {
                    ghost1.x = 0;
                }
                if (ghost1.y > HEIGHT - 250 && ghost1.y < HEIGHT - 50 && ghost1.x < 50) {
                    ghost1.x = 0;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x > 200 && ghost1.x < 250) {
                    ghost1.x = 200;
                }
                if (ghost1.y > HEIGHT - 200 && ghost1.y < HEIGHT - 50 && ghost1.x > 200 && ghost1.x < 250) {
                    ghost1.x = 200;
                }
                if (ghost1.y > 0 && ghost1.y < 150 && ghost1.x > 300 && ghost1.x < 350) {
                    ghost1.x = 300;
                }
                if (ghost1.y > HEIGHT / 2 - 150 && ghost1.y < HEIGHT / 2 + 100 && ghost1.x > WIDTH / 2 - 150 && ghost1.x < WIDTH / 2 - 100) {
                    ghost1.x = WIDTH / 2 - 150;
                }
                if (ghost1.y > HEIGHT - 250 && ghost1.y < HEIGHT - 100 && ghost1.x > WIDTH - 400 && ghost1.x < WIDTH - 350) {
                    ghost1.x = WIDTH - 400;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x > WIDTH - 375 && ghost1.x < WIDTH - 325) {
                    ghost1.x = WIDTH - 375;
                }
                if (ghost1.y > 0 && ghost1.y < 150 && ghost1.x > WIDTH - 250 && ghost1.x < WIDTH - 200) {
                    ghost1.x = WIDTH - 250;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x > WIDTH - 250 && ghost1.x < WIDTH - 200) {
                    ghost1.x = WIDTH - 250;
                }
                if (ghost1.y > HEIGHT - 250 && ghost1.y < HEIGHT - 100 && ghost1.x > WIDTH - 250 && ghost1.x < WIDTH - 200) {
                    ghost1.x = WIDTH - 250;
                }
            }
            if(main.x < ghost1.x){
                ghost1.x = ghost1.x - speed/2;
                if (ghost1.intersects(50, 50, 200, 100)) {
                    ghost1.x = 250;
                }
                if (ghost1.intersects(350, 50, 300, 100)) {
                    ghost1.x = 650;
                }
                if (ghost1.intersects(WIDTH - 200, 50, 100, 100)) {
                    ghost1.x = WIDTH - 100;
                }
                if (ghost1.intersects(50, 300, 100, 200)) {
                    ghost1.x = 150;
                }
                if (ghost1.intersects(250, 300, 50, 200)) {
                    ghost1.x = 300;
                }
                if (ghost1.intersects(WIDTH / 2 - 100, HEIGHT / 2 - 100, 200, 200)) {
                    ghost1.x = WIDTH / 2 + 100;
                }
                if (ghost1.intersects(WIDTH - 325, 300, 50, 200)) {
                    ghost1.x = WIDTH - 275;
                }
                if (ghost1.intersects(WIDTH - 200, 300, 100, 200)) {
                    ghost1.x = WIDTH - 100;
                }
                if (ghost1.intersects(50, HEIGHT - 200, 100, 150)) {
                    ghost1.x = 150;
                }
                if (ghost1.intersects(250, HEIGHT - 150, 300, 100)) {
                    ghost1.x = 550;
                }
                if (ghost1.intersects(WIDTH - 350, HEIGHT - 200, 50, 100)) {
                    ghost1.x = WIDTH - 300;
                }
                if (ghost1.intersects(WIDTH - 200, HEIGHT - 200, 100, 100)) {
                    ghost1.x = WIDTH - 100;
                }
            }
            if(main.x > ghost1.x){
                ghost1.x = ghost1.x + speed;
                if (ghost1.y > 0 && ghost1.y < 150 && ghost1.x < 50) {
                    ghost1.x = 0;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x < 50) {
                    ghost1.x = 0;
                }
                if (ghost1.y > HEIGHT - 250 && ghost1.y < HEIGHT - 50 && ghost1.x < 50) {
                    ghost1.x = 0;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x > 200 && ghost1.x < 250) {
                    ghost1.x = 200;
                }
                if (ghost1.y > HEIGHT - 200 && ghost1.y < HEIGHT - 50 && ghost1.x > 200 && ghost1.x < 250) {
                    ghost1.x = 200;
                }
                if (ghost1.y > 0 && ghost1.y < 150 && ghost1.x > 300 && ghost1.x < 350) {
                    ghost1.x = 300;
                }
                if (ghost1.y > HEIGHT / 2 - 150 && ghost1.y < HEIGHT / 2 + 100 && ghost1.x > WIDTH / 2 - 150 && ghost1.x < WIDTH / 2 - 100) {
                    ghost1.x = WIDTH / 2 - 150;
                }
                if (ghost1.y > HEIGHT - 250 && ghost1.y < HEIGHT - 100 && ghost1.x > WIDTH - 400 && ghost1.x < WIDTH - 350) {
                    ghost1.x = WIDTH - 400;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x > WIDTH - 375 && ghost1.x < WIDTH - 325) {
                    ghost1.x = WIDTH - 375;
                }
                if (ghost1.y > 0 && ghost1.y < 150 && ghost1.x > WIDTH - 250 && ghost1.x < WIDTH - 200) {
                    ghost1.x = WIDTH - 250;
                }
                if (ghost1.y > 250 && ghost1.y < 500 && ghost1.x > WIDTH - 250 && ghost1.x < WIDTH - 200) {
                    ghost1.x = WIDTH - 250;
                }
                if (ghost1.y > HEIGHT - 250 && ghost1.y < HEIGHT - 100 && ghost1.x > WIDTH - 250 && ghost1.x < WIDTH - 200) {
                    main.x = WIDTH - 250;
                }
            }
            if(main.intersects(ghost1)){
                done = true;
            }
                 Iterator<Rectangle> collect = food.iterator();
            while (collect.hasNext()) {
                // recognizing the dust
                Rectangle food = collect.next();
                // If player touches dust, collects it and stores it
                if (main.intersects(food)) {
                    collect.remove();
                    //adds to score
                    score++;
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
