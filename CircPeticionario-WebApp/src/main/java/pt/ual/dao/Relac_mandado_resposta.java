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

 public class Relac_mandado_resposta
 {
   private int mandado;
   private int resposta;
   private String nomePesq;
   private Integer capitaniaSearch;
   private Integer termoSearch;
   private Integer freguesiaSearch;
   private Integer comarcaSearch;
   private String dataDe;
   private String dataAte;

   public int getMandado() {
     return this.mandado;
   }

   public void setMandado(int mandado) {
     this.mandado = mandado;
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

   public List<Map<String, Object>> allMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select res.pk_resposta id, to_char(res.data_resposta,'dd/mm/yyyy') data_resposta,        res.resumo, res.nova_ordem_nao_cumprimento, res.tipologia_diplomatica, res.registro,        res.ano_resposta ano, res.referencia_documental   from app_hist.resposta res, app_hist.relac_mandado_resposta rmr  where res.pk_resposta = rmr.resposta   and rmr.mandado = ?  ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getMandado());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listMandado.add(Cap);
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
     return listMandado;
   }

   public List<Map<String, Object>> allResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select \tman.pk_mandado id, \tto_char(man.data, 'dd/mm/yyyy') data, \tman.ano, \treg.descricao registro, \tman.referencia_documental, \tman.resumo, \tman.mandado, \tman.nome_quem_envia from \tapp_hist.mandado man full outer join app_hist.registro reg on \tman.registro = reg.pk_registro,  app_hist.relac_mandado_resposta manResp where \t(lower(man.resumo) like '%' || lower(?) || '%' \t\tor man.ano::varchar(255) like '%' || lower(?) || '%') \tand man.pk_mandado = manResp.mandado  and manResp.resposta = ? ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setInt(3, getResposta());
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
       String q = " select man.pk_mandado id, to_char(man.data,'dd/mm/yyyy') data, man.ano, reg.descricao registro,       man.referencia_documental, man.resumo, man.mandado, man.nome_quem_envia    from  app_hist.mandado man         FULL OUTER JOIN app_hist.registro reg                      on man.registro = reg.pk_registro  where (lower(man.resumo) like '%'|| lower(?) || '%'        or man.ano::varchar(255) like '%'|| lower(?) || '%')   and man.pk_mandado not in (select manResp.mandado\t\t\t\t       from app_hist.relac_mandado_resposta manResp \t\t\t\t      where manResp.resposta  =  ?)";

       if (this.capitaniaSearch != null) {
         q = q + " and man.pk_mandado in ( select rcm.mandado                              from app_hist.relac_capitania_mandado rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and man.pk_mandado in ( select rtm.mandado                              from app_hist.relac_termo_mandado rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and man.pk_mandado in ( select rfm.mandado                              from app_hist.relac_freguesia_mandado rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and man.pk_mandado in ( select rcmm.mandado                              from app_hist.relac_comarca_mandado rcmm                             where rcmm.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(man.data,to_date('01/01/'||man.ano,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(man.data,to_date('01/01/'||man.ano,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
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

   public List<Map<String, Object>> notInMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select res.pk_resposta id, to_char(res.data_resposta,'dd/mm/yyyy') data_resposta,        res.resumo, res.nova_ordem_nao_cumprimento, res.tipologia_diplomatica, res.registro ,        res.ano_resposta ano, res.referencia_documental   from app_hist.resposta res  where (to_char(res.data_resposta,'dd/mm/yyyy') like  '%'|| lower(?) || '%'   \t\tor lower(res.resumo)  like '%'|| lower(?) || '%'   \t\tor lower(res.nova_ordem_nao_cumprimento) like '%'|| lower(?) || '%' )   and res.pk_resposta not in (select rmr.resposta                                  from app_hist.relac_mandado_resposta rmr                                where rmr.mandado =  ?)";

       if (this.capitaniaSearch != null) {
         q = q + " and res.pk_resposta in ( select rcm.resposta                              from app_hist.relac_capitania_resposta rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and res.pk_resposta in ( select rtm.resposta                              from app_hist.relac_termo_resposta rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and res.pk_resposta in ( select rfm.resposta                              from app_hist.relac_freguesia_resposta rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and res.pk_resposta in ( select rcmm.resposta                              from app_hist.relac_comarca_resposta rcmm                             where rcmm.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(res.data_resposta,to_date('01/01/'||res.ano_resposta,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(res.data_resposta,to_date('01/01/'||res.ano_resposta,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setInt(4, getMandado());
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
       String q = " INSERT INTO app_hist.relac_mandado_resposta   ( mandado, resposta)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado));
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

   public String updateMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_mandado_resposta SET  mandado = ?  where resposta = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado));
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
       String q = "UPDATE app_hist.relac_mandado_resposta SET  resposta = ?  where mandado = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.resposta));
       ps.setObject(2, Integer.valueOf(this.mandado));
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
     return "O ID: " + getMandado() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_mandado_resposta  WHERE mandado = ? and resposta = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado));
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