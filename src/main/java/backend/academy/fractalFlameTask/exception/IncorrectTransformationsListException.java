package backend.academy.fractalFlameTask.exception;

import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_TRANSFORMATIONS_LIST_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_TRANSFORMATIONS_LIST_ERROR_SOLVING;

public class IncorrectTransformationsListException extends FractalFlameAbstractException {
    public IncorrectTransformationsListException() {
        super(INCORRECT_TRANSFORMATIONS_LIST_EXCEPTION_TEXT);
    }

    @Override
    public String getSolution() {
        return INCORRECT_TRANSFORMATIONS_LIST_ERROR_SOLVING;
    }
}
