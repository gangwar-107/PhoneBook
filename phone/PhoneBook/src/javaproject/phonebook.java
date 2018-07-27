package javaproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class phonebook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	List list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					phonebook frame = new phonebook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	void fill(String st) 
	{
		try
		{
			File f=new File("e:\\phone.txt");
			FileReader fw=new FileReader(f);
			BufferedReader br=new BufferedReader(fw);
			list.removeAll();
			String str;
			while((str=br.readLine())!=null)
			{
				if(str.startsWith(st))
				list.add(str.split(",")[0]);
			}
			br.close();
			fw.close();
		}
		catch(Exception e)
		{
		 
		}
	}
	
	void find(String st) 
	{
		try
		{
			File f=new File("e:\\phone.txt");
			FileReader fw=new FileReader(f);
			BufferedReader br=new BufferedReader(fw);
			list.removeAll();
			String str; 
			while((str=br.readLine())!=null)
			{
				if(str.split(",")[0].equals(st))
				  JOptionPane.showMessageDialog(null,str.split(",")[1]);
			}
			br.close();
			fw.close();
		}
		catch(Exception e)
		{
		 JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public phonebook() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 298, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(36, 11, 221, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(36, 52, 221, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
			   try
			   {
				File f=new File("e:\\phone.txt");
				FileWriter fw=new FileWriter(f,true);
				PrintWriter pw=new PrintWriter(fw);
				pw.println(textField.getText()+","+textField_1.getText());
				pw.close();
				fw.close();
				list.add(textField.getText()+","+textField_1.getText());
				JOptionPane.showMessageDialog(null,"Data Saved");
				textField.setText("");
				textField_1.setText("");
				
			   }
			   catch(Exception e)
			   {
				 JOptionPane.showMessageDialog(null,e.getMessage());  
			   }				
			}
		});
		btnSave.setBounds(92, 99, 89, 23);
		contentPane.add(btnSave);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0)
			{
			    fill(textField_2.getText());  
			}
		});
		textField_2.setBounds(36, 144, 221, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		list = new List();
		list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0)
			{
			  
			  find(list.getSelectedItem());
			}
		});
		list.setBounds(36, 209, 221, 159);
		contentPane.add(list);
	    fill(null); 
	}
}
