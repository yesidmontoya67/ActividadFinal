package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.product.domain.Product;
import co.com.ias.certification.backend.serialization.StringSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Value (staticConstructor = "of")
public class Products implements StringSerializable {
    String value;

    private Products (String value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNoneBlank(value));
        Preconditions.checkArgument(value.length() >= 0);
        this.value=value;

    }

    @Override
    public String valueOf() {
        return value;
    }
}
