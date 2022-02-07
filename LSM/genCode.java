import java.io.FileWriter;
import java.io.IOException;

public class genCode {
    
    public genCode(String file) {
        try {
            FileWriter fWriter = new FileWriter("C:\\Users\\elabe\\OneDrive\\Desktop\\Work\\Projet Compilation\\" + file);
            String code = getDebut();
            for (int i=0; i<Semantique.input.size(); i++) {
                code += lexicalToCode(i);
            } 
            fWriter.write(code);
            fWriter.close();

            System.out.println("File is created successfully with the content.");
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
    
    public String getDebut() {
        return "#include <iostream>\nusing namespace std;\n\nint main() {\n\t";
    }

    public String getType(UniteLexicale u) {
        if (u.lexeme.equals("int")) return "int ";
        else if (u.lexeme.equals("real")) return "float ";
        else if (u.lexeme.equals("char")) return "string ";
        return "bool ";
    }

    public String getLE(UniteLexicale u) {
        if (u.lexeme.equals("ecrire")) return "cout << ";
        return "cin >> ";
    }

    public String getOPPRel(UniteLexicale u) {
        if (u.lexeme.equals("<>")) return " != ";
        else if (u.lexeme.equals("=")) return " == ";
        return " " + u.lexeme + " ";
    }

    public String getOPPAr(UniteLexicale u) {
        if (u.lexeme.equals("//")) return "/ ";
        return u.lexeme +  " ";
    }

    public String getOPPLg(UniteLexicale u) {
        if (u.lexeme.equals("et")) return " && ";
        else if (u.lexeme.equals("ou")) return " || ";
        else if (u.lexeme.equals("non")) return " !";
        else if (u.lexeme.equals("vrai")) return "true";
        return "false";
    }

    public String getCondition(UniteLexicale u) {
        if (u.categorie.toString().equals("si")) return "if (";
        else if (u.categorie.toString().equals("alors")) return ") {\n\t";
        return "else ";
    }

    public String getNumber(int index) {
        if (Semantique.input.get(index+1).lexeme.equals(",")) return Semantique.input.get(index).lexeme;
        else if (Semantique.input.get(index+1).lexeme.equals(".") || Semantique.input.get(index-1).lexeme.equals(".")) return "";
        return Semantique.input.get(index).lexeme + " ";
    }

    public String getPv(int index) {
        if (Semantique.input.get(index-1).lexeme.equals("fsi") || Semantique.input.get(index-1).lexeme.equals("fpour")) return "";
        return ";\n\t";
    }

    public String getLoop(int index) {
        if (Semantique.input.get(index).lexeme.equals("pour")) return "for (";
        if (Semantique.input.get(index).lexeme.equals("dans")) return " = " + Semantique.input.get(index+1).lexeme + "; " + Semantique.input.get(index-1).lexeme + " <= " + Semantique.input.get(index+3).lexeme;
        if (Semantique.input.get(index).lexeme.equals("faire")) return "; " + Semantique.input.get(index-5).lexeme + "++) {\n\t";
        return "";
    }

    public String lexicalToCode(int index) {
        if (Semantique.input.get(index).categorie.toString().equals("type")) return getType(Semantique.input.get(index));
        else if (Semantique.input.get(index).categorie.toString().equals("lire") || Semantique.input.get(index).categorie.toString().equals("ecrire")) return getLE(Semantique.input.get(index));
        else if (Semantique.input.get(index).categorie.toString().equals("pour") || Semantique.input.get(index).categorie.toString().equals("dans") || Semantique.input.get(index).categorie.toString().equals("point") || Semantique.input.get(index).categorie.toString().equals("faire")) return getLoop(index);
        else if (Semantique.input.get(index).categorie.toString().equals("id")) return Semantique.input.get(index).lexeme + " ";
        else if (Semantique.input.get(index).categorie.toString().equals("po")) return "( ";
        else if (Semantique.input.get(index).categorie.toString().equals("pf")) return ") "; 
        else if (Semantique.input.get(index).categorie.toString().equals("si") || Semantique.input.get(index).categorie.toString().equals("alors") || Semantique.input.get(index).categorie.toString().equals("sinon")) return getCondition(Semantique.input.get(index));
        else if (Semantique.input.get(index).categorie.toString().equals("nb")) return getNumber(index);
        else if (Semantique.input.get(index).categorie.toString().equals("litteral")) return Semantique.input.get(index).lexeme;
        else if (Semantique.input.get(index).categorie.toString().equals("v")) return ",";
        else if (Semantique.input.get(index).categorie.toString().equals("pv")) return getPv(index);
        else if (Semantique.input.get(index).categorie.toString().equals("opprel")) return getOPPRel(Semantique.input.get(index));
        else if (Semantique.input.get(index).categorie.toString().equals("oppaff")) return "= ";
        else if (Semantique.input.get(index).categorie.toString().equals("oppar")) return getOPPAr(Semantique.input.get(index));
        else if (Semantique.input.get(index).categorie.toString().equals("vrai") || Semantique.input.get(index).categorie.toString().equals("faux") || Semantique.input.get(index).categorie.toString().equals("et") || Semantique.input.get(index).categorie.toString().equals("ou") || Semantique.input.get(index).categorie.toString().equals("non")) return getOPPLg(Semantique.input.get(index));
        else if (Semantique.input.get(index).categorie.toString().equals("fsi") || Semantique.input.get(index).categorie.toString().equals("fpour")) return "}\n";
        return "return 1;\n}\n";
    }
}
