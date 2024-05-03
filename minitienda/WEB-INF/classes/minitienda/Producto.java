package minitienda;

public class Producto {
    private String nombre;
    private Float precio;

    public Producto(String nombre, Float precio){
        this.nombre = nombre;
        this.precio = precio;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPrecio(Float precio){
        this.precio = precio;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Float getPrecio(){
        return this.precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        Producto producto = (Producto) o;
        return this.nombre.equals(producto.getNombre()) && this.precio.equals(producto.getPrecio());
    }


    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (nombre != null ? nombre.hashCode() : 0);
        hash = 31 * hash + (precio != null ? precio.hashCode() : 0);
        return hash;
    }
}
