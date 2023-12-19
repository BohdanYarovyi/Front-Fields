package bin.programScenes;

import bin.GameDisplay;
import bin.GamePainter;
import bin.enemies.BossRed;
import bin.enemies.Enemy;
import bin.enemies.SpaceDestroyer;
import bin.enemies.Striker;
import bin.gameComponents.Bullet;
import bin.gameComponents.ExplosionEffect;
import bin.gameComponents.GameButton;
import bin.hero.Hero;
import bin.hero.UIDisplay;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;


public class GameScene {
    public static final Image BACKGROUND =
            new ImageIcon("src/bin/image/background/space.jpeg").getImage();
    public static GameButton pause =
            new GameButton(0,0, 50,50,"src/bin/image/buttons/home.png");
    // our hero
    public Hero hero = new Hero(300,600,80,90,
            100,4,6,
            100,10,15);

    public int CURRENT_LVL = 1;
    public int KILLED_ENEMIES = 0;
    public int POINTS = 0;

    public ArrayList<Bullet> bulletsOnBattlefield = new ArrayList<>();
    public final ArrayList<Bullet> deathBullets = new ArrayList<>();

    public ArrayList<Enemy> enemiesOnBattlefield = new ArrayList<>();
    public final ArrayList<Enemy> deathEnemies = new ArrayList<>();

    public ArrayList<ExplosionEffect> explosionsInGame = new ArrayList<>();
    public ArrayList<ExplosionEffect> deathExplosions = new ArrayList<>();

    public GameScene() {
        initGame();
        pause.addActionListener(a -> {
            GamePainter.status = ProgramStatus.PAUSE;
            GamePainter.isAdded = false;
        });
    }

    public void paintGame(Graphics g) {
        g.drawImage(BACKGROUND, 0, 0,
                GameDisplay.FRAME_WIDTH, GameDisplay.FRAME_HEIGHT, null);
        hero.paint(g);
        enemiesOnBattlefield.forEach(e -> e.paint(g));
        bulletsOnBattlefield.forEach(b -> b.paint(g));
        explosionsInGame.forEach(e -> e.paint(g));
        UIDisplay.paintUI(g,this);
    }

    public void removeFromField(){
        if (!deathBullets.isEmpty()) {
            bulletsOnBattlefield.removeAll(deathBullets);
            deathBullets.clear();
        }
        if (!deathEnemies.isEmpty()) {
            KILLED_ENEMIES += deathEnemies.size();
            deathEnemies.forEach(enemy -> POINTS += enemy.getPoint());
            enemiesOnBattlefield.removeAll(deathEnemies);
            deathEnemies.clear();
        }
        if (!deathExplosions.isEmpty()) {
            explosionsInGame.removeAll(deathExplosions);
            deathExplosions.clear();
        }
    }

    public void controller() {
        if (hero.getHealth() < 1){
            GamePainter.status = ProgramStatus.END_MENU;
            GamePainter.isAdded = false;
        }
        removeFromField();

        hero.controller(this);
        enemiesOnBattlefield.forEach(e -> e.controller(this));
        explosionsInGame.forEach(e -> e.controller(this));
        bulletsOnBattlefield.forEach(b -> b.controller(this));

        if (enemiesOnBattlefield.isEmpty()) {
            GamePainter.status = ProgramStatus.BONUS_MENU;
            CURRENT_LVL++;
            hero.setHealth(hero.getHealth() + 5);
            initGame();
            GamePainter.isAdded = false;
            GamePainter.upgradeScene = new UpgradeScene();
        }
    }

    public void initGame(){
        float modifier = (float)(1.0 + (0.05 * CURRENT_LVL));
        if(CURRENT_LVL % 10 == 0 && CURRENT_LVL != 0){
           //spawn Boss
            enemiesOnBattlefield.add(new BossRed(300, 150, 300, 200,
                    (int)(1000 * modifier),                 //health points
                    1,                                      //move speed
                    (int)(2 * modifier),                    //bullet speed
                    (int)(100 * modifier),                  //bullet damage
                    (int)(2 * modifier),                    //laser damage
                    (int)(30 / modifier),                   //regeneration speed
                    (int)(90 / modifier),                   //bullet coolDown
                    150));                                  //move amplitude

        }else if (CURRENT_LVL > 10 && CURRENT_LVL % 5 == 0 ){
            enemiesOnBattlefield.add(new SpaceDestroyer(100,90,80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(1 * modifier),                    //laser damage
                    (int)(60 / modifier),                   //regeneration spe
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude
            enemiesOnBattlefield.add(new SpaceDestroyer(300,90,80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(1 * modifier),                    //laser damage
                    (int)(60 / modifier),                   //regeneration spe
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude
            enemiesOnBattlefield.add(new SpaceDestroyer(500,90,80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(1 * modifier),                    //laser damage
                    (int)(60 / modifier),                   //regeneration spe
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude

        } else if (CURRENT_LVL == 1 || CURRENT_LVL % 2 == 0) {
            enemiesOnBattlefield.add(new Striker(100,90,80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(60 / modifier),                   //regeneration spe
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude
            enemiesOnBattlefield.add(new Striker(300,90,80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(60 / modifier),                   //regeneration spe
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude
            enemiesOnBattlefield.add(new Striker(500, 90, 80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(60 / modifier),                   //regeneration spe
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude

        } else {
            enemiesOnBattlefield.add(new Striker(100,90,80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(60 / modifier),                   //regeneration speed
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude
            enemiesOnBattlefield.add(new SpaceDestroyer(300,90,90,90,
                    (int)(80 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(1 * modifier),                    //laser damage
                    (int)(60 / modifier),                   //regeneration speed
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude
            enemiesOnBattlefield.add(new Striker(500,90,80,80,
                    (int)(70 * modifier),                   //health points
                    1,                                      //move speed
                    (int)(3 * modifier),                    //bullet speed
                    (int)(10 * modifier),                   //bullet damage
                    (int)(60 / modifier),                   //regeneration speed
                    (int)(80 / modifier),                   //bullet coolDown
                    50));                                   //move amplitude
        }
    }
}
