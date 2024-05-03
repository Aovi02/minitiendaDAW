package minitienda;

import java.util.ArrayList;

public class Carrito {
    ArrayList<Seleccion> selecciones;
    Float importe;

    public Carrito(){
        super();
    }

    public void addSeleccion(Seleccion s){
        if(s != null)
            selecciones.add(s);
    }

    public ArrayList<Seleccion> getSelecciones(){
        return this.selecciones;
    }

    public Float getImporte(){
        return this.importe;
    }
    
}
