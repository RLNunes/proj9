 package pt.ual.dao;

 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import pt.ual.utils.Utils;

 public class Destinatario_resposta
 {
   private int destinatario_id;
   private String nome;
   private int oficio_titulo;
   private String search;

   public int getDestinatario_id() {
     return this.destinatario_id;
   }

   public void setDestinatario_id(int destinatario_id) {
     this.destinatario_id = destinatario_id;
   }

   public String getNome() {
     return this.nome;
   }

   public void setNome(String nome) {
     this.nome = nome;
   }

   public int getOficio_titulo() {
     return this.oficio_titulo;
   }

   public void setOficio_titulo(int oficio_titulo) {
     this.oficio_titulo = oficio_titulo;
   }

   public String getSearch() {
     return this.search;
   }

   public void setSearch(String search) {
     this.search = search;
   }

   public List<Map<String, Object>> allDestResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listDestResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_destinatario, nome, oficio_titulo from app_hist.destinatario_resposta";

       ps = c.prepareStatement(q);
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listDestResposta.add(Cap);
       } 
       rs = ps.executeQuery();
     } catch (Exception ex) {
       ex.printStackTrace();
       throw ex;
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return listDestResposta;
   }

   public Destinatario_resposta conselheiro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Destinatario_resposta u = new Destinatario_resposta();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_destinatario, nome, oficio_titulo from app_hist.destinatario_resposta where pk_destinatario = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.destinatario_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setDestinatario_id(rs.getInt(1));
         u.setNome(rs.getString(2));
         u.setOficio_titulo(rs.getInt(3));
       } 
       rs.close();
       ps.close();
     }
     catch (Exception ex) {
       ex.printStackTrace();
       throw ex;
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return u;
   }

   public List<Map<String, Object>> search() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsultas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_destinatario, nome, oficio_titulo from app_hist.destinatario_resposta where lower(nome) like '%'||lower(?)||'%'\nor  lower(oficio_titulo) like '%'||lower(?)||'%'\n";

       ps = c.prepareStatement(q);
       ps.setString(1, getSearch());
       ps.setString(2, getSearch());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listConsultas.add(Cap);
       } 
       rs = ps.executeQuery();
     } catch (Exception ex) {
       ex.printStackTrace();
       throw ex;
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return listConsultas;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.destinatario_resposta (nome, oficio_titulo) VALUES(  ?, ?)";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome);
       ps.setObject(2, Integer.valueOf(this.oficio_titulo));
       if (ps.executeUpdate() > 0) {
         rs = ps.getGeneratedKeys();
         if (null != rs && rs.next()) {
           toRet = rs.getLong(1);
           c.commit();
         }

       } 
     } catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
       return ex.getMessage();
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return "Inserido com sucesso.";
   }

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.destinatario_resposta SET  nome = ?,  oficio_titulo = ?  where pk_destinatario = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome);
       ps.setObject(2, Integer.valueOf(this.oficio_titulo));
       ps.setObject(3, Integer.valueOf(this.destinatario_id));
       ps.executeUpdate();

       updatedId = this.destinatario_id;
       c.commit();
     }
     catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
       return ex.getMessage();
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return "O ID: " + getDestinatario_id() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.destinatario_resposta  WHERE pk_destinatario = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.destinatario_id));

       if (ps.executeUpdate() > 0) {
         rs = ps.getGeneratedKeys();
         if (null != rs && rs.next()) {
           toRet = rs.getLong(1);
           c.commit();
         }

       } 
     } catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
       return ex.getMessage();
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return "O ID: " + this.destinatario_id + " foi eliminado com sucesso.";
   }
 }