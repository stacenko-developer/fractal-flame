package backend.academy.fractalFlameTask.generator;

import backend.academy.fractalFlameTask.dto.AffineRatio;
import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.Pixel;
import backend.academy.fractalFlameTask.dto.Point;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.IntStream;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_GENERATION_SETTINGS_EXCEPTION_TEXT;

public abstract class FractalFlameGenerator extends ImageGenerator {

    private static final double MIN_X_VALUE = -4;
    private static final double MAX_X_VALUE = 4;
    private static final double MIN_Y_VALUE = -3;
    private static final double MAX_Y_VALUE = 3;

    private static final ThreadLocal<SecureRandom> THREAD_LOCAL_RANDOM
        = ThreadLocal.withInitial(SecureRandom::new);

    private final int affineTransformationsCount;
    private final int symmetry;
    private final List<Transformation> transformations;

    protected final EmptyImageGenerator emptyImageGenerator = new EmptyImageGenerator();
    protected final int iterationsCount;

    protected FractalFlameGenerator(GenerationSettings generationSettings) {
        this.affineTransformationsCount = generationSettings.affineTransformationsCount();
        this.symmetry = generationSettings.symmetry();
        this.transformations = generationSettings.transformations();
        this.iterationsCount = generationSettings.iterationsCount();
    }

    protected static void validateGenerationSettings(GenerationSettings generationSettings) {
        if (generationSettings == null) {
            throw new NullPointerException(NULL_GENERATION_SETTINGS_EXCEPTION_TEXT);
        }
    }

    protected List<AffineRatio> getAffineRatios() {
        return IntStream.range(0, affineTransformationsCount)
            .mapToObj(value -> new AffineRatio())
            .toList();
    }

    protected void generateOneThread(Image image, int iterationsCount) {
        final int stepsForNormalization = 20;
        final List<AffineRatio> affineRatios = getAffineRatios();
        Point currentPoint = getRandomPoint();

        for (int step = -stepsForNormalization; step < iterationsCount; ++step) {
            final AffineRatio affineRatio = affineRatios
                .get(THREAD_LOCAL_RANDOM.get().nextInt(affineRatios.size()));
            final Transformation transformation = transformations
                .get(THREAD_LOCAL_RANDOM.get().nextInt(transformations.size()));

            currentPoint = affineRatio.transform(currentPoint);
            currentPoint = transformation.transform(currentPoint);

            if (step <= 0) {
                continue;
            }

            double theta = 0.0;

            for (int segment = 0; segment < symmetry; theta += Math.PI * 2 / symmetry, ++segment) {
                final Point point = rotatePoint(currentPoint, theta);
                updateImage(image, point, affineRatio);
            }
        }
    }

    private Point rotatePoint(Point point, double angle) {
        final double centerX =  MIN_X_VALUE + (MAX_X_VALUE - MIN_X_VALUE) / 2;
        final double centerY = MIN_Y_VALUE + (MAX_Y_VALUE - MIN_Y_VALUE) / 2;
        final double x = (point.x() - centerX) * Math.cos(angle)
            - (point.y() - centerY) * Math.sin(angle) + centerX;
        final double y = (point.x() - centerX) * Math.sin(angle)
            + (point.y() - centerY) * Math.cos(angle) + centerY;

        return new Point(x, y);
    }

    private void updateImage(Image image, Point point, AffineRatio affine) {
        if (!isPointInRange(point)) {
            return;
        }

        final Pixel pixel = resolvePixel(point, image);

        if (pixel != null) {
            synchronized (pixel) {
                pixel.updateHitCount(affine.color());
            }
        }
    }

    private Pixel resolvePixel(Point point, Image image) {
        return image.pixel(
            (int) ((point.x() - MIN_X_VALUE) / (MAX_X_VALUE - MIN_X_VALUE) * image.imageSize().width()),
            (int) ((point.y() - MIN_Y_VALUE) / (MAX_Y_VALUE - MIN_Y_VALUE) * image.imageSize().height())
        );
    }

    private boolean isPointInRange(Point point) {
        return point.x() >= MIN_X_VALUE
            && point.x() < MAX_X_VALUE
            && point.y() >= MIN_Y_VALUE
            && point.y() < MAX_Y_VALUE;
    }

    private Point getRandomPoint() {
        return new Point(
            THREAD_LOCAL_RANDOM.get().nextDouble(MIN_X_VALUE, MAX_X_VALUE),
            THREAD_LOCAL_RANDOM.get().nextDouble(MIN_Y_VALUE, MAX_Y_VALUE)
        );
    }
}
