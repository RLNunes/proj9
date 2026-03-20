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

 public class Relac_destinatario_resposta
 {
   private int destinatario;
   private int resposta;
   private String nomePesq;

   public int getResposta() {
     return this.resposta;
   }

   public void setResposta(int resposta) {
     this.resposta = resposta;
   }

   public int getDestinatario() {
     return this.destinatario;
   }

   public void setDestinatario(int destinatario) {
     this.destinatario = destinatario;
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

   public List<Map<String, Object>> allResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pes.pk_pessoa id, pes.nome, pes.idade   from app_hist.relac_destinatario_resposta destRep, app_hist.pessoa pes  where destRep.destinatario = pes.pk_pessoa    and destRep.resposta = ?  ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getResposta());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listResposta.add(Cap);
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
     return listResposta;
   }

   public List<Map<String, Object>> allDestinatario() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listDestinatario = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select resposta, destinatario  from app_hist.relac_destinatario_resposta  where destinatario = ?  ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getDestinatario());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listDestinatario.add(Cap);
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
     return listDestinatario;
   }

   public List<Map<String, Object>> notInResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listInstado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "  select pes.pk_pessoa id, pes.nome||coalesce(' ('||pes.idade||')',' ') nome      from  app_hist.pessoa pes  where  lower(pes.nome) like '%'|| lower(?) || '%'    and pes.pk_pessoa not in (select destRep.destinatario  \t\t\t\t      from app_hist.relac_destinatario_resposta destRep  \t\t\t\t      where destRep.resposta = ? )";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getResposta());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listInstado.add(Cap);
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
     return listInstado;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_destinatario_resposta   (resposta, destinatario)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.resposta));
       ps.setObject(2, Integer.valueOf(this.destinatario));
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

   public String updateResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_destinatario_resposta SET  resposta = ?  where destinatario = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.resposta));
       ps.setObject(2, Integer.valueOf(this.destinatario));
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
     return "O ID: " + getDestinatario() + " foi atualizado com sucesso.";
   }

   public String updateDestinatarioa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_destinatario_resposta SET  destinatario = ?  where resposta = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.destinatario));
       ps.setObject(2, Integer.valueOf(this.resposta));
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
     return "O ID: " + getResposta() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_destinatario_resposta  WHERE destinatario = ? and resposta = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.destinatario));
       ps.setObject(2, Integer.valueOf(this.resposta));

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