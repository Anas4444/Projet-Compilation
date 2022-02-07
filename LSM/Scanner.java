//package analyselexical;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Scanner {
    private ArrayList<Character> fluxCaracteres;
    private int indiceCourant;
    private char caractereCourant;
    private boolean eof;

    public Scanner() {
        this("");
    }

    public Scanner(String nomFich) {
        BufferedReader f=null;
        int car=0;
        fluxCaracteres=new ArrayList<>();
        indiceCourant=0;
        eof=false;
        try {
            f=new BufferedReader(new FileReader(nomFich));
        }
        catch(IOException e) {
            System.out.println("taper votre texte ci-dessous (ctrl+z pour finir)");
            f=new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            while((car=f.read())!=-1)
                fluxCaracteres.add((char)car);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void caractereSuivant() {
    if(indiceCourant<fluxCaracteres.size())
        caractereCourant=fluxCaracteres.get(indiceCourant++);
    else
        eof=true;
    }

    public void reculer() {
        if(indiceCourant>0)
           indiceCourant--;
    }

    public UniteLexicale lexemeSuivant() {
		caractereSuivant();
		
		while(eof || Character.isWhitespace(caractereCourant)) { 
			if (eof) return null;
			caractereSuivant();
		}

        if (caractereCourant=='f')
            return getFin();
        
        if (caractereCourant=='p' || caractereCourant=='f' || caractereCourant=='d' || caractereCourant=='.')
        {
            if (caractereCourant=='.') return new UniteLexicale(Categorie.point, ".");
            return getBoucle();
        }
        
        if (caractereCourant=='i' || caractereCourant=='r' || caractereCourant=='c' || caractereCourant=='b')
            return getType();
        
        if (caractereCourant=='e' || caractereCourant=='o' || caractereCourant=='v' || caractereCourant=='f' || caractereCourant=='n')
            return getOPPLg();

        if (caractereCourant=='l' || caractereCourant=='e')
            return getLE();
		
        if (caractereCourant=='s' || caractereCourant=='a')
            return getCondition();

        if(Character.isDigit(caractereCourant))
            return getNombre();
        
        if (caractereCourant=='\"')
            return getLitteral();

        if(Character.isLetter(caractereCourant))
			return getID();

		if(caractereCourant==':') 
			return getOPPAff();
		
		if(caractereCourant==';')
			return new UniteLexicale(Categorie.pv, ";");
        
        if(caractereCourant==',')
			return new UniteLexicale(Categorie.v, ",");
        
        if(caractereCourant=='(')
			return new UniteLexicale(Categorie.po, "(");
        
        if(caractereCourant==')')
			return new UniteLexicale(Categorie.pf, ")");
               
		if(caractereCourant=='<' || caractereCourant=='>' ||caractereCourant=='=')
			return getOPPRel();
        
        if (caractereCourant=='+' || caractereCourant=='-' || caractereCourant=='*' || caractereCourant=='/' || caractereCourant=='%')
            return getOPPAr();
		return null;
	}

    public UniteLexicale getID() {
		int etat=0;
		StringBuffer sb=new StringBuffer(); 
		while(true) {
			switch(etat) {
				case 0 : etat=1; 
						 sb.append(caractereCourant);
                         caractereSuivant();
						 break;
				case 1 :
                        if (eof){
                            etat=2;
                            break;
                        }
                        if(Character.isLetterOrDigit(caractereCourant)){
                            sb.append(caractereCourant);
                            caractereSuivant();
                        }
                        else etat=2;
                        break;
				case 2 : reculer();
						 return new UniteLexicale(Categorie.id, sb.toString());
			}
		}
	}

    public UniteLexicale getLitteral() {
		StringBuffer sb=new StringBuffer();
        sb.append(caractereCourant);
        caractereSuivant();
		while(!eof) {
            sb.append(caractereCourant); 
            if (caractereCourant=='\"') return new UniteLexicale(Categorie.litteral, sb.toString());
            caractereSuivant();
        }
        return null;
    }

    public UniteLexicale getNombre() {
		int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 : etat=1; 
					 sb.append(caractereCourant); 
					 break;
			case 1 : caractereSuivant(); 
                    if(eof) return new UniteLexicale(Categorie.nb, sb.toString());
					if(Character.isDigit(caractereCourant)) sb.append(caractereCourant);
					else etat=2;
                    break; 
			case 2 : reculer();
					 return new UniteLexicale(Categorie.nb, sb.toString());
			}
		}
	}

    public UniteLexicale getOPPAff() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat = 1;
                    break;
                case 1:
                    if (eof)
                        return null;
                    if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        return new UniteLexicale(Categorie.OPPAff, sb.toString());
                    }
                    etat=2;
                    break;
                case 2:
                    return null;
            }
        }
    }

    public UniteLexicale getOPPRel() {
        int etat = 0;
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    if (caractereCourant == '=') {
                        return new UniteLexicale(Categorie.OPPRel, "=");
                    }
                    if (caractereCourant == '>') {
                        caractereSuivant();
                        etat = 2;
                        break;
                    }
                    caractereSuivant(); 
                    etat = 3;
                    break;
                case 2:
                    if (caractereCourant == '=') {
                        return new UniteLexicale(Categorie.OPPRel, ">=");
                    } 
                    return new UniteLexicale(Categorie.OPPRel, ">");
                 
                case 3:
                    if (caractereCourant == '=') { 
                        return new UniteLexicale(Categorie.OPPRel, "<=");
                    } 
                    else if (caractereCourant == '>') {
                        return new UniteLexicale(Categorie.OPPRel, "<>");
                    } 
                    return new UniteLexicale(Categorie.OPPRel, "<");
            }
        }
    }

    public UniteLexicale getOPPLg() {
    int etat = 0;
    StringBuffer sb = new StringBuffer();
    while (true) {
        switch (etat) {
            case 0:
                sb.append(caractereCourant);
                if (eof)
                    return null;
                else if (caractereCourant == 'e')
                {
                    etat = 1;
                }
                else if (caractereCourant == 'o')
                    etat = 2;
                else if (caractereCourant == 'v')
                    etat = 3;
                else if (caractereCourant == 'f')
                    etat = 4;
                else if (caractereCourant == 'n')
                    etat = 5;
                caractereSuivant();
                break;
            case 1:
                if (caractereCourant == 't')
                {
                    sb.append(caractereCourant);
                    return new UniteLexicale(Categorie.et, sb.toString());
                }
                else {
                    reculer();
                    reculer();
                    caractereSuivant();
                    return getLE();
                }
                
            case 2:
                if (caractereCourant == 'u')
                {
                    sb.append(caractereCourant);
                    return new UniteLexicale(Categorie.ou, sb.toString());
                }
                else {
                    reculer();
                    reculer();
                    caractereSuivant();
                    return getID(); 
                }
            case 3:
                if (caractereCourant == 'r')
                {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    if (caractereCourant == 'a')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'i')
                        {
                            sb.append(caractereCourant);
                            return new UniteLexicale(Categorie.vrai, sb.toString());
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                }
                else {
                    reculer();
                    reculer();
                    caractereSuivant();
                    return getID();
                }
            case 4:
                if (caractereCourant == 'a')
                {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    if (caractereCourant == 'u')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'x')
                        {
                            sb.append(caractereCourant);
                            return new UniteLexicale(Categorie.faux, sb.toString());
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                }
                else {
                    reculer();
                    reculer();
                    caractereSuivant();
                    return getID();
                }
            case 5:
                if (caractereCourant == 'o')
                {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    if (caractereCourant == 'n')
                    {
                        sb.append(caractereCourant);
                        return new UniteLexicale(Categorie.non, sb.toString());
                    }
                    else {
                        reculer();
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                }
                else {
                    reculer();
                    reculer();
                    caractereSuivant();
                    return getID();
                }   
            } 
        }
    }

    public UniteLexicale getOPPAr() {   
    int etat = 0;
    StringBuffer sb = new StringBuffer();
    while (true) {
        switch (etat) {
            case 0:
                sb.append(caractereCourant);
                if (caractereCourant=='+' || caractereCourant=='-' || caractereCourant=='*' || caractereCourant=='%')
                    return new UniteLexicale(Categorie.OPPAr, sb.toString());

                else if (caractereCourant=='/')
                {
                    caractereSuivant();
                    if (caractereCourant=='/')
                    {
                        sb.append(caractereCourant);
                        etat = 1;
                    }
                    else etat = 2;
                }
                case 1:
                    return new UniteLexicale(Categorie.OPPAr, sb.toString());
                case 2:
                {
                    reculer(); 
                    return new UniteLexicale(Categorie.OPPAr, sb.toString());
                }
            }
        } 
    }

    public UniteLexicale getLE() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    sb.append(caractereCourant);
                    if (caractereCourant == 'l') etat = 1;
                    else if (caractereCourant == 'e') etat = 2;
                    else {
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                    break;
                case 1:
                    caractereSuivant();
                    if (caractereCourant == 'i')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'r')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'e')
                            {
                                sb.append(caractereCourant);
                                return new UniteLexicale(Categorie.lire, sb.toString());
                            }
                            else 
                            {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 2:
                    caractereSuivant();
                    if (caractereCourant == 'c')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'r')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'i')
                            {
                                sb.append(caractereCourant);
                                caractereSuivant();
                                if (caractereCourant == 'r')
                                {
                                    sb.append(caractereCourant);
                                    caractereSuivant();
                                    if (caractereCourant == 'e')
                                    {
                                        sb.append(caractereCourant);
                                        return new UniteLexicale(Categorie.ecrire, sb.toString());
                                    }
                                    else {
                                        reculer();
                                        reculer();
                                        reculer();
                                        reculer();
                                        reculer();
                                        reculer();
                                        caractereSuivant();
                                        return getID();
                                    }
                                }
                                else {
                                    reculer();
                                    reculer();
                                    reculer();
                                    reculer();
                                    reculer();
                                    caractereSuivant();
                                    return getID();
                                }
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
            }
        }
    }

    public UniteLexicale getCondition() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    sb.append(caractereCourant);
                    if (caractereCourant == 's') etat = 1;
                    else etat = 2;
                    caractereSuivant();
                    break;
                case 1:
                    if (caractereCourant == 'i')
                    {
                        sb.append(caractereCourant);
                        return new UniteLexicale(Categorie.si, sb.toString());
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 2:
                    if (caractereCourant == 'l')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'o')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'r')
                            {
                                sb.append(caractereCourant);
                                caractereSuivant();
                                if (caractereCourant == 's')
                                {
                                    sb.append(caractereCourant);
                                    return new UniteLexicale(Categorie.alors, sb.toString());
                                }
                                else {
                                    reculer();
                                    reculer();
                                    reculer();
                                    reculer();
                                    reculer();
                                    caractereSuivant();
                                    return getID();
                                }
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
            }
        }
    }

    public UniteLexicale getType() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    sb.append(caractereCourant);
                    if (caractereCourant == 'i') etat = 1;
                    if (caractereCourant == 'r') etat = 2;
                    if (caractereCourant == 'b') etat = 3;
                    if (caractereCourant == 'c') etat = 4;
                    caractereSuivant();
                    break;
                case 1:
                    if (caractereCourant == 'n')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 't')
                        {
                            sb.append(caractereCourant);
                            return new UniteLexicale(Categorie.type, sb.toString());
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 2:
                    if (caractereCourant == 'e')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'a')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'l')
                            {
                                sb.append(caractereCourant);
                                return new UniteLexicale(Categorie.type, sb.toString());
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 3:
                    if (caractereCourant == 'o')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'o')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'l')
                            {
                                sb.append(caractereCourant);
                                return new UniteLexicale(Categorie.type, sb.toString());
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 4:
                    if (caractereCourant == 'h')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'a')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'r')
                            {
                                sb.append(caractereCourant);
                                return new UniteLexicale(Categorie.type, sb.toString());
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
            }
        }
    }

    public UniteLexicale getBoucle() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    sb.append(caractereCourant);
                    if (caractereCourant == 'p') etat = 1;
                    else if (caractereCourant == 'd') etat = 2;
                    else if (caractereCourant == 'f') etat = 3;
                    caractereSuivant();
                    break; 

                case 1:
                    if (caractereCourant == 'o')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'u')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'r')
                            {
                                sb.append(caractereCourant);
                                return new UniteLexicale(Categorie.pour, sb.toString());
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 2:
                    if (caractereCourant == 'a')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'n')
                            {
                                sb.append(caractereCourant);
                                caractereSuivant();
                                if (caractereCourant == 's')
                                {
                                    sb.append(caractereCourant);
                                    return new UniteLexicale(Categorie.dans, sb.toString());
                                }
                                else {
                                    reculer();
                                    reculer();
                                    reculer();
                                    reculer();
                                    caractereSuivant();
                                    return getID();
                                }
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 3:
                    if (caractereCourant == 'a')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'i')
                            {
                                sb.append(caractereCourant);
                                caractereSuivant();
                                if (caractereCourant == 'r')
                                {
                                    sb.append(caractereCourant);
                                    caractereSuivant();
                                    if (caractereCourant == 'e')
                                    {
                                        sb.append(caractereCourant);
                                        return new UniteLexicale(Categorie.faire, sb.toString());
                                    }
                                    else {
                                        reculer();
                                        reculer();
                                        reculer();
                                        reculer();
                                        reculer();
                                        caractereSuivant();
                                        return getID();
                                    }   
                                }
                                else {
                                    reculer();
                                    reculer();
                                    reculer();
                                    reculer();
                                    caractereSuivant();
                                    return getID();
                                }
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getOPPLg(); 
                            }
                        }
                    else {
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
            }
        }
    }

    public UniteLexicale getFin() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    etat = 1;
                    sb.append(caractereCourant);
                    break;
                case 1:
                    caractereSuivant();
                    if (caractereCourant == 'i')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    }
                    else if (caractereCourant == 's')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 3;
                    }
                    else if (caractereCourant == 'p')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 4;
                    }
                    else etat = 5;
                    break;
                case 2:
                    if (caractereCourant == 'n') 
                    {
                        sb.append(caractereCourant);
                        return new UniteLexicale(Categorie.fin, sb.toString());
                    }
                    else {
                        reculer();
                        reculer();
                        reculer();
                        return getID();
                    }
                case 3:
                    if (caractereCourant == 'i')
                    {
                        sb.append(caractereCourant);
                        return new UniteLexicale(Categorie.fsi, sb.toString());
                    }
                    else {
                        reculer();
                        reculer();
                        reculer();
                        return getID();
                    }
                case 4:
                    if (caractereCourant == 'o')
                    {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        if (caractereCourant == 'u')
                        {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            if (caractereCourant == 'r')
                            {
                                sb.append(caractereCourant);
                                return new UniteLexicale(Categorie.fpour, sb.toString());
                            }
                            else {
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                reculer();
                                caractereSuivant();
                                return getID();
                            }
                        }
                        else {
                            reculer();
                            reculer();
                            reculer();
                            reculer();
                            caractereSuivant();
                            return getID();
                        }
                    }
                    else {
                        reculer();
                        reculer();
                        reculer();
                        caractereSuivant();
                        return getID();
                    }
                case 5:
                    reculer();
                    reculer();
                    caractereSuivant();
                    return getBoucle();
            }
        }
    }
}