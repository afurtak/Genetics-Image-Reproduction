import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainWindow extends JFrame implements ImageReproduceBuffer {

    private static final int MARGIN = 30;

    private BufferedImage originalImage;

    private ImagePanel bestImagePanel;
    private ImagePanel currentImagePanel;
    private ImagePanel originalImagePanel;

    private ImageReproduce imageReproduce;

    public MainWindow(File file) {
        super("Generics Image Reproduction");
        try {
            originalImage = ImageIO.read(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        setSize(3 * originalImage.getWidth() + MARGIN, originalImage.getHeight() + 2 * MARGIN);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        updateOriginalImage(originalImage);

        imageReproduce = new ImageReproduce(file, this);
        imageReproduce.reproduce(200, 45, 10, 0.1f, 10);
    }

    @Override
    public void updateOriginalImage(BufferedImage image) {
        if (originalImagePanel == null) {
            originalImagePanel = new ImagePanel(image);
            originalImagePanel.setBounds(MARGIN / 3, MARGIN / 3, originalImage.getWidth(), originalImage.getHeight());
            originalImagePanel.setVisible(true);
            add(originalImagePanel);
        }

        originalImagePanel.repaint(image);
    }

    @Override
    public void updateCurrentImage(BufferedImage image) {
        if (currentImagePanel == null) {
            currentImagePanel = new ImagePanel(image);
            currentImagePanel.setBounds((2 * MARGIN) / 3 + originalImage.getWidth(),  MARGIN / 3, originalImage.getWidth(), originalImage.getHeight());
            currentImagePanel.setVisible(true);
            add(currentImagePanel);
        }
        currentImagePanel.repaint(image);
    }

    @Override
    public void updateBestImage(BufferedImage image) {
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
