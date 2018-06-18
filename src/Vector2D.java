
public class Vector2D {
    private int x, y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2D add(Vector2D vector) {
        return new Vector2D(x + vector.x, y + vector.y);
    }

    public static Vector2D getNormalDistributedVector(int scale) {
        return new Vector2D((int)RandomUtil.gaussRandom() * scale, (int)RandomUtil.gaussRandom() * scale);
    }
}
