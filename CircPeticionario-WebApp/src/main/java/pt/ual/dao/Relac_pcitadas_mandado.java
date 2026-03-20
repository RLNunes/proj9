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

 public class Relac_pcitadas_mandado
 {
   private int mandado;
   private int p_citadas;
   private String nomePesq;

   public int getMandado() {
     return this.mandado;
   }

   public void setMandado(int mandado) {
     this.mandado = mandado;
   }

   public int getP_citadas() {
     return this.p_citadas;
   }

   public void setP_citadas(int p_citadas) {
     this.p_citadas = p_citadas;
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

   public List<Map<String, Object>> allMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pes.pk_pessoa id, pes.nome,pes.idade,   (select eco.designacao    from app_hist.marcador_economico_ocupacao eco   where eco.pk_economico_ocupacao = pes.marcador_economico_ocupacao) marcador_economico_ocupacao     from  app_hist.pessoa pes, app_hist.relac_pcitadas_mandado rpm  where lower(pes.nome) like '%'|| lower(?) || '%'    and pes.pk_pessoa = rpm.p_citadas     and rpm.mandado  =  ? ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getMandado());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listMandado.add(Cap);
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
     return listMandado;
   }

   public List<Map<String, Object>> allPcitadas() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPcitadas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select mandado, p_citadas  from app_hist.relac_pcitadas_mandado  where p_citadas = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getP_citadas());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listPcitadas.add(Cap);
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
     return listPcitadas;
   }

   public List<Map<String, Object>> notInMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPCitadas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "  select pes.pk_pessoa id, pes.nome|| coalesce(' (' || pes.idade || ') ','') nome      from  app_hist.pessoa pes  where (lower(pes.nome) like '%'|| lower(?) || '%'           or pes.idade::varchar(255) like '%'|| lower(?) || '%'           or ( select lower(eco.designacao) \t                from app_hist.marcador_economico_ocupacao eco \t               where eco.pk_economico_ocupacao = pes.marcador_economico_ocupacao) like '%'|| lower(?) || '%' )    and pes.pk_pessoa not in (select rpm.p_citadas                                           from app_hist.relac_pcitadas_mandado rpm                                          where rpm.mandado  = ? ) ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setInt(4, getMandado());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listPCitadas.add(Cap);
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
     return listPCitadas;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_pcitadas_mandado   (mandado, p_citadas)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado));
       ps.setObject(2, Integer.valueOf(this.p_citadas));
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

   public String updateMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_pcitadas_mandado SET  mandado = ?  where p_citadas = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado));
       ps.setObject(2, Integer.valueOf(this.p_citadas));
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
     return "O ID: " + getP_citadas() + " foi atualizado com sucesso.";
   }

   public String updatePcitadas() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_pcitadas_mandado SET  p_citadas = ?  where mandado = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.p_citadas));
       ps.setObject(2, Integer.valueOf(this.mandado));
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
     return "O ID: " + getMandado() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_pcitadas_mandado  WHERE p_citadas = ? and mandado = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.p_citadas));
       ps.setObject(2, Integer.valueOf(this.mandado));

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