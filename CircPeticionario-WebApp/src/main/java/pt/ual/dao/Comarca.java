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

 public class Comarca
 {
   private int comarca_id;
   private String nome_comarca;
   private Integer comarca_nome2;
   private String comarca_nome2Desc;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allComarcas;

   public int getComarca_id() {
     return this.comarca_id;
   }

   public void setComarca_id(int comarca_id) {
     this.comarca_id = comarca_id;
   }

   public String getNome_comarca() {
     if (this.nome_comarca.equals("all")) {
       return "%";
     }
     return this.nome_comarca;
   }

   public void setNome_comarca(String nome_comarca) {
     this.nome_comarca = nome_comarca;
   }

   public Integer getComarca_nome2() {
     return this.comarca_nome2;
   }

   public void setComarca_nome2(Integer comarca_nome2) {
     this.comarca_nome2 = comarca_nome2;
   }

   public String getComarca_nome2Desc() {
     return this.comarca_nome2Desc;
   }

   public void setComarca_nome2Desc(String comarca_nome2Desc) {
     this.comarca_nome2Desc = comarca_nome2Desc;
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

   public void setAllComarcas(List<Map<String, Object>> allComarcas) {
     this.allComarcas = allComarcas;
   }

   public List<Map<String, Object>> getAllComarcas() {
     return this.allComarcas;
   }

   public Comarca allComarcas() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listComarcas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select \tc.pk_comarca id, \tc.nome_comarca || coalesce ( ' (' ||( \tselect \t\tc2.nome_comarca \tfrom \t\tapp_hist.comarca c2 \twhere \t\tc2.pk_comarca = c.comarca_nome2)|| ')', \t'') nome,  c.nome_comarca nome_comarca,  coalesce ((select c3.nome_comarca \t\t\t    from app_hist.comarca c3 \t\t\t   where c3.pk_comarca = c.comarca_nome2),'') comarca_nome2  from app_hist.comarca c  ORDER BY c.nome_comarca             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listComarcas.add(Cap);
       } 
       setNome_comarca("");
       setAllComarcas(listComarcas);

       String qCount = "select count(*)  from app_hist.comarca ";

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

   public Comarca comarca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Comarca u = new Comarca();
     try {
       c = Utils.getConnectionStock();
       String q = "select c.pk_comarca id, c.nome_comarca||coalesce ( ' ('||(select c2.nome_comarca \t\t\t\t\t\t\tfrom app_hist.comarca c2 \t\t\t\t\t\t\twhere c2.pk_comarca = c.comarca_nome2)||')','') nome,  c.nome_comarca,  c.comarca_nome2,  coalesce((select c2.nome_comarca \t\t\t\t\t\t\tfrom app_hist.comarca c2 \t\t\t\t\t\t\twhere c2.pk_comarca = c.comarca_nome2),'') Comarca2Desc from app_hist.comarca c where c.pk_comarca = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.comarca_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setComarca_id(rs.getInt(1));
         u.setNome_comarca(rs.getString(2));
         u.setNome_comarca(rs.getString(3));
         u.setComarca_nome2(Integer.valueOf(rs.getInt(4)));
         u.setComarca_nome2Desc(rs.getString(5));
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
     List<Map<String, Object>> listComarcas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select c.pk_comarca id, c.nome_comarca||coalesce ( ' ('||(select c2.nome_comarca \t\t\t\t\t\t\tfrom app_hist.comarca c2 \t\t\t\t\t\t\twhere c2.pk_comarca = c.comarca_nome2)||')','') nome,  c.nome_comarca,  coalesce ( (select c3.nome_comarca \t\t\t    from app_hist.comarca c3 \t\t\t   where c3.pk_comarca = c.comarca_nome2),'') comarca_nome2  from app_hist.comarca c  where lower(c.nome_comarca) like '%'||lower(?)||'%'  or (select lower(c2.nome_comarca) \t       from app_hist.comarca c2 \t      where c2.pk_comarca = c.comarca_nome2) like '%'||lower(?)||'%'  order by c.nome_comarca ";

       ps = c.prepareStatement(q, 1);
       ps.setString(1, getNome_comarca());
       ps.setString(2, getNome_comarca());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listComarcas.add(Cap);
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
     return listComarcas;
   }

   public Integer countComa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numTer  from app_hist.comarca ";

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
       String q = "INSERT INTO app_hist.comarca (nome_comarca, comarca_nome2) VALUES(?, ?)";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_comarca);
       ps.setObject(2, this.comarca_nome2);

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
       String q = "UPDATE app_hist.comarca SET  nome_comarca = ?, comarca_nome2 = ? where pk_comarca = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_comarca);
       ps.setObject(2, this.comarca_nome2);
       ps.setObject(3, Integer.valueOf(this.comarca_id));

       ps.executeUpdate();

       updatedId = this.comarca_id;
       c.commit();
     }
     catch (SQLException ex) {
       c.rollback();
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
       String q = "DELETE FROM app_hist.comarca  WHERE pk_comarca = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.comarca_id));

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