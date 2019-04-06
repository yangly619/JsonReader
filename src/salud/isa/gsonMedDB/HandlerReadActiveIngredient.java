package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class  HandlerReadActiveIngredient implements Handler{
	
	private static final String ActiveIng_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";
	protected Handler next;
	
	
	@Override
	public StringBuffer leer(JsonReader reader,String name) throws IOException  {
		StringBuffer readData = new StringBuffer();
		if (name.equals(ActiveIng_TAGNAME) ) {
			readData.append(readActiveIng(reader)).append("\n");
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
	
	 private StringBuffer readActiveIng(JsonReader reader) throws IOException {
			StringBuffer medicineData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				medicineData.append(readActiveIngEntry(reader)).append("\n");
				reader.endObject();
			}
			medicineData.append("\n");
			reader.endArray();
			return medicineData;
		}
	 private String readActiveIngEntry(JsonReader reader) throws IOException {
			//	        reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
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
