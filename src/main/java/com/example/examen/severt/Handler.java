package com.example.examen.severt;

import com.example.examen.base.Conexion;
import com.example.examen.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    Connection con;
    PreparedStatement pstm;
    Statement statement;
    ResultSet rs;
    public boolean createUser(String name,String lastname,String email,String password, int status){
        boolean state = false;
        try{
            con = Conexion.getConnection();
            String query = "insert into mydb.user ( `name`,`lastname`,`email`,`password`,` date_registered`,`status`) values (?,?,?,?,now(),?);";
            pstm = con.prepareStatement(query);
            pstm.setString(1, name );
            pstm.setString(2, lastname );
            pstm.setString(3, email );
            pstm.setString(4, password );
            pstm.setInt(5, status);
            state = pstm.executeUpdate() == 1;


        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return state;
    }
    public boolean UpdateUser(String name,String lastname,String email,String password, int status, int id){
        boolean state = false;
        try{
            con = Conexion.getConnection();
            String query = "update mydb.user set name=?, lastname=?, email=?, password=?, status=? where id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1, name );
            pstm.setString(2, lastname );
            pstm.setString(3, email );
            pstm.setString(4, password );
            pstm.setInt(5, status);
            state = pstm.executeUpdate() == 1;


        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return state;
    }
    public boolean deletePerson(int id){
        boolean state = false;
        try{
            con = Conexion.getConnection();
            String query = "DELETE FROM mydb.user WHERE id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }


    public void closeConnection() {
        try{
            if (con != null){
                con.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
            if (statement != null){
                statement.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public String findAll(Boolean flag){
        List<User> listUser = new ArrayList<>();
        String lista = "";
        try {
            con = Conexion.getConnection();
            String query = "select user.id, user.name, user.lastName, user.email, user.password, user.date_registered, user.status from user;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setDate(rs.getString("date_registered"));
                user.setStatus(rs.getInt("status"));
                listUser.add(user);
                for (User usuario : listUser){
                    lista+= "----------------------------\n" +
                            "Nombre: "+ usuario.getName()+"\n" +
                            "Apellido: "+ usuario.getLastName()+"\n" +
                            "Email" + usuario.getEmail()+ "\n" +
                            "Contraseña: " +usuario.getPassword()+"\n" +
                            "Fecha creación: "+usuario.getDate()+"\n" +
                            "Status: "+usuario.getStatus()+"\n" +
                            "-----------------------------\n";
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }

        return lista;
    }
}
