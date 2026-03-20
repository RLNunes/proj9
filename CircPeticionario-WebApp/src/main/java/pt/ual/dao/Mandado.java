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

 public class Mandado
 {
   private int mandado_id;
   private String data;
   private int ano;
   private int registro;
   private String registroDesc;
   private Integer referencia_documental;
   private String referencia_documentalDesc;
   private String resumo;
   private String mandado;
   private String nome_quem_envia;
   private String folio_pagina;
   private String search;
   private String ano_post;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allMandados;
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

   public int getMandado_id() {
     return this.mandado_id;
   }

   public void setMandado_id(int mandado_id) {
     this.mandado_id = mandado_id;
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

   public Integer getReferencia_documental() {
     return this.referencia_documental;
   }

   public void setReferencia_documental(Integer referencia_documental) {
     this.referencia_documental = referencia_documental;
   }

   public String getResumo() {
     return this.resumo;
   }

   public void setResumo(String resumo) {
     this.resumo = resumo;
   }

   public String getMandado() {
     return this.mandado;
   }

   public void setMandado(String mandado) {
     this.mandado = mandado;
   }

   public String getNome_quem_envia() {
     return this.nome_quem_envia;
   }

   public void setNome_quem_envia(String nome_quem_envia) {
     this.nome_quem_envia = nome_quem_envia;
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

   public String getAno_post() {
     return this.ano_post;
   }

   public void setAno_post(String ano_post) {
     this.ano_post = ano_post;
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

   public List<Map<String, Object>> getAllMandados() {
     return this.allMandados;
   }

   public void setAllMandados(List<Map<String, Object>> allMandados) {
     this.allMandados = allMandados;
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

   public Mandado allMandados() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandados = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select man.pk_mandado, coalesce(to_char(man.data,'dd/mm/yyyy'),'') data, man.ano,  reg.descricao registro,    coalesce ((select rd.referencia ||' ('||rd.complemento||')' \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = man.referencia_documental),' ') referencia_documental,  man.resumo, man.mandado, man.nome_quem_envia,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_mandado rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.mandado  = man.pk_mandado),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_mandado rrp    where pes.pk_pessoa = rrp.requerente    and rrp.mandado  = man.pk_mandado),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_mandado rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.mandado  = man.pk_mandado),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_mandado rtp    where tem.pk_tema = rtp.tema    and rtp.mandado  = man.pk_mandado),' ') tema,  coalesce(man.ano_post,'') ano_post, coalesce(man.folio_pagina,'') folio_pagina  from app_hist.mandado man, app_hist.registro reg  where man.registro = reg.pk_registro  ORDER BY man.pk_mandado             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listMandados.add(Cap);
       } 
       setAllMandados(listMandados);

       String qCount = "select count(*)  from app_hist.mandado ";

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

   public Mandado mandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Mandado u = new Mandado();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_mandado, coalesce(to_char(data,'dd/mm/yyyy'),'') data, ano, registro,  referencia_documental,  resumo, mandado, nome_quem_envia,  coalesce(ano_post,'') ano_post, coalesce(folio_pagina,'') folio_pagina  from app_hist.mandado where pk_mandado = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.mandado_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setMandado_id(rs.getInt(1));
         u.setData(rs.getString(2));
         u.setAno(rs.getInt(3));
         u.setRegistro(rs.getInt(4));
         u.setReferencia_documental(Integer.valueOf(rs.getInt(5)));
         u.setResumo(rs.getString(6));
         u.setMandado(rs.getString(7));
         u.setNome_quem_envia(rs.getString(8));
         u.setAno_post(rs.getString(9));
         u.setFolio_pagina(rs.getString(10));
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

   public Mandado mandadoInfo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Mandado u = new Mandado();
     try {
       c = Utils.getConnectionStock();
       String q = "select man.pk_mandado, coalesce(to_char(man.data,'dd/mm/yyyy'),'') data, man.ano,  reg.descricao registro, \t   coalesce ((select rd.referencia ||'('||rd.complemento||')' \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = man.referencia_documental),' ') referencia_documental,  man.resumo, man.mandado, man.nome_quem_envia,  coalesce(man.ano_post,'') ano_post, coalesce(man.folio_pagina,'') folio_pagina,  coalesce((select STRING_AGG (t.nome_termo,'<br>')     from app_hist.relac_termo_mandado rtp, app_hist.termo t     where rtp.termo = t.pk_termo and rtp.mandado = man.pk_mandado),'') termo,  coalesce((select string_agg(f.nome_freguesia,'<br>')      from app_hist.relac_freguesia_mandado rfp, app_hist.freguesia f      where rfp.freguesia = f.pk_freguesia        and rfp.mandado = man.pk_mandado),'') freguesia,   coalesce((select string_agg(c.nome_comarca ,'<br>')        from app_hist.relac_comarca_mandado rcp, app_hist.comarca c      where rcp.comarca = c.pk_comarca      and rcp.mandado = man.pk_mandado),'') comarca,   coalesce((select string_agg(c2.nome_capitania,'<br>')      from app_hist.relac_capitania_mandado rcp2, app_hist.capitania c2     where rcp2.capitania = c2.pk_capitania       and rcp2.mandado = man.pk_mandado),'') capitania  from app_hist.mandado man, app_hist.registro reg  where man.registro = reg.pk_registro  and pk_mandado = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.mandado_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setMandado_id(rs.getInt(1));
         u.setData(rs.getString(2));
         u.setAno(rs.getInt(3));
         u.setRegistroDesc(rs.getString(4));
         u.setReferencia_documentalDesc(rs.getString(5));
         u.setResumo(rs.getString(6));
         u.setMandado(rs.getString(7));
         u.setNome_quem_envia(rs.getString(8));
         u.setAno_post(rs.getString(9));
         u.setFolio_pagina(rs.getString(10));
         u.setTermo(rs.getString(11));
         u.setFreguesia(rs.getString(12));
         u.setComarca(rs.getString(13));
         u.setCapitania(rs.getString(14));
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
     List<Map<String, Object>> listMandado = new ArrayList<>();
     int conta = 5;
     try {
       c = Utils.getConnectionStock();
       String q = "select man.pk_mandado, coalesce(to_char(man.data,'dd/mm/yyyy'),'') data, man.ano,  reg.descricao registro, \t   coalesce ((select rd.referencia ||' ('||rd.complemento||')' \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = man.referencia_documental),' ') referencia_documental,  man.resumo, man.mandado, man.nome_quem_envia,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_pcitadas_mandado rpc    where pes.pk_pessoa = rpc.p_citadas    and rpc.mandado  = man.pk_mandado),' ') pesCita,  coalesce((select STRING_AGG(pes.nome,'<br>')    from app_hist.pessoa pes, app_hist.relac_requerente_mandado rrp    where pes.pk_pessoa = rrp.requerente    and rrp.mandado  = man.pk_mandado),' ') pesReq,  coalesce((select STRING_AGG(pal.palavra_chave,'<br>')    from app_hist.palavra_chave pal, app_hist.relac_pchave_mandado rpp    where pal.pk_palavra_chave = rpp.palavra_chave    and rpp.mandado  = man.pk_mandado),' ') palCha,  coalesce((select STRING_AGG(tem.tema,'<br>')    from app_hist.tema tem, app_hist.relac_tema_mandado rtp    where tem.pk_tema = rtp.tema    and rtp.mandado  = man.pk_mandado),' ') tema,  coalesce(man.ano_post,'') ano_post, coalesce(man.folio_pagina,'') folio_pagina  from app_hist.mandado man, app_hist.registro reg where reg.pk_registro = man.registro   and (lower(resumo) like '%'||lower(?)||'%'   or lower(mandado) like '%'||lower(?)||'%'   or lower(nome_quem_envia) like '%'||lower(?)||'%'   or (select lower(rd.referencia ||' ('||rd.complemento||')')  \t      from app_hist.referencia_documental rd \t     where rd.pk_ref_documento = man.referencia_documental) like '%'||lower(?)||'%'   or lower(reg.descricao) like '%'||lower(?)||'%') ";

       if (!"".equals(getFilPessoa())) {
         q = q + " and  (man.pk_mandado in (select distinct rpc.mandado                              from  app_hist.pessoa pes, app_hist.relac_pcitadas_mandado rpc                              where pes.pk_pessoa = rpc.p_citadas                                and lower(pes.nome) like '%'||lower(?)||'%')  or man.pk_mandado in (select distinct rrp.mandado                              from app_hist.pessoa pes, app_hist.relac_requerente_mandado rrp                             where pes.pk_pessoa = rrp.requerente                               and lower(pes.nome) like '%'||lower(?)||'%') ) ";
       }

       if (getFilTermo().intValue() != 0) {
         q = q + " and man.pk_mandado in (select rtp.mandado   from app_hist.relac_tema_mandado rtp    where rtp.tema = ? ) ";
       }

       if (this.filPalChave.intValue() != 0) {
         q = q + " and man.pk_mandado in (select rpp.mandado                      from app_hist.relac_pchave_mandado rpp                     where  rpp.palavra_chave = ?) ";
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

   public Integer countMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numProv  from app_hist.mandado ";

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

   public Mandado conReqMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select man.pk_mandado, coalesce(to_char(man.data,'dd/mm/yyyy'),'') data, man.ano,              reg.descricao registro,              coalesce ((select rd.referencia ||' ('||rd.complemento||')'                          from app_hist.referencia_documental rd                         where rd.pk_ref_documento = man.referencia_documental),' ') referencia_documental,               man.resumo, man.mandado, man.nome_quem_envia,               coalesce(man.ano_post,'') ano_post, coalesce(man.folio_pagina,'') folio_pagina      from app_hist.relac_requerente_mandado rrm, app_hist.mandado man, app_hist.registro reg      where rrm.mandado = man.pk_mandado        and man.registro = reg.pk_registro       and rrm.requerente  =  ?    ORDER BY man.pk_mandado             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listMandado.add(Cap);
       } 
       setAllMandados(listMandado);

       String qCount = "select count(*)         from app_hist.relac_requerente_mandado rrm, app_hist.mandado man     where rrm.mandado = man.pk_mandado        and rrm.requerente  =  ? ";

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

   public Mandado conPesCitaMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select man.pk_mandado, coalesce(to_char(man.data,'dd/mm/yyyy'),'') data, man.ano,              reg.descricao registro,              coalesce ((select rd.referencia ||' ('||rd.complemento||')'                          from app_hist.referencia_documental rd                         where rd.pk_ref_documento = man.referencia_documental),' ') referencia_documental,               man.resumo, man.mandado, man.nome_quem_envia,               coalesce(man.ano_post,'') ano_post, coalesce(man.folio_pagina,'') folio_pagina           from app_hist.relac_pcitadas_mandado rpm, app_hist.mandado man, app_hist.registro reg          where rpm.mandado = man.pk_mandado          and man.registro = reg.pk_registro          and rpm.p_citadas  =  ?    ORDER BY man.pk_mandado             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listMandado.add(Cap);
       } 
       setAllMandados(listMandado);

       String qCount = "select count(*)     from app_hist.relac_pcitadas_mandado rpm, app_hist.mandado man          where rpm.mandado = man.pk_mandado          and rpm.p_citadas  =  ? ";

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

   public Mandado conTemaMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select man.pk_mandado, coalesce(to_char(man.data,'dd/mm/yyyy'),'') data, man.ano,              reg.descricao registro,              coalesce ((select rd.referencia ||' ('||rd.complemento||')'                          from app_hist.referencia_documental rd                         where rd.pk_ref_documento = man.referencia_documental),' ') referencia_documental,               man.resumo, man.mandado, man.nome_quem_envia,               coalesce(man.ano_post,'') ano_post, coalesce(man.folio_pagina,'') folio_pagina      from app_hist.relac_tema_mandado rtm, app_hist.mandado man, app_hist.registro reg           where rtm.mandado = man.pk_mandado           and man.registro = reg.pk_registro          and rtm.tema  = ?    ORDER BY man.pk_mandado             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listMandado.add(Cap);
       } 
       setAllMandados(listMandado);

       String qCount = "select count(*)         from app_hist.relac_tema_mandado rtm, app_hist.mandado man, app_hist.registro reg           where rtm.mandado = man.pk_mandado           and man.registro = reg.pk_registro          and rtm.tema  = ? ";

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

   public Mandado conPalChaveMandado() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listMandado = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select man.pk_mandado, coalesce(to_char(man.data,'dd/mm/yyyy'),'') data, man.ano,              reg.descricao registro,              coalesce ((select rd.referencia ||' ('||rd.complemento||')'                          from app_hist.referencia_documental rd                         where rd.pk_ref_documento = man.referencia_documental),' ') referencia_documental,               man.resumo, man.mandado, man.nome_quem_envia,               coalesce(man.ano_post,'') ano_post, coalesce(man.folio_pagina,'') folio_pagina      from app_hist.relac_pchave_mandado rtm, app_hist.mandado man, app_hist.registro reg           where rtm.mandado = man.pk_mandado           and man.registro = reg.pk_registro          and rtm.palavra_chave  = ?    ORDER BY man.pk_mandado             LIMIT ?             OFFSET ((? - 1) * ?) ";

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
         listMandado.add(Cap);
       } 
       setAllMandados(listMandado);

       String qCount = "select count(*)         from app_hist.relac_pchave_mandado rtm, app_hist.mandado man          where rtm.mandado = man.pk_mandado           and rtm.palavra_chave  = ? ";

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
       String q = "INSERT INTO app_hist.mandado ( data, ano, registro,  referencia_documental,  resumo, mandado, nome_quem_envia, ano_post, folio_pagina)  VALUES( to_date(?, 'dd/mm/yyyy'), ?, ?, ?, ?, ?, ?, ?, ?) ";

       ps = c.prepareStatement(q, 1);
       if (this.data == "") {
         ps.setObject(1, (Object)null);
       } else {
         ps.setObject(1, this.data);
       } 
       ps.setObject(2, Integer.valueOf(this.ano));
       ps.setObject(3, Integer.valueOf(this.registro));
       ps.setObject(4, this.referencia_documental);
       ps.setObject(5, this.resumo);
       ps.setObject(6, this.mandado);
       ps.setObject(7, this.nome_quem_envia);
       ps.setObject(8, this.ano_post);
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
       String q = "UPDATE app_hist.mandado SET  data = to_date(?,'dd/mm/yyyy'),  ano = ?,  registro = ?,  referencia_documental = ?,  resumo = ?,  mandado = ?,  nome_quem_envia = ?,  ano_post = ?, folio_pagina = ?  where pk_mandado = ? ";

       ps = c.prepareStatement(q, 1);
       if ("".equals(this.data)) {
         ps.setObject(1, (Object)null);
       } else {
         ps.setObject(1, this.data);
       } 
       ps.setObject(2, Integer.valueOf(this.ano));
       ps.setObject(3, Integer.valueOf(this.registro));
       ps.setObject(4, this.referencia_documental);
       ps.setObject(5, this.resumo);
       ps.setObject(6, this.mandado);
       ps.setObject(7, this.nome_quem_envia);
       ps.setObject(8, this.ano_post);
       ps.setObject(9, this.folio_pagina);
       ps.setObject(10, Integer.valueOf(this.mandado_id));
       ps.executeUpdate();

       updatedId = this.mandado_id;
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
     return "" + updatedId;
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "with rrm as (       delete from app_hist.relac_requerente_mandado rrm           where rrm.mandado = ?),     rpm as (     delete from app_hist.relac_pcitadas_mandado rpm           where rpm.mandado = ? ),     rtm as (     delete from app_hist.relac_tema_mandado rtm           where rtm.mandado = ? ),     rpm2 as (     delete from app_hist.relac_pchave_mandado rpm2           where rpm2.mandado = ? ),     rmp as (     delete from app_hist.relac_mandado_provocacao rmp           where rmp.mandado = ? ),     rmm as (     delete from app_hist.relac_mandado_mandado rmm           where rmm.mandado_mais_recente = ?             or rmm.mandado_mais_antigo = ? ),     rmc as (     delete from app_hist.relac_mandado_consulta rmc           where rmc.mandado = ? ),     rmu as (     delete from app_hist.relac_mandado_ultramar rmu           where rmu.mandado = ? ),     rmr as (     delete from app_hist.relac_mandado_resposta rmr           where rmr.mandado = ? ), rcp2 as (     delete from app_hist.relac_capitania_mandado rcp2           where rcp2.mandado  = ? ), rcp3 as (     delete from app_hist.relac_comarca_mandado rcp3           where rcp3.mandado  = ? ), rfp as (     delete from app_hist.relac_freguesia_mandado rfp           where rfp.mandado  = ? ), rtp3 as (     delete from app_hist.relac_termo_mandado rtp3           where rtp3.mandado  = ? ) delete from app_hist.mandado m     where m.pk_mandado = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.mandado_id));
       ps.setObject(2, Integer.valueOf(this.mandado_id));
       ps.setObject(3, Integer.valueOf(this.mandado_id));
       ps.setObject(4, Integer.valueOf(this.mandado_id));
       ps.setObject(5, Integer.valueOf(this.mandado_id));
       ps.setObject(6, Integer.valueOf(this.mandado_id));
       ps.setObject(7, Integer.valueOf(this.mandado_id));
       ps.setObject(8, Integer.valueOf(this.mandado_id));
       ps.setObject(9, Integer.valueOf(this.mandado_id));
       ps.setObject(10, Integer.valueOf(this.mandado_id));
       ps.setObject(11, Integer.valueOf(this.mandado_id));
       ps.setObject(12, Integer.valueOf(this.mandado_id));
       ps.setObject(13, Integer.valueOf(this.mandado_id));
       ps.setObject(14, Integer.valueOf(this.mandado_id));
       ps.setObject(15, Integer.valueOf(this.mandado_id));

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
     return "" + this.mandado_id;
   }
 }