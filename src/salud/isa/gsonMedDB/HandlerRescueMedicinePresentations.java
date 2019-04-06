package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class HandlerRescueMedicinePresentations extends Handler{
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String FIELD_SEPARATOR = ";";
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	protected Handler next;
	
	
	public StringBuffer leer(JsonReader reader,String name) throws IOException  {
		StringBuffer readData = new StringBuffer();
		if (name.equals(RESCUEMEDPRES_TAGNAME)) {
			readData.append(readRescueMedicinePresentations(reader)).append("\n");
		} else if(this.getNextHandler()!=null) {
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
	
	private StringBuffer readRescueMedicinePresentations(JsonReader reader) throws IOException {
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueMedicinePresentationData.append(readRescueMedicinePresentationEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		reader.endArray();
		return rescueMedicinePresentationData;
	}
	// Parses the contents of a rescue medicine presentation entry
	private String readRescueMedicinePresentationEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			switch (name) {
			case MEDREF_FIELD_TAGNAME:
				medRef = reader.nextString();
				break;
			case ACTINGREF_FIELD_TAGNAME:
				aiRef = reader.nextString();
				break;
			case INHREF_FIELD_TAGNAME:
				inhRef = reader.nextString();
				break;
			case DOSE_FIELD_TAGNAME:
				dose = reader.nextString();
				break;
			default:
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR +
				inhRef + FIELD_SEPARATOR + dose;
	}
}
