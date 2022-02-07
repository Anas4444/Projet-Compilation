public class UniteSymbole {
    public Symbole symbole;
    public Type type = Type.Vide;
    
    public UniteSymbole(Symbole symbole) {
        this.symbole = symbole;
    }

    public UniteSymbole(Symbole symbole, Type t) {
        this.symbole = symbole;
        this.type = t;
    }

    public UniteSymbole(UniteSymbole us) {
        this.symbole = us.symbole;
        this.type = us.type;
    }

    public UniteSymbole(String s) {
        if (s.equals("P")) this.symbole = Symbole.P;
        else if (s.equals("S")) this.symbole = Symbole.S;
        else if (s.equals("LD")) this.symbole = Symbole.LD;
        else if (s.equals("LI")) this.symbole = Symbole.LI;
        else if (s.equals("D")) this.symbole = Symbole.D;
        else if (s.equals("I")) this.symbole = Symbole.I;
        else if (s.equals("ORE")) this.symbole = Symbole.ORE;
        else if (s.equals("ANDE")) this.symbole = Symbole.ANDE;
        else if (s.equals("ADDE")) this.symbole = Symbole.ADDE;
        else if (s.equals("PE")) this.symbole = Symbole.PE;
        else if (s.equals("id")) this.symbole = Symbole.id;
        else if (s.equals("nb")) { this.symbole = Symbole.nb; this.type = Type.Int; } 
        else if (s.equals("litteral")) { this.symbole = Symbole.litteral; this.type = Type.Char; }
        else if (s.equals("pv")) this.symbole = Symbole.pv;
        else if (s.equals("v")) this.symbole = Symbole.v;
        else if (s.equals("po")) this.symbole = Symbole.po;
        else if (s.equals("pf")) this.symbole = Symbole.pf;
        else if (s.equals("si")) this.symbole = Symbole.si;
        else if (s.equals("sinon")) this.symbole = Symbole.sinon;
        else if (s.equals("alors")) this.symbole = Symbole.alors;
        else if (s.equals("fsi")) this.symbole = Symbole.fsi;
        else if (s.equals("pour")) this.symbole = Symbole.pour;
        else if (s.equals("dans")) this.symbole = Symbole.dans;
        else if (s.equals("faire")) this.symbole = Symbole.faire;
        else if (s.equals("point")) this.symbole = Symbole.point;
        else if (s.equals("non")) this.symbole = Symbole.non;
        else if (s.equals("et")) this.symbole = Symbole.et;
        else if (s.equals("ou")) this.symbole = Symbole.ou;
        else if (s.equals("fpour")) this.symbole = Symbole.fpour;
        else if (s.equals("fin")) this.symbole = Symbole.fin;
        else if (s.equals("lire")) this.symbole = Symbole.lire;
        else if (s.equals("ecrire")) this.symbole = Symbole.ecrire;
        else if (s.equals("vrai")) { this.symbole = Symbole.vrai; this.type = Type.Boolean; }
        else if (s.equals("faux")) { this.symbole = Symbole.faux; this.type = Type.Boolean; }
        else if (s.equals("oppar")) this.symbole = Symbole.OPPAr;
        else if (s.equals("opprel")) this.symbole = Symbole.OPPRel;
        else if (s.equals("oppaff")) this.symbole = Symbole.OPPAff;
        else if (s.equals("type")) this.symbole = Symbole.type;
    }

    public UniteSymbole(String s, Type t) {
        this.type = t;
        if (s.equals("P")) this.symbole = Symbole.P;
        else if (s.equals("S")) this.symbole = Symbole.S;
        else if (s.equals("LD")) this.symbole = Symbole.LD;
        else if (s.equals("LI")) this.symbole = Symbole.LI;
        else if (s.equals("D")) this.symbole = Symbole.D;
        else if (s.equals("I")) this.symbole = Symbole.I;
        else if (s.equals("ORE")) this.symbole = Symbole.ORE;
        else if (s.equals("ANDE")) this.symbole = Symbole.ANDE;
        else if (s.equals("ADDE")) this.symbole = Symbole.ADDE;
        else if (s.equals("PE")) this.symbole = Symbole.PE;
        else if (s.equals("id")) this.symbole = Symbole.id;
        else if (s.equals("nb")) this.symbole = Symbole.nb; 
        else if (s.equals("litteral")) this.symbole = Symbole.litteral; 
        else if (s.equals("pv")) this.symbole = Symbole.pv;
        else if (s.equals("v")) this.symbole = Symbole.v;
        else if (s.equals("po")) this.symbole = Symbole.po;
        else if (s.equals("pf")) this.symbole = Symbole.pf;
        else if (s.equals("si")) this.symbole = Symbole.si;
        else if (s.equals("sinon")) this.symbole = Symbole.sinon;
        else if (s.equals("alors")) this.symbole = Symbole.alors;
        else if (s.equals("fsi")) this.symbole = Symbole.fsi;
        else if (s.equals("pour")) this.symbole = Symbole.pour;
        else if (s.equals("dans")) this.symbole = Symbole.dans;
        else if (s.equals("faire")) this.symbole = Symbole.faire;
        else if (s.equals("point")) this.symbole = Symbole.point;
        else if (s.equals("non")) this.symbole = Symbole.non;
        else if (s.equals("et")) this.symbole = Symbole.et;
        else if (s.equals("ou")) this.symbole = Symbole.ou;
        else if (s.equals("fpour")) this.symbole = Symbole.fpour;
        else if (s.equals("fin")) this.symbole = Symbole.fin;
        else if (s.equals("lire")) this.symbole = Symbole.lire;
        else if (s.equals("ecrire")) this.symbole = Symbole.ecrire;
        else if (s.equals("vrai")) this.symbole = Symbole.vrai;
        else if (s.equals("faux")) this.symbole = Symbole.faux; 
        else if (s.equals("oppar")) this.symbole = Symbole.OPPAr;
        else if (s.equals("opprel")) this.symbole = Symbole.OPPRel;
        else if (s.equals("oppaff")) this.symbole = Symbole.OPPAff;
        else if (s.equals("type")) this.symbole = Symbole.type;
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public void setType(Type t) {
        this.type = t;
    }

    public boolean sameType(Type t) {
        if (this.type.toString().equals(t.toString())) return true;
        return false;
    }

    public String toString() {
        if (type != Type.Vide)
            return "<Symbole = "+symbole.toString()+", Type = "+type.toString()+">";
        return "<Symbole = "+symbole.toString()+">";
    }
}