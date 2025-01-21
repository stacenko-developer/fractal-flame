package backend.academy.fractalFlameTask.transformation;

import backend.academy.fractalFlameTask.dto.Point;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_POINT_EXCEPTION_TEXT;

public interface Transformation {
    Point transform(Point point);

    default void validatePoint(Point point) {
        if (point == null) {
            throw new NullPointerException(NULL_POINT_EXCEPTION_TEXT);
        }
    }
}
