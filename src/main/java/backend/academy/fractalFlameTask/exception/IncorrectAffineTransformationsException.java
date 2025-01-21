package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_AFFINE_TRANSFORMATIONS_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_AFFINE_TRANSFORMATIONS_ERROR_SOLVING;

public class IncorrectAffineTransformationsException extends FractalFlameAbstractException {
    public IncorrectAffineTransformationsException() {
        super(INCORRECT_AFFINE_TRANSFORMATIONS_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_AFFINE_TRANSFORMATIONS_ERROR_SOLVING;
    }
}
