/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Joshua Deniziuk
 */
public class ActiveWeaponDisplay extends JPanel {//class

    private static String defaultText = "Active Weapon: ";

    private static JLabel activeWeapon = new JLabel(defaultText);

    private BufferedImage background;

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.background, 0, 0, this);
    }

    public ActiveWeaponDisplay() throws IOException {
        this.background = ImageIO.read(this.getClass().getResourceAsStream("/terrorist.png"));
        add(activeWeapon);
    }//close cons

    public static void setDisplay(String text) {
        activeWeapon.setText(defaultText + text);
    }

}//close outer class


