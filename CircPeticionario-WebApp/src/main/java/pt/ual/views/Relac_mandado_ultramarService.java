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
 import pt.ual.dao.Relac_mandado_ultramar;
 import pt.ual.utils.Utils;

 @Path("relac_mandado_ultramar")
 public class Relac_mandado_ultramarService
 {
   @GET
   @Path("/allMandado/{mandado}/{nomePesq}")
   @Produces({"application/json"})
   public Response allConcelheiro(@PathParam("mandado") int mandado, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_mandado_ultramar ptr = new Relac_mandado_ultramar();
     String token = (new Utils()).getToken();
     ptr.setUltramar(mandado);
     ptr.setNomePesq(nomePesq);
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

   @POST
   @Path("/notInMandado/{mandado}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInMandado(String data, @PathParam("mandado") int mandado, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_mandado_ultramar ptr = (Relac_mandado_ultramar)(new ObjectMapper()).readValue(data, Relac_mandado_ultramar.class);
     ptr.setNomePesq(nomePesq);
     ptr.setUltramar(mandado);
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

   @GET
   @Path("/allUltramar/{ultramar}/{nomePesq}")
   @Produces({"application/json"})
   public Response allConsulta(@PathParam("ultramar") int ultramar, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_mandado_ultramar ptr = new Relac_mandado_ultramar();
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
     ptr.setUltramar(ultramar);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allUltrarmar()).header("content-type", "application/json; charset=UTF-8");
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
   public Response notInResposta(String data, @PathParam("ultramar") int ultramar, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_mandado_ultramar ptr = (Relac_mandado_ultramar)(new ObjectMapper()).readValue(data, Relac_mandado_ultramar.class);
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
         Relac_mandado_ultramar ptr = (Relac_mandado_ultramar)(new ObjectMapper()).readValue(data, Relac_mandado_ultramar.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateMandado/{mandado}")
   @Produces({"application/json"})
   public Response updateConselheiro(String data, @PathParam("mandado") int mandado, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_mandado_ultramar ptr = (Relac_mandado_ultramar)(new ObjectMapper()).readValue(data, Relac_mandado_ultramar.class);
         ptr.setMandado(mandado);
         response = Response.ok(ptr.updateMandado()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateUltramar/{ultramar}")
   @Produces({"application/json"})
   public Response updateConsulta(String data, @PathParam("ultramar") int ultramar, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_mandado_ultramar ptr = (Relac_mandado_ultramar)(new ObjectMapper()).readValue(data, Relac_mandado_ultramar.class);
         ptr.setUltramar(ultramar);
         response = Response.ok(ptr.updateUltramar()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{mandado}/{ultramar}")
   @Produces({"application/json"})
   public Response delete(@PathParam("mandado") int mandado, @PathParam("ultramar") int ultramar, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_mandado_ultramar ptr = new Relac_mandado_ultramar();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setUltramar(ultramar);
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