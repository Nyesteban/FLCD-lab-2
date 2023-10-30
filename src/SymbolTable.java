public class SymbolTable {
    HashTable<Object> symTable;

    public SymbolTable() {
        symTable = new HashTable<Object>(127);
    }

    public SymbolTable(Integer size) {
        symTable = new HashTable<Object>(size);
    }

    public boolean add(Object element)
    {
        return symTable.add(element);
    }

    public Object getByPos(Pair pos)
    {
        try {
            return symTable.getByPos(pos);
        }
        catch(Exception e)
        {
            System.out.println("Invalid pos");
        }
        return null;
    }

    public Pair get(Object element)
    {
        return symTable.get(element);
    }

    @Override
    public String toString() {
        return symTable.toString();
    }
}
