package co.com.ias.certification.backend.order.ecxeptions;

import co.com.ias.certification.backend.order.domain.Order;
import co.com.ias.certification.backend.product.domain.Product;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor = "of")
public class OrderDoesNotExists extends OrderException {
    Order order;

    private OrderDoesNotExists(Order order) {
        super(String.format("Product %s don´t exist", order.getId()));
        this.order = order;
    }
}
