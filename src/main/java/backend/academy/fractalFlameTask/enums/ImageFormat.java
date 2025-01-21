package backend.academy.fractalFlameTask.enums;

import backend.academy.fractalFlameTask.exception.ImageFormatNotSupportedException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");

    private final String value;

    public static ImageFormat getImageFormatByValue(String value) {
        return Arrays.stream(ImageFormat.values())
            .filter(filterField -> filterField.value.equalsIgnoreCase(value))
            .findFirst()
            .orElseThrow(ImageFormatNotSupportedException::new);
    }
}
