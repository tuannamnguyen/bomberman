package uet.oop.bomberman.entities.movingObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.movingObj.enemy.Enemy;
import uet.oop.bomberman.entities.stillObj.Bomb;
import uet.oop.bomberman.entities.stillObj.BombItem;
import uet.oop.bomberman.entities.stillObj.Brick;
import uet.oop.bomberman.entities.stillObj.FlameItem;
import uet.oop.bomberman.entities.stillObj.Portal;
import uet.oop.bomberman.entities.stillObj.SpeedItem;
import uet.oop.bomberman.entities.stillObj.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends movingObjects {
    public static int bombCount = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.direction.equalsIgnoreCase("RIGHT")) {
            if (this.canMove(x + speed, y)) {
                this.x += speed;
                this.img = Sprite.player_right.getFxImage();
            }

        } else if (this.direction.equalsIgnoreCase("LEFT")) {
            if (this.canMove(x - speed, y)) {
                this.x -= speed;
                this.img = Sprite.player_left.getFxImage();
            }

        } else if (this.direction.equalsIgnoreCase("UP")) {
            if (this.canMove(x, y - speed)) {
                this.y -= speed;
                this.img = Sprite.player_up.getFxImage();
            }

        } else if (this.direction.equalsIgnoreCase("DOWN")) {
            if (this.canMove(x, y + speed)) {
                this.y += speed;
                this.img = Sprite.player_down.getFxImage();
            }

        } else if (this.direction.equalsIgnoreCase("NONE")) {
            this.x += 0;
            this.y += 0;
        }

        this.direction = "none";
    }

    @Override
    public boolean canMove(int x, int y) {

        if (BombermanGame.getAt(x, y) instanceof Enemy) {
            this.setRemoved(true);
            BombermanGame.gameOver = true;
            return true;
        }

        if (BombermanGame.getAt(x, y) instanceof Wall || BombermanGame.getAt(x, y) instanceof Brick) {
            return false;
        }

        if (BombermanGame.getAt(x, y) instanceof SpeedItem) {
            // TODO
            BombermanGame.getAt(x, y).setRemoved(true);
            return true;
        }

        if (BombermanGame.getAt(x, y) instanceof FlameItem) {
            Bomb.range++;
            BombermanGame.getAt(x, y).setRemoved(true);
            return true;
        }

        if (BombermanGame.getAt(x, y) instanceof BombItem) {
            bombCount++;
            BombermanGame.getAt(x, y).setRemoved(true);
            return true;
        }

        if (BombermanGame.getAt(x, y) instanceof Portal) {

            return true;
        }

        return true;
    }

}
