package uet.oop.bomberman.entities.movingObj;

import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Balloon extends Entity {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction direction;

    @Override
    public void update() {
        Random random = new Random();
        int ranNum = random.nextInt(4);

        if (ranNum == 0) {
            // go up

            

        } else if (ranNum == 1) {
            
        } else if (ranNum == 2) {
           
        } else if (ranNum == 3) {
            
        }
    }
}
