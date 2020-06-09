package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Total implements NumberSerializable {
    public static Total fromNumber(Number number) {
        return new Total(BigDecimal.valueOf(number.doubleValue()));
    }
    BigDecimal value;

    private Total(BigDecimal value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0);
        this.value = value;
    }


    @Override
    public BigDecimal valueOf() {
        return value;
    }
}

