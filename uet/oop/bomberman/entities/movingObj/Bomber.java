package uet.oop.bomberman.entities.movingObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends movingObjects {

    public static boolean isAlive = true;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.direction.equalsIgnoreCase("RIGHT")) {
            this.x += speed;
            this.img = Sprite.player_right.getFxImage();
        } else if (this.direction.equalsIgnoreCase("LEFT")) {
            this.x -= speed;
            this.img = Sprite.player_left.getFxImage();
        } else if (this.direction.equalsIgnoreCase("UP")) {
            this.y -= speed;
            this.img = Sprite.player_up.getFxImage();
        } else if (this.direction.equalsIgnoreCase("DOWN")) {
            this.y += speed;
            this.img = Sprite.player_down.getFxImage();
        } else if (this.direction.equalsIgnoreCase("NONE")) {
            this.x += 0;
            this.y += 0;
        }

        this.direction = "none";
    }

    @Override
    public boolean canMove() {
        return true;
    }
}
