package backend.academy.fractalFlameTask.generator;

import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_SIZE_EXCEPTION_TEXT;

public abstract class ImageGenerator {

    public abstract Image generate(ImageSize imageSize);

    protected void validateImageSize(ImageSize imageSize) {
        if (imageSize == null) {
            throw new NullPointerException(NULL_IMAGE_SIZE_EXCEPTION_TEXT);
        }
    }
}
