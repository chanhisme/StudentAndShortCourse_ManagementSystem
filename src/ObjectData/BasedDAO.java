/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjectData;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author chanh
 */
public abstract class BasedDAO <V> {

    protected Map <String, V> management;
    protected String FILE_PATH;
    protected BasedDAO(Map<String, V> management, String FILE_PATH) {
        this.management = management;
        this.FILE_PATH = FILE_PATH;
    }

  

    protected abstract void add(V entity);

    protected abstract ArrayList<V> getAll();

    protected abstract V findById(String id);
    
    protected abstract void save();
    
    protected abstract void load();
}