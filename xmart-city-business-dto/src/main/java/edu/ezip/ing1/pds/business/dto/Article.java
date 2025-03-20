package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Article {
    private String nom;
    private String titre;
    private String description;
    private int id;
    private int prix;
    private String typeTransaction;
    private String ville;



    public Article() {
    }
    public Article(int id) {
        this.id = id;
    }

    public Article(String nom, String titre,String description,int id,int prix,String typeTransaction,String ville) {
        this.nom = nom;
        this.titre = titre;
        this.description = description;
        this.id = id;
        this.prix = prix;
        this.typeTransaction = typeTransaction ;
        this.ville = ville;


    }
    public Article(String nom, String titre,String description,String typeTransaction,String ville) {
        this.nom = nom;
        this.titre = titre;
        this.description = description;
       
        this.typeTransaction = typeTransaction ;
        this.ville = ville;


    }



    public String getNom() { return nom; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public int getId() { return id; }
    public int getPrix() {return prix; }
    public String getTypeTransaction() {return typeTransaction; }
    public String getVille() {return ville; }


    public void setNom(String nom) { this.nom = nom; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setDescription(String description) { this.description = description; }
    public void setId(int id) { this.id = id; }
    public void setPrix(int prix) { this.prix = prix; }
    public void setTypeTransaction(String typeTransaction) { this.typeTransaction = typeTransaction; }

    public void setVille(String ville) {
        this.ville = ville;
    }

   

    public final Article build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet, "nom", "titre", "description", "prix", "typeTransaction", "ville");
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
