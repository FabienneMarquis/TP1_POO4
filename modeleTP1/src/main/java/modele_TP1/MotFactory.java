package modele_TP1;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Gabriel on 29/12/2015.
 */
public class MotFactory {

    private static MotFactory motFactory;

    private Path path;

    private MotFactory(){

        URL url = getClass().getResource("../liste.de.mots.francais.frgut.utf8.txt");
        URI uri = null;

        try {
            uri = url.toURI();
        }
        catch(URISyntaxException ex){
            ex.printStackTrace();
        }

        path = Paths.get(uri);

    }

    /**
     * permet de renvoyer ou de créer le MotFactory
     * @return
     */
    public static MotFactory getInstance(){
        if(motFactory==null){
            motFactory = new MotFactory();
        }
        return motFactory;
    }

    /**
     *Cette méthode renvoit le dictionnaire (liste de Mots) créer avec les fabriques de mot de cette classe
     * @return dictionnaire (List<Mot>)
     */
    public List<Mot> getMots(){

        List<Mot> dictionnaire = new ArrayList<>();
        try{
            List<String> words = Files.readAllLines(path, StandardCharsets.UTF_8);
            words.forEach((String word)->dictionnaire.add(new Mot(word.toLowerCase())));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionnaire;
    }

}
