package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import CSV.CSVWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.google.gson.JsonSyntaxException;

import Artificial_Intelligence.DeepNetworkAbilities;
import Data.GameData;
import Internet.HTTP;
import Internet.PingTBA;
import JSONing.JSONGenerators;
import JSONing.JSON_Parsing;
import Telegram.MyAmazingBot;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtDirectoryToCsv;
	private JRadioButton rdbtnVentura;
	private JRadioButton rdbtnAzNorth;
	public static long Ping;
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	private JLabel lblNotActive;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("Destination Deep Space Scouting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 344);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(162,5,29));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnAzNorth = new JRadioButton("AZ North");
		rdbtnAzNorth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnVentura.setSelected(false);
				GameData.event="azfl";
			}
		});
		rdbtnAzNorth.setForeground(new Color(255,253,56));
		rdbtnAzNorth.setBounds(6, 6, 89, 23);
		contentPane.add(rdbtnAzNorth);
		
		rdbtnVentura = new JRadioButton("Ventura");
		rdbtnVentura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAzNorth.setSelected(false);
				GameData.event="cave";
			}
		});
		rdbtnVentura.setForeground(new Color(255,253,56));
		rdbtnVentura.setBounds(6, 41, 89, 23);
		contentPane.add(rdbtnVentura);
		
		JButton btnSyncData = new JButton("Sync Data");
		btnSyncData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotActive.setText("--- Syncing Data ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
				 
				try {
					GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
					GameData.setMatchData();
				} catch (JsonSyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), basedir+"Match Data.txt");
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					CSVWriter.WriteGameData(basedir+"Match Data.csv");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNotActive.setText("--- Not Active ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
				 
				 
			}
		});
		btnSyncData.setBounds(6, 76, 117, 29);
		contentPane.add(btnSyncData);
		
		JButton btnUploadData = new JButton("Upload Data");
		btnUploadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotActive.setText("--- Uploading Data ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
				 
				try {
					try {
						GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					GameData.uploadMatchData(basedir+"Match Data.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNotActive.setText("--- Not Active ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
			}
		});
		btnUploadData.setBounds(6, 117, 117, 29);
		contentPane.add(btnUploadData);
		
		JButton btnTrainNetwork = new JButton("Train Net");
		btnTrainNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotActive.setText("--- Training Net ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
				 
				try {
					DeepNetworkAbilities.Train(1000, null, txtDirectoryToCsv.getText(), true);
					DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNotActive.setText("--- Not Active ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
			}
		});
		btnTrainNetwork.setBounds(6, 199, 117, 29);
		contentPane.add(btnTrainNetwork);
		
		JButton btnGenerateNet = new JButton("Generate Net");
		btnGenerateNet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotActive.setText("--- Generating Net ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
				 
				File file = new File(basedir+"DeepNetwork.zip");
				if(!file.exists()) {
					try {
						DeepNetworkAbilities.GenerateClassificationNet(100, 8, 25, 3);
						DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					System.out.println("Model Already Exists");
					try {
						DeepNetworkAbilities.loadModel(basedir+"DeepNetwork.zip");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				lblNotActive.setText("--- Not Active ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
			}
		});
		btnGenerateNet.setBounds(6, 158, 117, 29);
		contentPane.add(btnGenerateNet);
		
		JLabel lblDownloadDataFrom = new JLabel("Download Data from TBA");
		lblDownloadDataFrom.setForeground(new Color(255,253,56));
		lblDownloadDataFrom.setBounds(135, 81, 309, 16);
		contentPane.add(lblDownloadDataFrom);
		
		JLabel lblGetsDataFrom = new JLabel("Gets Data from previously downloaded file");
		lblGetsDataFrom.setForeground(new Color(255,253,56));
		lblGetsDataFrom.setBounds(135, 122, 309, 16);
		contentPane.add(lblGetsDataFrom);
		
		JLabel lblCreatesDeepLearning = new JLabel("Creates Deep Learning Network");
		lblCreatesDeepLearning.setForeground(new Color(255,253,56));
		lblCreatesDeepLearning.setBounds(135, 163, 309, 16);
		contentPane.add(lblCreatesDeepLearning);
		
		txtDirectoryToCsv = new JTextField();
		txtDirectoryToCsv.setForeground(Color.LIGHT_GRAY);
		txtDirectoryToCsv.setHorizontalAlignment(SwingConstants.CENTER);
		txtDirectoryToCsv.setText("Directory to CSV");
		txtDirectoryToCsv.setBounds(135, 199, 309, 23);
		contentPane.add(txtDirectoryToCsv);
		txtDirectoryToCsv.setColumns(10);
		
		JButton btnWriteNetData = new JButton("Write Net Data");
		btnWriteNetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotActive.setText("--- Writing Data ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
				try {
					CSVWriter.WritePredictedData(basedir+"Predicted Data.csv");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNotActive.setText("--- Not Active ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
			}
		});
		btnWriteNetData.setBounds(6, 240, 117, 29);
		contentPane.add(btnWriteNetData);
		
		JLabel lblMakesCsvFile = new JLabel("Makes CSV file of networks predictions");
		lblMakesCsvFile.setForeground(new Color(255,253,56));
		lblMakesCsvFile.setBounds(135, 245, 309, 16);
		contentPane.add(lblMakesCsvFile);
		
		lblNotActive = new JLabel("--- Not Active ---");
		lblNotActive.setForeground(new Color(255,253,56));
		lblNotActive.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotActive.setBounds(135, 10, 309, 19);
		contentPane.add(lblNotActive);
		JLabel lblNewLabel = new JLabel("Ping: "+Long.toString(Ping));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255,253,56));
		lblNewLabel.setBounds(135, 45, 309, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnServer = new JButton("Server");
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Running Server");
				lblNotActive.setText("--- Server is Starting up ---");
				lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
				
				ApiContextInitializer.init();
		    	TelegramBotsApi botsApi = new TelegramBotsApi();

		         try {
		             botsApi.registerBot(new MyAmazingBot()); 
		         } catch (TelegramApiException e1) {
		             e1.printStackTrace();
		         }
		         
		         lblNotActive.setText("--- Server is Running ---");
					lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
		        // lblNotActive.setText("--- Not Active ---");
					//lblNotActive.paintImmediately(lblNotActive.getVisibleRect());
			}
		});
		btnServer.setBounds(6, 281, 117, 29);
		contentPane.add(btnServer);
		
		JLabel lblStartsMessagingServer = new JLabel("Starts Messaging Server");
		lblStartsMessagingServer.setForeground(new Color(255,253,56));
		lblStartsMessagingServer.setBounds(135, 286, 309, 16);
		contentPane.add(lblStartsMessagingServer);
	}
}
