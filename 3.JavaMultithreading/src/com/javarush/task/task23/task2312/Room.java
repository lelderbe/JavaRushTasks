package com.javarush.task.task23.task2312;

public class Room {
    public static Room game;

    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
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

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public static void main(String[] args) {
        Snake snake = new Snake(5, 5);
        game = new Room(20, 10, snake);
        snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();

    }

    public void sleep() {}

    public void run() {}

    public void print() {}

    public void createMouse() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);

    }

    public void eatMouse() {
        createMouse();
    }
}