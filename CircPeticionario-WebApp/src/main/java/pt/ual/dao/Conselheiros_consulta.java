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

 public class Conselheiros_consulta
 {
   private int conselheiro_id;
   private String nome_conselheiro;

   public int getConselheiro_id() {
     return this.conselheiro_id;
   }

   public void setConselheiro_id(int conselheiro_id) {
     this.conselheiro_id = conselheiro_id;
   }

   public String getNome_conselheiro() {
     return this.nome_conselheiro;
   }

   public void setNome_conselheiro(String nome_conselheiro) {
     this.nome_conselheiro = nome_conselheiro;
   }

   public List<Map<String, Object>> allConselheiros() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConselheiros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select * from app_hist.conselheiros_consulta";
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
         listConselheiros.add(Cap);
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
     return listConselheiros;
   }

   public Conselheiros_consulta conselheiro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Conselheiros_consulta u = new Conselheiros_consulta();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_conselheiro, nome from app_hist.conselheiros_consulta where pk_conselheiro = ? ";
       ps = c.prepareStatement(q);
       ps.setInt(1, this.conselheiro_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setConselheiro_id(rs.getInt(1));
         u.setNome_conselheiro(rs.getString(2));
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
     List<Map<String, Object>> listConselheiros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select * from app_hist.conselheiros_consulta where lower(nome) like '%'||lower(?)||'%'";
       ps = c.prepareStatement(q);
       ps.setString(1, getNome_conselheiro());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listConselheiros.add(Cap);
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
     return listConselheiros;
   }

   public long create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.conselheiros_consulta (nome) VALUES(?)";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome_conselheiro);

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
     return toRet;
   }

   public long update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.conselheiros_consulta SET  nome = ? where pk_conselheiro = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome_conselheiro);
       ps.setObject(2, Integer.valueOf(this.conselheiro_id));

       ps.executeUpdate();

       updatedId = this.conselheiro_id;
       c.commit();
     }
     catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
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
     return updatedId;
   }

   public long delete() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.conselheiros_consulta  WHERE pk_conselheiro = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.conselheiro_id));

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
     return toRet;
   }
 }