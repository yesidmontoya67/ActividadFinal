package co.com.ias.certification.backend.product.ecxeptions;

import co.com.ias.certification.backend.product.domain.Product;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor = "of")
public class ProductDoesNotExists extends ProductException {
    Product product;

    private ProductDoesNotExists(Product product) {
        super(String.format("Product %s don´t exist", product.getName().getValue()));
        this.product = product;
    }
}
