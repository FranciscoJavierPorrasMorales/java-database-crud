import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GestorBaseDeDatos {

	private Connection conn;
	private Statement stm;
	private ResultSet resultados;
	
	private String rutaServidor;
	private String tipoBaseDatos;
	private String nombreBaseDatos;
	private String usuario;
	private String contrasenya;
	
	
	public GestorBaseDeDatos()
	{
		this.conn=null;
		this.stm=null;
		this.resultados=null;
		
		this.rutaServidor= "";
		this.tipoBaseDatos= "";
		this.nombreBaseDatos= "";
		this.usuario= "";
		this.contrasenya= "";
		
	}
	
	
	public GestorBaseDeDatos(String r, String t, String n, String u, String c)
	{
		this.conn=null;
		this.stm=null;
		this.resultados=null;
		this.rutaServidor= r;
		this.tipoBaseDatos= t;
		this.nombreBaseDatos= n;
		this.usuario= u;
		this.contrasenya= c;
		
	}
	
	
	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}


	public Statement getStm() {
		return stm;
	}


	public void setStm(Statement stm) {
		this.stm = stm;
	}


	public ResultSet getResultados() {
		return resultados;
	}


	public void setResultados(ResultSet resultados) {
		this.resultados = resultados;
	}


	public String getRutaServidor() {
		return rutaServidor;
	}


	public void setRutaServidor(String rutaServidor) {
		this.rutaServidor = rutaServidor;
	}


	public String getTipoBaseDatos() {
		return tipoBaseDatos;
	}


	public void setTipoBaseDatos(String tipoBaseDatos) {
		this.tipoBaseDatos = tipoBaseDatos;
	}


	public String getNombreBaseDatos() {
		return nombreBaseDatos;
	}


	public void setNombreBaseDatos(String nombreBaseDatos) {
		this.nombreBaseDatos = nombreBaseDatos;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasenya() {
		return contrasenya;
	}


	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	
	//Método propios
	
	public boolean conectar()
	{
	
		try {
			conn= (Connection) DriverManager.getConnection("jdbc:" + tipoBaseDatos + "://" + rutaServidor + "/" + nombreBaseDatos,this.usuario,this.contrasenya);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
	
	public boolean conectar(String r, String t, String n, String u, String c)
	{
		try {
			conn= (Connection) DriverManager.getConnection("jdbc:" + t + "://" + r + "/" + n,u,c);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public ArrayList<Alumno> listar()
	{
		ArrayList<Alumno> salida= new ArrayList<Alumno>();
		
		if (this.conn!=null)
		{
			try {
				stm= (Statement) this.conn.createStatement();
				resultados= (ResultSet) stm.executeQuery("SELECT * FROM misalumnos");
				if (resultados!=null)
				{
					resultados.last();
					if (resultados.getRow()>0)
					{
						//Al menos hay  un resultado
						resultados.first();
						while(!resultados.isAfterLast())
						{
							int id= resultados.getInt(1);
							String nombre= resultados.getString(2);
							String apellidos= resultados.getString(3);
							int edad= resultados.getInt(4);
							
							
							Alumno alum= new Alumno(id, nombre, apellidos, edad);
							salida.add(alum);
							
							resultados.next();
						}
						
					}
					else
					{
						return salida;
					}
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}
			
		}
		
		return salida;
	}
	public int insertar(String nombre, String apellidos, int edad)
	{
		
		if (this.conn!=null)
		{
			String consulta="";
			consulta="INSERT INTO misalumnos (nombre, apellidos, edad) VALUES ('" + nombre + "','" + apellidos + "','" + edad + "')";
			try {
				stm= (Statement) this.conn.createStatement();
				return stm.executeUpdate(consulta);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return -2; //Error al establecer la comunicación
			}
			
			
		}
		return -1; //Error no hay conexion
	}
	public int borrar(int id)
	{
		
		if (this.conn!=null)
		{
			String consulta="";
			consulta="DELETE FROM misalumnos WHERE id="+ id;
			try {
				stm= (Statement) this.conn.createStatement();
				return stm.executeUpdate(consulta);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return -2; //Error al establecer la comunicación
			}
			
			
		}
		return -1; //Error no hay conexion
	}
	
	public int actualizar(int id, String nombre)
	{
		
		if (this.conn!=null)
		{
			String consulta="";
			consulta="UPDATE misalumnos SET nombre='"+nombre+"'   WHERE id="+ id;
			try {
				stm= (Statement) this.conn.createStatement();
				return stm.executeUpdate(consulta);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return -2; //Error al establecer la comunicación
			}
			
			
		}
		return -1; //Error no hay conexion
	}
	
	public boolean cerrarConexion()
	{
		if (this.conn!=null) {
			
			try {
				if (this.stm!=null)
				{
					this.stm.close();
				}
				if (this.resultados!=null)
				{
					this.resultados.close();
				}
				this.conn.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
				
			}
			
		}
		return false;
	}
	
	
	
}
