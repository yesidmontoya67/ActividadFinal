package co.com.ias.certification.backend.product.repositories;

import co.com.ias.certification.backend.product.domain.*;
import co.com.ias.certification.backend.product.ecxeptions.ProductDoesNotExists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


public class SqlProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;


    public SqlProductRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    private final RowMapper<Product> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("ID");
        Name name = Name.of(resultSet.getString("NAME"));
        Description description = Description.of(resultSet.getString("DESCRIPTION"));
        BasePrice basePrice= BasePrice.of(resultSet.getBigDecimal("BASE_PRICE"));
        TaxRate taxRate= TaxRate.of(resultSet.getBigDecimal("TAX_RATE"));
        ProductStatus productStatus= ProductStatus.valueOf(resultSet.getString("STATUS"));
        InventoryQueantity inventoryQueantity= InventoryQueantity.of(resultSet.getInt("INVENTORY_QUANTITY"));

        return Product.of(id, name, description,basePrice,taxRate,productStatus,inventoryQueantity);
    };

    @Override
    public ProductOperation insertOne(ProductoperationRequest productoperationRequest) {
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
        String SQL = "INSERT INTO PRODUCTS (NAME, DESCRIPTION, BASE_PRICE, TAX_RATE, STATUS, INVENTORY_QUANTITY) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // inicializada {}
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productoperationRequest.getName().getValue());
            ps.setString(2, productoperationRequest.getDescription().getValue());
            ps.setBigDecimal(3, productoperationRequest.getBasePrice().getValue());
            ps.setBigDecimal(4, productoperationRequest.getTaxRate().getValue());
            ps.setString(5, productoperationRequest.getProductStatus().toString());
            ps.setInt(6, productoperationRequest.getInventoryQueantity().getValue());
            return ps;
        };

        jdbcTemplate.update(
                psc,
                keyHolder // --> {}, {1},

        );

        //long id = keyHolder.getKey().longValue();


        return ProductOperationSuccess.of(Product.of(
                0L,
                productoperationRequest.getName(),
                productoperationRequest.getDescription(),
                productoperationRequest.getBasePrice(),
                productoperationRequest.getTaxRate(),
                productoperationRequest.getProductStatus(),
                productoperationRequest.getInventoryQueantity()
        ));

    }

    @Override
    public ProductOperation findById(ProductId productId) {
        String SQL = "SELECT ID, NAME, DESCRIPTION, BASE_PRICE, TAX_RATE, STATUS, INVENTORY_QUANTITY FROM PRODUCTS WHERE ID =?";
        Object[] objects = {productId.valueOf()};

        try {
            Product product = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(product);
        } catch (ProductDoesNotExists e) {
            return ProductOperationFailure.of(e);
        }
    }

    @Override
    public List<Product> findAll() {
        String SQL = "SELECT ID, NAME, DESCRIPTION, BASE_PRICE, TAX_RATE, STATUS, INVENTORY_QUANTITY FROM PRODUCTS";

        try {
            List<Product> products = jdbcTemplate.query(SQL,rowMapper);
            return products;
        } catch (ProductDoesNotExists e) {
            ProductOperationFailure.of(e);
            return null;
        }
    }

    @Override
    public ProductOperation updateOne(ProductId productId, ProductoperationRequest productoperationRequest) {
        Object[] objects = {productoperationRequest.getName().getValue(),
                productoperationRequest.getDescription().getValue(),
                productoperationRequest.getBasePrice().getValue(),
                productoperationRequest.getTaxRate().getValue(),
                productoperationRequest.getProductStatus().toString(),
                productoperationRequest.getInventoryQueantity().getValue(),
                productId.getValue()};
        String SQL = String.format("UPDATE PRODUCTS SET NAME= '%s', DESCRIPTION = '%s', BASE_PRICE = %s, TAX_RATE= %s, STATUS= '%s', INVENTORY_QUANTITY= %d  WHERE ID = %d",objects);

        try {
            jdbcTemplate.execute(SQL);
            Product product = Product.of(
                    productId.getValue(),
                    productoperationRequest.getName(),
                    productoperationRequest.getDescription(),
                    productoperationRequest.getBasePrice(),
                    productoperationRequest.getTaxRate(),
                    productoperationRequest.getProductStatus(),
                    productoperationRequest.getInventoryQueantity()
            );
            return ProductOperationSuccess.of(product);
        } catch (ProductDoesNotExists e) {
            return ProductOperationFailure.of(e);
        }

    }

    @Override
    public ProductOperation deleteOne(ProductId productId) {
        Object[] objects = {productId.getValue()};
        String SQL = String.format("DELETE FROM PRODUCTS WHERE ID = %s",objects);

        try {
            ProductOperation product = findById(productId);
                    jdbcTemplate.execute(SQL);
            return product;
        } catch (ProductDoesNotExists e) {
            return ProductOperationFailure.of(e);
        }
    }
}
