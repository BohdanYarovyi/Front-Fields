package bin.programScenes;

import bin.GameDisplay;
import bin.GamePainter;
import bin.gameComponents.GameButton;

import javax.swing.*;
import java.awt.*;

public class EndScene {
    public static Image background = new ImageIcon("src/bin/image/background/space.jpeg").getImage();
    public static GameButton retryBtn =
            new GameButton(150,280, 300, 80, "src/bin/image/buttons/new_game.png");
    public static GameButton menuBtn =
            new GameButton(150,380, 300, 80, "src/bin/image/buttons/menu.png");
    public static GameButton exitBtn =
            new GameButton(150,480, 300, 80, "src/bin/image/buttons/quit.png");

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
        g.drawImage(new ImageIcon("src/bin/image/coin.png").getImage(),
                180,665,30,30, null);
        g.drawString("" + GamePainter.gameScene.POINTS ,220, 690);
    }
}
