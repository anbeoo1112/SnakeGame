import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control extends JPanel implements KeyListener, ActionListener {
    private Model model;
    private View view;
    private Timer time;
    private int delay = 100;
    private boolean canChangeDirection = true;

    public Control(Model model, View view) {
        this.model = model;
        this.view = view;
        model.setRight(true);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        time = new Timer(delay, this);
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.eatEnemy();
        updateSnakeMovement();
        view.repaint(); // Redraw the game state after updating the coordinates
        canChangeDirection = true; // Allow direction change after each tick
    }

    private void updateSnakeMovement() {
        if (model.isRight()) {
            updateSnakeDirection(25, 0);
            wrapAroundXAxis();
        } else if (model.isLeft()) {
            updateSnakeDirection(-25, 0);
            wrapAroundXAxis();
        } else if (model.isUp()) {
            updateSnakeDirection(0, -25);
            wrapAroundYAxis();
        } else if (model.isDown()) {
            updateSnakeDirection(0, 25);
            wrapAroundYAxis();
        }
    }

    private void updateSnakeDirection(int xIncrement, int yIncrement) {
        for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
            if (i == 0) {
                model.setSnakeX(i, model.getSnakeXLength()[i] + xIncrement);
                model.setSnakeY(i, model.getSnakeYLength()[i] + yIncrement);
            } else {
                model.setSnakeX(i, model.getSnakeXLength()[i - 1]);
                model.setSnakeY(i, model.getSnakeYLength()[i - 1]);
            }
        }
    }

    private void wrapAroundXAxis() {
        if (model.getSnakeXLength()[0] > 850) {
            model.setSnakeX(0, 25);
        } else if (model.getSnakeXLength()[0] < 25) {
            model.setSnakeX(0, 850);
        }
    }

    private void wrapAroundYAxis() {
        if (model.getSnakeYLength()[0] > 625) {
            model.setSnakeY(0, 75);
        } else if (model.getSnakeYLength()[0] < 75) {
            model.setSnakeY(0, 625);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed events here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (canChangeDirection) {
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

            canChangeDirection = false; // Prevent direction change until next tick
        }

        if (model.isGameOver() && key == KeyEvent.VK_SPACE) {
            model.resetGame();
            view.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key released events here
    }
}
