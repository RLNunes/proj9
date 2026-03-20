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

 public class Relac_consulta_provocacao
 {
   private int provocacao;
   private int consulta;
   private String nomePesq;
   private Integer capitaniaSearch;
   private Integer termoSearch;
   private Integer freguesiaSearch;
   private Integer comarcaSearch;
   private String dataDe;
   private String dataAte;

   public int getProvocacao() {
     return this.provocacao;
   }

   public void setProvocacao(int provocacao) {
     this.provocacao = provocacao;
   }

   public int getConsulta() {
     return this.consulta;
   }

   public void setConsulta(int consulta) {
     this.consulta = consulta;
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

   public List<Map<String, Object>> allProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select con.pk_consulta id, to_char(con.data_parecer_regio,'dd/mm/yyyy') data_parecer_regio, con.referencia_documental,        con.sumula, to_char(con.data_consulta,'dd/mm/yyyy') data_consulta,        con.ano_consulta, con.resumo, con.registro, con.parecer_regio   from app_hist.consulta con,  app_hist.relac_consulta_provocacao  conProv  where (lower(con.resumo) like '%'|| lower(?) || '%'     or con.ano_consulta::varchar(255) like '%'|| lower(?) || '%')    and con.pk_consulta = conProv.consulta    AND conProv.provocacao  = ?  ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setInt(3, getProvocacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listProvocacao.add(Cap);
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
     return listProvocacao;
   }

   public List<Map<String, Object>> allConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsulta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, to_char(p.data,'dd/mm/yyy') data,        p.ano, p.destinatario_autoridade_tratamento,        coalesce ((select r.descricao                     from app_hist.registro r                    where r.pk_registro= p.registro),' ') registro,        coalesce ((select rd.referencia ||' ('||rd.numero ||')'                     from app_hist.referencia_documental rd                    where rd.pk_ref_documento  = p.referencia_documental),' ') referencia_documental   from app_hist.relac_consulta_provocacao rcp, app_hist.provocacao p  where rcp.consulta = ?    and rcp.provocacao = p.pk_provocacao  ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getConsulta());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listConsulta.add(Cap);
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
     return listConsulta;
   }

   public List<Map<String, Object>> notInProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select con.pk_consulta id, to_char(con.data_parecer_regio,'dd/mm/yyyy') data_parecer_regio, con.referencia_documental,        con.sumula, to_char(con.data_consulta,'dd/mm/yyyy') data_consulta,        con.ano_consulta, con.resumo, con.registro, con.parecer_regio   from app_hist.consulta con  where (lower(con.resumo) like '%'|| lower(?) || '%'     or con.ano_consulta::varchar(255) like '%'|| lower(?) || '%')    and con.pk_consulta not in (select conProv.consulta \t\t\t\t\tfrom app_hist.relac_consulta_provocacao  conProv \t\t\t\t       where conProv.provocacao  = ?) ";

       if (this.capitaniaSearch != null) {
         q = q + " and con.pk_consulta in ( select rcco.consulta                              from app_hist.relac_capitania_consulta rcco                             where rcco.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and con.pk_consulta in ( select rtc.consulta                              from app_hist.relac_termo_consulta rtc                             where rtc.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and con.pk_consulta in ( select rfc.consulta                              from app_hist.relac_freguesia_consulta rfc                             where rfc.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and con.pk_consulta in ( select rcc.consulta                              from app_hist.relac_comarca_consulta rcc                             where rcc.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(con.data_consulta,to_date('01/01/'||con.ano_consulta,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(con.data_consulta,to_date('01/01/'||con.ano_consulta,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setInt(3, getProvocacao());
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

   public List<Map<String, Object>> notInConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsulta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, to_char(p.data,'dd/mm/yyy') data,        p.ano, p.destinatario_autoridade_tratamento,        coalesce ((select r.descricao                     from app_hist.registro r                    where r.pk_registro= p.registro),' ') registro,        coalesce ((select rd.referencia ||' ('||rd.numero ||')'                     from app_hist.referencia_documental rd                    where rd.pk_ref_documento  = p.referencia_documental),' ') referencia_documental   from app_hist.provocacao p  where p.pk_provocacao not in (select rcp.provocacao                                  from app_hist.relac_consulta_provocacao rcp                                 where rcp.consulta = ?)   and (lower(p.resumo) like '%'||lower(?)||'%'      or  p.ano ::varchar(255) like '%'|| lower(?) || '%'      or to_char(p.data,'dd/mm/yyy') like '%'|| lower(?) || '%'      or lower(p.destinatario_autoridade_tratamento) like '%'|| lower(?) || '%'      or (select lower(r.descricao)            from app_hist.registro r           where r.pk_registro= p.registro) like '%'|| lower(?) || '%'      or (select rd.referencia ||' ('||rd.numero ||')'            from app_hist.referencia_documental rd           where rd.pk_ref_documento  = p.referencia_documental) like '%'|| lower(?) || '%') ";

       if (this.capitaniaSearch != null) {
         q = q + " and p.pk_provocacao in ( select rcco.provocacao                              from app_hist.relac_capitania_provocacao rcco                             where rcco.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and p.pk_provocacao in ( select rtc.provocacao                              from app_hist.relac_termo_provocacao rtc                             where rtc.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and p.pk_provocacao in ( select rfc.provocacao                              from app_hist.relac_freguesia_provocacao rfc                             where rfc.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and p.pk_provocacao in ( select rcc.provocacao                              from app_hist.relac_comarca_provocacao rcc                             where rcc.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(p.data,to_date('01/01/'||p.ano,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(p.data,to_date('01/01/'||p.ano,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setInt(1, getConsulta());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       int index = 8;
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
         listConsulta.add(Cap);
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
     return listConsulta;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_consulta_provocacao   (provocacao, consulta)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.consulta));
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

   public String updateProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_consulta_provocacao SET  provocacao = ?  where consulta = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.consulta));
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
     return "O ID: " + getConsulta() + " foi atualizado com sucesso.";
   }

   public String updateConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_consulta_provocacao SET  consulta = ?  where provocacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.consulta));
       ps.setObject(2, Integer.valueOf(this.provocacao));
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
     return "O ID: " + getProvocacao() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_consulta_provocacao  WHERE provocacao = ? and consulta = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.consulta));

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