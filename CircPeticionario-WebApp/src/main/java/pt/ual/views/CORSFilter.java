package pt.ual.views;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    /**
     * CORS configurado apenas para desenvolvimento local.
     * <p>
     * Em ambientes integrados por Nginx / reverse proxy, esta política deve ser
     * revista ou movida para a camada de proxy, conforme os origins reais.
     */
    private static final Set<String> ALLOWED_ORIGINS = new HashSet<>(Arrays.asList(
            "http://localhost:4200",
            "http://localhost"
    ));

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        String origin = requestContext.getHeaderString("Origin");

        if (origin != null && ALLOWED_ORIGINS.contains(origin)) {
            responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", origin);
            responseContext.getHeaders().putSingle("Vary", "Origin");
            responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        }
    }
}