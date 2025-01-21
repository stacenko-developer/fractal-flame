package backend.academy.fractalFlameTask.dto;

import backend.academy.fractalFlameTask.exception.IncorrectAffineTransformationsException;
import backend.academy.fractalFlameTask.exception.IncorrectIterationsCountException;
import backend.academy.fractalFlameTask.exception.IncorrectSymmetryException;
import backend.academy.fractalFlameTask.exception.IncorrectTransformationsListException;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_SYMMETRY;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_SYMMETRY;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_TRANSFORMATIONS_LIST_EXCEPTION_TEXT;

public record GenerationSettings(int affineTransformationsCount, int symmetry,
                                 List<Transformation> transformations,
                                 int iterationsCount) {

    public GenerationSettings {
        validateAffineTransformationsCount(affineTransformationsCount);
        validateSymmetry(symmetry);
        validateTransformations(transformations);
        validateIterationsCount(iterationsCount);
    }

    private void validateAffineTransformationsCount(int affineTransformationsCount) {
        if (affineTransformationsCount < MIN_AFFINE_TRANSFORMATIONS_COUNT
            || affineTransformationsCount > MAX_AFFINE_TRANSFORMATIONS_COUNT) {
            throw new IncorrectAffineTransformationsException();
        }
    }

    private void validateSymmetry(int symmetry) {
        if (symmetry < MIN_SYMMETRY || symmetry > MAX_SYMMETRY) {
            throw new IncorrectSymmetryException();
        }
    }

    private void validateTransformations(List<Transformation> transformations) {
        if (transformations == null) {
            throw new NullPointerException(NULL_TRANSFORMATIONS_LIST_EXCEPTION_TEXT);
        }

        if (transformations.isEmpty()) {
            throw new IncorrectTransformationsListException();
        }

        final int maxTransformationsCount = 5;
        final Set<Transformation> transformationsHashSet = new HashSet<>(maxTransformationsCount);

        for (Transformation transformation : transformations) {
            if (transformation == null) {
                throw new NullPointerException(NULL_TRANSFORMATIONS_LIST_EXCEPTION_TEXT);
            }

            if (!transformationsHashSet.add(transformation)) {
                throw new IncorrectTransformationsListException();
            }
        }
    }

    private void validateIterationsCount(int iterationsCount) {
        if (iterationsCount < MIN_ITERATIONS_COUNT || iterationsCount > MAX_ITERATIONS_COUNT) {
            throw new IncorrectIterationsCountException();
        }
    }
}
