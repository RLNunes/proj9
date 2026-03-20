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

 public class Relac_marcador_economico_ocupacao_pessoa
 {
   private int marcador_economico_ocupacao;
   private int pessoa;
   private int ano;
   private String search;

   public int getMarcador_economico_ocupacao() {
     return this.marcador_economico_ocupacao;
   }

   public void setMarcador_economico_ocupacao(int marcador_economico_ocupacao) {
     this.marcador_economico_ocupacao = marcador_economico_ocupacao;
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

   public List<Map<String, Object>> allMarcEcoOcupacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMarcSocJuridico = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select  meo.designacao, rmeop.ano, rmeop.pessoa   from app_hist.marcador_economico_ocupacao meo, app_hist.relac_marcador_economico_ocupacao_pessoa rmeop  where meo.pk_economico_ocupacao = rmeop.marcador_economico_ocupacao    and rmeop.pessoa = ?    and ( lower(meo.designacao) like '%' || lower(?) || '%'       or lower(rmeop.ano::varchar(4)) like '%' || lower(?) || '%' ) ";

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
       String q = " select  meo.designacao, rmeop.ano, rmeop.pessoa, meo.pk_economico_ocupacao id   from app_hist.marcador_economico_ocupacao meo, app_hist.relac_marcador_economico_ocupacao_pessoa rmeop  where meo.pk_economico_ocupacao = rmeop.marcador_economico_ocupacao    and rmeop.pessoa = ? ";

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
       String q = " select  meo.designacao, meo.pk_economico_ocupacao id   from app_hist.marcador_economico_ocupacao meo  where meo.pk_economico_ocupacao not in ( select rmeop.marcador_economico_ocupacao                                                    from app_hist.relac_marcador_economico_ocupacao_pessoa rmeop                                                   where rmeop.pessoa = ?)    and lower(meo.designacao) like '%' || lower(?) || '%'  ";

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
       String q = " INSERT INTO app_hist.relac_marcador_economico_ocupacao_pessoa   (  marcador_economico_ocupacao, pessoa)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.marcador_economico_ocupacao));
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
     return "" + toRet;
   }

   public String updateMarcEcoOcupacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_marcador_economico_ocupacao_pessoa SET  marcador_economico_ocupacao = ?  where pessoa = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.marcador_economico_ocupacao));
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

   public String updatePessoa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_marcador_economico_ocupacao_pessoa SET  pessoa = ?  where marcador_economico_ocupacao = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.pessoa));
       ps.setObject(2, Integer.valueOf(this.marcador_economico_ocupacao));
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
     return "O ID: " + getMarcador_economico_ocupacao() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_marcador_economico_ocupacao_pessoa  WHERE marcador_economico_ocupacao = ? and pessoa = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.marcador_economico_ocupacao));
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