package uet.oop.bomberman.entities.stillObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    int countdown = 120;
    public static boolean existed = false;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (countdown == 0) {
            Entity explosionCenter = new Explosion(x / 32, y / 32, Sprite.bomb_exploded2.getFxImage());
            BombermanGame.getStillObjects().add(explosionCenter);

            if (BombermanGame.getAt(x + 32, y) instanceof Wall == false) {
                Entity explosionRight = new Explosion((x + 32) / 32, y / 32, Sprite.explosion_horizontal_right_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionRight);

            }

            if (BombermanGame.getAt(x - 32, y) instanceof Wall == false) {
                Entity explosionLeft = new Explosion((x - 32) / 32, y / 32, Sprite.explosion_horizontal_left_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionLeft);
            }

            if (BombermanGame.getAt(x, y + 32) instanceof Wall == false) {
                Entity explosionDown = new Explosion(x / 32, (y + 32) / 32, Sprite.explosion_vertical_down_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionDown);
            }

            if (BombermanGame.getAt(x, y - 32) instanceof Wall == false) {
                Entity explosionUp = new Explosion(x / 32, (y - 32) / 32, Sprite.explosion_vertical_top_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionUp);
            }
            
            
        } else {
            countdown--;
        }
    }
}
