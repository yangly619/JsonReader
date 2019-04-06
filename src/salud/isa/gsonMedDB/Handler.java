package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class Handler  {
	
	protected Handler nextHandler;
	 
    public Handler getNextHandler() {
        return nextHandler;
    }
 
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public abstract StringBuffer leer(JsonReader reader,String name) throws IOException;
}
