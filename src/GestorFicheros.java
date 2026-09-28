import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GestorFicheros {

	private File f;
	private FileWriter fw;
	private PrintWriter pw;
	
	
	public boolean escribirFichero(String ruta, String contenido)
	{
		
		try {
			f= new File(ruta);
			fw= new FileWriter(f);
			pw= new PrintWriter(fw);
			
			pw.println(contenido);
			
			pw.close();
			fw.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
		
		
	}
	
}
