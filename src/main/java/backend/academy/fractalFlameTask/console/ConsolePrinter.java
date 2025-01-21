package backend.academy.fractalFlameTask.console;

import backend.academy.fractalFlameTask.dto.Arguments;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import static backend.academy.fractalFlameTask.constants.ConstValues.FISH_EYE_TRANSFORMATION;
import static backend.academy.fractalFlameTask.constants.ConstValues.GAMMA_CORRECTION_PROCESSOR;
import static backend.academy.fractalFlameTask.constants.ConstValues.HORSESHOE_TRANSFORMATION;
import static backend.academy.fractalFlameTask.constants.ConstValues.LINEAR_TRANSFORMATION;
import static backend.academy.fractalFlameTask.constants.ConstValues.SINUSOIDAL_TRANSFORMATION;
import static backend.academy.fractalFlameTask.constants.ConstValues.SPHERICAL_TRANSFORMATION;

@UtilityClass
public class ConsolePrinter {

    private static final PrintStream OUTPUT = System.out;

    public static void printErrorText(String errorText, String decisionText) {
        final String errorTextFormat = "Обнаружена ошибка: %s%n%s%n";

        OUTPUT.format(errorTextFormat, errorText, decisionText);
    }

    public static void printErrorText(String errorText) {
        final String errorTextFormat = "Обнаружена ошибка: %s%n";

        OUTPUT.format(errorTextFormat, errorText);
    }

    public static void printArguments(Arguments arguments) {
        if (arguments == null) {
            return;
        }

        final Map<String, Object> args = getArguments(arguments);
        printMap(args);
    }

    public static void printWorkingTime(long time) {
        final String format = "Время работы: %d мс%n";

        OUTPUT.format(format, time);
    }

    private static void printMap(Map<String, Object> map) {
        final String format = "%s: %s%n";

        for (Map.Entry<String, Object> element : map.entrySet()) {
            OUTPUT.format(format, element.getKey(), element.getValue());
        }
    }

    private static Map<String, Object> getArguments(Arguments args) {
        final Map<String, Object> arguments = new LinkedHashMap<>();

        arguments.put("Ширина изображения фрактального пламени", args.width());
        arguments.put("Высота изображения фрактального пламени", args.height());
        arguments.put("Количество афинных преобразований", args.affineTransformationsCount());
        arguments.put("Значение симметрии", args.symmetry());
        arguments.put("Использованные трансформации", getTransformationsValues(args));
        arguments.put("Количество итераций", args.iterationsCount());
        arguments.put("Количество потоков", args.threadsCount());
        arguments.put("Использованные преобразования результирующего изображения",
            getProcessorsValues(args));

        return arguments;
    }

    private static List<String> getTransformationsValues(Arguments args) {
        final Map<BooleanSupplier, String> transformationMapping = Map.of(
            args::fishEyeTransformation, FISH_EYE_TRANSFORMATION,
            args::horseshoeTransformation, HORSESHOE_TRANSFORMATION,
            args::linearTransformation, LINEAR_TRANSFORMATION,
            args::sinusoidalTransformation, SINUSOIDAL_TRANSFORMATION,
            args::sphericalTransformation, SPHERICAL_TRANSFORMATION
        );

        return transformationMapping.entrySet().stream()
            .filter(entry -> entry.getKey().getAsBoolean())
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());
    }

    private static List<String> getProcessorsValues(Arguments args) {
        final Map<BooleanSupplier, String> transformationMapping = Map.of(
            args::gammaCorrectionProcessor, GAMMA_CORRECTION_PROCESSOR
        );

        return transformationMapping.entrySet().stream()
            .filter(entry -> entry.getKey().getAsBoolean())
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());
    }
}
