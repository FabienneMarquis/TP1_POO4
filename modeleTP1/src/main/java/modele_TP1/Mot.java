package modele_TP1;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Mot {

	private String mot,definition,imageURL;
	private LocalDate createdAt,updatedAt;
	
	// simpleton création d'un mots

	/*
	 * création de Mot
	 * 
	 * un mot (1 seul mot) • une définition (max 20 mots configurables par
	 * propriétés) • le nom d’un fichier contenant une image (en format jpeg)
	 * qui est associée à la définition (peut être null)• la date de saisie du
	 * mot (allez voir la classe LocaleDate) • la date de la dernière
	 * modification (LocaleDate aussi)
	 */

	/*
	 * La fabrique doit avoir une méthode pour créer les ~300 000 mots contenus
	 * dans le fichier qui vous a été fourni avec le travail. La date est saisie
	 * sur le moment. La définition et l’image sont vides par défaut.
	 */

	public Mot(String mot) throws IllegalArgumentException{
		setMot(mot);
		setCreatedAt();
		setUpdatedAt();
		imageURL = "";
	}

	public Mot(String mot, String definition, String urlImage){
		setMot(mot);
		setDefinition(definition);
		setCreatedAt();
		setUpdatedAt();
		imageURL = urlImage;
	}


	public String getMot() {
		return mot;
	}

	public void setMot(String mot) throws IllegalArgumentException {
		if(mot.split(" ").length > 1)
			throw new IllegalArgumentException("INVALID: More than one word in a word");

		setUpdatedAt();

		this.mot = mot;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	private void setCreatedAt() {
		this.createdAt = LocalDate.now();
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	private void setUpdatedAt() {
		this.updatedAt = LocalDate.now();
	}


	public static void main(String[] args) {
		Mot mot = null;
		try {
			mot = new Mot("derp");
		}catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		System.out.println(mot.toString());
		System.out.println(mot.getMot());
		System.out.println(mot.getCreatedAt());
		System.out.println(mot.getUpdatedAt());
		System.out.println(mot.getDefinition());
	}
}
