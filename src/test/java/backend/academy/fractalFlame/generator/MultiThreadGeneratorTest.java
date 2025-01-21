package backend.academy.fractalFlame.generator;

import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.exception.IncorrectThreadsCountException;
import backend.academy.fractalFlameTask.generator.ImageGenerator;
import backend.academy.fractalFlameTask.generator.MultiThreadGenerator;
import backend.academy.fractalFlameTask.generator.OneThreadGenerator;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_THREADS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_THREADS_COUNT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_THREADS_COUNT_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_GENERATION_SETTINGS_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_THREADS_COUNT_ERROR_SOLVING;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadGeneratorTest extends ImageGeneratorTest {

    private final int defaultThreadsCount = 4;

    @Override
    protected ImageGenerator getImageGenerator() {
        return MultiThreadGenerator.create(defaultGenerationSettings, defaultThreadsCount);
    }

    @Test
    public void createMultiThreadGeneratorWithNullGenerationSettings_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            MultiThreadGenerator.create(null, defaultThreadsCount);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_GENERATION_SETTINGS_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateMultiThreadGeneratorWithIncorrectThreadsCount")
    public void createMultiThreadGeneratorWithIncorrectThreadsCount_ShouldThrowIncorrectThreadsCountException(
        int incorrectThreadsCount
    ) {
        assertThatThrownBy(() -> {
            MultiThreadGenerator.create(defaultGenerationSettings, incorrectThreadsCount);
        }).isInstanceOf(IncorrectThreadsCountException.class)
            .satisfies(exception -> {
                final IncorrectThreadsCountException ex = (IncorrectThreadsCountException) exception;
                assertEquals(INCORRECT_THREADS_COUNT_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_THREADS_COUNT_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getAllCorrectArgumentsForGenerationSettings")
    public void generateFractalFrameByMultiThreadGeneratorWithCorrectGenerationSettings_ShouldCorrectlyGenerate(
        int affineTransformationsCount, int symmetry,
        List<Transformation> transformations,
        int iterationsCount
    ) {
        final GenerationSettings generationSettings = new GenerationSettings(
            affineTransformationsCount, symmetry, transformations, iterationsCount
        );
        final MultiThreadGenerator multiThreadGenerator = MultiThreadGenerator.create(
            generationSettings, defaultThreadsCount
        );
        final Image image = multiThreadGenerator.generate(defaultImageSize);

        assertNotNull(image);
    }

    @Test
    public void generateFractalFrameByMultiThreadGenerator_ShouldBeFasterThatOneThreadGenerator() {
        final long startTimeForOneThreadGeneration = System.currentTimeMillis();
        OneThreadGenerator.create(defaultGenerationSettings).generate(defaultImageSize);
        final long endTimeForOneThreadGeneration = System.currentTimeMillis();
        final long timeForOneThreadGeneration
            = endTimeForOneThreadGeneration - startTimeForOneThreadGeneration;

        final long startTimeForMultiThreadGeneration = System.currentTimeMillis();
        MultiThreadGenerator.create(defaultGenerationSettings, defaultThreadsCount).generate(defaultImageSize);
        final long endTimeForMultiThreadGeneration = System.currentTimeMillis();
        final long timeForMultiThreadGeneration
            = endTimeForMultiThreadGeneration - startTimeForMultiThreadGeneration;

        assertTrue(timeForMultiThreadGeneration < timeForOneThreadGeneration);
    }

    private static List<Integer> getArgumentsForCreateMultiThreadGeneratorWithIncorrectThreadsCount() {
        return getNumbersNotInRange(MIN_THREADS_COUNT, MAX_THREADS_COUNT, DEFAULT_DEVIATION_VALUE);
    }
}
