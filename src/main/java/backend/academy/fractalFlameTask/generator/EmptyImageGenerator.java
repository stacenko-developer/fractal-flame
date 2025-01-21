package backend.academy.fractalFlameTask.generator;

import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.dto.Pixel;

public class EmptyImageGenerator extends ImageGenerator {

    @Override
    public Image generate(ImageSize imageSize) {
        validateImageSize(imageSize);

        final int width = imageSize.width();
        final int height = imageSize.height();

        final Pixel[] pixels = new Pixel[width * height];

        for (int i = 0; i < height * width; i++) {
            pixels[i] = new Pixel();
        }

        return new Image(pixels, imageSize);
    }
}
