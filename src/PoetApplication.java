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

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.*;

public class PoetApplication {
	
	private static JFrame frame;
	private static JTextField poetField;
	private static JTextArea poetDisplay;
	private static JButton fetchButton;   	
	private static URL url;
	private static String fetchpoetData(String literature) {
		try {
			URL url = new URL ("https://api.ganjoor.net/api/ganjoor/poet?q="+literature);
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
			
			String name = (String) poetObj.get("name");
			String nickname = (String) poetObj.get("nickname "); 
	
			JSONArray catArray = (JSONArray) jsonObject.get("cat");
			JSONObject cat = (JSONObject) catArray.get(0);
			String description = (String) cat.get("description");
			
			return "Description :" +description + "name" + name+ "\nnickname" + nickname ;
			
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
		fetchButton = new JButton("Submit");
		poetDisplay = new JTextArea(10,30);
		poetDisplay.setEditable(false);
		
		frame.add( new JLabel("Enter Poet Name:"));
		frame.add(poetField);
		frame.add(fetchButton);
		frame.add(poetDisplay);
		
		
		fetchButton.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
				String literature = poetField.getText();
				String poetInfo = fetchpoetData(literature);
				poetDisplay.setText(poetInfo);
			   }
			});
		frame.setVisible(true);
	}
	
	}

