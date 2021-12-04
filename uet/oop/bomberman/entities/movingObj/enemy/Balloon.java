package uet.oop.bomberman.entities.movingObj.enemy;

import javafx.scene.image.Image;

public class Balloon extends Enemy {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean canMove(int x, int y) {
       return true;
    }
}
