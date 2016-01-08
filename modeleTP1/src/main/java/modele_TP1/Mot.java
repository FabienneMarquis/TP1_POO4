package modele_TP1;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Mot {

	private String mot,definition,imageURL;
	private LocalDate createdAt,updatedAt;

	/**
	 * Constructeur de l'objet Mot avec un seul parametre
	 * @param mot String
	 * @throws IllegalArgumentException
     */
	public Mot(String mot) throws IllegalArgumentException{
		setMot(mot);
		setCreatedAt();
		setUpdatedAt();
		imageURL = "";
	}

	/**
	 * Constructeur de l'objet Mot avec trois paramètres
	 * @param mot le mot
	 * @param definition la définition du mot
	 * @param urlImage la source de l'image (String de l'URL)
     */
	public Mot(String mot, String definition, String urlImage){
		setMot(mot);
		setDefinition(definition);
		setCreatedAt();
		setUpdatedAt();
		imageURL = urlImage;
	}

	/**
	 * Methode qui retourne un mot
	 * @return mot
     */

	public String getMot() {
		return mot;
	}

	/**
	 * methode qui vérifier que le mot que l'on veut ajouter n'ait qu'un seul mot
	 * @param mot
	 * @throws IllegalArgumentException
     */
	public void setMot(String mot) throws IllegalArgumentException {
		if(mot.split(" ").length > 1)
			throw new IllegalArgumentException("INVALID: More than one word in a word");

		setUpdatedAt();

		this.mot = mot;
	}

	/**
	 * methode qui retourne la définition associé à un mot
	 * @return definition
     */
	public String getDefinition() {
		return definition;
	}

	/**
	 *méthode qui permet de déterminier la définition d'un mot
	 * @param definition
     */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * Méthode qui renvoit l'URL en String de l'image associé à un mot
	 * @return
     */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * Méthode qui permet d'associé l'URL en String d'une image à un mot (changer ou ajouter)
	 * @param imageURL
     */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * Méthode qui permet de connaitre la date de création du mot
	 * @return
     */
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	private void setCreatedAt() {
		this.createdAt = LocalDate.now();
	}

	/**
	 * Méthode qui permet de connaitre la date de modification
	 * @return
     */
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Méthode qui permet d'ajouter une date de modification à un mot
	 */
	public void setUpdatedAt() {
		this.updatedAt = LocalDate.now();
	}

}
