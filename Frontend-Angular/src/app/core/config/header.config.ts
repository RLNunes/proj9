import {
  HeaderDomain,
  HeaderDomainConfig,
  HeaderNavItem,
} from '../models/header-config.model';

const HOME_HEADER_NAV: HeaderNavItem[] = [
  { label: 'Início', link: '/home' },
  { label: 'Provocação', link: '/home/provocacao' },
  { label: 'Mandado', link: '/home/mandado' },
  { label: 'Consulta', link: '/home/consulta' },
  { label: 'Ultramar', link: '/home/ultramar' },
  { label: 'Respostas', link: '/home/respostas' },
];

const ADMIN_HEADER_NAV: HeaderNavItem[] = [
  { label: 'Início Admin', link: '/admin' },
  {
    label: 'Toponímia',
    link: '/admin/toponimia',
    children: [
      { label: 'Termo', link: '/admin/toponimia/termo' },
      { label: 'Freguesia', link: '/admin/toponimia/freguesia' },
      { label: 'Comarca', link: '/admin/toponimia/comarca' },
      { label: 'Capitania', link: '/admin/toponimia/capitania' },
    ],
  },
  { label: 'Registo', link: '/admin/registo' },
  { label: 'Referência Documental', link: '/admin/referencia-documental' },
  {
    label: 'Marcadores',
    link: '/admin/marcadores',
    children: [
      { label: 'Económico/Ocupação', link: '/admin/marcadores/economico-ocupacao' },
      { label: 'Sócio Jurídico', link: '/admin/marcadores/socio-juridico' },
    ],
  },
  {
    label: 'Ofícios/Títulos',
    link: '/admin/oficios-titulos',
    children: [
      { label: 'Ofícios/Títulos', link: '/admin/oficios-titulos/oficios-titulos' },
      { label: 'Agregadores', link: '/admin/oficios-titulos/agregadores' },
    ],
  },
  {
    label: 'Secretário/Conselheiro',
    link: '/admin/secretario-conselheiro',
    children: [
      {
        label: 'Secretário/Conselheiro',
        link: '/admin/secretario-conselheiro/secretario-conselheiro',
      },
      { label: 'Agregadores', link: '/admin/secretario-conselheiro/agregadores' },
    ],
  },
  { label: 'Pessoa', link: '/admin/pessoa' },
  {
    label: 'Temas',
    link: '/admin/temas',
    children: [
      { label: 'Tema', link: '/admin/temas/tema' },
      { label: 'Agregadores', link: '/admin/temas/agregadores' },
    ],
  },
  { label: 'Palavra-Chave', link: '/admin/palavra-chave' },
  { label: 'Utilizador', link: '/admin/utilizador' },
];

const CONSULTA_HEADER_NAV: HeaderNavItem[] = [
  { label: 'Home', link: '/consulta' },
  { label: 'Pessoa', link: '/consulta/pessoa' },
  { label: 'Tema', link: '/consulta/tema' },
  { label: 'Palavra-Chave', link: '/consulta/palavra-chave' },
];

const HOME_HEADER_CONFIG: HeaderDomainConfig = {
  domain: 'home',
  logo: 'assets/img/branding/marca-circuitos-oceanicos-logo-selo-vermelho.png',
  label: 'Página Inicial',
  nav: HOME_HEADER_NAV,
};

const ADMIN_HEADER_CONFIG: HeaderDomainConfig = {
  domain: 'admin',
  logo: 'assets/img/branding/marca-circuitos-oceanicos-logo-selo-azul.png',
  label: 'Administração',
  nav: ADMIN_HEADER_NAV,
};

const CONSULTA_HEADER_CONFIG: HeaderDomainConfig = {
  domain: 'consulta',
  logo: 'assets/img/branding/marca-circuitos-oceanicos-logo-selo-verde.png',
  label: 'Consulta',
  nav: CONSULTA_HEADER_NAV,
};

export const HEADER_CONFIG: Record<HeaderDomain, HeaderDomainConfig> = {
  home: HOME_HEADER_CONFIG,
  admin: ADMIN_HEADER_CONFIG,
  consulta: CONSULTA_HEADER_CONFIG,
};
