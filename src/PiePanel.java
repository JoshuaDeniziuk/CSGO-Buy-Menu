
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

class PiePanel extends JPanel implements MouseListener {

    //stores the guns and their index for the pie slice.
    private HashMap<Integer, Gun> guns = new HashMap<>();
    BufferedImage image;
    final int PAD = 30;
    Font font;
    NumberFormat nf;
    boolean showConstructionMarkers;

    BufferedImage mirage;

    public PiePanel() {
        font = new Font("lucida sans regular", Font.PLAIN, 20);
        nf = NumberFormat.getPercentInstance();
        showConstructionMarkers = true;
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                image = null;
            }
        });
        try {
            //add guns to a key-value map for reference.
            guns.put(0, new Gun("Glock-18", "200", "Pistol", "/glock.png"));
            guns.put(1, new Gun("Dual Barettas", "400", "Pistol", "/dualbarreta.png"));
            guns.put(2, new Gun("P250", "300", "Pistol", "/p.png"));
            guns.put(3, new Gun("CZ75-AUTO", "500", "Pistol", "/cz75.png"));
            guns.put(4, new Gun("Desert Eagle", "700", "Pistol", "/deserteagle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (image == null)
            createChartImage();
        g2.drawImage(image, 0, 0, this);
    }

    private void createChartImage() {
        int[] data = {100, 100, 100, 100, 100};
        Color[] color = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.PINK};
        int w = getWidth();
        int h = getHeight();
        int cx = w / 2;
        int cy = h / 2;
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("/mirage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new Color(175, 172, 5, 75));
        // draw circle
        int dia = Math.min(w, h) - 2 * PAD;
        g2.fill(new Ellipse2D.Double(cx - dia / 2, cy - dia / 2, dia, dia));
        double total = 0;
        for (int j = 0; j < data.length; j++)
            total += data[j];
        double theta = 0, phi = 0;
        double x, y;
        for (int j = 0; j < data.length; j++) {
            // draw leading edge of pie slice
            x = cx + (dia / 2) * Math.cos(theta);
            y = cy + (dia / 2) * Math.sin(theta);
            g2.setColor(Color.BLACK);
            g2.draw(new Line2D.Double(cx, cy, x, y));
            // angle of pie slice
            phi = (data[j] / total) * 2 * Math.PI;

            // mark text center on centerline
            x = cx + (9 * dia / 24) * Math.cos(theta + phi / 2);
            y = cy + (9 * dia / 24) * Math.sin(theta + phi / 2);

            //get the gun from the index map.
            Gun gun = guns.get(j);
            //found resize method on google for buffered images.
            BufferedImage img = resize(gun.getImg(), 50, 50);
            int imgWidth = img.getWidth();
            int sx = (int) (x - imgWidth / 2);
            int sy = (int) (y - imgWidth / 2);
            gun.setX(sx);
            gun.setY(sy);
            g2.drawImage(img, sx, sy, this);

            theta += phi;
        }
        g2.dispose();
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        //loop over guns with a 'foreach' - googled.
        for(Gun gun : guns.values()) {
            //create a square with the created x and y
            Rectangle bounds = new Rectangle(gun.getX(), gun.getY(), 50, 50);
            //if the square contains the mouse point
            if(bounds.contains(point)) {
                //display the new text
                ActiveWeaponDisplay.setDisplay(gun.name);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}