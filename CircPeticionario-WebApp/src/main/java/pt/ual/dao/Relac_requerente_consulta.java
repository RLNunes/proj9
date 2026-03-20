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

 public class Relac_requerente_consulta
 {
   private int requerente;
   private int consulta;
   private String nomePesq;

   public int getRequerente() {
     return this.requerente;
   }

   public void setRequerente(int requerente) {
     this.requerente = requerente;
   }

   public int getConsulta() {
     return this.consulta;
   }

   public void setConsulta(int consulta) {
     this.consulta = consulta;
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

   public List<Map<String, Object>> allRequerente() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select requerente, consulta  from app_hist.relac_requerente_consulta  where requerente = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getRequerente());
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

   public List<Map<String, Object>> allConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsulta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select \tpes.pk_pessoa id, \tpes.nome, pes.idade  from     app_hist.relac_requerente_consulta reqCons, \t   app_hist.pessoa pes  where reqCons.requerente = pes.pk_pessoa    and reqCons.consulta = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getConsulta());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listConsulta.add(Cap);
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
     return listConsulta;
   }

   public List<Map<String, Object>> notInConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select \tpes.pk_pessoa id, \tpes.nome || coalesce(' (' || pes.idade || ')',' ') nome from \tapp_hist.pessoa pes  where (lower(pes.nome) like '%'|| lower(?) || '%'   or pes.idade::varchar(255) like '%'|| lower(?) || '%'         or ( select lower(eco.designacao) \t           from app_hist.marcador_economico_ocupacao eco \t          where eco.pk_economico_ocupacao = pes.marcador_economico_ocupacao) like '%'|| lower(?) || '%' )  and pes.pk_pessoa not in (select reqCons.requerente \t\t\t\t\tfrom app_hist.relac_requerente_consulta reqCons \t\t\t\t       where reqCons.consulta =  ? )";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setInt(4, getConsulta());
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
       String q = " INSERT INTO app_hist.relac_requerente_consulta   (requerente, consulta)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.requerente));
       ps.setObject(2, Integer.valueOf(this.consulta));
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

   public String updateRequerente() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_requerente_consulta SET  requerente = ?  where consulta = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.requerente));
       ps.setObject(2, Integer.valueOf(this.consulta));
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
     return "O ID: " + getConsulta() + " foi atualizado com sucesso.";
   }

   public String updateConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_requerente_consulta SET  consulta = ?  where requerente = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.consulta));
       ps.setObject(2, Integer.valueOf(this.requerente));
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
     return "O ID: " + getRequerente() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_requerente_consulta  WHERE requerente = ? and consulta = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.requerente));
       ps.setObject(2, Integer.valueOf(this.consulta));

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