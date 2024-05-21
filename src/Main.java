import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Tạo một đối tượng JFrame để chứa trò chơi
        JFrame f = new JFrame();

        // Đặt tiêu đề cho cửa sổ trò chơi
        f.setTitle("Snake Game");

        // Đặt kích thước và vị trí cho cửa sổ trò chơi
        f.setBounds(10, 10, 905, 700);

        // Không cho phép thay đổi kích thước cửa sổ
        f.setResizable(false);

        // Đặt cửa sổ hiển thị
        f.setVisible(true);

        // Đặt hành động khi đóng cửa sổ là thoát chương trình
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Đặt màu nền cho cửa sổ
        f.setBackground(Color.DARK_GRAY);

        // Tạo đối tượng Model để quản lý dữ liệu trò chơi
        Model model = new Model();

        // Tạo đối tượng View để hiển thị trò chơi
        View view = new View(model);

        // Tạo đối tượng Control để điều khiển trò chơi
        Control control = new Control(model, view);

        // Thêm đối tượng View vào JFrame
        f.add(view);

        // Thêm bộ lắng nghe sự kiện bàn phím vào JFrame
        f.addKeyListener(control);
    }
}
