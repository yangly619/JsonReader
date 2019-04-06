package salud.isa.gsonMedDB;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {


	public static void main(String[] args) throws IOException {
		try{
			DatabaseJSonReader dbjp = new DatabaseJSonReader();
			HandlerReadMedicine readmed =new HandlerReadMedicine();
			HandlerRescueMedicinePresentations readresue =new HandlerRescueMedicinePresentations();
			readmed.setNext(readresue);
			readresue.setNext(null);
			
				try {
					System.out.println(dbjp.parse("datos.json",readmed));
				} finally {
				}
			} catch (FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
			
	}

}
