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

 public class Relac_provocacao_resposta
 {
   private int provocacao;
   private int resposta;
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

   public int getResposta() {
     return this.resposta;
   }

   public void setResposta(int resposta) {
     this.resposta = resposta;
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
       String q = " select r.pk_resposta id, r.resumo, to_char(r.data_resposta, 'dd/mm/yyyy') data_resposta,       r.nova_ordem_nao_cumprimento, r.impressos, r.ano_resposta,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro= r.registro),' ') registro,        coalesce ((select rd.referencia ||' ('||rd.numero ||')'                     from app_hist.referencia_documental rd                    where rd.pk_ref_documento  = r.referencia_documental),' ') referencia_documental,        coalesce ((select td.nome                     from app_hist.tipologia_diplomatica td                    where td.pk_tipologia = r.tipologia_diplomatica ),' ') tipologia_diplomatica   from app_hist.resposta r, app_hist.relac_provocacao_resposta rpr  where r.pk_resposta = rpr.resposta     and rpr.provocacao  = ?    and (lower(r.resumo) like '%'||lower(?)||'%'      or  r.ano_resposta ::varchar(255) like '%'|| lower(?) || '%'      or to_char(r.data_resposta ,'dd/mm/yyy') like '%'|| lower(?) || '%'      or lower(r.nova_ordem_nao_cumprimento) like '%'|| lower(?) || '%'      or lower(r.impressos) like '%'|| lower(?) || '%'      or (select reg.descricao            from app_hist.registro reg           where reg.pk_registro= r.registro) like '%'|| lower(?) || '%'      or (select rd.referencia ||' ('||rd.numero ||')'            from app_hist.referencia_documental rd           where rd.pk_ref_documento  = r.referencia_documental) like '%'|| lower(?) || '%'      or (select td.nome            from app_hist.tipologia_diplomatica td           where td.pk_tipologia = r.tipologia_diplomatica ) like '%'|| lower(?) || '%') ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getProvocacao());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       ps.setString(8, getNomePesq());
       ps.setString(9, getNomePesq());
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

   public List<Map<String, Object>> allResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select prov.pk_provocacao id, prov.resumo, prov.\"data\", prov.ano, prov.registro, prov.destinatario_autoridade_tratamento, provResp.resposta  from app_hist.relac_provocacao_resposta provResp, app_hist.provocacao prov where provResp.provocacao = prov.pk_provocacao    and provResp.resposta = ? ";

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

   public List<Map<String, Object>> notInResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select prov.pk_provocacao id, prov.resumo, to_char(prov.data,'dd/mm/yyyy') data , prov.ano,        prov.registro, prov.destinatario_autoridade_tratamento   from app_hist.provocacao prov  where (lower(prov.resumo) like '%'|| lower(?) || '%'         or prov.ano::varchar(255) like '%'|| lower(?) || '%')    and prov.pk_provocacao not in ( select provResp.provocacao                                       from app_hist.relac_provocacao_resposta provResp                                     where provResp.resposta = ?)";

       if (this.capitaniaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rcre.provocacao                              from app_hist.relac_capitania_provocacao rcre                             where rcre.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rtr.provocacao                              from app_hist.relac_termo_provocacao rtr                             where rtr.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rfr.provocacao                              from app_hist.relac_freguesia_provocacao rfr                             where rfr.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rcr.provocacao                              from app_hist.relac_comarca_provocacao rcr                             where rcr.comarca  = ?) ";
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
       ps.setInt(3, getResposta());
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

   public List<Map<String, Object>> notInProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select r.pk_resposta id, r.resumo, to_char(r.data_resposta, 'dd/mm/yyyy') data_resposta,       r.nova_ordem_nao_cumprimento, r.impressos, r.ano_resposta,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro= r.registro),' ') registro,        coalesce ((select rd.referencia ||' ('||rd.numero ||')'                     from app_hist.referencia_documental rd                    where rd.pk_ref_documento  = r.referencia_documental),' ') referencia_documental,        coalesce ((select td.nome                     from app_hist.tipologia_diplomatica td                    where td.pk_tipologia = r.tipologia_diplomatica ),' ') tipologia_diplomatica   from app_hist.resposta r  where r.pk_resposta not in (select rpr.resposta                                 from app_hist.relac_provocacao_resposta rpr                                where rpr.provocacao  = ?)    and (lower(r.resumo) like '%'||lower(?)||'%'      or  r.ano_resposta ::varchar(255) like '%'|| lower(?) || '%'      or to_char(r.data_resposta ,'dd/mm/yyy') like '%'|| lower(?) || '%'      or lower(r.nova_ordem_nao_cumprimento) like '%'|| lower(?) || '%'      or lower(r.impressos) like '%'|| lower(?) || '%'      or (select reg.descricao            from app_hist.registro reg           where reg.pk_registro= r.registro) like '%'|| lower(?) || '%'      or (select rd.referencia ||' ('||rd.numero ||')'            from app_hist.referencia_documental rd           where rd.pk_ref_documento  = r.referencia_documental) like '%'|| lower(?) || '%'      or (select td.nome            from app_hist.tipologia_diplomatica td           where td.pk_tipologia = r.tipologia_diplomatica ) like '%'|| lower(?) || '%') ";

       if (this.capitaniaSearch != null) {
         q = q + " and r.pk_resposta in ( select rcre.resposta                              from app_hist.relac_capitania_resposta rcre                             where rcre.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and r.pk_resposta in ( select rtr.resposta                              from app_hist.relac_termo_resposta rtr                             where rtr.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and r.pk_resposta in ( select rfr.resposta                              from app_hist.relac_freguesia_resposta rfr                             where rfr.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and r.pk_resposta in ( select rcr.resposta                              from app_hist.relac_comarca_resposta rcr                             where rcr.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(r.data_resposta,to_date('01/01/'||r.ano_resposta,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(r.data_resposta,to_date('01/01/'||r.ano_resposta,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setInt(1, getProvocacao());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       ps.setString(8, getNomePesq());
       ps.setString(9, getNomePesq());
       int index = 10;
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
         listProvocacao.add(Cap);
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
     return listProvocacao;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_provocacao_resposta   (provocacao, resposta)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.resposta));
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
       String q = "UPDATE app_hist.relac_provocacao_resposta SET  provocacao = ?  where resposta = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
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

   public String updateResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_provocacao_resposta SET  resposta = ?  where provocacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.resposta));
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
       String q = "DELETE FROM app_hist.relac_provocacao_resposta  WHERE provocacao = ? and resposta = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
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