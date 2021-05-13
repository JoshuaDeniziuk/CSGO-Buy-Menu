/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import classes

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Joshua Deniziuk
 */

public class RootFrame {//class

    public static void main(String[] args) throws IOException {//main
                               
                                /* 
     By making the instance of the class itself, the consructor will be 
     called in which we can set up our key GUI components from the first line
                                */

        JFrame frame = new JFrame("CSGO: Buy Menu");

//Basic JFrame settings                        

        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 700));
        frame.setPreferredSize(new Dimension(700, 700));

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(2, 1));

        rootPanel.add(new PiePanel());
//adding ImageDisplayPanel
//adding ActiveWeaponDisplay
        rootPanel.add(new ActiveWeaponDisplay());

        frame.add(rootPanel);
        frame.setVisible(true);
        frame.pack();

    }//main

}//class

    

