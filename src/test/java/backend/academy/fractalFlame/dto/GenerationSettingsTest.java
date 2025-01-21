package backend.academy.fractalFlame.dto;

import backend.academy.CommonTest;
import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.exception.IncorrectAffineTransformationsException;
import backend.academy.fractalFlameTask.exception.IncorrectIterationsCountException;
import backend.academy.fractalFlameTask.exception.IncorrectSymmetryException;
import backend.academy.fractalFlameTask.exception.IncorrectTransformationsListException;
import backend.academy.fractalFlameTask.transformation.FishEyeTransformation;
import backend.academy.fractalFlameTask.transformation.HorseshoeTransformation;
import backend.academy.fractalFlameTask.transformation.LinearTransformation;
import backend.academy.fractalFlameTask.transformation.SinusoidalTransformation;
import backend.academy.fractalFlameTask.transformation.SphericalTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_SYMMETRY;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_SYMMETRY;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_AFFINE_TRANSFORMATIONS_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_ITERATIONS_COUNT_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_SYMMETRY_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_TRANSFORMATIONS_LIST_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_TRANSFORMATIONS_LIST_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_AFFINE_TRANSFORMATIONS_ERROR_SOLVING;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_ITERATIONS_COUNT_ERROR_SOLVING;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_SYMMETRY_ERROR_SOLVING;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_TRANSFORMATIONS_LIST_ERROR_SOLVING;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerationSettingsTest extends CommonTest {

    private final int defaultAffineTransformationsCount = MIN_AFFINE_TRANSFORMATIONS_COUNT;
    private final int defaultSymmetry = MIN_SYMMETRY;
    private final List<Transformation> defaultTransformations = List.of(new LinearTransformation());
    private final int defaultIterationsCount = MIN_ITERATIONS_COUNT;

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateGenerationSettingsWithIncorrectAffineTransformations")
    public void createSettingsWithIncorrectAffineTransformations_ShouldThrowIncorrectAffineTransformationsException(
        int incorrectAffineTransformationsCount
    ) {
        assertThatThrownBy(() -> {
            new GenerationSettings(incorrectAffineTransformationsCount,
                defaultSymmetry, defaultTransformations, defaultIterationsCount);
        }).isInstanceOf(IncorrectAffineTransformationsException.class)
            .satisfies(exception -> {
                final IncorrectAffineTransformationsException ex = (IncorrectAffineTransformationsException) exception;
                assertEquals(INCORRECT_AFFINE_TRANSFORMATIONS_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_AFFINE_TRANSFORMATIONS_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateGenerationSettingsWithIncorrectSymmetry")
    public void createGenerationSettingsWithIncorrectSymmetry_ShouldThrowIncorrectSymmetryException(
        int incorrectSymmetry
    ) {
        assertThatThrownBy(() -> {
            new GenerationSettings(defaultAffineTransformationsCount,
                incorrectSymmetry, defaultTransformations, defaultIterationsCount);
        }).isInstanceOf(IncorrectSymmetryException.class)
            .satisfies(exception -> {
                final IncorrectSymmetryException ex = (IncorrectSymmetryException) exception;
                assertEquals(INCORRECT_SYMMETRY_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_SYMMETRY_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateGenerationSettingsWithIncorrectIterationsCount")
    public void createGenerationSettingsWithIncorrectIterationsCount_ShouldThrowIncorrectIterationsCountException(
        int incorrectIterationsCount
    ) {
        assertThatThrownBy(() -> {
            new GenerationSettings(defaultAffineTransformationsCount,
                defaultSymmetry, defaultTransformations, incorrectIterationsCount);
        }).isInstanceOf(IncorrectIterationsCountException.class)
            .satisfies(exception -> {
                final IncorrectIterationsCountException ex = (IncorrectIterationsCountException) exception;
                assertEquals(INCORRECT_ITERATIONS_COUNT_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_ITERATIONS_COUNT_EXCEPTION_TEXT);
    }

    @Test
    public void createGenerationSettingsWithNullTransformations_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new GenerationSettings(defaultAffineTransformationsCount,
                defaultSymmetry, null, defaultIterationsCount);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_TRANSFORMATIONS_LIST_EXCEPTION_TEXT);
    }

    @Test
    public void createGenerationSettingsWithEmptyTransformations_ShouldThrowIncorrectTransformationsListException() {
        assertThatThrownBy(() -> {
            new GenerationSettings(defaultAffineTransformationsCount,
                defaultSymmetry, new ArrayList<>(), defaultIterationsCount);
        }).isInstanceOf(IncorrectTransformationsListException.class)
            .satisfies(exception -> {
                final IncorrectTransformationsListException ex = (IncorrectTransformationsListException) exception;
                assertEquals(INCORRECT_TRANSFORMATIONS_LIST_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_TRANSFORMATIONS_LIST_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateGenerationSettingsWithNullTransformation")
    public void createGenerationSettingsWithNullTransformation_ShouldThrowNullPointerException(
        List<Transformation> incorrectTransformations
    ) {
        assertThatThrownBy(() -> {
            new GenerationSettings(defaultAffineTransformationsCount,
                defaultSymmetry, incorrectTransformations, defaultIterationsCount);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_TRANSFORMATIONS_LIST_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateGenerationSettingsWithDuplicateTransformation")
    public void createGenerationSettingsWithDuplicateTransformations_ShouldThrowIncorrectTransformationsListException(
        List<Transformation> incorrectTransformations
    ) {
        assertThatThrownBy(() -> {
            new GenerationSettings(defaultAffineTransformationsCount,
                defaultSymmetry, incorrectTransformations, defaultIterationsCount);
        }).isInstanceOf(IncorrectTransformationsListException.class)
            .satisfies(exception -> {
                final IncorrectTransformationsListException ex = (IncorrectTransformationsListException) exception;
                assertEquals(INCORRECT_TRANSFORMATIONS_LIST_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_TRANSFORMATIONS_LIST_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getAllCorrectArgumentsForGenerationSettings")
    public void createGenerationsSettingsWithCorrectArguments_ShouldCreateGenerationsSettings(
        int affineTransformationsCount, int symmetry,
        List<Transformation> transformations,
        int iterationsCount
    ) {
        final GenerationSettings generationSettings = new GenerationSettings(
            affineTransformationsCount, symmetry, transformations, iterationsCount
        );

        assertEquals(affineTransformationsCount, generationSettings.affineTransformationsCount());
        assertEquals(symmetry, generationSettings.symmetry());
        assertEquals(transformations, generationSettings.transformations());
        assertEquals(iterationsCount, generationSettings.iterationsCount());
    }

    private static List<List<Transformation>> getArgumentsForCreateGenerationSettingsWithDuplicateTransformation() {
        return List.of(
            Arrays.asList(new FishEyeTransformation(), new FishEyeTransformation(), new HorseshoeTransformation(),
                new LinearTransformation(), new SinusoidalTransformation(), new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), new HorseshoeTransformation(), new HorseshoeTransformation(),
                new LinearTransformation(), new SinusoidalTransformation(), new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), new HorseshoeTransformation(), new LinearTransformation(),
                new LinearTransformation(), new SinusoidalTransformation(), new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), new HorseshoeTransformation(), new LinearTransformation(),
                new SinusoidalTransformation(), new SinusoidalTransformation(), new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), new HorseshoeTransformation(), new LinearTransformation(),
                new SinusoidalTransformation(), new SphericalTransformation(), new SphericalTransformation()
            )
        );
    }

    private static List<List<Transformation>> getArgumentsForCreateGenerationSettingsWithNullTransformation() {
        return List.of(
            Arrays.asList(null, new HorseshoeTransformation(), new LinearTransformation(),
                new SinusoidalTransformation(), new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), null, new LinearTransformation(),
                new SinusoidalTransformation(), new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), new HorseshoeTransformation(), null,
                new SinusoidalTransformation(), new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), new HorseshoeTransformation(), new LinearTransformation(),
                null, new SphericalTransformation()
            ),
            Arrays.asList(new FishEyeTransformation(), new HorseshoeTransformation(), new LinearTransformation(),
                new SinusoidalTransformation(), null
            )
        );
    }

    private static List<Integer> getArgumentsForCreateGenerationSettingsWithIncorrectIterationsCount() {
        return getNumbersNotInRange(MIN_ITERATIONS_COUNT, MAX_ITERATIONS_COUNT, DEFAULT_DEVIATION_VALUE);
    }

    private static List<Integer> getArgumentsForCreateGenerationSettingsWithIncorrectSymmetry() {
        return getNumbersNotInRange(MIN_SYMMETRY, MAX_SYMMETRY, DEFAULT_DEVIATION_VALUE);
    }

    private static List<Integer> getArgumentsForCreateGenerationSettingsWithIncorrectAffineTransformations() {
        return getNumbersNotInRange(MIN_AFFINE_TRANSFORMATIONS_COUNT,
            MAX_AFFINE_TRANSFORMATIONS_COUNT, DEFAULT_DEVIATION_VALUE);
    }
}
