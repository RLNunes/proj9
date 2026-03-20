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

 public class Relac_pchave_provocacao
 {
   private int provocacao;
   private int palavra_chave;
   private String nomePesq;

   public int getProvocacao() {
     return this.provocacao;
   }

   public void setProvocacao(int provocacao) {
     this.provocacao = provocacao;
   }

   public int getPalavra_chave() {
     return this.palavra_chave;
   }

   public void setPalavra_chave(int palavra_chave) {
     this.palavra_chave = palavra_chave;
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

   public List<Map<String, Object>> allProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pchave.pk_palavra_chave id, pchave.palavra_chave   from app_hist.palavra_chave pchave, app_hist.relac_pchave_provocacao pchaveProv  where  lower(pchave.palavra_chave) like '%'|| lower(?) || '%'    and pchave.pk_palavra_chave = pchaveProv.palavra_chave    and pchaveProv.provocacao  = ?  order by 2";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getProvocacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listProvocacao.add(Cap);
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
     return listProvocacao;
   }

   public List<Map<String, Object>> allPalavraChave() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPalavraChave = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select provocacao, palavra_chave  from app_hist.relac_pchave_provocacao  where palavra_chave = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getPalavra_chave());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listPalavraChave.add(Cap);
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
     return listPalavraChave;
   }

   public List<Map<String, Object>> notInProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pchave.pk_palavra_chave id, pchave.palavra_chave   from app_hist.palavra_chave pchave  where  lower(pchave.palavra_chave) like '%'|| lower(?) || '%'    and pchave.pk_palavra_chave not in (select pchaveProv.palavra_chave                                          from app_hist.relac_pchave_provocacao pchaveProv \t\t\t\t\t                    where  pchaveProv.provocacao  = ?)  order by 2";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getProvocacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listProvocacao.add(Cap);
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
     return listProvocacao;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_pchave_provocacao   (provocacao, palavra_chave)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.palavra_chave));
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

   public String updateProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_pchave_provocacao SET  provocacao = ?  where palavra_chave = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.palavra_chave));
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
     return "O ID: " + getPalavra_chave() + " foi atualizado com sucesso.";
   }

   public String updatePalavraChave() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_pchave_provocacao SET  palavra_chave = ?  where provocacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.palavra_chave));
       ps.setObject(2, Integer.valueOf(this.provocacao));
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
     return "O ID: " + getProvocacao() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_pchave_provocacao  WHERE palavra_chave = ? and provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.palavra_chave));
       ps.setObject(2, Integer.valueOf(this.provocacao));

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