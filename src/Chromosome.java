import java.awt.*;
import java.awt.image.BufferedImage;

public class Chromosome {

    BufferedImage image;
    GenericsTriangle[] triangles;

    public Chromosome(int numberOfPieces, int width, int height) {
        triangles = new GenericsTriangle[numberOfPieces];
        for (int i = 0; i < numberOfPieces; i++) {
            triangles[i] = new GenericsTriangle(width, height);
        }
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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
        draw();
    }

    private void draw() {
        Graphics g = image.getGraphics();
        for (GenericsTriangle triangle : triangles) {
            g.setColor(triangle.getColor());
            System.out.println(triangle.getPointsX()[0] + " " + triangle.getPointsY()[0]);
            g.fillPolygon(triangle.getPointsX(), triangle.getPointsY(), 3);
        }
    }

    public void show() {
        new ImageWindow("chromosome", image);
    }


}
