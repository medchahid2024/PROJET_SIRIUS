//package edu.ezip.ing1.pds.business.dto;
//
//import java.lang.reflect.Field;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//
//
//
//public class Connexion {
//
//    private String email;
//
//    private String mot_de_passe;
//private int id;
//
//
//    public Connexion() {
//    }
//
//    public Connexion(String email,String mot_de_passe) {
//
//        this.email=email;
//
//        this.mot_de_passe=mot_de_passe;
//    }
//
//
//    public int getId() { return id; }
//    public String getEmail() {return email;
//    }
//    public String getMot_de_passe() {return mot_de_passe; }
//
//    public void  setEmail(String email) {this.email=email;}
//    public void setMot_de_passe(String mot_de_passe) {
//        this.mot_de_passe=mot_de_passe;
//    }
//
//
//    public void setId(int id) { this.id = id; }
//
//    public final Connexion build(final ResultSet resultSet)
//            throws SQLException, NoSuchFieldException, IllegalAccessException {
//        setFieldsFromResultSet(resultSet, "email","mot_de_passe");
//        return this;
//    }
//
//    private void setFieldsFromResultSet(ResultSet resultSet, final String... fieldNames)
//            throws NoSuchFieldException, SQLException, IllegalAccessException {
//        for (final String fieldName : fieldNames) {
//            final Field field = this.getClass().getDeclaredField(fieldName);
//            field.set(this, resultSet.getObject(fieldName));
//        }
//    }

//    public final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement , final String ... fieldNames )
//            throws NoSuchFieldException, SQLException, IllegalAccessException {
//        int ix = 0;
//        for(final String fieldName : fieldNames ) {
//            preparedStatement.setString(++ix, fieldName);
//        }
//        return preparedStatement;
//    }
//
//
//}
