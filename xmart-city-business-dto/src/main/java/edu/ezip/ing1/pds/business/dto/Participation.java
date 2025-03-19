package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Participation {
    private String nom;
    private String prenom;
    private String email;
    private int id;


    public Participation() {
    }
    public Participation(int id) {
        this.id = id;
    }

    public Participation(String nom, String prenom,String email,int id ) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.id = id;


    }



    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
   public int getId() { return id; }
    public String getEmail() {return email;
    }


    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public void setEmail(String email) {
        this.email = email;
    }

   public void setId(int id) { this.id = id; }

    public final Participation build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet, "nom", "prenom", "email");
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
