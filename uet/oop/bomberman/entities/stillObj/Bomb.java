package uet.oop.bomberman.entities.stillObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class Bomb extends Entity {
    int countdown = 120;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (countdown == 0) {

        } else {
            countdown--;
        }
    }

    
}
