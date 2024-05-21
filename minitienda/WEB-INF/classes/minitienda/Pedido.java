package minitienda;

public class Pedido {
    private String correoUsuario;
    private Float importePedido;
    private String id;
    private static int cont;

    public Pedido(String correoUsuario, Float importePedido) {
        id = "PED" + cont;
        cont++;
        this.correoUsuario = correoUsuario;
        this.importePedido = importePedido;
    }

    
    public String getCorreoUsuario() {
        return correoUsuario;
    }
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
    public Float getImportePedido() {
        return importePedido;
    }
    public void setImportePedido(Float importePedido) {
        this.importePedido = importePedido;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    
}
