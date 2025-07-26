
package model;

public class Mascota {

    private String nombre;
    private String especie;
    private String edad;

    public Mascota( String nombre, String especie, String edad) {
     
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad; 
    }
    public Mascota(){
        
    }
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    public Object[] toArray(){
        Object[] obj = new Object[3];
        obj[0] = nombre;
        obj[1] = especie;
        obj[2] = edad;
        
    return obj;
    }
}
    
  