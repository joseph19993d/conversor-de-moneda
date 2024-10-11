package co.com.conversor.moneda.model;
import java.util.Map;
public record MonedaValue(
        String result,
        String base_code,
        Map<String, Double> conversion_rates

) {

}
