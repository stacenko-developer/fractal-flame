package backend.academy.fractalFlameTask.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionTextValues {
    public static final String UNKNOWN_ERROR_EXCEPTION_TEXT = "Неизвестная ошибка";

    public static final String NULL_POINT_EXCEPTION_TEXT = "Точка не должна быть null";
    public static final String NULL_IMAGE_EXCEPTION_TEXT = "Изображение не должно быть null";
    public static final String NULL_PIXELS_EXCEPTION_TEXT = "Пиксели не должны быть null";
    public static final String NULL_FILE_PATH_EXCEPTION_TEXT = "Путь к файлу не должен быть null";
    public static final String NULL_IMAGE_FORMAT_EXCEPTION_TEXT = "Формат изображения не должен быть null";
    public static final String NULL_COLOR_EXCEPTION_TEXT = "Цвет не должен быть null";
    public static final String NULL_IMAGE_SIZE_EXCEPTION_TEXT = "Размеры изображения не должны быть null";
    public static final String NULL_GENERATION_SETTINGS_EXCEPTION_TEXT = "Настройки генерации не должны бьть null";
    public static final String NULL_TRANSFORMATIONS_LIST_EXCEPTION_TEXT = "Список трансформаций не должен быть null";

    public static final String IMAGE_SAVE_EXCEPTION_TEXT = "Ошибка при сохранении изображения";

    public static final String INCORRECT_IMAGE_HEIGHT_EXCEPTION_TEXT
        = "Высота изображения вышла за допустимые пределы";
    public static final String INCORRECT_IMAGE_WIDTH_EXCEPTION_TEXT
        = "Ширина изображения вышла за допустимые пределы";
    public static final String INCORRECT_AFFINE_TRANSFORMATIONS_EXCEPTION_TEXT
        = "Количество афинных преобразований вышло за допустимые пределы";
    public static final String INCORRECT_SYMMETRY_EXCEPTION_TEXT
        = "Значение симметрии вышло за допустимые пределы";
    public static final String INCORRECT_ITERATIONS_COUNT_EXCEPTION_TEXT
        = "Количество итераций вышло за допустимые пределы";
    public static final String INCORRECT_THREADS_COUNT_EXCEPTION_TEXT
        = "Количество потоков вышло за допустимые пределы";
    public static final String INCORRECT_TRANSFORMATIONS_LIST_EXCEPTION_TEXT
        = "Список трансформаций представлен в некорректном формате";

    public static final String IMAGE_FORMAT_NOT_SUPPORTED_EXCEPTION_TEXT
        = "Данный формат изображения не поддерживается";

    public static final String INCORRECT_IMAGE_PIXELS_EXCEPTION_TEXT
        = "Некорректное количество пикселей для изображения с учетом его размеров";

    public static final String DIVISION_BY_ZERO_IN_TRANSFORMATION_EXCEPTION_TEXT
        = "Данные значения координат приводят к делению на 0";
}
