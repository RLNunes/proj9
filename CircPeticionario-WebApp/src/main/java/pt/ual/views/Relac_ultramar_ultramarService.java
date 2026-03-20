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
 import pt.ual.dao.Relac_ultramar_ultramar;
 import pt.ual.utils.Utils;

 @Path("relac_ultramar_ultramar")
 public class Relac_ultramar_ultramarService
 {
   @GET
   @Path("/allUltramarAntigo/{ultramar_mais_antigo}")
   @Produces({"application/json"})
   public Response allRespAntiga(@PathParam("ultramar_mais_antigo") int ultramar_mais_antigo, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_ultramar_ultramar ptr = new Relac_ultramar_ultramar();
     String token = (new Utils()).getToken();
     ptr.setUltramar_mais_antigo(ultramar_mais_antigo);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allUltAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allUltramarRecente/{ultramar_mais_recente}")
   @Produces({"application/json"})
   public Response allRespRecente(@PathParam("ultramar_mais_recente") int ultramar_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_ultramar_ultramar ptr = new Relac_ultramar_ultramar();
     String token = (new Utils()).getToken();
     ptr.setUltramar_mais_recente(ultramar_mais_recente);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allUltNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/notInUltramarNovo/{ultramar_mais_recente}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInUltramarNovo(String data, @PathParam("ultramar_mais_recente") int ultramar_mais_recente, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_ultramar_ultramar ptr = (Relac_ultramar_ultramar)(new ObjectMapper()).readValue(data, Relac_ultramar_ultramar.class);
     ptr.setNomePesq(nomePesq);
     ptr.setUltramar_mais_recente(ultramar_mais_recente);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInUltramarNovo()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_ultramar_ultramar ptr = (Relac_ultramar_ultramar)(new ObjectMapper()).readValue(data, Relac_ultramar_ultramar.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateUltramarAntigo/{ultramar_mais_antigo}")
   @Produces({"application/json"})
   public Response updateUltramarAntigo(String data, @PathParam("ultramar_mais_antigo") int ultramar_mais_antigo, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_ultramar_ultramar ptr = (Relac_ultramar_ultramar)(new ObjectMapper()).readValue(data, Relac_ultramar_ultramar.class);
         ptr.setUltramar_mais_antigo(ultramar_mais_antigo);
         response = Response.ok(ptr.updateUltAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateUltramarRecente/{ultramar_mais_recente}")
   @Produces({"application/json"})
   public Response updateUltramarRecente(String data, @PathParam("ultramar_mais_recente") int ultramar_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_ultramar_ultramar ptr = (Relac_ultramar_ultramar)(new ObjectMapper()).readValue(data, Relac_ultramar_ultramar.class);
         ptr.setUltramar_mais_recente(ultramar_mais_recente);
         response = Response.ok(ptr.updateUltNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{ultramar_mais_antigo}/{ultramar_mais_recente}")
   @Produces({"application/json"})
   public Response delete(@PathParam("ultramar_mais_antigo") int ultramar_mais_antigo, @PathParam("ultramar_mais_recente") int ultramar_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_ultramar_ultramar ptr = new Relac_ultramar_ultramar();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setUltramar_mais_antigo(ultramar_mais_antigo);
         ptr.setUltramar_mais_recente(ultramar_mais_recente);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }