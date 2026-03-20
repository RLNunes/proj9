 package pt.ual.views;

 import com.fasterxml.jackson.databind.ObjectMapper;
 import java.util.List;
 import java.util.Map;
 import javax.ws.rs.DELETE;
 import javax.ws.rs.GET;
 import javax.ws.rs.HeaderParam;
 import javax.ws.rs.POST;
 import javax.ws.rs.PUT;
 import javax.ws.rs.Path;
 import javax.ws.rs.PathParam;
 import javax.ws.rs.Produces;
 import javax.ws.rs.core.Context;
 import javax.ws.rs.core.HttpHeaders;
 import javax.ws.rs.core.Response;
 import pt.ual.dao.Tipo_referencia_documental;
 import pt.ual.utils.Utils;

 @Path("tipo_referencia_documental")
 public class Tipo_referencia_documentalService
 {
   @GET
   @Path("/all")
   @Produces({"application/json"})
   public Response getAll(@Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Tipo_referencia_documental list = new Tipo_referencia_documental();
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         List<Map<String, Object>> row = list.allRegistros();
         b = Response.ok(row);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/record/{tipo_referencia_id}")
   @Produces({"application/json"})
   public Response record(@PathParam("tipo_referencia_id") int tipo_referencia_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Tipo_referencia_documental ptr = new Tipo_referencia_documental();
     String token = (new Utils()).getToken();
     ptr.setTipo_referencia_id(tipo_referencia_id);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.registro()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/search/{tipo_referencia}")
   @Produces({"application/json"})
   public Response search(@PathParam("tipo_referencia") String tipo_referencia, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Tipo_referencia_documental ptr = new Tipo_referencia_documental();
     String token = (new Utils()).getToken();
     ptr.setTipo_referencia(tipo_referencia);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.search()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/insert")
   @Produces({"application/json"})
   public Response insert(String data, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Tipo_referencia_documental ptr = (Tipo_referencia_documental)(new ObjectMapper()).readValue(data, Tipo_referencia_documental.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/update/{tipo_referencia_id}")
   @Produces({"application/json"})
   public Response update(String data, @PathParam("tipo_referencia_id") int tipo_referencia_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Tipo_referencia_documental ptr = (Tipo_referencia_documental)(new ObjectMapper()).readValue(data, Tipo_referencia_documental.class);
         ptr.setTipo_referencia_id(tipo_referencia_id);
         response = Response.ok(ptr.update()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{tipo_referencia_id}")
   @Produces({"application/json"})
   public Response delete(@PathParam("tipo_referencia_id") int tipo_referencia_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Tipo_referencia_documental ptr = new Tipo_referencia_documental();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setTipo_referencia_id(tipo_referencia_id);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }