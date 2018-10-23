import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.Arrays;

public class ImageReproduce {

    private BufferedImage originalImage;
    private double bestSimilarity;
    private ImageReproduceBuffer buffer;

    public ImageReproduce(File file, ImageReproduceBuffer buffer) {
        try {
            originalImage = ImageIO.read(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        this.buffer = buffer;
    }

    public void reproduce(int numberOfPieces, int numberOfMembers, int numberOfTopMembers, float mutationThreshold, int mutationSize) {
        bestSimilarity = 0;
        float currentThreshold = mutationThreshold;
        Chromosome[] members = new Chromosome[numberOfMembers];
        for (int i = 0; i < numberOfMembers; i++) {
            members[i] = new Chromosome(numberOfPieces, originalImage.getWidth(), originalImage.getHeight());
            members[i].getRating(originalImage);
        }

        for (int i = 0; true; i++) {
            Arrays.sort(members, (o1, o2) -> {
                if (o1.getRating(originalImage) == o2.getRating(originalImage))
                    return 0;
                else
                    return (o1.getRating(originalImage) < o2.getRating(originalImage) ? 1 : -1);
            });
            Chromosome[] top = new Chromosome[numberOfTopMembers];
            System.arraycopy(members, 0, top, 0, numberOfTopMembers);

            System.out.println("generation: " + i);
            System.out.println("rate: \n" +
                    "            best:  " + top[0].getRating(originalImage) + "\n" +
                    "           worst:  " + top[top.length - 1].getRating(originalImage));
            System.out.println("mutation probability: " + currentThreshold * 100 + "%");

            buffer.updateCurrentImage(top[0].getImage());

            double currentSimilarity = top[0].getRating(originalImage);
            if (bestSimilarity < currentSimilarity) {
                bestSimilarity = currentSimilarity;
                buffer.updateBestImage(top[0].getImage());
            }

            int index = 0;
            for (int j = 0; j < numberOfTopMembers && index < numberOfMembers; j++) {
                for (int k = j; k < numberOfTopMembers && index < numberOfMembers; k++) {
                    members[index] = new Chromosome(top[j], top[k], currentThreshold, mutationSize);
                    index++;
                }
            }

            currentThreshold = getMutationThreshold(mutationThreshold);
        }
    }

    private float getMutationThreshold(float mutationThreshold) {
        if (bestSimilarity < 70)
            return mutationThreshold;
        else if (bestSimilarity < 80)
            return mutationThreshold * 0.9f;
        else if (bestSimilarity < 90)
            return mutationThreshold * 0.7f;
        else if (bestSimilarity < 95)
            return mutationThreshold * 0.5f;
        else
            return mutationThreshold * 0.3f;
    }


}
