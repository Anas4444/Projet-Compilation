import java.util.ArrayList;
import java.util.Stack;

public class Semantique {
    public static ArrayList<UniteLexicale> input = new ArrayList<>();
    public static ArrayList<UniteLexicale> ids = new ArrayList<>();
    public static Stack<Type> types = new Stack<>();
    public static String[] errors = new String[] {"Type mismatch", "Undeclared Variable", "Erreur Syntaxique"};

    public Semantique() {}
    public static void setInputType(ArrayList<UniteLexicale> inp) {
        for (int i = 0; i<inp.size(); i++) {
            if (inp.get(i).categorie.toString().equals("type")) {
                if (inp.get(i).lexeme.equals("int")) inp.get(i+1).setType(Type.Int);
                else if (inp.get(i).lexeme.equals("real")) inp.get(i+1).setType(Type.Float);
                else if (inp.get(i).lexeme.equals("char")) inp.get(i+1).setType(Type.Char);
                else if (inp.get(i).lexeme.equals("bool")) inp.get(i+1).setType(Type.Boolean);
                ids.add(inp.get(i+1));
            }
            if (inp.get(i).categorie.toString().equals("nb")) inp.get(i).setType(Type.Int);
            if (inp.get(i).categorie.toString().equals("litteral")) inp.get(i).setType(Type.Char);
            if (inp.get(i).categorie.toString().equals("vrai") || inp.get(i).categorie.toString().equals("faux")) inp.get(i).setType(Type.Boolean);
        }
        for (UniteLexicale in : inp) input.add(in);
        setIds();
    }

    public static void sameId(int index) {
        for (UniteLexicale id : ids) {
            if (id.lexeme.equals(input.get(index).lexeme)) input.get(index).setType(id.type);
        }
    }

    public static void setIds() {
        for (int i=0; i<input.size()-1; i++) {
            if (input.get(i).categorie.toString().equals("id")) {
                sameId(i);
            }
        }
    }

    public static int addType(ArrayList<Paire<UniteSymbole, Integer>> pile, String regle) {
        if (regle.equals("PE -> nb")) types.add(Type.Int);
        else if (regle.equals("PE -> nb v nb")) types.add(Type.Float);
        else if (regle.equals("PE -> vrai") || regle.equals("PE -> faux")) types.add(Type.Boolean);
        else if (regle.equals("PE -> litteral")) types.add(Type.Char);
        else if (regle.equals("I -> id oppaff ORE")) {
            if (pile.get(pile.size()-3).v1.sameType(Type.Vide)) {
                return 3;
            }
            if (pile.get(pile.size()-3).v1.sameType(types.peek())) {
                types.clear();
                return 1;
            }
            return 2;
        }
        else if (regle.equals("I -> pour id dans nb point nb faire LI fpour")) {
            if (pile.get(pile.size()-8).v1.sameType(Type.Vide)) return 3;
        }
        else if (regle.equals("I -> lire id")) {
            if (pile.get(pile.size()-1).v1.sameType(Type.Vide)) return 3;
        }
        else if (regle.equals("PE -> id")) {
            if (pile.get(pile.size()-1).v1.sameType(Type.Vide)) return 3;
            types.add(pile.get(pile.size()-1).v1.type);
        }
        else if (regle.equals("PE opprel PE")) {
            if (!pile.get(pile.size()-3).v1.sameType(types.peek())) return 2;
        }
        return 1;
    }
}
