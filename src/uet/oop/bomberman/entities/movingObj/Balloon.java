package uet.oop.bomberman.entities.movingObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.AnimatedImage;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.*;
import java.util.*;
import java.util.Timer;


public class Balloon extends Entity {

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    //    Bomber bomber = new Bomber();
    enum facingDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private facingDirection direction;

    public void moveRight() {
        this.setX(this.getX() + speed);

        List<Image> imgList = new ArrayList<>();
        imgList.add(Sprite.balloom_right1.getFxImage());
        imgList.add(Sprite.balloom_right2.getFxImage());
        imgList.add(Sprite.balloom_right3.getFxImage());

        AnimatedImage player = new AnimatedImage(imgList, 0.100);

        double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
        this.img = player.getFrame(t);
        direction = Balloon.facingDirection.RIGHT;
    }

    public void moveLeft(){
        this.setX(this.getX() - speed);

        List<Image> imgList = new ArrayList<>();
        imgList.add(Sprite.balloom_left1.getFxImage());
        imgList.add(Sprite.balloom_left2.getFxImage());
        imgList.add(Sprite.balloom_left3.getFxImage());

        AnimatedImage player = new AnimatedImage(imgList, 0.100);

        double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
        this.img = player.getFrame(t);
        direction = Balloon.facingDirection.LEFT;
        if(this.getX() <=0) {
            moveRight();
        }

    }




    @Override
    public void update()  {

        moveLeft();



    }
}
