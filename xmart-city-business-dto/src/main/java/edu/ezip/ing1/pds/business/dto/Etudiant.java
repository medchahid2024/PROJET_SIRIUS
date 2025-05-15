package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Etudiant {
    private String nom;
    private String prenom;
    private String matricule;
    private String email;
    private String mot_de_passe;
    private String cnf_mot_de_passe;
    private String photo;
    private int id;
    private boolean accepte;
     private int detail;
     private Date date;
     private String titre;
     private String duree;
     private String domaine;
     private int id_offre;




    public Etudiant() {

    }

    public String getTitre(){
       return titre;
    }
    public String getDuree(){
        return duree;
    }
    public String getDomaine(){
        return domaine;
    }
    public void setTitre(String titre){
       this.titre = titre;
    }
    public void setDuree(String duree){
        this.duree = duree;
    }
    public void setDomaine(String domaine){
        this.domaine = domaine;
    }
    public Etudiant(String email, String mot_de_passe) {
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    public Etudiant(String nom, String prenom, int id) {
       this.nom = nom;
       this.prenom = prenom;
       this.id = id;
    }
    public Etudiant(String nom, String prenom, int id,String photo,String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.photo = photo;
        this.email = email;
    }
    public Etudiant(String nom, String prenom, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
    }

    public Etudiant(String email) {
        this.email = email;
    }

    public Etudiant(String email, String mot_de_passe,String nom, String prenom) {
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Etudiant(String nom, String prenom, String matricule, String email, String mot_de_passe, String cnf_mot_de_passe,String photo, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.cnf_mot_de_passe = cnf_mot_de_passe;
        this.photo = photo;
        this.id = id;
    }
    public Etudiant(String nom, String prenom, String matricule, String email, String mot_de_passe, String cnf_mot_de_passe,String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.cnf_mot_de_passe = cnf_mot_de_passe;
        this.photo = photo;

    }
    public Etudiant(String email, String mot_de_passe, boolean accepte) {
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.accepte = accepte;
    }

    public Etudiant(int i) {
        this.id = i;
    }
    public Etudiant(int i ,int id_offre) {
        this.id = i;
        this.id_offre = id_offre;
    }


    public int getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getCnf_mot_de_passe() {
        return cnf_mot_de_passe;
    }
    public String getPhoto() {return photo;}
    public int getId_offre() {return id_offre;}
    public boolean isAccepte() {
        return accepte;
    }

    public int getDetail() {
        return detail;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setdetail(int detail) {
        this.detail=detail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setCnf_mot_de_passe(String cnf_mot_de_passe) {
        this.cnf_mot_de_passe = cnf_mot_de_passe;
    }
    public void setPhoto(String photo) {this.photo = photo;}

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public final Etudiant build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet, "nom", "prenom", "matricule", "email", "mot_de_passe", "cnf_mot_de_passe","photo");
        return this;
    }

    private void setFieldsFromResultSet(ResultSet resultSet, final String... fieldNames)
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for (final String fieldName : fieldNames) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName));
        }
    }

    public final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement ,final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }
        return preparedStatement;
    }
}

