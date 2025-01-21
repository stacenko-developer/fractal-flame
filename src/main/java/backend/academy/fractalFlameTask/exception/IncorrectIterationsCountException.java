package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_ITERATIONS_COUNT_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_ITERATIONS_COUNT_ERROR_SOLVING;

public class IncorrectIterationsCountException extends FractalFlameAbstractException {

    public IncorrectIterationsCountException() {
        super(INCORRECT_ITERATIONS_COUNT_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_ITERATIONS_COUNT_ERROR_SOLVING;
    }
}
