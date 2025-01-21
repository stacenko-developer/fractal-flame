package backend.academy.fractalFlameTask.dto;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class Arguments {

    @Parameter(names = "--width", required = true, description = "Ширина изображения с фрактальным пламенем")
    private int width;

    @Parameter(names = "--height", required = true, description = "Высота изображения с фрактальным пламенем")
    private int height;

    @Parameter(names = "--affine-transformations-count",
        required = true, description = "Количество афинных преобразований")
    private int affineTransformationsCount;

    @Parameter(names = "--symmetry", required = true, description = "Значение симметрии")
    private int symmetry;

    @Parameter(names = "--iterations-count", required = true, description = "Количество итераций")
    private int iterationsCount;

    @Parameter(names = "--threads-count", required = true, description = "Количество потоков")
    private int threadsCount;

    @Parameter(names = "--help", help = true, description = "Получить справку по аргументам")
    private boolean help = false;

    @Parameter(names = "--fish-eye-transformation", description = "Использовать трансформацию 'Рыбий глаз'")
    private boolean fishEyeTransformation = false;

    @Parameter(names = "--horseshoe-transformation", description = "Использовать подковообразную трансформацию")
    private boolean horseshoeTransformation = false;

    @Parameter(names = "--linear-transformation", description = "Использовать линейную трансформацию")
    private boolean linearTransformation = false;

    @Parameter(names = "--sinusoidal-transformation", description = "Использовать синусоидальную трансформацию")
    private boolean sinusoidalTransformation = false;

    @Parameter(names = "--spherical-transformation", description = "Использовать сферическую трансформацию")
    private boolean sphericalTransformation = false;

    @Parameter(names = "--gamma-correction-processor", description = "Использовать гамма-коррекцию")
    private boolean gammaCorrectionProcessor = false;

    @Parameter(names = "--file-format", required = true,
        description = "Формат изображения фрактального пламени (PNG, JPEG, BMP)")
    private String imageFormat;
}
