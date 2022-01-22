package nam.bomberman.uet.oop.bomberman.entities.stillObj;

import javafx.scene.image.Image;
import nam.bomberman.uet.oop.bomberman.entities.Entity;

public class Explosion extends Entity {
    private int wait = 20;

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
