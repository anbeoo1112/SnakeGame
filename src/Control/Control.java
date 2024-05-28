package Control;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control extends JPanel implements KeyListener, ActionListener {
    // Các biến model và view để tương tác với dữ liệu và giao diện trò chơi
    private Model model;
    private View view;
    // Bộ đếm thời gian để điều khiển tốc độ trò chơi
    private Timer time;
    // Độ trễ giữa các lần cập nhật trạng thái trò chơi
    private int delay = 100;
    // Biến để kiểm tra xem có thể thay đổi hướng di chuyển hay không
    private boolean canChangeDirection = true;

    // Hàm khởi tạo cho lớp Control.Control, nhận vào đối tượng model và view
    public Control(Model model, View view) {
        this.model = model;
        this.view = view;
        model.setRight(true); // Đặt hướng ban đầu của rắn là sang phải
        addKeyListener(this); // Thêm bộ lắng nghe sự kiện bàn phím
        setFocusable(true); // Đặt khả năng nhận sự kiện bàn phím cho panel
        setFocusTraversalKeysEnabled(true);
        time = new Timer(delay, this); // Khởi tạo bộ đếm thời gian với độ trễ nhất định
        time.start(); // Bắt đầu bộ đếm thời gian
    }

    // Phương thức xử lý sự kiện khi bộ đếm thời gian kích hoạt
    @Override
    public void actionPerformed(ActionEvent e) {
        model.eatEnemy(); // Kiểm tra và xử lý việc rắn ăn kẻ thù
        updateSnakeMovement(); // Cập nhật vị trí di chuyển của rắn
        view.repaint(); // Vẽ lại trạng thái trò chơi sau khi cập nhật tọa độ
        canChangeDirection = true; // Cho phép thay đổi hướng di chuyển sau mỗi lần cập nhật
    }

    // Phương thức cập nhật hướng di chuyển của rắn
    private void updateSnakeMovement() {
        if (model.isRight()) {
            updateSnakeDirection(25, 0); // Di chuyển sang phải
            wrapAroundXAxis(); // Xử lý việc rắn đi qua cạnh phải của màn hình
        } else if (model.isLeft()) {
            updateSnakeDirection(-25, 0); // Di chuyển sang trái
            wrapAroundXAxis(); // Xử lý việc rắn đi qua cạnh trái của màn hình
        } else if (model.isUp()) {
            updateSnakeDirection(0, -25); // Di chuyển lên trên
            wrapAroundYAxis(); // Xử lý việc rắn đi qua cạnh trên của màn hình
        } else if (model.isDown()) {
            updateSnakeDirection(0, 25); // Di chuyển xuống dưới
            wrapAroundYAxis(); // Xử lý việc rắn đi qua cạnh dưới của màn hình
        }
    }

    // Phương thức cập nhật vị trí của từng đoạn thân rắn
    private void updateSnakeDirection(int xIncrement, int yIncrement) {
        for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
            if (i == 0) {
                model.setSnakeXLength(i, model.getSnakeXLength()[i] + xIncrement); // Cập nhật tọa độ X của đầu rắn
                model.setSnakeYLength(i, model.getSnakeYLength()[i] + yIncrement); // Cập nhật tọa độ Y của đầu rắn
            } else {
                model.setSnakeXLength(i, model.getSnakeXLength()[i - 1]); // Cập nhật tọa độ X của thân rắn
                model.setSnakeYLength(i, model.getSnakeYLength()[i - 1]); // Cập nhật tọa độ Y của thân rắn
            }
        }
    }

    // Phương thức xử lý việc rắn đi qua cạnh trái hoặc phải của màn hình
    private void wrapAroundXAxis() {
        if (model.getSnakeXLength()[0] > 850) {
            model.setSnakeXLength(0, 25); // Nếu rắn đi qua cạnh phải, xuất hiện lại từ cạnh trái
        } else if (model.getSnakeXLength()[0] < 25) {
            model.setSnakeXLength(0, 850); // Nếu rắn đi qua cạnh trái, xuất hiện lại từ cạnh phải
        }
    }

    // Phương thức xử lý việc rắn đi qua cạnh trên hoặc dưới của màn hình
    private void wrapAroundYAxis() {
        if (model.getSnakeYLength()[0] > 625) {
            model.setSnakeYLength(0, 75); // Nếu rắn đi qua cạnh dưới, xuất hiện lại từ cạnh trên
        } else if (model.getSnakeYLength()[0] < 75) {
            model.setSnakeYLength(0, 625); // Nếu rắn đi qua cạnh trên, xuất hiện lại từ cạnh dưới
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Xử lý sự kiện phím được gõ tại đây (hiện tại không sử dụng)
    }

    // Phương thức xử lý sự kiện phím được nhấn
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (canChangeDirection) {
            // Xử lý thay đổi hướng di chuyển khi nhấn các phím mũi tên
            if (key == KeyEvent.VK_RIGHT && !model.isLeft()) {
                model.setMoves(model.getMoves() + 1);
                model.setRight(true);
                model.setUp(false);
                model.setDown(false);
            } else if (key == KeyEvent.VK_LEFT && !model.isRight()) {
                model.setMoves(model.getMoves() + 1);
                model.setLeft(true);
                model.setUp(false);
                model.setDown(false);
            } else if (key == KeyEvent.VK_UP && !model.isDown()) {
                model.setMoves(model.getMoves() + 1);
                model.setUp(true);
                model.setRight(false);
                model.setLeft(false);
            } else if (key == KeyEvent.VK_DOWN && !model.isUp()) {
                model.setMoves(model.getMoves() + 1);
                model.setDown(true);
                model.setRight(false);
                model.setLeft(false);
            }

            canChangeDirection = false; // Ngăn chặn việc thay đổi hướng cho đến lần cập nhật tiếp theo
        }

        // Xử lý khởi động lại trò chơi khi nhấn phím cách nếu trò chơi kết thúc
        if (model.isGameOver() && key == KeyEvent.VK_SPACE) {
            model.resetGame();
            view.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Xử lý sự kiện phím được nhả tại đây (hiện tại không sử dụng)
    }
}
