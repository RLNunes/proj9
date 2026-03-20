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

 public class Ultramar
 {
   private int ultramar_id;
   private String resumo;
   private int registro;
   private String registroDesc;
   private String data;
   private int ano;
   private Integer referencia_documental;
   private String referencia_documentalDesc;
   private String autoridade;
   private String search;
   private String anoPost;
   private String folio_pagina;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allUltramar;
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

   public int getUltramar_id() {
     return this.ultramar_id;
   }

   public void setUltramar_id(int ultramar_id) {
     this.ultramar_id = ultramar_id;
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

   public Integer getReferencia_documental() {
     return this.referencia_documental;
   }

   public void setReferencia_documental(Integer referencia_documental) {
     this.referencia_documental = referencia_documental;
   }

   public String getAutoridade() {
     return this.autoridade;
   }

   public void setAutoridade(String autoridade) {
     this.autoridade = autoridade;
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

   public List<Map<String, Object>> getAllUltramar() {
     return this.allUltramar;
   }

   public void setAllUltramar(List<Map<String, Object>> allUltramar) {
     this.allUltramar = allUltramar;
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

   public Ultramar allUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listRegistros = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ult.pk_ultramar, ult.resumo, coalesce(to_char(ult.data,'dd/mm/yyyy'),'') data, \t   coalesce(( select refa.referencia||'( '|| refa.complemento||')' \t       from app_hist.referencia_documental refa \t      where refa.pk_ref_documento = ult.referencia_documental),'') referencia_documental, \t   ( select reg.descricao \t       from app_hist.registro reg \t      where reg.pk_registro = ult.registro) registro,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_ultramar rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.ultramar  = ult.pk_ultramar),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_ultramar rrp    where pes.pk_pessoa = rrp.requerente    and rrp.ultramar  = ult.pk_ultramar),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_ultramar rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.ultramar  = ult.pk_ultramar),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_ultramar rtp    where tem.pk_tema = rtp.tema    and rtp.ultramar  = ult.pk_ultramar),' ') tema,        ult.ano, ult.autoridade, coalesce(ult.ano_post,'') ano_post, coalesce(ult.folio_pagina,'') folio_pagina from app_hist.ultramar ult  ORDER BY ult.pk_ultramar             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
       setAllUltramar(listRegistros);

       String qCount = "select count(*)  from app_hist.ultramar ";

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

   public Ultramar ultramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Ultramar u = new Ultramar();
     try {
       c = Utils.getConnectionStock();
       String q = " select pk_ultramar, resumo, registro, coalesce(to_char(data,'dd/mm/yyyy'),'') data, ano, referencia_documental, autoridade, coalesce(ano_post,'') ano_post,  coalesce(folio_pagina,'') folio_pagina  from app_hist.ultramar  where pk_ultramar = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.ultramar_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setUltramar_id(rs.getInt(1));
         u.setResumo(rs.getString(2));
         u.setRegistro(rs.getInt(3));
         u.setData(rs.getString(4));
         u.setAno(rs.getInt(5));
         u.setReferencia_documental(Integer.valueOf(rs.getInt(6)));
         u.setAutoridade(rs.getString(7));
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

   public Ultramar ultramarInfo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Ultramar u = new Ultramar();
     try {
       c = Utils.getConnectionStock();
       String q = " select ult.pk_ultramar id, ult.resumo, coalesce(to_char(ult.data,'dd/mm/yyyy'),'') data, \t   ( select refa.referencia||'( '|| refa.complemento||')' \t       from app_hist.referencia_documental refa \t      where refa.pk_ref_documento = ult.referencia_documental) referencia_documental, \t   ( select reg.descricao \t       from app_hist.registro reg \t      where reg.pk_registro = ult.registro) registro,        ult.ano, ult.autoridade, coalesce(ult.ano_post,'') ano_post,  coalesce(ult.folio_pagina,'') folio_pagina,  coalesce((select STRING_AGG (t.nome_termo,'<br>')     from app_hist.relac_termo_ultramar rtp, app_hist.termo t     where rtp.termo = t.pk_termo and rtp.ultramar = ult.pk_ultramar),'') termo,  coalesce((select string_agg(f.nome_freguesia,'<br>')      from app_hist.relac_freguesia_ultramar rfp, app_hist.freguesia f      where rfp.freguesia = f.pk_freguesia        and rfp.ultramar = ult.pk_ultramar),'') freguesia,   coalesce((select string_agg(c.nome_comarca ,'<br>')        from app_hist.relac_comarca_ultramar rcp, app_hist.comarca c      where rcp.comarca = c.pk_comarca      and rcp.ultramar = ult.pk_ultramar),'') comarca,   coalesce((select string_agg(c2.nome_capitania,'<br>')      from app_hist.relac_capitania_ultramar rcp2, app_hist.capitania c2     where rcp2.capitania = c2.pk_capitania       and rcp2.ultramar = ult.pk_ultramar),'') capitania  from app_hist.ultramar ult where ult.pk_ultramar = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.ultramar_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setUltramar_id(rs.getInt(1));
         u.setResumo(rs.getString(2));
         u.setData(rs.getString(3));
         u.setReferencia_documentalDesc(rs.getString(4));
         u.setRegistroDesc(rs.getString(5));
         u.setAno(rs.getInt(6));
         u.setAutoridade(rs.getString(7));
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
     List<Map<String, Object>> listRefDocumental = new ArrayList<>();
     int conta = 5;
     try {
       c = Utils.getConnectionStock();
       String q = "select ult.pk_ultramar, ult.resumo, coalesce(to_char(ult.data,'dd/mm/yyyy'),'') data, \t   coalesce(( select refa.referencia||'( '|| refa.complemento||')' \t       from app_hist.referencia_documental refa \t      where refa.pk_ref_documento = ult.referencia_documental),'') referencia_documental, \t   ( select reg.descricao \t       from app_hist.registro reg \t      where reg.pk_registro = ult.registro) registro,        ult.ano, ult.autoridade, coalesce(ult.ano_post,'') ano_post,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_ultramar rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.ultramar  = ult.pk_ultramar),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_ultramar rrp    where pes.pk_pessoa = rrp.requerente    and rrp.ultramar  = ult.pk_ultramar),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_ultramar rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.ultramar  = ult.pk_ultramar),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_ultramar rtp    where tem.pk_tema = rtp.tema    and rtp.ultramar  = ult.pk_ultramar),' ') tema,  coalesce(ult.folio_pagina,'') folio_pagina  from app_hist.ultramar ult where (lower(ult.resumo) like '%'||lower(?)||'%'     or lower(ult.autoridade) like '%'||lower(?)||'%'    or lower(to_char(ult.data,'dd/mm/yyyy')) like '%'||lower(?)||'%'     or ( select lower(refa.referencia||'( '|| refa.numero||')') \t       from app_hist.referencia_documental refa \t      where refa.pk_ref_documento = ult.referencia_documental) like '%'||lower(?)||'%'     or ( select lower(reg.descricao) \t       from app_hist.registro reg \t      where reg.pk_registro = ult.registro) like '%'||lower(?)||'%') ";

       if (!"".equals(getFilPessoa())) {
         q = q + " and  (ult.pk_ultramar in (select distinct rpc.ultramar                              from  app_hist.pessoa pes, app_hist.relac_pcitadas_ultramar rpc                              where pes.pk_pessoa = rpc.p_citadas                                and lower(pes.nome) like '%'||lower(?)||'%')  or ult.pk_ultramar in (select distinct rrp.ultramar                              from app_hist.pessoa pes, app_hist.relac_requerente_ultramar rrp                             where pes.pk_pessoa = rrp.requerente                               and lower(pes.nome) like '%'||lower(?)||'%') ) ";
       }

       if (getFilTermo().intValue() != 0) {
         q = q + " and ult.pk_ultramar in (select rtp.ultramar   from app_hist.relac_tema_ultramar rtp    where rtp.tema = ? ) ";
       }

       if (this.filPalChave.intValue() != 0) {
         q = q + " and ult.pk_ultramar in (select rpp.ultramar                      from app_hist.relac_pchave_ultramar rpp                     where  rpp.palavra_chave = ?) ";
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
         listRefDocumental.add(Cap);
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
     return listRefDocumental;
   }

   public Integer countUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numProv  from app_hist.ultramar ";

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

   public Ultramar conReqUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ult.pk_ultramar, ult.resumo, coalesce(to_char(ult.data,'dd/mm/yyyy'),'') data,        coalesce(( select refa.referencia||'( '|| refa.complemento||')'                     from app_hist.referencia_documental refa                    where refa.pk_ref_documento = ult.referencia_documental),'') referencia_documental,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = ult.registro) registro,         ult.ano, ult.autoridade, coalesce(ult.ano_post,'') ano_post, coalesce(ult.folio_pagina,'') folio_pagina    from app_hist.ultramar ult, app_hist.relac_requerente_ultramar rru   where ult.pk_ultramar = rru.ultramar     and rru.requerente = ?    ORDER BY ult.pk_ultramar             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listUltramar.add(Cap);
       } 
       setAllUltramar(listUltramar);

       String qCount = "select count(*)         from app_hist.ultramar ult, app_hist.relac_requerente_ultramar rru   where ult.pk_ultramar = rru.ultramar     and rru.requerente = ? ";

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

   public Ultramar conPesCitaUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ult.pk_ultramar, ult.resumo, coalesce(to_char(ult.data,'dd/mm/yyyy'),'') data,        coalesce(( select refa.referencia||'( '|| refa.complemento||')'                     from app_hist.referencia_documental refa                    where refa.pk_ref_documento = ult.referencia_documental),'') referencia_documental,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = ult.registro) registro,         ult.ano, ult.autoridade, coalesce(ult.ano_post,'') ano_post, coalesce(ult.folio_pagina,'') folio_pagina    from app_hist.ultramar ult, app_hist.relac_pcitadas_ultramar rpu   where ult.pk_ultramar = rpu.ultramar     and rpu.p_citadas  = ?    ORDER BY ult.pk_ultramar             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listUltramar.add(Cap);
       } 
       setAllUltramar(listUltramar);

       String qCount = "select count(*)         from app_hist.ultramar ult, app_hist.relac_pcitadas_ultramar rpu   where ult.pk_ultramar = rpu.ultramar     and rpu.p_citadas  = ? ";

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

   public Ultramar conTemaUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ult.pk_ultramar, ult.resumo, coalesce(to_char(ult.data,'dd/mm/yyyy'),'') data,        coalesce(( select refa.referencia||'( '|| refa.complemento||')'                     from app_hist.referencia_documental refa                    where refa.pk_ref_documento = ult.referencia_documental),'') referencia_documental,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = ult.registro) registro,         ult.ano, ult.autoridade, coalesce(ult.ano_post,'') ano_post, coalesce(ult.folio_pagina,'') folio_pagina    from app_hist.ultramar ult, app_hist.relac_tema_ultramar rtu   where ult.pk_ultramar = rtu.ultramar     and rtu.tema =  ?    ORDER BY ult.pk_ultramar             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listUltramar.add(Cap);
       } 
       setAllUltramar(listUltramar);

       String qCount = "select count(*)         from app_hist.ultramar ult, app_hist.relac_tema_ultramar rtu   where ult.pk_ultramar = rtu.ultramar     and rtu.tema = ? ";

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

   public Ultramar conPalChaveUltramar() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listUltramar = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select ult.pk_ultramar, ult.resumo, coalesce(to_char(ult.data,'dd/mm/yyyy'),'') data,        coalesce(( select refa.referencia||'( '|| refa.complemento||')'                     from app_hist.referencia_documental refa                    where refa.pk_ref_documento = ult.referencia_documental),'') referencia_documental,        ( select reg.descricao            from app_hist.registro reg           where reg.pk_registro = ult.registro) registro,         ult.ano, ult.autoridade, coalesce(ult.ano_post,'') ano_post, coalesce(ult.folio_pagina,'') folio_pagina    from app_hist.ultramar ult, app_hist.relac_pchave_ultramar rtu   where ult.pk_ultramar = rtu.ultramar     and rtu.palavra_chave = ?    ORDER BY ult.pk_ultramar             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listUltramar.add(Cap);
       } 
       setAllUltramar(listUltramar);

       String qCount = "select count(*)         from app_hist.ultramar ult, app_hist.relac_pchave_ultramar rtu   where ult.pk_ultramar = rtu.ultramar     and rtu.palavra_chave = ? ";

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
       String q = " INSERT INTO app_hist.ultramar   ( resumo, registro, data, ano, referencia_documental, autoridade, ano_post, folio_pagina )   VALUES( ?, ?, to_date(?,'dd/mm/yyyy'), ?, ?, ?, ?, ? ) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.resumo);
       ps.setObject(2, Integer.valueOf(this.registro));
       if ("".equals(this.data)) {
         ps.setObject(3, (Object)null);
       } else {
         ps.setObject(3, this.data);
       } 
       ps.setObject(4, Integer.valueOf(this.ano));
       ps.setObject(5, this.referencia_documental);
       ps.setObject(6, this.autoridade);
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
     int toRet = 0;
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.ultramar SET  resumo = ?,  registro = ?,  data = to_date(?,'dd/mm/yyyy'),  ano = ? , referencia_documental = ?,  autoridade = ?,  ano_post = ?,  folio_pagina = ?  where pk_ultramar = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.resumo);
       ps.setObject(2, Integer.valueOf(this.registro));
       if ("".equals(this.data)) {
         ps.setObject(3, (Object)null);
       } else {
         ps.setObject(3, this.data);
       } 
       ps.setObject(4, Integer.valueOf(this.ano));
       ps.setObject(5, this.referencia_documental);
       ps.setObject(6, this.autoridade);
       ps.setObject(7, this.anoPost);
       ps.setObject(8, this.folio_pagina);
       ps.setObject(9, Integer.valueOf(this.ultramar_id));
       ps.executeUpdate();
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

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "with rru as (       delete from app_hist.relac_requerente_ultramar rru           where rru.ultramar = ?),     rpu as (     delete from app_hist.relac_pcitadas_ultramar rpu           where rpu.ultramar = ? ),     rtu as (     delete from app_hist.relac_tema_ultramar rtu           where rtu.ultramar = ? ),     rpu2 as (     delete from app_hist.relac_pchave_ultramar rpu2           where rpu2.ultramar = ? ),     ruc as (     delete from app_hist.relac_ultramar_consulta ruc           where ruc.ultramar = ? ),     rup as (     delete from app_hist.relac_ultramar_provocacao rup           where rup.ultramar = ? ) ,     rmu as (     delete from app_hist.relac_mandado_ultramar rmu           where rmu.ultrarmar = ? ),     ruu as (     delete from app_hist.relac_ultramar_ultramar ruu           where ruu.ultramar_mais_antigo = ?              or ruu.ultramar_mais_recente = ? ),     rur as (     delete from app_hist.relac_ultramar_resposta rur           where rur.ultramar = ? ), rcp2 as (     delete from app_hist.relac_capitania_ultramar rcp2           where rcp2.ultramar  = ? ), rcp3 as (     delete from app_hist.relac_comarca_ultramar rcp3           where rcp3.ultramar  = ? ), rfp as (     delete from app_hist.relac_freguesia_ultramar rfp           where rfp.ultramar  = ? ), rtp3 as (     delete from app_hist.relac_termo_ultramar rtp3           where rtp3.ultramar  = ? )  delete from app_hist.ultramar u     where u.pk_ultramar = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.ultramar_id));
       ps.setObject(2, Integer.valueOf(this.ultramar_id));
       ps.setObject(3, Integer.valueOf(this.ultramar_id));
       ps.setObject(4, Integer.valueOf(this.ultramar_id));
       ps.setObject(5, Integer.valueOf(this.ultramar_id));
       ps.setObject(6, Integer.valueOf(this.ultramar_id));
       ps.setObject(7, Integer.valueOf(this.ultramar_id));
       ps.setObject(8, Integer.valueOf(this.ultramar_id));
       ps.setObject(9, Integer.valueOf(this.ultramar_id));
       ps.setObject(10, Integer.valueOf(this.ultramar_id));
       ps.setObject(11, Integer.valueOf(this.ultramar_id));
       ps.setObject(12, Integer.valueOf(this.ultramar_id));
       ps.setObject(13, Integer.valueOf(this.ultramar_id));
       ps.setObject(14, Integer.valueOf(this.ultramar_id));
       ps.setObject(15, Integer.valueOf(this.ultramar_id));

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
     return "" + this.ultramar_id;
   }
 }