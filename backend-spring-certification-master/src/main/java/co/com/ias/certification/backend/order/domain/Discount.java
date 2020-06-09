package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class Discount implements NumberSerializable {
    public static Discount fromNumber(Number number) {
        return new Discount(number.intValue());
    }
    Integer value;

    private Discount(int value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value >= 0 || value <=100);
        this.value = value;
    }


    @Override
    public Integer valueOf() {
        return value;
    }
}
