package pt.ual.auth.resource;

import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pt.ual.auth.dto.AuthLoginRequestDto;
import pt.ual.auth.dto.AuthUserDto;
import pt.ual.auth.service.AuthService;

@Path("auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

  @Inject
  private AuthService authService;

  @POST
  @Path("login")
  public Response login(@Valid AuthLoginRequestDto request, @Context HttpServletRequest httpRequest) throws Exception {
    Optional<AuthUserDto> authenticated = this.authService.authenticate(request);
    if (!authenticated.isPresent()) {
      return Response.status(Response.Status.UNAUTHORIZED)
              .entity("{\"code\":\"AUTH_INVALID_CREDENTIALS\",\"message\":\"Credenciais inválidas.\"}")
              .build();
    }

    HttpSession existingSession = httpRequest.getSession(false);
    if (existingSession != null) {
      existingSession.invalidate();
    }

    HttpSession session = httpRequest.getSession(true);
    this.authService.establishSession(session, authenticated.get());

    return Response.ok(authenticated.get()).build();
  }

  @GET
  @Path("me")
  public Response me(@Context HttpServletRequest httpRequest) {
    HttpSession session = httpRequest.getSession(false);
    AuthUserDto user = this.authService.getAuthenticatedUser(session);

    if (user == null) {
      return Response.status(Response.Status.UNAUTHORIZED)
              .entity("{\"code\":\"AUTH_NOT_AUTHENTICATED\",\"message\":\"Sessão inexistente ou expirada.\"}")
              .build();
    }

    return Response.ok(user).build();
  }

  @POST
  @Path("logout")
  public Response logout(@Context HttpServletRequest httpRequest) {
    HttpSession session = httpRequest.getSession(false);
    this.authService.logout(session);
    return Response.noContent().build();
  }
}