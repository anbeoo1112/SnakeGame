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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (model.getMoves() == 0) {
            model.resetSnakePosition();
        }

        paintGameboard(g);
        paintScore(g);
        if (model.isGameOver()) {
            paintGameover(g);
        }
        paintSnake(g);
        paintEnemy(g);
    }

    private void paintGameboard(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        titleImage = new ImageIcon("./Image/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);
    }

    private void paintGameover(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 300, 300);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Space to restart", 350, 340);
    }

    private void paintScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: " + model.getScores(), 780, 30);
        g.drawString("Length: " + model.getLengthOfSnake(), 780, 50);
    }

    private void paintSnake(Graphics g) {
        if (model.isRight()) {
            rightMount = new ImageIcon("./Image/rightmouth.png");
            rightMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        } else if (model.isLeft()) {
            leftMount = new ImageIcon("./Image/leftmouth.png");
            leftMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        } else if (model.isUp()) {
            upMount = new ImageIcon("./Image/upmouth.png");
            upMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        } else if (model.isDown()) {
            downMount = new ImageIcon("./Image/downmouth.png");
            downMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]);
        }

        for (int i = 1; i < model.getLengthOfSnake(); i++) {
            snakeImage = new ImageIcon("./Image/snakeimage.png");
            snakeImage.paintIcon(this, g, model.getSnakeXLength()[i], model.getSnakeYLength()[i]);
        }
    }

    private void paintEnemy(Graphics g) {
        enemyImage = new ImageIcon("./Image/enemy.png");
        enemyImage.paintIcon(this, g, model.getEnemyXPos()[model.getXPos()], model.getEnemyYPos()[model.getYPos()]);
    }
}
