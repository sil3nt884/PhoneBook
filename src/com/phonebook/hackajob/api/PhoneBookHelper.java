package com.phonebook.hackajob.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.phonebook.hackajob.pojo.Contact;


@Component 
public class PhoneBookHelper {

	private String api = "http://www.mocky.io/v2/581335f71000004204abaf83";
	
	
	public ArrayList<Contact> getContacts (){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			JsonArray jsonArray = getContactsJSON();
			for(int i =0; i<jsonArray.size(); i++) {
				String name = jsonArray.get(i).getAsJsonObject().get("name").getAsString();
				String phonenumber = jsonArray.get(i).getAsJsonObject().get("phone_number").getAsString();
				String address = jsonArray.get(i).getAsJsonObject().get("address").getAsString();
				contacts.add(new Contact(i,name,phonenumber,address));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	
	private JsonArray getContactsJSON() throws IOException {
		URL url = new URL(api);
		InputStream in =url.openConnection().getInputStream();
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		StringBuilder jsonArray = new StringBuilder();
		String jsonLine = null;
		while((jsonLine = input.readLine()) !=null) {
			jsonArray.append(jsonLine);
		}
		JsonObject jsonObject = new JsonParser().parse(jsonArray.toString()).getAsJsonObject();
		return jsonObject.get("contacts").getAsJsonArray();
	}
}
