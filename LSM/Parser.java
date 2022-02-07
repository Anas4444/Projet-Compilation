import java.util.ArrayList;
import java.util.StringTokenizer;

public class Parser 
{
    public String[][] tableSLR = new String[][]{
        {"Etat/VT","fin","pv","type","id","oppaff","ecrire","lire","si","alors","fsi","sinon","pour","dans","nb","point","faire","fpour","ou","et","oppar","opprel","v","vrai","faux","litteral","po","pf","non","$","p","s","ld","li","d","i","ore","ande","adde","pe"},
        {"0","err","err","s6","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","1","2","3","4","5","err","err","err","err"},
        {"1","s12","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"2","err","err","s6","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","13","14","5","err","err","err","err"},
        {"3","r2","err","err","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","15","err","err","err","err"},
        {"4","err","s16","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"5","err","s17","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"6","err","err","err","s18","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"7","err","err","err","err","s19","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"8","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","20","21","22","23"},
        {"9","err","err","err","s31","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"10","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","32","21","22","23"},
        {"11","err","err","err","s33","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"12","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","acc","err","err","err","err","err","err","err","err","err","err"},
        {"13","r1","err","err","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","15","err","err","err","err"},
        {"14","err","s34","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"15","err","s35","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"16","err","err","r4","r4","err","r4","r4","r4","err","err","err","r4","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"17","r6","err","err","r6","err","r6","r6","r6","err","r6","r6","r6","err","err","err","err","r6","err","err","err","err","err","err","err","err","errr","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"18","err","r7","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"19","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","36","21","22","23"},
        {"20","err","r9","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s37","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"21","err","r15","err","err","err","err","err","err","r15","err","err","err","err","err","err","err","err","r15","s38","err","err","err","err","err","err","err","r15","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"22","err","r17","err","err","err","err","err","err","r17","err","err","err","err","err","err","err","err","r17","r17","s39","err","err","err","err","err","err","r17","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"23","err","r20","err","err","err","err","err","err","r20","err","err","err","err","err","err","err","err","r20","r20","r20","s40","err","err","err","err","err","r20","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"24","err","r21","err","err","err","err","err","err","r21","err","err","err","err","err","err","err","err","r21","r21","r21","r21","err","err","err","err","err","r21","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"25","err","r22","err","err","err","err","err","err","r22","err","err","err","err","err","err","err","err","r22","r22","r22","r22","s41","err","err","err","err","r22","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"26","err","r24","err","err","err","err","err","err","r24","err","err","err","err","err","err","err","err","r24","r24","r24","r24","err","err","err","err","err","r24","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"27","err","r25","err","err","err","err","err","err","r25","err","err","err","err","err","err","err","err","r25","r25","r25","r25","err","err","err","err","err","r25","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"28","err","r26","err","err","err","err","err","err","r26","err","err","err","err","err","err","err","err","r26","r26","r26","r26","err","err","err","err","err","r26","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"29","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","42","21","22","23"},
        {"30","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","err","err","err","43"},
        {"31","err","r10","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"32","err","err","err","err","err","err","err","err","s44","err","err","err","err","err","err","err","err","s37","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"33","err","err","err","err","err","err","err","err","err","err","err","err","s45","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"34","err","err","r3","r3","err","r3","r3","r3","err","err","err","r3","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"35","r5","err","err","r5","err","r5","r5","r5","err","r5","r5","r5","err","err","err","err","r5","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"36","err","r8","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s37","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"37","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","err","46","22","23"},
        {"38","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","err","err","47","23"},
        {"39","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","err","err","err","48"},
        {"40","err","err","err","s24","err","err","err","err","err","err","err","err","err","s25","err","err","err","err","err","err","err","err","s26","s27","s28","s29","err","s30","err","err","err","err","err","err","err","err","err","err","49"},
        {"41","err","err","err","err","err","err","err","err","err","err","err","err","err","s50","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"42","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s37","err","err","err","err","err","err","err","err","s51","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"43","err","r28","err","err","err","err","err","err","r28","err","err","err","err","err","err","err","err","r28","r28","r28","r28","err","err","err","err","err","r28","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"44","err","err","err","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","52","err","5","err","err","err","err"},
        {"45","err","err","err","err","err","err","err","err","err","err","err","err","err","s53","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"46","err","r14","err","err","err","err","err","err","r14","err","err","err","err","err","err","err","err","r14","s38","err","err","err","err","err","err","err","r14","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"47","err","r16","err","err","err","err","err","err","r16","err","err","err","err","err","err","err","err","r16","r16","s39","err","err","err","err","err","err","r16","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"48","err","r18","err","err","err","err","err","err","r18","err","err","err","err","err","err","err","err","r18","r18","r18","err","err","err","err","err","err","r18","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"49","err","r19","err","err","err","err","err","err","r19","err","err","err","err","err","err","err","err","r19","r19","r19","err","err","err","err","err","err","r19","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"50","err","r23","err","err","err","err","err","err","r23","err","err","err","err","err","err","err","err","r23","r23","r23","r23","err","err","err","err","err","r23","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"51","err","r27","err","err","err","err","err","err","r27","err","err","err","err","err","err","err","err","r27","r27","r27","r27","err","err","err","err","err","r27","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"52","err","err","err","s7","err","s8","s9","s10","err","s54","s55","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","errr","err","err","err","err","err","err","err","15","err","err","err","err"},
        {"53","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s56","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"54","err","r11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"55","err","err","err","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","57","err","5","err","err","err","err"},
        {"56","err","err","err","err","err","err","err","err","err","err","err","err","err","s58","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"57","err","err","err","s7","err","s8","s9","s10","err","s59","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","15","err","err","err","err"},
        {"58","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s60","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"59","err","r12","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
        {"60","err","err","err","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","61","err","5","err","err","err","err"},
        {"61","err","err","err","s7","err","s8","s9","s10","err","err","err","s11","err","err","err","err","s62","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","15","err","err","err","err"},
        {"62","err","r13","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"}};
    
    public String[] LRGS = {"P -> S fin", "S -> LD LI", "S -> LI", "LD -> LD D pv", "LD -> D pv",
                            "LI -> LI I pv", "LI -> I pv", "D -> type id", "I -> id oppaff ORE", "I -> ecrire ORE",
                            "I -> lire id", "I -> si ORE alors LI fsi", "I -> si ORE alors LI sinon LI fsi",
                            "I -> pour id dans nb point nb faire LI fpour", "ORE -> ORE ou ANDE", "ORE -> ANDE",
                            "ANDE -> ANDE et ADDE", "ANDE -> ADDE", "ADDE -> ADDE oppar PE", "ADDE -> PE opprel PE",
                            "ADDE -> PE", "PE -> id", "PE -> nb", "PE -> nb v nb", "PE -> vrai", "PE -> faux", "PE -> litteral",
                            "PE -> po ORE pf", "PE -> non PE"};

    public ArrayList<ArrayList<UniteLexicale>> listInput = new ArrayList<ArrayList<UniteLexicale>>();
    public ArrayList<ArrayList<Paire<UniteSymbole, Integer>>> listPile = new ArrayList<ArrayList<Paire<UniteSymbole, Integer>>>();
    public ArrayList<Paire<String, Integer>> listAction = new ArrayList<>();
    public ArrayList<Paire<UniteSymbole, Integer>> pile = new ArrayList<>();
    public ArrayList<UniteLexicale> input = new ArrayList<>();
    ArrayList<UniteLexicale> ids = new ArrayList<>();

    public Parser() {}

    public Parser(ArrayList<UniteLexicale> input) 
    { 
        Semantique.setInputType(input);
        Semantique.setIds();
        for (UniteLexicale inp : Semantique.input) this.input.add(inp);

        Paire<UniteSymbole, Integer> p = new Paire<>();
        p.put(new UniteSymbole(Symbole.$), 0);
        this.pile.add(p);
        this.listPile.add(pile);
        this.listInput.add(this.input);
    }

    public int vt(String vtn) {
        for (int i = 1; i < 40; i++) {  
            if (tableSLR[0][i].equals(vtn)) return i;               
        }
        return 0;
    }

    public ArrayList<UniteSymbole> regleToSymboles(int index) {
        ArrayList<UniteSymbole> s = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(LRGS[index], " ");
        while(st.hasMoreTokens()) {
            String symbole = st.nextToken();
            if (symbole.equals("->")) continue;
            s.add(new UniteSymbole(symbole));
        }
        return s;
    }

    public void actionSuivante() {
        Paire<String, Integer> action = new Paire<>();
        String regle; 
        
        if (input.size() != 0) { regle = tableSLR[pile.get(pile.size()-1).v2 + 1][this.vt(input.get(0).categorie.toString())]; }
        else { regle = tableSLR[pile.get(pile.size()-1).v2 + 1][this.vt("$")]; }
        if (regle.charAt(0)=='s') { action.put("decaler", Integer.parseInt(regle.substring(1))); }
        else if (regle.charAt(0)=='r') { action.put("reculer", Integer.parseInt(regle.substring(1))); }
        else if (regle.charAt(0)=='e') { action.put("erreur", -1); }
        else { action.put("accepter", -2); }
        if (listAction.size() > 0)
            if (listAction.get(listAction.size()-1).v2 == -3) {
                this.listAction.add(new Paire<>("erreur", -3));
                return ;
            }
            else if (listAction.get(listAction.size()-1).v2 == -4) {
                this.listAction.add(new Paire<>("erreur", -4));
                return ;
            }
        this.listAction.add(action);
    }

    public void decaler() {
        Paire<UniteSymbole, Integer> p = new Paire<>();
        UniteSymbole symbole = new UniteSymbole(this.input.get(0).categorie.toString(), this.input.get(0).type);
        p.put(symbole, listAction.get(listAction.size()-1).v2);
        this.pile.add(p);
        this.input.remove(0);
    }

    public void reculer() {
        ArrayList<UniteSymbole> symboles = this.regleToSymboles((listAction.get(listAction.size()-1)).v2);
        for (int i=1; i<symboles.size(); i++) { this.pile.remove(this.pile.size()-1); }
        UniteSymbole key = symboles.get(0);
        Integer value = Integer.parseInt(tableSLR[pile.get(pile.size()-1).v2 + 1][this.vt(key.symbole.toString())]);
        Paire<UniteSymbole, Integer> p = new Paire<>();
        p.put(new UniteSymbole(key), value);
        this.pile.add(p);
    }

    public void parse() {
        actionSuivante();
        addInput(input);
        addPile(pile);
        String actionType = listAction.get(listAction.size()-1).v1;
        while(actionType.equals("decaler") || actionType.equals("reculer")) {
            if (actionType.equals("decaler")) decaler();
            else { 
                if (Semantique.addType(this.pile, LRGS[(listAction.get(listAction.size()-1)).v2])==1)
                    reculer(); 
                else if (Semantique.addType(this.pile, LRGS[(listAction.get(listAction.size()-1)).v2])==2) {
                    this.listAction.add(new Paire<>("erreur", -3));
                }
                else if (Semantique.addType(this.pile, LRGS[(listAction.get(listAction.size()-1)).v2])==3){
                    this.listAction.add(new Paire<>("erreur", -4));
                }
            }
            addInput(input);
            addPile(pile);
            actionSuivante();
            actionType = listAction.get(listAction.size()-1).v1;
        }
        actionSuivante();
    }

    public void showInput(int index) { 
        System.out.println("input :");
        System.out.println("\t" + this.listInput.get(index)); 
        System.out.println("\n");
    }

    public void showInput() { 
        System.out.println("input :");
        System.out.println("\t" + this.input); 
        System.out.println("\n");
    }

    public void showPile(int index) {
        System.out.println("Pile :");
        System.out.println("\t" + this.listPile.get(index)); 
        System.out.println("\n\t-------------------------------------------------------");
    }

    public void showPile() {
        System.out.println("Pile :");
        for (Paire<UniteSymbole, Integer> p : this.pile) { System.out.println("\t" + p); }
        System.out.println("\n\t-------------------------------------------------------");
    }

    public void showAction(int index) {
        Paire<String, Integer> action = this.listAction.get(index);
        System.out.println("Action :");
        System.out.println("\t" + action.v1 + " ");
        if (action.v2 > 0) {
            if (action.v1.equals("reculer")) System.out.println("\t" + action.v2 + " : " + LRGS[action.v2]);
            else {
                if (this.listInput.get(index).size()>0)
                    System.out.println("\t" + action.v2 + " : " + this.listInput.get(index).get(0).categorie.toString());
                else System.out.println("\t" + action.v2);
            }
        }
        else if (action.v2 == -3){ System.out.println("\t" + Semantique.errors[0]); }
        else if (action.v2 == -4){ System.out.println("\t" + Semantique.errors[1]); }
        else if (action.v2 == -1){ System.out.println("\t" + Semantique.errors[2]); }
    }

    public void showAll() {
        int i=0;
        for (; i<listInput.size(); i++)
        {
            showInput(i);
            showPile(i);
            showAction(i);
            System.out.println("\n");
        }
    }

    public void addPile(ArrayList<Paire<UniteSymbole, Integer>> pile) {
        ArrayList<Paire<UniteSymbole, Integer>> p = new ArrayList<Paire<UniteSymbole, Integer>>();
        for (Paire<UniteSymbole, Integer> pr : pile) {
            p.add(new Paire<UniteSymbole, Integer>(pr.v1, pr.v2));
        }
        this.listPile.add(p);
    }

    public void addInput(ArrayList<UniteLexicale> input) {
        ArrayList<UniteLexicale> inp = new ArrayList<UniteLexicale>();
        for (UniteLexicale p : input) {
            inp.add(new UniteLexicale(p.categorie, p.lexeme));
        }
        this.listInput.add(inp);
    }

    public Paire<String, Integer> getLastAction() { return listAction.get(listAction.size()-1); }
}