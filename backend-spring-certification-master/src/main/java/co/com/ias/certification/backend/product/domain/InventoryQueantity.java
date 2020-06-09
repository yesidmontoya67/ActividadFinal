package co.com.ias.certification.backend.product.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class InventoryQueantity implements NumberSerializable {
    public static InventoryQueantity fromNumber(Number number) {
        return new InventoryQueantity(number.intValue());
    }

    Integer value;

    private InventoryQueantity(int value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value >= 0);
        this.value = value;
    }


    @Override
    public Integer valueOf() {
        return value;
    }
}
