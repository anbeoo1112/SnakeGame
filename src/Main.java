import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Snake Game");
        f.setBounds(10,10,905,700);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(Color.DARK_GRAY);

        Model model = new Model();
        View view = new View(model);
        Control control = new Control(model, view);

        f.add(view);
        f.addKeyListener(control);

        // Thêm ActionListener vào JFrame để xử lý các sự kiện thời gian
        Timer timer = new Timer(model.getDelay(), control);
        timer.start();
    }
}
