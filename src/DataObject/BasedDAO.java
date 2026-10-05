
package DataObject;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author chanh
 */
public abstract class BasedDAO <V> {

    protected String FILE_PATH;
    protected BasedDAO(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

  

    protected abstract void add(V entity);

    protected abstract ArrayList<V> getAll();
    
    protected abstract boolean save();
    
    protected abstract void load();
}