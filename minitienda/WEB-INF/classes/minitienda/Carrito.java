package minitienda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class Carrito {
    private final Set<Seleccion> selecciones;
    private Float importe;
    private Usuario usuario;

    public Carrito(){
        super();
        selecciones = new HashSet<>();
        importe = 0.f;
    }

    public void addSeleccion(Seleccion s){
        if(s != null){
            selecciones.add(s);
            importe += s.getCantidad() * s.getCd().getPrecio();
        }
    }

    public void eliminarSeleccion(Integer id) {
        if (id != null) {
            Iterator<Seleccion> iterator = selecciones.iterator();
            while (iterator.hasNext()) {
                Seleccion s = iterator.next();
                if (s.getId() == id) {
                    iterator.remove();
                    importe -= s.getCantidad() * s.getCd().getPrecio();
                }
            }
        }
    }

    public void setUsuario(Usuario u){
        if(u != null)
            usuario = u;
    }

    public Set<Seleccion> getSelecciones(){
        return this.selecciones;
    }

    public Float getImporte(){
        return this.importe;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }
    
}
