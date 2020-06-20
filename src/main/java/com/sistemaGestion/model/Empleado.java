package sistemaGestion.model;

import javax.persistence.*;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column
    private String nombre;

    public Empleado(){

    }

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public Boolean es_lider_de_proyecto() {
        return true;
    }

}
