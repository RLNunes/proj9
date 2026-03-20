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

 public class Relac_mandado_provocacao
 {
   private int mandado;
   private int provocacao;
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

   public int getProvocacao() {
     return this.provocacao;
   }

   public void setProvocacao(int provocacao) {
     this.provocacao = provocacao;
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
       String q = " select prov.pk_provocacao id, prov.resumo, to_char(prov.data,'dd/mm/yyyy') data , prov.ano,         coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = prov.registro),' ') registro,                    case                       when prov.destinatario_autoridade_tratamento = 'S' then 'Secretário (Ilmo/Exmo)'                      when prov.destinatario_autoridade_tratamento = 'R' then 'Rei (Snr.)'                     end destinatario_autoridade_tratamento,        ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = prov.referencia_documental) referencia_documental   from app_hist.provocacao prov, app_hist.relac_mandado_provocacao rmp  where (lower(prov.resumo) like '%'|| lower(?) || '%' \tor prov.ano::varchar(255) like '%'|| lower(?) || '%'     or to_char(prov.data  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(prov.destinatario_autoridade_tratamento) like '%'|| lower(?) || '%'  )    and prov.pk_provocacao = rmp.provocacao    and rmp.mandado =  ? ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setInt(5, getMandado());
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

   public List<Map<String, Object>> allProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select man.pk_mandado id, to_char(man.data,'dd/mm/yyyy') data, man.ano, reg.descricao registro, \n       man.referencia_documental, man.resumo, man.mandado, man.nome_quem_envia  \n  from app_hist.mandado man        FULL OUTER JOIN app_hist.registro reg                     on man.registro = reg.pk_registro,       app_hist.relac_mandado_provocacao manProv  where (lower(man.resumo) like '%'|| lower(?) || '%'         or man.ano::varchar(255) like '%'|| lower(?) || '%')    and man.pk_mandado = manProv.mandado    and manProv.provocacao  = ? ";

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

   public List<Map<String, Object>> notInProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRequerente = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select man.pk_mandado id, to_char(man.data,'dd/mm/yyyy') data, man.ano, reg.descricao registro,        man.referencia_documental, man.resumo, man.mandado, man.nome_quem_envia   from app_hist.mandado man        FULL OUTER JOIN app_hist.registro reg                     on man.registro = reg.pk_registro  where (lower(man.resumo) like '%'|| lower(?) || '%'         or man.ano::varchar(255) like '%'|| lower(?) || '%')    and man.pk_mandado not in (select manProv.mandado \t\t\t\t       from app_hist.relac_mandado_provocacao manProv  \t\t\t\t      where manProv.provocacao  =  ?) ";

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

   public List<Map<String, Object>> notInMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select prov.pk_provocacao id, prov.resumo, to_char(prov.data,'dd/mm/yyyy') data , prov.ano,         coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = prov.registro),' ') registro,                    case                       when prov.destinatario_autoridade_tratamento = 'S' then 'Secretário (Ilmo/Exmo)'                      when prov.destinatario_autoridade_tratamento = 'R' then 'Rei (Snr.)'                     end destinatario_autoridade_tratamento,        ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = prov.referencia_documental) referencia_documental   from app_hist.provocacao prov  where (lower(prov.resumo) like '%'|| lower(?) || '%' \tor prov.ano::varchar(255) like '%'|| lower(?) || '%'     or to_char(prov.data  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(prov.destinatario_autoridade_tratamento) like '%'|| lower(?) || '%'  )    and prov.pk_provocacao not in ( select rmp.provocacao                                      from app_hist.relac_mandado_provocacao rmp                                     where rmp.mandado =  ?) ";

       if (this.capitaniaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rcm.provocacao                              from app_hist.relac_capitania_provocacao rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rtm.provocacao                              from app_hist.relac_termo_provocacao rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rfm.provocacao                              from app_hist.relac_freguesia_provocacao rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and prov.pk_provocacao in ( select rcmm.provocacao                              from app_hist.relac_comarca_provocacao rcmm                             where rcmm.comarca  = ?) ";
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
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setInt(5, getMandado());
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

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_mandado_provocacao   ( provocacao, mandado)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.mandado));
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
       String q = "UPDATE app_hist.relac_mandado_provocacao SET  mandado = ?  where provocacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado));
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

   public String updateProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_mandado_provocacao SET  provocacao = ?  where mandado = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
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
       String q = "DELETE FROM app_hist.relac_mandado_provocacao  WHERE mandado = ? and provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado));
       ps.setObject(2, Integer.valueOf(this.provocacao));

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