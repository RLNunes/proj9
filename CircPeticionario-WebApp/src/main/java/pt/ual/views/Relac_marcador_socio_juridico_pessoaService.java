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
 import pt.ual.dao.Relac_marcador_socio_juridico_pessoa;
 import pt.ual.utils.Utils;

 @Path("relac_marcador_socio_juridico_pessoa")
 public class Relac_marcador_socio_juridico_pessoaService
 {
   @GET
   @Path("/allMarcSocJuridico/{pessoa}/{search}")
   @Produces({"application/json"})
   public Response allConcelheiro(@PathParam("pessoa") int pessoa, @PathParam("search") String search, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_marcador_socio_juridico_pessoa ptr = new Relac_marcador_socio_juridico_pessoa();
     String token = (new Utils()).getToken();
     ptr.setPessoa(pessoa);
     ptr.setSearch(search);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allMarcSocJuridico()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allPessoa/{pessoa}")
   @Produces({"application/json"})
   public Response allConsulta(@PathParam("pessoa") int pessoa, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_marcador_socio_juridico_pessoa ptr = new Relac_marcador_socio_juridico_pessoa();
     String token = (new Utils()).getToken();
     ptr.setPessoa(pessoa);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allPessoa()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/notInPessoa/{pessoa}/{search}")
   @Produces({"application/json"})
   public Response notInPessoa(@PathParam("pessoa") int pessoa, @PathParam("search") String search, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_marcador_socio_juridico_pessoa ptr = new Relac_marcador_socio_juridico_pessoa();
     String token = (new Utils()).getToken();
     ptr.setPessoa(pessoa);
     ptr.setSearch(search);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.notInPessoa()).header("content-type", "application/json; charset=UTF-8");
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
         Relac_marcador_socio_juridico_pessoa ptr = (Relac_marcador_socio_juridico_pessoa)(new ObjectMapper()).readValue(data, Relac_marcador_socio_juridico_pessoa.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateMarcSocJuridico/{pessoa}")
   @Produces({"application/json"})
   public Response updateConselheiro(String data, @PathParam("pessoa") int pessoa, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_marcador_socio_juridico_pessoa ptr = (Relac_marcador_socio_juridico_pessoa)(new ObjectMapper()).readValue(data, Relac_marcador_socio_juridico_pessoa.class);
         ptr.setPessoa(pessoa);
         response = Response.ok(ptr.updateMarcSocJuridico()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updatePessoa/{marcador_socio_juridico}")
   @Produces({"application/json"})
   public Response updateRequerente(String data, @PathParam("marcador_socio_juridico") int marcador_socio_juridico, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_marcador_socio_juridico_pessoa ptr = (Relac_marcador_socio_juridico_pessoa)(new ObjectMapper()).readValue(data, Relac_marcador_socio_juridico_pessoa.class);
         ptr.setMarcador_socio_juridico(marcador_socio_juridico);
         response = Response.ok(ptr.updateRequerente()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{marcador_socio_juridico}/{pessoa}")
   @Produces({"application/json"})
   public Response delete(@PathParam("marcador_socio_juridico") int marcador_socio_juridico, @PathParam("pessoa") int pessoa, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_marcador_socio_juridico_pessoa ptr = new Relac_marcador_socio_juridico_pessoa();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setMarcador_socio_juridico(marcador_socio_juridico);
         ptr.setPessoa(pessoa);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }