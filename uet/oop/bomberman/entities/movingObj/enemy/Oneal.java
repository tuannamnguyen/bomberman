package uet.oop.bomberman.entities.movingObj.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObj.Bomber;
import uet.oop.bomberman.entities.stillObj.Brick;
import uet.oop.bomberman.entities.stillObj.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {

    private int refresh = 20;
    public Bomber bomber;

    public Oneal(int x, int y, Image img,Bomber bomber) {
        super(x, y, img);
        this.bomber = bomber;
    }


    public void moveBot() {
        if (refresh == 0) {
            String[] choices = {"UP", "DOWN", "LEFT", "RIGHT"};
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

            refresh = 20;
        } else {
            refresh--;
        }


    }

    public void move() {
        if (refresh == 0) {
            String[] choices = {"UP", "DOWN", "LEFT", "RIGHT"};
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
         /*for (Entity e : BombermanGame.getEntities()) {
            if ((e.getX() == x && e.getY() == y) && e instanceof Bomber) {
                BombermanGame.gameOver = true;
                return true;
            } else if ((e.getX() == x && e.getY() == y) && e instanceof Enemy) {
                return false;
            }
        }

        for (Entity e : BombermanGame.getStillObjects()) {
            if ((e instanceof Wall || e instanceof Brick) && e.getX() == x && e.getY() == y) {
                return false;
            }
        }*/

        Entity e = BombermanGame.getAt(x, y);

        if (e instanceof Bomber) {
            BombermanGame.gameOver = true;
            return true;
        }

        if (e instanceof Enemy || e instanceof Wall || e instanceof Brick) {
            return false;
        }

        return true;
    }
}
