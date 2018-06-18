import java.awt.*;
import java.util.Random;

public class GenericsTriangle {
    public static Random random = new Random();

    private Vector2D a, b, c;
    private Color color;

    public GenericsTriangle(Vector2D a, Vector2D b, Vector2D c, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public GenericsTriangle(GenericsTriangle triangle) {
        a = new Vector2D(triangle.a);
        b = new Vector2D(triangle.b);
        c = new Vector2D(triangle.c);
        color = new Color(triangle.color.getRGB());
    }

    public GenericsTriangle() {
        a = new Vector2D();
        b = new Vector2D();
        c = new Vector2D();
        color = new Color(0, 0, 0, 0);
    }
}
