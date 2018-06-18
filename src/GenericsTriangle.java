import java.awt.*;

public class GenericsTriangle {

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

    public GenericsTriangle(int maxX, int maxY) {
        a = new Vector2D(RandomUtil.getRandomInt(0, maxX), RandomUtil.getRandomInt(0, maxY));
        b = a.add(new Vector2D(RandomUtil.getRandomInt(10, 50), RandomUtil.getRandomInt(10, 50)));
        c = a.add(new Vector2D(RandomUtil.getRandomInt(10, 50), RandomUtil.getRandomInt(10, 50)));
        color = new Color(RandomUtil.getRandomInt(0, 255), RandomUtil.getRandomInt(0, 255), RandomUtil.getRandomInt(0, 255), 128);
    }

    public void mutate() {
        //TODO
    }

    public int[] getPointsX() {
        return new int[]{a.getX(), b.getX(), c.getX()};
    }

    public int[] getPointsY() {
        return new int[]{a.getY(), b.getY(), c.getY()};
    }

    public Color getColor() {
        return color;
    }
}
