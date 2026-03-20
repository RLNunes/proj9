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
 import pt.ual.dao.Ultramar;
 import pt.ual.utils.Utils;

 @Path("ultramar")
 public class UltramarService
 {
   @GET
   @Path("/all/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getAll(@PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Ultramar list = new Ultramar();
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Ultramar ult = list.allUltramar();
         b = Response.ok(ult);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/record/{ultramar_id}")
   @Produces({"application/json"})
   public Response record(@PathParam("ultramar_id") int ultramar_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Ultramar ptr = new Ultramar();
     String token = (new Utils()).getToken();
     ptr.setUltramar_id(ultramar_id);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.ultramar()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/recordInfo/{ultramar_id}")
   @Produces({"application/json"})
   public Response recordInfo(@PathParam("ultramar_id") int ultramar_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Ultramar ptr = new Ultramar();
     String token = (new Utils()).getToken();
     ptr.setUltramar_id(ultramar_id);
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.ultramarInfo()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/search/{search}/{pessoa}/{termo}/{palChave}")
   @Produces({"application/json"})
   public Response search(@PathParam("search") String search, @PathParam("pessoa") String Pessoa, @PathParam("termo") int termo, @PathParam("palChave") int palChave, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Ultramar ptr = new Ultramar();
     String token = (new Utils()).getToken();
     ptr.setSearch(search);
     ptr.setFilPessoa(Pessoa);
     ptr.setFilTermo(Integer.valueOf(termo));
     ptr.setFilPalChave(Integer.valueOf(palChave));
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
   public Response record(@Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Ultramar ptr = new Ultramar();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         response = Response.ok(ptr.countUltramar()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @GET
   @Path("/getConReqUltramar/{provocaId}/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getConReqUltramar(@PathParam("provocaId") int provocaId, @PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Ultramar list = new Ultramar();
     list.setIdPessoa(Integer.valueOf(provocaId));
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Ultramar prov = list.conReqUltramar();
         b = Response.ok(prov);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/getConPesCitaUltramar/{provocaId}/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getConPesCitaUltramar(@PathParam("provocaId") int provocaId, @PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Ultramar list = new Ultramar();
     list.setIdPessoa(Integer.valueOf(provocaId));
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Ultramar prov = list.conPesCitaUltramar();
         b = Response.ok(prov);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/getConTemaUltramar/{temaId}/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getConTemaUltramar(@PathParam("temaId") int temaId, @PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Ultramar list = new Ultramar();
     list.setIdTema(Integer.valueOf(temaId));
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Ultramar prov = list.conTemaUltramar();
         b = Response.ok(prov);
       } 
     } catch (Exception ex) {
       b = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex);
     } 
     return b.build();
   }

   @GET
   @Path("/getConPalChaveUltramar/{palChaveId}/{pageNum}/{rowsPage}")
   @Produces({"application/json"})
   public Response getConPalChaveUltramar(@PathParam("palChaveId") int palChaveId, @PathParam("pageNum") int pageNum, @PathParam("rowsPage") int rowsPage, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     Response.ResponseBuilder b = null;
     String token = (new Utils()).getToken();
     Ultramar list = new Ultramar();
     list.setIdPalChave(Integer.valueOf(palChaveId));
     list.setPageNum(pageNum);
     list.setRowsPage(rowsPage);
     try {
       if (authKey == null || !authKey.equals(token)) {
         b = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Ultramar prov = list.conPalChaveUltramar();
         b = Response.ok(prov);
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
         Ultramar ptr = (Ultramar)(new ObjectMapper()).readValue(data, Ultramar.class);
         response = Response.ok(ptr.create()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR);
     } 
     return response.build();
   }

   @PUT
   @Path("/update/{ultramar_id}")
   @Produces({"application/json"})
   public Response update(String data, @PathParam("ultramar_id") int ultramar_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     String token = (new Utils()).getToken();

     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         Ultramar ptr = (Ultramar)(new ObjectMapper()).readValue(data, Ultramar.class);
         ptr.setUltramar_id(ultramar_id);
         response = Response.ok(ptr.update()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }

   @DELETE
   @Path("/delete/{ultramar_id}")
   @Produces({"application/json"})
   public Response delete(@PathParam("ultramar_id") int ultramar_id, @Context HttpHeaders headers, @HeaderParam("token") String authKey) throws Exception {
     boolean result = false;
     Response.ResponseBuilder response = null;
     Ultramar ptr = new Ultramar();
     String token = (new Utils()).getToken();
     try {
       if (authKey == null || !authKey.equals(token)) {
         response = Response.status(Response.Status.UNAUTHORIZED);
       } else {
         ptr.setUltramar_id(ultramar_id);
         response = Response.ok(ptr.delete()).header("content-type", "application/json; charset=UTF-8");
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Boolean.valueOf(result)).header("content-type", "application/json; charset=UTF-8");
     } 
     return response.build();
   }
 }