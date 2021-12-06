package uet.oop.bomberman.entities.stillObj;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class Explosion extends Entity {
    private int wait = 60;

    public Explosion(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (wait == 0) {
            this.setRemoved(true);
        } else {
            wait--;
        }
    }
}
