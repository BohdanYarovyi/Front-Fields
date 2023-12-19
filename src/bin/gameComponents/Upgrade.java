package bin.gameComponents;

import bin.GamePainter;

import javax.swing.*;
import java.util.Random;


public class Upgrade {
    public static boolean heroHaveAllBonuses = false;
    public ImageIcon img;
    private Random random = new Random();
    private boolean isUltimate;
    int randomOption;
    int randomSubOption;

    public Upgrade(boolean isUltimate){
        this.isUltimate = isUltimate;
        this.randomOption = random.nextInt(1,7);
        this.randomSubOption = random.nextInt(1,4);
        if(isUltimate && GamePainter.gameScene.CURRENT_LVL % 10 == 1 &&
                !heroHaveAllBonuses && GamePainter.gameScene.CURRENT_LVL != 1){
            if(!GamePainter.gameScene.hero.isLaser()){
                img = new ImageIcon("src/bin/image/modifiers/laser.png");
            }else if(!GamePainter.gameScene.hero.isDoubleShot()){
                img = new ImageIcon("src/bin/image/modifiers/doubleShot.png");
            }else if(!GamePainter.gameScene.hero.isTwoGuns()){
                img = new ImageIcon("src/bin/image/modifiers/twoGuns.png");
            }else if(!GamePainter.gameScene.hero.isVampirism()){
                img = new ImageIcon("src/bin/image/modifiers/vampirism.png");
            }
        }else{
            switch(randomOption){
                case 1 -> {img = new ImageIcon("src/bin/image/modifiers/hp.png");}
                case 2 -> {img = new ImageIcon("src/bin/image/modifiers/damage.png");}
                case 3 -> {img = new ImageIcon("src/bin/image/modifiers/bulletSpeed.png");}
                case 4 -> {img = new ImageIcon("src/bin/image/modifiers/regenerationSpeed.png");}
                case 5 -> {img = new ImageIcon("src/bin/image/modifiers/moveSpeed.png");}
                case 6 -> {
                    switch (randomSubOption){
                        case 1 -> {img = new ImageIcon("src/bin/image/modifiers/coolDown.png");}
                        case 2 -> {img = new ImageIcon("src/bin/image/modifiers/hp_mythical.png");}
                        case 3 -> {img = new ImageIcon("src/bin/image/modifiers/damage_mythical.png");}
                    }
                }
            }
        }
    }

    public void controller(){
        if(isUltimate && GamePainter.gameScene.CURRENT_LVL % 10 == 1 &&
                !heroHaveAllBonuses && GamePainter.gameScene.CURRENT_LVL != 1){
            if(!GamePainter.gameScene.hero.isLaser()){
                addLaser();
            }else if(!GamePainter.gameScene.hero.isDoubleShot()){
                setDoubleShot();
            }else if(!GamePainter.gameScene.hero.isTwoGuns()){
                setTwoGuns();
            }else if(!GamePainter.gameScene.hero.isVampirism()){
                System.out.println("yeah");
                setVampirism();
                heroHaveAllBonuses = true;
            }
        }else{
            switch(randomOption){
                case 1 -> {addHP();}
                case 2 -> {addDamage();}
                case 3 -> {addBulletSpeed();}
                case 4 -> {lowerRegenerationSpeed();}
                case 5 -> {addMoveSpeed();}
                case 6 -> {
                    switch (randomSubOption){
                        case 1 -> {lowerCoolDown();}
                        case 2 -> {addHPMythical();}
                        case 3 -> {addDamageMythical();}
                    }
                }
            }
        }
    }

    public void addHP(){ // +5 HP
        int h = GamePainter.gameScene.hero.getStartHealth();
        GamePainter.gameScene.hero.setStartHealth(h + 5);
        GamePainter.gameScene.hero.setHealth(GamePainter.gameScene.hero.getHealth() + 5);
    }

    public void addHPMythical(){ // +10 HP
        int h = GamePainter.gameScene.hero.getStartHealth();
        GamePainter.gameScene.hero.setStartHealth(h + 10);
        GamePainter.gameScene.hero.setHealth(GamePainter.gameScene.hero.getHealth() + 10);
    }

    public void addDamage(){ // +2 damage
        int d = GamePainter.gameScene.hero.getDamage();
        GamePainter.gameScene.hero.setDamage(d + 2);
    }

    public void addDamageMythical(){ // +4 damage
        int d = GamePainter.gameScene.hero.getDamage();
        GamePainter.gameScene.hero.setDamage(d + 4);
    }

    public void addMoveSpeed(){ // +1 moveSpeed
        int mSpeed = GamePainter.gameScene.hero.getMoveSpeed();
        GamePainter.gameScene.hero.setMoveSpeed(mSpeed + 1);
    }

    public void lowerCoolDown(){ // -5% coolDown
        int coolDown = GamePainter.gameScene.hero.getCoolDown();
        GamePainter.gameScene.hero.setCoolDown((int)(coolDown * 0.95));
    }

    public void addBulletSpeed(){ // +1 bulletSpeed
        int bSpeed = GamePainter.gameScene.hero.getBulletSpeed();
        GamePainter.gameScene.hero.setBulletSpeed(bSpeed + 1);
    }

    public void lowerRegenerationSpeed(){ // -5% regenerationSpeed
        int regSpeed = GamePainter.gameScene.hero.getRegenerationSpeed();
        GamePainter.gameScene.hero.setRegenerationSpeed((int)(regSpeed * 0.95));
    }

    //one-time upgrades
    public void addLaser(){
        GamePainter.gameScene.hero.setLaser(true);
    }

    public void setDoubleShot(){
        GamePainter.gameScene.hero.setDoubleShot(true);
    }

    public void setTwoGuns(){
        GamePainter.gameScene.hero.setTwoGuns(true);
    }

    public void setVampirism(){
        GamePainter.gameScene.hero.setVampirism(true);
    }
}
