package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;



/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";

	private static final String FIELD_SEPARATOR = ";";

	public DatabaseJSonReader(){}

	public String parse(String jsonFileName,Handler handler) throws IOException {

		InputStream usersIS = new FileInputStream (new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		while (reader.hasNext()) {
			String name = reader.nextName();			
			readData.append(handler.leer(reader,name));			
		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}


	// Parses the contents of a medicine list.
	private StringBuffer readMedicines(JsonReader reader) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medicineData.append(readMedicineEntry(reader)).append("\n");
			reader.endObject();
		}
		medicineData.append("\n");
		reader.endArray();
		return medicineData;
	}
	// Parses the contents of a medicine. 
	private String readMedicineEntry(JsonReader reader) throws IOException {
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

	// Parses the contents of a medicine list.
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
