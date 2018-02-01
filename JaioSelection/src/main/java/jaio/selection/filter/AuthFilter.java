package jaio.selection.filter;

import jaio.selection.util.Constantes;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthFilter implements Filter, Serializable {

    private static Log log = LogFactory.getLog(AuthFilter.class);

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
                if (reqURI.contains("/login.jsf")) {
                    chain.doFilter(request, response);
                    log.debug("Sin validacion en login");
                } else if (ses != null
                        && ses.getAttribute(Constantes.SESSION_USUARIO) != null
                        || reqURI.contains("/public/")
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

        } catch (IOException | ServletException t) {
            log.error(t);
        }
    } //doFilter

    @Override
    public void destroy() {

    }

    public boolean validaUri(HttpServletRequest req) {

        String strUri = req.getServletPath();

        return !(strUri.endsWith(Constantes.STR_CSS)
                || strUri.endsWith(Constantes.STR_GIF)
                || strUri.endsWith(Constantes.STR_PNG)
                || strUri.endsWith(Constantes.STR_JPG)
                || strUri.endsWith(Constantes.STR_HTM)
                || strUri.endsWith(Constantes.STR_JS)
                || strUri.endsWith(Constantes.STR_SWF)
                || strUri.contains("/login")
                || strUri.contains("/clave")
                || strUri.contains("/error")
                || strUri.contains("/404")
                || strUri.contains("/expiro"));
    }
}
