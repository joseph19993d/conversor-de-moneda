package co.com.conversor.moneda.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvertidorMoneda {

    // Variables para almacenar las monedas seleccionadas
    private String moneda = "";
    private String otraMoneda = "";

    // Método para obtener la otra moneda
    public String getOtraMoneda() {
        return otraMoneda;
    }

    // Método para establecer la otra moneda
    public void setOtraMoneda(String otraMoneda) {
        this.otraMoneda = otraMoneda;
    }

    // Constructor por defecto
    public ConvertidorMoneda() {
    }

    // Constructor con parámetros
    public ConvertidorMoneda(String moneda, String otraMoneda) {
        this.moneda = moneda;
        this.otraMoneda = otraMoneda;
    }

    // Método para obtener la moneda
    public String getMoneda() {
        return moneda;
    }

    // Método para establecer la moneda
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    // Cliente HTTP para realizar solicitudes
    HttpClient client = HttpClient.newHttpClient();

    // Método para convertir moneda según la opción seleccionada
    public double convertirMoneda(int option) {

        // Imprime la opción elegida por el usuario
        System.out.println("Opción elegida: " + option);

        // Configura las monedas según la opción seleccionada
        switch (option) {
            case 1:
                setMoneda("USD");
                setOtraMoneda("ARS");
                break;
            case 2:
                setMoneda("ARS");
                setOtraMoneda("USD");
                break;
            case 3:
                setMoneda("USD");
                setOtraMoneda("BRL");
                break;
            case 4:
                setMoneda("BRL");
                setOtraMoneda("USD");
                break;
            case 5:
                setMoneda("USD");
                setOtraMoneda("COP");
                break;
            case 6:
                setMoneda("COP");
                setOtraMoneda("USD");
                break;
            default:
                System.out.println("Opción no válida.");
                return 0; // Retorna 0 si la opción no es válida
        }

        // Crea la URI para la solicitud HTTP utilizando la moneda seleccionada
        URI moneda = URI.create("https://v6.exchangerate-api.com/v6/33b400a67d5b1ba70584ecb4/latest/" + this.moneda);
        HttpRequest request = HttpRequest.newBuilder().uri(moneda).build();
        HttpResponse<String> response = null;

        try {
            // Envía la solicitud y obtiene la respuesta
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Error en la solicitud HTTP.");
        }

        // Crea una instancia de Gson para manejar JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // Convierte el cuerpo de la respuesta JSON a un objeto MonedaValue
            MonedaValue monedaValue = gson.fromJson(response.body(), MonedaValue.class);

            // Accede a la tasa de cambio de la otra moneda
            Double rate = monedaValue.conversion_rates().get(this.otraMoneda);

            if (rate != null) {
                // Imprime y retorna la tasa de cambio encontrada
                System.out.println("El precio de la moneda (" + this.moneda + ") en moneda (" + this.otraMoneda + ") es: " + rate);
                return rate;
            } else {
                // Imprime un mensaje si no se encuentra la tasa de cambio
                System.out.println("No se encontró la tasa de (" + this.moneda + ") a (" + this.otraMoneda + ")");
                return 0;
            }

        } catch (Exception e) {
            // Maneja excepciones relacionadas con la conversión de JSON
            System.err.println("Error al convertir el JSON a un objeto MonedaValue: " + e.getMessage());
            return 0;
        }
    }
}
