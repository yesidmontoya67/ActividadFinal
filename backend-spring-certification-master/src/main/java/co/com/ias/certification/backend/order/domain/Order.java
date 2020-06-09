package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.product.domain.Product;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class Order {
    Long id;
    Products products;
    Client client;
    Status status;
    Discount discount;
    Total total;


}
