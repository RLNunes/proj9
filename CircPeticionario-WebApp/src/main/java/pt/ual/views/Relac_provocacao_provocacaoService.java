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
 import pt.ual.dao.Relac_provocacao_provocacao;
 import pt.ual.utils.Utils;

 @Path("relac_provocacao_provocacao")
 public class Relac_provocacao_provocacaoService
 {
   @GET
   @Path("/allProvAntiga/{provocacao_mais_antiga}/{nomePesq}")
   @Produces({"application/json"})
   public Response allProvAntiga(@PathParam("provocacao_mais_antiga") int provocacao_mais_antiga, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_provocacao_provocacao ptr = new Relac_provocacao_provocacao();
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
     ptr.setProvocacao_mais_antiga(provocacao_mais_antiga);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allProvAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allProvRecente/{provocacao_mais_recente}")
   @Produces({"application/json"})
   public Response allProvRecente(@PathParam("provocacao_mais_recente") int provocacao_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_provocacao_provocacao ptr = new Relac_provocacao_provocacao();
     String token = (new Utils()).getToken();
     ptr.setProvocacao_mais_recente(provocacao_mais_recente);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allProvNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/notInProvocacaoAntiga/{provocacao_mais_antiga}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInResposta(String data, @PathParam("provocacao_mais_antiga") int provocacao_mais_antiga, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_provocacao_provocacao ptr = (Relac_provocacao_provocacao)(new ObjectMapper()).readValue(data, Relac_provocacao_provocacao.class);
         ptr.setNomePesq(nomePesq);
         ptr.setProvocacao_mais_antiga(provocacao_mais_antiga);
         response = Response.ok(ptr.notInProvocacaoAntiga()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_provocacao_provocacao ptr = (Relac_provocacao_provocacao)(new ObjectMapper()).readValue(data, Relac_provocacao_provocacao.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateProvAntiga/{provocacao_mais_recente}")
   @Produces({"application/json"})
   public Response updateUltramar(String data, @PathParam("provocacao_mais_recente") int provocacao_mais_recente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_provocacao_provocacao ptr = (Relac_provocacao_provocacao)(new ObjectMapper()).readValue(data, Relac_provocacao_provocacao.class);
         ptr.setProvocacao_mais_recente(provocacao_mais_recente);
         response = Response.ok(ptr.updateProvAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateProvRecente/{provocacao_mais_antiga}")
   @Produces({"application/json"})
   public Response updateProvRecente(String data, @PathParam("provocacao_mais_antiga") int provocacao_mais_antiga, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_provocacao_provocacao ptr = (Relac_provocacao_provocacao)(new ObjectMapper()).readValue(data, Relac_provocacao_provocacao.class);
         ptr.setProvocacao_mais_antiga(provocacao_mais_antiga);
         response = Response.ok(ptr.updateProvNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{provocacao_mais_recente}/{provocacao_mais_antiga}")
   @Produces({"application/json"})
   public Response delete(@PathParam("provocacao_mais_recente") int provocacao_mais_recente, @PathParam("provocacao_mais_antiga") int provocacao_mais_antiga, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_provocacao_provocacao ptr = new Relac_provocacao_provocacao();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setProvocacao_mais_recente(provocacao_mais_recente);
         ptr.setProvocacao_mais_antiga(provocacao_mais_antiga);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }