import java.util.Random;

public class Model {
    private int[] snakeXLength = new int [750];
    private int[] snakeYLength = new int [750];
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    private int delay = 100;
    private int lengthOfSnake = 3;
    private int moves = 0;
    private int scores = 0;
    private boolean gameOver = false;
    private int[] enemyXPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
            600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyYPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
            600,625};
    private Random random = new Random();
    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);
    public Model (){}

    // Getter and setter methods for all the variables
    public int[] getSnakeXLength() {
        return snakeXLength;
    }

    public void setSnakeXLength(int[] snakeXLength) {
        this.snakeXLength = snakeXLength;
    }

    public int[] getSnakeYLength() {
        return snakeYLength;
    }

    public void setSnakeYLength(int[] snakeYLength) {
        this.snakeYLength = snakeYLength;
    }

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

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getLengthOfSnake() {
        return lengthOfSnake;
    }

    public void setLengthOfSnake(int lengthOfSnake) {
        this.lengthOfSnake = lengthOfSnake;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public boolean isGameOver() {
        for (int i=1;i<lengthOfSnake;i++)
            if(snakeXLength[i]==snakeXLength[0]&&snakeYLength[i]==snakeYLength[0]){
                gameOver=true;
                right=false;
                left=false;
                up=false;
                down=false;
            }
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int[] getEnemyXPos() {
        return enemyXPos;
    }

    public void setEnemyXPos(int[] enemyXPos) {
        this.enemyXPos = enemyXPos;
    }

    public int[] getEnemyYPos() {
        return enemyYPos;
    }

    public void setEnemyYPos(int[] enemyYPos) {
        this.enemyYPos = enemyYPos;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
    public void setEnemy(){
        Random random = new Random();
        xPos=random.nextInt(34);
        yPos=random.nextInt(23);
    }
    public void setSnakeX(int x, int u){
        snakeXLength[x] = u;
    }public void setSnakeY(int y, int u){
        snakeYLength[y] = u;
    }
    public void eatEnemy(){
        if(snakeXLength[0]==enemyXPos[xPos]&&snakeYLength[0]==enemyYPos[yPos]){
            lengthOfSnake++;
            scores++;
            xPos=random.nextInt(34);
            yPos=random.nextInt(23);
        }
    }


}
