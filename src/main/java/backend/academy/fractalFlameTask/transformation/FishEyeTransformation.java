package backend.academy.fractalFlameTask.transformation;

import backend.academy.fractalFlameTask.dto.Point;

public record FishEyeTransformation() implements Transformation {

    @Override
    public Point transform(Point point) {
        validatePoint(point);

        final double x = point.x();
        final double y = point.y();
        final double r = Math.sqrt(x * x + y * y);

        return new Point(2 * y / (r + 1), 2 * x / (r + 1));
    }
}
