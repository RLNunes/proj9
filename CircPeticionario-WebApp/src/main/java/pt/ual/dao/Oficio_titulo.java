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

 public class Oficio_titulo
 {
   private int oficio_titulo_id;
   private String designacao;
   private Integer oficio_agregador;
   private String agregadorDesc;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allOficioTitulo;

   public int getOficio_titulo_id() {
     return this.oficio_titulo_id;
   }

   public void setOficio_titulo_id(int oficio_titulo_id) {
     this.oficio_titulo_id = oficio_titulo_id;
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

   public Integer getOficio_agregador() {
     return this.oficio_agregador;
   }

   public void setOficio_agregador(Integer oficio_agregador) {
     this.oficio_agregador = oficio_agregador;
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

   public List<Map<String, Object>> getAllOficioTitulo() {
     return this.allOficioTitulo;
   }

   public void setAllOficioTitulo(List<Map<String, Object>> allOficioTitulo) {
     this.allOficioTitulo = allOficioTitulo;
   }

   public Oficio_titulo allOficioTitulo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listOficioTitulo = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select ot.pk_oficio_titulo, ot.designacao,        coalesce((select oa.designacao                    from app_hist.oficio_agregador oa                   where pk_oficio_agregador = ot.oficio_agregador),'') oficio_agregador   from app_hist.oficio_titulo ot  ORDER BY ot.designacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listOficioTitulo.add(Cap);
       } 
       setAllOficioTitulo(listOficioTitulo);
       rs = ps.executeQuery();

       String qCount = "select count(*)  from app_hist.oficio_titulo ";

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

   public Oficio_titulo oficio_titulo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Oficio_titulo u = new Oficio_titulo();
     try {
       c = Utils.getConnectionStock();
       String q = "select ot.pk_oficio_titulo, ot.designacao, ot.oficio_agregador,  coalesce((select oa.designacao                    from app_hist.oficio_agregador oa                   where oa.pk_oficio_agregador = ot.oficio_agregador),'') agregador  from app_hist.oficio_titulo ot where ot.pk_oficio_titulo = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.oficio_titulo_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setOficio_titulo_id(rs.getInt(1));
         u.setDesignacao(rs.getString(2));
         u.setOficio_agregador(Integer.valueOf(rs.getInt(3)));
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
     List<Map<String, Object>> listOficioTitulo = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select ot.pk_oficio_titulo, ot.designacao,        coalesce((select oa.designacao                    from app_hist.oficio_agregador oa                   where pk_oficio_agregador = ot.oficio_agregador),'') oficio_agregador,        ot.designacao|| coalesce((select '('||oa.designacao ||')'                                     from app_hist.oficio_agregador oa                                    where pk_oficio_agregador = ot.oficio_agregador),'') descricao  from app_hist.oficio_titulo ot  where lower(ot.designacao) like '%'||lower(?)||'%'    or (select lower(oa.designacao)          from app_hist.oficio_agregador oa         where pk_oficio_agregador = ot.oficio_agregador) like '%'||lower(?)||'%'  order by ot.designacao ";

       ps = c.prepareStatement(q);
       ps.setString(1, getDesignacao());
       ps.setString(2, getDesignacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listOficioTitulo.add(Cap);
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
     return listOficioTitulo;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.oficio_titulo   (designacao, oficio_agregador)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
       ps.setObject(2, this.oficio_agregador);
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
       String q = "UPDATE app_hist.oficio_titulo SET  designacao = ?,  oficio_agregador = ?  where pk_oficio_titulo = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
       ps.setObject(2, this.oficio_agregador);
       ps.setObject(3, Integer.valueOf(this.oficio_titulo_id));
       ps.executeUpdate();

       updatedId = this.oficio_titulo_id;
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
     return "" + getOficio_titulo_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.oficio_titulo  WHERE pk_oficio_titulo = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.oficio_titulo_id));

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
     return "" + this.oficio_titulo_id;
   }
 }