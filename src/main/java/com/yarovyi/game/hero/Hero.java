package com.yarovyi.game.hero;

import com.yarovyi.game.GameDisplay;
import com.yarovyi.game.gameComponents.Bullet;
import com.yarovyi.game.gameComponents.HitBox;
import com.yarovyi.game.programScenes.GameScene;
import com.yarovyi.game.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hero implements KeyListener {
    public static final Color healthBorder = new Color(5, 160,50);
    public static final Color healthFiller = new Color(50, 230,10);
    public final Font healthFont = new Font("", Font.BOLD, 20);
    public Image imagePNG = FileManager.HERO_IDLE.getImage();
    public final int size_W;
    public final int size_H;
    private final HitBox hitBox;

    //starting parameters
    private final int startMoveSpeed;
    private final int startBulletSpeed;
    private final int startDamage;

    //current parameters
    private String nameClass = "HERO";
    private int startHealth;
    private boolean mouseIsPressed = false;
    private int x;
    private int y;
    private int health;
    private int damage;
    private int moveSpeed;
    private int bulletSpeed;
    private int coolDown;
    private int regenerationSpeed;

    //timers
    private int regenerationTimer;
    private int rechargeTimer;

    //modifiers
    private boolean laser = false;
    private boolean doubleShot = false;
    private boolean twoGuns = false;
    private boolean vampirism = false;

    private Direction heroDirection = Direction.NONE;

    public Hero( int x,
                 int y,
                 int size_W,
                 int size_H,
                 int startHealth,
                 int startMoveSpeed,
                 int startBulletSpeed,
                 int startDamage,
                 int regenerationSpeed,
                 int coolDown)
    {
        this.size_W = size_W;
        this.size_H = size_H;
        this.startHealth = startHealth;
        this.startMoveSpeed = startMoveSpeed;
        this.startBulletSpeed = startBulletSpeed;
        this.startDamage = startDamage;
        this.regenerationSpeed = regenerationSpeed;
        this.coolDown = coolDown;

        this.x = x - size_W/2;
        this.y = y - size_H/2;
        this.health = startHealth;
        this.damage = startDamage;
        this.moveSpeed = startMoveSpeed;
        this.bulletSpeed = startBulletSpeed;
        this.rechargeTimer = coolDown;
        this.regenerationTimer = regenerationSpeed;

        this.hitBox = new HitBox(this.x, this.y, this.size_W, this.size_H);
    }


    public void controller(GameScene gc){
        hitBox.setCords(this.x, this.y, this.size_W, this.size_H);
        move();
        regeneration();
        rechargeGun(gc);
    }

    public void move(){
        switch (heroDirection) {
            case LEFT -> {
                if (x > 0) {
                    x -= moveSpeed;
                }
            }
            case RIGHT -> {
                if (x < GameDisplay.FRAME_WIDTH - this.size_W - 10) {
                    x += moveSpeed;
                }
            }
        }
    }

    public void paint(Graphics g){
        g.drawImage(imagePNG, x, y,size_W,size_H, null);
        if (laser) {
            g.setColor(Color.RED);
            g.drawLine(this.x + this.size_W/2,this.y, this.x + this.size_W/2,0);
        }
//        showHitBox(g);
    }

    private boolean isShoot = true;
    private int timerDoubleShot;

    public void shoot(GameScene gc){
        if (twoGuns) {
            gc.bulletsOnBattlefield.add(
                    new Bullet(this.x + (int)(this.size_W * 0.15),
                            this.y + 30, 6, 10,this.damage,
                            this.bulletSpeed,this.nameClass)
            );
            gc.bulletsOnBattlefield.add(
                    new Bullet(this.x + (int)(this.size_W * 0.85),
                            this.y + 30, 6, 10,this.damage,
                            this.bulletSpeed,this.nameClass)
            );
        }else {
            gc.bulletsOnBattlefield.add(
                    new Bullet(this.x + this.size_W/2,this.y,
                            6, 10,this.damage,
                            this.bulletSpeed,this.nameClass)
            );
        }
    }

    public void rechargeGun(GameScene gc){
        if(this.rechargeTimer < 1 && mouseIsPressed){
            shoot(gc);
            if(doubleShot){
                timerDoubleShot = 4;
                isShoot = false;
            }
            rechargeTimer = coolDown;
        }else{
            if(timerDoubleShot < 1 && !isShoot){
                shoot(gc);
                isShoot = true;
            }
            rechargeTimer--;
            timerDoubleShot--;
        }
    }

    public void regeneration(){
        if (this.regenerationTimer < 1) {
            if ((this.health + 2) > this.startHealth){
                this.health = this.startHealth;
                this.regenerationTimer = this.regenerationSpeed;
                return;
            }
            this.health++;
            this.regenerationTimer = this.regenerationSpeed;
        }else{
            this.regenerationTimer--;
        }
    }

    public void isDamaged(int damage){
        health -= damage;
    }

    public void showHitBox(Graphics g){
        g.setColor(Color.white);
        g.drawRect(hitBox.aX, hitBox.aY, hitBox.bX - hitBox.aX, hitBox.bY - hitBox.aY);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            heroDirection = Direction.LEFT;
            imagePNG = FileManager.HERO_LEFT.getImage();
        }
        if (key == KeyEvent.VK_RIGHT) {
            heroDirection = Direction.RIGHT;
            imagePNG = FileManager.HERO_RIGHT.getImage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        heroDirection = Direction.NONE;
        imagePNG = FileManager.HERO_IDLE.getImage();
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseIsPressed = true;
        }
    }

    public void mouseReleased(MouseEvent e){
        mouseIsPressed = false;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public int getStartHealth() {
        return startHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public int getRechargeTimer() {
        return rechargeTimer;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getRegenerationSpeed() {
        return regenerationSpeed;
    }

    public void setRegenerationSpeed(int regenerationSpeed) {
        this.regenerationSpeed = regenerationSpeed;
    }

    public void setStartHealth(int startHealth) {
        this.startHealth = startHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isLaser() {
        return laser;
    }

    public void setLaser(boolean laser) {
        this.laser = laser;
    }

    public boolean isDoubleShot() {
        return doubleShot;
    }

    public void setDoubleShot(boolean doubleShot) {
        this.doubleShot = doubleShot;
    }

    public boolean isTwoGuns() {
        return twoGuns;
    }

    public void setTwoGuns(boolean twoGuns) {
        this.twoGuns = twoGuns;
    }

    public boolean isVampirism() {
        return vampirism;
    }

    public void setVampirism(boolean vampirism) {
        this.vampirism = vampirism;
    }

}

enum Direction{
    LEFT,
    RIGHT,
    NONE
}

