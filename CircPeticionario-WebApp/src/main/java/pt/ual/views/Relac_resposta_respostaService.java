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
 import pt.ual.dao.Relac_resposta_resposta;
 import pt.ual.utils.Utils;

 @Path("relac_resposta_resposta")
 public class Relac_resposta_respostaService
 {
   @GET
   @Path("/allRespAntiga/{resposta_mais_antiga}")
   @Produces({"application/json"})
   public Response allRespAntiga(@PathParam("resposta_mais_antiga") int resposta_mais_antiga, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_resposta_resposta ptr = new Relac_resposta_resposta();
     String token = (new Utils()).getToken();
     ptr.setResposta_mais_antiga(resposta_mais_antiga);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allRespAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allRespRecente/{resposta_mais_recente}")
   @Produces({"application/json"})
   public Response allRespRecente(@PathParam("resposta_mais_recente") int resposta_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_resposta_resposta ptr = new Relac_resposta_resposta();
     String token = (new Utils()).getToken();
     ptr.setResposta_mais_recente(resposta_mais_recente);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allRespNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/notInResposta/{resposta}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInResposta(String data, @PathParam("resposta") int resposta, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     Relac_resposta_resposta ptr = (Relac_resposta_resposta)(new ObjectMapper()).readValue(data, Relac_resposta_resposta.class);
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
     ptr.setResposta_mais_antiga(resposta);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInRespostaAnt()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_resposta_resposta ptr = (Relac_resposta_resposta)(new ObjectMapper()).readValue(data, Relac_resposta_resposta.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateRespAntiga/{resposta_mais_recente}")
   @Produces({"application/json"})
   public Response updateRespAntiga(String data, @PathParam("resposta_mais_recente") int resposta_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_resposta_resposta ptr = (Relac_resposta_resposta)(new ObjectMapper()).readValue(data, Relac_resposta_resposta.class);
         ptr.setResposta_mais_recente(resposta_mais_recente);
         response = Response.ok(ptr.updateRespAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateRespRecente/{resposta_mais_antiga}")
   @Produces({"application/json"})
   public Response updateRespRecente(String data, @PathParam("resposta_mais_antiga") int resposta_mais_antiga, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_resposta_resposta ptr = (Relac_resposta_resposta)(new ObjectMapper()).readValue(data, Relac_resposta_resposta.class);
         ptr.setResposta_mais_antiga(resposta_mais_antiga);
         response = Response.ok(ptr.updateRespNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{resposta_mais_antiga}/{resposta_mais_recente}")
   @Produces({"application/json"})
   public Response delete(@PathParam("resposta_mais_antiga") int resposta_mais_antiga, @PathParam("resposta_mais_recente") int resposta_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_resposta_resposta ptr = new Relac_resposta_resposta();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setResposta_mais_antiga(resposta_mais_antiga);
         ptr.setResposta_mais_recente(resposta_mais_recente);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }