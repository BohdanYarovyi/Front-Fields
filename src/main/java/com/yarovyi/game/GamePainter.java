package com.yarovyi.game;

import com.yarovyi.game.programScenes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GamePainter extends JPanel {
    public static ProgramStatus status = ProgramStatus.MENU;
    public final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1); //1000/60 = 17
    public static GameScene gameScene = new GameScene();
    public static MenuScene menu = new MenuScene();
    public static PauseScene pause = new PauseScene();
    public static EndScene endScene = new EndScene();
    public static UpgradeScene upgradeScene = new UpgradeScene();

    public GamePainter() {
        setLayout(null);
        executor.scheduleAtFixedRate(this::updateGame, 0, 17, TimeUnit.MILLISECONDS);
    }

    public static boolean isAdded = false;

    private void updateGame() {
        repaint();
        switch(status){
            case MENU -> {
                if (!isAdded) {
                    removeBtn();
                    add(MenuScene.playBtn);
                    add(MenuScene.exitBtn);
                    isAdded = true;
                }
            }
            case GAME -> {
                if (!isAdded) {
                    removeBtn();
                    add(GameScene.pause);
                    isAdded = true;
                }
                gameScene.controller();
            }
            case PAUSE -> {
                if (!isAdded) {
                    removeBtn();
                    add(PauseScene.continueBtn);
                    add(PauseScene.menuBtn);
                    isAdded = true;
                }
            }
            case END_MENU -> {
                if (!isAdded) {
                    removeBtn();
                    add(EndScene.retryBtn);
                    add(EndScene.menuBtn);
                    add(EndScene.exitBtn);
                    isAdded = true;
                }
            }
            case BONUS_MENU -> {
                if (!isAdded) {
                    removeBtn();
                    add(upgradeScene.centerBonusField);
                    add(upgradeScene.leftBonusField);
                    add(upgradeScene.rightBonusField);
                    isAdded = true;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        switch(status){
            case MENU -> menu.paint(g);
            case GAME -> gameScene.paintGame(g);
            case PAUSE -> {
                gameScene.paintGame(g);
                pause.paint(g);
            }
            case END_MENU -> endScene.paint(g);
            case BONUS_MENU -> {
                gameScene.paintGame(g);
                upgradeScene.paint(g);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }


    public void removeBtn(){
        remove(MenuScene.playBtn);
        remove(MenuScene.exitBtn);
        remove(PauseScene.continueBtn);
        remove(PauseScene.menuBtn);
        remove(GameScene.pause);
        remove(EndScene.retryBtn);
        remove(EndScene.menuBtn);
        remove(EndScene.exitBtn);
        remove(upgradeScene.leftBonusField);
        remove(upgradeScene.centerBonusField);
        remove(upgradeScene.rightBonusField);
    }
}