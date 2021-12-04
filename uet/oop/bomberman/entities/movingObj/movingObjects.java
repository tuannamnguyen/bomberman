package uet.oop.bomberman.entities.movingObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class movingObjects extends Entity {
    protected int speed = 32;

    public movingObjects(int x, int y, Image img) {
        super(x, y, img);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract boolean canMove();
}


