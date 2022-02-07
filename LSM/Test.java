import java.util.ArrayList;

public class Test
{
    public static void main(String args[])
    {   
        ArrayList<UniteLexicale> table = new ArrayList<>();
        Scanner code = new Scanner(args[0]);
        UniteLexicale u;
        while( (u=code.lexemeSuivant())!= null){
            table.add(u);
            if(u.getCategorie()==Categorie.fin){
                break;
            }
        }
        for (UniteLexicale v : table) System.out.println(v);
        System.out.println("\n");
        Parser p = new Parser(table);
        p.parse();
        p.showAll();
        if (p.listAction.get(p.listAction.size()-1).v2==-2)
            new genCode("code.cpp");
    }
}