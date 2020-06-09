package co.com.ias.certification.backend.order.respositories;

import co.com.ias.certification.backend.order.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    OrderOperation insertOne(OrderRequest orderRequest);
    OrderOperation findById (OrderId ordertId);
    List<Order> findAll();
    OrderOperation updateOne(OrderId ordertId, OrderRequest orderRequest);
    OrderOperation deleteOne(OrderId ordertId);
    List<Order> findByClient(Client cleint);
}
