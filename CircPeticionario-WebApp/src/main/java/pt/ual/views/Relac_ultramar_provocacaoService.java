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
 import pt.ual.dao.Relac_ultramar_provocacao;
 import pt.ual.utils.Utils;

 @Path("relac_ultramar_provocacao")
 public class Relac_ultramar_provocacaoService
 {
   @GET
   @Path("/allUltramar/{ultramar}")
   @Produces({"application/json"})
   public Response allUltramar(@PathParam("ultramar") int ultramar, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_ultramar_provocacao ptr = new Relac_ultramar_provocacao();
     String token = (new Utils()).getToken();
     ptr.setUltramar(ultramar);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allUltramar()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/notInUltramar/{ultramar}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInUltramar(String data, @PathParam("ultramar") int ultramar, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_ultramar_provocacao ptr = (Relac_ultramar_provocacao)(new ObjectMapper()).readValue(data, Relac_ultramar_provocacao.class);
     ptr.setNomePesq(nomePesq);
     ptr.setUltramar(ultramar);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInUltramar()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allProvocacao/{provocacao}/{nomePesq}")
   @Produces({"application/json"})
   public Response allProvocacao(@PathParam("provocacao") int provocacao, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_ultramar_provocacao ptr = new Relac_ultramar_provocacao();
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
     ptr.setProvocacao(provocacao);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allProvocacao()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/notInProvocacao/{provocacao}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInConsulta(String data, @PathParam("provocacao") int provocacao, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_ultramar_provocacao ptr = (Relac_ultramar_provocacao)(new ObjectMapper()).readValue(data, Relac_ultramar_provocacao.class);
     ptr.setNomePesq(nomePesq);
     ptr.setProvocacao(provocacao);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInProvocacao()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_ultramar_provocacao ptr = (Relac_ultramar_provocacao)(new ObjectMapper()).readValue(data, Relac_ultramar_provocacao.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateUltramar/{provocacao}")
   @Produces({"application/json"})
   public Response updateUltramar(String data, @PathParam("provocacao") int provocacao, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_ultramar_provocacao ptr = (Relac_ultramar_provocacao)(new ObjectMapper()).readValue(data, Relac_ultramar_provocacao.class);
         ptr.setProvocacao(provocacao);
         response = Response.ok(ptr.updateUltramar()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateProvocacao/{ultramar}")
   @Produces({"application/json"})
   public Response updateProvocacao(String data, @PathParam("ultramar") int ultramar, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_ultramar_provocacao ptr = (Relac_ultramar_provocacao)(new ObjectMapper()).readValue(data, Relac_ultramar_provocacao.class);
         ptr.setUltramar(ultramar);
         response = Response.ok(ptr.updateProvocacao()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{ultramar}/{provocacao}")
   @Produces({"application/json"})
   public Response delete(@PathParam("ultramar") int ultramar, @PathParam("provocacao") int provocacao, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_ultramar_provocacao ptr = new Relac_ultramar_provocacao();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setUltramar(ultramar);
         ptr.setProvocacao(provocacao);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }