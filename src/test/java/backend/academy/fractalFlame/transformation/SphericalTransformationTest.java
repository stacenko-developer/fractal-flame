package backend.academy.fractalFlame.transformation;

import backend.academy.fractalFlameTask.dto.Point;
import backend.academy.fractalFlameTask.transformation.SphericalTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.DIVISION_BY_ZERO_IN_TRANSFORMATION_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphericalTransformationTest extends TransformationTest {

    @Override
    protected Transformation getTransformation() {
        return new SphericalTransformation();
    }

    @Test
    public void transformPointWithDivisionByZero_ShouldThrowArithmeticException() {
        final Point incorrectPoint = new Point(0, 0);

        assertThatThrownBy(() -> {
            getTransformation().transform(incorrectPoint);
        }).isInstanceOf(ArithmeticException.class)
            .hasMessageContaining(DIVISION_BY_ZERO_IN_TRANSFORMATION_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForSphericalTransformation")
    public void transformPointBySphericalTransformation_ShouldCorrectlyTransform(
        Point currentPoint, Point correctResult
    ) {
        assertEquals(correctResult, getTransformation().transform(currentPoint));
    }

    private static List<Object[]> getArgumentsForSphericalTransformation() {
        return List.of(
            new Object[]{new Point(-10, -5), new Point(-0.08, -0.04)},
            new Object[]{new Point(1, 0), new Point(1, 0)},
            new Object[]{new Point(0, 1), new Point(0, 1)},
            new Object[]{new Point(6.5, 0), new Point(0.15384615384615385, 0)},
            new Object[]{new Point(2.1, 3.4), new Point(0.13149655604257984, 0.2128991859737007)},
            new Object[]{new Point(9.5, 3.1), new Point(0.09513318646104546, 0.03104346084518326)},
            new Object[]{new Point(-1.62, 0.38), new Point(-0.5850910141577578, 0.13724357122219008)},
            new Object[]{new Point(0.37, 0.1), new Point(2.518720217835262, 0.6807351940095303)},
            new Object[]{new Point(6, 3), new Point(0.13333333333333333, 0.06666666666666667)},
            new Object[]{new Point(3, 6), new Point(0.06666666666666667, 0.13333333333333333)},
            new Object[]{new Point(0.3, 4), new Point(0.018645121193287754, 0.24860161591050342)},
            new Object[]{new Point(10, 10), new Point(0.05, 0.05)},
            new Object[]{new Point(7.5, 7.5), new Point(0.06666666666666667, 0.06666666666666667)},
            new Object[]{new Point(2.5, 1.5), new Point(0.29411764705882354, 0.17647058823529413)},
            new Object[]{new Point(5.4, 3.2), new Point(0.13705583756345177, 0.08121827411167512)}
        );
    }
}
