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
 import java.util.Objects;
 import pt.ual.utils.Utils;

 public class Consulta
 {
   private int consulta_id;
   private String data_parecer_regio;
   private Integer referencia_documental;
   private String referencia_documentalDesc;
   private String sumula;
   private String data_consulta;
   private int ano_consulta;
   private String resumo;
   private int registro;
   private String registroDesc;
   private String folio_pagina;
   private String search;
   private String parecer_regio;
   private String anoPost;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allConsulta;
   private String termo;
   private String freguesia;
   private String comarca;
   private String capitania;
   private String filPessoa;
   private Integer filTermo;
   private Integer filPalChave;
   private Integer idPessoa;
   private Integer idTema;
   private Integer idPalChave;

   public String getSearch() {
     return this.search;
   }

   public void setSearch(String search) {
     if (Objects.equals("all", search)) {
       this.search = "%";
     } else {
       this.search = search.replace(" ", "%");
     } 
   }

   public int getConsulta_id() {
     return this.consulta_id;
   }

   public void setConsulta_id(int consulta_id) {
     this.consulta_id = consulta_id;
   }

   public String getData_parecer_regio() {
     return this.data_parecer_regio;
   }

   public void setData_parecer_regio(String data_parecer_regio) {
     this.data_parecer_regio = data_parecer_regio;
   }

   public Integer getReferencia_documental() {
     return this.referencia_documental;
   }

   public void setReferencia_documental(Integer referencia_documental) {
     this.referencia_documental = referencia_documental;
   }

   public String getSumula() {
     return this.sumula;
   }

   public void setSumula(String sumula) {
     this.sumula = sumula;
   }

   public String getData_consulta() {
     return this.data_consulta;
   }

   public void setData_consulta(String data_consulta) {
     this.data_consulta = data_consulta;
   }

   public int getAno_consulta() {
     return this.ano_consulta;
   }

   public void setAno_consulta(int ano_consulta) {
     this.ano_consulta = ano_consulta;
   }

   public String getResumo() {
     return this.resumo;
   }

   public void setResumo(String resumo) {
     this.resumo = resumo;
   }

   public int getRegistro() {
     return this.registro;
   }

   public void setRegistro(int registro) {
     this.registro = registro;
   }

   public String getParecer_regio() {
     return this.parecer_regio;
   }

   public void setParecer_regio(String parecer_regio) {
     this.parecer_regio = parecer_regio;
   }

   public String getReferencia_documentalDesc() {
     return this.referencia_documentalDesc;
   }

   public void setReferencia_documentalDesc(String referencia_documentalDesc) {
     this.referencia_documentalDesc = referencia_documentalDesc;
   }

   public String getRegistroDesc() {
     return this.registroDesc;
   }

   public void setRegistroDesc(String registroDesc) {
     this.registroDesc = registroDesc;
   }

   public String getFolio_pagina() {
     return this.folio_pagina;
   }

   public void setFolio_pagina(String folio_pagina) {
     this.folio_pagina = folio_pagina;
   }

   public String getAnoPost() {
     return this.anoPost;
   }

   public void setAnoPost(String anoPost) {
     this.anoPost = anoPost;
   }

   public int getPageNum() {
     return this.pageNum;
   }

   public void setPageNum(int pageNum) {
     this.pageNum = pageNum;
   }

   public int getRowsPage() {
     return this.rowsPage;
   }

   public void setRowsPage(int rowsPage) {
     this.rowsPage = rowsPage;
   }

   public int getRowcount() {
     return this.rowcount;
   }

   public void setRowcount(int rowcount) {
     this.rowcount = rowcount;
   }

   public List<Map<String, Object>> getAllConsulta() {
     return this.allConsulta;
   }

   public void setAllConsulta(List<Map<String, Object>> allConsulta) {
     this.allConsulta = allConsulta;
   }

   public String getTermo() {
     return this.termo;
   }

   public void setTermo(String termo) {
     this.termo = termo;
   }

   public String getFreguesia() {
     return this.freguesia;
   }

   public void setFreguesia(String freguesia) {
     this.freguesia = freguesia;
   }

   public String getComarca() {
     return this.comarca;
   }

   public void setComarca(String comarca) {
     this.comarca = comarca;
   }

   public String getCapitania() {
     return this.capitania;
   }

   public void setCapitania(String capitania) {
     this.capitania = capitania;
   }

   public String getFilPessoa() {
     return this.filPessoa;
   }

   public void setFilPessoa(String filPessoa) {
     if (Objects.equals("all", filPessoa)) {
       this.filPessoa = "";
     } else {
       this.filPessoa = filPessoa.replace(" ", "%");
     } 
   }

   public Integer getFilTermo() {
     return this.filTermo;
   }

   public void setFilTermo(Integer filTermo) {
     this.filTermo = filTermo;
   }

   public Integer getFilPalChave() {
     return this.filPalChave;
   }

   public void setFilPalChave(Integer filPalChave) {
     this.filPalChave = filPalChave;
   }

   public Integer getIdPessoa() {
     return this.idPessoa;
   }

   public void setIdPessoa(Integer idPessoa) {
     this.idPessoa = idPessoa;
   }

   public Integer getIdTema() {
     return this.idTema;
   }

   public void setIdTema(Integer idTema) {
     this.idTema = idTema;
   }

   public Integer getIdPalChave() {
     return this.idPalChave;
   }

   public void setIdPalChave(Integer idPalChave) {
     this.idPalChave = idPalChave;
   }

   public Consulta allConsultas() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsultas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,       coalesce(( select ref.referencia||'( '|| ref.complemento||')'           from app_hist.referencia_documental ref          where ref.pk_ref_documento = con.referencia_documental),'') referencia_documental,       con.sumula, coalesce(to_char(con.data_consulta,'dd/mm/yyyy'),'') data_consulta, con.ano_consulta,       con.resumo, con.parecer_regio,       coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro,        coalesce(con.ano_post,'') ano_post,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_consulta rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.consulta  = con.pk_consulta),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_consulta rrp    where pes.pk_pessoa = rrp.requerente    and rrp.consulta  = con.pk_consulta),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_consulta rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.consulta  = con.pk_consulta),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_consulta rtp    where tem.pk_tema = rtp.tema    and rtp.consulta  = con.pk_consulta),' ') tema,       coalesce(con.folio_pagina,'') folio_pagina  from app_hist.consulta con  ORDER BY con.pk_consulta             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.rowsPage);
       ps.setInt(2, this.pageNum);
       ps.setInt(3, this.rowsPage);
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listConsultas.add(Cap);
       } 
       setAllConsulta(listConsultas);

       String qCount = "select count(*)  from app_hist.consulta ";

       ps = c.prepareStatement(qCount);
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public Consulta consulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Consulta u = new Consulta();
     try {
       c = Utils.getConnectionStock();
       String q = "select con.pk_consulta id, to_char(con.data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,  con.referencia_documental,  con.sumula, coalesce(to_char(con.data_consulta,'dd/mm/yyyy'),'') data_consulta, con.ano_consulta, con.resumo, con.registro, con.parecer_regio,  coalesce(con.ano_post,'') ano_post, coalesce(con.folio_pagina,'') folio_pagina  from app_hist.consulta con where con.pk_consulta = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.consulta_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setConsulta_id(rs.getInt(1));
         u.setData_parecer_regio(rs.getString(2));
         u.setReferencia_documental(Integer.valueOf(rs.getInt(3)));
         u.setSumula(rs.getString(4));
         u.setData_consulta(rs.getString(5));
         u.setAno_consulta(rs.getInt(6));
         u.setResumo(rs.getString(7));
         u.setRegistro(rs.getInt(8));
         u.setParecer_regio(rs.getString(9));
         u.setAnoPost(rs.getString(10));
         u.setFolio_pagina(rs.getString(11));
       } 
       rs.close();
       ps.close();
     }
     catch (Exception ex) {
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
     return u;
   }

   public Consulta consultaInfo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Consulta u = new Consulta();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,  coalesce(( select ref.referencia||'( '|| ref.complemento||')'      from app_hist.referencia_documental ref     where ref.pk_ref_documento = con.referencia_documental),' ') referencia_documental, con.sumula, coalesce(to_char(con.data_consulta,'dd/mm/yyyy'),'') data_consulta, con.ano_consulta, con.resumo, ( select reg.descricao     from app_hist.registro reg   where reg.pk_registro = con.registro) registro,  con.parecer_regio,  coalesce(con.ano_post,'') ano_post, coalesce(con.folio_pagina,'') folio_pagina,  coalesce((select STRING_AGG (t.nome_termo,'<br>')     from app_hist.relac_termo_consulta rtp, app_hist.termo t     where rtp.termo = t.pk_termo and rtp.consulta = con.pk_consulta),'') termo,  coalesce((select string_agg(f.nome_freguesia,'<br>')      from app_hist.relac_freguesia_consulta rfp, app_hist.freguesia f      where rfp.freguesia = f.pk_freguesia        and rfp.consulta = con.pk_consulta),'') freguesia,   coalesce((select string_agg(c.nome_comarca ,'<br>')        from app_hist.relac_comarca_consulta rcp, app_hist.comarca c      where rcp.comarca = c.pk_comarca      and rcp.consulta = con.pk_consulta),'') comarca,   coalesce((select string_agg(c2.nome_capitania,'<br>')      from app_hist.relac_capitania_consulta rcp2, app_hist.capitania c2     where rcp2.capitania = c2.pk_capitania       and rcp2.consulta = con.pk_consulta),'') capitania  from app_hist.consulta con where con.pk_consulta = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.consulta_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setConsulta_id(rs.getInt(1));
         u.setData_parecer_regio(rs.getString(2));
         u.setReferencia_documentalDesc(rs.getString(3));
         u.setSumula(rs.getString(4));
         u.setData_consulta(rs.getString(5));
         u.setAno_consulta(rs.getInt(6));
         u.setResumo(rs.getString(7));
         u.setRegistroDesc(rs.getString(8));
         u.setParecer_regio(rs.getString(9));
         u.setAnoPost(rs.getString(10));
         u.setFolio_pagina(rs.getString(11));
         u.setTermo(rs.getString(12));
         u.setFreguesia(rs.getString(13));
         u.setComarca(rs.getString(14));
         u.setCapitania(rs.getString(15));
       } 
       rs.close();
       ps.close();
     }
     catch (Exception ex) {
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
     return u;
   }

   public List<Map<String, Object>> search() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsultas = new ArrayList<>();
     int conta = 8;
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_consulta, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio, sumula,  coalesce((select ref.referencia||'( '|| complemento||')'      from app_hist.referencia_documental ref     where ref.pk_ref_documento = con.referencia_documental),'') referencia_documental,  coalesce(to_char(data_consulta,'dd-mm-yyyy'),'') data_consulta, ano_consulta,  resumo, parecer_regio,  (select reg.descricao     from app_hist.registro reg   where reg.pk_registro = con.registro) registro,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_consulta rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.consulta  = con.pk_consulta),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_consulta rrp    where pes.pk_pessoa = rrp.requerente    and rrp.consulta  = con.pk_consulta),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_consulta rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.consulta  = con.pk_consulta),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_consulta rtp    where tem.pk_tema = rtp.tema    and rtp.consulta  = con.pk_consulta),' ') tema,  coalesce(con.ano_post,'') ano_post, coalesce(con.folio_pagina,'') folio_pagina  from app_hist.consulta con  where (to_char(data_parecer_regio,'dd/mm/yyyy') like '%'||lower(?)||'%'  or  ( select lower( ref.referencia||'( '|| complemento||')')      from app_hist.referencia_documental ref     where ref.pk_ref_documento = con.referencia_documental) like '%'||lower(?)||'%'  or  lower(sumula) like '%'||lower(?)||'%'  or  to_char(data_consulta,'dd/mm/yyyy') like '%'||lower(?)||'%'  or  ano_consulta::varchar(255) like '%'|| lower(?) || '%' or  lower(resumo) like '%'||lower(?)||'%'  or  ( select lower(reg.descricao)     from app_hist.registro reg   where reg.pk_registro = con.registro) like '%'||lower(?)||'%'  or  lower(parecer_regio) like '%'||lower(?)||'%') ";

       if (!"".equals(getFilPessoa())) {
         q = q + " and  (con.pk_consulta in (select distinct rpc.consulta                              from  app_hist.pessoa pes, app_hist.relac_pcitadas_consulta rpc                              where pes.pk_pessoa = rpc.p_citadas                                and lower(pes.nome) like '%'||lower(?)||'%')  or con.pk_consulta in (select distinct rrp.consulta                              from app_hist.pessoa pes, app_hist.relac_requerente_consulta rrp                             where pes.pk_pessoa = rrp.requerente                               and lower(pes.nome) like '%'||lower(?)||'%') ) ";
       }

       if (getFilTermo().intValue() != 0) {
         q = q + " and con.pk_consulta in (select rtp.consulta   from app_hist.relac_tema_consulta rtp    where rtp.tema = ? ) ";
       }

       if (this.filPalChave.intValue() != 0) {
         q = q + " and con.pk_consulta in (select rpp.consulta                      from app_hist.relac_pchave_consulta rpp                     where  rpp.palavra_chave = ?) ";
       }

       q = q + " ";
       ps = c.prepareStatement(q);
       ps.setString(1, getSearch());
       ps.setString(2, getSearch());
       ps.setString(3, getSearch());
       ps.setString(4, getSearch());
       ps.setString(5, getSearch());
       ps.setString(6, getSearch());
       ps.setString(7, getSearch());
       ps.setString(8, getSearch());
       if (!"".equals(getFilPessoa())) {
         conta++;
         ps.setString(conta, getFilPessoa());
         conta++;
         ps.setString(conta, getFilPessoa());
       } 
       if (getFilTermo().intValue() != 0) {
         conta++;
         ps.setInt(conta, getFilTermo().intValue());
       } 
       if (getFilPalChave().intValue() != 0) {
         conta++;
         ps.setInt(conta, getFilPalChave().intValue());
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
         listConsultas.add(Cap);
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
     return listConsultas;
   }

   public Integer countConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numProv  from app_hist.consulta ";

       ps = c.prepareStatement(q);
       rs = ps.executeQuery();

       while (rs.next()) {
         u = Integer.valueOf(rs.getInt(1));
       }
       rs.close();
       ps.close();
     }
     catch (Exception ex) {
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
     return u;
   }

   public Consulta conReqConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsulta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,        coalesce(( select ref.referencia||'( '|| ref.complemento||')'                     from app_hist.referencia_documental ref                    where ref.pk_ref_documento = con.referencia_documental),'') referencia_documental,        con.sumula, coalesce(to_char(con.data_consulta,'dd/mm/yyyy'),'') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro,         coalesce(con.ano_post,'') ano_post, coalesce(con.folio_pagina,'') folio_pagina   from app_hist.relac_requerente_consulta rrc, app_hist.consulta con  where rrc.consulta  = con.pk_consulta    and rrc.requerente  = ?    ORDER BY con.pk_consulta             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.idPessoa.intValue());
       ps.setInt(2, this.rowsPage);
       ps.setInt(3, this.pageNum);
       ps.setInt(4, this.rowsPage);
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
       setAllConsulta(listConsulta);

       String qCount = "select count(*)         from app_hist.relac_requerente_consulta rrc, app_hist.consulta con        where rrc.consulta  = con.pk_consulta          and rrc.requerente  =  ? ";

       ps = c.prepareStatement(qCount);
       ps.setInt(1, this.idPessoa.intValue());
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public Consulta conPesCitaConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsulta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,        coalesce(( select ref.referencia||'( '|| ref.complemento||')'                     from app_hist.referencia_documental ref                    where ref.pk_ref_documento = con.referencia_documental),'') referencia_documental,        con.sumula, coalesce(to_char(con.data_consulta,'dd/mm/yyyy'),'') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro,         coalesce(con.ano_post,'') ano_post, coalesce(con.folio_pagina,'') folio_pagina   from app_hist.relac_pcitadas_consulta rpc, app_hist.consulta con  where rpc.consulta  = con.pk_consulta    and rpc.p_citadas  = ?    ORDER BY con.pk_consulta             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.idPessoa.intValue());
       ps.setInt(2, this.rowsPage);
       ps.setInt(3, this.pageNum);
       ps.setInt(4, this.rowsPage);
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
       setAllConsulta(listConsulta);

       String qCount = "select count(*)         from app_hist.relac_pcitadas_consulta rpc, app_hist.consulta con  where rpc.consulta  = con.pk_consulta    and rpc.p_citadas  = ? ";

       ps = c.prepareStatement(qCount);
       ps.setInt(1, this.idPessoa.intValue());
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public Consulta conTemaConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsulta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,        coalesce(( select ref.referencia||'( '|| ref.complemento||')'                     from app_hist.referencia_documental ref                    where ref.pk_ref_documento = con.referencia_documental),'') referencia_documental,        con.sumula, coalesce(to_char(con.data_consulta,'dd/mm/yyyy'),'') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro,         coalesce(con.ano_post,'') ano_post, coalesce(con.folio_pagina,'') folio_pagina   from app_hist.relac_tema_consulta rrc, app_hist.consulta con  where rrc.consulta  = con.pk_consulta    and rrc.tema  = ?    ORDER BY con.pk_consulta             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.idTema.intValue());
       ps.setInt(2, this.rowsPage);
       ps.setInt(3, this.pageNum);
       ps.setInt(4, this.rowsPage);
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
       setAllConsulta(listConsulta);

       String qCount = "select count(*)         from app_hist.relac_tema_consulta rrc, app_hist.consulta con  where rrc.consulta  = con.pk_consulta    and rrc.tema  = ? ";

       ps = c.prepareStatement(qCount);
       ps.setInt(1, this.idTema.intValue());
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public Consulta conPalChaveConsulta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listConsulta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_consulta id, to_char(data_parecer_regio,'dd/mm/yyyy') data_parecer_regio,        coalesce(( select ref.referencia||'( '|| ref.complemento||')'                     from app_hist.referencia_documental ref                    where ref.pk_ref_documento = con.referencia_documental),'') referencia_documental,        con.sumula, coalesce(to_char(con.data_consulta,'dd/mm/yyyy'),'') data_consulta, con.ano_consulta,        con.resumo, con.parecer_regio,        coalesce ((select reg.descricao                     from app_hist.registro reg                    where reg.pk_registro = con.registro),' ') registro,         coalesce(con.ano_post,'') ano_post, coalesce(con.folio_pagina,'') folio_pagina   from app_hist.relac_pchave_consulta rrc, app_hist.consulta con   where rrc.consulta  = con.pk_consulta     and rrc.palavra_chave  = ?    ORDER BY con.pk_consulta             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, this.idPalChave.intValue());
       ps.setInt(2, this.rowsPage);
       ps.setInt(3, this.pageNum);
       ps.setInt(4, this.rowsPage);
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
       setAllConsulta(listConsulta);

       String qCount = "select count(*)         from app_hist.relac_pchave_consulta rrc, app_hist.consulta con   where rrc.consulta  = con.pk_consulta     and rrc.palavra_chave  = ? ";

       ps = c.prepareStatement(qCount);
       ps.setInt(1, this.idPalChave.intValue());
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
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
     return this;
   }

   public String create() throws Exception {
     int toRet = 0;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.consulta (data_parecer_regio, referencia_documental, sumula, data_consulta, ano_consulta,resumo, registro, parecer_regio, ano_post, folio_pagina) VALUES(  to_date( ?,'dd/mm/yyyy'), ?, ?,  to_date( ?,'dd/mm/yyyy'), ?, ?, ?, ?, ?, ?)";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.data_parecer_regio);
       ps.setObject(2, this.referencia_documental);
       ps.setObject(3, this.sumula);
       if ("".equals(this.data_consulta)) {
         ps.setObject(4, (Object)null);
       } else {
         ps.setObject(4, this.data_consulta);
       } 
       ps.setObject(5, Integer.valueOf(this.ano_consulta));
       ps.setObject(6, this.resumo);
       ps.setObject(7, Integer.valueOf(this.registro));
       ps.setObject(8, this.parecer_regio);
       ps.setObject(9, this.anoPost);
       ps.setObject(10, this.folio_pagina);

       if (ps.executeUpdate() > 0) {
         rs = ps.getGeneratedKeys();
         if (null != rs && rs.next()) {
           toRet = rs.getInt(1);
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

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.consulta SET  data_parecer_regio = to_date(?,'dd/mm/yyyy'),  referencia_documental = ?,  sumula = ?,  data_consulta = to_date(?,'dd/mm/yyyy'),  ano_consulta = ?,  resumo = ?,  registro = ?,  parecer_regio = ?,  ano_post = ?, folio_pagina = ?  where pk_consulta = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.data_parecer_regio);
       ps.setObject(2, this.referencia_documental);
       ps.setObject(3, this.sumula);
       if ("".equals(this.data_consulta)) {
         ps.setObject(4, (Object)null);
       } else {
         ps.setObject(4, this.data_consulta);
       } 
       ps.setObject(5, Integer.valueOf(this.ano_consulta));
       ps.setObject(6, this.resumo);
       ps.setObject(7, Integer.valueOf(this.registro));
       ps.setObject(8, this.parecer_regio);
       ps.setObject(9, this.anoPost);
       ps.setObject(10, this.folio_pagina);
       ps.setObject(11, Integer.valueOf(this.consulta_id));

       ps.executeUpdate();

       updatedId = this.consulta_id;
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
     return "" + this.consulta_id;
   }

   public String delete() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "with rrc as (       delete from app_hist.relac_requerente_consulta rrc           where rrc.consulta = ?),     rpc as (     delete from app_hist.relac_pcitadas_consulta rpc           where rpc.consulta = ? ),     rtc as (     delete from app_hist.relac_tema_consulta rtc           where rtc.consulta = ? ),     rpc2 as (     delete from app_hist.relac_pchave_consulta rpc2           where rpc2.consulta = ? ),     rcp as (     delete from app_hist.relac_consulta_provocacao rcp           where rcp.consulta = ? ),     rmc as (     delete from app_hist.relac_mandado_consulta rmc           where rmc.consulta = ? ),     rcc as (     delete from app_hist.relac_consulta_consulta rcc           where rcc.consulta_mais_antiga  = ?              or rcc.consulta_mais_recente = ?),     ruc as (     delete from app_hist.relac_ultramar_consulta ruc           where ruc.consulta = ? ),     rcr as (     delete from app_hist.relac_consulta_resposta rcr           where rcr.consulta = ? ), rcp2 as (     delete from app_hist.relac_capitania_consulta rcp2           where rcp2.consulta  = ? ), rcp3 as (     delete from app_hist.relac_comarca_consulta rcp3           where rcp3.consulta  = ? ), rfp as (     delete from app_hist.relac_freguesia_consulta rfp           where rfp.consulta  = ? ), rtp3 as (     delete from app_hist.relac_termo_consulta rtp3           where rtp3.consulta  = ? )  delete from app_hist.consulta c     where c.pk_consulta = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.consulta_id));
       ps.setObject(2, Integer.valueOf(this.consulta_id));
       ps.setObject(3, Integer.valueOf(this.consulta_id));
       ps.setObject(4, Integer.valueOf(this.consulta_id));
       ps.setObject(5, Integer.valueOf(this.consulta_id));
       ps.setObject(6, Integer.valueOf(this.consulta_id));
       ps.setObject(7, Integer.valueOf(this.consulta_id));
       ps.setObject(8, Integer.valueOf(this.consulta_id));
       ps.setObject(9, Integer.valueOf(this.consulta_id));
       ps.setObject(10, Integer.valueOf(this.consulta_id));
       ps.setObject(11, Integer.valueOf(this.consulta_id));
       ps.setObject(12, Integer.valueOf(this.consulta_id));
       ps.setObject(13, Integer.valueOf(this.consulta_id));
       ps.setObject(14, Integer.valueOf(this.consulta_id));
       ps.setObject(15, Integer.valueOf(this.consulta_id));

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
     return "" + this.consulta_id;
   }
 }