package minitienda;

public class Usuario {
    private String nombre;
    private String contrasena;
    private String tipoTarjeta;
    private String numeroTarjeta;
    private final Carrito carrito;

    public Usuario(String nombre, String contrasena, String numeroTarjeta, String tipoTarjeta){
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.numeroTarjeta = numeroTarjeta;
        this.tipoTarjeta = tipoTarjeta;
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

    public void setTipoTarjeta(String tipoTarjeta){
        this.tipoTarjeta = tipoTarjeta;
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

    public String getTipoTarjeta(){
        return this.tipoTarjeta;
    }

    public Carrito geCarrito(){
        return this.carrito;
    }
}
