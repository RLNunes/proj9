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

 public class Relac_oficio_pessoa
 {
   private int oficio_titulo;
   private int pessoa;
   private String nomePesq;

   public int getOficio_titulo() {
     return this.oficio_titulo;
   }

   public void setOficio_titulo(int oficio_titulo) {
     this.oficio_titulo = oficio_titulo;
   }

   public int getPessoa() {
     return this.pessoa;
   }

   public void setPessoa(int pessoa) {
     this.pessoa = pessoa;
   }

   public String getNomePesq() {
     return this.nomePesq;
   }

   public void setNomePesq(String nomePesq) {
     if (nomePesq.equals("all")) {
       this.nomePesq = "%";
     } else {
       this.nomePesq = nomePesq;
     } 
   }

   public List<Map<String, Object>> allOficioTitulo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listOficioTitulo = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select oficio_titulo, pessoa  from app_hist.relac_oficio_pessoa  where oficio_titulo = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getOficio_titulo());
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

   public List<Map<String, Object>> allRequerente() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ot.pk_oficio_titulo id, ot.designacao ||coalesce ((select '('||oa.designacao ||')'                                       from app_hist.oficio_agregador oa                                       where oa.pk_oficio_agregador = ot.oficio_agregador ),'') designacao  from app_hist.relac_oficio_pessoa rop, app_hist.oficio_titulo ot  where ot.pk_oficio_titulo = rop.oficio_titulo    and rop.pessoa  = ?    and lower( ot.designacao ||        coalesce ((select '('||oa.designacao ||')'                      from app_hist.oficio_agregador oa                     where oa.pk_oficio_agregador = ot.oficio_agregador ),'')) like '%'||lower(?)||'%' ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getPessoa());
       ps.setString(2, getNomePesq());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listRequerente.add(Cap);
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
     return listRequerente;
   }

   public List<Map<String, Object>> notInPessoa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ot.pk_oficio_titulo id, ot.designacao ||coalesce ((select '('||oa.designacao ||')'                                       from app_hist.oficio_agregador oa                                       where oa.pk_oficio_agregador = ot.oficio_agregador ),'') designacao  from app_hist.oficio_titulo ot  where ot.pk_oficio_titulo not in (select rop.oficio_titulo from app_hist.relac_oficio_pessoa rop where rop.pessoa = ?)    and lower( ot.designacao ||        coalesce ((select '('||oa.designacao ||')'                      from app_hist.oficio_agregador oa                     where oa.pk_oficio_agregador = ot.oficio_agregador ),'')) like '%'||lower(?)||'%' ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getPessoa());
       ps.setString(2, getNomePesq());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listRequerente.add(Cap);
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
     return listRequerente;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_oficio_pessoa   (  oficio_titulo, pessoa)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.oficio_titulo));
       ps.setObject(2, Integer.valueOf(this.pessoa));
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
     return "Inserido com sucesso.";
   }

   public String updateOficioTitulo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_oficio_pessoa SET  oficio_titulo = ?  where pessoa = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.oficio_titulo));
       ps.setObject(2, Integer.valueOf(this.pessoa));
       ps.executeUpdate();

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
     return "O ID: " + getPessoa() + " foi atualizado com sucesso.";
   }

   public String updateRequerente() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_oficio_pessoa SET  pessoa = ?  where oficio_titulo = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.pessoa));
       ps.setObject(2, Integer.valueOf(this.oficio_titulo));
       ps.executeUpdate();

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
     return "O ID: " + getOficio_titulo() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_oficio_pessoa  WHERE oficio_titulo = ? and pessoa = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.oficio_titulo));
       ps.setObject(2, Integer.valueOf(this.pessoa));

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
     return "Eliminado com sucesso.";
   }
 }