package bin.hero;

import bin.GameDisplay;
import bin.programScenes.GameScene;

import javax.swing.*;
import java.awt.*;

public class UIDisplay {

    public static void paintUI(Graphics g, GameScene gc){
        paintHealthBar(g, gc);
        paintReloadBar(g, gc);
        paintStatistics(g, gc);
    }

    private static void paintHealthBar(Graphics g, GameScene gc){
        float coefficientHP = 100.0f / gc.hero.getStartHealth();
            g.setFont(gc.hero.healthFont);
        if(gc.hero.getHealth() < gc.hero.getStartHealth() * 0.2){
            g.setColor(Color.RED);
        }else{
            g.setColor(Hero.healthFiller);
        }
        g.fillRect(21, GameDisplay.FRAME_HEIGHT - 59,
                (int)(coefficientHP * gc.hero.getHealth()) * 2 , 18);
        g.setColor(Hero.healthBorder);
        g.drawRect(20, GameDisplay.FRAME_HEIGHT - 60,
                202 , 20);
        g.setColor(Color.WHITE);
        g.drawString("" + gc.hero.getHealth(), 25, GameDisplay.FRAME_HEIGHT - 45);
    }

    private static void paintReloadBar(Graphics g, GameScene gc){
        float coefficientRepairBarLength = (float)(GameDisplay.FRAME_HEIGHT * 0.33) / gc.hero.getCoolDown();
        g.setColor(Color.ORANGE);
        g.fillRect(0, GameDisplay.FRAME_HEIGHT - (int)(coefficientRepairBarLength * gc.hero.getRechargeTimer()),
                6, (int)(gc.hero.getRechargeTimer() * coefficientRepairBarLength));
        g.fillRect(GameDisplay.FRAME_WIDTH - 20,
                GameDisplay.FRAME_HEIGHT - (int)(coefficientRepairBarLength * gc.hero.getRechargeTimer()),
                6, (int)(gc.hero.getRechargeTimer() * coefficientRepairBarLength));
    }

    private static void paintStatistics(Graphics g, GameScene gc){
        g.drawImage(new ImageIcon("src/bin/image/coin.png").getImage(),
                465, 720, 30, 30, null);
        g.setFont(new Font("", Font.BOLD, 30));
        g.drawString("" + gc.POINTS, 500, 745);
        g.setColor(Color.WHITE);
        if(gc.CURRENT_LVL % 10 == 0){
            g.drawString("BOSS " + gc.CURRENT_LVL + " LVL", 250, 750);
        }else{
            g.drawString("Wave " + gc.CURRENT_LVL, 250, 750);
        }
    }
}
