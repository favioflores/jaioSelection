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
 
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
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
 
            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            
            /* EVADIR OBJETOS INNECESARIOS */
            
            if(validaUri(req)){
                
                String reqURI = req.getRequestURI();
                if ( reqURI.indexOf("/ui/iniciar.jsf") >= 0){
                    chain.doFilter(request, response);
                    log.debug("Sin validacion en login");
                }else if (ses != null && 
                          ses.getAttribute("usuarioInfo") != null || 
                          reqURI.indexOf("/public/") >= 0 || 
                          reqURI.contains("javax.faces.resource") ){
                    log.debug("Session valida");
                    chain.doFilter(request, response);
                }else{   // user didn't log in but asking for a page that is not allowed so take user to login page
                    log.debug("Session invalida");
                    res.sendRedirect(req.getContextPath() + "/ui/sesionExpirada.jsf");  // Anonymous user. Redirect to login page
                }
                
            }else{
                chain.doFilter(request, response);
            }
            
      }
     catch(Throwable t) {
         log.error(t);
     }
    } //doFilter
 
    @Override
    public void destroy() {
         
    }
    
    public boolean validaUri(HttpServletRequest req){
 
        String strUri = req.getServletPath();
        
        if(strUri.endsWith(Constantes.STR_CSS) ||
           strUri.endsWith(Constantes.STR_GIF) ||
           strUri.endsWith(Constantes.STR_PNG) ||
           strUri.endsWith(Constantes.STR_JPG) ||
           strUri.endsWith(Constantes.STR_HTM) ||
           strUri.endsWith(Constantes.STR_JS)  ||
           strUri.endsWith(Constantes.STR_SWF) ||
           strUri.indexOf("/ui/iniciar") >= 0 ||
           strUri.indexOf("/ui/clave") >= 0 ||
           strUri.indexOf("/ui/sesionExpirada") >= 0){
            
            return false;
            
        }
        
        return true;
    }
}