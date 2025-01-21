package backend.academy.fractalFlameTask.dto;

import backend.academy.fractalFlameTask.exception.IncorrectImagePixelsException;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_SIZE_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_PIXELS_EXCEPTION_TEXT;

public record Image(Pixel[] pixels, ImageSize imageSize) {

    public Image {
        validateImageSize(imageSize);
        validatePixels(pixels, imageSize);
    }

    public Pixel pixel(int x, int y) {
        if (contains(x, y)) {
            return pixels[y * imageSize().width() + x];
        }

        return null;
    }

    private boolean contains(int x, int y) {
        return x >= 0 && x < imageSize().width() && y >= 0 && y < imageSize().height();
    }

    private void validateImageSize(ImageSize imageSize) {
        if (imageSize == null) {
            throw new NullPointerException(NULL_IMAGE_SIZE_EXCEPTION_TEXT);
        }
    }

    private void validatePixels(Pixel[] pixels, ImageSize imageSize) {
        if (pixels == null) {
            throw new NullPointerException(NULL_PIXELS_EXCEPTION_TEXT);
        }

        if (pixels.length != imageSize.width() * imageSize.height()) {
            throw new IncorrectImagePixelsException();
        }

        for (Pixel pixel : pixels) {
            if (pixel == null) {
                throw new NullPointerException(NULL_PIXELS_EXCEPTION_TEXT);
            }
        }
    }
}
