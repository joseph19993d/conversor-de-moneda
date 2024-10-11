package co.com.conversor.moneda.principal;

import co.com.conversor.moneda.calculos.CalculardoraMoneda;
import co.com.conversor.moneda.model.ConvertidorMoneda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            ConvertidorMoneda convertir = new ConvertidorMoneda();
            Scanner scanner = new Scanner(System.in);
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
                
                    *-->Ingrese la opcion de su preferencia<--*
                
                    *******************************************
                """
            );

            int moneda = scanner.nextInt();

            if (moneda == 7) {break;} else if (moneda < 7) {
                double valorDeLaMoneda = convertir.convertirMoneda(moneda);
                System.out.println("-->¿Cuanto desea convertir? <--");
                double cantidad = scanner.nextDouble();
                CalculardoraMoneda calculardoraMoneda = new CalculardoraMoneda(valorDeLaMoneda, cantidad);
                calculardoraMoneda.decirTotal(convertir.getMoneda(), convertir.getOtraMoneda());
            }


        }

    }
}
