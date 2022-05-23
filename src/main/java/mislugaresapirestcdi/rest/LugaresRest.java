package mislugaresapirestcdi.rest;

import java.sql.SQLException;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import mislugaresapirestcdi.modelo.Lugar;
import mislugaresapirestcdi.modelo.Lugares;
import mislugaresapirestcdi.servicios.MisLugaresServicio;


@Stateless
@Path("/lugares")
@Produces(MediaType.APPLICATION_JSON)
public class LugaresRest {

	public static int ID_FAILED = -1;
	
	@Inject
	private MisLugaresServicio servicioLugares;
	
	public LugaresRest() {
		
		//servicioLugares= new MisLugaresServicioImpl();
		
		
	}

	@GET
	@Path("/lugareslista")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getLugares() {

	

		List<Lugar> lista = null;
		try {
			lista = servicioLugares.getLugares();
			
			if (lista==null) {
				
				return Response.status(Status.BAD_REQUEST).entity("Lugares not found").build();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		

		return Response.ok( new Lugares(lista) ).build();

	}
	
	@GET
	@Path("/lugar/{lugarid}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getLugarPorId(@PathParam("lugarid") String lugarid) {

		

		int id =Integer.valueOf(lugarid);

		Lugar lugar = null;
		try {
			lugar = servicioLugares.findLugarById(id);
			
			if (lugar==null) {
				
				return Response.status(Status.NOT_FOUND).entity("Lugar not found").build();
				
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(lugar).build();

	}

	
	@GET
	@Path("/lugarpornombre/{nombre}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getLugarPorNombre(@PathParam("nombre") String nombre) {



		Lugar lugar = null;
		try {
			lugar = servicioLugares.findLugarByName(nombre);
			
			if (lugar==null) {
				
				return Response.status(Status.NOT_FOUND).entity("Lugar not found").build();
				
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(lugar).build();

	}

	

	@POST
	@Path("/creaLugar")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})

	public Response creaLugar(@HeaderParam("Accept") String accept, Lugar lugar) {

		

	
		GenericEntity genericEntityLugar= null;
		try {
			lugar = servicioLugares.createLugar(lugar);
			
			//entityLugar = objMapper.writeValueAsString(lugar);
			 genericEntityLugar = new GenericEntity(lugar, Lugar.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.status(Status.CREATED).entity(genericEntityLugar).build();

	}

	@PUT
	@Path("/actualizalugar/{lugarid}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response actualizarLugarPut(@PathParam("lugarid") String lugarid, Lugar lugar) {

	
		int id= Integer.valueOf(lugarid);
		
		try {
			Lugar lugarEncontrado = servicioLugares.findLugarById(id);

			if (lugarEncontrado != null) {

				servicioLugares.updateLugar(lugar);
				return Response.ok().build();

			}

			return Response.status(Status.BAD_REQUEST).entity("Lugar not found").build();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();

		}

	}
	
	@PATCH
	@Path("/patchlugar/{lugarid}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response patchLugar(@PathParam("lugarid") String lugarid, Lugar lugar) {


		int id = Integer.valueOf(lugarid);
		try {
			Lugar lugarEncontrado = servicioLugares.findLugarById(id);

			if (lugarEncontrado != null) {

				servicioLugares.updateLugar(lugar);
				return Response.accepted().build();

			}

			return Response.status(Status.BAD_REQUEST).entity("Lugar not found").build();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();

		}

	}

	@DELETE
	@Path("/deletelugar")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response borrarLugarDelete(Lugar lugar) {


		int id = ID_FAILED;
		try {
			id = servicioLugares.borrarLugar(id);

			if (id == ID_FAILED) {

				return Response.status(Status.BAD_REQUEST).entity("Lugar not found").build();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.accepted().build();

	}

}