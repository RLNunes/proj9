export type HeaderDomain = 'home' | 'admin' | 'consulta';

export interface HeaderNavItem {
  label: string;
  link: string;
  children?: HeaderNavItem[];
}

export interface HeaderDomainConfig {
  domain: HeaderDomain;
  logo: string;
  label: string;
  nav: HeaderNavItem[];
}

export interface HeaderConfig extends HeaderDomainConfig {
  displayNav: boolean;
  displaySearchbar: boolean;
}
