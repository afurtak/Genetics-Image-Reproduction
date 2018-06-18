import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        try {
            BufferedImage image = ImageIO.read(new File("lenna.png"));
            new ImageWindow("Lenna", image);
        }
        catch (Exception e) {
            System.out.println("there is no such image as lenna.png");
        }
    }
}
