package minitienda;

import java.util.ArrayList;

public class Carrito {
    private final ArrayList<Seleccion> selecciones;
    private Float importe;
    private Usuario usuario;

    public Carrito(){
        super();
        selecciones = new ArrayList<>();
        importe = 0.f;
    }

    public void addSeleccion(Seleccion s){
        if(s != null){
            selecciones.add(s);
            importe += s.getCantidad() * s.getCd().getPrecio();
        }
    }

    public void setUsuario(Usuario u){
        if(u != null)
            usuario = u;
    }

    public ArrayList<Seleccion> getSelecciones(){
        return this.selecciones;
    }

    public Float getImporte(){
        return this.importe;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }
    
}
