package bin.programScenes;

import bin.GameDisplay;
import bin.GamePainter;
import bin.gameComponents.GameButton;

import javax.swing.*;
import java.awt.*;



public class MenuScene {
    public static Image background = new ImageIcon("src/bin/image/background/space.jpeg").getImage();
    public static GameButton playBtn =
            new GameButton(150,280, 300, 80, "src/bin/image/buttons/play.png");
    public static GameButton exitBtn =
            new GameButton(150,380, 300, 80, "src/bin/image/buttons/quit.png");

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
        g.drawImage(new ImageIcon("src/bin/image/title.png").getImage(),30,100,520,120, null);
    }
}
