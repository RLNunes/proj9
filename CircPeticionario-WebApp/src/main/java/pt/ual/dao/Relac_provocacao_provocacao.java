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

 public class Relac_provocacao_provocacao
 {
   private int provocacao_mais_antiga;
   private int provocacao_mais_recente;
   private String nomePesq;
   private Integer capitaniaSearch;
   private Integer termoSearch;
   private Integer freguesiaSearch;
   private Integer comarcaSearch;
   private String dataDe;
   private String dataAte;

   public int getProvocacao_mais_antiga() {
     return this.provocacao_mais_antiga;
   }

   public void setProvocacao_mais_antiga(int provocacao_mais_antiga) {
     this.provocacao_mais_antiga = provocacao_mais_antiga;
   }

   public int getProvocacao_mais_recente() {
     return this.provocacao_mais_recente;
   }

   public void setProvocacao_mais_recente(int provocacao_mais_recente) {
     this.provocacao_mais_recente = provocacao_mais_recente;
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

   public List<Map<String, Object>> allProvAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvAntiga = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select prov.pk_provocacao id, prov.resumo, to_char(prov.data,'dd/mm/yyyy') data , prov.ano,        prov.registro, prov.destinatario_autoridade_tratamento   from app_hist.provocacao prov, app_hist.relac_provocacao_provocacao provProv  where (lower(prov.resumo) like '%'|| lower(?) || '%' \tor prov.ano::varchar(255) like '%'|| lower(?) || '%')    and prov.pk_provocacao = provProv.provocacao_mais_recente    and provProv.provocacao_mais_antiga =  ? ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setInt(3, getProvocacao_mais_antiga());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listProvAntiga.add(Cap);
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
     return listProvAntiga;
   }

   public List<Map<String, Object>> allProvNova() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvNova = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select provocacao_mais_antiga, provocacao_mais_recente  from app_hist.relac_provocacao_provocacao  where provocacao_mais_recente = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getProvocacao_mais_recente());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listProvNova.add(Cap);
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
     return listProvNova;
   }

   public List<Map<String, Object>> notInProvocacaoAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select prov.pk_provocacao id, prov.resumo, to_char(prov.data,'dd/mm/yyyy') data , prov.ano,        prov.registro, prov.destinatario_autoridade_tratamento   from app_hist.provocacao prov  where (lower(prov.resumo) like '%'|| lower(?) || '%' \tor prov.ano::varchar(255) like '%'|| lower(?) || '%')    and prov.pk_provocacao not in ( select provProv.provocacao_mais_recente                                       from app_hist.relac_provocacao_provocacao provProv                                     where provProv.provocacao_mais_antiga =  ?) ";

       if (this.capitaniaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rcp.provocacao                              from app_hist.relac_capitania_provocacao rcp                             where rcp.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rtp.provocacao                              from app_hist.relac_termo_provocacao rtp                             where rtp.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rfp.provocacao                              from app_hist.relac_freguesia_provocacao rfp                             where rfp.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rcpp.provocacao                              from app_hist.relac_comarca_provocacao rcpp                             where rcpp.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(prov.data,to_date('01/01/'||prov.ano,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(prov.data,to_date('01/01/'||prov.ano,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setInt(3, getProvocacao_mais_antiga());
       int index = 4;
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
       String q = " INSERT INTO app_hist.relac_provocacao_provocacao   (provocacao_mais_antiga, provocacao_mais_recente)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao_mais_antiga));
       ps.setObject(2, Integer.valueOf(this.provocacao_mais_recente));
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

   public String updateProvAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_provocacao_provocacao SET  provocacao_mais_antiga = ?  where provocacao_mais_recente = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao_mais_antiga));
       ps.setObject(2, Integer.valueOf(this.provocacao_mais_recente));
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
     return "O ID: " + getProvocacao_mais_recente() + " foi atualizado com sucesso.";
   }

   public String updateProvNova() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_provocacao_provocacao SET  provocacao_mais_recente = ?  where provocacao_mais_antiga = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao_mais_recente));
       ps.setObject(2, Integer.valueOf(this.provocacao_mais_antiga));
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
     return "O ID: " + getProvocacao_mais_antiga() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_provocacao_provocacao  WHERE provocacao_mais_antiga = ? and provocacao_mais_recente = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao_mais_antiga));
       ps.setObject(2, Integer.valueOf(this.provocacao_mais_recente));

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