package co.com.ias.certification.backend.configuration;

import co.com.ias.certification.backend.order.domain.*;
import co.com.ias.certification.backend.order.ecxeptions.OrderException;
import co.com.ias.certification.backend.product.domain.*;
import co.com.ias.certification.backend.product.ecxeptions.ProductException;
import co.com.ias.certification.backend.serialization.NumberValueAdapter;
import co.com.ias.certification.backend.serialization.StringValueAdapter;
import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.function.Function;

@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson(){

        Function<Number, ProductId> creatorP= s -> ProductId.fromNumber(s);

        return new GsonBuilder()
                .registerTypeAdapter(ProductId.class, new NumberValueAdapter<ProductId>(creatorP))
                .registerTypeAdapter(Name.class, new StringValueAdapter<>(Name::of))
                .registerTypeAdapter(Description.class, new StringValueAdapter<>(Description::of))
                .registerTypeAdapter(BasePrice.class, new NumberValueAdapter<>(BasePrice::fromNumber))
                .registerTypeAdapter(TaxRate.class, new NumberValueAdapter<>(TaxRate::fromNumber))
                .registerTypeAdapter(InventoryQueantity.class, new NumberValueAdapter<>(InventoryQueantity::fromNumber))
                .registerTypeAdapter(OrderId.class, new NumberValueAdapter<>(OrderId::fromNumber))
                .registerTypeAdapter(Client.class, new StringValueAdapter<>(Client::of))
                .registerTypeAdapter(Discount.class, new NumberValueAdapter<>(Discount::fromNumber))
                .registerTypeAdapter(Total.class, new NumberValueAdapter<>(Total::fromNumber))
                .registerTypeAdapter(Products.class, new StringValueAdapter<>(Products::of))

                .registerTypeAdapter(ProductException.class, new JsonSerializer<ProductException>() {
                    @Override
                    public JsonElement serialize(ProductException src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        String message = src.getMessage();
                        JsonPrimitive errorValue = new JsonPrimitive(message);
                        jsonObject.add("error", errorValue);
                        return jsonObject;
                    }
                })

                .registerTypeAdapter(OrderException.class, new JsonSerializer<OrderException>() {
                    @Override
                    public JsonElement serialize(OrderException src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        String message = src.getMessage();
                        JsonPrimitive errorValue = new JsonPrimitive(message);
                        jsonObject.add("error", errorValue);
                        return jsonObject;
                    }
                })
                .create();
    }
}
