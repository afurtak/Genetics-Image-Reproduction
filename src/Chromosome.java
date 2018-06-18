import java.awt.*;
import java.awt.image.BufferedImage;

public class Chromosome {

    private BufferedImage image;
    private GenericsTriangle[] triangles;
    private double rating;

    public Chromosome(int numberOfPieces, int width, int height) {
        triangles = new GenericsTriangle[numberOfPieces];
        for (int i = 0; i < numberOfPieces; i++) {
            triangles[i] = new GenericsTriangle(width, height);
        }
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        rating = -1;
        draw();
    }

    public Chromosome(Chromosome parent1, Chromosome parent2, float mutationThreshold) {
        triangles = new GenericsTriangle[parent1.triangles.length];

        for (int i = 0; i < triangles.length; i++) {

            if (RandomUtil.getRandom() < .5)
                triangles[i] = new GenericsTriangle(parent1.triangles[i]);
            else
                triangles[i] = new GenericsTriangle(parent2.triangles[i]);

            if (RandomUtil.getRandom() < mutationThreshold)
                triangles[i].mutate();
        }
        image = new BufferedImage(parent1.image.getWidth(), parent1.image.getWidth(), BufferedImage.TYPE_INT_ARGB);
        rating = -1;
        draw();
    }

    private void draw() {
        Graphics g = image.getGraphics();
        for (GenericsTriangle triangle : triangles) {
            g.setColor(triangle.getColor());
            g.fillPolygon(triangle.getPointsX(), triangle.getPointsY(), 3);
        }
    }

    public void show() {
        new ImageWindow("chromosome", image);
    }

    public double getRating(BufferedImage image) {
        if (rating == -1)
            rating = rate(image);
        return rating;
    }

    private double rate(BufferedImage image) {
        double d = 0;
        double maxD = image.getWidth() * image.getHeight() * Math.sqrt(255 * 255 * 3);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int red1 = new Color(image.getRGB(x, y)).getRed();
                int blue1 = new Color(image.getRGB(x, y)).getBlue();
                int green1 = new Color(image.getRGB(x,y)).getGreen();
                int red2 = new Color(this.image.getRGB(x, y)).getRed();
                int blue2 = new Color(this.image.getRGB(x, y)).getBlue();
                int green2 = new Color(this.image.getRGB(x,y)).getGreen();
                d += Math.sqrt((red1 - red2) * (red1 - red2) + (blue1 - blue2) * (blue1 - blue2) + (green1 - green2) * (green1 - green2));
            }
        }
        return ((maxD - d) * 100) / maxD;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String toString() {
        return triangles[0].getPointsX()[0] + " " + triangles[0].getPointsY()[0];
    }
}
