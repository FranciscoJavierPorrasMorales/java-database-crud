import java.util.ArrayList;
import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner entradaDatos= new Scanner(System.in);
		GestorBaseDeDatos db= new GestorBaseDeDatos("localhost", "mysql", "alumnos", "root", "");
		ArrayList<Alumno> alumnos= new ArrayList<Alumno>();
		String nombre="";
		String apellidos="";
		int edad=0;
		int i=0;
		int id=0;
		String nombreFichero="";
		
		GestorFicheros gf=new GestorFicheros();
		
		int res=0;
	
	
		int opcion=-1;
		if (db.conectar())
		{
			
			//
			
			System.out.println("Conexion correcta");
			//CRUD   
			/*
			 * C-> Create --> INSERT
			 * R -> Read --> SELECT
			 * U -> Update --> UPDATE
			 * D -> Delete --> DELETE
			 */
			do
			{
				//Muestro el menú infinitamente
				System.out.println(" GESTOR DE BASE DE DATOS");
				System.out.println(" 1) Insertar un nuevo alumno");
				System.out.println(" 2) Borrar un alumno");
				System.out.println(" 3) Actualizar nombre de un alumno");
				System.out.println(" 4) Listado de alumnos");
				System.out.println(" 5) Exportar a CSV");
				System.out.println(" 0) Salir");
				System.out.print(" Seleccione una opción:");
				opcion= entradaDatos.nextInt();
				
				if (opcion==1)
				{
					//Insertar
					System.out.print("----- Insertar datos\n");
					
					System.out.println("Introduce el nombre: ");
					nombre= entradaDatos.next(); 
					
					System.out.println("Introduce el apellidos: ");
					apellidos= entradaDatos.next(); 
					
					System.out.println("Introduce la edad: ");
					edad= entradaDatos.nextInt();
					
					res=db.insertar(nombre,apellidos, edad);
						
					if (res==-1)
					{
						System.out.println("Se ha producido un error en la conexión");
					}
					else if (res==-2)
					{
						System.out.println("Se ha producido un error en la comunicación");
					}
					else if (res==0)
					{
						System.out.println("No se ha podido crear el nuevo alumno");
					}
					else
					{
						System.out.println("El alumno "+ nombre + " se ha creado correctamente");
					}
					
					
				}
				else if (opcion==2)
				{
					//Borrar
					System.out.print("----- Borrar datos");
					alumnos= db.listar();
					if (alumnos.size()>0)
					{
						//Recorrer todos los alumnos
						for (i=0; i< alumnos.size(); i++)
						{
							System.out.println(alumnos.get(i).getId()+ " - "+alumnos.get(i).getNombre()+ " - "+ alumnos.get(i).getApellidos()+ " - "+ " - "+ alumnos.get(i).getEdad());
							
							
						}
						
					}
					else if (alumnos.size()==0)
					{
						System.out.println("No existen alumnos en la base de datos");
					}
					else
					{
						System.out.println("Se ha producido un error al listar los alumnos");
					}
					
					
					System.out.println("Introduce el id del alumno a borrar: ");
					id= entradaDatos.nextInt();
					
					res=db.borrar(id);
					
					if (res==-1)
					{
						System.out.println("Se ha producido un error en la conexión");
					}
					else if (res==-2)
					{
						System.out.println("Se ha producido un error en la comunicación");
					}
					else if (res==0)
					{
						System.out.println("No se ha podido eliminar el alumno");
					}
					else
					{
						System.out.println("El alumno con id "+id+" se ha borrado correctamente");
					}
					
					
					
				}
				else if (opcion==3)
				{
					//Actualizar
					System.out.print("----- Actualizar datos");
					
					alumnos= db.listar();
					if (alumnos.size()>0)
					{
						//Recorrer todos los alumnos
						for (i=0; i< alumnos.size(); i++)
						{
							System.out.println(alumnos.get(i).getId()+ " - "+alumnos.get(i).getNombre()+ " - "+ alumnos.get(i).getApellidos()+ " - "+ " - "+ alumnos.get(i).getEdad());
							
							
						}
						
					}
					else if (alumnos.size()==0)
					{
						System.out.println("No existen alumnos en la base de datos");
					}
					else
					{
						System.out.println("Se ha producido un error al listar los alumnos");
					}
					
					
					System.out.println("Introduce el id del alumno a modificar: ");
					id= entradaDatos.nextInt();
					
					System.out.println("Introduce el nombre a modificar: ");
					nombre= entradaDatos.next(); 
					
					
					res=db.actualizar(id,nombre);
					
					if (res==-1)
					{
						System.out.println("Se ha producido un error en la conexión");
					}
					else if (res==-2)
					{
						System.out.println("Se ha producido un error en la comunicación");
					}
					else if (res==0)
					{
						System.out.println("No se ha podido actualizar el alumno");
					}
					else
					{
						System.out.println("El alumno con id "+id+" se ha modificado correctamente");
					}
					
					
					
					
					
				}
				else if (opcion==4)
				{
					////Listar
					System.out.print("----- Listar datos");
					
					alumnos= db.listar();
					if (alumnos.size()>0)
					{
						//Recorrer todos los alumnos
						for (i=0; i< alumnos.size(); i++)
						{
							System.out.println(alumnos.get(i).getId()+ " - "+alumnos.get(i).getNombre()+ " - "+ alumnos.get(i).getApellidos()+ " - "+ " - "+ alumnos.get(i).getEdad());
							
							
						}
						
					}
					else if (alumnos.size()==0)
					{
						System.out.println("No existen alumnos en la base de datos");
					}
					else
					{
						System.out.println("Se ha producido un error al listar los alumnos");
					}
					
					
					
				}
				else if (opcion==5)
				{
					//Exportar
					System.out.print("----- Exportar datos");
					String formatoCSV="id;nombre;apellidos;edad\n";
					alumnos= db.listar();
					if (alumnos.size()>0)
					{
						
						System.out.println("\nIntroduce el nombre del fichero a exportar: ");
						nombreFichero= entradaDatos.next(); 
						
						
						//Recorrer todos los alumnos
						for (i=0; i< alumnos.size(); i++)
						{
							formatoCSV+=alumnos.get(i).getId()+ ";"+alumnos.get(i).getNombre()+ ";"+ alumnos.get(i).getApellidos()+ ";"+ alumnos.get(i).getEdad()+"\n";
							
						}
						System.out.println("----------------------------------");
						System.out.println(formatoCSV);
						System.out.println("----------------------------------");
						
						if (gf.escribirFichero(nombreFichero,formatoCSV))
						{
							System.out.println("Se ha exportado correctamente");
						}
						else
						{
							System.out.println("No se ha podido exportar");	
						}
						
						
					}
					else if (alumnos.size()==0)
					{
						System.out.println("No existen alumnos en la base de datos");
					}
					else
					{
						System.out.println("Se ha producido un error al exportar los alumnos");
					}
					
					
				}
				else if (opcion==0)
				{
					System.out.println("Programa finalizado");
				}
				else
				{
					System.out.println("Opción errónea");
				}
				
				
				
				
				
			}while(opcion!=0);
			
			
			
			
			
		}
		else
		{
			System.out.println("Error al intentar la conexión");
		}
		
		
	}

}
