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

 public class Capitania
 {
   private int capitania_id;
   private String nome_capitania;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allCapitanias;

   public int getCapitania_id() {
     return this.capitania_id;
   }

   public void setCapitania_id(int capitania_id) {
     this.capitania_id = capitania_id;
   }

   public String getNome_capitania() {
     if (this.nome_capitania.equals("all")) {
       this.nome_capitania = "%";
     }
     return this.nome_capitania;
   }

   public void setNome_capitania(String nome_capitania) {
     this.nome_capitania = nome_capitania;
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

   public List<Map<String, Object>> getAllCapitanias() {
     return this.allCapitanias;
   }

   public void setAllCapitanias(List<Map<String, Object>> allCapitanias) {
     this.allCapitanias = allCapitanias;
   }

   public Capitania allCapitanias() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listCapitanias = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select cap.pk_capitania id, cap.nome_capitania    from app_hist.capitania cap ORDER BY cap.nome_capitania             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listCapitanias.add(Cap);
       } 
       setNome_capitania("");
       setAllCapitanias(listCapitanias);

       String qCount = "select count(*)  from app_hist.capitania ";

       ps = c.prepareStatement(qCount);
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public List<Map<String, Object>> allCapitania() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listCapitanias = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select cap.pk_capitania id, cap.nome_capitania    from app_hist.capitania cap ";

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
         listCapitanias.add(Cap);
       }

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
     return listCapitanias;
   }

   public Capitania capitania() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Capitania u = new Capitania();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_capitania id, nome_capitania from app_hist.capitania where pk_capitania = ? ";
       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.capitania_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setCapitania_id(rs.getInt(1));
         u.setNome_capitania(rs.getString(2));
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
     List<Map<String, Object>> listCapitanias = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_capitania id, nome_capitania from app_hist.capitania where lower(nome_capitania) like '%'||lower(?)||'%'  order by nome_capitania";

       ps = c.prepareStatement(q, 1);
       ps.setString(1, getNome_capitania());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listCapitanias.add(Cap);
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
     return listCapitanias;
   }

   public Integer countCap() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numTer  from app_hist.capitania ";

       ps = c.prepareStatement(q);
       rs = ps.executeQuery();

       while (rs.next()) {
         u = Integer.valueOf(rs.getInt(1));
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

   public long create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.capitania (nome_capitania) VALUES(?)";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_capitania);

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
       String q = "UPDATE app_hist.capitania SET  nome_capitania = ? where pk_capitania = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_capitania);
       ps.setObject(2, Integer.valueOf(this.capitania_id));

       ps.executeUpdate();

       updatedId = this.capitania_id;
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

   public String delete() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.capitania  WHERE pk_capitania = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.capitania_id));

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
 }