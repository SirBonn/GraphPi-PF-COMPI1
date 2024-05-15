package srbn.graphpi.BackEnd.DomainObjs;

import java.io.Serializable;
import java.util.HashMap;

public class SymTable implements Serializable {

    private HashMap<String, Object> symTable;

    public SymTable() {
        symTable = new HashMap<>();
    }

    public void addVar(String name, Object value) {
        symTable.put(name, value);
    }

    public Object getVar(String name) {
        return symTable.get(name);
    }

    public void removeVar(String name) {
        symTable.remove(name);
    }

    public void clear() {
        symTable.clear();
    }

    public boolean containsVar(String name) {
        return symTable.containsKey(name);
    }

    public void updateVar(String name, Object value) {
        symTable.replace(name, value);
    }

    public HashMap<String, Object> getSymTable() {
        return symTable;
    }

}
