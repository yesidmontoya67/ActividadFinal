package co.com.ias.certification.backend.order.respositories;

import co.com.ias.certification.backend.order.domain.*;
import co.com.ias.certification.backend.order.ecxeptions.OrderDoesNotExists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


public class SqlOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;


    public SqlOrderRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    private final RowMapper<Order> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("ID");
        Products products = Products.of(resultSet.getString("PRODUCTS"));
        Client client = Client.of(resultSet.getString("CLIENT"));
        Total total= Total.of(resultSet.getBigDecimal("TOTAL"));
        Discount discount= Discount.of(resultSet.getInt("DISCOUNT"));
        Status status= Status.valueOf(resultSet.getString("STATUS"));

        return Order.of(id, products,client,status,discount,total);
    };

    @Override
    public OrderOperation insertOne(OrderRequest orderRequest) {
        /*Map<String, Object> parameters = new HashMap<>();
        parameters.put("NAME", productoperationRequest.getName().getValue());
        parameters.put("DESCRIPTION", productoperationRequest.getDescription().getValue());
        parameters.put("BASE_PRICE", productoperationRequest.getBasePrice().getValue());
        parameters.put("TAX_RATE", productoperationRequest.getTaxRate().getValue());
        parameters.put("PRODUCT_STATUS", productoperationRequest.getProductStatus());
        parameters.put("INVENTORY_QUANTITY", productoperationRequest.getInventoryQueantity().getValue());



        Number number = simpleJdbcInsert.executeAndReturnKey(parameters);
        long id = number.longValue();
        */
        String SQL = "INSERT INTO ORDERS (PRODUCTS, CLIENT, TOTAL, DISCOUNT, STATUS) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // inicializada {}
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, orderRequest.getProducts().getValue());
            ps.setString(2, orderRequest.getClient().getValue());
            ps.setBigDecimal(3, orderRequest.getTotal().getValue());
            ps.setInt(4, orderRequest.getDiscount().getValue());
            ps.setString(5, orderRequest.getStatus().toString());
            return ps;
        };

        jdbcTemplate.update(
                psc,
                keyHolder // --> {}, {1},

        );

        //long id = keyHolder.getKey().longValue();


        return OrderOperationSuccess.of(Order.of(
                0L,
                orderRequest.getProducts(),
                orderRequest.getClient(),
                orderRequest.getStatus(),
                orderRequest.getDiscount(),
                orderRequest.getTotal()
        ));

    }

    @Override
    public OrderOperation findById(OrderId orderId) {
        String SQL = "SELECT ID, PRODUCTS, CLIENT, TOTAL, DISCOUNT, STATUS FROM ORDERS WHERE ID =?";
        Object[] objects = {orderId.valueOf()};

        try {
            Order order = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return OrderOperationSuccess.of(order);
        } catch (OrderDoesNotExists e) {
            return OrderOperationFailure.of(e);
        }
    }

    @Override
    public List<Order> findAll() {
        String SQL = "SELECT ID, PRODUCTS, CLIENT, TOTAL, DISCOUNT, STATUS FROM ORDERS";

        try {
            List<Order> orders= jdbcTemplate.query(SQL,rowMapper);
            return orders;
        } catch (OrderDoesNotExists e) {
            OrderOperationFailure.of(e);
            return null;
        }
    }

    @Override
    public OrderOperation updateOne(OrderId orderId, OrderRequest orderRequest) {
        Object[] objects = {orderRequest.getProducts().getValue(),
                orderRequest.getClient().getValue(),
                orderRequest.getStatus().toString(),
                orderRequest.getDiscount().getValue(),
                orderRequest.getTotal().getValue(),
                orderId.getValue()};
        String SQL = String.format("UPDATE ORDERS SET PRODUCTS= '%s', CLIENT = '%s', STATUS = '%s', DISCOUNT= %s, TOTAL= '%s'  WHERE ID = %d",objects);

        try {
            jdbcTemplate.execute(SQL);
            Order order = Order.of(
                    orderId.getValue(),
                    orderRequest.getProducts(),
                    orderRequest.getClient(),
                    orderRequest.getStatus(),
                    orderRequest.getDiscount(),
                    orderRequest.getTotal()
            );
            return OrderOperationSuccess.of(order);
        } catch (OrderDoesNotExists e) {
            return OrderOperationFailure.of(e);
        }

    }

    @Override
    public OrderOperation deleteOne(OrderId orderId) {
        Object[] objects = {orderId.getValue()};
        String SQL = String.format("DELETE FROM ORDERS WHERE ID = %s",objects);

        try {
            OrderOperation product = findById(orderId);
                    jdbcTemplate.execute(SQL);
            return product;
        } catch (OrderDoesNotExists e) {
            return OrderOperationFailure.of(e);
        }
    }

    @Override
    public List<Order> findByClient(Client client) {
        String SQL = "SELECT ID, PRODUCTS, CLIENT, TOTAL, DISCOUNT, STATUS FROM ORDERS WHERE CLIENT=?";
        Object[] objects = {client.valueOf()};
        try {
            List<Order> orders = jdbcTemplate.query(SQL,objects,rowMapper);
            return orders;
        } catch (OrderDoesNotExists e) {
            OrderOperationFailure.of(e);
            return null;
        }
    }
}
