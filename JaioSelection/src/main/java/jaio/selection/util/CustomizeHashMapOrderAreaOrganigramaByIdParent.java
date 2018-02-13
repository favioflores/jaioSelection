package jaio.selection.util;

import jaio.selection.bean.AreaOrganigramaBean;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

public class CustomizeHashMapOrderAreaOrganigramaByIdParent implements Serializable, Comparator<Map.Entry<String, AreaOrganigramaBean>> {

    @Override
    public int compare(Map.Entry<String, AreaOrganigramaBean> o1, Map.Entry<String, AreaOrganigramaBean> o2) {
        return -(o2.getValue().getId_parent()==null?Constantes.strVacio:o2.getValue().getId_parent()).compareTo(o1.getValue().getId_parent()==null?Constantes.strVacio:o1.getValue().getId_parent());
    }
    
}
