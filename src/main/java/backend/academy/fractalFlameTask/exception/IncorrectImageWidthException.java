package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_IMAGE_WIDTH_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_IMAGE_WIDTH_ERROR_SOLVING;

public class IncorrectImageWidthException extends FractalFlameAbstractException {
    public IncorrectImageWidthException() {
        super(INCORRECT_IMAGE_WIDTH_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_IMAGE_WIDTH_ERROR_SOLVING;
    }
}
