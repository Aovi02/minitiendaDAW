package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.*;

public class SQL {

	private String url = null;
	private String usuario = null;
	private String contraseña = null;
	
	public void SQL(){
		// Datos de conexión a la base de datos
		url = "jdbc:postgresql://localhost:5432/postgres";
		usuario = "postgres";
		contraseña = "postgres";
	}

    public void meterUsuario(String correo, String contrasena, String numeroTarjeta) {
    

		// Intentar establecer la conexión y ejecutar la consulta
		try {
		    // Cargar el controlador de la base de datos
		    Class.forName("org.postgresql.Driver");

		    // Establecer la conexión
		    Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

		    // Crear una declaración para ejecutar la consulta
		    //Statement statement = conexion.createStatement();

		    // Ejecutar la consulta SQL para seleccionar todos los usuarios
		    String sql = "INSERT INTO usuarios (correo_electronico, contrasena, numero_tarjeta) VALUES (?, ?, ?)";
		    PreparedStatement statement = conexion.prepareStatement(sql);
		    statement.setString(1, correo);
		    statement.setString(2, contrasena);
		    statement.setString(3, numeroTarjeta);

		    // Ejecutar la sentencia de inserción
		    int filasInsertadas = statement.executeUpdate();

		    // Verificar si la inserción fue exitosa
		    if (filasInsertadas > 0) {
		        System.out.println("Se ha insertado el nuevo usuario correctamente.");
		    } else {
		        System.out.println("No se pudo insertar el nuevo usuario.");
		    }

		    // Cerrar la conexión
		    statement.close();
		    conexion.close();
		} catch (ClassNotFoundException e) {
		    System.out.println("insert No se pudo cargar el controlador: " + e.getMessage());
		} catch (SQLException e) {
		    System.out.println("insert Error al conectar a la base de datos: " + e.getMessage());
		}
            }
        public void sacarUsuario(String correo) {
    

		// Intentar establecer la conexión y ejecutar la consulta
		try {
		    // Cargar el controlador de la base de datos
		    Class.forName("org.postgresql.Driver");

		    // Establecer la conexión
		    Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

		    // Crear una declaración para ejecutar la consulta
		    //Statement statement = conexion.createStatement();

		     // Ejecutar la consulta SQL para seleccionar todos los usuarios
		    String sql = "DELETE FROM usuarios WHERE correo_electronico = ?";
            	    PreparedStatement statement = conexion.prepareStatement(sql);
            	    statement.setString(1, correo);

		    // Ejecutar la sentencia de inserción
		    int filasEliminadas = statement.executeUpdate();

		    // Verificar si la eliminación fue exitosa
		    if (filasEliminadas > 0) {
		        System.out.println("Se ha eliminado el usuario correctamente.");
		    } else {
		        System.out.println("No se encontró el usuario para eliminar.");
		    }

		    // Cerrar la conexión
		    statement.close();
		    conexion.close();
		} catch (ClassNotFoundException e) {
		    System.out.println("insert No se pudo cargar el controlador: " + e.getMessage());
		} catch (SQLException e) {
		    System.out.println("insert Error al conectar a la base de datos: " + e.getMessage());
		}
            }
            
            public void meterPedido(String id, String usuario, Float importe) {
    

		// Intentar establecer la conexión y ejecutar la consulta
		try {
		    // Cargar el controlador de la base de datos
		    Class.forName("org.postgresql.Driver");

		    // Establecer la conexión
		    Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

		    // Crear una declaración para ejecutar la consulta
		    //Statement statement = conexion.createStatement();

		    // Ejecutar la consulta SQL para seleccionar todos los usuarios
		    String sql = "INSERT INTO pedidos (id_pedido, usuario, importe) VALUES (?, ?, ?)";
		    PreparedStatement statement = conexion.prepareStatement(sql);
		    statement.setString(1, id);
		    statement.setString(2, usuario);
		    statement.setFloat(3, importe);

		    // Ejecutar la sentencia de inserción
		    int filasInsertadas = statement.executeUpdate();

		    // Verificar si la inserción fue exitosa
		    if (filasInsertadas > 0) {
		        System.out.println("Se ha insertado el nuevo pedido correctamente.");
		    } else {
		        System.out.println("No se pudo insertar el nuevo pedido.");
		    }

		    // Cerrar la conexión
		    statement.close();
		    conexion.close();
		} catch (ClassNotFoundException e) {
		    System.out.println("insert No se pudo cargar el controlador: " + e.getMessage());
		} catch (SQLException e) {
		    System.out.println("insert Error al conectar a la base de datos: " + e.getMessage());
		}
            }
        }
