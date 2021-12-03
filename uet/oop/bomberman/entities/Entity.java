package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity extends Circle {
    // Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.setCenterX(this.x += 16);
        this.setCenterY(this.y += 16);
        this.setRadius(16);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public boolean collide(Entity other) {
        double distance = Math.sqrt(Math.pow(this.getCenterX() - other.getCenterX(), 2)
                + Math.pow(this.getCenterY() - other.getCenterY(), 2));
        return (distance < this.getRadius() + other.getRadius()
                && distance > Math.abs(this.getRadius() - other.getRadius()));
    }
}