package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;



/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

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
}