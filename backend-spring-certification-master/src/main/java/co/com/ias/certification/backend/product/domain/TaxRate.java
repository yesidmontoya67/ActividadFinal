package co.com.ias.certification.backend.product.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.NumberSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class TaxRate implements NumberSerializable {
    public static TaxRate fromNumber(Number number) {
        return new TaxRate(BigDecimal.valueOf(number.doubleValue()));
    }

    BigDecimal value;

    private TaxRate(BigDecimal value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0 && value.compareTo(new BigDecimal("1"))<=0);  //El compareTo retorna 0 si el numeor es igual retorna -1 si es menor y retorna 1 si es mayor
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
