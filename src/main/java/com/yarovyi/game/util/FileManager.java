package com.yarovyi.game.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FileManager {
    public static final ImageIcon GAME_ICON = new ImageIcon();
    public static final ImageIcon BACKGROUND = new ImageIcon();
    public static final ImageIcon BACKGROUND_ALPHA = new ImageIcon();
    public static final ImageIcon TITLE = new ImageIcon();
    public static final ImageIcon COIN = new ImageIcon();

    public static final ImageIcon HERO_IDLE = new ImageIcon();
    public static final ImageIcon HERO_LEFT = new ImageIcon();
    public static final ImageIcon HERO_RIGHT = new ImageIcon();

    public static final ImageIcon BOSS_IDLE = new ImageIcon();
    public static final ImageIcon BOSS_DAMAGED = new ImageIcon();
    public static final ImageIcon SPACEDESTROYER_IDLE = new ImageIcon();
    public static final ImageIcon SPACEDESTROYER_DAMAGED = new ImageIcon();
    public static final ImageIcon STRIKER_IDLE = new ImageIcon();
    public static final ImageIcon STRIKER_DAMAGED = new ImageIcon();

    public static final ImageIcon PLAY = new ImageIcon();
    public static final ImageIcon RETRY = new ImageIcon();
    public static final ImageIcon EXIT = new ImageIcon();
    public static final ImageIcon MENU = new ImageIcon();
    public static final ImageIcon HOME = new ImageIcon();
    public static final ImageIcon CONTINUE = new ImageIcon();

    public static final ImageIcon GREEN_BULLET = new ImageIcon();
    public static final ImageIcon RED_BULLET = new ImageIcon();

    public static final ImageIcon EFFECT_EXPLOSION_1 = new ImageIcon();
    public static final ImageIcon EFFECT_EXPLOSION_2 = new ImageIcon();
    public static final ImageIcon EFFECT_EXPLOSION_3 = new ImageIcon();
    public static final ImageIcon EFFECT_EXPLOSION_4 = new ImageIcon();
    public static final ImageIcon EFFECT_EXPLOSION_5 = new ImageIcon();
    public static final ImageIcon EFFECT_EXPLOSION_6 = new ImageIcon();
    public static final ImageIcon EFFECT_EXPLOSION_7 = new ImageIcon();

    public static final ImageIcon MODIFIERS_LASER = new ImageIcon();
    public static final ImageIcon MODIFIERS_DOUBLE_SHOT = new ImageIcon();
    public static final ImageIcon MODIFIERS_TWO_GUNS = new ImageIcon();
    public static final ImageIcon MODIFIERS_VAMPIRISM = new ImageIcon();
    public static final ImageIcon MODIFIERS_HEALTH = new ImageIcon();
    public static final ImageIcon MODIFIERS_DAMAGE = new ImageIcon();
    public static final ImageIcon MODIFIERS_BULLET_SPEED = new ImageIcon();
    public static final ImageIcon MODIFIERS_MOVE_SPEED = new ImageIcon();
    public static final ImageIcon MODIFIERS_REGENERATION_SPEED = new ImageIcon();
    public static final ImageIcon MODIFIERS_COOL_DOWN = new ImageIcon();
    public static final ImageIcon MODIFIERS_HEALTH_MYTHICAL = new ImageIcon();
    public static final ImageIcon MODIFIERS_DAMAGE_MYTHICAL = new ImageIcon();


    static {
        GAME_ICON.setImage(loadImage("image/persons/hero/hero1.png"));
        BACKGROUND.setImage(loadImage("image/background/space.jpeg"));
        BACKGROUND_ALPHA.setImage(loadImage("image/background/alpha_background.png"));
        TITLE.setImage(loadImage("image/title.png"));
        COIN.setImage(loadImage("image/coin.png"));

        HERO_IDLE.setImage(loadImage("image/persons/hero/hero1.png"));
        HERO_LEFT.setImage(loadImage("image/persons/hero/hero1_left.png"));
        HERO_RIGHT.setImage(loadImage("image/persons/hero/hero1_right.png"));

        BOSS_IDLE.setImage(loadImage("image/persons/enemy/boss.png"));
        BOSS_DAMAGED.setImage(loadImage("image/persons/enemy/boss_red.png"));
        SPACEDESTROYER_IDLE.setImage(loadImage("image/persons/enemy/spaceDestroyer.png"));
        SPACEDESTROYER_DAMAGED.setImage(loadImage("image/persons/enemy/spaceDestroyer_red.png"));
        STRIKER_IDLE.setImage(loadImage("image/persons/enemy/striker.png"));
        STRIKER_DAMAGED.setImage(loadImage("image/persons/enemy/striker_red.png"));

        PLAY.setImage(loadImage("image/buttons/play.png"));
        RETRY.setImage(loadImage("image/buttons/new_game.png"));
        EXIT.setImage(loadImage("image/buttons/quit.png"));
        MENU.setImage(loadImage("image/buttons/menu.png"));
        HOME.setImage(loadImage("image/buttons/home.png"));
        CONTINUE.setImage(loadImage("image/buttons/continue.png"));

        GREEN_BULLET.setImage(loadImage("image/bullets/greenPlasma_oval.png"));
        RED_BULLET.setImage(loadImage("image/bullets/redPlasma_oval.png"));

        EFFECT_EXPLOSION_1.setImage(loadImage("image/effects/explosion/1.png"));
        EFFECT_EXPLOSION_2.setImage(loadImage("image/effects/explosion/2.png"));
        EFFECT_EXPLOSION_3.setImage(loadImage("image/effects/explosion/3.png"));
        EFFECT_EXPLOSION_4.setImage(loadImage("image/effects/explosion/4.png"));
        EFFECT_EXPLOSION_5.setImage(loadImage("image/effects/explosion/5.png"));
        EFFECT_EXPLOSION_6.setImage(loadImage("image/effects/explosion/6.png"));
        EFFECT_EXPLOSION_7.setImage(loadImage("image/effects/explosion/7.png"));

        MODIFIERS_LASER.setImage(loadImage("image/modifiers/laser.png"));
        MODIFIERS_DOUBLE_SHOT.setImage(loadImage("image/modifiers/doubleShot.png"));
        MODIFIERS_TWO_GUNS.setImage(loadImage("image/modifiers/twoGuns.png"));
        MODIFIERS_VAMPIRISM.setImage(loadImage("image/modifiers/vampirism.png"));
        MODIFIERS_HEALTH.setImage(loadImage("image/modifiers/hp.png"));
        MODIFIERS_DAMAGE.setImage(loadImage("image/modifiers/damage.png"));
        MODIFIERS_BULLET_SPEED.setImage(loadImage("image/modifiers/bulletSpeed.png"));
        MODIFIERS_MOVE_SPEED.setImage(loadImage("image/modifiers/moveSpeed.png"));
        MODIFIERS_REGENERATION_SPEED.setImage(loadImage("image/modifiers/regenerationSpeed.png"));
        MODIFIERS_COOL_DOWN.setImage(loadImage("image/modifiers/coolDown.png"));
        MODIFIERS_HEALTH_MYTHICAL.setImage(loadImage("image/modifiers/hp_mythical.png"));
        MODIFIERS_DAMAGE_MYTHICAL.setImage(loadImage("image/modifiers/damage_mythical.png"));
    }

    private static Image loadImage(String path) {
        ClassLoader classLoader = FileManager.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Image %s not loaded%n".formatted(path));
            } else {
                return ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
