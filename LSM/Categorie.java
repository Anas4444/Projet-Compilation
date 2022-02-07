//package analyselexical;

public enum Categorie {
    id, nb, litteral, pv, v, po, pf, si, sinon, alors, fsi, pour, dans, faire, point, non, et, ou, fpour, fin, lire, ecrire, vrai, faux, OPPAr, OPPRel, OPPAff, type
    ;
    public String toString() {
        return this.name().toLowerCase();
    }
}