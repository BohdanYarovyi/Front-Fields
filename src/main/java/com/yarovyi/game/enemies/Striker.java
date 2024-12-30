package com.yarovyi.game.enemies;

import com.yarovyi.game.util.FileManager;

public class Striker extends Enemy{
    public Striker(int x, int y,
                   int size_W,
                   int size_H,
                   int startHealth,
                   int startMoveSpeed,
                   int startBulletSpeed,
                   int startDamage,
                   int regenerationSpeed,
                   int coolDown,
                   int moveAmplitude) {
        super(x, y,
                size_W,
                size_H,
                startHealth,
                startMoveSpeed,
                startBulletSpeed,
                startDamage,
                regenerationSpeed,
                coolDown,
                moveAmplitude
        );
        this.point = 10;
        this.imageWithoutFilter = FileManager.STRIKER_IDLE.getImage();
        this.imageRedFilter = FileManager.STRIKER_DAMAGED.getImage();
    }

    public String toString(){
        return "ID:" + id + " " + getClass();
    }
}
