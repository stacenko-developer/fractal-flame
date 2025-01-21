package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.IMAGE_SAVE_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.IMAGE_SAVE_ERROR_SOLVING;

public class ImageSaveException extends FractalFlameAbstractException {
    public ImageSaveException(Exception ex) {
        super(IMAGE_SAVE_EXCEPTION_TEXT, ex);
    }

    @Override
    public String getSolution() {
        return IMAGE_SAVE_ERROR_SOLVING;
    }
}
