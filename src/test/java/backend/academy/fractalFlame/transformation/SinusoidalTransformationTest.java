package backend.academy.fractalFlame.transformation;

import backend.academy.fractalFlameTask.dto.Point;
import backend.academy.fractalFlameTask.transformation.SinusoidalTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinusoidalTransformationTest extends TransformationTest {

    @Override
    protected Transformation getTransformation() {
        return new SinusoidalTransformation();
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForSinusoidalTransformation")
    public void transformPointBySinusoidalTransformation_ShouldCorrectlyTransform(
        Point currentPoint, Point correctResult
    ) {
        assertEquals(correctResult, getTransformation().transform(currentPoint));
    }

    private static List<Object[]> getArgumentsForSinusoidalTransformation() {
        return List.of(
            new Object[]{new Point(0, 0), new Point(0, -0)},
            new Object[]{new Point(1, 0), new Point(0.8414709848078965, 0)},
            new Object[]{new Point(0, 1), new Point(0, 0.8414709848078965)},
            new Object[]{new Point(6.5, 0), new Point(0.21511998808781552, 0)},
            new Object[]{new Point(2.1, 3.4), new Point(0.8632093666488737, -0.2555411020268312)},
            new Object[]{new Point(9.5, 3.1), new Point(-0.0751511204618093, 0.04158066243329049)},
            new Object[]{new Point(-1.62, 0.38), new Point(-0.998789743470524, 0.3709204694129827)},
            new Object[]{new Point(0.37, 0.1), new Point(0.361615431964962, 0.09983341664682815)},
            new Object[]{new Point(6, 3), new Point(-0.27941549819892586, 0.1411200080598672)},
            new Object[]{new Point(3, 6), new Point(0.1411200080598672, -0.27941549819892586)},
            new Object[]{new Point(0.3, 4), new Point(0.29552020666133955, -0.7568024953079282)},
            new Object[]{new Point(10, 10), new Point(-0.5440211108893698, -0.5440211108893698)},
            new Object[]{new Point(7.5, 7.5), new Point(0.9379999767747389, 0.9379999767747389)},
            new Object[]{new Point(2.5, 1.5), new Point(0.5984721441039564, 0.9974949866040544)},
            new Object[]{new Point(5.4, 3.2), new Point(-0.7727644875559871, -0.058374143427580086)}
        );
    }
}
