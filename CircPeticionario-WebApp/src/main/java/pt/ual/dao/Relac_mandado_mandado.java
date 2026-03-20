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

 public class Relac_mandado_mandado
 {
   private int mandado_mais_antigo;
   private int mandado_mais_recente;
   private String nomePesq;
   private Integer capitaniaSearch;
   private Integer termoSearch;
   private Integer freguesiaSearch;
   private Integer comarcaSearch;
   private String dataDe;
   private String dataAte;

   public int getMandado_mais_antigo() {
     return this.mandado_mais_antigo;
   }

   public void setMandado_mais_antigo(int mandado_mais_antigo) {
     this.mandado_mais_antigo = mandado_mais_antigo;
   }

   public int getMandado_mais_recente() {
     return this.mandado_mais_recente;
   }

   public void setMandado_mais_recente(int mandado_mais_recente) {
     this.mandado_mais_recente = mandado_mais_recente;
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

   public List<Map<String, Object>> mandadoAntigoNovo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listAntigaNova = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select m.pk_mandado id, to_char(m.data, 'dd/mm/yyyy') data, m.ano, m.resumo,        m.mandado, m.nome_quem_envia,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = m.registro),' ') registro,        ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) referencia_documental   from app_hist.mandado m, app_hist.relac_mandado_mandado rmm  where m.pk_mandado = rmm.mandado_mais_recente     and rmm.mandado_mais_antigo = ?    and(lower(m.resumo) like '%'|| lower(?) || '%' \tor m.ano::varchar(255) like '%'|| lower(?) || '%'     or to_char(m.data  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(m.mandado) like '%'|| lower(?) || '%'      or lower(m.nome_quem_envia) like '%'|| lower(?) || '%'     or (select lower(reg.descricao)           from app_hist.registro reg          where reg.pk_registro = m.registro) like '%'|| lower(?) || '%'     or ( select lower(ref.referencia||'( '|| numero||')')           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) like '%'|| lower(?) || '%') ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getMandado_mais_antigo());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       ps.setString(8, getNomePesq());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listAntigaNova.add(Cap);
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
     return listAntigaNova;
   }

   public List<Map<String, Object>> mandadoNovoAntigo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listNovaAntiga = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select m.pk_mandado id, to_char(m.data, 'dd/mm/yyyy') data, m.ano, m.resumo,        m.mandado, m.nome_quem_envia,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = m.registro),' ') registro,        ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) referencia_documental   from app_hist.mandado m, app_hist.relac_mandado_mandado rmm  where m.pk_mandado = rmm.mandado_mais_antigo     and rmm.mandado_mais_recente = ?    and(lower(m.resumo) like '%'|| lower(?) || '%' \tor m.ano::varchar(255) like '%'|| lower(?) || '%'     or to_char(m.data  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(m.mandado) like '%'|| lower(?) || '%'      or lower(m.nome_quem_envia) like '%'|| lower(?) || '%'     or (select lower(reg.descricao)           from app_hist.registro reg          where reg.pk_registro = m.registro) like '%'|| lower(?) || '%'     or ( select lower(ref.referencia||'( '|| numero||')')           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) like '%'|| lower(?) || '%') ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getMandado_mais_recente());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       ps.setString(8, getNomePesq());
       rs = ps.executeQuery();
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listNovaAntiga.add(Cap);
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
     return listNovaAntiga;
   }

   public List<Map<String, Object>> mandadoNotInAntigo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listAntigaNova = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select m.pk_mandado id, to_char(m.data, 'dd/mm/yyyy') data, m.ano, m.resumo,        m.mandado, m.nome_quem_envia,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = m.registro),' ') registro,        ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) referencia_documental   from app_hist.mandado m  where m.pk_mandado not in (select rmm.mandado_mais_recente                               from app_hist.relac_mandado_mandado rmm                              where rmm.mandado_mais_antigo = ?)    and(lower(m.resumo) like '%'|| lower(?) || '%' \tor m.ano::varchar(255) like '%'|| lower(?) || '%'     or to_char(m.data  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(m.mandado) like '%'|| lower(?) || '%'      or lower(m.nome_quem_envia) like '%'|| lower(?) || '%'     or (select lower(reg.descricao)           from app_hist.registro reg          where reg.pk_registro = m.registro) like '%'|| lower(?) || '%'     or ( select lower(ref.referencia||'( '|| numero||')')           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) like '%'|| lower(?) || '%') ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getMandado_mais_antigo());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       ps.setString(8, getNomePesq());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listAntigaNova.add(Cap);
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
     return listAntigaNova;
   }

   public List<Map<String, Object>> mandadoNotInNovo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listNovaAntiga = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select m.pk_mandado id, to_char(m.data, 'dd/mm/yyyy') data, m.ano, m.resumo,        m.mandado, m.nome_quem_envia,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = m.registro),' ') registro,        ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) referencia_documental   from app_hist.mandado m  where m.pk_mandado not in (select rmm.mandado_mais_antigo                               from app_hist.relac_mandado_mandado rmm                              where rmm.mandado_mais_recente = ?)    and(lower(m.resumo) like '%'|| lower(?) || '%' \tor m.ano::varchar(255) like '%'|| lower(?) || '%'     or to_char(m.data  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(m.mandado) like '%'|| lower(?) || '%'      or lower(m.nome_quem_envia) like '%'|| lower(?) || '%'     or (select lower(reg.descricao)           from app_hist.registro reg          where reg.pk_registro = m.registro) like '%'|| lower(?) || '%'     or ( select lower(ref.referencia||'( '|| numero||')')           from app_hist.referencia_documental ref          where ref.pk_ref_documento = m.referencia_documental) like '%'|| lower(?) || '%') ";

       if (this.capitaniaSearch != null) {
         q = q + " and m.pk_mandado in ( select rcm.mandado                              from app_hist.relac_capitania_mandado rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and m.pk_mandado in ( select rtm.mandado                              from app_hist.relac_termo_mandado rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and m.pk_mandado in ( select rfm.mandado                              from app_hist.relac_freguesia_mandado rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and m.pk_mandado in ( select rcmm.mandado                              from app_hist.relac_comarca_mandado rcmm                             where rcmm.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(m.data,to_date('01/01/'||m.ano,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(m.data,to_date('01/01/'||m.ano,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }
       ps = c.prepareStatement(q);
       ps.setInt(1, getMandado_mais_recente());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       ps.setString(8, getNomePesq());
       int index = 9;
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
         listNovaAntiga.add(Cap);
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
     return listNovaAntiga;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_mandado_mandado   (mandado_mais_antigo, mandado_mais_recente)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado_mais_antigo));
       ps.setObject(2, Integer.valueOf(this.mandado_mais_recente));
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

   public String updateAntigo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_mandado_mandado SET  mandado_mais_recente = ?  where mandado_mais_antigo = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado_mais_antigo));
       ps.setObject(2, Integer.valueOf(this.mandado_mais_recente));
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
     return "O ID: " + getMandado_mais_antigo() + " foi atualizado com sucesso.";
   }

   public String updateNovo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_mandado_mandado SET  mandado_mais_antigo = ?  where mandado_mais_recente = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado_mais_antigo));
       ps.setObject(2, Integer.valueOf(this.mandado_mais_recente));
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
     return "O ID: " + getMandado_mais_recente() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_mandado_mandado  WHERE mandado_mais_antigo = ? and mandado_mais_recente = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado_mais_antigo));
       ps.setObject(2, Integer.valueOf(this.mandado_mais_recente));

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