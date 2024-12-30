package com.yarovyi.game.programScenes;

import com.yarovyi.game.GameDisplay;
import com.yarovyi.game.GamePainter;
import com.yarovyi.game.gameComponents.GameButton;
import com.yarovyi.game.util.FileManager;

import javax.swing.*;
import java.awt.*;

public class EndScene {
    public static Image background = FileManager.BACKGROUND.getImage();
    public static GameButton retryBtn =
            new GameButton(150,280, 300, 80, FileManager.RETRY);
    public static GameButton menuBtn =
            new GameButton(150,380, 300, 80, FileManager.MENU);
    public static GameButton exitBtn =
            new GameButton(150,480, 300, 80, FileManager.EXIT);

    public EndScene() {
        retryBtn.addActionListener(a ->{
            GamePainter.status = ProgramStatus.GAME;
            GamePainter.gameScene = new GameScene();
            GameDisplay.frame.requestFocus();
            GamePainter.isAdded = false;
        });
        menuBtn.addActionListener(a -> {
            GamePainter.status = ProgramStatus.MENU;
            GamePainter.isAdded = false;
        });
        exitBtn.addActionListener(a -> System.exit(0));
    }

    public void paint(Graphics g){
        g.drawImage(background, 0, 0, GameDisplay.FRAME_WIDTH, GameDisplay.FRAME_HEIGHT, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("", Font.BOLD, 80));
        g.drawString("The end!", 135,200);

        g.setFont(new Font("", Font.BOLD, 30));
        g.drawString("Wave: " + GamePainter.gameScene.CURRENT_LVL ,180, 610);
        g.drawString("Killed enemy: " + GamePainter.gameScene.KILLED_ENEMIES ,180, 650);
        g.drawImage(FileManager.COIN.getImage(),
                180,665,30,30, null);
        g.drawString("" + GamePainter.gameScene.POINTS ,220, 690);
    }
}
