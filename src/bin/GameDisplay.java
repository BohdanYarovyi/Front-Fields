package bin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * FrontFields
 * Гру створив студент групи КНД-12 Яровий Богдан
 * 31.05.2023
 */

public class GameDisplay{
    static final Image gameIcon = new ImageIcon("src/bin/image/persons/hero/hero1.png").getImage();
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 800;
    public static GamePainter game;
    public static JFrame frame;

    public static void main(String[] args) { // створюємо фрейм
        frame = new JFrame("FrontFields");
        game = new GamePainter();
        frame.setIconImage(gameIcon);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        addListeners();

        frame.add(game);

        frame.setVisible(true);
    }

    public static void addListeners(){
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GamePainter.gameScene.hero.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                GamePainter.gameScene.hero.mouseReleased(e);
            }
        });
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                GamePainter.gameScene.hero.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GamePainter.gameScene.hero.keyReleased(e);
            }
        });
    }
}

//   new Enemy(300,650,120,120,1,2,1,1,1,1,1, 100);
//   public static final Color healthBorder = new Color(5, 160,50);
//   public static final Color healthFiller = new Color(50, 230,10);
