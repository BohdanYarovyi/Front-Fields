package com.yarovyi.game.programScenes;

import com.yarovyi.game.GameDisplay;
import com.yarovyi.game.GamePainter;
import com.yarovyi.game.gameComponents.GameButton;
import com.yarovyi.game.util.FileManager;

import javax.swing.*;
import java.awt.*;

public class PauseScene {
    public static Image alphaBackground = FileManager.BACKGROUND_ALPHA.getImage();
    public static GameButton continueBtn =
            new GameButton(150,150, 300, 80, FileManager.CONTINUE);
    public static GameButton menuBtn =
            new GameButton(150,270, 300, 80, FileManager.MENU);

    public PauseScene() {
        continueBtn.addActionListener(a -> {
            GamePainter.status = ProgramStatus.GAME;
            GameDisplay.frame.requestFocus();
            GamePainter.isAdded = false;
        });
        menuBtn.addActionListener(a -> {
            GamePainter.status = ProgramStatus.MENU;
            GamePainter.isAdded = false;
        });
    }
    public void paint(Graphics g){
        g.drawImage(alphaBackground, 0, 0, GameDisplay.FRAME_WIDTH, GameDisplay.FRAME_HEIGHT, null);
    }
}
