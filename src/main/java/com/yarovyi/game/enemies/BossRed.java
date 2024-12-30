package com.yarovyi.game.enemies;

import com.yarovyi.game.gameComponents.Bullet;
import com.yarovyi.game.gameComponents.ExplosionEffect;
import com.yarovyi.game.gameComponents.LaserGun;
import com.yarovyi.game.programScenes.GameScene;
import com.yarovyi.game.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BossRed extends Enemy{
    LaserGun laserOne;
    LaserGun laserTwo;
    private int laserDamage;

    public BossRed(int x,
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
        this.point = 70;
        this.imageWithoutFilter = FileManager.BOSS_IDLE.getImage();
        this.imageRedFilter = FileManager.BOSS_DAMAGED.getImage();
        laserOne = new LaserGun(this.x + (int)(this.size_W*0.2), this.y + (int)(this.size_H* 0.8),
                this.laserDamage, 180, 120);
        laserTwo = new LaserGun(this.x + (int)(this.size_W*0.8), this.y + (int)(this.size_H* 0.8),
                this.laserDamage, 180, 120);
    }

    @Override
    public void controller(GameScene gc) {
        hitBox.setCords(this.x, this.y, this.size_W, this.size_H);
        this.laserOne.controller((int)(this.x + (this.size_W*0.20)), this.y + (int)(this.size_H* 0.78));
        this.laserTwo.controller((int)(this.x + (this.size_W*0.80)), this.y + (int)(this.size_H* 0.78));
        move();
        showDamage();
        regeneration();

        shoot(gc);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imagePNG, x, y,size_W,size_H, null);
        this.laserOne.paint(g);
        this.laserTwo.paint(g);
        paintHealthBar(g);
//        showHitBox(g);
    }

    @Override
    public void paintHealthBar(Graphics g) {
        super.paintHealthBar(g);
        float coefficientReloadTime;
        if (laserOne.isWork()){
            coefficientReloadTime = (float)size_W / laserOne.getLaserWorkTime();
            g.setColor(Color.ORANGE);
            g.fillRect(this.x, this.y - 10, (int)(laserOne.getLaserTimer() * coefficientReloadTime),
                    4);
        }else{
            coefficientReloadTime = (float)size_W / laserOne.getLaserCoolDown();
            g.setColor(Color.ORANGE);
            g.fillRect(this.x, this.y - 10, (int)((laserOne.getLaserCoolDown() -
                    laserOne.getRechargeTimer()) * coefficientReloadTime), 4);
        }
    }

    @Override
    public void shoot(GameScene gc) {
        if(rechargeTimer < 1){
            gc.bulletsOnBattlefield.add(
                    new Bullet(this.x + this.size_W / 2, this.y + this.size_H - 40,8,14,
                            this.damage,this.bulletSpeed,nameClass)
            );
            rechargeTimer = coolDown;
        }else{
            rechargeTimer--;
        }
    }

    @Override
    public void isDamaged(int damage, GameScene gc){
        health -= damage;
        timerForIMG = 10;
        if (health < 1) {
            gc.deathEnemies.add(this);
            gc.explosionsInGame.addAll(Arrays.asList(
                    new ExplosionEffect(
                            this.x + this.size_W/2,this.y + this.size_H/2,120),
                    new ExplosionEffect(
                            this.x + (int)(this.size_W * 0.25),this.y + (int)(this.size_H * 0.30),100),
                    new ExplosionEffect(
                            this.x + (int)(this.size_W * 0.75),this.y + (int)(this.size_H * 0.30),100),
                    new ExplosionEffect(
                            this.x + (int)(this.size_W * 0.25),this.y + (int)(this.size_H * 0.70),100),
                    new ExplosionEffect(
                            this.x + (int)(this.size_W * 0.75),this.y + (int)(this.size_H * 0.70),100),
                    new ExplosionEffect(
                            this.x + (int)(this.size_W * 0.50),this.y + (int)(this.size_H * 0.25),100),
                    new ExplosionEffect(
                            this.x + (int)(this.size_W * 0.50),this.y + (int)(this.size_H * 0.75),100)
            ));
        }
    }
}
