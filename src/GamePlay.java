import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private ImageIcon titleImage;
    private int[] snakeXLength = new int [750];
    private int[] snakeYLength = new int [750];
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    private ImageIcon rightMount;
    private ImageIcon leftMount;
    private ImageIcon upMount;
    private ImageIcon downMount;
    private ImageIcon snakeImage;
    private Timer time;
    private int delay=100;
    private int lengthOfSnake=3;
    private int moves=0;
    private int scores=0;
    private boolean gameOver=false;
    private int[] enemyXPos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
            600,625,650,675,700,725,750,775,800,825,850};

    private int[] enemyYPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
            600,625};

    private ImageIcon enemyImage;
    private Random random =new Random();
    private int xPos=random.nextInt(34);
    private int yPos=random.nextInt(23);
    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        time=new Timer(delay,this);
        time.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        if(moves==0){
            snakeXLength[0]=100;
            snakeXLength[1]=75;
            snakeXLength[2]=50;


            snakeYLength[0]=100;
            snakeYLength[1]=100;
            snakeYLength[2]=100;

        }
        //border of title image
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        titleImage=new ImageIcon("./Image/snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);
        //border of gameplay
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);

        //draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Score: "+scores,780,30);
        g.drawString("Length: "+lengthOfSnake,780,45);

        rightMount=new ImageIcon("./Image/rightmouth.png");
        rightMount.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
        for (int i=0;i<lengthOfSnake;i++){
            if(i==0 && right){
                rightMount=new ImageIcon("./Image/rightmouth.png");
                rightMount.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }if(i==0 && left){
                leftMount=new ImageIcon("./Image/leftmouth.png");
                leftMount.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }if(i==0 && up){
                upMount=new ImageIcon("./Image/upmouth.png");
                upMount.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }if(i==0 && down){
                downMount=new ImageIcon("./Image/downmouth.png");
                downMount.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
            if(i!=0){
                snakeImage = new ImageIcon("./Image/snakeimage.png");
                snakeImage.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
        }
        enemyImage=new ImageIcon("./Image/enemy.png");
        enemyImage.paintIcon(this,g,enemyXPos[xPos],enemyYPos[yPos]);
        if(snakeXLength[0]==enemyXPos[xPos]&& snakeYLength[0]==enemyYPos[yPos]){
            lengthOfSnake++;
            scores++;
            xPos=random.nextInt(34);
            yPos=random.nextInt(23);
        }

        for (int i=1;i<lengthOfSnake;i++){
            if(snakeXLength[i]==snakeXLength[0]&&snakeYLength[i]==snakeYLength[0]){
                gameOver=true;
                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.white);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("GAME OVER",300,300);
                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Space to restart",350,340);
            }
        }
        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (right) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeYLength[i + 1] = snakeYLength[i];
            }
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                if (i == 0) {
                    snakeXLength[i] = snakeXLength[i] + 25;
                } else {
                    snakeXLength[i] = snakeXLength[i - 1];
                }
                if(snakeXLength[i]>850){
                    snakeXLength[i]=25;
                }
            }
            repaint(); // Vẽ lại giao diện sau khi cập nhật tọa độ
        }if (left) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeYLength[i + 1] = snakeYLength[i];
            }
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                if (i == 0) {
                    snakeXLength[i] = snakeXLength[i] - 25;
                } else {
                    snakeXLength[i] = snakeXLength[i - 1];
                }
                if(snakeXLength[i]<25){
                    snakeXLength[i]=850;
                }
            }
            repaint(); // Vẽ lại giao diện sau khi cập nhật tọa độ
        }if (up) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeXLength[i + 1] = snakeXLength[i];
            }
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                if (i == 0) {
                    snakeYLength[i] = snakeYLength[i] - 25;
                } else {
                    snakeYLength[i] = snakeYLength[i - 1];
                }
                if(snakeYLength[i]<75){
                    snakeYLength[i]=625;
                }
            }
            repaint(); // Vẽ lại giao diện sau khi cập nhật tọa độ
        }if (down) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeXLength[i + 1] = snakeXLength[i];
            }
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                if (i == 0) {
                    snakeYLength[i] = snakeYLength[i] +25;
                } else {
                    snakeYLength[i] = snakeYLength[i - 1];
                }
                if(snakeYLength[i]>625){
                    snakeYLength[i]=75;
                }
            }
            repaint(); // Vẽ lại giao diện sau khi cập nhật tọa độ
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            moves++;
            if(!left) right=true;
            else {left=true;right=false;}
            up=false;
            down=false;
        }if(e.getKeyCode()==KeyEvent.VK_LEFT){
            moves++;
            if(!right) left=true;
            else {left=false;right=true;}
            up=false;
            down=false;
        }if(e.getKeyCode()==KeyEvent.VK_UP){
            moves++;
            right=false;
            left=false;
            if(!down)up=true;
            else {down=true;up=false;}
        }if(e.getKeyCode()==KeyEvent.VK_DOWN){
            moves++;
            right=false;
            left=false;
            if(!up)down=true;
            else {up=true;down=false;}

        }
        if(gameOver){
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                lengthOfSnake = 3;
                moves = 0;
                scores = 0;
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
