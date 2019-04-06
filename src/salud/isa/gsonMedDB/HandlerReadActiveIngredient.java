package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class  HandlerReadActiveIngredient extends TemplateHander{
	
	private static final String NAME_FIELD_TAGNAME = "name";
	
	public HandlerReadActiveIngredient(String TAGNAME) {
		super(TAGNAME);
	}

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
