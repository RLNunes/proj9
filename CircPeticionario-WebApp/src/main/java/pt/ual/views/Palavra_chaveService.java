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
 import pt.ual.dao.Palavra_chave;
 import pt.ual.utils.Utils;

 @Path("palavra_chave")
 public class Palavra_chaveService
 {
   @GET
   @Path("/all/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getAll(@PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Palavra_chave list = new Palavra_chave();
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Palavra_chave palChave = list.allPalavraChave();
         b = Response.ok(palChave);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/record/{palavra_chave_id}")
   @Produces({"application/json"})
   public Response record(@PathParam("palavra_chave_id") int palavra_chave_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Palavra_chave ptr = new Palavra_chave();
     String token = (new Utils()).getToken();
     ptr.setPalavra_chave_id(palavra_chave_id);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.palavra_chave()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/search/{palavra_chave}")
   @Produces({"application/json"})
   public Response search(@PathParam("palavra_chave") String search, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Palavra_chave ptr = new Palavra_chave();
     String token = (new Utils()).getToken();
     ptr.setPalavra_chave(search);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.search()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/consultaPalChave/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getConsultaPessoa(@PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Palavra_chave list = new Palavra_chave();
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Palavra_chave pes = list.consultaPalChave();
         b = Response.ok(pes);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
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
         Palavra_chave ptr = (Palavra_chave)(new ObjectMapper()).readValue(data, Palavra_chave.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/update/{palavra_chave_id}")
   @Produces({"application/json"})
   public Response update(String data, @PathParam("palavra_chave_id") int palavra_chave_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Palavra_chave ptr = (Palavra_chave)(new ObjectMapper()).readValue(data, Palavra_chave.class);
         ptr.setPalavra_chave_id(palavra_chave_id);
         response = Response.ok(ptr.update()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{palavra_chave_id}")
   @Produces({"application/json"})
   public Response delete(@PathParam("palavra_chave_id") int palavra_chave_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Palavra_chave ptr = new Palavra_chave();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setPalavra_chave_id(palavra_chave_id);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }