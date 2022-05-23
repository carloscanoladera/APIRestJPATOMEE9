package mislugaresapirestcdi;





import java.sql.Connection;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;





	@ApplicationPath("/")
	public class MisLugaresAplicacion  extends Application  {       
		
		

	    public MisLugaresAplicacion() {
	    	
	   
	     System.out.println("La aplicacion arranca");   
	     
	     
	    
	    }
	}
	

