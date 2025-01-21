package backend.academy.fractalFlameTask.exception;

public abstract class FractalFlameAbstractException extends RuntimeException {

    public FractalFlameAbstractException(String message) {
        super(message);
    }

    public FractalFlameAbstractException(String message, Exception ex) {
        super(message, ex);
    }

    public abstract String getSolution();
}
