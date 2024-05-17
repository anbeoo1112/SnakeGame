import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener, ActionListener {
    private Model model;
    private View view;

    public Control(Model model, View view) {
        this.model = model;
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(model.getMoves()>0){
        if (model.isRight()) {
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                model.setSnakeY(i + 1, model.getSnakeYLength()[i]);
            }
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                if (i == 0) {
                    model.setSnakeX(i, model.getSnakeXLength()[i] + 25);
                } else {
                    model.setSnakeX(i, model.getSnakeXLength()[i - 1]);
                }
                if(model.getSnakeXLength()[i] > 850){
                    model.setSnakeX(i, 25);
                }
            }
            view.repaint(); // Redraw the game state after updating the coordinates
        }
        if(model.isLeft()){
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                model.setSnakeY(i + 1, model.getSnakeYLength()[i]);
            }
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                if (i == 0) {
                    model.setSnakeX(i, model.getSnakeXLength()[i] - 25);
                } else {
                    model.setSnakeX(i, model.getSnakeXLength()[i - 1]);
                }
                if(model.getSnakeXLength()[i] < 25){
                    model.setSnakeX(i, 850);
                }
            }
            view.repaint();
        }
        if (model.isUp()) {
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                model.setSnakeX(i + 1, model.getSnakeXLength()[i]);
            }
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                if (i == 0) {
                    model.setSnakeY(i, model.getSnakeYLength()[i] - 25);
                } else {
                    model.setSnakeY(i, model.getSnakeYLength()[i - 1]);
                }
                if(model.getSnakeYLength()[i] < 75){
                    model.setSnakeY(i, 625);
                }
            }
            view.repaint(); // Redraw the game state after updating the coordinates
        }
        if (model.isDown()) {
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                model.setSnakeX(i + 1, model.getSnakeXLength()[i]);
            }
            for (int i = model.getLengthOfSnake() - 1; i >= 0; i--) {
                if (i == 0) {
                    model.setSnakeY(i, model.getSnakeYLength()[i] + 25);
                } else {
                    model.setSnakeY(i, model.getSnakeYLength()[i - 1]);
                }
                if(model.getSnakeYLength()[i] > 625){
                    model.setSnakeY(i, 75);
                }
            }
            view.repaint(); // Redraw the game state after updating the coordinates
        }}
        model.eatEnemy();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed events here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            model.setMoves(model.getMoves() + 1);
            if(!model.isLeft()) {
                model.setRight(true);
            } else {
                model.setLeft(true);
                model.setRight(false);
            }
            model.setUp(false);
            model.setDown(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            model.setMoves(model.getMoves() + 1);
            if(!model.isRight()) {
                model.setLeft(true);
            } else {
                model.setLeft(false);
                model.setRight(true);
            }
            model.setUp(false);
            model.setDown(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            model.setMoves(model.getMoves() + 1);
            model.setRight(false);
            model.setLeft(false);
            if(!model.isDown()){
                model.setUp(true);
            } else {
                model.setDown(true);
                model.setUp(false);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            model.setMoves(model.getMoves() + 1);
            model.setRight(false);
            model.setLeft(false);
            if(!model.isUp()){
                model.setDown(true);
            } else {
                model.setUp(true);
                model.setDown(false);
            }
        }
        if(model.isGameOver()){
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                model.setLengthOfSnake(3);
                model.setMoves(0);
                model.setScores(0);
                view.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key released events here
    }
}
