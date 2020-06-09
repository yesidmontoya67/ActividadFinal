package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.order.ecxeptions.OrderException;
import co.com.ias.certification.backend.product.domain.Product;
import co.com.ias.certification.backend.product.domain.ProductOperation;
import co.com.ias.certification.backend.product.ecxeptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderOperationFailure implements OrderOperation {
 OrderException exception;

    @Override
    public Order value() {
        return null;
    }

    @Override
    public OrderException failure() {
        return exception;
    }

    @Override
    public Boolean isValid() {
        return false;
    }
}
