package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class HandlerRescueMedicinePresentations extends TemplateHander{
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String FIELD_SEPARATOR = ";";
	public HandlerRescueMedicinePresentations(String TAGNAME) {
		super(TAGNAME);
	}

	public String readDataEntry(JsonReader reader) throws IOException {
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
