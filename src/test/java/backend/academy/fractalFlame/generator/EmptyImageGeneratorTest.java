package backend.academy.fractalFlame.generator;

import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.dto.Pixel;
import backend.academy.fractalFlameTask.generator.EmptyImageGenerator;
import backend.academy.fractalFlameTask.generator.ImageGenerator;
import org.junit.jupiter.api.Test;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyImageGeneratorTest extends ImageGeneratorTest {

    @Override
    protected ImageGenerator getImageGenerator() {
        return new EmptyImageGenerator();
    }

    @Test
    public void generateEmptyImage_ShouldGenerateEmptyImage() {
        final ImageSize imageSize = new ImageSize(MIN_IMAGE_WIDTH , MIN_IMAGE_HEIGHT);
        final Image image = getImageGenerator().generate(imageSize);
        final Pixel startPixel = new Pixel();

        for (int index = 0; index < image.pixels().length; index++) {
            assertEquals(startPixel, image.pixels()[index]);
        }
    }
}
