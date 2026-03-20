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

 public class Resposta
 {
   private int resposta_id;
   private String data_resposta;
   private String resumo;
   private String nova_ordem_nao_cumprimento;
   private int tipologia_diplomatica;
   private String tipologia_diplomaticaDesc;
   private int registro;
   private String registroDesc;
   private int ano_resposta;
   private Integer referencia_documental;
   private String referencia_documentalDesc;
   private String folio_pagina;
   private String search;
   private String impressos;
   private String anoPost;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allPalResposta;
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

   public int getResposta_id() {
     return this.resposta_id;
   }

   public void setResposta_id(int resposta_id) {
     this.resposta_id = resposta_id;
   }

   public String getData_resposta() {
     return this.data_resposta;
   }

   public void setData_resposta(String data_resposta) {
     this.data_resposta = data_resposta;
   }

   public String getResumo() {
     return this.resumo;
   }

   public void setResumo(String resumo) {
     this.resumo = resumo;
   }

   public String getNova_ordem_nao_cumprimento() {
     return this.nova_ordem_nao_cumprimento;
   }

   public void setNova_ordem_nao_cumprimento(String nova_ordem_nao_cumprimento) {
     this.nova_ordem_nao_cumprimento = nova_ordem_nao_cumprimento;
   }

   public int getTipologia_diplomatica() {
     return this.tipologia_diplomatica;
   }

   public void setTipologia_diplomatica(int tipologia_diplomatica) {
     this.tipologia_diplomatica = tipologia_diplomatica;
   }

   public int getRegistro() {
     return this.registro;
   }

   public void setRegistro(int registro) {
     this.registro = registro;
   }

   public int getAno_resposta() {
     return this.ano_resposta;
   }

   public void setAno_resposta(int ano_resposta) {
     this.ano_resposta = ano_resposta;
   }

   public Integer getReferencia_documental() {
     return this.referencia_documental;
   }

   public void setReferencia_documental(Integer referencia_documental) {
     this.referencia_documental = referencia_documental;
   }

   public String getFolio_pagina() {
     return this.folio_pagina;
   }

   public void setFolio_pagina(String folio_pagina) {
     this.folio_pagina = folio_pagina;
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

   public String getImpressos() {
     return this.impressos;
   }

   public void setImpressos(String impressos) {
     this.impressos = impressos;
   }

   public String getRegistroDesc() {
     return this.registroDesc;
   }

   public void setRegistroDesc(String registroDesc) {
     this.registroDesc = registroDesc;
   }

   public String getReferencia_documentalDesc() {
     return this.referencia_documentalDesc;
   }

   public void setReferencia_documentalDesc(String referencia_documentalDesc) {
     this.referencia_documentalDesc = referencia_documentalDesc;
   }

   public String getTipologia_diplomaticaDesc() {
     return this.tipologia_diplomaticaDesc;
   }

   public void setTipologia_diplomaticaDesc(String tipologia_diplomaticaDesc) {
     this.tipologia_diplomaticaDesc = tipologia_diplomaticaDesc;
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

   public List<Map<String, Object>> getAllPalResposta() {
     return this.allPalResposta;
   }

   public void setAllPalResposta(List<Map<String, Object>> allPalResposta) {
     this.allPalResposta = allPalResposta;
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

   public Resposta allRegistros() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRegistros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select res.pk_resposta, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data_resposta, res.resumo, res.nova_ordem_nao_cumprimento,  (select tdi.nome   from app_hist.tipologia_diplomatica tdi where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica, \t   ( select reg.descricao \t       from app_hist.registro reg \t      where reg.pk_registro = res.registro) registro,  res.ano_resposta, \t   coalesce(( select refa.referencia||'( '|| refa.complemento||')' \t       from app_hist.referencia_documental refa \t      where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_instado_resposta rpc    where pes.pk_pessoa = rpc.instado    and rpc.resposta  = res.pk_resposta),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_resposta rrp    where pes.pk_pessoa = rrp.requerente    and rrp.resposta  = res.pk_resposta),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_resposta rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.resposta  = res.pk_resposta),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_resposta rtp    where tem.pk_tema = rtp.tema    and rtp.resposta  = res.pk_resposta),' ') tema,  coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina  from app_hist.resposta res  ORDER BY res.pk_resposta             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listRegistros.add(Cap);
       } 
       setAllPalResposta(listRegistros);

       String qCount = "select count(*)  from app_hist.resposta ";

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

   public Resposta registro() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Resposta u = new Resposta();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_resposta, coalesce(to_char(data_resposta,'dd/mm/yyyy'),'') data_resposta, resumo, nova_ordem_nao_cumprimento, tipologia_diplomatica, registro , ano_resposta, referencia_documental, impressos,  coalesce(ano_post,'') ano_post, coalesce(folio_pagina,'') folio_pagina  from app_hist.resposta  where pk_resposta = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.resposta_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setResposta_id(rs.getInt(1));
         u.setData_resposta(rs.getString(2));
         u.setResumo(rs.getString(3));
         u.setNova_ordem_nao_cumprimento(rs.getString(4));
         u.setTipologia_diplomatica(rs.getInt(5));
         u.setRegistro(rs.getInt(6));
         u.setAno_resposta(rs.getInt(7));
         u.setReferencia_documental(Integer.valueOf(rs.getInt(8)));
         u.setImpressos(rs.getString(9));
         u.setAnoPost(rs.getString(10));
         u.setFolio_pagina(rs.getString(11));
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

   public Resposta registroInfo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Resposta u = new Resposta();
     try {
       c = Utils.getConnectionStock();
       String q = " select res.pk_resposta id, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data,  res.resumo, res.nova_ordem_nao_cumprimento,  (select tdi.nome   from app_hist.tipologia_diplomatica tdi where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica, \t   ( select reg.descricao \t       from app_hist.registro reg \t      where reg.pk_registro = res.registro) registro,  res.ano_resposta ano, \t   coalesce(( select refa.referencia||'( '|| refa.complemento||')' \t       from app_hist.referencia_documental refa \t      where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental, res.impressos,  coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina,  coalesce((select STRING_AGG (t.nome_termo,'<br>')     from app_hist.relac_termo_resposta rtp, app_hist.termo t     where rtp.termo = t.pk_termo and rtp.resposta = res.pk_resposta),'') termo,  coalesce((select string_agg(f.nome_freguesia,'<br>')      from app_hist.relac_freguesia_resposta rfp, app_hist.freguesia f      where rfp.freguesia = f.pk_freguesia        and rfp.resposta = res.pk_resposta),'') freguesia,   coalesce((select string_agg(c.nome_comarca ,'<br>')        from app_hist.relac_comarca_resposta rcp, app_hist.comarca c      where rcp.comarca = c.pk_comarca      and rcp.resposta = res.pk_resposta),'') comarca,   coalesce((select string_agg(c2.nome_capitania,'<br>')      from app_hist.relac_capitania_resposta rcp2, app_hist.capitania c2     where rcp2.capitania = c2.pk_capitania       and rcp2.resposta = res.pk_resposta),'') capitania  from app_hist.resposta res  where res.pk_resposta = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.resposta_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setResposta_id(rs.getInt(1));
         u.setData_resposta(rs.getString(2));
         u.setResumo(rs.getString(3));
         u.setNova_ordem_nao_cumprimento(rs.getString(4));
         u.setTipologia_diplomaticaDesc(rs.getString(5));
         u.setRegistroDesc(rs.getString(6));
         u.setAno_resposta(rs.getInt(7));
         u.setReferencia_documentalDesc(rs.getString(8));
         u.setImpressos(rs.getString(9));
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
     List<Map<String, Object>> listRefDocumental = new ArrayList<>();
     int conta = 6;
     try {
       c = Utils.getConnectionStock();
       String q = " select res.pk_resposta, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data_resposta, res.resumo, res.nova_ordem_nao_cumprimento,  (select tdi.nome   from app_hist.tipologia_diplomatica tdi where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica, \t   ( select reg.descricao \t       from app_hist.registro reg \t      where reg.pk_registro = res.registro) registro,  res.ano_resposta, \t   coalesce(( select refa.referencia||'( '|| refa.complemento||')' \t       from app_hist.referencia_documental refa \t      where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental, res.impressos,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_instado_resposta rpc    where pes.pk_pessoa = rpc.instado    and rpc.resposta  = res.pk_resposta),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_resposta rrp    where pes.pk_pessoa = rrp.requerente    and rrp.resposta  = res.pk_resposta),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_resposta rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.resposta  = res.pk_resposta),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_resposta rtp    where tem.pk_tema = rtp.tema    and rtp.resposta  = res.pk_resposta),' ') tema,  coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina  from app_hist.resposta res  where (lower(to_char(res.data_resposta,'dd/mm/yyyy')) like '%'||lower(?)||'%'     or lower(res.resumo) like '%'||lower(?)||'%'     or lower(res.nova_ordem_nao_cumprimento) like '%'||lower(?)||'%' \t   or ( select lower(reg.descricao) \t          from app_hist.registro reg \t         where reg.pk_registro = res.registro) like '%'||lower(?)||'%'     or lower(res.impressos) like '%'||lower(?)||'%' \t   or ( select lower(refa.referencia||'( '|| refa.numero||')') \t          from app_hist.referencia_documental refa \t         where refa.pk_ref_documento = res.referencia_documental) like '%'||lower(?)||'%') ";

       if (!"".equals(getFilPessoa())) {
         q = q + " and  (res.pk_resposta in (select distinct rpc.resposta                              from  app_hist.pessoa pes, app_hist.relac_instado_resposta rpc                              where pes.pk_pessoa = rpc.instado                                and lower(pes.nome) like '%'||lower(?)||'%')  or res.pk_resposta in (select distinct rrp.resposta                              from app_hist.pessoa pes, app_hist.relac_requerente_resposta rrp                             where pes.pk_pessoa = rrp.requerente                               and lower(pes.nome) like '%'||lower(?)||'%') ) ";
       }

       if (getFilTermo().intValue() != 0) {
         q = q + " and res.pk_resposta in (select rtp.resposta   from app_hist.relac_tema_resposta rtp    where rtp.tema = ? ) ";
       }

       if (this.filPalChave.intValue() != 0) {
         q = q + " and res.pk_resposta in (select rpp.resposta                      from app_hist.relac_pchave_resposta rpp                     where  rpp.palavra_chave = ?) ";
       }

       q = q + " ";
       ps = c.prepareStatement(q);
       ps.setString(1, getSearch());
       ps.setString(2, getSearch());
       ps.setString(3, getSearch());
       ps.setString(4, getSearch());
       ps.setString(5, getSearch());
       ps.setString(6, getSearch());
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
         listRefDocumental.add(Cap);
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
     return listRefDocumental;
   }

   public Integer countResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numProv  from app_hist.resposta ";

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

   public Resposta conReqResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select res.pk_resposta, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data_resposta,        res.resumo, res.nova_ordem_nao_cumprimento,        (select tdi.nome           from app_hist.tipologia_diplomatica tdi          where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = res.registro) registro,         res.ano_resposta,         coalesce(( select refa.referencia||'( '|| refa.complemento||')'                      from app_hist.referencia_documental refa                     where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental,         coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina    from app_hist.resposta res, app_hist.relac_requerente_resposta rrr   where rrr.resposta =res.pk_resposta     and rrr.requerente = ?    ORDER BY res.pk_resposta             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listResposta.add(Cap);
       } 
       setAllPalResposta(listResposta);

       String qCount = "select count(*)         from app_hist.resposta res, app_hist.relac_requerente_resposta rrr   where rrr.resposta =res.pk_resposta     and rrr.requerente = ? ";

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

   public Resposta conPesCitaResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select res.pk_resposta, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data_resposta,        res.resumo, res.nova_ordem_nao_cumprimento,        (select tdi.nome           from app_hist.tipologia_diplomatica tdi          where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = res.registro) registro,         res.ano_resposta,         coalesce(( select refa.referencia||'( '|| refa.complemento||')'                      from app_hist.referencia_documental refa                     where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental,         coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina    from app_hist.resposta res, app_hist.relac_instado_resposta rir   where rir.resposta =res.pk_resposta     and rir.instado  = ?    ORDER BY res.pk_resposta             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listResposta.add(Cap);
       } 
       setAllPalResposta(listResposta);

       String qCount = "select count(*)         from app_hist.resposta res, app_hist.relac_instado_resposta rir   where rir.resposta =res.pk_resposta     and rir.instado  = ? ";

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

   public Resposta conDestResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "  select res.pk_resposta, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data_resposta,        res.resumo, res.nova_ordem_nao_cumprimento,        (select tdi.nome           from app_hist.tipologia_diplomatica tdi          where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = res.registro) registro,         res.ano_resposta,         coalesce(( select refa.referencia||'( '|| refa.complemento||')'                      from app_hist.referencia_documental refa                     where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental,         coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina    from app_hist.resposta res, app_hist.relac_destinatario_resposta rdr   where rdr.resposta =res.pk_resposta     and rdr.destinatario  = ?    ORDER BY res.pk_resposta             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listResposta.add(Cap);
       } 
       setAllPalResposta(listResposta);

       String qCount = "select count(*)         from app_hist.resposta res, app_hist.relac_destinatario_resposta rdr   where rdr.resposta =res.pk_resposta     and rdr.destinatario  = ? ";

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

   public Resposta conTematResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "  select res.pk_resposta, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data_resposta,        res.resumo, res.nova_ordem_nao_cumprimento,        (select tdi.nome           from app_hist.tipologia_diplomatica tdi          where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = res.registro) registro,         res.ano_resposta,         coalesce(( select refa.referencia||'( '|| refa.complemento||')'                      from app_hist.referencia_documental refa                     where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental,         coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina    from app_hist.resposta res, app_hist.relac_tema_resposta rir   where rir.resposta =res.pk_resposta     and rir.tema  = ?    ORDER BY res.pk_resposta             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listResposta.add(Cap);
       } 
       setAllPalResposta(listResposta);

       String qCount = "select count(*)         from app_hist.resposta res, app_hist.relac_tema_resposta rir   where rir.resposta =res.pk_resposta     and rir.tema  = ? ";

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

   public Resposta conPalChaveResposta() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listResposta = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "  select res.pk_resposta, coalesce(to_char(res.data_resposta,'dd/mm/yyyy'),'') data_resposta,        res.resumo, res.nova_ordem_nao_cumprimento,        (select tdi.nome           from app_hist.tipologia_diplomatica tdi          where tdi.pk_tipologia = res.tipologia_diplomatica) tipologia_diplomatica,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = res.registro) registro,         res.ano_resposta,         coalesce(( select refa.referencia||'( '|| refa.complemento||')'                      from app_hist.referencia_documental refa                     where refa.pk_ref_documento = res.referencia_documental),'') referencia_documental,         coalesce(res.ano_post,'') ano_post, coalesce(res.folio_pagina,'') folio_pagina    from app_hist.resposta res, app_hist.relac_pchave_resposta rir   where rir.resposta =res.pk_resposta     and rir.palavra_chave  = ?    ORDER BY res.pk_resposta             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listResposta.add(Cap);
       } 
       setAllPalResposta(listResposta);

       String qCount = "select count(*)         from app_hist.resposta res, app_hist.relac_pchave_resposta rir   where rir.resposta =res.pk_resposta     and rir.palavra_chave  = ? ";

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
       String q = " INSERT INTO app_hist.resposta   ( data_resposta, resumo, nova_ordem_nao_cumprimento, registro , ano_resposta, referencia_documental, impressos, ano_post, folio_pagina)   VALUES( to_date(?,'dd/mm/yyyy') , ?, ?, ?, ?, ?, ?, ?, ?) ";

       ps = c.prepareStatement(q, 1);
       if ("".equals(this.data_resposta)) {
         ps.setObject(1, (Object)null);
       } else {
         ps.setObject(1, this.data_resposta);
       } 
       ps.setObject(2, this.resumo);
       ps.setObject(3, this.nova_ordem_nao_cumprimento);
       ps.setObject(4, Integer.valueOf(this.registro));
       ps.setObject(5, Integer.valueOf(this.ano_resposta));
       ps.setObject(6, this.referencia_documental);
       ps.setObject(7, this.impressos);
       ps.setObject(8, this.anoPost);
       ps.setObject(9, this.folio_pagina);
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
       String q = "UPDATE app_hist.resposta SET  data_resposta = to_date(?,'dd/mm/yyyy') , resumo = ? , nova_ordem_nao_cumprimento = ? , registro = ?,  ano_resposta = ?,  referencia_documental = ?, impressos = ?,  ano_post = ?, folio_pagina = ?  where pk_resposta = ?";

       ps = c.prepareStatement(q, 1);
       if ("".equals(this.data_resposta)) {
         ps.setObject(1, (Object)null);
       } else {
         ps.setObject(1, this.data_resposta);
       } 
       ps.setObject(2, this.resumo);
       ps.setObject(3, this.nova_ordem_nao_cumprimento);
       ps.setObject(4, Integer.valueOf(this.registro));
       ps.setObject(5, Integer.valueOf(this.ano_resposta));
       ps.setObject(6, this.referencia_documental);
       ps.setObject(7, this.impressos);
       ps.setObject(8, this.anoPost);
       ps.setObject(9, this.folio_pagina);
       ps.setObject(10, Integer.valueOf(this.resposta_id));
       ps.executeUpdate();

       updatedId = this.resposta_id;
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
     return "" + getResposta_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "with rpr as (       delete from app_hist.relac_pchave_resposta rpr           where rpr.resposta  = ?),     rrr as (     delete from app_hist.relac_requerente_resposta rrr           where rrr.resposta  = ? ),     rtr as (     delete from app_hist.relac_tema_resposta rtr           where rtr.resposta  = ? ),     rscr as (     delete from app_hist.relac_secretario_conselho_resposta rscr           where rscr.resposta  = ? ),     rdr as (     delete from app_hist.relac_destinatario_resposta rdr           where rdr.resposta  = ? ),     rir as (     delete from app_hist.relac_instado_resposta rir           where rir.resposta = ? ),     rpr2 as (     delete from app_hist.relac_provocacao_resposta rpr2           where rpr2.resposta  = ? ),     rmr as (     delete from app_hist.relac_mandado_resposta rmr            where rmr.resposta = ? ),     rur as (     delete from app_hist.relac_ultramar_resposta rur           where rur.resposta  = ? ),     rcr as (     delete from app_hist.relac_consulta_resposta rcr           where rcr.resposta  = ? ),   rrr2 as (     delete from app_hist.relac_resposta_resposta rrr2           where rrr2.resposta_mais_recente = ? ),   rrr3 as (     delete from app_hist.relac_resposta_resposta rrr3           where rrr3.resposta_mais_antiga = ? ), rcp2 as (     delete from app_hist.relac_capitania_resposta rcp2           where rcp2.resposta  = ? ), rcp3 as (     delete from app_hist.relac_comarca_resposta rcp3           where rcp3.resposta  = ? ), rfp as (     delete from app_hist.relac_freguesia_resposta rfp           where rfp.resposta  = ? ), rtp3 as (     delete from app_hist.relac_termo_resposta rtp3           where rtp3.resposta  = ? )  delete from app_hist.resposta r     where r.pk_resposta  = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.resposta_id));
       ps.setObject(2, Integer.valueOf(this.resposta_id));
       ps.setObject(3, Integer.valueOf(this.resposta_id));
       ps.setObject(4, Integer.valueOf(this.resposta_id));
       ps.setObject(5, Integer.valueOf(this.resposta_id));
       ps.setObject(6, Integer.valueOf(this.resposta_id));
       ps.setObject(7, Integer.valueOf(this.resposta_id));
       ps.setObject(8, Integer.valueOf(this.resposta_id));
       ps.setObject(9, Integer.valueOf(this.resposta_id));
       ps.setObject(10, Integer.valueOf(this.resposta_id));
       ps.setObject(11, Integer.valueOf(this.resposta_id));
       ps.setObject(12, Integer.valueOf(this.resposta_id));
       ps.setObject(13, Integer.valueOf(this.resposta_id));
       ps.setObject(14, Integer.valueOf(this.resposta_id));
       ps.setObject(15, Integer.valueOf(this.resposta_id));
       ps.setObject(16, Integer.valueOf(this.resposta_id));
       ps.setObject(17, Integer.valueOf(this.resposta_id));

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
     return "" + this.resposta_id;
   }
 }