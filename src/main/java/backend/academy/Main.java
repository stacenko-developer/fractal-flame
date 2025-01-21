package backend.academy;

import backend.academy.fractalFlameTask.FractalFlame;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        new FractalFlame().start(args);
    }
}
