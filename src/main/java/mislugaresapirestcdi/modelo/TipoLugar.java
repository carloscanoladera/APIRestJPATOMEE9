package mislugaresapirestcdi.modelo;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;





/**
 * Enumerated Type to define kind of places
 *Cambios
 * @version 1.1
 * @author Carlos@Cano
 */


@XmlType
@XmlEnum(Integer.class)
public enum TipoLugar implements Serializable {

	 	@XmlEnumValue("1")	 
	 	OTROS("Otros", 1),
	 	@XmlEnumValue("2")
	    RESTAURANTE("Restaurante", 2),
	    @XmlEnumValue("3")
	    BAR("Bar", 3),
	    @XmlEnumValue("4")
	    COPAS("Copas", 4),
	    @XmlEnumValue("5")
	    ESPECTACULO("Espectáculo", 5),
	    @XmlEnumValue("6")
	    HOTEL("Hotel", 6),
	    @XmlEnumValue("7")
	    COMPRAS("Compras", 7),
	    @XmlEnumValue("8")
	    EDUCACION("Educación", 8),
	    @XmlEnumValue("9")
	    DEPORTE("Deporte", 9),
	    @XmlEnumValue("10")
	    NATURALEZA("Naturaleza", 10),
	    @XmlEnumValue("11")
	    GASOLINERA("Gasolinera", 11);

   
    private  String texto;
    
  
    private  int recurso;


     TipoLugar(String texto, int recurso) {
        
    	
    	this.texto=texto;
        this.recurso=recurso;
    }
    
    /**
     * returns the kind of place description
     *
     *
     * @return String
     */
    public String getTexto() { return texto; }
    
    
    public String setTexto(String texto) { return this.texto= texto; }

    /**
     * returns the id related to the icon that depicts the kind of place
     *
     *
     * @return int return an icon identifier
     */

    public int getRecurso() {return recurso; }
    /**
     * This methods return the array of kind of places depicted int this enumerated type
     *
     *
     * @return String [] array of TipoLugar
     */
    public  void setRecurso(int recurso) {this.recurso= recurso; }
    

    public static TipoLugar getTipoPorNombre(String nombre) {
        TipoLugar tipoRes=null;


        for (TipoLugar tipo : TipoLugar.values()) {
            if (tipo.getTexto().contains(nombre)) {

                tipoRes=tipo;
                break;
            };
        }

        return tipoRes;

    }
    

	 @Override
	    public String toString() {
	        return "TipoLugar{" +
	                "texto='" + texto + '\'' +
	                ", recurso=" + recurso +
	                '}';
	    }
  
}
