package minitienda;

public class Pedido {
    private String correoUsuario;
    private Float importePedido;

    public Pedido(String correoUsuario, Float importePedido) {
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

    
}
