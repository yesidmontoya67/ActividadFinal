package co.com.ias.certification.backend.order.domain;

import co.com.ias.certification.backend.order.ecxeptions.OrderException;


public interface OrderOperation {
    Order value();
    OrderException failure();
    Boolean isValid();
}
