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

 public class Marcador_status_socio_juridico_pessoa
 {
   private int marcador_socio_juridico_id;
   private String marcador_status_juridico;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allMarcStatus;

   public int getMarcador_socio_juridico_id() {
     return this.marcador_socio_juridico_id;
   }

   public void setMarcador_socio_juridico_id(int marcador_socio_juridico_id) {
     this.marcador_socio_juridico_id = marcador_socio_juridico_id;
   }

   public String getMarcador_status_juridico() {
     if ("all".equals(this.marcador_status_juridico)) {
       return "%";
     }
     return this.marcador_status_juridico;
   }

   public void setMarcador_status_juridico(String marcador_status_juridico) {
     this.marcador_status_juridico = marcador_status_juridico;
   }

   public int getPageNum() {
     return this.pageNum;
   }

   public void setPageNum(int pageNum) {
     this.pageNum = pageNum;
   }

   public int getRowsPage() {
     return this.rowsPage;
   }

   public void setRowsPage(int rowsPage) {
     this.rowsPage = rowsPage;
   }

   public int getRowcount() {
     return this.rowcount;
   }

   public void setRowcount(int rowcount) {
     this.rowcount = rowcount;
   }

   public List<Map<String, Object>> getAllMarcStatus() {
     return this.allMarcStatus;
   }

   public void setAllMarcStatus(List<Map<String, Object>> allMarcStatus) {
     this.allMarcStatus = allMarcStatus;
   }

   public Marcador_status_socio_juridico_pessoa allMarcadorStatus() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMarcadorStatus = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_marcador_socio_juridico id, marcador_status_juridico from app_hist.marcador_status_socio_juridico_pessoa  ORDER BY marcador_status_juridico             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.rowsPage);
       ps.setInt(2, this.pageNum);
       ps.setInt(3, this.rowsPage);
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listMarcadorStatus.add(Cap);
       } 
       setAllMarcStatus(listMarcadorStatus);

       String qCount = "select count(*)  from app_hist.marcador_status_socio_juridico_pessoa ";

       ps = c.prepareStatement(qCount);
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public Marcador_status_socio_juridico_pessoa marcador_status_socio_juridico_pessoa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Marcador_status_socio_juridico_pessoa u = new Marcador_status_socio_juridico_pessoa();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_marcador_socio_juridico id, marcador_status_juridico from app_hist.marcador_status_socio_juridico_pessoa  where pk_marcador_socio_juridico = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.marcador_socio_juridico_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setMarcador_socio_juridico_id(rs.getInt(1));
         u.setMarcador_status_juridico(rs.getString(2));
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
     List<Map<String, Object>> listMarcadorStatus = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_marcador_socio_juridico id, marcador_status_juridico  from app_hist.marcador_status_socio_juridico_pessoa   where lower(marcador_status_juridico) like '%'||lower(?)||'%'  order by marcador_status_juridico ";

       ps = c.prepareStatement(q);
       ps.setString(1, getMarcador_status_juridico());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listMarcadorStatus.add(Cap);
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
     return listMarcadorStatus;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.marcador_status_socio_juridico_pessoa (marcador_status_juridico ) VALUES( ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.marcador_status_juridico);
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
     return "" + toRet;
   }

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.marcador_status_socio_juridico_pessoa SET  marcador_status_juridico = ?  where pk_marcador_socio_juridico = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.marcador_status_juridico);
       ps.setObject(2, Integer.valueOf(this.marcador_socio_juridico_id));
       ps.executeUpdate();

       updatedId = this.marcador_socio_juridico_id;
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
     return "" + getMarcador_socio_juridico_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.marcador_status_socio_juridico_pessoa  WHERE pk_marcador_socio_juridico = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.marcador_socio_juridico_id));

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
     return "" + this.marcador_socio_juridico_id;
   }
 }