package backend.academy.fractalFlameTask.constants;

import backend.academy.fractalFlameTask.enums.ImageFormat;
import java.util.Arrays;
import lombok.experimental.UtilityClass;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_SYMMETRY;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_THREADS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_SYMMETRY;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_THREADS_COUNT;

@UtilityClass
public class ProblemSolving {

    public static final String INCORRECT_IMAGE_WIDTH_ERROR_SOLVING
        = "Необходимо указать ширину изображения в пределах: " + getRange(MIN_IMAGE_WIDTH, MAX_IMAGE_WIDTH);
    public static final String INCORRECT_IMAGE_HEIGHT_ERROR_SOLVING
        = "Необходимо указать высоту изображения в пределах: " + getRange(MIN_IMAGE_HEIGHT, MAX_IMAGE_HEIGHT);
    public static final String INCORRECT_AFFINE_TRANSFORMATIONS_ERROR_SOLVING =
        "Необходимо указать количество афинных преобразований в пределах: "
            + getRange(MIN_AFFINE_TRANSFORMATIONS_COUNT, MAX_AFFINE_TRANSFORMATIONS_COUNT);
    public static final String INCORRECT_SYMMETRY_ERROR_SOLVING
        = "Необходимо указать значение симметрии в пределах: "
        + getRange(MIN_SYMMETRY, MAX_SYMMETRY);
    public static final String INCORRECT_ITERATIONS_COUNT_ERROR_SOLVING
        = "Необходимо указать количество итераций в пределах: "
        + getRange(MIN_ITERATIONS_COUNT, MAX_ITERATIONS_COUNT);
    public static final String INCORRECT_THREADS_COUNT_ERROR_SOLVING
        = "Необходимо указать количество потоков в пределах: "
        + getRange(MIN_THREADS_COUNT, MAX_THREADS_COUNT);

    public static final String IMAGE_FORMAT_NOT_SUPPORTED_ERROR_SOLVING
        = "Указывайте допустимые форматы изображений: "
        + Arrays.toString(Arrays.stream(ImageFormat.values()).map(ImageFormat::value).toArray());

    public static final String INCORRECT_IMAGE_PIXELS_ERROR_SOLVING
        = "Убедитесь, что количество пикселей соответствует щирине и длине изображения";

    public static final String INCORRECT_TRANSFORMATIONS_LIST_ERROR_SOLVING
        = "Убедитесь, что список трансформаций не пустой и не содержит повторяющихся трансформаций";

    public static final String IMAGE_SAVE_ERROR_SOLVING
        = "Убедитесь, что вы указали правильный путь или попробуте данное действие позже";

    private static String getRange(int min, int max) {
        return "[" + min + ", " + max + "]";
    }
}
