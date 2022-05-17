package mislugaresapirestcdi.modelo;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="lugares")
public class Lugares {
	@XmlElement(name="lugar")
	private List<Lugar> lugareslista;

	
	public Lugares() {
		
		
	}


	public Lugares(List<Lugar> lugares) {
		super();
		this.lugareslista = lugares;
	}


	public List<Lugar> getLugares() {
		return lugareslista;
	}


	public void setLugares(List<Lugar> lugares) {
		this.lugareslista = lugares;
	}
	

	
	
	

}
