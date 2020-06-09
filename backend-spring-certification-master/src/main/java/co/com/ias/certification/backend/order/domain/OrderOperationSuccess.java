package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.order.ecxeptions.OrderException;
import co.com.ias.certification.backend.product.domain.Product;
import co.com.ias.certification.backend.product.domain.ProductOperation;
import co.com.ias.certification.backend.product.ecxeptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderOperationSuccess  implements OrderOperation {
    Order value;
    @Override
    public Order value() {
        return value;
    }

    @Override
    public OrderException failure() {
        return null;
    }

    @Override
    public Boolean isValid() {
        return true;
    }
}
