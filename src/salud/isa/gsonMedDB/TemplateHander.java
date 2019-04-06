package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class TemplateHander implements Handler {
	private String TAGNAME;
	protected Handler next;
	
	public TemplateHander(String TAGNAME) {
		this.TAGNAME=TAGNAME;
		
	}
		
	@Override
	public StringBuffer leer(JsonReader reader,String name) throws IOException  {
		StringBuffer readData = new StringBuffer();
		if (name.equals(TAGNAME) ) {
			readData.append(readData(reader)).append("\n");
		}else if(this.getNextHandler()!=null)  {
			readData=(next.leer(reader,name));
		}else{
			reader.skipValue();
			System.err.println("Category " + name + " not processed.");
			
		}
		return readData; 
	}
	
	public void setNext(Handler nextHandler) {
		next=nextHandler;
	}
	 public Handler getNextHandler() {
	        return next;
	    }
	
	 private StringBuffer readData(JsonReader reader) throws IOException {
			StringBuffer ActiveIngData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				ActiveIngData.append(readDataEntry(reader)).append("\n");
				reader.endObject();
			}
			ActiveIngData.append("\n");
			reader.endArray();
			return ActiveIngData;
		}
	 
	 public abstract String readDataEntry(JsonReader reader)throws IOException;

}
