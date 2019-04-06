package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class HandlerReadMedicine implements Handler{
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";
	protected Handler next;

	@Override
	public StringBuffer leer(JsonReader reader,String name) throws IOException  {
		StringBuffer readData = new StringBuffer();
		if (name.equals(MEDICINES_TAGNAME) ) {
			readData.append(readMedicine(reader)).append("\n");
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
	
	 private StringBuffer readMedicine(JsonReader reader) throws IOException {
			StringBuffer ActiveIngData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				ActiveIngData.append(readMedicineEntry(reader)).append("\n");
				reader.endObject();
			}
			ActiveIngData.append("\n");
			reader.endArray();
			return ActiveIngData;
		}
	 private String readMedicineEntry(JsonReader reader) throws IOException {

			String medName = null;
			while(reader.hasNext()){
				String name = reader.nextName();
				switch (name) {
				case NAME_FIELD_TAGNAME:
					medName = reader.nextString();
					break;
				default:
					reader.skipValue();
				}
			}

			return medName;
		}
}
