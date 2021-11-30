package uet.oop.bomberman.entities.movingObj;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.AnimatedImage;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends movingObjects {
    enum facingDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private facingDirection direction;

    public static boolean isAlive = true;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (BombermanGame.currentlyPressedKey.contains(KeyCode.D)) {
            moveRight();
        } else if (BombermanGame.currentlyPressedKey.contains(KeyCode.A)) {
            moveLeft();
        } else if (BombermanGame.currentlyPressedKey.contains(KeyCode.W)) {
            moveUp();
        } else if (BombermanGame.currentlyPressedKey.contains(KeyCode.S)) {
            moveDown();
        } else {
            if (direction == facingDirection.UP) {
                this.img = Sprite.player_up.getFxImage();
            } else if (direction == facingDirection.DOWN) {
                this.img = Sprite.player_down.getFxImage();
            } else if (direction == facingDirection.LEFT) {
                this.img = Sprite.player_left.getFxImage();
            } else if (direction == facingDirection.RIGHT) {
                this.img = Sprite.player_right.getFxImage();
            }
        }
    }

    @Override
    public boolean canMove() {

    }

    private void moveUp() {
        this.setY(this.getY() - speed);

        List<Image> imgList = new ArrayList<>();
        imgList.add(Sprite.player_up.getFxImage());
        imgList.add(Sprite.player_up_1.getFxImage());
        imgList.add(Sprite.player_up_2.getFxImage());

        AnimatedImage player = new AnimatedImage(imgList, 0.100);

        double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
        this.img = player.getFrame(t);
        direction = facingDirection.UP;
    }

    private void moveDown() {
        this.setY(this.getY() + speed);

        List<Image> imgList = new ArrayList<>();
        imgList.add(Sprite.player_down.getFxImage());
        imgList.add(Sprite.player_down_1.getFxImage());
        imgList.add(Sprite.player_down_2.getFxImage());

        AnimatedImage player = new AnimatedImage(imgList, 0.100);

        double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
        this.img = player.getFrame(t);
        direction = facingDirection.DOWN;

    }

    private void moveLeft() {
        this.setX(this.getX() - speed);

        List<Image> imgList = new ArrayList<>();
        imgList.add(Sprite.player_left.getFxImage());
        imgList.add(Sprite.player_left_1.getFxImage());
        imgList.add(Sprite.player_left_2.getFxImage());

        AnimatedImage player = new AnimatedImage(imgList, 0.100);

        double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
        this.img = player.getFrame(t);
        direction = facingDirection.LEFT;
    }

    private void moveRight() {
        this.setX(this.getX() + speed);

        List<Image> imgList = new ArrayList<>();
        imgList.add(Sprite.player_right.getFxImage());
        imgList.add(Sprite.player_right_1.getFxImage());
        imgList.add(Sprite.player_right_2.getFxImage());

        AnimatedImage player = new AnimatedImage(imgList, 0.100);

        double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
        this.img = player.getFrame(t);
        direction = facingDirection.RIGHT;
    }
}
