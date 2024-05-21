import java.util.Random;

public class Model {
    // Mảng để lưu trữ tọa độ X của các đoạn thân rắn
    private int[] snakeXLength = new int[750];

    // Mảng để lưu trữ tọa độ Y của các đoạn thân rắn
    private int[] snakeYLength = new int[750];

    // Các biến boolean để theo dõi hướng di chuyển của rắn
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    // Chiều dài ban đầu của rắn
    private int lengthOfSnake = 3;

    // Biến đếm số lần di chuyển
    private int moves = 0;

    // Biến lưu điểm số
    private int scores = 0;

    // Biến boolean để kiểm tra trò chơi kết thúc hay chưa
    private boolean gameOver = false;

    // Các mảng lưu trữ các tọa độ X và Y có thể của kẻ thù
    private int[] enemyXPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575,
            600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyYPos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575,
            600, 625};

    // Bộ tạo số ngẫu nhiên để đặt kẻ thù ở các vị trí ngẫu nhiên
    private Random random = new Random();

    // Các vị trí được chọn ngẫu nhiên cho kẻ thù
    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);

    // Hàm khởi tạo cho lớp Model
    public Model() {}

    // Phương thức getter và setter cho tọa độ X của rắn
    public int[] getSnakeXLength() {
        return snakeXLength;
    }

    public void setSnakeXLength(int i, int x) {
        this.snakeXLength[i] = x;
    }

    // Phương thức getter và setter cho tọa độ Y của rắn
    public int[] getSnakeYLength() {
        return snakeYLength;
    }

    public void setSnakeYLength(int i, int y) {
        this.snakeYLength[i] = y;
    }

    // Phương thức getter và setter cho các biến hướng di chuyển
    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    // Phương thức getter và setter cho chiều dài của rắn
    public int getLengthOfSnake() {
        return lengthOfSnake;
    }

    public void setLengthOfSnake(int lengthOfSnake) {
        this.lengthOfSnake = lengthOfSnake;
    }

    // Phương thức getter và setter cho số lần di chuyển
    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    // Phương thức getter và setter cho điểm số
    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    // Phương thức kiểm tra trò chơi kết thúc
    public boolean isGameOver() {
        for (int i = 1; i < lengthOfSnake; i++) {
            if (snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0]) {
                gameOver = true;
                right = false;
                left = false;
                up = false;
                down = false;
            }
        }
        return gameOver;
    }

    // Phương thức setter cho biến gameOver
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    // Phương thức getter cho các tọa độ X của kẻ thù
    public int[] getEnemyXPos() {
        return enemyXPos;
    }

    // Phương thức getter cho các tọa độ Y của kẻ thù
    public int[] getEnemyYPos() {
        return enemyYPos;
    }

    // Phương thức getter và setter cho tọa độ X ngẫu nhiên của kẻ thù
    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    // Phương thức getter và setter cho tọa độ Y ngẫu nhiên của kẻ thù
    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    // Phương thức thiết lập lại trò chơi
    public void resetGame() {
        lengthOfSnake = 3;
        moves = 0;
        scores = 0;
        gameOver = false;
        right = true;
        left = false;
        up = false;
        down = false;
        resetSnakePosition();
    }

    // Phương thức thiết lập lại vị trí ban đầu của rắn
    public void resetSnakePosition() {
        setSnakeXLength(0, 100);
        setSnakeXLength(1, 75);
        setSnakeXLength(2, 50);
        setSnakeYLength(0, 100);
        setSnakeYLength(1, 100);
        setSnakeYLength(2, 100);
    }

    // Phương thức khi rắn ăn kẻ thù
    public void eatEnemy() {
        if (snakeXLength[0] == enemyXPos[xPos] && snakeYLength[0] == enemyYPos[yPos]) {
            lengthOfSnake++;
            scores++;
            xPos = random.nextInt(34);
            yPos = random.nextInt(23);
        }
    }
}
