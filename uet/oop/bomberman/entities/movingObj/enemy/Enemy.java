package uet.oop.bomberman.entities.movingObj.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.movingObj.movingObjects;

public abstract class Enemy extends movingObjects{
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean canMove() {

    }
}
