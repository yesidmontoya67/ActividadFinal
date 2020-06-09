package co.com.ias.certification.backend.product.domain;

import co.com.ias.certification.backend.product.ecxeptions.ProductException;

public interface ProductOperation {
    Product value();
    ProductException failure();
    Boolean isValid();
}
