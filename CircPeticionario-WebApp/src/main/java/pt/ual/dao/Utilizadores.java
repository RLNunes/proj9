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

 public class Utilizadores
 {
   private int user_id;
   private String nome;
   private String username;
   private String email;
   private String password;
   private char isAdmin;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allUtilizadores;

   public int getUser_id() {
     return this.user_id;
   }

   public void setUser_id(int user_id) {
     this.user_id = user_id;
   }

   public String getNome() {
     return this.nome;
   }

   public void setNome(String nome) {
     this.nome = nome;
   }

   public String getUsername() {
     return this.username;
   }

   public void setUsername(String username) {
     this.username = username;
   }

   public String getEmail() {
     return this.email;
   }

   public void setEmail(String email) {
     this.email = email;
   }

   public String getPassword() {
     return this.password;
   }

   public void setPassword(String password) {
     this.password = password;
   }

   public char getIsAdmin() {
     return this.isAdmin;
   }

   public void setIsAdmin(char isAdmin) {
     this.isAdmin = isAdmin;
   }

   public int getPageNum() {
     return this.pageNum;
   }

   public void setPageNum(int pageNum) {
     this.pageNum = pageNum;
   }

   public int getRowsPage() {
     return this.rowsPage;
   }

   public void setRowsPage(int rowsPage) {
     this.rowsPage = rowsPage;
   }

   public int getRowcount() {
     return this.rowcount;
   }

   public void setRowcount(int rowcount) {
     this.rowcount = rowcount;
   }

   public List<Map<String, Object>> getAllUtilizadores() {
     return this.allUtilizadores;
   }

   public void setAllUtilizadores(List<Map<String, Object>> allUtilizadores) {
     this.allUtilizadores = allUtilizadores;
   }

   public Utilizadores allUtilizadores() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUtilizadores = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select * from app_hist.utilizadores ORDER BY user_id             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.rowsPage);
       ps.setInt(2, this.pageNum);
       ps.setInt(3, this.rowsPage);
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listUtilizadores.add(Cap);
       } 
       setAllUtilizadores(listUtilizadores);

       String qCount = "select count(*)  from app_hist.utilizadores ";

       ps = c.prepareStatement(qCount);
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public Utilizadores utilizador() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Utilizadores u = new Utilizadores();
     try {
       c = Utils.getConnectionStock();
       String q = "select user_id, nome, username, email, password, admin from app_hist.utilizadores where user_id = ? ";
       ps = c.prepareStatement(q);
       ps.setInt(1, this.user_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setUser_id(rs.getInt(1));
         u.setNome(rs.getString(2));
         u.setUsername(rs.getString(3));
         u.setEmail(rs.getString(4));
         u.setPassword(rs.getString(5));
         u.setIsAdmin(rs.getString(6).charAt(0));
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
     List<Map<String, Object>> listCapitanias = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select * from app_hist.utilizadores where lower(username) like '%'||lower(?)||'%'";
       ps = c.prepareStatement(q);
       ps.setString(1, getUsername());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listCapitanias.add(Cap);
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
     return listCapitanias;
   }

   public Utilizadores userExist() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Utilizadores u = new Utilizadores();
     try {
       c = Utils.getConnectionStock();
       String q = "select user_id, password, admin from app_hist.utilizadores where lower(username) = lower(?) ";
       ps = c.prepareStatement(q, 1);
       ps.setString(1, this.username);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setUser_id(rs.getInt(1));
         u.setPassword(rs.getString(2));
         u.setIsAdmin(rs.getString(3).charAt(0));
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

   public int passValidate() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Utilizadores u = new Utilizadores();
     try {
       c = Utils.getConnectionStock();
       String q = "select user_id from app_hist.utilizadores where password = ? and lower(username) = lower(?) ";
       ps = c.prepareStatement(q, 1);
       ps.setString(1, this.password);
       ps.setString(2, this.username);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setUser_id(rs.getInt(1));
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
     return u.user_id;
   }

   public long create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.utilizadores (username, email, password, admin, nome) VALUES(?, ?, ?, ?, ?)";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.username);
       ps.setObject(2, this.email);
       ps.setObject(3, this.password);
       ps.setObject(4, Character.valueOf(this.isAdmin));
       ps.setObject(5, this.nome);

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
     return toRet;
   }

   public long update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.utilizadores SET  username = ?, email = ?,";

       if (!"".equals(this.password)) {
         q = q + " password = ?,";
       }
       q = q + " admin = ?,  nome = ?  where user_id = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, this.username);
       ps.setObject(2, this.email);
       int conta = 3;
       if (!"".equals(this.password)) {
         ps.setObject(conta, this.password);
         conta++;
       } 
       ps.setObject(conta, Character.valueOf(this.isAdmin));
       conta++;
       ps.setObject(conta, this.nome);
       conta++;
       ps.setObject(conta, Integer.valueOf(this.user_id));

       ps.executeUpdate();

       updatedId = this.user_id;
       c.commit();
     }
     catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
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
     return updatedId;
   }

   public long delete() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.utilizadores  WHERE user_id = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.user_id));

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
     return toRet;
   }
 }