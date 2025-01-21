package backend.academy.fractalFlameTask.processor;

import backend.academy.fractalFlameTask.dto.Image;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_EXCEPTION_TEXT;

public abstract class ImageProcessor {
    public abstract void process(Image image);

    protected void validateImage(Image image) {
        if (image == null) {
            throw new NullPointerException(NULL_IMAGE_EXCEPTION_TEXT);
        }
    }
}
