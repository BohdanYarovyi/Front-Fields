package bin.gameComponents;

import bin.programScenes.GameScene;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ExplosionEffect {
    public static final ArrayList<Image> imagesExplosion = new ArrayList<>(Arrays.asList(
            new ImageIcon("src/bin/image/effects/explosion/1.png").getImage(),
            new ImageIcon("src/bin/image/effects/explosion/2.png").getImage(),
            new ImageIcon("src/bin/image/effects/explosion/3.png").getImage(),
            new ImageIcon("src/bin/image/effects/explosion/4.png").getImage(),
            new ImageIcon("src/bin/image/effects/explosion/5.png").getImage(),
            new ImageIcon("src/bin/image/effects/explosion/6.png").getImage(),
            new ImageIcon("src/bin/image/effects/explosion/7.png").getImage()
    ));
    private Image currentImage;
    private int lifeTimer = 40;
    private int x, y;
    private int size;

    public ExplosionEffect(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void controller(GameScene gc){
        if (this.lifeTimer < 1) {
            gc.deathExplosions.add(this);
        }
        switch(lifeTimer){
            case 35 -> {currentImage = new ImageIcon(imagesExplosion.get(0)).getImage();}
            case 30 -> {currentImage = new ImageIcon(imagesExplosion.get(1)).getImage();}
            case 25 -> {currentImage = new ImageIcon(imagesExplosion.get(2)).getImage();}
            case 20 -> {currentImage = new ImageIcon(imagesExplosion.get(3)).getImage();}
            case 15 -> {currentImage = new ImageIcon(imagesExplosion.get(4)).getImage();}
            case 10 -> {currentImage = new ImageIcon(imagesExplosion.get(5)).getImage();}
            case 5 -> {currentImage = new ImageIcon(imagesExplosion.get(6)).getImage();}
        }
        lifeTimer--;
    }

    public void paint(Graphics g){
        g.drawImage(currentImage, this.x - this.size/2,
                this.y - this.size/2,this.size, this.size, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
