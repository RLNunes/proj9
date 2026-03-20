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
 import pt.ual.dao.Marcador_economico_ocupacao;
 import pt.ual.utils.Utils;

 @Path("marcador_economico")
 public class Marcador_economico_ocupacaoService
 {
   @GET
   @Path("/all/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getAll(@PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Marcador_economico_ocupacao list = new Marcador_economico_ocupacao();
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Marcador_economico_ocupacao marEco = list.allMarcadorEconomico();
         b = Response.ok(marEco);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/all")
   @Produces({"application/json"})
   public Response getAllEco(@Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Marcador_economico_ocupacao list = new Marcador_economico_ocupacao();
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         List<Map<String, Object>> marEco = list.allMarEco();
         b = Response.ok(marEco);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/record/{marcador_economico_id}")
   @Produces({"application/json"})
   public Response record(@PathParam("marcador_economico_id") int marcador_economico_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Marcador_economico_ocupacao ptr = new Marcador_economico_ocupacao();
     String token = (new Utils()).getToken();
     ptr.setPk_economico_ocupacao(marcador_economico_id);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.marcadorEconomico()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/search/{nome}")
   @Produces({"application/json"})
   public Response search(@PathParam("nome") String search, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Marcador_economico_ocupacao ptr = new Marcador_economico_ocupacao();
     String token = (new Utils()).getToken();
     ptr.setSearch(search);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.search()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
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
         Marcador_economico_ocupacao ptr = (Marcador_economico_ocupacao)(new ObjectMapper()).readValue(data, Marcador_economico_ocupacao.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/update/{marcador_economico_id}")
   @Produces({"application/json"})
   public Response update(String data, @PathParam("marcador_economico_id") int marcador_economico_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Marcador_economico_ocupacao ptr = (Marcador_economico_ocupacao)(new ObjectMapper()).readValue(data, Marcador_economico_ocupacao.class);
         ptr.setPk_economico_ocupacao(marcador_economico_id);
         response = Response.ok(ptr.update()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{marcador_economico_id}")
   @Produces({"application/json"})
   public Response delete(@PathParam("marcador_economico_id") int marcador_economico_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Marcador_economico_ocupacao ptr = new Marcador_economico_ocupacao();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setPk_economico_ocupacao(marcador_economico_id);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }