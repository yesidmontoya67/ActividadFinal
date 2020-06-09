package co.com.ias.certification.backend.product.configuration;

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
public class ProductRepositoryConfiguration {

    @Bean
    //@Profile({"dev"})
    public ProductRepository productRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PRODUCTS")
                .usingGeneratedKeyColumns("ID");

        return new SqlProductRepository(jdbcTemplate, simpleJdbcInsert);
    }

    @Bean
    @Profile({"test"})
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }
}
