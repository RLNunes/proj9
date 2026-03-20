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
 import pt.ual.dao.Relac_oficio_pessoa;
 import pt.ual.utils.Utils;

 @Path("relac_oficio_requerente")
 public class Relac_oficio_pessoaService
 {
   @GET
   @Path("/allOficioTitulo/{oficio_titulo}")
   @Produces({"application/json"})
   public Response allConcelheiro(@PathParam("oficio_titulo") int oficio_titulo, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_oficio_pessoa ptr = new Relac_oficio_pessoa();
     String token = (new Utils()).getToken();
     ptr.setOficio_titulo(oficio_titulo);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allOficioTitulo()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/allPessoa/{pessoa}/{nomePesq}")
   @Produces({"application/json"})
   public Response allPessoa(@PathParam("pessoa") int pessoa, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_oficio_pessoa ptr = new Relac_oficio_pessoa();
     String token = (new Utils()).getToken();
     ptr.setPessoa(pessoa);
     ptr.setNomePesq(nomePesq);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.allRequerente()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/notInPessoa/{pessoa}/{nomePesq}")
   @Produces({"application/json"})
   public Response notInPessoa(@PathParam("pessoa") int pessoa, @PathParam("nomePesq") String nomePesq, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_oficio_pessoa ptr = new Relac_oficio_pessoa();
     String token = (new Utils()).getToken();
     ptr.setPessoa(pessoa);
     ptr.setNomePesq(nomePesq);

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
         Relac_oficio_pessoa ptr = (Relac_oficio_pessoa)(new ObjectMapper()).readValue(data, Relac_oficio_pessoa.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/updateOficioTitulo/{pessoa}")
   @Produces({"application/json"})
   public Response updateConselheiro(String data, @PathParam("pessoa") int pessoa, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_oficio_pessoa ptr = (Relac_oficio_pessoa)(new ObjectMapper()).readValue(data, Relac_oficio_pessoa.class);
         ptr.setPessoa(pessoa);
         response = Response.ok(ptr.updateOficioTitulo()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @PUT
   @Path("/updateRequerente/{oficio_titulo}")
   @Produces({"application/json"})
   public Response updateRequerente(String data, @PathParam("oficio_titulo") int oficio_titulo, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Relac_oficio_pessoa ptr = (Relac_oficio_pessoa)(new ObjectMapper()).readValue(data, Relac_oficio_pessoa.class);
         ptr.setOficio_titulo(oficio_titulo);
         response = Response.ok(ptr.updateRequerente()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{oficio_titulo}/{pessoa}")
   @Produces({"application/json"})
   public Response delete(@PathParam("oficio_titulo") int oficio_titulo, @PathParam("pessoa") int pessoa, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Relac_oficio_pessoa ptr = new Relac_oficio_pessoa();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setOficio_titulo(oficio_titulo);
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