package backend.academy.fractalFlameTask.dto;

import java.awt.Color;
import java.security.SecureRandom;
import lombok.Getter;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_POINT_EXCEPTION_TEXT;

@Getter
public class AffineRatio {

    private static final ThreadLocal<SecureRandom> THREAD_LOCAL_RANDOM
        = ThreadLocal.withInitial(SecureRandom::new);

    private final Color color;

    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public Point transform(Point point) {
        validatePoint(point);

        final double x = a + point.x() * b + point.y() * c;
        final double y = d + point.x() * e + point.y() * f;

        return new Point(x, y);
    }

    public AffineRatio(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;

        while (!isAffineRatio(this.a, this.b, this.d, this.e)) {
            generateAffineRatios();
        }

        color = getRandomColor();
    }

    public AffineRatio() {
        do {
            generateAffineRatios();
        } while (!isAffineRatio(a, b, d, e));

        color = getRandomColor();
    }

    private void generateAffineRatios() {
        a = getRandomRatio();
        b = getRandomRatio();
        c = getRandomRatio();
        d = getRandomRatio();
        e = getRandomRatio();
        f = getRandomRatio();
    }

    private Color getRandomColor() {
        final int maxColorValue = 255;

        return new Color(
            THREAD_LOCAL_RANDOM.get().nextInt(maxColorValue),
            THREAD_LOCAL_RANDOM.get().nextInt(maxColorValue),
            THREAD_LOCAL_RANDOM.get().nextInt(maxColorValue)
        );
    }

    private boolean isAffineRatio(double a, double b, double d, double e) {
        return ((a * a + d * d) < 1)
            && ((b * b + e * e) < 1)
            && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }

    private double getRandomRatio() {
        final int minValue = -1;
        final int maxValue = 1;

        return THREAD_LOCAL_RANDOM.get().nextDouble(minValue, maxValue);
    }

    private void validatePoint(Point point) {
        if (point == null) {
            throw new NullPointerException(NULL_POINT_EXCEPTION_TEXT);
        }
    }
}
