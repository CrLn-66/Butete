package StudentInformationSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.UIManager;
import com.github.lgooddatepicker.components.DatePicker;

public class StudentInformationSystem {

	private JFrame frame;
	private JTextField sID;
	private JTextField fName;
	private JTextField mName;
	private JTextField lName;
	private JTextField Age;
	private JTextField mother;
	private JTextField father;
	private JTextField cp;
	private JTextField address;
	private JTable table_2;
	private static Connection con = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	 Class.forName("org.sqlite.JDBC");
					// con = DriverManager.getConnection("jdbc:sqlite:C:\\\\Users\\\\Von\\\\Desktop\\\\sql.db");
					 //con.setAutoCommit(false);
					StudentInformationSystem window = new StudentInformationSystem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentInformationSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JComboBox bading = new JComboBox();
		bading.setForeground(new Color(240, 128, 128));
		bading.setBackground(new Color(255, 228, 225));
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(255, 204, 204));
		frame.getContentPane().setForeground(new Color(204, 153, 51));
		frame.setBounds(100, 100, 1014, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		DefaultTableModel model = new DefaultTableModel();
		JLabel lblNewLabel = new JLabel("First Name : ");
		lblNewLabel.setBounds(20, 44, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		//StudentID, FirstName, MiddleName, LastName, Birthdate, Age, Gender, MothersnName, FathersName, CellphoneNumber, Address
		model.addColumn("");
		model.addColumn("Student ID");
	    model.addColumn("Name");
	    model.addColumn("Gender");
	    model.addColumn("Age");
	    model.addColumn("Birthday");
	    model.addColumn("Mother");
	    model.addColumn("Father");
	    model.addColumn("Cell No.");
	    model.addColumn("Address");
	    table_2 = new JTable(model);
	    table_2.setForeground(new Color(255, 182, 193));
	    table_2.setBackground(new Color(240, 128, 128));
	    table_2.getColumnModel().getColumn(0).setPreferredWidth(23);

	    table_2.getColumn("").setCellRenderer(new JCheckRender());
	    table_2.getColumn("").setCellEditor(new JCheckBoxEditor());
		JLabel lblNewLabel_1 = new JLabel("Middle Name :");
		lblNewLabel_1.setBounds(20, 69, 103, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name : ");
		lblNewLabel_2.setBounds(20, 90, 86, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Birthdate :");
		lblNewLabel_3.setBounds(20, 115, 86, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Age : ");
		lblNewLabel_4.setBounds(20, 140, 59, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gender :");
		lblNewLabel_5.setBounds(20, 163, 59, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mother's Name :");
		lblNewLabel_6.setBounds(20, 188, 103, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Father's Name :");
		lblNewLabel_7.setBounds(20, 213, 103, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Cellphone Number : ");
		lblNewLabel_8.setBounds(20, 236, 127, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Student ID :");
		lblNewLabel_9.setFont(UIManager.getFont("Button.font"));
		lblNewLabel_9.setBounds(20, 25, 86, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Address : ");
		lblNewLabel_10.setBounds(20, 261, 86, 14);
		frame.getContentPane().add(lblNewLabel_10);
		
		sID = new JTextField();
		sID.setBackground(new Color(255, 228, 225));
		sID.setBounds(93, 22, 201, 17);
		frame.getContentPane().add(sID);
		sID.setColumns(10);
		
		fName = new JTextField();
		fName.setBackground(new Color(255, 228, 225));
		fName.setBounds(93, 44, 201, 17);
		frame.getContentPane().add(fName);
		fName.setColumns(10);
		
		mName = new JTextField();
		mName.setBackground(new Color(255, 228, 225));
		mName.setBounds(103, 69, 191, 17);
		frame.getContentPane().add(mName);
		mName.setColumns(10);
		
		lName = new JTextField();
		lName.setBackground(new Color(255, 228, 225));
		lName.setBounds(103, 90, 191, 17);
		frame.getContentPane().add(lName);
		lName.setColumns(10);
		
		Age = new JTextField();
		Age.setBackground(new Color(255, 228, 225));
		Age.setColumns(10);
		Age.setBounds(62, 137, 232, 17);
		frame.getContentPane().add(Age);
		
		mother = new JTextField();
		mother.setBackground(new Color(255, 228, 225));
		mother.setColumns(10);
		mother.setBounds(120, 185, 174, 17);
		frame.getContentPane().add(mother);
		
		father = new JTextField();
		father.setBackground(new Color(255, 228, 225));
		father.setColumns(10);
		father.setBounds(120, 210, 174, 17);
		frame.getContentPane().add(father);
		
		cp = new JTextField();
		cp.setBackground(new Color(255, 228, 225));
		cp.setColumns(10);
		cp.setBounds(132, 233, 162, 17);
		frame.getContentPane().add(cp);
		
		address = new JTextField();
		address.setBackground(new Color(255, 228, 225));
		address.setColumns(10);
		address.setBounds(89, 258, 205, 17);
		frame.getContentPane().add(address);
		DatePicker Bdate = new DatePicker();
		Bdate.getComponentDateTextField().setBackground(Color.PINK);
		Bdate.setBounds(90, 113, 204, 20);
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setForeground(new Color(240, 128, 128));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			
			
			public void mouseClicked(MouseEvent e) {
				
				String sid=sID.getText();
				String fname=fName.getText();
				String mname=mName.getText();
				String lname=lName.getText();
				String bday=Bdate.getDateStringOrEmptyString();
				String age=Age.getText();
				String gender = bading.getSelectedItem().toString();	
				String mtname=mother.getText(); 
				String ftname=father.getText(); 
				String cnum=cp.getText();
				String addr=address.getText();
				
				try {
					PreparedStatement stmt= con.prepareStatement( "INSERT INTO Students (StudentID, FirstName, MiddleName, LastName, Birthdate, Age, Gender, MothersnName, FathersName, CellphoneNumber, Address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					stmt.setString(1, sid);
					stmt.setString(2, fname);
					stmt.setString(3, mname);
					stmt.setString(4, lname);
					stmt.setString(5, bday); 
					stmt.setString(6, age);
					stmt.setString(7, gender);
					stmt.setString(8, mtname);
					stmt.setString(9, ftname);
					stmt.setString(10, cnum);
					stmt.setString(11, addr);
					
					stmt.executeUpdate();
					con.commit();
					
					stmt.close();
					String query = "Select * from Students";
	                PreparedStatement pst = con.prepareStatement(query);
	                ResultSet resultset = pst.executeQuery();
	                while(resultset.next()) {
	                	model.addRow(new Object[] {false, resultset.getString("StudentID"), resultset.getString("FirstName")+ " " +resultset.getString("LastName"), resultset.getString("Gender"), resultset.getString("Age"), resultset.getString("Birthdate"),resultset.getString("MothersnName"),resultset.getString("FathersName"),resultset.getString("CellphoneNumber"),resultset.getString("Address")});
	                }
			 }catch (Exception j) {
		            
		            j.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(426, 317, 180, 23);
		frame.getContentPane().add(btnNewButton);
		JButton btnNewButton_3 = new JButton("DELETE");
		btnNewButton_3.setForeground(new Color(240, 128, 128));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cons = 0;
				for(int i = 0; i < table_2.getRowCount(); i++) {
					boolean isSel = (boolean) table_2.getValueAt(i, 0);
					PreparedStatement stmt = null;
					if(isSel) {
						System.out.println("Deleting a row in the table...");
					    String sql = "DELETE FROM Students WHERE StudentID = ?";
					      try {
							stmt = con.prepareStatement(sql);
						      stmt.setString(1, (String) table_2.getValueAt(i, 1));
						      stmt.executeUpdate();
						      con.commit();
						      stmt.close();
						      cons++;
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				 Statement as;
				try {
					as = con.createStatement();
					ResultSet resultset = as.executeQuery("SELECT * FROM Students");
				      model.setRowCount(0);
						while(resultset.next()) {
							model.addRow(new Object[] {false, resultset.getString("StudentID"), resultset.getString("FirstName")+ " " +resultset.getString("LastName"), resultset.getString("Gender"), resultset.getString("Age"), resultset.getString("Birthdate"),resultset.getString("MothersnName"),resultset.getString("FathersName"),resultset.getString("CellphoneNumber"),resultset.getString("Address")});
				              
						}
						as.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			      
				JOptionPane.showMessageDialog(null, cons+" item deleted successfully!");
			}
		});
		btnNewButton_3.setBounds(695, 317, 180, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		
		bading.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Prefer not to say"}));
		bading.setBounds(76, 161, 218, 18);
		frame.getContentPane().add(bading);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(304, 11, 684, 295);
		frame.getContentPane().add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(table_2);
		
		
		frame.getContentPane().add(Bdate);
	}
}
class JCheckBoxEditor extends DefaultCellEditor {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3846878501975009951L;

	public JCheckBoxEditor() {
        super(new JCheckBox());
    }

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		super.addCellEditorListener(l);
	}
}
