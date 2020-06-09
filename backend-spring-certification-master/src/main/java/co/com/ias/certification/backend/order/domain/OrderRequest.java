package co.com.ias.certification.backend.order.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderRequest {
    Products products;
    Client client;
    Status status;
    Discount discount;
    Total total;
}
