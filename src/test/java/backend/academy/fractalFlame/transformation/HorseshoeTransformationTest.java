package backend.academy.fractalFlame.transformation;

import backend.academy.fractalFlameTask.dto.Point;
import backend.academy.fractalFlameTask.transformation.HorseshoeTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.DIVISION_BY_ZERO_IN_TRANSFORMATION_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorseshoeTransformationTest extends TransformationTest {

    @Override
    protected Transformation getTransformation() {
        return new HorseshoeTransformation();
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
    @MethodSource("getArgumentsForHorseshoeTransformation")
    public void transformPointByHorseshoeTransformation_ShouldCorrectlyTransform(
        Point currentPoint, Point correctResult
    ) {
        assertEquals(correctResult, getTransformation().transform(currentPoint));
    }

    private static List<Object[]> getArgumentsForHorseshoeTransformation() {
        return List.of(
            new Object[]{new Point(-10, -5), new Point(6.7082039324993685, 8.94427190999916)},
            new Object[]{new Point(1, 0), new Point(1, 0)},
            new Object[]{new Point(0, 1), new Point(-1, 0)},
            new Object[]{new Point(6.5, 0), new Point(6.5, 0)},
            new Object[]{new Point(2.1, 3.4), new Point(-1.7891781415055703, 3.5733515889090275)},
            new Object[]{new Point(9.5, 3.1), new Point(8.069650733963362, 5.89412733420687)},
            new Object[]{new Point(-1.62, 0.38), new Point(1.4904104525131379, -0.7399166730379738)},
            new Object[]{new Point(0.37, 0.1), new Point(0.3310935529061811, 0.19307267860565325)},
            new Object[]{new Point(6, 3), new Point(4.024922359499621, 5.366563145999495)},
            new Object[]{new Point(3, 6), new Point(-4.024922359499621, 5.366563145999495)},
            new Object[]{new Point(0.3, 4), new Point(-3.9663602550813355, 0.5983195859330739)},
            new Object[]{new Point(10, 10), new Point(0, 14.14213562373095)},
            new Object[]{new Point(7.5, 7.5), new Point(0, 10.606601717798213)},
            new Object[]{new Point(2.5, 1.5), new Point(1.3719886811400706, 2.5724787771376323)},
            new Object[]{new Point(5.4, 3.2), new Point(3.0142066326589965, 5.505865815258716)}
        );
    }
}
