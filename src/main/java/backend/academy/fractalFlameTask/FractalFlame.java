package backend.academy.fractalFlameTask;

import backend.academy.fractalFlameTask.console.ConsolePrinter;
import backend.academy.fractalFlameTask.dto.Arguments;
import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.enums.ImageFormat;
import backend.academy.fractalFlameTask.exception.FractalFlameAbstractException;
import backend.academy.fractalFlameTask.generator.FractalFlameGenerator;
import backend.academy.fractalFlameTask.generator.MultiThreadGenerator;
import backend.academy.fractalFlameTask.generator.OneThreadGenerator;
import backend.academy.fractalFlameTask.processor.GammaCorrectionImageProcessor;
import backend.academy.fractalFlameTask.processor.ImageProcessor;
import backend.academy.fractalFlameTask.saver.ImageSaver;
import backend.academy.fractalFlameTask.transformation.FishEyeTransformation;
import backend.academy.fractalFlameTask.transformation.HorseshoeTransformation;
import backend.academy.fractalFlameTask.transformation.LinearTransformation;
import backend.academy.fractalFlameTask.transformation.SinusoidalTransformation;
import backend.academy.fractalFlameTask.transformation.SphericalTransformation;
import backend.academy.fractalFlameTask.transformation.Transformation;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static backend.academy.fractalFlameTask.constants.ConstValues.BASE_PATH;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.UNKNOWN_ERROR_EXCEPTION_TEXT;

public class FractalFlame {

    public void start(String[] args) {
        try {
            final Arguments arguments = new Arguments();
            final JCommander jCommander = JCommander.newBuilder()
                .addObject(arguments)
                .build();
            jCommander.parse(args);

            if (arguments.help()) {
                jCommander.usage();
                return;
            }

            final ImageSize imageSize = new ImageSize(arguments.width(), arguments.height());
            final GenerationSettings generationSettings = new GenerationSettings(
                arguments.affineTransformationsCount(),
                arguments.symmetry(),
                getTransformations(arguments),
                arguments.iterationsCount()
            );
            final int threadsCount = arguments.threadsCount();
            final ImageFormat format = ImageFormat.getImageFormatByValue(arguments.imageFormat());
            final ImageSaver imageSaver = new ImageSaver();

            final FractalFlameGenerator fractalFlameGenerator
                = getFractalFlameGenerator(threadsCount, generationSettings);
            final String filename = "fractal-flame";
            final Path path = getPath(filename, format);

            final long startTime = System.currentTimeMillis();
            final Image fractalFlameResult = fractalFlameGenerator.generate(imageSize);
            final long endTime = System.currentTimeMillis();
            final long workingTimeMillis = endTime - startTime;

            for (ImageProcessor processor : getImageProcessors(arguments)) {
                processor.process(fractalFlameResult);
            }

            imageSaver.save(fractalFlameResult, path, format);

            ConsolePrinter.printWorkingTime(workingTimeMillis);
            ConsolePrinter.printArguments(arguments);
        } catch (Exception ex) {
            handleException(ex);
        }
    }

    private Path getPath(String filename, ImageFormat imageFormat) {
        return Path.of(String.format("%s%s.%s", BASE_PATH, filename, imageFormat.toString().toLowerCase()));
    }

    private FractalFlameGenerator getFractalFlameGenerator(int threadsCount,
        GenerationSettings generationSettings
    ) {
        final int threadsCountForSingleThreadGeneration = 1;

        return threadsCount == threadsCountForSingleThreadGeneration
            ? OneThreadGenerator.create(generationSettings)
            : MultiThreadGenerator.create(generationSettings, threadsCount);
    }

    private List<ImageProcessor> getImageProcessors(Arguments arguments) {
        final List<ImageProcessor> processors = new ArrayList<>();

        if (arguments.gammaCorrectionProcessor()) {
            processors.add(new GammaCorrectionImageProcessor());
        }

        return processors;
    }

    private List<Transformation> getTransformations(Arguments arguments) {
        final Map<BooleanSupplier, Supplier<Transformation>> transformationMapping = Map.of(
            arguments::fishEyeTransformation, FishEyeTransformation::new,
            arguments::horseshoeTransformation, HorseshoeTransformation::new,
            arguments::linearTransformation, LinearTransformation::new,
            arguments::sinusoidalTransformation, SinusoidalTransformation::new,
            arguments::sphericalTransformation, SphericalTransformation::new
        );

        return transformationMapping.entrySet().stream()
            .filter(entry -> entry.getKey().getAsBoolean())
            .map(entry -> entry.getValue().get())
            .collect(Collectors.toList());
    }

    private void handleException(Exception ex) {
        if (ex instanceof FractalFlameAbstractException fractalFlameAbstractException) {
            ConsolePrinter.printErrorText(
                fractalFlameAbstractException.getMessage(), fractalFlameAbstractException.getSolution()
            );
        } else if (ex instanceof ParameterException) {
            ConsolePrinter.printErrorText(ex.getMessage());
        } else {
            ConsolePrinter.printErrorText(UNKNOWN_ERROR_EXCEPTION_TEXT);
        }
    }
}
