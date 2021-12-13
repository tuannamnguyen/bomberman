package uet.oop.bomberman.entities.stillObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObj.movingObjects;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int countdown = 120;
    public static boolean existed = false;
    public static int range = 1;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        for (int i = 1; i <= range; i++) {
            Entity center = BombermanGame.getAt(x, y);
            Entity left = BombermanGame.getAt(x - 32 * i, y);
            Entity right = BombermanGame.getAt(x + 32 * i, y);
            Entity up = BombermanGame.getAt(x, y - 32 * i);
            Entity down = BombermanGame.getAt(x, y + 32 * i);

            Entity prevLeft = BombermanGame.getAt(x - 32 * (i - 1), y);
            Entity prevRight = BombermanGame.getAt(x + 32 * (i - 1), y);
            Entity prevUp = BombermanGame.getAt(x, y - 32 * (i - 1));
            Entity prevDown = BombermanGame.getAt(x, y + 32 * (i - 1));

            if (countdown == 0) {
                Entity explosionCenter = new Explosion(x / 32, y / 32, Sprite.bomb_exploded2.getFxImage());
                BombermanGame.getStillObjects().add(explosionCenter);
                if (center instanceof movingObjects) {
                    center.setRemoved(true);
                }

                if (!(right instanceof Wall) && !(prevRight instanceof Wall)) {
                    Entity explosionRight = new Explosion((x + 32 * i) / 32, y / 32,
                            Sprite.explosion_horizontal_right_last2.getFxImage());
                    BombermanGame.getStillObjects().add(explosionRight);

                    if (right instanceof Brick || right instanceof movingObjects) {
                        right.setRemoved(true);
                    }
                }

                if (!(left instanceof Wall) && !(prevLeft instanceof Wall)) {
                    Entity explosionLeft = new Explosion((x - 32 * i) / 32, y / 32,
                            Sprite.explosion_horizontal_left_last2.getFxImage());
                    BombermanGame.getStillObjects().add(explosionLeft);

                    if (left instanceof Brick || left instanceof movingObjects) {
                        left.setRemoved(true);
                    }
                }

                if (!(down instanceof Wall) && !(prevDown instanceof Wall)) {
                    Entity explosionDown = new Explosion(x / 32, (y + 32 * i) / 32,
                            Sprite.explosion_vertical_down_last2.getFxImage());
                    BombermanGame.getStillObjects().add(explosionDown);

                    if (down instanceof Brick || down instanceof movingObjects) {
                        down.setRemoved(true);
                    }
                }

                if (!(up instanceof Wall) && !(prevUp instanceof Wall)) {
                    Entity explosionUp = new Explosion(x / 32, (y - 32 * i) / 32,
                            Sprite.explosion_vertical_top_last2.getFxImage());
                    BombermanGame.getStillObjects().add(explosionUp);

                    if (up instanceof Brick || up instanceof movingObjects) {
                        up.setRemoved(true);
                    }
                }
                Sound.playSound("explosionBomb");
                this.setRemoved(true);

            } else {
                countdown--;
            }
        }

    }
}
