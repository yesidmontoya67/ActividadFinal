package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.StringSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value(staticConstructor = "of")
public class Client implements StringSerializable {
    String value;

    private Client (String value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNoneBlank(value));
        Preconditions.checkArgument(value.length() <= 50);
        this.value=value;

    }

    @Override
    public String valueOf() {
        return value;
    }
}
