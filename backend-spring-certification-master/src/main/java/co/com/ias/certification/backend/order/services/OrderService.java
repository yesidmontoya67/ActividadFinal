package co.com.ias.certification.backend.order.services;

import co.com.ias.certification.backend.order.domain.*;
import co.com.ias.certification.backend.order.respositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderOperation createOrder(OrderRequest orderRequest){
        List<Order> orders= repository.findByClient(orderRequest.getClient());
        int discount = (orders.size()/3)*10;
        OrderRequest request= OrderRequest.of(orderRequest.getProducts(),orderRequest.getClient(),orderRequest.getStatus(), Discount.of(discount),orderRequest.getTotal());
        return repository.insertOne(request);
    }

    public OrderOperation findOrderById(OrderId orderId) {
        return repository.findById(orderId);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public OrderOperation updateOrder(OrderId orderId,OrderRequest orderRequest) {
        return repository.updateOne(orderId,orderRequest);
    }

    public OrderOperation deleteOrder(OrderId orderId) {
        return repository.deleteOne(orderId);
    }
}
