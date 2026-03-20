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

 public class Relac_pchave_ultramar
 {
   private int ultramar;
   private int palavra_chave;
   private String nomePesq;

   public int getUltramar() {
     return this.ultramar;
   }

   public void setUltramar(int ultramar) {
     this.ultramar = ultramar;
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

   public List<Map<String, Object>> allUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pchave.pk_palavra_chave id, pchave.palavra_chave   from app_hist.palavra_chave pChave, app_hist.relac_pchave_ultramar rpu  where  lower(pchave.palavra_chave) like '%'|| lower(?) || '%'    and pchave.pk_palavra_chave = rpu.palavra_chave    and rpu.ultramar  = ?  order by 2";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getUltramar());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listUltramar.add(Cap);
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
     return listUltramar;
   }

   public List<Map<String, Object>> allPalavraChave() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPalavraChave = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ultramar, palavra_chave  from app_hist.relac_pchave_ultramar  where palavra_chave = ? ";

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

   public List<Map<String, Object>> notInUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPChave = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pchave.pk_palavra_chave id, pchave.palavra_chave   from app_hist.palavra_chave pChave  where  lower(pchave.palavra_chave) like '%'|| lower(?) || '%'    and pchave.pk_palavra_chave not in (select rpu.palavra_chave                                          from app_hist.relac_pchave_ultramar rpu                                         where rpu.ultramar  =?)  order by 2";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getUltramar());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listPChave.add(Cap);
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
     return listPChave;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_pchave_ultramar   (ultramar, palavra_chave)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar));
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

   public String updateUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_pchave_ultramar SET  ultramar = ?  where palavra_chave = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar));
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
       String q = "UPDATE app_hist.relac_pchave_ultramar SET  palavra_chave = ?  where ultramar = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.palavra_chave));
       ps.setObject(2, Integer.valueOf(this.ultramar));
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
     return "O ID: " + getUltramar() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_pchave_ultramar  WHERE palavra_chave = ? and ultramar = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.palavra_chave));
       ps.setObject(2, Integer.valueOf(this.ultramar));

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