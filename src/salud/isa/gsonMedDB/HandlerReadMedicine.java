package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class HandlerReadMedicine extends TemplateHander{

	private  static final String NAME_FIELD_TAGNAME = "name";
	
	public HandlerReadMedicine(String TAGNAME) {
		super(TAGNAME);
	}
	
	@Override
	public String readDataEntry(JsonReader reader) throws IOException {
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
