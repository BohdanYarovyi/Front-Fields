package bin.enemies;

import bin.gameComponents.Bullet;
import bin.gameComponents.ExplosionEffect;
import bin.gameComponents.HitBox;
import bin.programScenes.GameScene;

import java.awt.*;

public abstract class Enemy {
    public static final Color healthFiller = new Color(225, 50,50);
    public static final String nameClass = "ENEMY";
    protected static int countOf = 0;
    public final int id;
    public Image imagePNG;
    public Image imageWithoutFilter;
    public Image imageRedFilter;
    public final int size_W;
    public final int size_H;
    protected final HitBox hitBox;

    //starting parameters
    protected final int startHealth;
    protected final int startMoveSpeed;
    protected final int startBulletSpeed;
    protected final int startDamage;

    //current parameters
    protected int point;
    protected int x;
    protected int y;
    protected int y_finallyPosition;
    protected int health;
    protected int damage;
    protected int moveSpeed;
    protected int bulletSpeed;
    protected int coolDown;
    protected int regenerationSpeed;

    //timers
    protected int regenerationTimer;
    protected int rechargeTimer;
    protected int moveAmplitude;

    public Enemy(int x,
                 int y,
                 int size_W,
                 int size_H,
                 int startHealth,
                 int startMoveSpeed,
                 int startBulletSpeed,
                 int startDamage,
                 int regenerationSpeed,
                 int coolDown,
                 int moveAmplitude){
        this.size_W = size_W;
        this.size_H = size_H;
        this.id = ++countOf;
        this.startHealth = startHealth;
        this.startMoveSpeed = startMoveSpeed;
        this.startBulletSpeed = startBulletSpeed;
        this.startDamage = startDamage;
        this.regenerationSpeed = regenerationSpeed;
        this.coolDown = coolDown;
        this.moveAmplitude = moveAmplitude;

        this.x = x - size_W/2;
        this.y = -this.size_H;
        this.y_finallyPosition = y - size_H/2;
        this.health = startHealth;
        this.damage = startDamage;
        this.moveSpeed = startMoveSpeed;
        this.bulletSpeed = startBulletSpeed;
        this.rechargeTimer = coolDown;
        this.regenerationTimer = regenerationSpeed;

        this.hitBox = new HitBox(this.x, this.y, this.size_W, this.size_H);
    }

    int xRight;
    int xLeft;
    boolean isCreated = false;
    boolean fromRight = false;

    public void move(){
        if(y < y_finallyPosition){
            y += (moveSpeed/5) + 1;
            return;
        }
        if(!isCreated){
            xRight = x + moveAmplitude;
            xLeft = x - moveAmplitude;
            isCreated = true;
        }
        if(!fromRight && x < xRight){
            x += moveSpeed;
        }else{
            fromRight = true;
        }
        if (fromRight && x > xLeft) {
            x -= moveSpeed;
        }else{
            fromRight = false;
        }
    }

    public void controller(GameScene gc){
        hitBox.setCords(this.x, this.y, this.size_W, this.size_H);
        move();
        showDamage();
        regeneration();
        shoot(gc);
    }

    protected int timerForIMG = 0;

    public void showDamage(){
      if (timerForIMG > 1) {
          imagePNG = imageRedFilter;
          timerForIMG--;
      }else{
          imagePNG = imageWithoutFilter;
      }
    }

    public void paint(Graphics g){
        g.drawImage(imagePNG, x, y,size_W,size_H, null);
        paintHealthBar(g);
//        showHitBox(g);
    }

    public void paintHealthBar(Graphics g){
        float coefficientHP = (float)size_W / startHealth;
        g.setColor(healthFiller);
        g.fillRect(this.x, this.y - 20, (int)(coefficientHP * health) , 10);
        g.drawString("" + this.health,this.x + (int)(this.size_W*0.4),this.y - 25);
    }

    public void shoot(GameScene gc){
        if(rechargeTimer < 1){
            gc.bulletsOnBattlefield.add(
                    new Bullet(this.x + this.size_W/2,this.y + this.size_H,5,10,
                            this.damage,this.bulletSpeed,nameClass)
            );
            rechargeTimer = coolDown;
        }else{
            rechargeTimer--;
        }
    }

    public void regeneration(){
        if (this.regenerationTimer < 1) {
            if ((this.health + 2) > this.startHealth){
                this.health = this.startHealth;
                return;
            }
            this.health += 2;
            this.regenerationTimer = this.regenerationSpeed;
        }else{
            this.regenerationTimer--;
        }
    }

    public void isDamaged(int damage, GameScene gc){
        health -= damage;
        timerForIMG = 10;
        if (health < 1) {
            gc.deathEnemies.add(this);
            gc.explosionsInGame.add(new ExplosionEffect(
                    this.x + this.size_W/2,this.y + this.size_H/2,70)
            );
        }
    }

    public String toString(){
        return "ID:" + id + " " + getClass();
    }

    public void showHitBox(Graphics g){
        g.setColor(Color.white);
        g.drawRect(hitBox.aX, hitBox.aY, hitBox.bX - hitBox.aX, hitBox.bY - hitBox.aY);
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public int getPoint() {
        return point;
    }
}