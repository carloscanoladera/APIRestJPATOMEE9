package mislugaresapirestcdi.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;



import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.*;

import jakarta.ws.rs.ext.MessageBodyReader;

@Provider
@Consumes("application/json") // (1)
@Produces("application/json")
public class JsonJettisonMessageBodyProvider implements MessageBodyReader<Object> ,
	MessageBodyWriter<Object>{

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		// TODO Auto-generated method stub

    ObjectMapper json = new ObjectMapper();
    		
    return json.readValue(entityStream, type);
	
	
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void writeTo(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		ObjectMapper jsonObj = new ObjectMapper();
		
		 PrintWriter printWriter = new PrintWriter( entityStream );
		    try {
		      String json =jsonObj.writeValueAsString(t);
		      
		      printWriter.write(json);
		      printWriter.flush();
		    } finally {
		      printWriter.close();
		    }
		
		
	}

 
}