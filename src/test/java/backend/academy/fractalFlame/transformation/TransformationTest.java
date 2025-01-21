package backend.academy.fractalFlame.transformation;

import backend.academy.fractalFlameTask.transformation.Transformation;
import org.junit.jupiter.api.Test;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_POINT_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class TransformationTest {

    protected abstract Transformation getTransformation();

    @Test
    public void transformNullPoint_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            getTransformation().transform(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_POINT_EXCEPTION_TEXT);
    }
}
