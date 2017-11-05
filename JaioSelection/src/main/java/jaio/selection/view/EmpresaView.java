package jaio.selection.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "empresaView")
@Scope("request")
@Component("empresaView")
public class EmpresaView extends BaseView implements Serializable {

	private static Log log = LogFactory.getLog(EmpresaView.class);

	private static final long serialVersionUID = -1L;



}
