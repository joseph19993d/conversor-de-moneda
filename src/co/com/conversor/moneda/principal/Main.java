package co.com.conversor.moneda.principal;

import co.com.conversor.moneda.calculos.CalculardoraMoneda;
import co.com.conversor.moneda.model.ConvertidorMoneda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Bucle infinito para que la aplicación siga ejecutándose hasta que el usuario decida salir
        while (true) {
            // Instancia de la clase ConvertidorMoneda
            ConvertidorMoneda convertir = new ConvertidorMoneda();
            // Scanner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            // Muestra las opciones de conversión disponibles al usuario
            System.out.println(
                    """
                        *******************************************
                        1. **Dólar (USD) => Peso Argentino (ARS)
                        2. **Peso Argentino (ARS) => Dólar (USD)
                        3. **Dólar (USD) => Real Brasileño (BRL)
                        4. **Real Brasileño (BRL) => Dólar (USD)
                        5. **Dólar (USD) => Peso Colombiano (COP)
                        6. **Peso Colombiano (COP) => Dólar (USD)
                        7. **Salir**: Cierra la aplicación.
                        *******************************************
                    
                        *-->Ingrese la opción de su preferencia<--*
                    
                        *******************************************
                    """
            );

            // Lee la opción ingresada por el usuario
            int moneda = scanner.nextInt();

            // Si el usuario elige la opción 7, se rompe el bucle y se cierra la aplicación
            if (moneda == 7) {
                break;
            } else if (moneda < 7) {
                // Llama al método para convertir moneda según la opción elegida
                double valorDeLaMoneda = convertir.convertirMoneda(moneda);

                // Pide al usuario que ingrese la cantidad que desea convertir
                System.out.println("-->¿Cuánto desea convertir? <--");
                double cantidad = scanner.nextDouble();

                // Instancia de la clase CalculardoraMoneda para realizar el cálculo de conversión
                CalculardoraMoneda calculardoraMoneda = new CalculardoraMoneda(valorDeLaMoneda, cantidad);

                // Muestra el resultado de la conversión al usuario
                calculardoraMoneda.decirTotal(convertir.getMoneda(), convertir.getOtraMoneda());
            }
        }
    }
}
