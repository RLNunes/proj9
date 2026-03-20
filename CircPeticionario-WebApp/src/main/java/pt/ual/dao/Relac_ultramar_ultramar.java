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

 public class Relac_ultramar_ultramar
 {
   private int ultramar_mais_antigo;
   private int ultramar_mais_recente;
   private String nomePesq;
   private Integer capitaniaSearch;
   private Integer termoSearch;
   private Integer freguesiaSearch;
   private Integer comarcaSearch;
   private String dataDe;
   private String dataAte;

   public int getUltramar_mais_antigo() {
     return this.ultramar_mais_antigo;
   }

   public void setUltramar_mais_antigo(int ultramar_mais_antigo) {
     this.ultramar_mais_antigo = ultramar_mais_antigo;
   }

   public int getUltramar_mais_recente() {
     return this.ultramar_mais_recente;
   }

   public void setUltramar_mais_recente(int ultramar_mais_recente) {
     this.ultramar_mais_recente = ultramar_mais_recente;
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

   public Integer getCapitaniaSearch() {
     return this.capitaniaSearch;
   }

   public void setCapitaniaSearch(Integer capitaniaSearch) {
     this.capitaniaSearch = capitaniaSearch;
   }

   public Integer getTermoSearch() {
     return this.termoSearch;
   }

   public void setTermoSearch(Integer termoSearch) {
     this.termoSearch = termoSearch;
   }

   public Integer getFreguesiaSearch() {
     return this.freguesiaSearch;
   }

   public void setFreguesiaSearch(Integer freguesiaSearch) {
     this.freguesiaSearch = freguesiaSearch;
   }

   public Integer getComarcaSearch() {
     return this.comarcaSearch;
   }

   public void setComarcaSearch(Integer comarcaSearch) {
     this.comarcaSearch = comarcaSearch;
   }

   public String getDataDe() {
     return this.dataDe;
   }

   public void setDataDe(String dataDe) {
     this.dataDe = dataDe;
   }

   public String getDataAte() {
     return this.dataAte;
   }

   public void setDataAte(String dataAte) {
     this.dataAte = dataAte;
   }

   public List<Map<String, Object>> allUltAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRespAntiga = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select u.pk_ultramar id, u.resumo, to_char(u.data ,'dd/mm/yyyy') data,          u.ano, u.autoridade     from app_hist.ultramar u, app_hist.relac_ultramar_ultramar ruu    where ruu.ultramar_mais_recente = u.pk_ultramar      and ruu.ultramar_mais_antigo = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getUltramar_mais_antigo());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listRespAntiga.add(Cap);
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
     return listRespAntiga;
   }

   public List<Map<String, Object>> allUltNova() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select u.pk_ultramar id, u.resumo, to_char(u.data ,'dd/mm/yyyy') data,          u.ano, u.autoridade     from app_hist.ultramar u, app_hist.relac_ultramar_ultramar ruu    where ruu.ultramar_mais_antigo = u.pk_ultramar      and ruu.ultramar_mais_recente = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getUltramar_mais_recente());
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

   public List<Map<String, Object>> notInUltramarNovo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select u.pk_ultramar id, u.resumo, to_char(u.data ,'dd/mm/yyyy') data,        u.ano, u.autoridade   from app_hist.ultramar u  where u.pk_ultramar not in (select ruu.ultramar_mais_antigo                                from app_hist.relac_ultramar_ultramar ruu                               where ruu.ultramar_mais_recente = ?)    and (lower(u.resumo) like '%'||lower(?)||'%'         or to_char(u.data ,'dd/mm/yyyy') like '%'||lower(?)||'%'         or u.ano::varchar(255) like '%'|| lower(?) || '%'         or lower(u.autoridade) like '%'|| lower(?) || '%')  ";

       if (this.capitaniaSearch != null) {
         q = q + " and u.pk_ultramar in ( select rcm.ultramar                              from app_hist.relac_capitania_ultramar rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and u.pk_ultramar in ( select rtm.ultramar                              from app_hist.relac_termo_ultramar rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and u.pk_ultramar in ( select rfm.ultramar                              from app_hist.relac_freguesia_ultramar rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and u.pk_ultramar in ( select rcmm.ultramar                              from app_hist.relac_comarca_ultramar rcmm                             where rcmm.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(u.data,to_date('01/01/'||u.ano,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(u.data,to_date('01/01/'||u.ano,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setInt(1, getUltramar_mais_recente());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       int index = 6;
       if (this.capitaniaSearch != null) {
         ps.setInt(index, getCapitaniaSearch().intValue());
         index++;
       } 
       if (this.termoSearch != null) {
         ps.setInt(index, getTermoSearch().intValue());
         index++;
       } 
       if (this.freguesiaSearch != null) {
         ps.setInt(index, getFreguesiaSearch().intValue());
         index++;
       } 
       if (this.comarcaSearch != null) {
         ps.setInt(index, getComarcaSearch().intValue());
         index++;
       } 
       if (!"".equals(this.dataDe)) {
         ps.setString(index, getDataDe());
         index++;
       } 
       if (!"".equals(this.dataAte)) {
         ps.setString(index, getDataAte());
       }
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

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_ultramar_ultramar   (ultramar_mais_antigo, ultramar_mais_recente)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar_mais_antigo));
       ps.setObject(2, Integer.valueOf(this.ultramar_mais_recente));
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

   public String updateUltAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_ultramar_ultramar SET  ultramar_mais_recente = ?  where ultramar_mais_antigo = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar_mais_recente));
       ps.setObject(2, Integer.valueOf(this.ultramar_mais_antigo));
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
     return "O ID: " + getUltramar_mais_antigo() + " foi atualizado com sucesso.";
   }

   public String updateUltNova() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_ultramar_ultramar SET  ultramar_mais_antigo = ?  where ultramar_mais_recente = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar_mais_antigo));
       ps.setObject(2, Integer.valueOf(this.ultramar_mais_recente));
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
     return "O ID: " + getUltramar_mais_recente() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_ultramar_ultramar  WHERE ultramar_mais_antigo = ? and ultramar_mais_recente = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar_mais_antigo));
       ps.setObject(2, Integer.valueOf(this.ultramar_mais_recente));

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