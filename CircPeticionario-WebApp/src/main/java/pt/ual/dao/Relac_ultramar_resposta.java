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

 public class Relac_ultramar_resposta
 {
   private int ultramar;
   private int resposta;
   private String nomePesq;
   private Integer capitaniaSearch;
   private Integer termoSearch;
   private Integer freguesiaSearch;
   private Integer comarcaSearch;
   private String dataDe;
   private String dataAte;

   public int getUltramar() {
     return this.ultramar;
   }

   public void setUltramar(int ultramar) {
     this.ultramar = ultramar;
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

   public List<Map<String, Object>> allUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select resp.pk_resposta id, to_char(resp.data_resposta,'dd/mm/yyyy') data_resposta,  resp.resumo, resp.nova_ordem_nao_cumprimento, resp.tipologia_diplomatica, resp.registro ,  resp.ano_resposta, resp.referencia_documental  from app_hist.resposta resp, app_hist.relac_ultramar_resposta ultRes  where (lower(resp.resumo) like '%'|| lower(?) || '%'         or lower(resp.nova_ordem_nao_cumprimento) like '%'|| lower(?) || '%'         or resp.ano_resposta ::varchar(255) like '%'|| lower(?) || '%')    and resp.pk_resposta = ultRes.resposta    and ultRes.ultramar  = ? ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setInt(4, getUltramar());
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

   public List<Map<String, Object>> allResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ult.pk_ultramar id, ult.resumo, ult.registro, ult.\"data\", ult.ano,        ult.referencia_documental, ult.autoridade, ultRes.resposta  from app_hist.relac_ultramar_resposta ultRes, app_hist.ultramar ult  where ultRes.ultramar = ult.pk_ultramar    and ultRes.resposta = ? ";

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
       String q = " select ult.pk_ultramar id, ult.resumo, ult.registro, to_char(ult.data,'dd/mm/yyyy') data, ult.ano,        ult.referencia_documental, ult.autoridade  from app_hist.ultramar ult  where (lower(ult.resumo) like '%'|| lower(?) || '%'         or ult.ano::varchar(255) like '%'|| lower(?) || '%')    and ult.pk_ultramar not in (select ultRes.ultramar  \t\t\t\t        from app_hist.relac_ultramar_resposta ultRes \t\t\t\t       where  ultRes.resposta = ? )";

       if (this.capitaniaSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rcm.ultramar                              from app_hist.relac_capitania_ultramar rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rtm.ultramar                              from app_hist.relac_termo_ultramar rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rfm.ultramar                              from app_hist.relac_freguesia_ultramar rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rcmm.ultramar                              from app_hist.relac_comarca_ultramar rcmm                             where rcmm.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(ult.data,to_date('01/01/'||ult.ano,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(ult.data,to_date('01/01/'||ult.ano,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
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

   public List<Map<String, Object>> notInUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select r.pk_resposta id, r.resumo, to_char(r.data_resposta, 'dd/mm/yyyy') data_resposta,        r.nova_ordem_nao_cumprimento, r.impressos, r.ano_resposta,         coalesce ((select reg.descricao                      from app_hist.registro reg                     where reg.pk_registro= r.registro),' ') registro,         coalesce ((select rd.referencia ||' ('||rd.numero ||')'                      from app_hist.referencia_documental rd                     where rd.pk_ref_documento  = r.referencia_documental),' ') referencia_documental,         coalesce ((select td.nome                      from app_hist.tipologia_diplomatica td                     where td.pk_tipologia = r.tipologia_diplomatica ),' ') tipologia_diplomatica    from app_hist.resposta r   where (lower(r.resumo) like '%'|| lower(?) || '%'          or lower(r.nova_ordem_nao_cumprimento) like '%'|| lower(?) || '%'          or r.ano_resposta ::varchar(255) like '%'|| lower(?) || '%')     and r.pk_resposta  not in (select ultRes.resposta \t \t\t\t        from app_hist.relac_ultramar_resposta ultRes \t\t\t\t       where  ultRes.ultramar  = ? )";

       if (this.capitaniaSearch != null) {
         q = q + " and r.pk_resposta in ( select rcm.resposta                              from app_hist.relac_capitania_resposta rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and r.pk_resposta in ( select rtm.resposta                              from app_hist.relac_termo_resposta rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and r.pk_resposta in ( select rfm.resposta                              from app_hist.relac_freguesia_resposta rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and r.pk_resposta in ( select rcmm.resposta                              from app_hist.relac_comarca_resposta rcmm                             where rcmm.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(r.data_resposta,to_date('01/01/'||r.ano_resposta,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(r.data_resposta,to_date('01/01/'||r.ano_resposta,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setInt(4, getUltramar());
       int index = 5;
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
         listResposta.add(Cap);
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
     return listResposta;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_ultramar_resposta   (ultramar, resposta)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar));
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

   public String updateUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_ultramar_resposta SET  ultramar = ?  where resposta = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar));
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
       String q = "UPDATE app_hist.relac_ultramar_resposta SET  resposta = ?  where ultramar = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.resposta));
       ps.setObject(2, Integer.valueOf(this.ultramar));
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
     return "O ID: " + getUltramar() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_ultramar_resposta  WHERE ultramar = ? and resposta = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar));
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