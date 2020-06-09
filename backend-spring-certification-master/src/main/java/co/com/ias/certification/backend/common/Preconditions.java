package co.com.ias.certification.backend.common;

import java.util.Objects;

public class Preconditions {

    public static void checkNotNull(Object reference) {
        if(Objects.isNull(reference)){
            throw new NullPointerException();
        }
    }

    public static void checkArgument(boolean argument) {
        if(!argument) {
            throw new IllegalArgumentException();
        }
    }
}
