public class Main {
    public static void main(String[] args)
    {
        SymbolTable symTable = new SymbolTable();
        Integer a=10;
        String s="yes";
        symTable.add(a);
        symTable.add(s);
        System.out.println(symTable.get(a));
        var apos = symTable.get(a);
        System.out.println(symTable.getByPos(apos));
        System.out.println(symTable.getByPos(apos).getClass().getName());
        System.out.println(symTable.get(s));
        var spos = symTable.get(s);
        System.out.println(symTable.getByPos(spos));
        System.out.println(symTable.getByPos(spos).getClass().getName());
        System.out.println(symTable.get(20));
        System.out.println(symTable.getByPos(new Pair(100,0)));
    }
}