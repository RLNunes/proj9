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

 public class Tipo_referencia_documental
 {
   private int tipo_referencia_id;
   private String tipo_referencia;

   public int getTipo_referencia_id() {
     return this.tipo_referencia_id;
   }

   public void setTipo_referencia_id(int tipo_referencia_id) {
     this.tipo_referencia_id = tipo_referencia_id;
   }

   public String getTipo_referencia() {
     return this.tipo_referencia;
   }

   public void setTipo_referencia(String tipo_referencia) {
     this.tipo_referencia = tipo_referencia;
   }

   public List<Map<String, Object>> allRegistros() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRegistros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_tipo_referencia id, tipo_referencia  from app_hist.tipo_referencia_documental ";

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
         listRegistros.add(Cap);
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
     return listRegistros;
   }

   public Tipo_referencia_documental registro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Tipo_referencia_documental u = new Tipo_referencia_documental();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_tipo_referencia id, tipo_referencia  from app_hist.tipo_referencia_documental  where pk_tipo_referencia = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.tipo_referencia_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setTipo_referencia_id(rs.getInt(1));
         u.setTipo_referencia(rs.getString(2));
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
     List<Map<String, Object>> listRefDocumental = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_tipo_referencia id, tipo_referencia  from app_hist.tipo_referencia_documental  where lower(tipo_referencia) like '%'||lower(?)||'%' ";

       ps = c.prepareStatement(q);
       ps.setString(1, getTipo_referencia());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listRefDocumental.add(Cap);
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
     return listRefDocumental;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.tipo_referencia_documental   ( tipo_referencia)   VALUES( ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.tipo_referencia);
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
       String q = "UPDATE app_hist.tipo_referencia_documental SET  tipo_referencia = ?  where pk_tipo_referencia = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.tipo_referencia);
       ps.setObject(2, Integer.valueOf(this.tipo_referencia_id));
       ps.executeUpdate();

       updatedId = this.tipo_referencia_id;
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
     return "O ID: " + getTipo_referencia_id() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.tipo_referencia_documental  WHERE pk_tipo_referencia = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.tipo_referencia_id));

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
     return "O ID: " + this.tipo_referencia_id + " foi eliminado com sucesso.";
   }
 }