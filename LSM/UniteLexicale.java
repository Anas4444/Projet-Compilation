//package analyselexical;

public class UniteLexicale {
    public Categorie categorie;
    public String lexeme;
    public Type type = Type.Vide;

    public UniteLexicale(Categorie categorie, String lexeme) {
        this.categorie=categorie;
        this.lexeme = lexeme;
    }

    public UniteLexicale(UniteLexicale unite) {
        this.categorie = unite.categorie;
        this.lexeme = unite.lexeme;
        this.type = unite.type;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setType(Type t) {
        this.type = t;
    }

    public String toString() {
        if (this.type != Type.Vide)
            return "<"+categorie.toString()+","+lexeme+", "+type+">";
        return "<"+categorie.toString()+","+lexeme+">";
    }
}