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

 public class Provocacao
 {
   private int provocacao_id;
   private String resumo;
   private Integer referencia_documental;
   private String referencia_documentalDesc;
   private String data;
   private int ano;
   private int registro;
   private String registroDesc;
   private String destinatario_autoridade_tratamento;
   private String search;
   private String anoPost;
   private String folio_pagina;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allProvocacao;
   private String termo;
   private String freguesia;
   private String comarca;
   private String capitania;
   private String pesCita;
   private String filPessoa;
   private Integer filTermo;
   private Integer filPalChave;
   private Integer numProv;
   private Integer idPessoa;
   private Integer idTema;
   private Integer idPalChave;

   public int getProvocacao_id() {
     return this.provocacao_id;
   }

   public void setProvocacao_id(int provocacao_id) {
     this.provocacao_id = provocacao_id;
   }

   public String getResumo() {
     return this.resumo;
   }

   public void setResumo(String resumo) {
     this.resumo = resumo;
   }

   public Integer getReferencia_documental() {
     return this.referencia_documental;
   }

   public void setReferencia_documental(Integer referencia_documental) {
     this.referencia_documental = referencia_documental;
   }

   public String getData() {
     return this.data;
   }

   public void setData(String data) {
     this.data = data;
   }

   public int getAno() {
     return this.ano;
   }

   public void setAno(int ano) {
     this.ano = ano;
   }

   public int getRegistro() {
     return this.registro;
   }

   public void setRegistro(int registro) {
     this.registro = registro;
   }

   public String getDestinatario_autoridade_tratamento() {
     return this.destinatario_autoridade_tratamento;
   }

   public void setDestinatario_autoridade_tratamento(String destinatario_autoridade_tratamento) {
     this.destinatario_autoridade_tratamento = destinatario_autoridade_tratamento;
   }

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

   public String getAnoPost() {
     return this.anoPost;
   }

   public void setAnoPost(String anoPost) {
     this.anoPost = anoPost;
   }

   public String getFolio_pagina() {
     return this.folio_pagina;
   }

   public void setFolio_pagina(String folio_pagina) {
     this.folio_pagina = folio_pagina;
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

   public List<Map<String, Object>> getAllProvocacao() {
     return this.allProvocacao;
   }

   public void setAllProvocacao(List<Map<String, Object>> allProvocacao) {
     this.allProvocacao = allProvocacao;
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

   public String getPesCita() {
     return this.pesCita;
   }

   public void setPesCita(String pesCita) {
     this.pesCita = pesCita;
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

   public Integer getNumProv() {
     return this.numProv;
   }

   public void setNumProv(Integer numProv) {
     this.numProv = numProv;
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

   public Provocacao allProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select prov.pk_provocacao, prov.resumo, \t   coalesce ((select rd.referencia ||' ('||rd.complemento||')' \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = prov.referencia_documental),' ') referencia_documental,  coalesce(to_char(prov.data,'dd/mm/yyyy'),'') data, prov.ano, reg.descricao registro,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_provocacao rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.provocacao  = prov.pk_provocacao),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_provocacao rrp    where pes.pk_pessoa = rrp.requerente    and rrp.provocacao  = prov.pk_provocacao),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_provocacao rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.provocacao  = prov.pk_provocacao),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_provocacao rtp    where tem.pk_tema = rtp.tema    and rtp.provocacao  = prov.pk_provocacao),' ') tema,  coalesce( case     when prov.destinatario_autoridade_tratamento = 'S' then 'Secretário (Ilmo/Exmo)'   when prov.destinatario_autoridade_tratamento = 'R' then 'Rei (Snr.)'end,' ') destinatario_autoridade_tratamento,  coalesce(prov.ano_post,'') ano_post, coalesce(prov.folio_pagina,'') folio_pagina  from app_hist.provocacao prov, app_hist.registro reg  where prov.registro = reg.pk_registro  ORDER BY prov.pk_provocacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.rowsPage);
       ps.setInt(2, this.pageNum);
       ps.setInt(3, this.rowsPage);
       setPesCita(null);
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

       setAllProvocacao(listProvocacao);

       String qCount = "select count(*)  from app_hist.provocacao ";

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

   public Provocacao provocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Provocacao u = new Provocacao();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_provocacao, resumo, referencia_documental,  coalesce(to_char(data,'dd/mm/yyyy'),'') data, ano, registro,  destinatario_autoridade_tratamento, coalesce(ano_post,'') ano_post, coalesce(folio_pagina,'') folio_pagina  from app_hist.provocacao  where pk_provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.provocacao_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setProvocacao_id(rs.getInt(1));
         u.setResumo(rs.getString(2));
         u.setReferencia_documental(Integer.valueOf(rs.getInt(3)));
         u.setData(rs.getString(4));
         u.setAno(rs.getInt(5));
         u.setRegistro(rs.getInt(6));
         u.setDestinatario_autoridade_tratamento(rs.getString(7));
         u.setAnoPost(rs.getString(8));
         u.setFolio_pagina(rs.getString(9));
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

   public Provocacao provocacaoInfo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Provocacao u = new Provocacao();
     try {
       c = Utils.getConnectionStock();
       String q = " select prov.pk_provocacao, prov.resumo, \t   coalesce ((select rd.referencia ||' - Nº: '||rd.numero \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = prov.referencia_documental),' ') referencia_documental,  coalesce(to_char(prov.data,'dd/mm/yyyy'),'') data, prov.ano, reg.descricao registro,  case prov.destinatario_autoridade_tratamento \t\tWHEN 'R' then 'Secretário (Ilmo/Exmo)'\t\twhen 'S' then 'Rei (Snr.)' \tend destinatario_autoridade_tratamento,  coalesce(prov.ano_post,'') ano_post, coalesce(prov.folio_pagina,'') folio_pagina,  coalesce((select STRING_AGG (t.nome_termo,'<br>')     from app_hist.relac_termo_provocacao rtp, app_hist.termo t     where rtp.termo = t.pk_termo and rtp.provocacao = prov.pk_provocacao),'') termo,  coalesce((select string_agg(f.nome_freguesia,'<br>')      from app_hist.relac_freguesia_provocacao rfp, app_hist.freguesia f      where rfp.freguesia = f.pk_freguesia        and rfp.provocacao = prov.pk_provocacao),'') freguesia,   coalesce((select string_agg(c.nome_comarca ,'<br>')        from app_hist.relac_comarca_provocacao rcp, app_hist.comarca c      where rcp.comarca = c.pk_comarca      and rcp.provocacao = prov.pk_provocacao),'') comarca,   coalesce((select string_agg(c2.nome_capitania,'<br>')      from app_hist.relac_capitania_provocacao rcp2, app_hist.capitania c2     where rcp2.capitania = c2.pk_capitania       and rcp2.provocacao = prov.pk_provocacao),'') capitania  from app_hist.provocacao prov, app_hist.registro reg  where prov.registro = reg.pk_registro  and pk_provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.provocacao_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setProvocacao_id(rs.getInt(1));
         u.setResumo(rs.getString(2));
         u.setReferencia_documentalDesc(rs.getString(3));
         u.setData(rs.getString(4));
         u.setAno(rs.getInt(5));
         u.setRegistroDesc(rs.getString(6));
         u.setDestinatario_autoridade_tratamento(rs.getString(7));
         u.setAnoPost(rs.getString(8));
         u.setFolio_pagina(rs.getString(9));
         u.setTermo(rs.getString(10));
         u.setFreguesia(rs.getString(11));
         u.setComarca(rs.getString(12));
         u.setCapitania(rs.getString(13));
       } 
       rs.close();
       ps.close();
     }
     catch (Exception ex) {
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
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     int conta = 5;
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_provocacao, resumo,  coalesce ((select rd.referencia ||' ('||rd.complemento||')' \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = prov.referencia_documental),' ') referencia_documental,  coalesce(to_char(data,'dd/mm/yyyy'),'') data, ano, reg.descricao registro, reg.pk_registro,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_provocacao rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.provocacao  = prov.pk_provocacao),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_provocacao rrp    where pes.pk_pessoa = rrp.requerente    and rrp.provocacao  = prov.pk_provocacao),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_provocacao rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.provocacao  = prov.pk_provocacao),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_provocacao rtp    where tem.pk_tema = rtp.tema    and rtp.provocacao  = prov.pk_provocacao),' ') tema,  case prov.destinatario_autoridade_tratamento \t\tWHEN 'R' then 'Secretário (Ilmo/Exmo)'\t\twhen 'S' then 'Rei (Snr.)' \tend destinatario_autoridade_tratamento,  coalesce(prov.ano_post,'') ano_post, coalesce(prov.folio_pagina,'') folio_pagina  from app_hist.provocacao prov, app_hist.registro reg  where prov.registro = reg.pk_registro and ( lower(resumo) like '%'||lower(?)||'%'    or lower(destinatario_autoridade_tratamento) like '%'||lower(?)||'%'    or (select lower(rd.referencia ||' ('||rd.complemento||')') \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = prov.referencia_documental) like '%'||lower(?)||'%'     or lower(reg.descricao)  like '%'||lower(?)||'%'     or to_char(prov.data,'dd/mm/yyyy') like '%'||lower(?)||'%' ) ";

       if (!"".equals(getFilPessoa())) {
         q = q + " and  (prov.pk_provocacao in (select distinct rpc.provocacao                              from  app_hist.pessoa pes, app_hist.relac_pcitadas_provocacao rpc                              where pes.pk_pessoa = rpc.p_citadas                                and lower(pes.nome) like '%'||lower(?)||'%')  or prov.pk_provocacao in (select distinct rrp.provocacao                              from app_hist.pessoa pes, app_hist.relac_requerente_provocacao rrp                             where pes.pk_pessoa = rrp.requerente                               and lower(pes.nome) like '%'||lower(?)||'%') ) ";
       }

       if (this.filTermo.intValue() != 0) {
         q = q + " and prov.pk_provocacao in (select rtp.provocacao   from app_hist.relac_tema_provocacao rtp    where rtp.tema = ? ) ";
       }

       if (this.filPalChave.intValue() != 0) {
         q = q + " and prov.pk_provocacao in (select rpp.provocacao                                                   from app_hist.relac_pchave_provocacao rpp                                                   where  rpp.palavra_chave = ?) ";
       }

       q = q + " ";
       ps = c.prepareStatement(q);
       ps.setString(1, getSearch());
       ps.setString(2, getSearch());
       ps.setString(3, getSearch());
       ps.setString(4, getSearch());
       ps.setString(5, getSearch());
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

   public Integer countProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numProv  from app_hist.provocacao ";

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

   public Provocacao conReqProvoca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvoca = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, reg.descricao registro, coalesce(to_char(p.data,'dd/mm/yyyy'),'') data, p.ano, coalesce ((select rd.referencia ||' ('||rd.complemento||')'              from app_hist.referencia_documental rd             where rd.pk_ref_documento = p.referencia_documental),' ') referencia_documental,             coalesce(p.folio_pagina,'') folio_pagina           from app_hist.relac_requerente_provocacao rrp, app_hist.provocacao p, app_hist.registro reg           where rrp.provocacao = p.pk_provocacao          and p.registro = reg.pk_registro          and rrp.requerente =  ?    ORDER BY p.pk_provocacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listProvoca.add(Cap);
       } 
       setAllProvocacao(listProvoca);

       String qCount = "select count(*)         from app_hist.relac_requerente_provocacao rrp, app_hist.provocacao p          where rrp.provocacao = p.pk_provocacao          and rrp.requerente =  ? ";

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

   public Provocacao conPesCitaProvoca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvoca = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, reg.descricao registro, coalesce(to_char(p.data,'dd/mm/yyyy'),'') data, p.ano, coalesce ((select rd.referencia ||' ('||rd.complemento||')'              from app_hist.referencia_documental rd             where rd.pk_ref_documento = p.referencia_documental),' ') referencia_documental,             coalesce(p.folio_pagina,'') folio_pagina           from app_hist.relac_pcitadas_provocacao rpp, app_hist.provocacao p, app_hist.registro reg           where rpp.provocacao = p.pk_provocacao          and p.registro = reg.pk_registro          and rpp.p_citadas =  ?    ORDER BY p.pk_provocacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listProvoca.add(Cap);
       } 
       setAllProvocacao(listProvoca);

       String qCount = "select count(*)         from app_hist.relac_pcitadas_provocacao rpp, app_hist.provocacao p          where rpp.provocacao = p.pk_provocacao          and rpp.p_citadas =   ? ";

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

   public Provocacao conRemProvoca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvoca = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, reg.descricao registro, coalesce(to_char(p.data,'dd/mm/yyyy'),'') data, p.ano, coalesce ((select rd.referencia ||' ('||rd.complemento||')'              from app_hist.referencia_documental rd             where rd.pk_ref_documento = p.referencia_documental),' ') referencia_documental,             coalesce(p.folio_pagina,'') folio_pagina           from app_hist.relac_remetente_provocacao rrp, app_hist.provocacao p, app_hist.registro reg           where rrp.provocacao = p.pk_provocacao          and p.registro = reg.pk_registro          and rrp.remetente = ?    ORDER BY p.pk_provocacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listProvoca.add(Cap);
       } 
       setAllProvocacao(listProvoca);

       String qCount = "select count(*)         from app_hist.relac_remetente_provocacao rrp, app_hist.provocacao p        where rrp.provocacao = p.pk_provocacao          and rrp.remetente = ? ";

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

   public Provocacao conTemaProvoca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvoca = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, reg.descricao registro, coalesce(to_char(p.data,'dd/mm/yyyy'),'') data, p.ano, coalesce ((select rd.referencia ||' ('||rd.complemento||')'              from app_hist.referencia_documental rd             where rd.pk_ref_documento = p.referencia_documental),' ') referencia_documental,             coalesce(p.folio_pagina,'') folio_pagina           from app_hist.relac_tema_provocacao rtp, app_hist.provocacao p, app_hist.registro reg           where rtp.provocacao = p.pk_provocacao          and p.registro = reg.pk_registro          and rtp.tema = ?    ORDER BY p.pk_provocacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listProvoca.add(Cap);
       } 
       setAllProvocacao(listProvoca);

       String qCount = "select count(*)         from app_hist.relac_tema_provocacao rtp, app_hist.provocacao p          where rtp.provocacao = p.pk_provocacao          and rtp.tema = ? ";

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

   public Provocacao conPalChaveProvoca() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvoca = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select p.pk_provocacao id, p.resumo, reg.descricao registro, coalesce(to_char(p.data,'dd/mm/yyyy'),'') data, p.ano, coalesce ((select rd.referencia ||' ('||rd.complemento||')'              from app_hist.referencia_documental rd             where rd.pk_ref_documento = p.referencia_documental),' ') referencia_documental,             coalesce(p.folio_pagina,'') folio_pagina           from app_hist.relac_pchave_provocacao rtp, app_hist.provocacao p, app_hist.registro reg           where rtp.provocacao = p.pk_provocacao           and p.registro = reg.pk_registro          and rtp.palavra_chave = ?    ORDER BY p.pk_provocacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listProvoca.add(Cap);
       } 
       setAllProvocacao(listProvoca);

       String qCount = "select count(*)         from app_hist.relac_pchave_provocacao rtp, app_hist.provocacao p         where rtp.provocacao = p.pk_provocacao           and rtp.palavra_chave = ? ";

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
       String q = " INSERT INTO app_hist.provocacao   (resumo, referencia_documental, data, ano,      registro, destinatario_autoridade_tratamento, ano_post, folio_pagina)   VALUES( ?, ?, to_date(?, 'dd/mm/yyyy'), ?, ?, ?, ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.resumo);
       ps.setObject(2, this.referencia_documental);
       if (this.data == "") {
         ps.setObject(3, (Object)null);
       } else {
         ps.setObject(3, this.data);
       } 
       ps.setObject(4, Integer.valueOf(this.ano));
       ps.setObject(5, Integer.valueOf(this.registro));
       ps.setObject(6, this.destinatario_autoridade_tratamento);
       ps.setObject(7, this.anoPost);
       ps.setObject(8, this.folio_pagina);
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
       String q = "UPDATE app_hist.provocacao SET  resumo = ?,  referencia_documental = ?,  data = to_date(?,'dd/mm/yyyy'),  ano = ?,  registro = ?,  destinatario_autoridade_tratamento = ?,  ano_post = ?, folio_pagina = ?  where pk_provocacao = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.resumo);
       ps.setObject(2, this.referencia_documental);
       if ("".equals(this.data)) {
         ps.setObject(3, (Object)null);
       } else {
         ps.setObject(3, this.data);
       } 
       ps.setObject(4, Integer.valueOf(this.ano));
       ps.setObject(5, Integer.valueOf(this.registro));
       ps.setObject(6, this.destinatario_autoridade_tratamento);
       ps.setObject(7, this.anoPost);
       ps.setObject(8, this.folio_pagina);
       ps.setObject(9, Integer.valueOf(this.provocacao_id));
       ps.executeUpdate();

       updatedId = this.provocacao_id;
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
     return "" + getProvocacao_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "with rrp as (       delete from app_hist.relac_requerente_provocacao rrp            where rrp.provocacao = ?),     rpp as (     delete from app_hist.relac_pcitadas_provocacao rpp           where rpp.provocacao = ? ),     rrp2 as (     delete from app_hist.relac_remetente_provocacao rrp2           where rrp2.provocacao = ? ),     rtp as (     delete from app_hist.relac_termo_provocacao rtp           where rtp.provocacao = ? ),     rtp2 as (     delete from app_hist.relac_tema_provocacao rtp2           where rtp2.provocacao = ? ),     rpp2 as (     delete from app_hist.relac_pchave_provocacao rpp2              where rpp2.provocacao = ? ) ,     rpp3 as (     delete from app_hist.relac_provocacao_provocacao rpp3              where rpp3.provocacao_mais_antiga = ?              or rpp3.provocacao_mais_recente = ? ),     rmp as (     delete from app_hist.relac_mandado_provocacao rmp           where rmp.provocacao = ? ),     rcp as (     delete from app_hist.relac_consulta_provocacao rcp           where rcp.provocacao = ? ),     rup as (     delete from app_hist.relac_ultramar_provocacao rup           where rup.provocacao = ? ),     rpr as (     delete from app_hist.relac_provocacao_resposta rpr           where rpr.provocacao = ? ), rcp2 as (     delete from app_hist.relac_capitania_provocacao rcp2           where rcp2.provocacao  = ? ), rcp3 as (     delete from app_hist.relac_comarca_provocacao rcp3           where rcp3.provocacao  = ? ), rfp as (     delete from app_hist.relac_freguesia_provocacao rfp           where rfp.provocacao  = ? ), rtp3 as (     delete from app_hist.relac_termo_provocacao rtp3           where rtp3.provocacao  = ? )  delete from app_hist.provocacao p     where p.pk_provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao_id));
       ps.setObject(2, Integer.valueOf(this.provocacao_id));
       ps.setObject(3, Integer.valueOf(this.provocacao_id));
       ps.setObject(4, Integer.valueOf(this.provocacao_id));
       ps.setObject(5, Integer.valueOf(this.provocacao_id));
       ps.setObject(6, Integer.valueOf(this.provocacao_id));
       ps.setObject(7, Integer.valueOf(this.provocacao_id));
       ps.setObject(8, Integer.valueOf(this.provocacao_id));
       ps.setObject(9, Integer.valueOf(this.provocacao_id));
       ps.setObject(10, Integer.valueOf(this.provocacao_id));
       ps.setObject(11, Integer.valueOf(this.provocacao_id));
       ps.setObject(12, Integer.valueOf(this.provocacao_id));
       ps.setObject(13, Integer.valueOf(this.provocacao_id));
       ps.setObject(14, Integer.valueOf(this.provocacao_id));
       ps.setObject(15, Integer.valueOf(this.provocacao_id));
       ps.setObject(16, Integer.valueOf(this.provocacao_id));
       ps.setObject(17, Integer.valueOf(this.provocacao_id));

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
     return "" + this.provocacao_id;
   }
 }