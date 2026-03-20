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

 public class Marcador_economico_ocupacao
 {
   private int pk_economico_ocupacao;
   private String designacao;
   private String search;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allMarcEco;

   public int getPk_economico_ocupacao() {
     return this.pk_economico_ocupacao;
   }

   public void setPk_economico_ocupacao(int pk_economico_ocupacao) {
     this.pk_economico_ocupacao = pk_economico_ocupacao;
   }

   public String getDesignacao() {
     return this.designacao;
   }

   public void setDesignacao(String designacao) {
     this.designacao = designacao;
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

   public List<Map<String, Object>> getAllMarcEco() {
     return this.allMarcEco;
   }

   public void setAllMarcEco(List<Map<String, Object>> allMarcEco) {
     this.allMarcEco = allMarcEco;
   }

   public Marcador_economico_ocupacao allMarcadorEconomico() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMarcadorStatus = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_economico_ocupacao id, designacao from app_hist.marcador_economico_ocupacao ORDER BY designacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
       setAllMarcEco(listMarcadorStatus);

       String qCount = "select count(*)  from app_hist.marcador_economico_ocupacao ";

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

   public List<Map<String, Object>> allMarEco() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMarcadorStatus = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_economico_ocupacao id, designacao from app_hist.marcador_economico_ocupacao";

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
         listMarcadorStatus.add(Cap);
       }

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

   public Marcador_economico_ocupacao marcadorEconomico() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Marcador_economico_ocupacao u = new Marcador_economico_ocupacao();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_economico_ocupacao id, designacao from app_hist.marcador_economico_ocupacao where pk_economico_ocupacao = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.pk_economico_ocupacao);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setPk_economico_ocupacao(rs.getInt(1));
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
     List<Map<String, Object>> listMarcadorStatus = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_economico_ocupacao id, designacao from app_hist.marcador_economico_ocupacao  where lower(designacao) like '%'||lower(?)||'%'  order by designacao ";

       ps = c.prepareStatement(q, 1);
       ps.setString(1, getSearch());
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
       String q = "INSERT INTO app_hist.marcador_economico_ocupacao (designacao ) VALUES( ?) ";

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
       String q = " UPDATE app_hist.marcador_economico_ocupacao SET  designacao = ?  where pk_economico_ocupacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.designacao);
       ps.setObject(2, Integer.valueOf(this.pk_economico_ocupacao));
       ps.executeUpdate();

       updatedId = this.pk_economico_ocupacao;
       c.commit();
     }
     catch (SQLException ex) {
       c.rollback();
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
     return "" + getPk_economico_ocupacao();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.marcador_economico_ocupacao  WHERE pk_economico_ocupacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.pk_economico_ocupacao));

       if (ps.executeUpdate() > 0) {
         rs = ps.getGeneratedKeys();
         if (null != rs && rs.next()) {
           c.commit();
         }
       }

     } catch (SQLException ex) {
       c.rollback();
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
     return "" + this.pk_economico_ocupacao;
   }
 }