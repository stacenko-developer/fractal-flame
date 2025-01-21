package backend.academy.fractalFlameTask.transformation;

import backend.academy.fractalFlameTask.dto.Point;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.DIVISION_BY_ZERO_IN_TRANSFORMATION_EXCEPTION_TEXT;

public record SphericalTransformation() implements Transformation {

    @Override
    public Point transform(Point point) {
        validatePoint(point);

        final double x = point.x();
        final double y = point.y();
        final double rInSquare = x * x + y * y;

        if (rInSquare == 0) {
            throw new ArithmeticException(DIVISION_BY_ZERO_IN_TRANSFORMATION_EXCEPTION_TEXT);
        }

        return new Point(x / rInSquare, y / rInSquare);
    }
}
