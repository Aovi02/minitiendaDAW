package minitienda;

public class Seleccion {
    private CD cd;
    private Integer cantidad;

    public Seleccion(CD cd, Integer cantidad){
        this.cd = cd;
        this.cantidad = cantidad;
    }

    public void setCd(CD cd){
        this.cd = cd;
    }

    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }

    public CD getCd(){
        return this.cd;
    }

    public Integer getCantidad(){
        return this.cantidad;
    }
}
