import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageWindow extends JFrame {

    private static final int MARGIN = 20;

    private BufferedImage image;

    public ImageWindow(String title, BufferedImage image) {
        super(title);
        this.image = image;
        setSize(image.getWidth() + MARGIN, image.getHeight() + (int)(MARGIN * 1.5));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, MARGIN / 2, MARGIN ,this);
    }
}
