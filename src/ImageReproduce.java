import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageReproduce {

    private BufferedImage originalImage;

    public ImageReproduce(String path) {
        try {
            File file = new File(path);
            originalImage = ImageIO.read(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show_original() {
        new ImageWindow("original", originalImage);
    }
}
