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

 public class Secretario_conselheiro
 {
   private int secretario_conselheiro_id;
   private String designacao;
   private Integer secretario_agregador;
   private String agregadorDesc;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allSecretarioConselheiro;

   public int getSecretario_conselheiro_id() {
     return this.secretario_conselheiro_id;
   }

   public void setSecretario_conselheiro_id(int secretario_conselheiro_id) {
     this.secretario_conselheiro_id = secretario_conselheiro_id;
   }

   public Integer getSecretario_agregador() {
     return this.secretario_agregador;
   }

   public void setSecretario_agregador(Integer secretario_agregador) {
     this.secretario_agregador = secretario_agregador;
   }

   public String getDesignacao() {
     if ("all".equals(this.designacao)) {
       return "%";
     }
     return this.designacao;
   }

   public void setDesignacao(String designacao) {
     this.designacao = designacao;
   }

   public String getAgregadorDesc() {
     return this.agregadorDesc;
   }

   public void setAgregadorDesc(String agregadorDesc) {
     this.agregadorDesc = agregadorDesc;
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

   public List<Map<String, Object>> getAllSecretarioConselheiro() {
     return this.allSecretarioConselheiro;
   }

   public void setAllSecretarioConselheiro(List<Map<String, Object>> allSecretarioConselheiro) {
     this.allSecretarioConselheiro = allSecretarioConselheiro;
   }

   public Secretario_conselheiro allSecretarioConselheiro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listSecretarioConselheiro = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select sc.pk_secretario_conselheiro, sc.descricao,        coalesce((select sa.designacao                    from app_hist.secretario_agregador sa                   where sa.pk_secretario_agregador = sc.secretario_agregador),'') secretario_agregador   from app_hist.secretario_conselheiro sc  ORDER BY sc.descricao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listSecretarioConselheiro.add(Cap);
       } 
       setAllSecretarioConselheiro(listSecretarioConselheiro);
       rs = ps.executeQuery();

       String qCount = "select count(*)  from app_hist.secretario_conselheiro ";

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

   public Secretario_conselheiro secretario_conselheiro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Secretario_conselheiro u = new Secretario_conselheiro();
     try {
       c = Utils.getConnectionStock();
       String q = "select sc.pk_secretario_conselheiro, sc.descricao,        coalesce((select sa.designacao                    from app_hist.secretario_agregador sa                   where sa.pk_secretario_agregador = sc.secretario_agregador),'') agregadorDesc,  sc.secretario_agregador   from app_hist.secretario_conselheiro sc  where sc.pk_secretario_conselheiro = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.secretario_conselheiro_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setSecretario_conselheiro_id(rs.getInt(1));
         u.setDesignacao(rs.getString(2));
         u.setAgregadorDesc(rs.getString(3));
         u.setSecretario_agregador(Integer.valueOf(rs.getInt(4)));
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
     List<Map<String, Object>> listSecretarioConselheiro = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select sc.pk_secretario_conselheiro, sc.descricao,        coalesce((select sa.designacao                    from app_hist.secretario_agregador sa                   where sa.pk_secretario_agregador = sc.secretario_agregador),'') secretario_agregador,        sc.descricao|| coalesce((select '('||sa.designacao ||')'                                     from app_hist.secretario_agregador sa                                    where sa.pk_secretario_agregador = sc.secretario_agregador),'') descricao  from app_hist.secretario_conselheiro sc  where lower(sc.descricao) like '%'||lower(?)||'%'    or (select lower(sa.designacao)          from app_hist.secretario_agregador sa         where sa.pk_secretario_agregador = sc.secretario_agregador) like '%'||lower(?)||'%'  order by sc.descricao ";

       ps = c.prepareStatement(q);
       ps.setString(1, getDesignacao());
       ps.setString(2, getDesignacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listSecretarioConselheiro.add(Cap);
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
     return listSecretarioConselheiro;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.secretario_conselheiro   (descricao, secretario_agregador)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
       ps.setObject(2, this.secretario_agregador);
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

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.secretario_conselheiro SET  descricao = ?,  secretario_agregador = ?  where pk_secretario_conselheiro = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
       ps.setObject(2, this.secretario_agregador);
       ps.setObject(3, Integer.valueOf(this.secretario_conselheiro_id));
       ps.executeUpdate();

       updatedId = this.secretario_conselheiro_id;
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
     return "" + getSecretario_conselheiro_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.secretario_conselheiro  WHERE pk_secretario_conselheiro = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.secretario_conselheiro_id));

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
     return "" + this.secretario_conselheiro_id;
   }
 }