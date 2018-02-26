package jaio.selection.util;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.orm.Elemento;
import net.sf.ehcache.Element;

public class EHCacheManager implements Serializable {

    private static Log log = LogFactory.getLog(EHCacheManager.class);

    private static final CacheManager cacheManager;
    private static Ehcache elementosCache;

    static {

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        InputStream resourceAsStream = contextClassLoader.getResourceAsStream("ehcache.xml");

        cacheManager = CacheManager.create(resourceAsStream);

    }

    public EHCacheManager() {
        elementosCache = cacheManager.getEhcache("elementosCache");
    }

    public void agregarElemento(Elemento objElemento) {
        Element element = new Element(objElemento.getId(), objElemento);
        elementosCache.put(element);
    }

    public static String obtenerDescripcionElemento(Integer intIdElemento) {

        try {
            Elemento objElement = (Elemento) elementosCache.get(intIdElemento).getValue();
            return objElement.getDescripcion();
        } catch (Exception e) {
            log.error(e);
        }

        return Constantes.strVacio;
    }

    public static List<Elemento> obtenerElementosPorDefinicion(Integer intIdDefinicion) {

        List<Elemento> lstElementos = new ArrayList();
        try {

            List lst = elementosCache.getKeys();

            Iterator itLst = lst.iterator();

            while (itLst.hasNext()) {
                Elemento objElemento = (Elemento) elementosCache.get(itLst.next()).getValue();

                if (intIdDefinicion.equals(objElemento.getDefinicion().getId())) {
                    lstElementos.add(objElemento);
                }

            }

            return lstElementos;

        } catch (Exception e) {
            log.error(e);
            return null;
        }

    }

}
