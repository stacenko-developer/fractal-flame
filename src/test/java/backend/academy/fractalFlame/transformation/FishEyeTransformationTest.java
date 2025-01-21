package backend.academy.fractalFlame.transformation;

import backend.academy.fractalFlameTask.dto.Point;
import backend.academy.fractalFlameTask.transformation.FishEyeTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FishEyeTransformationTest extends TransformationTest {

    @Override
    protected Transformation getTransformation() {
        return new FishEyeTransformation();
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForFishEyeTransformation")
    public void transformPointByFishEyeTransformation_ShouldCorrectlyTransform(
        Point currentPoint, Point correctResult
    ) {
        assertEquals(correctResult, getTransformation().transform(currentPoint));
    }

    private static List<Object[]> getArgumentsForFishEyeTransformation() {
        return List.of(
            new Object[]{new Point(0, 0), new Point(0, 0)},
            new Object[]{new Point(1, 0), new Point(0, 1)},
            new Object[]{new Point(0, 1), new Point(1, 0)},
            new Object[]{new Point(-1, 0), new Point(0, -1)},
            new Object[]{new Point(0, -1), new Point(-1, 0)},
            new Object[]{new Point(0.5, 0.5), new Point(0.585786437626905, 0.585786437626905)},
            new Object[]{new Point(-0.5, -0.5), new Point(-0.585786437626905, -0.585786437626905)},
            new Object[]{new Point(0.25, 0.25), new Point(0.3693980625181293, 0.3693980625181293)},
            new Object[]{new Point(5, 5), new Point(1.238993430992954, 1.238993430992954)},
            new Object[]{new Point(-0.25, 0.25), new Point(0.3693980625181293, -0.3693980625181293)},
            new Object[]{new Point(-5, -5), new Point(-1.238993430992954, -1.238993430992954)},
            new Object[]{new Point(10, 10), new Point(1.3208176506262261, 1.3208176506262261)},
            new Object[]{new Point(7.5, 7.5), new Point(1.2923679440984142, 1.2923679440984142)},
            new Object[]{new Point(2.5, 1.5), new Point(0.7661903789690601, 1.2769839649484334)},
            new Object[]{new Point(5.4, 3.2), new Point(0.8794903217650144, 1.4841399179784618)}
        );
    }
}
