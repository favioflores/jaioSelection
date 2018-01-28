/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaio.selection.initial;

import jaio.selection.util.MailSender;
import java.io.Serializable;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Favio
 */
public class MailSenderInit extends HttpServlet implements Serializable{
    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException{
        
        /* Iniciar Thread */
        
        MailSender sender = new MailSender();
        sender.start();
        
    }
    
}
