import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class PoetApplication {
	
	private static JFrame frame;
	private static JTextField poetField;
	private static JTextArea poetDisplay;
	private static JButton fetchButton;   	
	private static URL url;
	private static JTextArea TextArea;
	private static JButton Button;
	private static String fetchpoemData(String literature) {
		try {
			URL url = new URL ("https://api.ganjoor.net/api/ganjoor/poems/search"+literature);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			String response = "";
			String line ;
			
			while ((line = reader.readLine()) !=null) {
				response += line ;
			}
			
			reader.close();
			
			JSONObject jsonObject = (JSONObject) JSONValue.parse(response.toString());
			JSONObject poetObj = (JSONObject) jsonObject.get("poet");
			
			int id = (int) poetObj.get("id");
			String fulltitle = (String) poetObj.get("fulltitle");
			String plainText = (String) poetObj.get("plainText"); 
	
			JSONArray poetArray = (JSONArray) jsonObject.get("cat");
			JSONObject poet = (JSONObject) poetArray.get(0);
			String name = (String) poet.get("name");
			String nickname = (String) poet.get("nickname");
			String description = (String) poet.get("description");
			
			return "id:" + id + "Description :" + description + "title" + fulltitle + "\n plainText" + plainText + "\n name :"+ name + "\n nickname :" + nickname;
			
		}catch(Exception e){
				return "Failed to fetch poem .Please Check Your Internet";
				
			
			}
	
	} 
	
	private static String fetchpoetData(String Biography) {
		try {
			URL url = new URL ("https://api.ganjoor.net/api/ganjoor/poets/secure");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			String response = "";
			String line ;
			
			while ((line = reader.readLine()) !=null) {
				response += line ;
			}
			
			reader.close();
			
			JSONObject jsonObject = (JSONObject) JSONValue.parse(response.toString());
			JSONObject poetObj = (JSONObject) jsonObject.get("id");
			
			int id = (int) poetObj.get("id");
			String birthYearInLHijri = (String) poetObj.get("birthYearInLHijri");
			String birthPlace = (String) poetObj.get("birthPlace"); 
			String deathPlace = (String) poetObj.get("deathPlace"); 
			
			JSONArray IdArray = (JSONArray) jsonObject.get("");
			JSONObject poet = (JSONObject) IdArray.get(0);
			String name = (String) poet.get("name");
			String nickname = (String) poet.get("nickname");
			String description = (String) poet.get("description");
			
			return "ID:" + id + "Description :" + description + "BirthYearInLHijri" + birthYearInLHijri + "\n BirthPlace" + birthPlace + "\n DeathPlace" + deathPlace + "\n Name :"+ name + "\n Nickname :" + nickname;
			
		}catch(Exception e){
				return "Failed to fetch poet .Please Check Your Internet";
				
			
			}
	
	} 
	
	public static void main (String args[]) {
		
		frame = new JFrame("PoetApplication");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setLayout(new FlowLayout());
		
		
		poetField = new JTextField(16);
		fetchButton = new JButton("Poem");
		Button = new JButton("Bio");
		poetDisplay = new JTextArea(30,30);
		TextArea = new JTextArea(30,30);
		poetDisplay.setEditable(false);
		
		frame.add( new JLabel("Enter the part of poem :"));
		frame.add(poetField);
		frame.add(fetchButton);
		frame.add(Button);	
		frame.add(poetDisplay);
		frame.add(TextArea);
		
		
		
		
		fetchButton.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				String literature = poetField.getText();
				String poetInfo = fetchpoemData(literature);
				poetDisplay.setText(poetInfo);
			   }
			});
		
		Button.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				String Biography = poetField.getText();
				String poetInfo = fetchpoetData(Biography);
				TextArea.setText(poetInfo);
			   }
			});
		frame.setVisible(true);
	}
	
	}

