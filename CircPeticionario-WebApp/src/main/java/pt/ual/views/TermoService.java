 package pt.ual.views;

 import com.fasterxml.jackson.databind.ObjectMapper;
 import java.util.List;
 import java.util.Map;
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
 import pt.ual.dao.Termo;
 import pt.ual.utils.Utils;

 @Path("termo")
 public class TermoService
 {
   @GET
   @Path("/all/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getAll(@PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Termo list = new Termo();
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Termo Term = list.allRegistros();
         b = Response.ok(Term);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/all")
   @Produces({"application/json"})
   public Response getAllTermo(@Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Termo list = new Termo();
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         List<Map<String, Object>> Term = list.allReg();
         b = Response.ok(Term);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/record/{termo_id}")
   @Produces({"application/json"})
   public Response record(@PathParam("termo_id") int termo_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Termo ptr = new Termo();
     String token = (new Utils()).getToken();
     ptr.setTermo_id(termo_id);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.registro()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/search/{nome_termo}")
   @Produces({"application/json"})
   public Response search(@PathParam("nome_termo") String nome_termo, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Termo ptr = new Termo();
     String token = (new Utils()).getToken();
     ptr.setNome_termo(nome_termo);
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
   @Path("/count")
   @Produces({"application/json"})
   public Response pesCount(@Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Termo ptr = new Termo();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.countTermo()).header("content-type", "application/json; charset=UTF-8");
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
         Termo ptr = (Termo)(new ObjectMapper()).readValue(data, Termo.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/update/{termo_id}")
   @Produces({"application/json"})
   public Response update(String data, @PathParam("termo_id") int termo_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Termo ptr = (Termo)(new ObjectMapper()).readValue(data, Termo.class);
         ptr.setTermo_id(termo_id);
         response = Response.ok(ptr.update()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{termo_id}")
   @Produces({"application/json"})
   public Response delete(@PathParam("termo_id") int termo_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Termo ptr = new Termo();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setTermo_id(termo_id);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }