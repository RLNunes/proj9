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
 import pt.ual.dao.Relac_pchave_consulta;
 import pt.ual.utils.Utils;

 @Path("relac_pchave_consulta")
 public class Relac_pchave_consultaService
 {
   @GET
   @Path("/allConsulta/{consulta}")
   @Produces({"application/json"})
   public Response allConcelheiro(@PathParam("consulta") int consulta, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_consulta ptr = new Relac_pchave_consulta();
     String token = (new Utils()).getToken();
     ptr.setConsulta(consulta);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allConsulta()).header("content-type", "application/json; charset=UTF-8");
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
   public Response allConsulta(@PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_consulta ptr = new Relac_pchave_consulta();
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
   @Path("/notInConsulta/{consulta}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInConsulta(@PathParam("consulta") int consulta, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_consulta ptr = new Relac_pchave_consulta();
     String token = (new Utils()).getToken();
     ptr.setNomePesq(nomePesq);
     ptr.setConsulta(consulta);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInConsulta()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_pchave_consulta ptr = (Relac_pchave_consulta)(new ObjectMapper()).readValue(data, Relac_pchave_consulta.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateConsulta/{palavra_chave}")
   @Produces({"application/json"})
   public Response updateConselheiro(String data, @PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_pchave_consulta ptr = (Relac_pchave_consulta)(new ObjectMapper()).readValue(data, Relac_pchave_consulta.class);
         ptr.setPalavra_chave(palavra_chave);
         response = Response.ok(ptr.updateConsulta()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updatePalavraChave/{consulta}")
   @Produces({"application/json"})
   public Response updateRequerente(String data, @PathParam("consulta") int consulta, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_pchave_consulta ptr = (Relac_pchave_consulta)(new ObjectMapper()).readValue(data, Relac_pchave_consulta.class);
         ptr.setConsulta(consulta);
         response = Response.ok(ptr.updatePalavraChave()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{consulta}/{palavra_chave}")
   @Produces({"application/json"})
   public Response delete(@PathParam("consulta") int consulta, @PathParam("palavra_chave") int palavra_chave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_pchave_consulta ptr = new Relac_pchave_consulta();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setConsulta(consulta);
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