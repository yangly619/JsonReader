package salud.isa.gsonMedDB;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {


	public static void main(String[] args) throws IOException {
		try{
			DatabaseJSonReader dbjp = new DatabaseJSonReader();
			HandlerReadMedicine readmed =new HandlerReadMedicine();
			HandlerRescueMedicinePresentations readresue =new HandlerRescueMedicinePresentations();
			HandlerReadActiveIngredient activIng=new HandlerReadActiveIngredient();
			prueba p=new prueba("physiotherapies");
			readmed.setNext(activIng);
			activIng.setNext(readresue);
			
			readresue.setNext(p);
			p.setNext(null);
			
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
