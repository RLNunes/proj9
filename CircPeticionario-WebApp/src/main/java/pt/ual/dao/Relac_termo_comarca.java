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

 public class Relac_termo_comarca
 {
   private int termo;
   private int comarca;
   private String search;

   public int getTermo() {
     return this.termo;
   }

   public void setTermo(int termo) {
     this.termo = termo;
   }

   public int getComarca() {
     return this.comarca;
   }

   public void setComarca(int comarca) {
     this.comarca = comarca;
   }

   public String getSearch() {
     if ("all".equals(this.search)) {
       return "%";
     }
     return this.search;
   }

   public void setSearch(String search) {
     this.search = search;
   }

   public List<Map<String, Object>> allTermos() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listTermos = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select c.pk_comarca id, c.nome_comarca comarca from app_hist.relac_termo_comarca rtm, app_hist.comarca c  where rtm.termo = ?    and rtm.comarca = c.pk_comarca    and lower(c.nome_comarca) like '%' || lower(?) || '%' ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getTermo());
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
         listTermos.add(Cap);
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
     return listTermos;
   }

   public List<Map<String, Object>> allComarca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select termo, comarca  from app_hist.relac_termo_comarca  where comarca = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getComarca());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listUltramar.add(Cap);
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
     return listUltramar;
   }

   public List<Map<String, Object>> notInTermo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listTermos = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select c.pk_comarca id, c.nome_comarca comarca     from  app_hist.comarca c    where c.pk_comarca not in (select rtm.comarca     \t\t\t       from app_hist.relac_termo_comarca rtm                 \t\t      where rtm.termo =  ?)    and lower(c.nome_comarca) like '%' || lower(?) || '%' ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getTermo());
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
         listTermos.add(Cap);
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
     return listTermos;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_termo_comarca   (termo, comarca)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.termo));
       ps.setObject(2, Integer.valueOf(this.comarca));
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

   public String updateTermo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_termo_comarca SET  termo = ?  where comarca = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.termo));
       ps.setObject(2, Integer.valueOf(this.comarca));
       ps.executeUpdate();

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
     return "O ID: " + getComarca() + " foi atualizado com sucesso.";
   }

   public String updateCcomarca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_termo_comarca SET  comarca = ?  where termo = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.comarca));
       ps.setObject(2, Integer.valueOf(this.termo));
       ps.executeUpdate();

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
     return "O ID: " + getTermo() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_termo_comarca  WHERE termo = ? and comarca = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.termo));
       ps.setObject(2, Integer.valueOf(this.comarca));

       if (ps.executeUpdate() > 0) {
         rs = ps.getGeneratedKeys();
         if (null != rs && rs.next()) {
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
     return "Eliminado com sucesso.";
   }
 }