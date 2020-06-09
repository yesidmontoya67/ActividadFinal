package co.com.ias.certification.backend.product.domain;

import co.com.ias.certification.backend.product.ecxeptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationSuccess  implements ProductOperation{
    Product value;
    @Override
    public Product value() {
        return value;
    }

    @Override
    public ProductException failure() {
        return null;
    }

    @Override
    public Boolean isValid() {
        return true;
    }
}
