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

 public class Tema
 {
   private int tema_id;
   private String tema;
   private Integer agregador;
   private String agregadorDesc;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allTemas;

   public int getTema_id() {
     return this.tema_id;
   }

   public void setTema_id(int tema_id) {
     this.tema_id = tema_id;
   }

   public String getTema() {
     return this.tema;
   }

   public void setTema(String tema) {
     this.tema = tema;
   }

   public Integer getAgregador() {
     return this.agregador;
   }

   public void setAgregador(Integer agregador) {
     this.agregador = agregador;
   }

   public String getAgregadorDesc() {
     return this.agregadorDesc;
   }

   public void setAgregadorDesc(String agregadorDesc) {
     this.agregadorDesc = agregadorDesc;
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

   public List<Map<String, Object>> getAllTemas() {
     return this.allTemas;
   }

   public void setAllTemas(List<Map<String, Object>> allTemas) {
     this.allTemas = allTemas;
   }

   public Tema allRegistros() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRegistros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select t.pk_tema, t.tema, t.agregador,  coalesce((select a.designacao from app_hist.tema_agregador a where a.pk_tema_agregador = t.agregador),'') agregadorDesc  from app_hist.tema t  ORDER BY t.tema             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listRegistros.add(Cap);
       } 
       setAllTemas(listRegistros);

       String qCount = "select count(*)  from app_hist.tema ";

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

   public Tema registro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Tema u = new Tema();
     try {
       c = Utils.getConnectionStock();
       String q = " select t.pk_tema, t.tema, t.agregador,  coalesce((select a.designacao from app_hist.tema_agregador a where a.pk_tema_agregador = t.agregador),'') agregadorDesc  from app_hist.tema t  where t.pk_tema = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.tema_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setTema_id(rs.getInt(1));
         u.setTema(rs.getString(2));
         u.setAgregador(Integer.valueOf(rs.getInt(3)));
         u.setAgregadorDesc(rs.getString(4));
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
       String q = "select t.pk_tema, t.tema, t.agregador,  coalesce((select a.designacao from app_hist.tema_agregador a where a.pk_tema_agregador = t.agregador),'') agregadorDesc  from app_hist.tema t  where lower(t.tema) like '%'||lower(?)||'%'    or coalesce((select lower(a.designacao) from app_hist.tema_agregador a where a.pk_tema_agregador = t.agregador),'') like '%'||lower(?)||'%'  order by t.tema ";

       ps = c.prepareStatement(q);
       ps.setString(1, getTema());
       ps.setString(2, getTema());
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

   public Tema consultaTema() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listTemas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select t.pk_tema, t.tema, t.agregador,        coalesce((select a.designacao from app_hist.tema_agregador a where a.pk_tema_agregador = t.agregador),'') agregadorDesc,        (select count(*) from app_hist.relac_tema_provocacao rtp           where rtp.tema = t.pk_tema  ) provNum,        (select count(*) from app_hist.relac_tema_mandado rtm           where rtm.tema = t.pk_tema  ) manNum,        (select count(*) from app_hist.relac_tema_consulta rtc           where rtc.tema = t.pk_tema  ) conNum,        (select count(*) from app_hist.relac_tema_ultramar rtu           where rtu.tema = t.pk_tema  ) ultraNum,        (select count(*) from app_hist.relac_tema_resposta rtr           where rtr.tema = t.pk_tema  ) resNum                    from app_hist.tema t    ORDER BY t.pk_tema             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listTemas.add(Cap);
       } 
       setAllTemas(listTemas);

       String qCount = "select count(*)  from app_hist.tema ";

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
       String q = " INSERT INTO app_hist.tema   ( tema, agregador)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.tema);
       ps.setObject(2, this.agregador);
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
       String q = "UPDATE app_hist.tema SET  tema = ?,  agregador = ?  where pk_tema = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.tema);
       ps.setObject(2, this.agregador);
       ps.setObject(3, Integer.valueOf(this.tema_id));
       ps.executeUpdate();

       updatedId = this.tema_id;
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
     return "" + getTema_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.tema  WHERE pk_tema = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.tema_id));

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
     return "" + this.tema_id;
   }
 }