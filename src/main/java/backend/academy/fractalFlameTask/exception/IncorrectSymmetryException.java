package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_SYMMETRY_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_SYMMETRY_ERROR_SOLVING;

public class IncorrectSymmetryException extends FractalFlameAbstractException {
    public IncorrectSymmetryException() {
        super(INCORRECT_SYMMETRY_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_SYMMETRY_ERROR_SOLVING;
    }
}
