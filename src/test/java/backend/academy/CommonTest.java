package backend.academy;

import backend.academy.fractalFlameTask.transformation.FishEyeTransformation;
import backend.academy.fractalFlameTask.transformation.HorseshoeTransformation;
import backend.academy.fractalFlameTask.transformation.LinearTransformation;
import backend.academy.fractalFlameTask.transformation.SinusoidalTransformation;
import backend.academy.fractalFlameTask.transformation.SphericalTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_SYMMETRY;

public class CommonTest {

    protected static final int DEFAULT_DEVIATION_VALUE = 100;

    protected static List<Integer> getNumbersByRange(int minNumber, int maxNumber) {
        return new ArrayList<>(IntStream.rangeClosed(minNumber, maxNumber)
            .boxed()
            .toList());
    }

    protected static List<Integer> getNumbersNotInRange(int minValue, int maxValue, int deviationValue) {
        final List<Integer> result = getNumbersByRange(minValue - deviationValue,
            minValue - 1);
        result.addAll(getNumbersByRange(maxValue + 1, maxValue + deviationValue));

        return result;
    }

    protected static List<Object[]> getAllCorrectArgumentsForGenerationSettings() {
        final List<Object[]> result = new ArrayList<>();
        final List<Transformation> transformations = List.of(
            new FishEyeTransformation(), new HorseshoeTransformation(), new LinearTransformation(),
            new SinusoidalTransformation(), new SphericalTransformation()
        );
        final int minSymmetry = MIN_SYMMETRY;
        final int maxSymmetry = minSymmetry + 10;

        int transformationIndex = 0;
        int iterationsCount = MIN_ITERATIONS_COUNT;

        for (int affine = MIN_AFFINE_TRANSFORMATIONS_COUNT; affine <= MAX_AFFINE_TRANSFORMATIONS_COUNT; affine++) {
            for (int symmetry = minSymmetry; symmetry <= maxSymmetry; symmetry++) {
                result.add(new Object[] {
                    affine,
                    symmetry,
                    transformations.subList(transformationIndex % transformations.size(), transformations.size()),
                    iterationsCount
                });

                transformationIndex++;
                iterationsCount++;
            }
        }

        return result;
    }
}
