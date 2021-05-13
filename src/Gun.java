/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Joshua Deniziuk
 */
public class Gun {

    public String name, price, description, resource;

    public BufferedImage img;

    private int x = 0, y = 0;

    public Gun(String name, String price, String description, String resource) throws IOException {
        this.name = name;
        this.price = price;
        this.description = description;
        this.resource = resource;
        this.img = ImageIO.read(this.getClass().getResourceAsStream(resource));
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public String getResource() { return this.resource; }

    public int setX(int x) { return this.x = x; }

    public int setY(int y) { return this.y = y; }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public BufferedImage getImg() {
        return this.img;
    }

}
