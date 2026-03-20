 package pt.ual.views;

 import com.fasterxml.jackson.databind.ObjectMapper;
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
 import pt.ual.dao.Relac_secretario_conselho_mandado;
 import pt.ual.utils.Utils;

 @Path("relac_secretario_conselho_mandado")
 public class Relac_secretario_conselho_mandadoService
 {
   @GET
   @Path("/allSecretarioConselho/{secretario_conselho}")
   @Produces({"application/json"})
   public Response allSecretarioConselho(@PathParam("secretario_conselho") int secretario_conselho, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_secretario_conselho_mandado ptr = new Relac_secretario_conselho_mandado();
     String token = (new Utils()).getToken();
     ptr.setSecretario_conselho(secretario_conselho);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allSecretarioConselho()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allMandado/{mandado}")
   @Produces({"application/json"})
   public Response allResposta(@PathParam("mandado") int mandado, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_secretario_conselho_mandado ptr = new Relac_secretario_conselho_mandado();
     String token = (new Utils()).getToken();
     ptr.setMandado(mandado);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allMandado()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/notInMandado/{mandado}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInResposta(@PathParam("mandado") int mandado, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_secretario_conselho_mandado ptr = new Relac_secretario_conselho_mandado();
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
     ptr.setMandado(mandado);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInMandado()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_secretario_conselho_mandado ptr = (Relac_secretario_conselho_mandado)(new ObjectMapper()).readValue(data, Relac_secretario_conselho_mandado.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateSecretarioConselho/{mandado}")
   @Produces({"application/json"})
   public Response updateSecretarioConselho(String data, @PathParam("mandado") int mandado, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_secretario_conselho_mandado ptr = (Relac_secretario_conselho_mandado)(new ObjectMapper()).readValue(data, Relac_secretario_conselho_mandado.class);
         ptr.setMandado(mandado);
         response = Response.ok(ptr.updateSecretarioConselho()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateMandado/{secretario_conselho}")
   @Produces({"application/json"})
   public Response updateResposta(String data, @PathParam("secretario_conselho") int secretario_conselho, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_secretario_conselho_mandado ptr = (Relac_secretario_conselho_mandado)(new ObjectMapper()).readValue(data, Relac_secretario_conselho_mandado.class);
         ptr.setSecretario_conselho(secretario_conselho);
         response = Response.ok(ptr.updateMandado()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{secretario_conselho}/{mandado}")
   @Produces({"application/json"})
   public Response delete(@PathParam("secretario_conselho") int secretario_conselho, @PathParam("mandado") int mandado, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_secretario_conselho_mandado ptr = new Relac_secretario_conselho_mandado();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setSecretario_conselho(secretario_conselho);
         ptr.setMandado(mandado);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }