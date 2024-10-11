package co.com.conversor.moneda.calculos;

public class CalculardoraMoneda {
    private double precioActual;
    private double cantidadCalcular;
    private double total;

    public CalculardoraMoneda(double precioActual, double cantidadCalcular) {
        this.precioActual = precioActual;
        this.cantidadCalcular = cantidadCalcular;
    }



    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public double getCantidadCalcular() {
        return cantidadCalcular;
    }

    public void setCantidadCalcular( double cantidadCalcular) {
        this.cantidadCalcular = cantidadCalcular;
    }

    public double getTotal() {
        return this.total = calculo();
    }

    public double calculo(){
      return  this.precioActual  * this.cantidadCalcular;
    }

    public void decirTotal(String moneda, String otraMoneda ){
        System.out.println(this.cantidadCalcular+"("+moneda+")" +" x "+this.precioActual+"("+otraMoneda+")" +" = "+getTotal() +" "+ otraMoneda );
    }


}
