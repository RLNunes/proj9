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

 public class Palavra_chave
 {
   private int palavra_chave_id;
   private String palavra_chave;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allPalChaves;

   public int getPalavra_chave_id() {
     return this.palavra_chave_id;
   }

   public void setPalavra_chave_id(int palavra_chave_id) {
     this.palavra_chave_id = palavra_chave_id;
   }

   public String getPalavra_chave() {
     return this.palavra_chave;
   }

   public void setPalavra_chave(String palavra_chave) {
     this.palavra_chave = palavra_chave;
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

   public List<Map<String, Object>> getAllPalChaves() {
     return this.allPalChaves;
   }

   public void setAllPalChaves(List<Map<String, Object>> allPalChaves) {
     this.allPalChaves = allPalChaves;
   }

   public Palavra_chave allPalavraChave() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPalavraChave = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_palavra_chave, palavra_chave from app_hist.palavra_chave  ORDER BY palavra_chave             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listPalavraChave.add(Cap);
       } 
       setAllPalChaves(listPalavraChave);

       String qCount = "select count(*)  from app_hist.palavra_chave ";

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

   public Palavra_chave palavra_chave() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Palavra_chave u = new Palavra_chave();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_palavra_chave, palavra_chave from app_hist.palavra_chave  where pk_palavra_chave = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.palavra_chave_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setPalavra_chave_id(rs.getInt(1));
         u.setPalavra_chave(rs.getString(2));
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
     List<Map<String, Object>> listPalavraChave = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_palavra_chave, palavra_chave from app_hist.palavra_chave where lower(palavra_chave) like '%'||lower(?)||'%' order by palavra_chave ";

       ps = c.prepareStatement(q);
       ps.setString(1, getPalavra_chave());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listPalavraChave.add(Cap);
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
     return listPalavraChave;
   }

   public Palavra_chave consultaPalChave() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPalChaves = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_palavra_chave, palavra_chave,        (select count(*) from app_hist.relac_pchave_provocacao rtp           where rtp.palavra_chave = t.pk_palavra_chave  ) provNum,        (select count(*) from app_hist.relac_pchave_mandado rtm            where rtm.palavra_chave = t.pk_palavra_chave  ) manNum,        (select count(*) from app_hist.relac_pchave_consulta rtc           where rtc.palavra_chave = t.pk_palavra_chave  ) conNum,        (select count(*) from app_hist.relac_pchave_ultramar rtu           where rtu.palavra_chave = t.pk_palavra_chave  ) ultraNum,        (select count(*) from app_hist.relac_pchave_resposta rtr           where rtr.palavra_chave = t.pk_palavra_chave  ) resNum   from app_hist.palavra_chave t    ORDER BY t.pk_palavra_chave             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q, 1);
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
         listPalChaves.add(Cap);
       } 
       setAllPalChaves(listPalChaves);

       String qCount = "select count(*)  from app_hist.palavra_chave ";

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

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.palavra_chave   (palavra_chave)   VALUES( ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.palavra_chave);
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
       String q = "UPDATE app_hist.palavra_chave SET  palavra_chave = ? where pk_palavra_chave = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.palavra_chave);
       ps.setObject(2, Integer.valueOf(this.palavra_chave_id));
       ps.executeUpdate();

       updatedId = this.palavra_chave_id;
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
     return "" + getPalavra_chave_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.palavra_chave  WHERE pk_palavra_chave = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.palavra_chave_id));

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
     return "" + this.palavra_chave_id;
   }
 }