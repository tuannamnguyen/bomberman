package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity extends Rectangle {
    /**
     * Các thuộc tính:
     * x: tọa độ x tính từ góc trên bên trái canvas.
     * y: tọa độ y tính từ góc trên bên trái canvas.
     * w và h là chiều dài và rộng của hình chữ nhật bao quanh đối tượng.
     * 
     * truy cập các thuộc tính trên bằng setter và getter của lớp rectangle.
     */

    protected int speed = 1;

    protected Image img;

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {  
        super(xUnit * Sprite.SCALED_SIZE, yUnit * Sprite.SCALED_SIZE, img.getWidth(), img.getHeight());
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, this.getX(), this.getY());
    }

    public abstract void update();

    public boolean collide(Entity other) {
        return (this.getX() < other.getX() + other.getWidth() &&
                this.getX() + this.getWidth() > other.getX() &&
                this.getY() < other.getY() + other.getHeight() &&
                this.getHeight() + this.getY() > other.getY());
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    

    
}
