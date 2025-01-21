package backend.academy.fractalFlameTask.processor;

import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.Pixel;
import java.awt.Color;

public class GammaCorrectionImageProcessor extends ImageProcessor {

    private static final double GAMMA = 1.5;
    private static final double GAMMA_INV = 1.0 / GAMMA;

    private int width;
    private int height;

    @Override
    public void process(Image image) {
        super.validateImage(image);

        gammaCorrectionProcess(image);
    }

    private void gammaCorrectionProcess(Image image) {
        width = image.imageSize().width();
        height = image.imageSize().height();

        normalizationProcess(image, getMaxNormal(image));
    }

    private double getMaxNormal(Image image) {
        double maxNormal = 0.0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Pixel pixel = image.pixel(x, y);
                final int hitCount = pixel.hitCount();

                if (hitCount != 0) {
                    maxNormal = Math.max(maxNormal, Math.log10(hitCount));
                }
            }
        }

        return maxNormal;
    }

    private void normalizationProcess(Image image, double maxNormal) {
        if (maxNormal == 0) {
            return;
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                processPixel(image.pixel(x, y), maxNormal);
            }
        }
    }

    private void processPixel(Pixel pixel, double maxNormal) {
        final double normal = pixel.hitCount() == 0
            ? 0
            : Math.log10(pixel.hitCount());
        final double normalized = normal / maxNormal;
        final double gammaCorrected = Math.pow(normalized, GAMMA_INV);

        final int newRed = getNewColorValue(pixel.red(), gammaCorrected);
        final int newGreen = getNewColorValue(pixel.green(), gammaCorrected);
        final int newBlue = getNewColorValue(pixel.blue(), gammaCorrected);

        pixel.setColor(new Color(newRed, newGreen, newBlue));
    }

    private int getNewColorValue(int colorValue, double gammaCorrected) {
        final int minColorValue = 0;
        final int maxColorValue = 255;
        final int newColorValue = (int) Math.round(colorValue * gammaCorrected);

        return Math.min(maxColorValue, Math.max(minColorValue, newColorValue));
    }
}
