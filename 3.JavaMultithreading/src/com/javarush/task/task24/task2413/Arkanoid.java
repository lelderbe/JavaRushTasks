package com.javarush.task.task24.task2413;

import java.util.List;

public class Arkanoid {
    private int width;
    private int height;

    private Ball ball;
    private Stand stand;
    private List<Brick> bricks;

    private boolean isGameOver;

    static Arkanoid game;

    public void run() {}

    public void move() {
        stand.move();
        ball.move();
    }

    public void checkBricksBump() {
        Brick deadBrick = null;
        for (Brick brick : bricks) {
            if (brick.isIntersec(ball)) {
                double angle = Math.random() * 360;
                ball.setDirection(angle);
                deadBrick = brick;
                break;
            }
        }

        if (deadBrick != null)
            bricks.remove(deadBrick);
    }

    public void checkStandBump() {
        if (stand.isIntersec(ball)) {
            double angle = 90 + 20 * (Math.random() - 0.5);
            ball.setDirection(angle);
        }
    }

    public void checkEndGame() {
        if (ball.getY() > height) {
            isGameOver = true;
        }
    }

    public void draw(Canvas canvas) {
        for (Brick brick : bricks)
            brick.draw(canvas);
        ball.draw(canvas);
        stand.draw(canvas);
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void main(String[] args) {

    }
}
