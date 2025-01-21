package backend.academy.fractalFlameTask.constants;

import java.io.File;
import java.nio.file.Paths;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class ConstValues {

    public static final int MIN_IMAGE_HEIGHT = 100;
    public static final int MAX_IMAGE_HEIGHT = 3000;
    public static final int MIN_IMAGE_WIDTH = 100;
    public static final int MAX_IMAGE_WIDTH = 3000;

    public static final int MIN_AFFINE_TRANSFORMATIONS_COUNT = 1;
    public static final int MAX_AFFINE_TRANSFORMATIONS_COUNT = 20;

    public static final int MIN_SYMMETRY = 1;
    public static final int MAX_SYMMETRY = 360;

    public static final int MIN_ITERATIONS_COUNT = 200_000;
    public static final int MAX_ITERATIONS_COUNT = 100_000_000;

    public static final int MIN_THREADS_COUNT = 1;
    public static final int MAX_THREADS_COUNT = 4;

    public static final String BASE_PATH = Paths.get(StringUtils.EMPTY).toAbsolutePath()
        + File.separator;
    public static final String RESOURCES_PATH = "src"
        + File.separator + "main"
        + File.separator + "resources" + File.separator;

    public static final String FISH_EYE_TRANSFORMATION = "Рыбий глаз";
    public static final String HORSESHOE_TRANSFORMATION = "Подковообразная";
    public static final String LINEAR_TRANSFORMATION = "Линейная";
    public static final String SINUSOIDAL_TRANSFORMATION = "Синусоидальная";
    public static final String SPHERICAL_TRANSFORMATION = "Сферическая";

    public static final String GAMMA_CORRECTION_PROCESSOR = "Гамма-коррекция";

}
