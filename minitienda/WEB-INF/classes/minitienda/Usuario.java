package minitienda;

public class Usuario {
    private String nombre;
    private String contrasena;
    private String numeroTarjeta;
    private Carrito carrito;

    public Usuario(String nombre, String contrasena, String numeroTarjeta){
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.numeroTarjeta = numeroTarjeta;
        this.carrito = new Carrito();
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public void setNumeroTarjeta(String numeroTarjeta){
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String setContrasena(){
        return this.contrasena;
    }

    public String getNumeroTarjeta(){
        return this.numeroTarjeta;
    }

    public Carrito geCarrito(){
        return this.carrito;
    }
}
