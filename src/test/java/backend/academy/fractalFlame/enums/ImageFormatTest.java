package backend.academy.fractalFlame.enums;

import backend.academy.fractalFlameTask.enums.ImageFormat;
import backend.academy.fractalFlameTask.exception.ImageFormatNotSupportedException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.IMAGE_FORMAT_NOT_SUPPORTED_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.IMAGE_FORMAT_NOT_SUPPORTED_ERROR_SOLVING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageFormatTest {

    @ParameterizedTest
    @EnumSource(ImageFormat.class)
    public void getImageFormatByValue_ShouldReturnImageFormat(ImageFormat imageFormat) {
        assertEquals(imageFormat, ImageFormat.getImageFormatByValue(imageFormat.value()));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGetEnumByIncorrectValue")
    public void getImageFormatByIncorrectValue_ShouldThrowImageFormatNotSupportedException(String incorrectValue) {
        assertThatThrownBy(() -> {
            ImageFormat.getImageFormatByValue(incorrectValue);
        }).isInstanceOf(ImageFormatNotSupportedException.class)
            .satisfies(exception -> {
                final ImageFormatNotSupportedException ex = (ImageFormatNotSupportedException) exception;
                assertEquals(IMAGE_FORMAT_NOT_SUPPORTED_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(IMAGE_FORMAT_NOT_SUPPORTED_EXCEPTION_TEXT);
    }

    private static String[] getArgumentsForGetEnumByIncorrectValue() {
        return new String[] {
            null, "", " ", "   ", "Неизвестный ключ"
        };
    }
}
