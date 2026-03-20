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
 import pt.ual.dao.Relac_pchave_ultramar;
 import pt.ual.utils.Utils;

 @Path("relac_pchave_ultramar")
 public class Relac_pchave_ultramarService
 {
   @GET
   @Path("/allUltramar/{ultramar}/{nomePesq}")
   @Produces({"application/json"})
   public Response allUltramar(@PathParam("ultramar") int ultramar, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_ultramar ptr = new Relac_pchave_ultramar();
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
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

   @GET
   @Path("/allPalavraChave/{palavra_chave}")
   @Produces({"application/json"})
   public Response allPalavraChave(@PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_ultramar ptr = new Relac_pchave_ultramar();
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
   @Path("/notInUltramar/{ultramar}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInConsulta(@PathParam("ultramar") int ultramar, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_ultramar ptr = new Relac_pchave_ultramar();
     String token = (new Utils()).getToken();
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
         Relac_pchave_ultramar ptr = (Relac_pchave_ultramar)(new ObjectMapper()).readValue(data, Relac_pchave_ultramar.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateUltramar/{palavra_chave}")
   @Produces({"application/json"})
   public Response updateUltramar(String data, @PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_pchave_ultramar ptr = (Relac_pchave_ultramar)(new ObjectMapper()).readValue(data, Relac_pchave_ultramar.class);
         ptr.setPalavra_chave(palavra_chave);
         response = Response.ok(ptr.updateUltramar()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updatePalavraChave/{ultramar}")
   @Produces({"application/json"})
   public Response updatePalavraChave(String data, @PathParam("ultramar") int ultramar, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_pchave_ultramar ptr = (Relac_pchave_ultramar)(new ObjectMapper()).readValue(data, Relac_pchave_ultramar.class);
         ptr.setUltramar(ultramar);
         response = Response.ok(ptr.updatePalavraChave()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{ultramar}/{palavra_chave}")
   @Produces({"application/json"})
   public Response delete(@PathParam("ultramar") int ultramar, @PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_ultramar ptr = new Relac_pchave_ultramar();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setUltramar(ultramar);
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