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

 public class Termo
 {
   private int termo_id;
   private String nome_termo;
   private int capitania;
   private String capitaniaDesc;
   private Integer termo_nome2;
   private String termo_nome2Desc;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allTermos;

   public int getTermo_id() {
     return this.termo_id;
   }

   public void setTermo_id(int termo_id) {
     this.termo_id = termo_id;
   }

   public String getNome_termo() {
     if (this.nome_termo.equals("all")) {
       this.nome_termo = "%";
     }
     return this.nome_termo;
   }

   public void setNome_termo(String nome_termo) {
     this.nome_termo = nome_termo;
   }

   public int getCapitania() {
     return this.capitania;
   }

   public void setCapitania(int capitania) {
     this.capitania = capitania;
   }

   public String getCapitaniaDesc() {
     return this.capitaniaDesc;
   }

   public void setCapitaniaDesc(String capitaniaDesc) {
     this.capitaniaDesc = capitaniaDesc;
   }

   public Integer getTermo2() {
     return this.termo_nome2;
   }

   public void setTermo2(Integer termo2) {
     this.termo_nome2 = termo2;
   }

   public int getTermo_nome2() {
     return this.termo_nome2.intValue();
   }

   public void setTermo_nome2(Integer termo_nome2) {
     this.termo_nome2 = termo_nome2;
   }

   public String getTermo_nome2Desc() {
     return this.termo_nome2Desc;
   }

   public void setTermo_nome2Desc(String termo_nome2Desc) {
     this.termo_nome2Desc = termo_nome2Desc;
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

   public List<Map<String, Object>> getAllTermos() {
     return this.allTermos;
   }

   public void setAllTermos(List<Map<String, Object>> allTermos) {
     this.allTermos = allTermos;
   }

   public Termo allRegistros() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRegistros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ter.pk_termo id, ter.nome_termo,        (select cap.nome_capitania from app_hist.capitania cap where cap.pk_capitania = ter.capitania) capitania,        (select ter2.nome_termo from app_hist.termo ter2 where ter2.pk_termo = ter.termo_nome2 ) termo2,       coalesce((select STRING_AGG(f.nome_freguesia,', ')           from app_hist.relac_termo_freguesia rtf, app_hist.freguesia f          where rtf.freguesia = f.pk_freguesia            and rtf.termo = ter.pk_termo),'') Freguesias,         coalesce((select STRING_AGG(c.nome_comarca ,', ')                      from app_hist.relac_termo_comarca rtc , app_hist.comarca c                     where rtc.comarca  = c.pk_comarca                       and rtc.termo  = ter.pk_termo),'') comarca   from app_hist.termo ter  ORDER BY ter.nome_termo             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listRegistros.add(Cap);
       } 
       setTermo_nome2(Integer.valueOf(0));
       setNome_termo("");
       setAllTermos(listRegistros);

       String qCount = "select count(*)  from app_hist.termo ";

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

   public List<Map<String, Object>> allReg() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRegistros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ter.pk_termo id, ter.nome_termo,        (select cap.nome_capitania from app_hist.capitania cap where cap.pk_capitania = ter.capitania) capitania,        (select ter2.nome_termo from app_hist.termo ter2 where ter2.pk_termo = ter.termo_nome2 ) termo2,       coalesce((select STRING_AGG(f.nome_freguesia,', ')           from app_hist.relac_termo_freguesia rtf, app_hist.freguesia f          where rtf.freguesia = f.pk_freguesia            and rtf.termo = ter.pk_termo),'') Freguesias,         coalesce((select STRING_AGG(c.nome_comarca ,', ')                      from app_hist.relac_termo_comarca rtc , app_hist.comarca c                     where rtc.comarca  = c.pk_comarca                       and rtc.termo  = ter.pk_termo),'') comarca   from app_hist.termo ter ";

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

   public Termo registro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Termo u = new Termo();
     try {
       c = Utils.getConnectionStock();
       String q = " select ter.pk_termo id, ter.nome_termo,    ter.capitania,        coalesce((select ter2.nome_termo from app_hist.termo ter2 where ter2.pk_termo = ter.termo_nome2 ),' ') termo2, ter.termo_nome2 ,  coalesce((select STRING_AGG(c.nome_comarca ,', ')               from app_hist.relac_termo_comarca rtc , app_hist.comarca c              where rtc.comarca  = c.pk_comarca                 and rtc.termo  = ter.pk_termo),'') comarca   from app_hist.termo ter  where ter.pk_termo = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.termo_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setTermo_id(rs.getInt(1));
         u.setNome_termo(rs.getString(2));
         u.setCapitania(rs.getInt(3));
         u.setTermo_nome2Desc(rs.getString(4));
         u.setTermo2(Integer.valueOf(rs.getInt(5)));
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
       String q = " select ter.pk_termo id, ter.nome_termo,        (select cap.nome_capitania from app_hist.capitania cap where cap.pk_capitania = ter.capitania) capitania,        (select ter2.nome_termo from app_hist.termo ter2 where ter2.pk_termo = ter.termo_nome2 ) termo2,       coalesce((select STRING_AGG(f.nome_freguesia,', ')           from app_hist.relac_termo_freguesia rtf, app_hist.freguesia f          where rtf.freguesia = f.pk_freguesia            and rtf.termo = ter.pk_termo),'') freguesias ,         coalesce((select STRING_AGG(c.nome_comarca ,', ')                      from app_hist.relac_termo_comarca rtc , app_hist.comarca c                     where rtc.comarca  = c.pk_comarca                       and rtc.termo  = ter.pk_termo),'') comarca   from app_hist.termo ter  where lower(nome_termo) like '%'||lower(?)||'%'  or (select lower(cap.nome_capitania) from app_hist.capitania cap where cap.pk_capitania = ter.capitania) like '%'||lower(?)||'%'  or (select lower(ter2.nome_termo) from app_hist.termo ter2 where ter2.pk_termo = ter.termo_nome2 ) like '%'||lower(?)||'%'  or lower((select STRING_AGG(f.nome_freguesia,', ')           from app_hist.relac_termo_freguesia rtf, app_hist.freguesia f          where rtf.freguesia = f.pk_freguesia            and rtf.termo = ter.pk_termo)) like '%' || lower(?) || '%'  or lower((select STRING_AGG(c.nome_comarca ,', ')               from app_hist.relac_termo_comarca rtc , app_hist.comarca c              where rtc.comarca  = c.pk_comarca                 and rtc.termo  = ter.pk_termo)) like '%' || lower(?) || '%'  order by ter.nome_termo";

       ps = c.prepareStatement(q);
       ps.setString(1, getNome_termo());
       ps.setString(2, getNome_termo());
       ps.setString(3, getNome_termo());
       ps.setString(4, getNome_termo());
       ps.setString(5, getNome_termo());
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

   public Integer countTermo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numTer  from app_hist.termo ";

       ps = c.prepareStatement(q);
       rs = ps.executeQuery();

       while (rs.next()) {
         u = Integer.valueOf(rs.getInt(1));
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

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.termo  (nome_termo, capitania, termo_nome2)  VALUES( ?, ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_termo);
       ps.setObject(2, Integer.valueOf(this.capitania));
       ps.setObject(3, this.termo_nome2);
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
     return " " + toRet;
   }

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.termo SET  nome_termo = ? , capitania = ?, termo_nome2 = ?  where pk_termo = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome_termo);
       ps.setObject(2, Integer.valueOf(this.capitania));
       ps.setObject(3, this.termo_nome2);
       ps.setObject(4, Integer.valueOf(this.termo_id));
       ps.executeUpdate();

       updatedId = this.termo_id;
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
     return "" + getTermo_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "with rtc as (       delete from app_hist.relac_termo_comarca rtc           where rtc.termo  = ?),     rtf as (     delete from app_hist.relac_termo_freguesia rtf           where rtf.termo = ? ) delete from app_hist.termo t     where t.pk_termo = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.termo_id));
       ps.setObject(2, Integer.valueOf(this.termo_id));
       ps.setObject(3, Integer.valueOf(this.termo_id));

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
     return "" + this.termo_id;
   }
 }