package backend.academy.fractalFlame.transformation;

import backend.academy.fractalFlameTask.dto.Point;
import backend.academy.fractalFlameTask.transformation.LinearTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearTransformationTest extends TransformationTest {

    @Override
    protected Transformation getTransformation() {
        return new LinearTransformation();
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForLinearTransformation")
    public void transformPointByLinearTransformation_ShouldCorrectlyTransform(
        Point currentPoint, Point correctResult
    ) {
        assertEquals(correctResult, getTransformation().transform(currentPoint));
    }

    private static List<Object[]> getArgumentsForLinearTransformation() {
        return List.of(
            new Object[]{new Point(0, 0), new Point(0, 0)},
            new Object[]{new Point(1, 0), new Point(1, 0)},
            new Object[]{new Point(0, 1), new Point(0, 1)},
            new Object[]{new Point(6.5, 0), new Point(6.5, 0)},
            new Object[]{new Point(2.1, 3.4), new Point(2.1, 3.4)},
            new Object[]{new Point(9.5, 3.1), new Point(9.5, 3.1)},
            new Object[]{new Point(-1.62, 0.38), new Point(-1.62, 0.38)},
            new Object[]{new Point(0.37, 0.1), new Point(0.37, 0.1)},
            new Object[]{new Point(6, 3), new Point(6, 3)},
            new Object[]{new Point(3, 6), new Point(3, 6)},
            new Object[]{new Point(0.3, 4), new Point(0.3, 4)},
            new Object[]{new Point(10, 10), new Point(10, 10)},
            new Object[]{new Point(7.5, 7.5), new Point(7.5, 7.5)},
            new Object[]{new Point(2.5, 1.5), new Point(2.5, 1.5)},
            new Object[]{new Point(5.4, 3.2), new Point(5.4, 3.2)}
        );
    }
}
