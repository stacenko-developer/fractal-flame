package backend.academy.fractalFlameTask.transformation;

import backend.academy.fractalFlameTask.dto.Point;

public record SinusoidalTransformation() implements Transformation {

    @Override
    public Point transform(Point point) {
        validatePoint(point);

        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
