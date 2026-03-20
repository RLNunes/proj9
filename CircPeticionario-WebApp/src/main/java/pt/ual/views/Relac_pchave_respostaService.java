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
 import pt.ual.dao.Relac_pchave_resposta;
 import pt.ual.utils.Utils;

 @Path("relac_pchave_resposta")
 public class Relac_pchave_respostaService
 {
   @GET
   @Path("/allResposta/{resposta}")
   @Produces({"application/json"})
   public Response allResposta(@PathParam("resposta") int resposta, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_resposta ptr = new Relac_pchave_resposta();
     String token = (new Utils()).getToken();
     ptr.setResposta(resposta);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allResposta()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allPalavraChave/{palavra_chave}")
   @Produces({"application/json"})
   public Response allPalavraChave(@PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_resposta ptr = new Relac_pchave_resposta();
     String token = (new Utils()).getToken();
     ptr.setPalavra_chave(palavra_chave);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allPalavraChave()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/notInResposta/{resposta}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInResposta(@PathParam("resposta") int resposta, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_resposta ptr = new Relac_pchave_resposta();
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
     ptr.setResposta(resposta);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInResposta()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_pchave_resposta ptr = (Relac_pchave_resposta)(new ObjectMapper()).readValue(data, Relac_pchave_resposta.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateResposta/{palavra_chave}")
   @Produces({"application/json"})
   public Response updateResposta(String data, @PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_pchave_resposta ptr = (Relac_pchave_resposta)(new ObjectMapper()).readValue(data, Relac_pchave_resposta.class);
         ptr.setPalavra_chave(palavra_chave);
         response = Response.ok(ptr.updateResposta()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updatePalavraChave/{resposta}")
   @Produces({"application/json"})
   public Response updatePalavraChave(String data, @PathParam("resposta") int resposta, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_pchave_resposta ptr = (Relac_pchave_resposta)(new ObjectMapper()).readValue(data, Relac_pchave_resposta.class);
         ptr.setResposta(resposta);
         response = Response.ok(ptr.updatePalavraChave()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{resposta}/{palavra_chave}")
   @Produces({"application/json"})
   public Response delete(@PathParam("resposta") int resposta, @PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_resposta ptr = new Relac_pchave_resposta();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setResposta(resposta);
         ptr.setPalavra_chave(palavra_chave);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }