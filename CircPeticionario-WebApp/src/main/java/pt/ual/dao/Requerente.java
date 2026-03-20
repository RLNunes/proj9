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

 public class Requerente
 {
   private int requerente_id;
   private String nome_requerente;
   private int idade;
   private String marcador_economico_ocupacao;
   private int marcador_status_socio_juridico;
   private String search;

   public int getRequerente_id() {
     return this.requerente_id;
   }

   public void setRequerente_id(int requerente_id) {
     this.requerente_id = requerente_id;
   }

   public String getNome_requerente() {
     return this.nome_requerente;
   }

   public void setNome_requerente(String nome_requerente) {
     this.nome_requerente = nome_requerente;
   }

   public int getIdade() {
     return this.idade;
   }

   public void setIdade(int idade) {
     this.idade = idade;
   }

   public String getMarcador_economico_ocupacao() {
     return this.marcador_economico_ocupacao;
   }

   public void setMarcador_economico_ocupacao(String marcador_economico_ocupacao) {
     this.marcador_economico_ocupacao = marcador_economico_ocupacao;
   }

   public int getMarcador_status_socio_juridico() {
     return this.marcador_status_socio_juridico;
   }

   public void setMarcador_status_socio_juridico(int marcador_status_socio_juridico) {
     this.marcador_status_socio_juridico = marcador_status_socio_juridico;
   }

   public String getSearch() {
     return this.search;
   }

   public void setSearch(String search) {
     this.search = search;
   }

   public List<Map<String, Object>> allRegistros() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRegistros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_requerente, nome_requerente, idade, marcador_economico_ocupacao, marcador_status_socio_juridico  from app_hist.requerente ";

       ps = c.prepareStatement(q);
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listRegistros.add(Cap);
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
     return listRegistros;
   }

   public Requerente registro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Requerente u = new Requerente();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_requerente, nome_requerente, idade, marcador_economico_ocupacao, marcador_status_socio_juridico  from app_hist.requerente  where pk_requerente = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.requerente_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setRequerente_id(rs.getInt(1));
         u.setNome_requerente(rs.getString(2));
         u.setIdade(rs.getInt(3));
         u.setMarcador_economico_ocupacao(rs.getString(4));
         u.setMarcador_status_socio_juridico(rs.getInt(5));
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
     List<Map<String, Object>> listRefDocumental = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_requerente, nome_requerente, idade, marcador_economico_ocupacao, marcador_status_socio_juridico  from app_hist.requerente   where lower(nome_requerente) like '%'||lower(?)||'%'     or lower(idade) like '%'||lower(?)||'%'     or lower(marcador_economico_ocupacao) like '%'||lower(?)||'%' ";

       ps = c.prepareStatement(q);
       ps.setString(1, getSearch());
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
       String q = " INSERT INTO app_hist.requerente   (nome_requerente, idade, marcador_economico_ocupacao, marcador_status_socio_juridico)   VALUES( ?, ?, ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome_requerente);
       ps.setObject(2, Integer.valueOf(this.idade));
       ps.setObject(3, this.marcador_economico_ocupacao);
       ps.setObject(4, Integer.valueOf(this.marcador_status_socio_juridico));
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

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.requerente SET  nome_requerente = ? , idade = ? , marcador_economico_ocupacao = ? , marcador_status_socio_juridico = ?  where pk_requerente = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome_requerente);
       ps.setObject(2, Integer.valueOf(this.idade));
       ps.setObject(3, this.marcador_economico_ocupacao);
       ps.setObject(4, Integer.valueOf(this.marcador_status_socio_juridico));
       ps.setObject(5, Integer.valueOf(this.requerente_id));
       ps.executeUpdate();

       updatedId = this.requerente_id;
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
     return "O ID: " + getRequerente_id() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.requerente  WHERE pk_requerente = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.requerente_id));

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
     return "O ID: " + this.requerente_id + " foi eliminado com sucesso.";
   }
 }