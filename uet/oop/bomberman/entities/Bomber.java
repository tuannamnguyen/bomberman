package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Box;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.AnimatedImage;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        Box box = new Box();
        box.setOnKeyPressed(e -> {
            System.out.println(e.getCode().toString());
        });
        box.setOnKeyReleased(e -> {
            System.out.println(e.getCode().toString());
        });


        if (BombermanGame.currentlyPressedKey.contains(KeyCode.RIGHT)) {
            x += speed;

            List<Image> imgList = new ArrayList<>();
            imgList.add(Sprite.player_right.getFxImage());
            imgList.add(Sprite.player_right_1.getFxImage());
            imgList.add(Sprite.player_right_2.getFxImage());

            AnimatedImage player = new AnimatedImage(imgList, 0.100);

            double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
            this.img = player.getFrame(t);
        } else if (BombermanGame.currentlyPressedKey.contains(KeyCode.LEFT)) {
            x -= speed;

            List<Image> imgList = new ArrayList<>();
            imgList.add(Sprite.player_left.getFxImage());
            imgList.add(Sprite.player_left_1.getFxImage());
            imgList.add(Sprite.player_left_2.getFxImage());

            AnimatedImage player = new AnimatedImage(imgList, 0.100);

            double t = (System.nanoTime() - BombermanGame.startNanoTime) / 1000000000.0;
            this.img = player.getFrame(t);
        }

         
    }
}
