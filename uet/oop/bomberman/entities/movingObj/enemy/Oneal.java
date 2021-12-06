package uet.oop.bomberman.entities.movingObj.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.facingDirection;
import uet.oop.bomberman.entities.movingObj.Bomber;
import uet.oop.bomberman.entities.stillObj.Brick;
import uet.oop.bomberman.entities.stillObj.Wall;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends Enemy {

//    int speed =64;
    public Bomber bomber;
    public Oneal(int x, int y, Image img,Bomber bomber) {
            super(x, y, img);
            this.bomber=bomber;
        }

        @Override
        public void update() {

            BotEnemy botEnemy = new BotEnemy(bomber, this);
            switch (botEnemy.getDirection()) {
                case RIGHT:
                    if(this.canMove(x - speed, y)){
                        x=x - speed;
                        this.img = Sprite.oneal_left1.getFxImage();
                    }
                    break;


                case LEFT:
                    if(this.canMove(x + speed, y)){
                        x=x + speed;
                        this.img = Sprite.oneal_right1.getFxImage();
                    }
                    break;


                case DOWN:
                    if(this.canMove(x, y - speed)){
                        y=y - speed;
                        this.img = Sprite.oneal_right1.getFxImage();
                    }
                    break;


                case UP:
                    if(this.canMove(x,y + speed)) {
                        y=y + speed;
                        this.img = Sprite.oneal_right1.getFxImage();
                    }
                    break;


                default: break;
            }



        }

        @Override
        public boolean canMove(int x, int y) {

            for (Entity e : BombermanGame.getEntities()) {
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
            }

            return true;
        }
    }
