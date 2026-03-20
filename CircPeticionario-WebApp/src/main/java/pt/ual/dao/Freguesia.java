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

 public class Freguesia
 {
   private int freguesia_id;
   private String nome_freguesia;
   private Integer comarca;
   private String comarcaDesc;
   private Integer capitania;
   private String capitaniaDesc;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allFreguesias;

   public int getFreguesia_id() {
     return this.freguesia_id;
   }

   public void setFreguesia_id(int freguesia_id) {
     this.freguesia_id = freguesia_id;
   }

   public String getNome_freguesia() {
     if ("all".equals(this.nome_freguesia)) {
       return "%";
     }
     return this.nome_freguesia;
   }

   public Integer getComarca() {
     return this.comarca;
   }

   public void setComarca(Integer comarca) {
     this.comarca = comarca;
   }

   public String getComarcaDesc() {
     return this.comarcaDesc;
   }

   public void setComarcaDesc(String comarcaDesc) {
     this.comarcaDesc = comarcaDesc;
   }

   public Integer getCapitania() {
     return this.capitania;
   }

   public void setCapitania(Integer capitania) {
     this.capitania = capitania;
   }

   public String getCapitaniaDesc() {
     return this.capitaniaDesc;
   }

   public void setCapitaniaDesc(String capitaniaDesc) {
     this.capitaniaDesc = capitaniaDesc;
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

   public void setNome_freguesia(String nome_freguesia) {
     this.nome_freguesia = nome_freguesia;
   }

   public List<Map<String, Object>> getAllFreguesias() {
     return this.allFreguesias;
   }

   public void setAllFreguesias(List<Map<String, Object>> allFreguesias) {
     this.allFreguesias = allFreguesias;
   }

   public int getRowcount() {
     return this.rowcount;
   }

   public void setRowcount(int rowcount) {
     this.rowcount = rowcount;
   }

   public Freguesia allFreguesias() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listFreguesias = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select freg.pk_freguesia id, freg.nome_freguesia, freg.capitania , freg.comarca,   coalesce ((select com.nome_comarca               from app_hist.comarca com              where com.pk_comarca = freg.comarca),'') comarcaDesc,   coalesce ((select cap.nome_capitania                 from app_hist.capitania cap               where cap.pk_capitania = freg.capitania),'') capitaniaDesc  from app_hist.freguesia freg  ORDER BY freg.nome_freguesia             LIMIT ?             OFFSET ((? - 1) * ?)";

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
         listFreguesias.add(Cap);
       } 

       setAllFreguesias(listFreguesias);

       String qConut = "select count(*)  from app_hist.freguesia ";

       ps = c.prepareStatement(qConut);
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

   public Freguesia freguesia() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Freguesia u = new Freguesia();
     try {
       c = Utils.getConnectionStock();
       String q = "select freg.pk_freguesia id, freg.nome_freguesia, freg.capitania , freg.comarca,   coalesce ((select com.nome_comarca               from app_hist.comarca com              where com.pk_comarca = freg.comarca),'') comarcaDesc,   coalesce ((select cap.nome_capitania                 from app_hist.capitania cap               where cap.pk_capitania = freg.capitania),'') capitaniaDesc  from app_hist.freguesia freg where freg.pk_freguesia = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.freguesia_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setFreguesia_id(rs.getInt(1));
         u.setNome_freguesia(rs.getString(2));
         u.setCapitania(Integer.valueOf(rs.getInt(3)));
         u.setComarca(Integer.valueOf(rs.getInt(4)));
         u.setComarcaDesc(rs.getString(5));
         u.setCapitaniaDesc(rs.getString(6));
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
     List<Map<String, Object>> listFreguesias = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select freg.pk_freguesia id, freg.nome_freguesia, freg.capitania , freg.comarca,   coalesce ((select com.nome_comarca               from app_hist.comarca com              where com.pk_comarca = freg.comarca),'') comarcaDesc,   coalesce ((select cap.nome_capitania                 from app_hist.capitania cap               where cap.pk_capitania = freg.capitania),'') capitaniaDesc  from app_hist.freguesia freg  where lower(nome_freguesia) like '%'||lower(?)||'%'    or (select lower(com.nome_comarca)               from app_hist.comarca com              where com.pk_comarca = freg.comarca) like '%'||lower(?)||'%'    or (select cap.nome_capitania                 from app_hist.capitania cap               where cap.pk_capitania = freg.capitania) like '%'||lower(?)||'%'  order by nome_freguesia ";

       ps = c.prepareStatement(q, 1);
       ps.setString(1, getNome_freguesia());
       ps.setString(2, getNome_freguesia());
       ps.setString(3, getNome_freguesia());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listFreguesias.add(Cap);
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
     return listFreguesias;
   }

   public Integer countFreg() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numTer  from app_hist.freguesia ";

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

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.freguesia (nome_freguesia, capitania, comarca) VALUES(  ?, ?, ?)";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_freguesia);
       ps.setObject(2, this.capitania);
       ps.setObject(3, this.comarca);
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
     return "" + getFreguesia_id();
   }

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.freguesia SET  nome_freguesia = ?, capitania = ?, comarca = ?  where pk_freguesia = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_freguesia);
       ps.setObject(2, this.capitania);
       ps.setObject(3, this.comarca);
       ps.setObject(4, Integer.valueOf(this.freguesia_id));
       ps.executeUpdate();

       updatedId = this.freguesia_id;
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
     return "" + getFreguesia_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.freguesia  WHERE pk_freguesia = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.freguesia_id));

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
     return "" + this.freguesia_id;
   }
 }