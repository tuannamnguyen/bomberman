package nam.bomberman.uet.oop.bomberman.entities.movingObj.enemy;

import javafx.scene.image.Image;
import nam.bomberman.uet.oop.bomberman.BombermanGame;
import nam.bomberman.uet.oop.bomberman.entities.Entity;
import nam.bomberman.uet.oop.bomberman.entities.movingObj.Bomber;
import nam.bomberman.uet.oop.bomberman.entities.stillObj.Brick;
import nam.bomberman.uet.oop.bomberman.entities.stillObj.Wall;
import nam.bomberman.uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {

    private int refresh = 30;
    public Bomber bomber;

    public Oneal(int x, int y, Image img, Bomber bomber) {
        super(x, y, img);
        this.bomber = bomber;
    }

    public void moveBot() {
        if (refresh == 0) {
            String[] choices = { "UP", "DOWN", "LEFT", "RIGHT" };
            this.setDirection(choices[randomize()]);
            onealBot bot = new onealBot(bomber, this);

            switch (bot.getDirection()) {
                case UP:
                    if (this.canMove(x, y + speed)) {
                        this.y += speed;
                        this.img = Sprite.oneal_right1.getFxImage();
                        break;
                    }

                case DOWN:
                    if (this.canMove(x, y - speed)) {
                        this.y -= speed;
                        this.img = Sprite.oneal_right1.getFxImage();
                        break;
                    }

                case LEFT:
                    if (this.canMove(x - speed, y)) {
                        this.x -= speed;
                        this.img = Sprite.oneal_left1.getFxImage();
                        break;
                    }

                case RIGHT:
                    if (this.canMove(x + speed, y)) {
                        this.x += speed;
                        this.img = Sprite.oneal_right1.getFxImage();
                        break;
                    }

                default:
                    if (this.direction.equalsIgnoreCase("RIGHT")) {
                        if (this.canMove(x + speed, y)) {
                            this.x += speed;
                            this.img = Sprite.oneal_right1.getFxImage();
                        }

                    } else if (this.direction.equalsIgnoreCase("LEFT")) {
                        if (this.canMove(x - speed, y)) {
                            this.x -= speed;
                            this.img = Sprite.oneal_left1.getFxImage();
                        }

                    } else if (this.direction.equalsIgnoreCase("UP")) {
                        if (this.canMove(x, y - speed)) {
                            this.y -= speed;
                            this.img = Sprite.oneal_right1.getFxImage();
                        }

                    } else if (this.direction.equalsIgnoreCase("DOWN")) {
                        if (this.canMove(x, y + speed)) {
                            this.y += speed;
                            this.img = Sprite.oneal_right1.getFxImage();
                        }

                    }
            }

            refresh = 30;
        } else {
            refresh--;
        }

    }

    public void move() {
        if (refresh == 0) {
            String[] choices = { "UP", "DOWN", "LEFT", "RIGHT" };
            this.setDirection(choices[randomize()]);

            if (this.direction.equalsIgnoreCase("RIGHT")) {
                if (this.canMove(x + speed, y)) {
                    this.x += speed;
                    this.img = Sprite.oneal_right1.getFxImage();
                }

            } else if (this.direction.equalsIgnoreCase("LEFT")) {
                if (this.canMove(x - speed, y)) {
                    this.x -= speed;
                    this.img = Sprite.oneal_left1.getFxImage();
                }

            } else if (this.direction.equalsIgnoreCase("UP")) {
                if (this.canMove(x, y - speed)) {
                    this.y -= speed;
                    this.img = Sprite.oneal_right1.getFxImage();
                }

            } else if (this.direction.equalsIgnoreCase("DOWN")) {
                if (this.canMove(x, y + speed)) {
                    this.y += speed;
                    this.img = Sprite.oneal_right1.getFxImage();
                }

            }

            refresh = 20;
        } else {
            refresh--;
        }

    }

    @Override
    public void update() {
        moveBot();
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
