package backend.academy.fractalFlameTask.transformation;

import backend.academy.fractalFlameTask.dto.Point;

public record LinearTransformation() implements Transformation {

    @Override
    public Point transform(Point point) {
        validatePoint(point);

        return point;
    }
}
