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

 public class Referencia_documental
 {
   private int ref_documento_id;
   private String referencia;
   private String numero;
   private String complemento;
   private String url;
   private Integer tipo_referencia;
   private String search;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allRefDocs;

   public int getRef_documento_id() {
     return this.ref_documento_id;
   }

   public void setRef_documento_id(int ref_documento_id) {
     this.ref_documento_id = ref_documento_id;
   }

   public String getReferencia() {
     return this.referencia;
   }

   public void setReferencia(String referencia) {
     this.referencia = referencia;
   }

   public String getNumero() {
     return this.numero;
   }

   public void setNumero(String numero) {
     this.numero = numero;
   }

   public String getComplemento() {
     return this.complemento;
   }

   public void setComplemento(String complemento) {
     this.complemento = complemento;
   }

   public String getUrl() {
     return this.url;
   }

   public void setUrl(String url) {
     this.url = url;
   }

   public int getTipo_referencia() {
     return this.tipo_referencia.intValue();
   }

   public void setTipo_referencia(Integer tipo_referencia) {
     if (tipo_referencia.intValue() == 0) {
       this.tipo_referencia = Integer.valueOf(0);
     } else {
       this.tipo_referencia = tipo_referencia;
     } 
   }

   public String getSearch() {
     if ("all".equals(this.search)) {
       setSearch("%");
     }
     return this.search;
   }

   public void setSearch(String search) {
     this.search = search;
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

   public List<Map<String, Object>> getAllRefDocs() {
     return this.allRefDocs;
   }

   public void setAllRefDocs(List<Map<String, Object>> allRefDocs) {
     this.allRefDocs = allRefDocs;
   }

   public int getPageNum() {
     return this.pageNum;
   }

   public void setPageNum(int pageNum) {
     this.pageNum = pageNum;
   }

   public Referencia_documental allRefDocumental() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRefDocumental = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_ref_documento id, referencia, numero,  complemento, coalesce(url,'') url,  coalesce((select tip.tipo_referencia   from app_hist.tipo_referencia_documental tip  where tip.pk_tipo_referencia = ref.tipo_referencia), '') tipo_referencia  from app_hist.referencia_documental ref  ORDER BY ref.referencia             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listRefDocumental.add(Cap);
       } 
       setAllRefDocs(listRefDocumental);
       this.tipo_referencia = Integer.valueOf(0);

       String qCount = "select count(*)  from app_hist.referencia_documental ";

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

   public Referencia_documental refDocumental() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Referencia_documental u = new Referencia_documental();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_ref_documento, referencia, numero,  complemento, coalesce(url,'') url, coalesce(tipo_referencia,0) tipo_referencia  from app_hist.referencia_documental  where pk_ref_documento = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.ref_documento_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setRef_documento_id(rs.getInt(1));
         u.setReferencia(rs.getString(2));
         u.setNumero(rs.getString(3));
         u.setComplemento(rs.getString(4));
         u.setUrl(rs.getString(5));
         u.setTipo_referencia(Integer.valueOf(rs.getInt(6)));

       }

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
       String q = "select pk_ref_documento, referencia, numero,  complemento, coalesce(url,'') url,  (select tip.tipo_referencia   from app_hist.tipo_referencia_documental tip  where tip.pk_tipo_referencia = ref.tipo_referencia) tipo_referencia  from app_hist.referencia_documental ref  where lower(referencia) like '%'||lower(?)||'%'    or lower(numero) like '%'||lower(?)||'%'    or lower(complemento) like '%'||lower(?)||'%'    or lower(url) like '%'||lower(?)||'%'    or (select lower(tip.tipo_referencia)          from app_hist.tipo_referencia_documental tip         where tip.pk_tipo_referencia = ref.tipo_referencia) like '%'||lower(?)||'%'  order by ref.referencia ";

       ps = c.prepareStatement(q);
       ps.setString(1, getSearch());
       ps.setString(2, getSearch());
       ps.setString(3, getSearch());
       ps.setString(4, getSearch());
       ps.setString(5, getSearch());
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
       String q = " INSERT INTO app_hist.referencia_documental   (referencia, numero, complemento, url, tipo_referencia)   VALUES( ?, ?, ?, ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.referencia);
       ps.setObject(2, this.numero);
       ps.setObject(3, this.complemento);
       ps.setObject(4, this.url);
       ps.setObject(5, this.tipo_referencia);
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
       String q = "UPDATE app_hist.referencia_documental SET  referencia = ?,  numero = ?,  complemento = ?,  url = ?,  tipo_referencia = ?  where pk_ref_documento = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.referencia);
       ps.setObject(2, this.numero);
       ps.setObject(3, this.complemento);
       ps.setObject(4, this.url);
       ps.setObject(5, this.tipo_referencia);
       ps.setObject(6, Integer.valueOf(this.ref_documento_id));
       ps.executeUpdate();

       updatedId = this.ref_documento_id;
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
     return "" + getRef_documento_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.referencia_documental  WHERE pk_ref_documento = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ref_documento_id));

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
     return "" + this.ref_documento_id;
   }
 }