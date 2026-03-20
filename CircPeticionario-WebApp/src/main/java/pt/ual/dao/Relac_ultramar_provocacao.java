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

 public class Relac_ultramar_provocacao
 {
   private int ultramar;
   private int provocacao;
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

   public List<Map<String, Object>> allUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, to_char(p.data ,'dd/mm/yyyy') data,        p.ano,       (select rd.referencia ||'('||rd.numero||')'           from app_hist.referencia_documental rd          where rd.pk_ref_documento = p.referencia_documental) referencia_documental,        (select r.descricao           from app_hist.registro r          where r.pk_registro = p.registro) registro,        p.destinatario_autoridade_tratamento   from app_hist.relac_ultramar_provocacao rup, app_hist.provocacao p  where rup.ultramar = ?    and rup.provocacao = p.pk_provocacao ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getUltramar());
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

   public List<Map<String, Object>> notInUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, to_char(p.data ,'dd/mm/yyyy') data,        p.ano,        (select rd.referencia ||'('||rd.numero||')'           from app_hist.referencia_documental rd          where rd.pk_ref_documento = p.referencia_documental) referencia_documental,        (select r.descricao           from app_hist.registro r          where r.pk_registro = p.registro) registro,        p.destinatario_autoridade_tratamento   from app_hist.provocacao p  where p.pk_provocacao not in (select rup.provocacao                                  from app_hist.relac_ultramar_provocacao rup                                 where rup.ultramar = ?)    and ( lower(p.resumo) like '%'||lower(?)||'%'          or to_char(p.data ,'dd/mm/yyyy') like '%'||lower(?)||'%'          or p.destinatario_autoridade_tratamento like '%'||lower(?)||'%'          or (select lower(rd.referencia ||'('||rd.numero||')')           from app_hist.referencia_documental rd          where rd.pk_ref_documento = p.referencia_documental)  like '%'||lower(?)||'%') ";

       if (this.capitaniaSearch != null) {
         q = q + " and p.pk_provocacao in ( select rcul.provocacao                              from app_hist.relac_capitania_provocacao rcul                             where rcul.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and p.pk_provocacao in ( select rtu.provocacao                              from app_hist.relac_termo_provocacao rtu                             where rtu.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and p.pk_provocacao in ( select rfu.provocacao                              from app_hist.relac_freguesia_provocacao rfu                             where rfu.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and p.pk_provocacao in ( select rcu.provocacao                              from app_hist.relac_comarca_provocacao rcu                             where rcu.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(p.data,to_date('01/01/'||p.ano,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(p.data,to_date('01/01/'||p.ano,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }

       ps = c.prepareStatement(q);
       ps.setInt(1, getUltramar());
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

   public List<Map<String, Object>> allProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "  select ult.pk_ultramar id, ult.resumo, ult.registro, to_char(ult.data,'dd/mm/yyyy') data, ult.ano,        ult.referencia_documental, ult.autoridade   from app_hist.ultramar ult , app_hist.relac_ultramar_provocacao ultProv  where (lower(ult.resumo) like '%'|| lower(?) || '%'     or ult.ano::varchar(255) like '%'|| lower(?) || '%')    and ult.pk_ultramar = ultProv.ultramar    and ultProv.provocacao = ? ";

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
       String q = "  select ult.pk_ultramar id, ult.resumo, ult.registro, to_char(ult.data,'dd/mm/yyyy') data, ult.ano,        ult.referencia_documental, ult.autoridade   from app_hist.ultramar ult  where (lower(ult.resumo) like '%'|| lower(?) || '%'     or ult.ano::varchar(255) like '%'|| lower(?) || '%')    and ult.pk_ultramar not in (select ultProv.ultramar                                  from app_hist.relac_ultramar_provocacao ultProv                                 where ultProv.provocacao = ?)  ";

       if (this.capitaniaSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rcul.ultramar                              from app_hist.relac_capitania_ultramar rcul                             where rcul.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rtu.ultramar                              from app_hist.relac_termo_ultramar rtu                             where rtu.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rfu.ultramar                              from app_hist.relac_freguesia_ultramar rfu                             where rfu.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and ult.pk_ultramar in ( select rcu.ultramar                              from app_hist.relac_comarca_ultramar rcu                             where rcu.comarca  = ?) ";
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

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_ultramar_provocacao   (ultramar, provocacao)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.ultramar));
       ps.setObject(2, Integer.valueOf(this.provocacao));
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

   public String updateUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_ultramar_provocacao SET  ultramar = ?  where provocacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar));
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
       String q = "UPDATE app_hist.relac_ultramar_provocacao SET  provocacao = ?  where ultramar = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
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
       String q = "DELETE FROM app_hist.relac_ultramar_provocacao  WHERE ultramar = ? and provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar));
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