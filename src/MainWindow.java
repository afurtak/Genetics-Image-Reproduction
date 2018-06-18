import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainWindow extends JFrame {

    private static final int MARGIN = 30;

    private BufferedImage originalImage;

    private ImagePanel bestImagePanel;
    private ImagePanel currentImagePanel;
    private ImagePanel originalImagePanel;

    ImageReproduce imageReproduce;

    public MainWindow(String path) {
        super("Generics Image Reproduction");
        try {
            originalImage = ImageIO.read(new File(path));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        setSize(3 * originalImage.getWidth() + MARGIN, originalImage.getHeight() + 2 * MARGIN);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        drawOriginalImage(originalImage);

        imageReproduce = new ImageReproduce(path, this);
        imageReproduce.reproduce(100, 45, 10, 0.1f);
    }

    public void drawOriginalImage(BufferedImage image) {
        if (originalImagePanel == null) {
            originalImagePanel = new ImagePanel(image);
            originalImagePanel.setBounds(MARGIN / 3, MARGIN / 3, originalImage.getWidth(), originalImage.getHeight());
            originalImagePanel.setVisible(true);
            add(originalImagePanel);
        }

        originalImagePanel.repaint(image);
    }

    public void drawCurrentImage(BufferedImage image) {
        if (currentImagePanel == null) {
            currentImagePanel = new ImagePanel(image);
            currentImagePanel.setBounds((2 * MARGIN) / 3 + originalImage.getWidth(),  MARGIN / 3, originalImage.getWidth(), originalImage.getHeight());
            currentImagePanel.setVisible(true);
            add(currentImagePanel);
        }
        currentImagePanel.repaint(image);
    }

    public void drawBestImage(BufferedImage image) {
        if (bestImagePanel == null) {
            bestImagePanel = new ImagePanel(image);
            bestImagePanel.setBounds((3 * MARGIN) / 3 + 2 * originalImage.getWidth(), MARGIN / 3, originalImage.getWidth(), originalImage.getHeight());
            bestImagePanel.setVisible(true);
            add(bestImagePanel);
        }

        bestImagePanel.repaint(image);
    }
}

class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    public void repaint(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null)
            g.drawImage(image, 0, 0, this);
    }
}
