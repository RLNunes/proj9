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

 public class Pessoas_citadas
 {
   private int pessoas_citadas_id;
   private String nome;

   public int getPessoas_citadas_id() {
     return this.pessoas_citadas_id;
   }

   public void setPessoas_citadas_id(int pessoas_citadas_id) {
     this.pessoas_citadas_id = pessoas_citadas_id;
   }

   public String getNome() {
     return this.nome;
   }

   public void setNome(String nome) {
     this.nome = nome;
   }

   public List<Map<String, Object>> allPessoasCitadas() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPessoasCitadas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_pessoas_citadas, nome from app_hist.pessoas_citadas ";

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
         listPessoasCitadas.add(Cap);
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
     return listPessoasCitadas;
   }

   public Pessoas_citadas pessoas_citadas() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Pessoas_citadas u = new Pessoas_citadas();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_pessoas_citadas, nome from app_hist.pessoas_citadas  where pk_pessoas_citadas = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.pessoas_citadas_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setPessoas_citadas_id(rs.getInt(1));
         u.setNome(rs.getString(2));
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
     List<Map<String, Object>> listPessoasCitadas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_pessoas_citadas, nome from app_hist.pessoas_citadas where lower(nome) like '%'||lower(?)||'%' ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNome());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listPessoasCitadas.add(Cap);
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
     return listPessoasCitadas;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.pessoas_citadas   (nome)   VALUES( ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome);
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
       String q = "UPDATE app_hist.pessoas_citadas SET  nome = ? where pk_pessoas_citadas = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.nome);
       ps.setObject(2, Integer.valueOf(this.pessoas_citadas_id));
       ps.executeUpdate();

       updatedId = this.pessoas_citadas_id;
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
     return "O ID: " + getPessoas_citadas_id() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.pessoas_citadas  WHERE pk_pessoas_citadas = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.pessoas_citadas_id));

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
     return "O ID: " + this.pessoas_citadas_id + " foi eliminado com sucesso.";
   }
 }