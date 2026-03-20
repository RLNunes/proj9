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
 import pt.ual.dao.Relac_consulta_provocacao;
 import pt.ual.utils.Utils;

 @Path("relac_consulta_provocacao")
 public class Relac_consulta_provocacaoService
 {
   @GET
   @Path("/allProvocacao/{provocacao}/{nomePesq}")
   @Produces({"application/json"})
   public Response allConcelheiro(@PathParam("provocacao") int provocacao, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_consulta_provocacao ptr = new Relac_consulta_provocacao();
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

   @GET
   @Path("/allConsulta/{consulta}")
   @Produces({"application/json"})
   public Response allConsulta(@PathParam("consulta") int consulta, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_consulta_provocacao ptr = new Relac_consulta_provocacao();
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

   @POST
   @Path("/notInProvocacao/{provocacao}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInProvocacao(String data, @PathParam("provocacao") int provocacao, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_consulta_provocacao ptr = (Relac_consulta_provocacao)(new ObjectMapper()).readValue(data, Relac_consulta_provocacao.class);
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
   @Path("/notInConsulta/{consulta}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInConsulta(String data, @PathParam("consulta") int consulta, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_consulta_provocacao ptr = (Relac_consulta_provocacao)(new ObjectMapper()).readValue(data, Relac_consulta_provocacao.class);
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
         Relac_consulta_provocacao ptr = (Relac_consulta_provocacao)(new ObjectMapper()).readValue(data, Relac_consulta_provocacao.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateProvocacao/{provocacao}")
   @Produces({"application/json"})
   public Response updateConselheiro(String data, @PathParam("provocacao") int provocacao, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_consulta_provocacao ptr = (Relac_consulta_provocacao)(new ObjectMapper()).readValue(data, Relac_consulta_provocacao.class);
         ptr.setProvocacao(provocacao);
         response = Response.ok(ptr.updateProvocacao()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateConsulta/{consulta}")
   @Produces({"application/json"})
   public Response updateConsulta(String data, @PathParam("consulta") int consulta, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_consulta_provocacao ptr = (Relac_consulta_provocacao)(new ObjectMapper()).readValue(data, Relac_consulta_provocacao.class);
         ptr.setConsulta(consulta);
         response = Response.ok(ptr.updateConsulta()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{consulta}/{provocacao}")
   @Produces({"application/json"})
   public Response delete(@PathParam("provocacao") int provocacao, @PathParam("consulta") int consulta, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_consulta_provocacao ptr = new Relac_consulta_provocacao();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setProvocacao(provocacao);
         ptr.setConsulta(consulta);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }