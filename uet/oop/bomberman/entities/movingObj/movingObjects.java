package uet.oop.bomberman.entities.movingObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class movingObjects extends Entity {
    protected int speed = 32;
    protected String direction = "";

    public movingObjects(int x, int y, Image img) {
        super(x, y, img);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public abstract boolean canMove();
}


