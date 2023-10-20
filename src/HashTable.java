import java.util.ArrayList;

public class HashTable<T> {
    private Integer size;
    private ArrayList<ArrayList<T>> table;

    public HashTable(Integer size)
    {
        this.size = size;
        this.table = new ArrayList<>();
        for(int i=0; i < size; i++)
        {
            this.table.add(new ArrayList<>());
        }
    }

    public Integer hash(T element)
    {
        var value = element.hashCode() % size;
        return Math.abs(value);
    }

    public Pair get(T element)
    {
        var hashValue = hash(element);
        if(!table.get(hashValue).isEmpty())
        {
            var list = this.table.get(hashValue);
            for(int i=0; i < list.size(); i++)
            {
                if(list.get(i).equals(element))
                {
                    return new Pair(hashValue, i);
                }
            }
        }
        return null;
    }

    public T getByPos(Pair pos) throws Exception {
        if(this.table.size() <= pos.getFirst() || this.table.size() <= pos.getSecond())
        {
            throw new Exception();
        }
        return this.table.get(pos.getFirst()).get(pos.getSecond());
    }

    public boolean add(T element)
    {
        if(this.get(element)!=null)
            return false;
        var hashValue = hash(element);
        var list = this.table.get(hashValue);
        list.add(element);
        return true;
    }
}
