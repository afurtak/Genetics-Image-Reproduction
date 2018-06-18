import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ImageReproduce imageReproduce = new ImageReproduce("lenna.png");
        imageReproduce.reproduce(200, 45, 10, 0.1f);
    }
}
