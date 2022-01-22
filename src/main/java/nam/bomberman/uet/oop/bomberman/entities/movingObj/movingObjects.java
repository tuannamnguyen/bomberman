package nam.bomberman.uet.oop.bomberman.entities.movingObj;

import javafx.scene.image.Image;
import nam.bomberman.uet.oop.bomberman.entities.Entity;

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

    /**
     * check if object can move to (x, y).
     * 
     * @param x x coordinate.
     * @param y y coordinate.
     * @return can move?
     */
    public abstract boolean canMove(int x, int y);
}
