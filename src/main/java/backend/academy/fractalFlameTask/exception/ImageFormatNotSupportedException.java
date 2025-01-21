package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.IMAGE_FORMAT_NOT_SUPPORTED_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.IMAGE_FORMAT_NOT_SUPPORTED_ERROR_SOLVING;

public class ImageFormatNotSupportedException extends FractalFlameAbstractException {
    public ImageFormatNotSupportedException() {
        super(IMAGE_FORMAT_NOT_SUPPORTED_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return IMAGE_FORMAT_NOT_SUPPORTED_ERROR_SOLVING;
    }
}
