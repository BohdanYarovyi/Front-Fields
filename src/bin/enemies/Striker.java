package bin.enemies;

import javax.swing.*;

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
        this.imageWithoutFilter = new ImageIcon("src/bin/image/persons/enemy/striker.png").getImage();
        this.imageRedFilter = new ImageIcon("src/bin/image/persons/enemy/striker_red.png").getImage();
    }

    public String toString(){
        return "ID:" + id + " " + getClass();
    }
}
