package bin.gameComponents;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    public GameButton(int x,
                      int y,
                      int width,
                      int height,
                      String image)
    {
        setBounds(x,y,width,height);
        setIcon(new ImageIcon(image));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public GameButton(int x,
                      int y,
                      int width,
                      int height,
                      ImageIcon img)
    {

        setIcon(img);
        setBounds(x,y,width,height);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}