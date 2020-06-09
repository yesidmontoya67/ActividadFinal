package co.com.ias.certification.backend.order.configuration;

import co.com.ias.certification.backend.order.respositories.OrderRepository;
import co.com.ias.certification.backend.order.respositories.SqlOrderRepository;
import co.com.ias.certification.backend.product.repositories.InMemoryProductRepository;
import co.com.ias.certification.backend.product.repositories.ProductRepository;
import co.com.ias.certification.backend.product.repositories.SqlProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
public class OrderRepositoryConfiguration {

    @Bean
    //@Profile({"dev"})
    public OrderRepository orderRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("ORDERS")
                .usingGeneratedKeyColumns("ID");

        return new SqlOrderRepository(jdbcTemplate, simpleJdbcInsert);
    }

   /* @Bean
    @Profile({"test"})
    public OrderRepository orderRepository() {
        return new InMemoryOrderRepository();
    }*/
}
