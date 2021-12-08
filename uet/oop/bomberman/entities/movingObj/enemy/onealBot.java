package uet.oop.bomberman.entities.movingObj.enemy;

import uet.oop.bomberman.entities.facingDirection;
import uet.oop.bomberman.entities.movingObj.Bomber;

import java.util.Random;

public class onealBot {
    public Bomber bomber;
    public Enemy enemy;


    public onealBot(Bomber bomber, Enemy enemy) {

        this.bomber = bomber;
        this.enemy = enemy;
    }




    public facingDirection diNgang() {
        int distanceX = Math.abs(enemy.getX() - bomber.getX()) / 32;
        if (enemy.getX() == bomber.getX()
                && enemy.getY() == bomber.getY()) return facingDirection.STATIONARY;
        else if (enemy.getX() > bomber.getX() && (distanceX <= 5)) {
            return facingDirection.LEFT;
        } else if (enemy.getX() == bomber.getX()) {
            return diDoc();
        } else if (enemy.getX() < bomber.getX() && (distanceX <= 5)) {
            return facingDirection.RIGHT;
        } else {
            return facingDirection.STATIONARY;
        }
    }

    public facingDirection diDoc() {
        int distanceY = Math.abs(enemy.getY() - bomber.getY()) / 32;
        if (enemy.getX() == bomber.getX()
                && enemy.getY() == bomber.getY()) return facingDirection.STATIONARY;
        else if (enemy.getY() > bomber.getY() && (distanceY <= 5)) {
            return facingDirection.DOWN;
        } else if (enemy.getY() == bomber.getY()) {
            return diNgang();
        } else if (enemy.getY() < bomber.getY() && (distanceY <= 5)) {
            return facingDirection.UP;
        } else {
            return facingDirection.STATIONARY;
        }
    }

    public facingDirection getDirection() {
        Random random = new Random();
        if (random.nextInt(2) == 0) return diNgang();
        return diDoc();


    }


}
