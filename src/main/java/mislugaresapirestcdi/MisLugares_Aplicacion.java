package mislugaresapirestcdi;





import java.sql.Connection;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;





	@ApplicationPath("/")
	public class MisLugares_Aplicacion  extends Application  {       
		
		

	    public MisLugares_Aplicacion() {
	    	
	    
	     System.out.println("La aplicacion arranca y conecta a base de datos correctamente");   
	    
	    }
	}
	

