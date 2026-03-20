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

 public class Relac_consulta_consulta
 {
   private int consulta_mais_antiga;
   private int consulta_mais_recente;
   private String nomePesq;
   private Integer capitaniaSearch;
   private Integer termoSearch;
   private Integer freguesiaSearch;
   private Integer comarcaSearch;
   private String dataDe;
   private String dataAte;

   public int getConsulta_mais_antiga() {
     return this.consulta_mais_antiga;
   }

   public void setConsulta_mais_antiga(int consulta_mais_antiga) {
     this.consulta_mais_antiga = consulta_mais_antiga;
   }

   public int getConsulta_mais_recente() {
     return this.consulta_mais_recente;
   }

   public void setConsulta_mais_recente(int consulta_mais_recente) {
     this.consulta_mais_recente = consulta_mais_recente;
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

   public List<Map<String, Object>> consultaAntigaNova() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listAntigaNova = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,       ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental) referencia_documental,        con.sumula, to_char(con.data_consulta,'dd/mm/yyyy') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro   from app_hist.consulta con, app_hist.relac_consulta_consulta rcc  where con.pk_consulta = rcc.consulta_mais_recente    and rcc.consulta_mais_antiga = ?    and (lower(con.resumo) like '%'||lower(?)||'%'     or con.ano_consulta  ::varchar(255) like '%'|| lower(?) || '%'    or to_char(con.data_consulta  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.sumula) like '%'|| lower(?) || '%'     or to_char(con.data_parecer_regio  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.parecer_regio) like '%'|| lower(?) || '%'     or (select reg.descricao           from app_hist.registro reg          where reg.pk_registro = con.registro) like '%'|| lower(?) || '%'     or (select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental) like '%'|| lower(?) || '%')  ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getConsulta_mais_antiga());
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

   public List<Map<String, Object>> consultaNovaAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listNovaAntiga = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,       ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental) referencia_documental,        con.sumula, to_char(con.data_consulta,'dd/mm/yyyy') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro   from app_hist.consulta con, app_hist.relac_consulta_consulta rcc  where con.pk_consulta = rcc.consulta_mais_antiga    and rcc.consulta_mais_recente = ?    and (lower(con.resumo) like '%'||lower(?)||'%'     or con.ano_consulta  ::varchar(255) like '%'|| lower(?) || '%'    or to_char(con.data_consulta  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.sumula) like '%'|| lower(?) || '%'     or to_char(con.data_parecer_regio  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.parecer_regio) like '%'|| lower(?) || '%'     or (select reg.descricao           from app_hist.registro reg          where reg.pk_registro = con.registro) like '%'|| lower(?) || '%'     or (select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental) like '%'|| lower(?) || '%')  ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getConsulta_mais_recente());
       ps.setString(2, getNomePesq());
       ps.setString(3, getNomePesq());
       ps.setString(4, getNomePesq());
       ps.setString(5, getNomePesq());
       ps.setString(6, getNomePesq());
       ps.setString(7, getNomePesq());
       ps.setString(8, getNomePesq());
       ps.setString(9, getNomePesq());
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

   public List<Map<String, Object>> consultaNotInAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listAntigaNova = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,       ( select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental) referencia_documental,        con.sumula, to_char(con.data_consulta,'dd/mm/yyyy') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro   from app_hist.consulta con   where con.pk_consulta not in (select rcc.consulta_mais_recente                                  from app_hist.relac_consulta_consulta rcc                                 where rcc.consulta_mais_antiga = ?)   and(lower(con.resumo) like '%'||lower(?)||'%'     or con.ano_consulta  ::varchar(255) like '%'|| lower(?) || '%'    or to_char(con.data_consulta  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.sumula) like '%'|| lower(?) || '%'     or to_char(con.data_parecer_regio  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.parecer_regio) like '%'|| lower(?) || '%'     or (select reg.descricao           from app_hist.registro reg          where reg.pk_registro = con.registro) like '%'|| lower(?) || '%'     or (select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental) like '%'|| lower(?) || '%')  ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getConsulta_mais_antiga());
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

   public List<Map<String, Object>> consultaNotInNova() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listNovaAntiga = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,       (select ref.referencia||'( '|| numero||')'          from app_hist.referencia_documental ref         where ref.pk_ref_documento = con.referencia_documental) referencia_documental,        con.sumula, to_char(con.data_consulta,'dd/mm/yyyy') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro   from app_hist.consulta con   where con.pk_consulta not in (select rcc.consulta_mais_antiga                                  from app_hist.relac_consulta_consulta rcc                                 where rcc.consulta_mais_recente = ?)   and(lower(con.resumo) like '%'||lower(?)||'%'     or con.ano_consulta  ::varchar(255) like '%'|| lower(?) || '%'    or to_char(con.data_consulta  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.sumula) like '%'|| lower(?) || '%'     or to_char(con.data_parecer_regio  ,'dd/mm/yyy') like '%'|| lower(?) || '%'     or lower(con.parecer_regio) like '%'|| lower(?) || '%'     or (select reg.descricao           from app_hist.registro reg          where reg.pk_registro = con.registro) like '%'|| lower(?) || '%'     or (select ref.referencia||'( '|| numero||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental) like '%'|| lower(?) || '%')  ";

       if (this.capitaniaSearch != null) {
         q = q + " and con.pk_consulta in ( select rcm.consulta                              from app_hist.relac_capitania_consulta rcm                             where rcm.capitania  = ?) ";
       }

       if (this.termoSearch != null) {
         q = q + " and con.pk_consulta in ( select rtm.consulta                              from app_hist.relac_termo_consulta rtm                             where rtm.termo  = ?) ";
       }

       if (this.freguesiaSearch != null) {
         q = q + " and con.pk_consulta in ( select rfm.consulta                              from app_hist.relac_freguesia_consulta rfm                             where rfm.freguesia  = ?) ";
       }

       if (this.comarcaSearch != null) {
         q = q + " and con.pk_consulta in ( select rcmm.consulta                              from app_hist.relac_comarca_consulta rcmm                             where rcmm.comarca  = ?) ";
       }

       if (!"".equals(this.dataDe)) {
         q = q + " and coalesce(con.data_consulta,to_date('01/01/'||con.ano_consulta,'dd/mm/yyyy')) >= to_date(?, 'dd/mm/yyyy') ";
       }
       if (!"".equals(this.dataAte)) {
         q = q + " and coalesce(con.data_consulta,to_date('01/01/'||con.ano_consulta,'dd/mm/yyyy')) <= to_date(?, 'dd/mm/yyyy') ";
       }
       ps = c.prepareStatement(q);
       ps.setInt(1, getConsulta_mais_recente());
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
       String q = " INSERT INTO app_hist.relac_consulta_consulta   (consulta_mais_antiga, consulta_mais_recente)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.consulta_mais_antiga));
       ps.setObject(2, Integer.valueOf(this.consulta_mais_recente));
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

   public String updateAntiga() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_consulta_consulta SET  consulta_mais_antiga = ?  where consulta_mais_recente = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.consulta_mais_antiga));
       ps.setObject(2, Integer.valueOf(this.consulta_mais_recente));
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
     return "O ID: " + getConsulta_mais_recente() + " foi atualizado com sucesso.";
   }

   public String updateNova() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_consulta_consulta SET  consulta_mais_recente = ?  where consulta_mais_antiga = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.consulta_mais_recente));
       ps.setObject(2, Integer.valueOf(this.consulta_mais_antiga));
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
     return "O ID: " + getConsulta_mais_antiga() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_consulta_consulta  WHERE consulta_mais_antiga = ? and consulta_mais_recente = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.consulta_mais_antiga));
       ps.setObject(2, Integer.valueOf(this.consulta_mais_recente));

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