package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Box;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.AnimatedImage;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    enum facingDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private facingDirection direction;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (BombermanGame.currentlyPressedKey.contains(KeyCode.D)) {
            x += speed;

            List<Image> imgList = new ArrayList<>();
            imgList.add(Sprite.player_right.getFxImage());
            imgList.add(Sprite.player_right_1.getFxImage());
            imgList.add(Sprite.player_right_2.getFxImage());

            AnimatedImage player = new AnimatedImage(imgList, 0.100);

            double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
            this.img = player.getFrame(t);
            direction = facingDirection.RIGHT;
        } else if (BombermanGame.currentlyPressedKey.contains(KeyCode.A)) {
            x -= speed;

            List<Image> imgList = new ArrayList<>();
            imgList.add(Sprite.player_left.getFxImage());
            imgList.add(Sprite.player_left_1.getFxImage());
            imgList.add(Sprite.player_left_2.getFxImage());

            AnimatedImage player = new AnimatedImage(imgList, 0.100);

            double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
            this.img = player.getFrame(t);
            direction = facingDirection.LEFT;
        } else if (BombermanGame.currentlyPressedKey.contains(KeyCode.W)) {
            y -= speed;

            List<Image> imgList = new ArrayList<>();
            imgList.add(Sprite.player_up.getFxImage());
            imgList.add(Sprite.player_up_1.getFxImage());
            imgList.add(Sprite.player_up_2.getFxImage());

            AnimatedImage player = new AnimatedImage(imgList, 0.100);

            double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
            this.img = player.getFrame(t);
            direction = facingDirection.UP;
        } else if (BombermanGame.currentlyPressedKey.contains(KeyCode.S)) {
            y += speed;

            List<Image> imgList = new ArrayList<>();
            imgList.add(Sprite.player_down.getFxImage());
            imgList.add(Sprite.player_down_1.getFxImage());
            imgList.add(Sprite.player_down_2.getFxImage());

            AnimatedImage player = new AnimatedImage(imgList, 0.100);

            double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
            this.img = player.getFrame(t);
            direction = facingDirection.DOWN;
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
}
