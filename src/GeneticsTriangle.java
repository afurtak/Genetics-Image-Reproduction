import java.awt.*;

public class GeneticsTriangle {

    private Vector2D a, b, c;
    private Color color;
    private int maxX, maxY;

    public GeneticsTriangle(GeneticsTriangle triangle) {
        a = new Vector2D(triangle.a);
        b = new Vector2D(triangle.b);
        c = new Vector2D(triangle.c);
        color = new Color(triangle.color.getRed(), triangle.color.getGreen(), triangle.color.getBlue(), 128);
        maxX = triangle.maxX;
        maxY = triangle.maxY;
    }

    public GeneticsTriangle(int maxX, int maxY) {
        a = new Vector2D(RandomUtil.getRandomInt(0, maxX), RandomUtil.getRandomInt(0, maxY));
        b = a.add(new Vector2D(RandomUtil.getRandomInt(-10, 10), RandomUtil.getRandomInt(-10, 10)));
        c = a.add(new Vector2D(RandomUtil.getRandomInt(-10, 10), RandomUtil.getRandomInt(-10, 10)));
        color = new Color(RandomUtil.getRandomInt(0, 255), RandomUtil.getRandomInt(0, 255), RandomUtil.getRandomInt(0, 255), 128);
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void mutate(int mutationSize) {
        int t = RandomUtil.getRandomInt(0, 3);
        if (t == 0)
            a = a.add(Vector2D.getNormalDistributedVector(mutationSize, mutationSize));
        else if (t == 1)
            b = b.add(Vector2D.getNormalDistributedVector(mutationSize, mutationSize));
        else
            c = c.add(Vector2D.getNormalDistributedVector(mutationSize, mutationSize));

        if (RandomUtil.getRandom() < 0.5) {
            int r = color.getRed() + (int)(RandomUtil.gaussRandom() * mutationSize * 10);
            if (r < 0) r = 0;
            if (r > 255) r = 255;

            int g = color.getGreen() + (int)(RandomUtil.gaussRandom() * mutationSize * 10);
            if (g < 0) g = 0;
            if (g > 255) g = 255;

            int b = color.getBlue() + (int)(RandomUtil.gaussRandom() * mutationSize * 10);
            if (b < 0) b = 0;
            if (b > 255) b = 255;

            color = new Color(r, g, b, 128);
        }
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
