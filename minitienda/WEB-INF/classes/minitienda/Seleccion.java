package minitienda;

import java.util.Objects;

public class Seleccion {
    private static int nextId = 1;
    
    private int id;
    private CD cd;
    private Integer cantidad;

    public Seleccion(CD cd, Integer cantidad) {
        this.id = nextId++;
        this.cd = cd;
        this.cantidad = cantidad;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public CD getCd() {
        return this.cd;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seleccion seleccion = (Seleccion) o;
        return id == seleccion.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
