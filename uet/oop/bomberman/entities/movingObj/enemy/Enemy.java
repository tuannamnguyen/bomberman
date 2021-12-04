package uet.oop.bomberman.entities.movingObj.enemy;

import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.movingObj.movingObjects;

public abstract class Enemy extends movingObjects {
    protected int refresh = 20;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    protected int randomize() {
        Random random = new Random();
        int r = random.nextInt(4);
        return r;
    }

    
}
