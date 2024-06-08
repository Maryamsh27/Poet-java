import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PoetApplication {
	
	private static JFrame frame;
	private static JTextField poetField;
	private static JTextArea poetDisplay;
	private static JButton fetchButton;
	private static String apikey="bWFyeWFtOnBhc3N3b3Jk";
	private static URL url;
	private static String fetchpoetData(String poet) {
		try {
			URL url = new URL ("https://api.ganjoor.net/api/ganjoor/poets?q=\"+ poet + \"&appid\" + apikey");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("Get");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			String response = "";
			String line = reader.readLine();
			
			while ((line = reader.readLine()) !=null) {
				response += line ;
			}
			
			reader.close();
			
			JSONObject jsonObject = (JSONObject) JSONValue.parse(response.toString());
			JSONObject mainObj = (JSONObject) jsonObject.get("main");
			
			String poet1 = (String) mainObj.get("poet");
		}catch(Exception e){
				return "Failed to fetch poet .Please Check Your Internet";
				
			
			}
		
		
	} 
	
	public static void main (String args[]) {
		
		frame = new JFrame("PoetApplication");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
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

			private String poetInfo;

			@Override
			public void actionPerformed(ActionEvent e) {
				String poet = poetField.getText();
				String poetData = fetchpoetData(poet);
				poetDisplay.setText(poetInfo);
			   }
			});
		frame.setVisible(true);
	}
	
	}


