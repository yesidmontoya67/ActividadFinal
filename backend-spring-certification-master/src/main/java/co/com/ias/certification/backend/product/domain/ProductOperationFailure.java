package co.com.ias.certification.backend.product.domain;

import co.com.ias.certification.backend.product.ecxeptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationFailure implements ProductOperation {
 ProductException exception;

    @Override
    public Product value() {
        return null;
    }

    @Override
    public ProductException failure() {
        return exception;
    }

    @Override
    public Boolean isValid() {
        return false;
    }
}
