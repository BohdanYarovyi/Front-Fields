package bin.gameComponents;

import bin.GameDisplay;
import bin.enemies.Enemy;
import bin.programScenes.GameScene;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    private static int countOf = 0;
    private final int ID;
    private Image IMG;
    private int x, y;
    private int width, height;
    private String fromBullet;
    private int damage;
    private int moveSpeed;

    public Bullet( int x,
                   int y,
                   int width,
                   int height,
                   int damage,
                   int moveSpeed,
                   String fromBullet){
        this.width = width;
        this.height = height;
        this.x = x - this.width/2;
        this.y = y;
        this.damage = damage;
        this.moveSpeed = moveSpeed;
        this.fromBullet = fromBullet;
        switch(fromBullet){
            case "HERO" -> {
                IMG = new ImageIcon("src/bin/image/bullets/greenPlasma_oval.png").getImage();
            }
            case "ENEMY" -> {
                IMG = new ImageIcon("src/bin/image/bullets/redPlasma_oval.png").getImage();
            }
        }
        this.ID = ++countOf;
    }

    public void move(GameScene gc){
        switch (fromBullet) {
            case "HERO" -> y -= moveSpeed;
            case "ENEMY" -> y += moveSpeed;
        }
        removeBullet(gc);
    }

    public void controller(GameScene gc){
        this.move(gc);
        switch (fromBullet) {
            case "HERO" -> {
                for (Enemy enemy : gc.enemiesOnBattlefield) {
                    if (this.y > enemy.getHitBox().aY && this.y < enemy.getHitBox().bY &&
                        this.x > enemy.getHitBox().aX && this.x < enemy.getHitBox().bX) {
                        enemy.isDamaged(this.damage, gc);
                        gc.deathBullets.add(this);
                        if (gc.hero.isVampirism()) {
                            if (gc.hero.getHealth() < gc.hero.getStartHealth()) {
                                gc.hero.setHealth(gc.hero.getHealth() + (int)(this.damage * 0.02) + 1);
                            }
                        }
                        gc.explosionsInGame.add(new ExplosionEffect(this.x,this.y,35));
                    }
                }
            }
            case "ENEMY" -> {
                if (this.y > gc.hero.getHitBox().aY && this.y < gc.hero.getHitBox().bY &&
                        this.x > gc.hero.getHitBox().aX && this.x < gc.hero.getHitBox().bX) {
                    gc.hero.isDamaged(this.damage);
                    gc.deathBullets.add(this);
                    gc.explosionsInGame.add(new ExplosionEffect(this.x,this.y,35));
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public void removeBullet(GameScene gc){
        if(this.y < -100 || this.y > GameDisplay.FRAME_HEIGHT + 10){
            gc.deathBullets.add(this);
        }
    }

    public void paint(Graphics g){
           g.drawImage(IMG, this.x, this.y, this.width, this.height, null);
    }

    public String toString(){
        return "" + this.ID + " " + fromBullet;
    }
}
