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

 public class Pessoa
 {
   private int pk_pessoa;
   private String nome;
   private Integer idade;
   private Integer marcador_economico_ocupacao;
   private String marcador_economico_ocupacaoDesc;
   private String sexo;
   private String filiacao;
   private Integer termo;
   private String termoDesc;
   private Integer freguesia;
   private String freguesiaDesc;
   private Integer comarca;
   private String comarcaDesc;
   private Integer capitania;
   private String capitaniaDesc;
   private String search;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allPessoas;

   public int getPk_pessoa() {
     return this.pk_pessoa;
   }

   public void setPk_pessoa(int pk_pessoa) {
     this.pk_pessoa = pk_pessoa;
   }

   public String getNome() {
     return this.nome;
   }

   public void setNome(String nome) {
     this.nome = nome;
   }

   public Integer getIdade() {
     return this.idade;
   }

   public void setIdade(Integer idade) {
     this.idade = idade;
   }

   public Integer getMarcador_economico_ocupacao() {
     if (this.marcador_economico_ocupacao.intValue() == -1 || this.marcador_economico_ocupacao.intValue() == 0) {
       return null;
     }
     return this.marcador_economico_ocupacao;
   }

   public void setMarcador_economico_ocupacao(Integer marcador_economico_ocupacao) {
     this.marcador_economico_ocupacao = marcador_economico_ocupacao;
   }

   public String getMarcador_economico_ocupacaoDesc() {
     return this.marcador_economico_ocupacaoDesc;
   }

   public void setMarcador_economico_ocupacaoDesc(String marcador_economico_ocupacaoDesc) {
     this.marcador_economico_ocupacaoDesc = marcador_economico_ocupacaoDesc;
   }

   public String getSexo() {
     return this.sexo;
   }

   public void setSexo(String sexo) {
     this.sexo = sexo;
   }

   public String getFiliacao() {
     return this.filiacao;
   }

   public void setFiliacao(String filiacao) {
     this.filiacao = filiacao;
   }

   public Integer getTermo() {
     return this.termo;
   }

   public void setTermo(Integer termo) {
     this.termo = termo;
   }

   public String getTermoDesc() {
     return this.termoDesc;
   }

   public void setTermoDesc(String termoDesc) {
     this.termoDesc = termoDesc;
   }

   public Integer getFreguesia() {
     if (this.freguesia.intValue() == -1) {
       return null;
     }
     return this.freguesia;
   }

   public void setFreguesia(Integer freguesia) {
     this.freguesia = freguesia;
   }

   public String getFreguesiaDesc() {
     return this.freguesiaDesc;
   }

   public void setFreguesiaDesc(String freguesiaDesc) {
     this.freguesiaDesc = freguesiaDesc;
   }

   public Integer getComarca() {
     if (this.comarca.intValue() == -1) {
       return null;
     }
     return this.comarca;
   }

   public void setComarca(Integer comarca) {
     this.comarca = comarca;
   }

   public String getComarcaDesc() {
     return this.comarcaDesc;
   }

   public void setComarcaDesc(String comarcaDesc) {
     this.comarcaDesc = comarcaDesc;
   }

   public Integer getCapitania() {
     return this.capitania;
   }

   public void setCapitania(Integer capitania) {
     this.capitania = capitania;
   }

   public String getCapitaniaDesc() {
     return this.capitaniaDesc;
   }

   public void setCapitaniaDesc(String capitaniaDesc) {
     this.capitaniaDesc = capitaniaDesc;
   }

   public String getSearch() {
     if ("all".equals(this.search)) {
       return "%";
     }
     return this.search;
   }

   public void setSearch(String search) {
     this.search = search;
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

   public List<Map<String, Object>> getAllPessoas() {
     return this.allPessoas;
   }

   public void setAllPessoas(List<Map<String, Object>> allPessoas) {
     this.allPessoas = allPessoas;
   }

   public Pessoa allPessoas() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPessoas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pes.pk_pessoa id, \t   pes.nome, \t   coalesce(to_char(pes.idade,'9999'),' ') idade, \t   pes.marcador_economico_ocupacao, \t   coalesce((select mar.designacao \t      from app_hist.marcador_economico_ocupacao mar \t     where mar.pk_economico_ocupacao = pes.marcador_economico_ocupacao),'') marcador_economico_ocupacaoInfo, \t   pes.sexo, \t   coalesce(pes.filiacao,' ') filiacao, \t   pes.termo, \t   coalesce((select ter.nome_termo ||'('||cap.nome_capitania  ||')' \t      from app_hist.termo ter, app_hist.capitania cap \t     where ter.pk_termo = pes.termo \t       and ter.capitania = cap.pk_capitania ), ' ') termoInfo, \t   pes.freguesia, \t   coalesce((select fre.nome_freguesia \t      from app_hist.freguesia fre \t     where fre.pk_freguesia = pes.freguesia),'') freguesiaInfon, \t   pes.comarca, \t   coalesce((select com.nome_comarca ||case \t   \t                             when com.comarca_nome2 = null then '' \t   \t                             else '('||(select com2.nome_comarca \t   \t                                          from app_hist.comarca com2 \t   \t                                          where com.comarca_nome2 = com2.pk_comarca) ||')'\t   \t\t\t\t\t\t\t   end\t      from app_hist.comarca com \t     where com.pk_comarca = pes.comarca),'') comarcaInfo, \t   pes.capitania, \t   coalesce((select cap.nome_capitania \t      from app_hist.capitania cap \t     where cap.pk_capitania = pes.capitania),'') capitaniaInfo   from app_hist.pessoa pes  ORDER BY pes.nome             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q, 1);
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
         listPessoas.add(Cap);
       } 
       setComarca(Integer.valueOf(0));
       setFreguesia(Integer.valueOf(0));
       setMarcador_economico_ocupacao(Integer.valueOf(0));
       setTermo(Integer.valueOf(0));
       setAllPessoas(listPessoas);

       String qCount = "select count(*)  from app_hist.pessoa ";

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

   public Pessoa pessoa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Pessoa u = new Pessoa();
     try {
       c = Utils.getConnectionStock();
       String q = "select pes.pk_pessoa id, \t   pes.nome, \t   pes.idade idade, \t   pes.marcador_economico_ocupacao, \t   coalesce((select mar.designacao \t      from app_hist.marcador_economico_ocupacao mar \t     where mar.pk_economico_ocupacao = pes.marcador_economico_ocupacao),'') marcador_economico_ocupacaoInfo, \t   pes.sexo, \t   coalesce(pes.filiacao,' ') filiacao, \t   pes.termo, \t   coalesce((select ter.nome_termo ||'('||cap.nome_capitania  ||')' \t      from app_hist.termo ter, app_hist.capitania cap \t     where ter.pk_termo = pes.termo \t       and ter.capitania = cap.pk_capitania ),'') termoInfo, \t   pes.freguesia, \t   coalesce((select fre.nome_freguesia \t      from app_hist.freguesia fre \t     where fre.pk_freguesia = pes.freguesia),'') freguesiaInfon, \t   pes.comarca, \t   coalesce((select com.nome_comarca ||coalesce((select '('||com2.nome_comarca||')' \t   \t                                          from app_hist.comarca com2 \t   \t                                          where com.comarca_nome2 = com2.pk_comarca),'' ) \t      from app_hist.comarca com \t     where com.pk_comarca = pes.comarca),'') comarcaInfo, \t   pes.capitania, \t   coalesce((select cap.nome_capitania \t      from app_hist.capitania cap \t     where cap.pk_capitania = pes.capitania),'') capitaniaInfo   from app_hist.pessoa pes   where pk_pessoa = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setInt(1, getPk_pessoa());
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setPk_pessoa(rs.getInt(1));
         u.setNome(rs.getString(2));
         u.setIdade(Integer.valueOf(rs.getInt(3)));
         u.setMarcador_economico_ocupacao(Integer.valueOf(rs.getInt(4)));
         u.setMarcador_economico_ocupacaoDesc(rs.getString(5));
         u.setSexo(rs.getString(6));
         u.setFiliacao(rs.getString(7));
         u.setTermo(Integer.valueOf(rs.getInt(8)));
         u.setTermoDesc(rs.getString(9));
         u.setFreguesia(Integer.valueOf(rs.getInt(10)));
         u.setFreguesiaDesc(rs.getString(11));
         u.setComarca(Integer.valueOf(rs.getInt(12)));
         u.setComarcaDesc(rs.getString(13));
         u.setCapitania(Integer.valueOf(rs.getInt(14)));
         u.setCapitaniaDesc(rs.getString(15));
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
     try {
       c = Utils.getConnectionStock();

       String q = "select pes.pk_pessoa id, \t   pes.nome, \t   coalesce(to_char(pes.idade,'9999'),' ') idade, \t   pes.marcador_economico_ocupacao, \t   coalesce((select mar.designacao \t      from app_hist.marcador_economico_ocupacao mar \t     where mar.pk_economico_ocupacao = pes.marcador_economico_ocupacao),' ') marecoocupInfo, \t   pes.sexo, \t   coalesce(pes.filiacao,' ') filiacao, \t   pes.termo, \t   coalesce((select ter.nome_termo ||'('||cap.nome_capitania  ||')' \t      from app_hist.termo ter, app_hist.capitania cap \t     where ter.pk_termo = pes.termo \t       and ter.capitania = cap.pk_capitania ),'') termoInfo, \t   pes.freguesia, \t   coalesce((select fre.nome_freguesia \t      from app_hist.freguesia fre \t     where fre.pk_freguesia = pes.freguesia),'') freguesiaInfon, \t   pes.comarca, \t   coalesce((select com.nome_comarca ||case \t   \t                             when com.comarca_nome2 = null then '' \t   \t                             else '('||(select com2.nome_comarca \t   \t                                          from app_hist.comarca com2 \t   \t                                          where com.comarca_nome2 = com2.pk_comarca) ||')'\t   \t\t\t\t\t\t\t   end\t      from app_hist.comarca com \t     where com.pk_comarca = pes.comarca),'') comarcaInfo, \t   pes.capitania, \t   coalesce((select cap.nome_capitania \t      from app_hist.capitania cap \t     where cap.pk_capitania = pes.capitania),'') capitaniaInfo   from app_hist.pessoa pes   where (lower(pes.nome) like '%'||lower(?)||'%'      or  pes.idade::varchar(255) like '%'|| lower(?) || '%'      or  (select lower(mar.designacao) \t        from app_hist.marcador_economico_ocupacao mar \t       where mar.pk_economico_ocupacao = pes.marcador_economico_ocupacao) like '%'||lower(?)||'%' \t or  lower(pes.filiacao) like '%'||lower(?)||'%' \t or  (select lower(ter.nome_termo ||'('||cap.nome_capitania  ||')' ) \t      from app_hist.termo ter, app_hist.capitania cap \t     where ter.pk_termo = pes.termo \t       and ter.capitania = cap.pk_capitania ) like '%'||lower(?)||'%' \t or (select lower(fre.nome_freguesia) \t      from app_hist.freguesia fre \t     where fre.pk_freguesia = pes.freguesia) like '%'||lower(?)||'%' \t or  (select lower(com.nome_comarca ||case \t   \t                             when com.comarca_nome2 = null then '' \t   \t                             else '('||(select com2.nome_comarca \t   \t                                          from app_hist.comarca com2 \t   \t                                          where com.comarca_nome2 = com2.pk_comarca) ||')' \t   \t\t\t\t\t\t\t   end) \t      from app_hist.comarca com \t     where com.pk_comarca = pes.comarca) like '%'||lower(?)||'%'  or coalesce((select lower(cap.nome_capitania) \t              from app_hist.capitania cap \t             where cap.pk_capitania = pes.capitania),'')like '%'||lower(?)||'%' )  order by pes.nome ";

       ps = c.prepareStatement(q, 1);
       ps.setString(1, getSearch());
       ps.setString(2, getSearch());
       ps.setString(3, getSearch());
       ps.setString(4, getSearch());
       ps.setString(5, getSearch());
       ps.setString(6, getSearch());
       ps.setString(7, getSearch());
       ps.setString(8, getSearch());
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

   public Integer countPessoa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Integer u = Integer.valueOf(0);
     try {
       c = Utils.getConnectionStock();
       String q = "select count(*) numPes  from app_hist.pessoa ";

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

   public Pessoa consultaPessoa() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listPessoas = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select pes.pk_pessoa id,          pes.nome,          coalesce(pes.idade||'', ' ') idade,          coalesce(pes.sexo,'M/F') sexo,        (select count(*)            from app_hist.relac_requerente_provocacao rrp           where rrp.requerente = pes.pk_pessoa  ) ProvReq,        (select count(*)            from app_hist.relac_pcitadas_provocacao rpp          where rpp.p_citadas = pes.pk_pessoa  ) ProvPesCita,        (select count(*)           from app_hist.relac_remetente_provocacao rrp           where rrp.remetente = pes.pk_pessoa  ) ProvRem,        (select count(*)           from app_hist.relac_requerente_mandado rrm          where rrm.requerente = pes.pk_pessoa  ) ManReq,        (select count(*)           from app_hist.relac_pcitadas_mandado rpm          where rpm.p_citadas = pes.pk_pessoa  ) ManPesCita,        (select count(*)           from app_hist.relac_requerente_consulta rrc          where rrc.requerente = pes.pk_pessoa  ) ConReq,        (select count(*)           from app_hist.relac_pcitadas_consulta rpc          where rpc.p_citadas = pes.pk_pessoa  ) ConPesCita,        (select count(*)            from app_hist.relac_requerente_ultramar rru          where rru.requerente = pes.pk_pessoa  ) UltraReq,        (select count(*)           from app_hist.relac_pcitadas_ultramar rpu          where rpu.p_citadas = pes.pk_pessoa  ) UltraPesCita,        (select count(*)           from app_hist.relac_requerente_resposta rrr          where rrr.requerente = pes.pk_pessoa  ) ResReq,        (select count(*)           from app_hist.relac_instado_resposta rpr          where rpr.instado = pes.pk_pessoa  ) ResPesCita,        (select count(*)           from app_hist.relac_destinatario_resposta rdp          where rdp.destinatario = pes.pk_pessoa  ) ResDest    from app_hist.pessoa pes     ORDER BY pes.nome             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q, 1);
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
         listPessoas.add(Cap);
       } 
       setComarca(Integer.valueOf(0));
       setFreguesia(Integer.valueOf(0));
       setMarcador_economico_ocupacao(Integer.valueOf(0));
       setTermo(Integer.valueOf(0));
       setAllPessoas(listPessoas);

       String qCount = "select count(*)  from app_hist.pessoa ";

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

   public String create() throws Exception {
     int toRet = 0;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "INSERT INTO app_hist.pessoa ( nome, idade, marcador_economico_ocupacao,                                sexo, filiacao, termo, freguesia, comarca, capitania)  VALUES( ? , ?, ?, ?, ?, ?, ?, ?, ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome);
       ps.setObject(2, this.idade);
       ps.setObject(3, this.marcador_economico_ocupacao);
       ps.setObject(4, this.sexo);
       ps.setObject(5, this.filiacao);
       ps.setObject(6, getTermo());
       ps.setObject(7, this.freguesia);
       ps.setObject(8, this.comarca);
       ps.setObject(9, this.capitania);

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
       String q = "UPDATE app_hist.pessoa SET  nome = ?,  idade = ?,  marcador_economico_ocupacao = ?,  sexo = ?,  filiacao = ?,  termo = ?,  freguesia = ?,  comarca = ?,  capitania = ?  where pk_pessoa = ? ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.nome);
       ps.setObject(2, this.idade);
       ps.setObject(3, this.marcador_economico_ocupacao);
       ps.setObject(4, this.sexo);
       ps.setObject(5, this.filiacao);
       ps.setObject(6, this.termo);
       ps.setObject(7, this.freguesia);
       ps.setObject(8, this.comarca);
       ps.setObject(9, this.capitania);
       ps.setObject(10, Integer.valueOf(this.pk_pessoa));
       ps.executeUpdate();

       updatedId = this.pk_pessoa;
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
       String q = "with d as (       delete from app_hist.relac_marcador_socio_juridico_pessoa rm           where rm.pessoa  = ?      ),   c as (       delete from app_hist.relac_oficio_pessoa ror           where ror.pessoa  = ?      ),   e as (       delete from app_hist.relac_marcador_economico_ocupacao_pessoa rmeop           where rmeop.pessoa  = ?      ) delete from app_hist.pessoa pes     where pes.pk_pessoa  = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.pk_pessoa));
       ps.setObject(2, Integer.valueOf(this.pk_pessoa));
       ps.setObject(3, Integer.valueOf(this.pk_pessoa));
       ps.setObject(4, Integer.valueOf(this.pk_pessoa));

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
     return "" + this.pk_pessoa;
   }
 }