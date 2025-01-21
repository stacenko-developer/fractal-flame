package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_IMAGE_PIXELS_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_IMAGE_PIXELS_ERROR_SOLVING;

public class IncorrectImagePixelsException extends FractalFlameAbstractException {
    public IncorrectImagePixelsException() {
        super(INCORRECT_IMAGE_PIXELS_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_IMAGE_PIXELS_ERROR_SOLVING;
    }
}
