import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private ImageIcon titleImage;
    private ImageIcon rightMount;
    private ImageIcon leftMount;
    private ImageIcon upMount;
    private ImageIcon downMount;
    private ImageIcon snakeImage;
    private ImageIcon enemyImage;
    private Model model;

    public View(Model model) {
        this.model = model;
        if(model.getMoves()==0){
            model.setSnakeX(0,50);
            model.setSnakeX(1,75);
            model.setSnakeX(2,100);
            model.setSnakeY(0,100);
            model.setSnakeY(0,100);
            model.setSnakeY(0,100);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        titleImage=new ImageIcon("./Image/snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);
        paintGameboard(g);
        paintScore(g);
        paintGameover(g);
        paintSnake(g);
        paintEnemy(g);
        g.dispose();
        if(model.isGameOver()){
            paintGameover(g);
        }
    }

    public void paintGameboard(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);
    }

    public void paintGameover(Graphics g) {
        if (model.isGameOver()) {
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 50));
            g.drawString("GAME OVER", 300, 300);
            g.setFont(new Font("arial", Font.BOLD, 20));
            g.drawString("Space to restart", 350, 340);
        }
    }

    public void paintScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: " + model.getScores(), 780, 30);
        g.drawString("Length: " + model.getLengthOfSnake(), 780, 50);
    }

    public void paintSnake(Graphics g){
        // Draw the snake's mouth
        if (model.isRight()) {
            rightMount = new ImageIcon("./Image/rightmouth.png");
            rightMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        }
        if (model.isLeft()) {
            leftMount = new ImageIcon("./Image/leftmouth.png");
            leftMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        }
        if (model.isUp()) {
            upMount = new ImageIcon("./Image/upmouth.png");
            upMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        }
        if (model.isDown()) {
            downMount = new ImageIcon("./Image/downmouth.png");
            downMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        }

        // Draw the snake's body
        for (int i = 1; i < model.getLengthOfSnake(); i++) {
            snakeImage = new ImageIcon("./Image/snakeimage.png");
            snakeImage.paintIcon(this, g, model.getSnakeXLength()[i], model.getSnakeYLength()[i]);
        }
    }

    public void paintEnemy(Graphics g){
        enemyImage = new ImageIcon("./Image/enemy.png");
        enemyImage.paintIcon(this, g, model.getEnemyXPos()[model.getXPos()], model.getEnemyYPos()[model.getYPos()]);
    }
}
