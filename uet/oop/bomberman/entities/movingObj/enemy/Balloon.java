package uet.oop.bomberman.entities.movingObj.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObj.Bomber;
import uet.oop.bomberman.entities.stillObj.Brick;
import uet.oop.bomberman.entities.stillObj.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {
    private int refresh = 60;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (refresh == 0) {
            String[] choices = { "UP", "DOWN", "LEFT", "RIGHT" };
            this.setDirection(choices[randomize()]);

            if (this.direction.equalsIgnoreCase("RIGHT")) {
                if (this.canMove(x + speed, y)) {
                    this.x += speed;
                    this.img = Sprite.balloom_right1.getFxImage();
                }

            } else if (this.direction.equalsIgnoreCase("LEFT")) {
                if (this.canMove(x - speed, y)) {
                    this.x -= speed;
                    this.img = Sprite.balloom_left1.getFxImage();
                }

            } else if (this.direction.equalsIgnoreCase("UP")) {
                if (this.canMove(x, y - speed)) {
                    this.y -= speed;
                    this.img = Sprite.balloom_right1.getFxImage();
                }

            } else if (this.direction.equalsIgnoreCase("DOWN")) {
                if (this.canMove(x, y + speed)) {
                    this.y += speed;
                    this.img = Sprite.balloom_right1.getFxImage();
                }

            }

            refresh = 60;
        } else {
            refresh--;
        }

    }

    @Override
    public boolean canMove(int x, int y) {
 
        Entity e = BombermanGame.getAt(x, y);

        if (e instanceof Bomber) {
            e.setRemoved(true);
            return true;
        }

        if (e instanceof Enemy || e instanceof Wall || e instanceof Brick) {
            return false;
        }

        return true;
    }
}
