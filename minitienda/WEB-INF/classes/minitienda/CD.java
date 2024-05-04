package minitienda;

public class CD {
    private String nombre;
    private String autor;
    private String pais;
    private Float precio;

    public CD(String nombre, String autor, String pais, Float precio){
        this.nombre = nombre;
        this.autor = autor;
        this.pais = pais;
        this.precio = precio;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public void setPrecio(Float precio){
        this.precio = precio;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getPais(){
        return this.pais;
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

        CD producto = (CD) o;
        return this.nombre.equals(producto.getNombre()) && this.autor.equals(producto.getAutor()) && this.autor.equals(producto.getAutor()) && this.precio.equals(producto.getPrecio());
    }


    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (nombre != null ? nombre.hashCode() : 0);
        hash = 31 * hash + (autor != null ? autor.hashCode() : 0);
        hash = 31 * hash + (pais != null ? pais.hashCode() : 0);
        hash = 31 * hash + (precio != null ? precio.hashCode() : 0);
        return hash;
    }
}
