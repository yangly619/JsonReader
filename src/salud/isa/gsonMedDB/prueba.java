package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class prueba extends TemplateHander{
	private static final String MEDREF_FIELD_TAGNAME = "name";
	private static final String ACTINGREF_FIELD_TAGNAME = "image";	
	private String FIELD_SEPARATOR = ";";
	
	public prueba(String MEDICINES_TAGNAME) {
		super(MEDICINES_TAGNAME);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String readDataEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		int i=0;
		while(reader.hasNext() ){
			String name = reader.nextName();
			switch (name) {
			case MEDREF_FIELD_TAGNAME:
				medRef = reader.nextString();
				break;
			case ACTINGREF_FIELD_TAGNAME:
				aiRef = reader.nextString();
				break;
			default:
				reader.skipValue();
			}
		}			
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR ;
	}
	
}
