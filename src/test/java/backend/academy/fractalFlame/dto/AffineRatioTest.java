package backend.academy.fractalFlame.dto;

import backend.academy.fractalFlameTask.dto.AffineRatio;
import backend.academy.fractalFlameTask.dto.Point;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_POINT_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AffineRatioTest {

    @Test
    public void createAffineRatioByDefaultConstructor_ShouldGenerateCorrectRatios() {
        final AffineRatio affineRatio = new AffineRatio();

        final double a = affineRatio.a();
        final double b = affineRatio.b();
        final double c = affineRatio.c();
        final double d = affineRatio.d();
        final double e = affineRatio.e();
        final double f = affineRatio.f();

        checkRatioValue(a);
        checkRatioValue(b);
        checkRatioValue(c);
        checkRatioValue(d);
        checkRatioValue(e);
        checkRatioValue(f);

        assertTrue((a * a + d * d) < 1);
        assertTrue((b * b + e * e) < 1);
        assertTrue((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));

        assertNotNull(affineRatio.color());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateAffineRatiosByCorrectArgumentsInConstructor")
    public void createAffineRatioByCorrectArgumentsInConstructor_ShouldCorrectlyCreate(
        double a, double b, double c, double d, double e, double f
    ) {
        final AffineRatio affineRatio = new AffineRatio(a, b, c, d, e, f);

        assertEquals(a, affineRatio.a());
        assertEquals(b, affineRatio.b());
        assertEquals(c, affineRatio.c());
        assertEquals(d, affineRatio.d());
        assertEquals(e, affineRatio.e());
        assertEquals(f, affineRatio.f());

        assertNotNull(affineRatio.color());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateAffineRatiosByIncorrectArgumentsInConstructor")
    public void createAffineRatioByIncorrectArgumentsConstructor_ShouldRegenerateRatios(
        double a, double b, double c, double d, double e, double f
    ) {
        final AffineRatio affineRatio = new AffineRatio(a, b, c, d, e, f);

        assertNotEquals(a, affineRatio.a());
        assertNotEquals(b, affineRatio.b());
        assertNotEquals(c, affineRatio.c());
        assertNotEquals(d, affineRatio.d());
        assertNotEquals(e, affineRatio.e());
        assertNotEquals(f, affineRatio.f());

        assertNotNull(affineRatio.color());
    }

    @Test
    public void transformNullPoint_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new AffineRatio().transform(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_POINT_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForTransformCorrectPoint")
    public void transformCorrectPoint_ShouldCorrectlyTransform(
        AffineRatio affineRatio, Point inputPoint, Point correctPoint
    ) {
        final Point result = affineRatio.transform(inputPoint);

        assertEquals(correctPoint, result);
    }

    private static List<Object[]> getArgumentsForCreateAffineRatiosByIncorrectArgumentsInConstructor() {
        return List.of(
            new Object[] {0.8, 0.5, 0.4, 0.6, 0.3, 0.1},
            new Object[] {-0.9, 0.8, 0.3, 0.4, 0.2, 0.2},
            new Object[] {1.1, 0.4, 0.2, -0.6, 0.2, 0.1},
            new Object[] {-0.95, -0.85, 0.3, 0.1, 0.2, 0.2},
            new Object[] {1.05, 0.2, 0.3, -0.4, 0.5, 0.1},
            new Object[] {-0.95, 0.9, 0.7, 0.6, 0.1, 0.4},
            new Object[] {0.75, 0.5, 0.6, 0.8, 0.5, -0.3},
            new Object[] {0.85, -0.9, -0.8, 0.7, -0.4, 0.2},
            new Object[] {-1.2, 0.5, -0.2, 0.4, 0.3, -0.1}
        );
    }

    private static List<Object[]> getArgumentsForCreateAffineRatiosByCorrectArgumentsInConstructor() {
        return List.of(
            new Object[] {0, 0, 0, 0, 0, 0},
            new Object[] {-0.2134, -0.1523, -0.3112, -0.7724, -0.4352, -0.1458},
            new Object[] {0.24, -0.48, 0.16, -0.27, 0.32, -0.74},
            new Object[] {-0.12, 0.76, -0.55, 0.68, 0.48, -0.36},
            new Object[] {0.43, 0.28, 0.91, -0.53, -0.22, 0.44},
            new Object[] {0.2286, -0.1532, 0.2014, -0.1455, -0.8639, -0.0221},
            new Object[] {0.7175, 0.1839, 0.4627, 0.0151, -0.0664, 0.0985},
            new Object[] {0.3155, -0.6324, 0.4934, -0.2208, 0.4343, 0.7663},
            new Object[] {-0.6930, -0.5782, -0.5777, 0.2607, 0.3476, 0.3548},
            new Object[] {-0.1016, -0.7113, 0.7807, 0.1988, -0.3836, 0.8145},
            new Object[] {0.0724, -0.353, 0.6494, 0.2744, -0.6781, -0.225},
            new Object[] {0.1735, 0.4861, -0.8375, -0.4703, -0.5226, -0.4718},
            new Object[] {-0.5897, -0.1563, 0.3144, -0.1754, 0.1172, -0.0645},
            new Object[] {0.0159, 0.0646, -0.1511, -0.6876, -0.0333, 0.7537}
        );
    }

    private static List<Object[]> getArgumentsForTransformCorrectPoint() {
        return List.of(
            new Object[]{
                new AffineRatio(0, 0, 0, 0, 0, 0),
                new Point(0, 0),
                new Point(0, 0)
            },
            new Object[]{
                new AffineRatio(-0.2134, -0.1523, -0.3112, -0.7724, -0.4352, -0.1458),
                new Point(0, 0),
                new Point(-0.2134, -0.7724)
            },
            new Object[]{
                new AffineRatio(0.24, -0.5, 0.16, -0.27, 0.32, -0.74),
                new Point(1, 0),
                new Point(-0.26, 0.04999999999999999)
            },
            new Object[]{
                new AffineRatio(-0.12, 0.76, -0.55, 0.68, 0.48, -0.36),
                new Point(0, 1),
                new Point(-0.67, 0.32000000000000006)
            },
            new Object[]{
                new AffineRatio(0.43, 0.28, 0.91, -0.53, -0.22, 0.44),
                new Point(1, 1),
                new Point(1.62, -0.31)
            },
            new Object[]{
                new AffineRatio(0.2286, -0.1532, 0.2014, -0.1455, -0.8639, -0.0221),
                new Point(-1, -1),
                new Point(0.18040000000000003, 0.7405)
            },
            new Object[]{
                new AffineRatio(0.7175, 0.1839, 0.4627, 0.0151, -0.0664, 0.0985),
                new Point(0.5, 0.5),
                new Point(1.0408, 0.031150000000000004)
            },
            new Object[]{
                new AffineRatio(0.3155, -0.6324, 0.4934, -0.2208, 0.4343, 0.7663),
                new Point(10, 10),
                new Point(-1.0744999999999996, 11.7852)
            },
            new Object[]{
                new AffineRatio(-0.6930, -0.5782, -0.5777, 0.2607, 0.3476, 0.3548),
                new Point(-10, -10),
                new Point(10.866, -6.7633)
            },
            new Object[]{
                new AffineRatio(-0.1016, -0.7113, 0.7807, 0.1988, -0.3836, 0.8145),
                new Point(0.25, 0.25),
                new Point(-0.08425000000000005, 0.306525)
            },
            new Object[]{
                new AffineRatio(0.0724, -0.353, 0.6494, 0.2744, -0.6781, -0.225),
                new Point(-0.25, 0.25),
                new Point(0.323, 0.387675)
            },
            new Object[]{
                new AffineRatio(0.1735, 0.4861, -0.8375, -0.4703, -0.5226, -0.4718),
                new Point(1.5, 1.5),
                new Point(-0.35360000000000014, -1.9619)
            },
            new Object[]{
                new AffineRatio(-0.5897, -0.1563, 0.3144, -0.1754, 0.1172, -0.0645),
                new Point(-1.5, -2),
                new Point(-0.9840500000000001, -0.2222)
            },
            new Object[]{
                new AffineRatio(0.0159, 0.0646, -0.1511, -0.6876, -0.0333, 0.7537),
                new Point(2, 1.5),
                new Point(-0.08155000000000001, 0.37634999999999996)
            }
        );
    }

    private void checkRatioValue(double value) {
        final int minRatioValue = -1;
        final int maxRatioValue = 1;

        assertTrue(minRatioValue <= value && value < maxRatioValue);
    }
}
