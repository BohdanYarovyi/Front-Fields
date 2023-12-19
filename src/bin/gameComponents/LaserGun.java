package bin.gameComponents;

import bin.GamePainter;

import java.awt.*;

public class LaserGun { //only fo enemy
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int laserDamage;

    private int laserWorkTime;
    private int laserTimer;
    private int laserCoolDown;
    private int rechargeTimer;
    private boolean isWork = false;

    public LaserGun(int x1,
                    int y1,
                    int laserDamage,
                    int laserWorkTime,
                    int laserCoolDown
    ){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1;
        this.y2 = 1000;
        this.laserDamage = laserDamage;
        this.laserWorkTime = laserWorkTime;
        this.laserCoolDown = laserCoolDown;
        this.rechargeTimer = this.laserCoolDown;
        this.laserTimer = this.laserWorkTime;
    }

    public void controller(int x, int y){
        setCoords(x,y);
        if(rechargeTimer < 1){
            isWork = true;
            laserTimer--;
            if (laserTimer < 1) {
                isWork = false;
                rechargeTimer = laserCoolDown;
            }
        }else{
            laserTimer = laserWorkTime;
            rechargeTimer--;
        }
    }

    public void paint(Graphics g){
        HitBox hero = GamePainter.gameScene.hero.getHitBox();
        if(isWork){
            if(checkOnHero()){
                g.setColor(Color.RED);
                g.drawLine(this.x1 - 2,this.y1,this.x2 - 2,hero.aY + 15);
                g.drawLine(this.x1 + 3,this.y1,this.x2 + 3,hero.aY + 15);
                g.setColor(Color.PINK);
                g.drawLine(this.x1 - 1,this.y1,this.x2 - 1,hero.aY + 15);
                g.drawLine(this.x1 + 2,this.y1,this.x2 + 2,hero.aY + 15);
                g.setColor(Color.WHITE);
                g.drawLine(this.x1,this.y1,this.x2,hero.aY + 15);
                g.drawLine(this.x1 + 1,this.y1,this.x2 + 1,hero.aY + 15);
                GamePainter.gameScene.hero.isDamaged(this.laserDamage);
                GamePainter.gameScene.explosionsInGame.
                        add(new ExplosionEffect(this.x2, hero.aY + 15, 30));
            }else{
                g.setColor(Color.RED);
                g.drawLine(this.x1 - 2,this.y1,this.x2 - 2,this.y2);
                g.drawLine(this.x1 + 3,this.y1,this.x2 + 3,this.y2);
                g.setColor(Color.PINK);
                g.drawLine(this.x1 - 1,this.y1,this.x2 - 1,this.y2);
                g.drawLine(this.x1 + 2,this.y1,this.x2 + 2,this.y2);
                g.setColor(Color.WHITE);
                g.drawLine(this.x1,this.y1,this.x2,this.y2);
                g.drawLine(this.x1 + 1,this.y1,this.x2 + 1,this.y2);
            }
        }
    }

    public boolean checkOnHero(){
        HitBox hero = GamePainter.gameScene.hero.getHitBox();
        return this.x1 > hero.aX && this.x1 < hero.bX;
    }

    public void setCoords(int x, int y){
        this.x1 = x;
        this.y1 = y;
        this.x2 = x1;
        this.y2 = 1000;
    }

    public boolean isWork() {
        return isWork;
    }

    public int getLaserTimer() {
        return laserTimer;
    }

    public int getRechargeTimer() {
        return rechargeTimer;
    }

    public int getLaserWorkTime() {
        return laserWorkTime;
    }

    public int getLaserCoolDown() {
        return laserCoolDown;
    }
}
