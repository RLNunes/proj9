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
 import pt.ual.dao.Relac_consulta_consulta;
 import pt.ual.utils.Utils;

 @Path("relac_consulta_consulta")
 public class Relac_consulta_consultaService
 {
   @GET
   @Path("/allAntiga/{maisAntiga}/{nomePesq}")
   @Produces({"application/json"})
   public Response allConcelheiro(@PathParam("maisAntiga") int maisAntiga, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_consulta_consulta ptr = new Relac_consulta_consulta();
     String token = (new Utils()).getToken();
     ptr.setConsulta_mais_antiga(maisAntiga);
     ptr.setNomePesq(nomePesq);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.consultaAntigaNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allRecente/{maisRecente}/{nomePesq}")
   @Produces({"application/json"})
   public Response allConsulta(@PathParam("maisRecente") int maisRecente, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_consulta_consulta ptr = new Relac_consulta_consulta();
     String token = (new Utils()).getToken();
     ptr.setConsulta_mais_recente(maisRecente);
     ptr.setNomePesq(nomePesq);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.consultaNovaAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/notInAntiga/{maisRecente}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInAntiga(@PathParam("maisAntiga") int maisAntiga, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_consulta_consulta ptr = new Relac_consulta_consulta();
     String token = (new Utils()).getToken();
     ptr.setConsulta_mais_antiga(maisAntiga);
     ptr.setNomePesq(nomePesq);

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.consultaNotInAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/notInNova/{maisRecente}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInNova(String data, @PathParam("maisRecente") int maisRecente, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_consulta_consulta ptr = (Relac_consulta_consulta)(new ObjectMapper()).readValue(data, Relac_consulta_consulta.class);
     ptr.setConsulta_mais_recente(maisRecente);
     ptr.setNomePesq(nomePesq);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.consultaNotInNova()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_consulta_consulta ptr = (Relac_consulta_consulta)(new ObjectMapper()).readValue(data, Relac_consulta_consulta.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateMaisAntiga/{maisAntiga}")
   @Produces({"application/json"})
   public Response updateConselheiro(String data, @PathParam("maisAntiga") int maisAntiga, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_consulta_consulta ptr = (Relac_consulta_consulta)(new ObjectMapper()).readValue(data, Relac_consulta_consulta.class);
         ptr.setConsulta_mais_antiga(maisAntiga);
         response = Response.ok(ptr.updateAntiga()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateMaisRecente/{maisNova}")
   @Produces({"application/json"})
   public Response updateConsulta(String data, @PathParam("maisNova") int maisNova, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_consulta_consulta ptr = (Relac_consulta_consulta)(new ObjectMapper()).readValue(data, Relac_consulta_consulta.class);
         ptr.setConsulta_mais_recente(maisNova);
         response = Response.ok(ptr.updateNova()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{maisAntiga}/{maisRecente}")
   @Produces({"application/json"})
   public Response delete(@PathParam("maisAntiga") int maisAntiga, @PathParam("maisRecente") int maisRecente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_consulta_consulta ptr = new Relac_consulta_consulta();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setConsulta_mais_antiga(maisAntiga);
         ptr.setConsulta_mais_recente(maisRecente);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }