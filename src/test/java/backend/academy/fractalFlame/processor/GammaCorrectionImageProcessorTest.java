package backend.academy.fractalFlame.processor;

import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.dto.Pixel;
import backend.academy.fractalFlameTask.processor.GammaCorrectionImageProcessor;
import backend.academy.fractalFlameTask.processor.ImageProcessor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GammaCorrectionImageProcessorTest extends ImageProcessorTest {

    private static final ImageSize DEFAULT_IMAGE_SIZE = new ImageSize(100, 100);

    @Override
    protected ImageProcessor getImageProcessor() {
        return new GammaCorrectionImageProcessor();
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGammaCorrectionWithSameHitCount")
    public void gammaCorrectionWithSameHitCount_ShouldNotChangePixels(
        Image imageForProcess, Image imageForCompare
    ) {
        getImageProcessor().process(imageForProcess);

        assertTrue(Arrays.deepEquals(imageForProcess.pixels(), imageForCompare.pixels()));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGammaCorrectionWithDifferentHitCount")
    public void gammaCorrectionWithDifferentHitCount_ShouldDoGammaCorrection(
        Image imageForProcess, Image imageForCompare
    ) {
        getImageProcessor().process(imageForProcess);

        assertFalse(Arrays.deepEquals(imageForProcess.pixels(), imageForCompare.pixels()));
    }

    private static List<Object[]> getArgumentsForGammaCorrectionWithSameHitCount() {
        final int minColorValue = 0;
        final int maxColorValue = 255;
        final int minHitCount = 0;
        final int maxHitCount = 2;
        final List<Object[]> result = new ArrayList<>();

        for (int i = minColorValue; i <= maxColorValue; i++) {
            for (int hitCount = minHitCount; hitCount <= maxHitCount; hitCount++) {
                result.add(new Object[]{
                    new Image(getPixels(i, hitCount, hitCount), DEFAULT_IMAGE_SIZE),
                    new Image(getPixels(i, hitCount, hitCount), DEFAULT_IMAGE_SIZE)
                });
            }
        }

        return result;
    }

    private static List<Object[]> getArgumentsForGammaCorrectionWithDifferentHitCount() {
        final int minColorValue = 1;
        final int maxColorValue = 255;
        final int minHitCount = 0;
        final int maxHitCount = 255;
        final List<Object[]> result = new ArrayList<>();

        for (int i = minColorValue; i <= maxColorValue; i++) {
            result.add(new Object[]{
                new Image(getPixels(i, minHitCount, maxHitCount), DEFAULT_IMAGE_SIZE),
                new Image(getPixels(i, minHitCount, maxHitCount), DEFAULT_IMAGE_SIZE)
            });
        }

        return result;
    }

    private static Pixel[] getPixels(int value, int minHitCount, int maxHitCount) {
        final int pixelsCount = DEFAULT_IMAGE_SIZE.width() * DEFAULT_IMAGE_SIZE.height();
        final Pixel[] pixels = new Pixel[pixelsCount];

        for (int i = 0, hitCount = minHitCount; i < pixelsCount; i++) {
            pixels[i] = getPixel(value, hitCount);

            if (hitCount >= maxHitCount) {
                hitCount = minHitCount;
            } else {
                hitCount++;
            }
        }

        return pixels;
    }

    private static Pixel getPixel(int value, int hitCount) {
        final Pixel pixel = new Pixel();
        pixel.setColor(new Color(value, value, value));

        for (int i = 0; i < hitCount; i++) {
            pixel.updateHitCount(new Color(value, value, value));
        }

        return pixel;
    }

}
