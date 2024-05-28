package View;

import Model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    // Các biến để lưu trữ hình ảnh của trò chơi
    private ImageIcon titleImage;
    private ImageIcon rightMount;
    private ImageIcon leftMount;
    private ImageIcon upMount;
    private ImageIcon downMount;
    private ImageIcon snakeImage;
    private ImageIcon enemyImage;

    // Biến model để tương tác với dữ liệu trò chơi
    private Model model;

    // Hàm khởi tạo cho lớp View.View, nhận vào đối tượng model
    public View(Model model) {
        this.model = model;
    }

    // Phương thức vẽ lại thành phần giao diện
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Thiết lập vị trí ban đầu của rắn nếu số lần di chuyển là 0
        if (model.getMoves() == 0) {
            model.resetSnakePosition();
        }

        // Vẽ bảng trò chơi, điểm số, trò chơi kết thúc, rắn và kẻ thù
        paintGameboard(g);
        paintScore(g);
        if (model.isGameOver()) {
            paintGameover(g);
        }
        paintSnake(g);
        paintEnemy(g);
    }

    // Phương thức vẽ bảng trò chơi
    private void paintGameboard(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55); // Vẽ khung cho tiêu đề

        titleImage = new ImageIcon("./src\\View\\Image\\snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11); // Vẽ hình ảnh tiêu đề

        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577); // Vẽ khung cho bảng trò chơi
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575); // Đổ màu nền cho bảng trò chơi
    }

    // Phương thức vẽ thông báo trò chơi kết thúc
    private void paintGameover(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 300, 300); // Vẽ thông báo "GAME OVER"
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("High Score: " + model.getHighScore(), 350, 340);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Space to restart", 350, 380); // Vẽ thông báo "Space to restart"
    }

    // Phương thức vẽ điểm số
    private void paintScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: " + model.getScores(), 780, 30); // Vẽ điểm số
        g.drawString("Length: " + model.getLengthOfSnake(), 780, 50); // Vẽ chiều dài của rắn
    }

    // Phương thức vẽ rắn
    private void paintSnake(Graphics g) {
        if (model.isRight()) {
            rightMount = new ImageIcon("./src\\View\\Image\\rightmouth.png");
            rightMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]); // Vẽ đầu rắn khi di chuyển sang phải
        } else if (model.isLeft()) {
            leftMount = new ImageIcon("./src\\View\\Image\\leftmouth.png");
            leftMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]); // Vẽ đầu rắn khi di chuyển sang trái
        } else if (model.isUp()) {
            upMount = new ImageIcon("./src\\View\\Image\\upmouth.png");
            upMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]); // Vẽ đầu rắn khi di chuyển lên trên
        } else if (model.isDown()) {
            downMount = new ImageIcon("./src\\View\\Image\\downmouth.png");
            downMount.paintIcon(this, g, model.getSnakeXLength()[0], model.getSnakeYLength()[0]); // Vẽ đầu rắn khi di chuyển xuống dưới
        }

        // Vẽ thân rắn
        for (int i = 1; i < model.getLengthOfSnake(); i++) {
            snakeImage = new ImageIcon("./src\\View\\Image\\snakeimage.png");
            snakeImage.paintIcon(this, g, model.getSnakeXLength()[i], model.getSnakeYLength()[i]);
        }
    }

    // Phương thức vẽ kẻ thù
    private void paintEnemy(Graphics g) {
        enemyImage = new ImageIcon("./src\\View\\Image\\enemy.png");
        enemyImage.paintIcon(this, g, model.getEnemyXPos()[model.getXPos()], model.getEnemyYPos()[model.getYPos()]);
    }
}
