package modele_TP1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0940135 on 2016-01-04.
 */
public class DictionnairePrincipale {
    private static Dictionnaire dictionnaire;

    public static Dictionnaire getDictionnaire() {
        return dictionnaire;
    }

    public static void setDictionnaire(Dictionnaire dictionnaire) {
        DictionnairePrincipale.dictionnaire = dictionnaire;
    }
}
