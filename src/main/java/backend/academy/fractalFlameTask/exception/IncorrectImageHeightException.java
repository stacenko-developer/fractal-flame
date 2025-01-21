package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_IMAGE_HEIGHT_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_IMAGE_HEIGHT_ERROR_SOLVING;

public class IncorrectImageHeightException extends FractalFlameAbstractException {
    public IncorrectImageHeightException() {
        super(INCORRECT_IMAGE_HEIGHT_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_IMAGE_HEIGHT_ERROR_SOLVING;
    }
}
