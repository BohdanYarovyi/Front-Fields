package bin.programScenes;

import bin.GameDisplay;
import bin.GamePainter;
import bin.gameComponents.GameButton;
import bin.gameComponents.Upgrade;

import javax.swing.*;
import java.awt.*;

public class UpgradeScene {
    public static Image alphaBackground =
            new ImageIcon("src/bin/image/background/alpha_background.png").getImage();
    public Upgrade upgrade1 = new Upgrade(false);
    public Upgrade upgrade2 = new Upgrade(true);
    public Upgrade upgrade3 = new Upgrade(false);

    public GameButton leftBonusField = new GameButton(50,338,125,125, upgrade1.img);
    public GameButton centerBonusField = new GameButton(225,338,125,125, upgrade2.img);
    public GameButton rightBonusField = new GameButton(400,338,125,125, upgrade3.img);

    public UpgradeScene() {
        leftBonusField.addActionListener(e -> {
            GamePainter.status = ProgramStatus.GAME;
            upgrade1.controller();
            GameDisplay.frame.requestFocus();
            GamePainter.isAdded = false;
        });
        centerBonusField.addActionListener(e -> {
            GamePainter.status = ProgramStatus.GAME;
            upgrade2.controller();
            GameDisplay.frame.requestFocus();
            GamePainter.isAdded = false;
        });
        rightBonusField.addActionListener(e -> {
            GamePainter.status = ProgramStatus.GAME;
            upgrade3.controller();
            GameDisplay.frame.requestFocus();
            GamePainter.isAdded = false;
        });
    }

    public void paint(Graphics g){
        g.drawImage(alphaBackground, 0, 0,
                GameDisplay.FRAME_WIDTH, GameDisplay.FRAME_HEIGHT, null);
        g.setFont(new Font("", Font.BOLD, 60));
        g.drawString("Chose upgrade:", 70, 300);
    }
}
