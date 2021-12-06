package uet.oop.bomberman.entities.stillObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObj.movingObjects;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    int countdown = 120;
    public static boolean existed = false;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        Entity center = BombermanGame.getAt(x, y);
        Entity left = BombermanGame.getAt(x - 32, y);
        Entity right = BombermanGame.getAt(x + 32, y);
        Entity up = BombermanGame.getAt(x, y - 32);
        Entity down = BombermanGame.getAt(x, y + 32);

        if (countdown == 0) {
            Entity explosionCenter = new Explosion(x / 32, y / 32, Sprite.bomb_exploded2.getFxImage());
            BombermanGame.getStillObjects().add(explosionCenter);
            if (center instanceof movingObjects) {
                center.setRemoved(true);
            } 

            if (right instanceof Wall == false) {
                Entity explosionRight = new Explosion((x + 32) / 32, y / 32, Sprite.explosion_horizontal_right_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionRight);

                if (right instanceof Brick || right instanceof movingObjects) {
                    right.setRemoved(true);
                }
            }

            if (left instanceof Wall == false) {
                Entity explosionLeft = new Explosion((x - 32) / 32, y / 32, Sprite.explosion_horizontal_left_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionLeft);

                if (left instanceof Brick || left instanceof movingObjects) {
                    left.setRemoved(true);
                }
            }

            if (down instanceof Wall == false) {
                Entity explosionDown = new Explosion(x / 32, (y + 32) / 32, Sprite.explosion_vertical_down_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionDown);

                if (down instanceof Brick || down instanceof movingObjects) {
                    down.setRemoved(true);
                }
            }

            if (up instanceof Wall == false) {
                Entity explosionUp = new Explosion(x / 32, (y - 32) / 32, Sprite.explosion_vertical_top_last2.getFxImage());
                BombermanGame.getStillObjects().add(explosionUp);

                if (up instanceof Brick || up instanceof movingObjects) {
                    up.setRemoved(true);
                }
            }
            
            this.setRemoved(true);
            
        } else {
            countdown--;
        }
    }
}
