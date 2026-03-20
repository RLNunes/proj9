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
 import pt.ual.dao.Relac_mandado_mandado;
 import pt.ual.utils.Utils;

 @Path("relac_mandado_mandado")
 public class Relac_mandado_mandadoService
 {
   @GET
   @Path("/allAntigo/{maisAntigo}/{nomePesq}")
   @Produces({"application/json"})
   public Response allConcelheiro(@PathParam("maisAntigo") int maisAntigo, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_mandado_mandado ptr = new Relac_mandado_mandado();
     String token = (new Utils()).getToken();
     ptr.setMandado_mais_antigo(maisAntigo);
     ptr.setNomePesq(nomePesq);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.mandadoAntigoNovo()).header("content-type", "application/json; charset=UTF-8");
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
     Relac_mandado_mandado ptr = new Relac_mandado_mandado();
     String token = (new Utils()).getToken();
     ptr.setMandado_mais_recente(maisRecente);
     ptr.setNomePesq(nomePesq);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.mandadoNovoAntigo()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/notInAntigo/{maisAntigo}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInAntiga(@PathParam("maisAntigo") int maisAntigo, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_mandado_mandado ptr = new Relac_mandado_mandado();
     String token = (new Utils()).getToken();
     ptr.setMandado_mais_antigo(maisAntigo);
     ptr.setNomePesq(nomePesq);

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.mandadoNotInAntigo()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @POST
   @Path("/notInNovo/{maisRecente}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInNovo(String data, @PathParam("maisRecente") int maisRecente, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;

     String token = (new Utils()).getToken();
     Relac_mandado_mandado ptr = (Relac_mandado_mandado)(new ObjectMapper()).readValue(data, Relac_mandado_mandado.class);
     ptr.setMandado_mais_recente(maisRecente);
     ptr.setNomePesq(nomePesq);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.mandadoNotInNovo()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_mandado_mandado ptr = (Relac_mandado_mandado)(new ObjectMapper()).readValue(data, Relac_mandado_mandado.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateMaisAntigo/{maisAntiga}")
   @Produces({"application/json"})
   public Response updateMaisAntigo(String data, @PathParam("maisAntiga") int maisAntiga, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_mandado_mandado ptr = (Relac_mandado_mandado)(new ObjectMapper()).readValue(data, Relac_mandado_mandado.class);
         ptr.setMandado_mais_antigo(maisAntiga);
         response = Response.ok(ptr.updateAntigo()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateMaisRecente/{maisNovo}")
   @Produces({"application/json"})
   public Response updateConsulta(String data, @PathParam("maisNovo") int maisNovo, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_mandado_mandado ptr = (Relac_mandado_mandado)(new ObjectMapper()).readValue(data, Relac_mandado_mandado.class);
         ptr.setMandado_mais_recente(maisNovo);
         response = Response.ok(ptr.updateNovo()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{maisAntigo}/{maisRecente}")
   @Produces({"application/json"})
   public Response delete(@PathParam("maisAntigo") int maisAntigo, @PathParam("maisRecente") int maisRecente, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_mandado_mandado ptr = new Relac_mandado_mandado();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setMandado_mais_antigo(maisAntigo);
         ptr.setMandado_mais_recente(maisRecente);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }