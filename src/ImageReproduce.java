import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

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

    public void reproduce(int numberOfPieces, int numberOfMembers, int numberOfTopMembers, float mutationThershold) {
        Chromosome[] members = new Chromosome[numberOfMembers];
        for (int i = 0; i < numberOfMembers; i++) {
            members[i] = new Chromosome(numberOfPieces, originalImage.getWidth(), originalImage.getHeight());
            members[i].getRating(originalImage);
            new ImageWindow("ADSF",members[i].getImage());
        }

        for (int i = 0; i <1000; i++) {
            Arrays.sort(members, new Comparator<Chromosome>() {
                @Override
                public int compare(Chromosome o1, Chromosome o2) {
                    return (o1.getRating(originalImage) < o2.getRating(originalImage) ? 1 : -1);
                }
            });
            Chromosome[] top = new Chromosome[numberOfTopMembers];
            System.arraycopy(members, 0, top, 0, numberOfTopMembers);
            System.out.println("generation: " + i);
            System.out.println("rate:       " + top[0].getRating(originalImage) + ", " + top[top.length - 1].getRating(originalImage));
            new ImageWindow("vest", top[0].getImage());
            int index = 0;
            for (int j = 0; j < numberOfTopMembers && index < numberOfMembers; j++) {
                for (int k = j; k < numberOfTopMembers && index < numberOfMembers; k++) {
                    members[index] = new Chromosome(top[j], top[k], mutationThershold);
                    index++;
                }
            }
        }
    }
}
