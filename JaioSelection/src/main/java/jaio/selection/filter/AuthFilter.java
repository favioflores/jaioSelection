package jaio.selection.filter;

import jaio.selection.util.Constantes;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    private static Log log = LogFactory.getLog(AuthFilter.class);

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);

            if (validaUri(req)) {

                String reqURI = req.getRequestURI();
                if (reqURI.indexOf("/login.jsf") >= 0) {
                    chain.doFilter(request, response);
                    log.debug("Sin validacion en login");
                } else if (ses != null
                        && ses.getAttribute(Constantes.SESSION_USUARIO) != null
                        || reqURI.indexOf("/public/") >= 0
                        || reqURI.contains("javax.faces.resource")) {
                    log.debug("Session valida");
                    chain.doFilter(request, response);
                } else {
                    log.debug("Session invalida");
                    res.sendRedirect(req.getContextPath() + "/expiro.jsf");
                }

            } else {
                chain.doFilter(request, response);
            }

        } catch (Throwable t) {
            log.error(t);
        }
    } //doFilter

    @Override
    public void destroy() {

    }

    public boolean validaUri(HttpServletRequest req) {

        String strUri = req.getServletPath();

        if (strUri.endsWith(Constantes.STR_CSS)
                || strUri.endsWith(Constantes.STR_GIF)
                || strUri.endsWith(Constantes.STR_PNG)
                || strUri.endsWith(Constantes.STR_JPG)
                || strUri.endsWith(Constantes.STR_HTM)
                || strUri.endsWith(Constantes.STR_JS)
                || strUri.endsWith(Constantes.STR_SWF)
                || strUri.indexOf("/login") >= 0
                || strUri.indexOf("/clave") >= 0
                || strUri.indexOf("/error") >= 0
                || strUri.indexOf("/404") >= 0
                || strUri.indexOf("/access") >= 0) {

            return false;

        }

        return true;
    }
}
