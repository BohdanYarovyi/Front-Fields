package bin.programScenes;

import bin.GameDisplay;
import bin.GamePainter;
import bin.gameComponents.GameButton;

import javax.swing.*;
import java.awt.*;

public class PauseScene {
    public static Image alphaBackground =
            new ImageIcon("src/bin/image/background/alpha_background.png").getImage();
    public static GameButton continueBtn =
            new GameButton(150,150, 300, 80, "src/bin/image/buttons/continue.png");
    public static GameButton menuBtn =
            new GameButton(150,270, 300, 80, "src/bin/image/buttons/menu.png");

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
