package com.yarovyi.game.programScenes;

import com.yarovyi.game.GameDisplay;
import com.yarovyi.game.GamePainter;
import com.yarovyi.game.gameComponents.GameButton;
import com.yarovyi.game.util.FileManager;

import javax.swing.*;
import java.awt.*;

public class MenuScene {
    public static Image background = FileManager.BACKGROUND.getImage();
    public static GameButton playBtn =
            new GameButton(150,280, 300, 80, FileManager.PLAY);
    public static GameButton exitBtn =
            new GameButton(150,380, 300, 80, FileManager.EXIT);

    public MenuScene() {
        playBtn.addActionListener(a ->{
            GamePainter.status = ProgramStatus.GAME;
            GamePainter.gameScene = new GameScene();
            GameDisplay.frame.requestFocus();
            GamePainter.isAdded = false;
        });
        exitBtn.addActionListener(a -> System.exit(0));
    }
    public void paint(Graphics g){
        g.drawImage(background, 0, 0, GameDisplay.FRAME_WIDTH, GameDisplay.FRAME_HEIGHT, null);
        g.setColor(Color.WHITE);
        g.drawImage(FileManager.TITLE.getImage(),30,100,520,120, null);
    }
}
