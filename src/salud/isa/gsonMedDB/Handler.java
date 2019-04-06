package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public interface  Handler  {
	 
    public Handler getNextHandler();
 
    public void setNext(Handler nextHandler);
    
    public  StringBuffer leer(JsonReader reader,String name) throws IOException;
   
}
