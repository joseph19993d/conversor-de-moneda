package co.com.conversor.moneda.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvertidorMoneda {


    private String moneda = "";
    private String otraMoneda = "";

    public String getOtraMoneda() {
        return otraMoneda;
    }

    public void setOtraMoneda(String otraMoneda) {
        this.otraMoneda = otraMoneda;
    }

    public ConvertidorMoneda() {
    }

    public ConvertidorMoneda(String moneda, String otraMoneda) {
        this.moneda = moneda;
        this.otraMoneda = otraMoneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    HttpClient client = HttpClient.newHttpClient();

    public double convertirMoneda (int option){

        System.out.println("Opcion elegida : " + option);

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
                System.out.println("hi");

        }

        URI moneda = URI.create("https://v6.exchangerate-api.com/v6/33b400a67d5b1ba70584ecb4/latest/"+this.moneda);
        HttpRequest request = HttpRequest.newBuilder().uri(moneda).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Error en la solicitud HTTP.");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // Convierte el cuerpo de la respuesta JSON a un objeto MonedaValue
            MonedaValue monedaValue = gson.fromJson(response.body(), MonedaValue.class);
            // Accede a la tasa de cambio de la moneda
            Double Rate = monedaValue.conversion_rates().get(this.otraMoneda);

            if (Rate != null) {
                System.out.println("El precio de la moneda ("+this.moneda+") en moneda ("+this.otraMoneda+") es: " + Rate);
                return Rate;
            } else {
                System.out.println("No se encontró la tasa de ("+this.moneda+") a ("+ this.otraMoneda+ ")");
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Error al convertir el JSON a un objeto MonedaValue: " + e.getMessage());
            return 0;
        }

    }
}
