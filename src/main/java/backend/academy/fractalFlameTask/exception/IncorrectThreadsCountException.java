package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_THREADS_COUNT_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_THREADS_COUNT_ERROR_SOLVING;

public class IncorrectThreadsCountException extends FractalFlameAbstractException {
    public IncorrectThreadsCountException() {
        super(INCORRECT_THREADS_COUNT_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_THREADS_COUNT_ERROR_SOLVING;
    }
}
