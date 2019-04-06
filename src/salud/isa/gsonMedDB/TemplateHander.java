package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class TemplateHander implements Handler {
	private String TAGNAME;
	protected Handler next;
	
	public TemplateHander(String TAGNAME) {
		this.TAGNAME=TAGNAME;		
	}
	
	public String getTAGNAME() {
		return TAGNAME;
	}
	
	@Override
	public StringBuffer leer(JsonReader reader,String name) throws IOException  {
		StringBuffer readData = new StringBuffer();
		if (name.equals(getTAGNAME()) ) {
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
			StringBuffer Data = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				Data.append(readDataEntry(reader)).append("\n");
				reader.endObject();
			}
			Data.append("\n");
			reader.endArray();
			return Data;
		}
	 
	 public abstract String readDataEntry(JsonReader reader)throws IOException;

}
