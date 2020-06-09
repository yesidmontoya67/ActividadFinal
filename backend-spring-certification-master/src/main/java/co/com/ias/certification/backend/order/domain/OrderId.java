package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderId implements NumberSerializable {
    public static OrderId fromNumber(Number number) {
        return new OrderId(number.longValue());
    }

    Long value;

    private OrderId(Long value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value >= 1);
        this.value = value;
    }

    @Override
    public Number valueOf() {
        return value;
    }

}
