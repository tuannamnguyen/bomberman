package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity extends Rectangle {
    /*//Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected int w = 16;

    protected int h = 16;*/

    protected int speed = 1;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        /*this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.setWidth(w);
        this.setHeight(h);*/
        super(xUnit * Sprite.SCALED_SIZE, yUnit * Sprite.SCALED_SIZE, 16, 16);
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, this.getX(), this.getY());
    }
    public abstract void update();

    

    
}
