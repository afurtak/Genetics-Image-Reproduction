import java.awt.image.BufferedImage;

public interface ImageReproduceBuffer {
    void updateOriginalImage(BufferedImage bufferedImage);
    void updateCurrentImage(BufferedImage bufferedImage);
    void updateBestImage(BufferedImage bufferedImage);
}
