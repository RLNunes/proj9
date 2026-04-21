import { AuthUser } from './auth-user.model';

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  user: AuthUser;
  accessToken?: string;
  refreshToken?: string;
}
