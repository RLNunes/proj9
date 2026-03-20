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

 public class Relac_marcador_socio_juridico_pessoa
 {
   private int marcador_socio_juridico;
   private int pessoa;
   private int ano;
   private String search;

   public int getMarcador_socio_juridico() {
     return this.marcador_socio_juridico;
   }

   public void setMarcador_socio_juridico(int marcador_socio_juridico) {
     this.marcador_socio_juridico = marcador_socio_juridico;
   }

   public int getPessoa() {
     return this.pessoa;
   }

   public void setPessoa(int pessoa) {
     this.pessoa = pessoa;
   }

   public int getAno() {
     return this.ano;
   }

   public void setAno(int ano) {
     this.ano = ano;
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

   public List<Map<String, Object>> allMarcSocJuridico() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMarcSocJuridico = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select mssjp.marcador_status_juridico, rmsjp.ano, rmsjp.pessoa   from app_hist.relac_marcador_socio_juridico_pessoa  rmsjp, app_hist.marcador_status_socio_juridico_pessoa mssjp  where rmsjp.pessoa = ?    and mssjp.pk_marcador_socio_juridico = rmsjp.marcador_socio_juridico    and ( lower(mssjp.marcador_status_juridico) like '%' || lower(?) || '%'       or lower(rmsjp.ano::varchar(4)) like '%' || lower(?) || '%' ) ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, getPessoa());
       ps.setString(2, getSearch());
       ps.setString(3, getSearch());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listMarcSocJuridico.add(Cap);
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
     return listMarcSocJuridico;
   }

   public List<Map<String, Object>> allPessoa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select mssjp.marcador_status_juridico , mssjp.pk_marcador_socio_juridico id  from app_hist.relac_marcador_socio_juridico_pessoa rm, app_hist.marcador_status_socio_juridico_pessoa mssjp  where rm.marcador_socio_juridico = mssjp.pk_marcador_socio_juridico    and rm.pessoa = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, getPessoa());
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
     List<Map<String, Object>> listPessoa = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select mssjp.marcador_status_juridico , mssjp.pk_marcador_socio_juridico id  from app_hist.marcador_status_socio_juridico_pessoa mssjp  where mssjp.pk_marcador_socio_juridico not in ( select rm.marcador_socio_juridico                                                    from app_hist.relac_marcador_socio_juridico_pessoa rm                                                   where rm.pessoa = ?)    and lower(mssjp.marcador_status_juridico) like '%' || lower(?) || '%' ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, getPessoa());
       ps.setString(2, getSearch());

       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listPessoa.add(Cap);
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
     return listPessoa;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_marcador_socio_juridico_pessoa   (marcador_socio_juridico, pessoa)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.marcador_socio_juridico));
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

   public String updateMarcSocJuridico() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_marcador_socio_juridico_pessoa SET  marcador_socio_juridico = ?  where pessoa = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.marcador_socio_juridico));
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
       String q = "UPDATE app_hist.relac_marcador_socio_juridico_pessoa SET  pessoa = ?  where marcador_socio_juridico = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.pessoa));
       ps.setObject(2, Integer.valueOf(this.marcador_socio_juridico));
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
     return "O ID: " + getMarcador_socio_juridico() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_marcador_socio_juridico_pessoa  WHERE marcador_socio_juridico = ? and pessoa = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.marcador_socio_juridico));
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