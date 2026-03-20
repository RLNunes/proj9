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

 public class Tema_agregador
 {
   private int pk_tema_agregador;
   private String designacao;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allAgregador;

   public int getPk_tema_agregador() {
     return this.pk_tema_agregador;
   }

   public void setPk_tema_agregador(int pk_tema_agregador) {
     this.pk_tema_agregador = pk_tema_agregador;
   }

   public String getDesignacao() {
     if ("all".equals(this.designacao)) {
       return "%";
     }
     return this.designacao;
   }

   public void setDesignacao(String designacao) {
     this.designacao = designacao;
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

   public List<Map<String, Object>> getAllAgregador() {
     return this.allAgregador;
   }

   public void setAllAgregador(List<Map<String, Object>> allAgregador) {
     this.allAgregador = allAgregador;
   }

   public Tema_agregador allOficioAgregador() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listOficioAgregador = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_tema_agregador, designacao  from app_hist.tema_agregador  ORDER BY designacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listOficioAgregador.add(Cap);
       } 
       setAllAgregador(listOficioAgregador);
       rs = ps.executeQuery();

       String qCount = "select count(*)  from app_hist.tema_agregador ";

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

   public Tema_agregador oficio_agregador() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Tema_agregador u = new Tema_agregador();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_tema_agregador, designacao from app_hist.tema_agregador where pk_tema_agregador = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.pk_tema_agregador);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setPk_tema_agregador(rs.getInt(1));
         u.setDesignacao(rs.getString(2));
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
     List<Map<String, Object>> listOficioAgregador = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_tema_agregador, designacao from app_hist.tema_agregador  where lower(designacao) like '%'||lower(?)||'%' order by designacao ";

       ps = c.prepareStatement(q);
       ps.setString(1, getDesignacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listOficioAgregador.add(Cap);
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
     return listOficioAgregador;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.tema_agregador   (designacao)   VALUES( ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
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
       String q = "UPDATE app_hist.tema_agregador SET  designacao = ?  where pk_tema_agregador = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
       ps.setObject(2, Integer.valueOf(this.pk_tema_agregador));
       ps.executeUpdate();

       updatedId = this.pk_tema_agregador;
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
     return "" + getPk_tema_agregador();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.tema_agregador  WHERE pk_tema_agregador = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.pk_tema_agregador));

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
     return "" + this.pk_tema_agregador + "";
   }
 }