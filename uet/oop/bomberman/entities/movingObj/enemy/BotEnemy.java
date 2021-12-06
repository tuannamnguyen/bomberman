package uet.oop.bomberman.entities.movingObj.enemy;

import uet.oop.bomberman.entities.movingObj.Bomber;
import uet.oop.bomberman.entities.facingDirection;

import java.util.Random;

public class BotEnemy {
    Bomber bomber;
    Enemy enemy;

    public BotEnemy(Bomber bomber, Enemy enemy) {
        this.enemy = enemy;
        this.bomber = bomber;
    }

    public facingDirection diNgang() {
        if (enemy.getX() == bomber.getX()
                && enemy.getY() == bomber.getY()) return facingDirection.STATIONARY;
        else if (enemy.getX() > bomber.getX()) {
            return facingDirection.RIGHT;
        } else if (enemy.getX() == bomber.getX()) {
            return diDoc();
        } else {
            return facingDirection.LEFT;
        }
    }

    public facingDirection diDoc() {
        if (enemy.getX() == bomber.getX()
                && enemy.getY() == bomber.getY()) return facingDirection.STATIONARY;
        else if (enemy.getY() > bomber.getY()) {
            return facingDirection.DOWN;
        } else if (enemy.getY() == bomber.getY()) {
            return diNgang();
        } else {
            return facingDirection.UP;
        }
    }

    public facingDirection getDirection() {
        Random random = new Random();
        if(random.nextInt(2) == 0) return diNgang();
        return diDoc();



    }
}

