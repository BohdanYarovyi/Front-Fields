package com.yarovyi.game.enemies;

import com.yarovyi.game.gameComponents.Bullet;
import com.yarovyi.game.gameComponents.LaserGun;
import com.yarovyi.game.programScenes.GameScene;
import com.yarovyi.game.util.FileManager;

import javax.swing.*;
import java.awt.*;

public class SpaceDestroyer extends Enemy{
    private int laserDamage;
    private LaserGun laser;
    public SpaceDestroyer(int x,
                          int y,
                          int size_W,
                          int size_H,
                          int startHealth,
                          int startMoveSpeed,
                          int startBulletSpeed,
                          int startDamage,
                          int laserDamage,
                          int regenerationSpeed,
                          int coolDown,
                          int moveAmplitude) {
        super(  x,
                y,
                size_W,
                size_H,
                startHealth,
                startMoveSpeed,
                startBulletSpeed,
                startDamage,
                regenerationSpeed,
                coolDown,
                moveAmplitude);
        this.laserDamage = laserDamage;
        this.point = 20;
        this.imageWithoutFilter = FileManager.SPACEDESTROYER_IDLE.getImage();
        this.imageRedFilter = FileManager.SPACEDESTROYER_DAMAGED.getImage();
        laser = new LaserGun(this.x + size_W/2, this.y + size_H, this.laserDamage, 90, 120);
    }

    @Override
    public void controller(GameScene gc) {
        laser.controller(this.x + size_W/2, this.y + size_H);
        hitBox.setCords(this.x, this.y, this.size_W, this.size_H);
        move();
        showDamage();
        regeneration();
        shoot(gc);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imagePNG, x, y,size_W,size_H, null);
        laser.paint(g);
        paintHealthBar(g);
//        showHitBox(g);
    }

    @Override
    public void paintHealthBar(Graphics g) {
        super.paintHealthBar(g);
        float coefficientReloadTime;
        if (laser.isWork()){
            coefficientReloadTime = (float)size_W / laser.getLaserWorkTime();
            g.setColor(Color.ORANGE);
            g.fillRect(this.x, this.y - 10, (int)(laser.getLaserTimer() * coefficientReloadTime), 4);
        }else{
            coefficientReloadTime = (float)size_W / laser.getLaserCoolDown();
            g.setColor(Color.ORANGE);
            g.fillRect(this.x, this.y - 10, (int)((laser.getLaserCoolDown() -
                    laser.getRechargeTimer()) * coefficientReloadTime), 4);
        }
    }

    @Override
    public void shoot(GameScene gc) {
        if(rechargeTimer < 1 && !laser.isWork()){
            gc.bulletsOnBattlefield.add(
                    new Bullet(this.x + (int)(this.size_W * 0.2), this.y + this.size_H - 30,5,10,
                            this.damage,this.bulletSpeed,nameClass)
            );
            gc.bulletsOnBattlefield.add(
                    new Bullet(this.x + (int)(this.size_W * 0.8),this.y + this.size_H - 30,5,10,
                            this.damage,this.bulletSpeed,nameClass)
            );
            rechargeTimer = coolDown;
        }else{
            rechargeTimer--;
        }
    }
}
