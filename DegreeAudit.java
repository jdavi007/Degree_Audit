/*
 * CSC 2220 - Group Project
 * Jacob Davis, Kaylee Strope, Dylan Dray
 * November 22, 2024
 */


//--------------------------------------------------------------------------------------------------------------------------Imports
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


// DegreeAudit class start
public class DegreeAudit extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//---------------------------------------------------------------------------------------------------------------------Member Data
	private JPanel contentPane;
	private JPanel MainPanel;
	private CardLayout cardLayout;
	private JTextField firstNameTextField_updateRecord;		//
	private JTextField lastNameTextField_updateRecord;		//
	private JTextField yearTextField_updateRecord;			//
	private JTextField firstNameField_createRecord;			//
	private JTextField lastNameField_createRecord;			//
	private JTextField yearField_createRecord;				//
	private JTextField firstNameField_updateMajor; 			// All of these are used to update various GUI items
	private JTextField lastNameField_updateMajor;			//
	private JTextField currentMajorField;					//
	private JTextArea textArea_courseSummary; 				//
	private JTextArea textArea_available;					//
	private JTextArea textArea_inProgress;					//
	private JTextArea textArea_complete;					//
	private JTextArea textArea_registered;					//
	private JCheckBox CSmajorCheckBox_updateMajor;			//
	private JCheckBox SWEmajorCheckBox_updateMajor;			//
	private JCheckBox MISmajorCheckBox_updateMajor;			//
	private JCheckBox mathMajorCheckBox_updateMajor;		//
	private JTextField firstNameField_delteRecord;			//
	private JTextField lastNameField_deleteRecord;			//
	private JTextField yearField_deleteRecord;				//
	private String newOrUpdate = ""; // Used for determining if user is creating a new record or updating an existing one
	private JLabel changingLabel; // Used on the "Course Summary" panel to indicate which category of courses are displayed
	private JButton liberalArtsCoreButton; // Color-changing button indicates progress
	private JButton studiesButton; // Color-changing button indicates progress
	private JButton majorCoreButton; // Color-changing button indicates progress
	private JButton majorElectivesButton; // Color-changing button indicates progress
	private Color red = new Color(246, 97, 81);        //
	private Color orange = new Color(255, 190, 111);   // Colors for
	private Color yellow = new Color(249, 240, 107);   // color-changing buttons
	private Color green = new Color(143, 240, 164);    //
	private int confirmation = 0; // Used on pop-up confirmation windows
	
	//---------------------------------------------------------------------------------------------------------------------Student Info
	private String studentFirstName = "";
	private String studentLastName = "";
	private String studentCatalogYear = "";
	private String studentMajor = "";
	private String completedCourses = "";
	private String coursesInProgress = "";
	private String coursesRegistered = "";
	private StringBuilder libArts = new StringBuilder(); // variables to hold student's courses in each area
	private StringBuilder studies = new StringBuilder();
	private StringBuilder majorCore = new StringBuilder();
	private StringBuilder majorElec = new StringBuilder();
	private File studentRecord; // student record file variable
	
	//---------------------------------------------------------------------------------------------------------------------File data
	private String csMajor = null; // File content string declarations
	private String csElec = null; // TODO: Need to add csMathScienceRequirements.txt. I keep forgetting - Jacob
	private String csCourses = null;
	private String seCore = null;
	private String seElec = null;
	private String seMath = null;
	private String seCourses = null;
	private String comm = null;
	private String nati = null;
	private String self = null;
	private String world = null;
	private String libA = null;
	private String allCourses = null;
	
	File csFile = new File("res/csCore.txt"); // File variables
	File csElecFile = new File("res/csElectives.txt");
	File seCoreFile = new File("res/seCore.txt");
	File seElecFile = new File("res/seElectives.txt");
	File seMathFile = new File("res/seMathElectives.txt");
	File commFile = new File("res/contextualCourseworkCommunity.txt");
	File natiFile = new File("res/contextualCourseworkNation.txt");
	File selfFile = new File("res/contextualCourseworkSelf.txt");
	File worldFile = new File ("res/contextualCourseworkWorld.txt");
	File libAFile = new File("res/liberalArtsCore_2024.txt");
	
	Path csPath = csFile.toPath(); // Cast file data type to file path data type
	Path csElecPath = csElecFile.toPath();
	Path seCorePath = seCoreFile.toPath();
	Path seElecPath = seElecFile.toPath();
	Path seMathPath = seMathFile.toPath();
	Path commPath = commFile.toPath();
	Path natiPath = natiFile.toPath();
	Path selfPath = selfFile.toPath();
	Path worldPath = worldFile.toPath();
	Path libAPath = libAFile.toPath();

	//---------------------------------------------------------------------------------------------------------------------Launch the application
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					DegreeAudit frame = new DegreeAudit();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	//---------------------------------------------------------------------------------------------------------------------Create the frame
	public DegreeAudit() {
		setResizable(false);
		setBounds(new Rectangle(0, 0, 800, 800));
		setTitle("Degree Audit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		cardLayout = new CardLayout(0,0);
		
		
		MainPanel = new JPanel();
		MainPanel.setBounds(0, 0, 763, 647);
		contentPane.add(MainPanel);
		MainPanel.setLayout(cardLayout);
		
		//Getting course info from files
		try 
		{
			csMajor = Files.readString(csPath);
			csElec = Files.readString(csElecPath);
			seCore = Files.readString(seCorePath);
			seElec = Files.readString(seElecPath);
			seMath = Files.readString(seMathPath);
			comm = Files.readString(commPath);
			nati = Files.readString(natiPath);
			self = Files.readString(selfPath);
			world = Files.readString(worldPath);
			libA = Files.readString(libAPath);
			csCourses = csMajor + csElec;
			seCourses = seCore + seElec + seMath;
			allCourses = csCourses + seCourses + comm + nati + self + world + libA;
		}
		catch (Exception ex)
		{
			// Error message pop-up
			JOptionPane.showMessageDialog(null, 
					"Error: A problem was encountered loading a file.",
					"Degree Audit: Error",JOptionPane.PLAIN_MESSAGE);
		}
		
		//---------------------------------------------------------------------------------------------------------------------Start panel
		JPanel StartScreen = new JPanel();
		MainPanel.add(StartScreen, "start");
		
		JButton updateStudentRecordBtn = new JButton("Update Student Record"); // Update Student Record button
		updateStudentRecordBtn.setBounds(12, 23, 739, 100);
		updateStudentRecordBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Change card
				setCardLayoutView("update student record");
			}
		});
		StartScreen.setLayout(null);
		updateStudentRecordBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		StartScreen.add(updateStudentRecordBtn);
		
		
		JButton createStudentRecordBtn = new JButton("Create Student Record"); // Create Student Record button
		createStudentRecordBtn.setBounds(12, 193, 739, 100);
		createStudentRecordBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		createStudentRecordBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// Change card
				setCardLayoutView("create student record");
			}
		});
		StartScreen.add(createStudentRecordBtn);
		
		
		JButton updateMajorBtn = new JButton("Update Major"); // Update Major button
		updateMajorBtn.setBounds(12, 368, 739, 100);
		updateMajorBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		updateMajorBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Change card
				setCardLayoutView("update major");
			}
		});
		StartScreen.add(updateMajorBtn);
		
		
		JButton deleteStudentRecordBtn = new JButton("Delete Student Record"); // Delete Student Record button
		deleteStudentRecordBtn.setBounds(12, 535, 739, 100);
		deleteStudentRecordBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		deleteStudentRecordBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("delete record");
			}
		});
		StartScreen.add(deleteStudentRecordBtn);
		
		// Start Screen 'tab' traversal policy
		StartScreen.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]
				{updateStudentRecordBtn, createStudentRecordBtn, updateMajorBtn, deleteStudentRecordBtn}));
		
		//---------------------------------------------------------------------------------------------------------------------Update Record panel
		JPanel UpdateStudentRecord = new JPanel();
		MainPanel.add(UpdateStudentRecord, "update student record");
		UpdateStudentRecord.setLayout(null);
		
		JLabel stdntRecordLabel = new JLabel("Update Student Record"); // Page Title Label
		stdntRecordLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		stdntRecordLabel.setBounds(254, 0, 260, 15);
		UpdateStudentRecord.add(stdntRecordLabel);
		
		firstNameTextField_updateRecord = new JTextField();
		firstNameTextField_updateRecord.setFont(new Font("Dialog", Font.PLAIN, 16));
		firstNameTextField_updateRecord.setBounds(130, 63, 204, 25);
		UpdateStudentRecord.add(firstNameTextField_updateRecord);
		firstNameTextField_updateRecord.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name:"); // First Name label
		firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel.setBounds(12, 65, 114, 15);
		UpdateStudentRecord.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:"); // Last Name label
		lastNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lastNameLabel.setBounds(12, 100, 114, 15);
		UpdateStudentRecord.add(lastNameLabel);
		
		lastNameTextField_updateRecord = new JTextField(); // Last Name text field
		lastNameTextField_updateRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastNameTextField_updateRecord.setColumns(10);
		lastNameTextField_updateRecord.setBounds(130, 98, 204, 25);
		UpdateStudentRecord.add(lastNameTextField_updateRecord);
		
		JLabel catalogYearLabel = new JLabel("Catalog Year:"); // Catalog Year label
		catalogYearLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		catalogYearLabel.setBounds(12, 137, 133, 15);
		UpdateStudentRecord.add(catalogYearLabel);
		
		yearTextField_updateRecord = new JTextField(); // Catalog Year text field
		yearTextField_updateRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		yearTextField_updateRecord.setColumns(10);
		yearTextField_updateRecord.setBounds(156, 133, 178, 25);
		UpdateStudentRecord.add(yearTextField_updateRecord);
		
		JLabel majorLabel = new JLabel("Major:"); // Major label
		majorLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		majorLabel.setBounds(12, 208, 59, 15);
		UpdateStudentRecord.add(majorLabel);
		
		JCheckBox CSmajorCheckBox_updateRecord = new JCheckBox("CS"); // CS Major check box
		CSmajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		CSmajorCheckBox_updateRecord.setBounds(85, 205, 60, 23);
		UpdateStudentRecord.add(CSmajorCheckBox_updateRecord);
		
		JCheckBox SWEmajorCheckBox_updateRecord = new JCheckBox("SWE"); // SWE Major check box 
		SWEmajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		SWEmajorCheckBox_updateRecord.setBounds(149, 205, 64, 23);
		UpdateStudentRecord.add(SWEmajorCheckBox_updateRecord);
		
		JCheckBox MISmajorCheckBox_updateRecord = new JCheckBox("MIS"); // MIS Major check box
		MISmajorCheckBox_updateRecord.setEnabled(false);
		MISmajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		MISmajorCheckBox_updateRecord.setBounds(220, 205, 60, 23);
		UpdateStudentRecord.add(MISmajorCheckBox_updateRecord);
		
		JCheckBox mathMajorCheckBox_updateRecord = new JCheckBox("MATH"); // Math Major check box
		mathMajorCheckBox_updateRecord.setEnabled(false);
		mathMajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		mathMajorCheckBox_updateRecord.setBounds(290, 205, 76, 23);
		UpdateStudentRecord.add(mathMajorCheckBox_updateRecord);
		
		JButton nextButton_updateRecord = new JButton("Next"); // Next button
		nextButton_updateRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!studentFirstName.isEmpty()) // Check to see if record has been loaded 
				{
					newOrUpdate = "update";
				
					// Sorting student's courses
					StringBuilder studentCourses = new StringBuilder();
					studentCourses.append(coursesRegistered).append(coursesInProgress).append(completedCourses);
					String courses[] = studentCourses.toString().split("\n");
					String fourStudies = comm + nati + self + world;
				
				
					if(studentMajor == "Computer Science") // CS Major
					{
						for(String course : courses) // Loop through student's courses 
						{
							for( String coreClass : csMajor.split("\n")) // Check for major core course
							{
								if(course.equals(coreClass)) 
								{
									majorCore.append(course).append("\n");
								}
							}
						
							for(String elecCourse : csElec.split("\n")) // Check for major elective
							{
								if(course.equals(elecCourse)) 
								{
									majorElec.append(course).append("\n");
								}
							}
						
							for(String libCourse : libA.split("\n")) // Check for liberal arts course 
							{
								if(course.equals(libCourse)) 
								{
									libArts.append(course).append("\n");
								}
							}
						
							for(String studiesCourse : fourStudies.split("\n")) // Check for four studies course 
							{
								if(course.equals(studiesCourse)) 
								{
									studies.append(course).append("\n");
								}
							}
						}
					}
					else if(studentMajor == "Software Engineering") // SWE Major
					{
						for(String course : courses) // Loop through student's courses 
						{
							for( String coreClass : seCore.split("\n")) // Check for major core course
							{
								if(course.equals(coreClass)) 
								{
									majorCore.append(course).append("\n");
								}
							}
							
							for(String elecCourse : seElec.split("\n")) // Check for major elective
							{
								if(course.equals(elecCourse)) 
								{
									majorElec.append(course).append("\n");
								}
							}
							
							for(String libCourse : libA.split("\n")) // Check for liberal arts course 
							{
								if(course.equals(libCourse)) 
								{
									libArts.append(course).append("\n");
								}
							}
							
							for(String studiesCourse : fourStudies.split("\n")) // Check for four studies course 
							{
								if(course.equals(studiesCourse)) 
								{
									studies.append(course).append("\n");
								}
							}
						}
					} // Didn't bother with other majors due to lack of course files for them
				
					//----------------------------------------Change button colors
					// No progress = red
					if(majorCore.isEmpty()) 
					{
						majorCoreButton.setBackground(red); // Major Core
					}
				
					if(majorElec.isEmpty()) 
					{
						majorElectivesButton.setBackground(red); // Major Electives
					}
				
					if(libArts.isEmpty()) 
					{
						liberalArtsCoreButton.setBackground(red); // Liberal Arts
					}
				
					if(studies.isEmpty()) 
					{
						studiesButton.setBackground(red); // Studies
					}
				
					// Registered = orange
					for(String regCourse : coursesRegistered.split("\n"))  
					{
						// Major Core
						for(String majorCourse : majorCore.toString().split("\n")) 
						{
							if(regCourse.equals(majorCourse)) 
							{
								majorCoreButton.setBackground(orange);
								break;
							}
						}
					
						// Major Electives
						for(String elecCourse : majorElec.toString().split("\n")) 
						{
							if(regCourse.equals(elecCourse)) 
							{
								majorElectivesButton.setBackground(orange);
								break;
							}
						}
					
						// Liberal Arts
						for(String libCourse : libArts.toString().split("\n")) 
						{
							if(regCourse.equals(libCourse)) 
							{
								liberalArtsCoreButton.setBackground(orange);
								break;
							}
						}
					
						// Studies
						for(String studCourse : studies.toString().split("\n")) 
						{
							if(regCourse.equals(studCourse)) 
							{
								studiesButton.setBackground(orange);
								break;
							}
						}
					}
				
					// In progress = yellow
					for(String progCourse : coursesInProgress.split("\n"))  
					{
						// Major Core
						for(String majorCourse : majorCore.toString().split("\n")) 
						{
							if(progCourse.equals(majorCourse)) 
							{
								majorCoreButton.setBackground(yellow);
								break;
							}
						}
					
						// Major Electives
						for(String elecCourse : majorElec.toString().split("\n")) 
						{
							if(progCourse.equals(elecCourse)) 
							{
								majorElectivesButton.setBackground(yellow);
								break;
							}
						}
					
						// Lib Arts
						for(String libCourse : libArts.toString().split("\n")) 
						{
							if(progCourse.equals(libCourse)) 
							{
								liberalArtsCoreButton.setBackground(yellow);
								break;
							}
						}
					
						// Studies
						for(String studCourse : studies.toString().split("\n")) 
						{
							if(progCourse.equals(studCourse)) 
							{
								studiesButton.setBackground(yellow);
								break;
							}
						}
					}
				
					// Completed = green
					// CS Major
					if(studentMajor == "Computer Science") 
					{
						// Major Core
						// Student's course strings have an extra "\n" somehow so length is incorrect
						if(csMajor.length() == (majorCore.length()-1))
						{
							majorCoreButton.setBackground(green);
						}
				
						// TODO: CSC Major Electives
						// MTH/STA 2180, CSC 3710, 1 3k/4k level math
						// 2k level programming class, 9 hours upper division course work
						
					}
					else if(studentMajor == "Software Engineering") 
					{
						// Major Core
						// Student's course strings have an extra "\n" somehow so length is incorrect
						if(seCore.length() == (majorCore.length()-1))
						{
							majorCoreButton.setBackground(green);
						}
				
						// TODO: SWE Major Electives
						// 2 3k/4k level SWE Courses - 3240, 3420, or 4240
						// CSC 1010, 1180, 2180, 2300, 3180 3660, 3400 all required
						// Math: CSC 3710, MTH 2040, 2050, MTH/STA 2180, MTH 3030
						// At least 30 hours of math & science courses
						// 1 math course 3060 or higher
						// 9 hours from the following:
						// CSC 2220, 2230, 2240, 4000, 4110, 4150, 4200, 4280, 4281,
						// 4300, 4350, 4380, 4690, 4950, 4960
						// DSC/MIS 4350, 4500
						
					}
				
					// Liberal Arts - minimum 48 hours
					// Instead of finding each required course, calculate hours of liberal arts courses student has had
					// & compare with minimum required hours
					String libAcourses[] = libArts.toString().split("\n");
					int totalLibArtsHours = 0;
					
					// loop through student's liberal arts courses
					for(String course : libAcourses) 
					{
						// adding course hours to total 
						totalLibArtsHours += Integer.parseInt(String.valueOf(course.charAt(course.length()-1)));
					}
					
					if(totalLibArtsHours >= 48) // If requirement is met, turn button green
					{
						liberalArtsCoreButton.setBackground(green);
					}
				
					// 4 studies - minimum 21 hours
					// Did the same as process as liberal arts for the studies courses
					String studiesCourses[] = libArts.toString().split("\n");
					int totalStudiesHours = 0;
					
					for(String course : studiesCourses) 
					{
						totalStudiesHours += Integer.parseInt(String.valueOf(course.charAt(course.length()-1)));
					}
					
					if(totalStudiesHours >= 21) // If requirement is met, turn button green
					{
						studiesButton.setBackground(green);
					}
				
					// Change card
					setCardLayoutView("degree progress");
				}
				else // No student loaded - can't update record 
				{
					// Error message pop-up
					JOptionPane.showMessageDialog(null, 
							"Error: No student record has been loaded.",
							"Degree Audit: Record Not Found",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		nextButton_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		nextButton_updateRecord.setBounds(634, 610, 117, 25);
		UpdateStudentRecord.add(nextButton_updateRecord);
		
		JButton loadStudentButton_updateRecord = new JButton("Load Student Record"); // Load Student Record button
		loadStudentButton_updateRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// open file explorer & get student info
				loadStudent();
				firstNameTextField_updateRecord.setText(studentFirstName);
				lastNameTextField_updateRecord.setText(studentLastName);
				yearTextField_updateRecord.setText(studentCatalogYear);
				
				// Set major checked
				if(studentMajor == "Computer Science") 
				{
					CSmajorCheckBox_updateRecord.doClick();
				}
				else if(studentMajor == "Software Engineering") 
				{
					SWEmajorCheckBox_updateRecord.doClick();
				}
				else if(studentMajor == "Management Information Systems") 
				{
					MISmajorCheckBox_updateRecord.doClick();
				}
				else if(studentMajor == "Mathematics") 
				{
					mathMajorCheckBox_updateRecord.doClick();
				}
				
			}
		});
		loadStudentButton_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		loadStudentButton_updateRecord.setBounds(254, 610, 260, 25);
		UpdateStudentRecord.add(loadStudentButton_updateRecord);
		
		JButton backButton_updateRecord = new JButton("Back"); // Back button
		backButton_updateRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Reset text fields
				firstNameTextField_updateRecord.setText(null);
				lastNameTextField_updateRecord.setText(null);
				yearTextField_updateRecord.setText(null);
				
				// Un-check major
				if(studentMajor == "Computer Science") 
				{
					CSmajorCheckBox_updateRecord.doClick();
				}
				else if(studentMajor == "Software Engineering") 
				{
					SWEmajorCheckBox_updateRecord.doClick();
				}
				else if(studentMajor == "Management Information Systems") 
				{
					MISmajorCheckBox_updateRecord.doClick();
				}
				else if(studentMajor == "Mathematics") 
				{
					mathMajorCheckBox_updateRecord.doClick();
				}
				
				// Clear student info
				clearStudent();
				
				// Change card
				setCardLayoutView("start");
			}
		});
		backButton_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		backButton_updateRecord.setBounds(12, 610, 117, 25);
		UpdateStudentRecord.add(backButton_updateRecord);
		
		// Update Record 'tab' traversal policy
		UpdateStudentRecord.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]
				{firstNameTextField_updateRecord, lastNameTextField_updateRecord, yearTextField_updateRecord, 
						CSmajorCheckBox_updateRecord, SWEmajorCheckBox_updateRecord, MISmajorCheckBox_updateRecord, 
						mathMajorCheckBox_updateRecord, backButton_updateRecord, loadStudentButton_updateRecord, nextButton_updateRecord}));
		
		//---------------------------------------------------------------------------------------------------------------------Create Record panel
		JPanel CreateStudentRecord = new JPanel();
		MainPanel.add(CreateStudentRecord, "create student record");
		CreateStudentRecord.setLayout(null);
		
		JLabel lblCreateStudentRecord = new JLabel("Create Student Record"); // Page Title label
		lblCreateStudentRecord.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCreateStudentRecord.setBounds(261, 0, 260, 15);
		CreateStudentRecord.add(lblCreateStudentRecord);
		
		JLabel firstNameLabel_1 = new JLabel("First Name:"); // First Name label
		firstNameLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel_1.setBounds(12, 65, 114, 15);
		CreateStudentRecord.add(firstNameLabel_1);
		
		firstNameField_createRecord = new JTextField(); // First Name text field
		firstNameField_createRecord.setFont(new Font("Dialog", Font.PLAIN, 16));
		firstNameField_createRecord.setColumns(10);
		firstNameField_createRecord.setBounds(130, 63, 204, 25);
		CreateStudentRecord.add(firstNameField_createRecord);
		
		JLabel lastNameLabel_1 = new JLabel("Last Name:"); // Last Name label
		lastNameLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lastNameLabel_1.setBounds(12, 100, 114, 15);
		CreateStudentRecord.add(lastNameLabel_1);
		
		lastNameField_createRecord = new JTextField(); // Last Name text field
		lastNameField_createRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastNameField_createRecord.setColumns(10);
		lastNameField_createRecord.setBounds(130, 98, 204, 25);
		CreateStudentRecord.add(lastNameField_createRecord);
		
		yearField_createRecord = new JTextField(); // Year text field
		yearField_createRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		yearField_createRecord.setColumns(10);
		yearField_createRecord.setBounds(156, 133, 178, 25);
		CreateStudentRecord.add(yearField_createRecord);
		
		JLabel catalogYearLabel_1 = new JLabel("Catalog Year:"); // Year label
		catalogYearLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		catalogYearLabel_1.setBounds(12, 137, 133, 15);
		CreateStudentRecord.add(catalogYearLabel_1);
		
		JLabel majorLabel_1 = new JLabel("Major:"); // Major label
		majorLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		majorLabel_1.setBounds(12, 208, 59, 15);
		CreateStudentRecord.add(majorLabel_1);
		
		JCheckBox CSmajorCheckBox_createRecord = new JCheckBox("CS"); // CS Major check box
		CSmajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		CSmajorCheckBox_createRecord.setBounds(85, 205, 60, 23);
		CreateStudentRecord.add(CSmajorCheckBox_createRecord);
		
		JCheckBox SWEmajorCheckBox_createRecord = new JCheckBox("SWE"); // SWE Major check box
		SWEmajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		SWEmajorCheckBox_createRecord.setBounds(149, 205, 64, 23);
		CreateStudentRecord.add(SWEmajorCheckBox_createRecord);
		
		JCheckBox MISmajorCheckBox_createRecord = new JCheckBox("MIS"); // MIS Major check box
		MISmajorCheckBox_createRecord.setEnabled(false);
		MISmajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		MISmajorCheckBox_createRecord.setBounds(220, 205, 60, 23);
		CreateStudentRecord.add(MISmajorCheckBox_createRecord);
		
		JCheckBox mathMajorCheckBox_createRecord = new JCheckBox("MATH"); // Math Major check box
		mathMajorCheckBox_createRecord.setEnabled(false);
		mathMajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		mathMajorCheckBox_createRecord.setBounds(290, 205, 76, 23);
		CreateStudentRecord.add(mathMajorCheckBox_createRecord);
		
		JButton nextBtn_createRecord = new JButton("Next"); // Next button
		nextBtn_createRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Checking text fields for missing required information
				if(!firstNameField_createRecord.getText().isBlank() && !lastNameField_createRecord.getText().isBlank() && !yearField_createRecord.getText().isBlank()) 
				{
					newOrUpdate = "new";
				
					// Get student info from text boxes/check boxes
					studentFirstName = firstNameField_createRecord.getText();
					studentLastName = lastNameField_createRecord.getText();
					studentCatalogYear = yearField_createRecord.getText();
					if(CSmajorCheckBox_createRecord.isSelected()) {studentMajor = "Computer Science";}
					else if (SWEmajorCheckBox_createRecord.isSelected()) {studentMajor = "Software Engineering";}
					else if (MISmajorCheckBox_createRecord.isSelected()) {studentMajor = "Management Information Systems";}
					else {studentMajor = "Mathematics";}
				
					// Set button colors to red on Degree Progress Panel
					liberalArtsCoreButton.setBackground(red);
					studiesButton.setBackground(red);
					majorCoreButton.setBackground(red);
					majorElectivesButton.setBackground(red);
				
					// Change card
					setCardLayoutView("degree progress");
				}
				else // One of the required text fields is empty 
				{
					// Error message pop-up
					JOptionPane.showMessageDialog(null, 
							"Error: Form missing required information.",
							"Degree Audit: Missing Information",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		nextBtn_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_createRecord.setBounds(634, 610, 117, 25);
		CreateStudentRecord.add(nextBtn_createRecord);
		
		JButton backBtn_createRecord = new JButton("Back"); // Back button
		backBtn_createRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Reset text fields
				firstNameField_createRecord.setText(null);
				lastNameField_createRecord.setText(null);
				yearField_createRecord.setText(null);
				
				// Un-check major
				if(studentMajor == "Computer Science") 
				{
					CSmajorCheckBox_createRecord.doClick();
				}
				else if(studentMajor == "Software Engineering") 
				{
					SWEmajorCheckBox_createRecord.doClick();
				}
				else if(studentMajor == "Management Information Systems") 
				{
					MISmajorCheckBox_createRecord.doClick();
				}
				else if(studentMajor == "Mathematics") 
				{
					mathMajorCheckBox_createRecord.doClick();
				}
				
				// Clear student variables
				clearStudent();
				
				// Change card
				setCardLayoutView("start");
			}
		});
		backBtn_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_createRecord.setBounds(12, 610, 117, 25);
		CreateStudentRecord.add(backBtn_createRecord);
		
		// Create Record 'tab' traversal policy
		CreateStudentRecord.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{firstNameField_createRecord, lastNameField_createRecord, yearField_createRecord, 
						CSmajorCheckBox_createRecord, SWEmajorCheckBox_createRecord, MISmajorCheckBox_createRecord,
						mathMajorCheckBox_createRecord, backBtn_createRecord, nextBtn_createRecord}));
		
		//---------------------------------------------------------------------------------------------------------------------Update Major panel
		JPanel UpdateMajor = new JPanel();
		MainPanel.add(UpdateMajor, "update major");
		UpdateMajor.setLayout(null);
		
		JLabel updateMajorLabel = new JLabel("Update Major"); // Page Title label
		updateMajorLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		updateMajorLabel.setBounds(302, 0, 260, 15);
		UpdateMajor.add(updateMajorLabel);
		
		JButton backBtn_updateMajor = new JButton("Back"); // Back button
		backBtn_updateMajor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Reset text fields
				firstNameField_updateMajor.setText(null);
				lastNameField_updateMajor.setText(null);
				currentMajorField.setText(null);
				
				// Clear any checked boxes
				if(CSmajorCheckBox_updateMajor.isSelected()) 
				{
					CSmajorCheckBox_updateMajor.doClick();
				}
				else if(SWEmajorCheckBox_updateMajor.isSelected())
				{
					SWEmajorCheckBox_updateMajor.doClick();
				}
				else if(MISmajorCheckBox_updateMajor.isSelected()) 
				{
					MISmajorCheckBox_updateMajor.doClick();
				}
				else if(mathMajorCheckBox_updateMajor.isSelected()) 
				{
					mathMajorCheckBox_updateMajor.doClick();
				}
				
				// Clear student info
				clearStudent();
				
				// Change card
				setCardLayoutView("start");
			}
		});
		backBtn_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_updateMajor.setBounds(12, 610, 117, 25);
		UpdateMajor.add(backBtn_updateMajor);
		
		JButton nextBtn_updateMajor = new JButton("Submit"); // Next button - changed to "Submit"
		nextBtn_updateMajor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{	
				// Check for no student record loaded
				if(!studentFirstName.isEmpty()) 
				{
					// Pop-up to ask for confirmation
					confirmation = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to update the major?",
						"Degree Audit: Confirm Sumbission",JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
				
					if(confirmation == 0) // Check confirmation
					{
						StringBuilder newFileName = new StringBuilder(); // StringBuilder used to update filename
						StringBuilder fileText = new StringBuilder(); // String builder used to update file content
					
						studentRecord.delete(); // Delete old file
					
						if(CSmajorCheckBox_updateMajor.isSelected()) // Get new major from check boxes
						{
							studentMajor = "Computer Science";
						}
						else if(SWEmajorCheckBox_updateMajor.isSelected())
						{
							studentMajor = "Software Engineering";
						}
						else if(MISmajorCheckBox_updateMajor.isSelected()) 
						{
							studentMajor = "Management Information Systems";
						}
						else if(mathMajorCheckBox_updateMajor.isSelected()) 
						{
							studentMajor = "Mathematics";
						}
						else 
						{
							studentMajor = null;
						}
					
						// Creating filename with updated major
						newFileName.append(studentLastName)
						.append("_")
						.append(studentFirstName)
						.append("_");
					
						if(studentMajor == "Computer Science") 
						{
							newFileName.append("CSC");
						}
						else if(studentMajor == "Software Engineering") 
						{
							newFileName.append("SWE");
						}
						else if(studentMajor == "Management Information Systems") 
						{
							newFileName.append("MIS");
						}
						else if(studentMajor == "Mathematics")
						{
							newFileName.append("MATH");
						}
						newFileName.append(".txt");
					
						// Creating text for output to file
						fileText.append("Last Name: ")
						.append(studentLastName)
						.append("\n")
						.append("First Name: ")
						.append(studentFirstName)
						.append("\n")
						.append("Catalog Year: ")
						.append(studentCatalogYear)
						.append("\n")
						.append("Major: ")
						.append(studentMajor)
						.append("\n\n")
						.append("Registered Courses:\n")
						.append(coursesRegistered)
						.append("\n")
						.append("Courses In Progress:\n")
						.append(coursesInProgress)
						.append("\n")
						.append("Completed Courses:\n")
						.append(completedCourses);
					
						// File declaration
						File updatedStudentFile = new File(newFileName.toString());
					
						// Creating updated file
						try 
						{
							FileWriter writer = new FileWriter(updatedStudentFile);
							writer.write(fileText.toString());
							writer.close();
						}
						catch (Exception ex) 
						{
							JOptionPane.showMessageDialog(null, 
									"Error: A problem was encountered writing to the file.",
									"Degree Audit: Error",JOptionPane.PLAIN_MESSAGE);
						}
					
						// Pop-up confirmation of changes
						JOptionPane.showMessageDialog(null, 
							"The student's major has been updated",
							"Degree Audit: Confirmation",JOptionPane.PLAIN_MESSAGE);
					
						// Clear student info
						clearStudent();
				
						// Change card to "start"
						setCardLayoutView("start");
					}
				}
				else // No student loaded - can't update major 
				{
					// Error message pop-up
					JOptionPane.showMessageDialog(null, 
							"Error: No student record has been loaded.",
							"Degree Audit: Record Not Found",JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		nextBtn_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_updateMajor.setBounds(634, 610, 117, 25);
		UpdateMajor.add(nextBtn_updateMajor);
		
		JLabel changeToLabel = new JLabel("Change to:"); // Change Major label
		changeToLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		changeToLabel.setBounds(12, 208, 97, 15);
		UpdateMajor.add(changeToLabel);
		
		CSmajorCheckBox_updateMajor = new JCheckBox("CS"); // CS Major check box
		CSmajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		CSmajorCheckBox_updateMajor.setBounds(156, 204, 60, 23);
		UpdateMajor.add(CSmajorCheckBox_updateMajor);
		
		SWEmajorCheckBox_updateMajor = new JCheckBox("SWE"); // SWE Major check box
		SWEmajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		SWEmajorCheckBox_updateMajor.setBounds(220, 204, 64, 23);
		UpdateMajor.add(SWEmajorCheckBox_updateMajor);
		
		MISmajorCheckBox_updateMajor = new JCheckBox("MIS"); // MIS Major check box
		MISmajorCheckBox_updateMajor.setEnabled(false);
		MISmajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		MISmajorCheckBox_updateMajor.setBounds(291, 204, 60, 23);
		UpdateMajor.add(MISmajorCheckBox_updateMajor);
		
		mathMajorCheckBox_updateMajor = new JCheckBox("MATH"); // Math Major check box
		mathMajorCheckBox_updateMajor.setEnabled(false);
		mathMajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		mathMajorCheckBox_updateMajor.setBounds(361, 204, 76, 23);
		UpdateMajor.add(mathMajorCheckBox_updateMajor);
		
		JLabel firstNameLabel_1_1 = new JLabel("First Name:"); // First Name label
		firstNameLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel_1_1.setBounds(12, 65, 114, 15);
		UpdateMajor.add(firstNameLabel_1_1);
		
		JLabel lastNameLabel_1_1 = new JLabel("Last Name:"); // Last Name label
		lastNameLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lastNameLabel_1_1.setBounds(12, 100, 114, 15);
		UpdateMajor.add(lastNameLabel_1_1);
		
		firstNameField_updateMajor = new JTextField(); // First Name text field
		firstNameField_updateMajor.setFont(new Font("Dialog", Font.PLAIN, 16));
		firstNameField_updateMajor.setColumns(10);
		firstNameField_updateMajor.setBounds(130, 63, 204, 25);
		UpdateMajor.add(firstNameField_updateMajor);
		
		lastNameField_updateMajor = new JTextField(); // Last Name text field
		lastNameField_updateMajor.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastNameField_updateMajor.setColumns(10);
		lastNameField_updateMajor.setBounds(130, 98, 204, 25);
		UpdateMajor.add(lastNameField_updateMajor);
		
		JLabel currentMajorLabel = new JLabel("Current Major:"); // Current Major label
		currentMajorLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		currentMajorLabel.setBounds(12, 137, 133, 15);
		UpdateMajor.add(currentMajorLabel);
		
		currentMajorField = new JTextField(); // Current Major text field
		currentMajorField.setFont(new Font("Dialog", Font.PLAIN, 14));
		currentMajorField.setColumns(10);
		currentMajorField.setBounds(156, 133, 178, 25);
		UpdateMajor.add(currentMajorField);
		
		JButton loadBtn_updateMajor = new JButton("Load Student Record"); // Load Student Record button
		loadBtn_updateMajor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// open file explorer & get student info
				loadStudent();
				firstNameField_updateMajor.setText(studentFirstName);
				lastNameField_updateMajor.setText(studentLastName);
				currentMajorField.setText(studentMajor);
			}
		});
		loadBtn_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		loadBtn_updateMajor.setBounds(254, 610, 260, 25);
		UpdateMajor.add(loadBtn_updateMajor);
		
		// Update Major 'tab' traversal policy
		UpdateMajor.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				firstNameField_updateMajor, lastNameField_updateMajor, currentMajorField, 
				CSmajorCheckBox_updateMajor, SWEmajorCheckBox_updateMajor, MISmajorCheckBox_updateMajor, 
				mathMajorCheckBox_updateMajor, backBtn_updateMajor, loadBtn_updateMajor, nextBtn_updateMajor}));
		
		//---------------------------------------------------------------------------------------------------------------------Degree Progress panel
		JPanel DegreeProgress = new JPanel();
		MainPanel.add(DegreeProgress, "degree progress");
		DegreeProgress.setLayout(null);
		
		JLabel degreeProgressLabel = new JLabel("Degree Progress"); // Page Title label
		degreeProgressLabel.setBounds(298, 0, 169, 22);
		degreeProgressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		DegreeProgress.add(degreeProgressLabel);
		
		JButton backBtn_degreeProgress = new JButton("Back"); // Back button
		backBtn_degreeProgress.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Clear student course area variables - avoids repeating courses on course summary page
				libArts = new StringBuilder();
				studies = new StringBuilder();
				majorCore = new StringBuilder();
				majorElec = new StringBuilder();
				
				if(newOrUpdate == "new") 
				{
					// Change card
					setCardLayoutView("create student record");
				}
				else
				{
					// Change card
					setCardLayoutView("update student record");
				}
			}
		});
		backBtn_degreeProgress.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_degreeProgress.setBounds(12, 610, 117, 25);
		DegreeProgress.add(backBtn_degreeProgress);
		
		JButton nextBtn_degreeProgress = new JButton("Next"); // Next button
		nextBtn_degreeProgress.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Populate text areas with courses
				if(newOrUpdate == "update") 
				{
					textArea_registered.setText(coursesRegistered);
					textArea_inProgress.setText(coursesInProgress);
					textArea_complete.setText(completedCourses);
				}
				
				textArea_available.setText(allCourses); // Default textArea_available to show all available courses
				textArea_available.setCaretPosition(0); // Moves to top of list so it doesn't look weird
				
				// Change card
				setCardLayoutView("courses");
			}
		});
		nextBtn_degreeProgress.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_degreeProgress.setBounds(634, 610, 117, 25);
		DegreeProgress.add(nextBtn_degreeProgress);
		
		liberalArtsCoreButton = new JButton("Liberal Arts Core"); // Liberal Arts Core button
		liberalArtsCoreButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Update label on Course Summary panel
				changingLabel.setText("Liberal Arts Core");
				
				// Populate text area on course summary panel with student's liberal arts courses
				textArea_courseSummary.setText(libArts.toString());
				
				// Change card
				setCardLayoutView("course summary");
			}
		});
		liberalArtsCoreButton.setBackground(new Color(143, 240, 164));
		liberalArtsCoreButton.setFont(new Font("Dialog", Font.BOLD, 18));
		liberalArtsCoreButton.setBounds(12, 41, 739, 100);
		DegreeProgress.add(liberalArtsCoreButton);
		
		studiesButton = new JButton("Studies"); // Studies button
		studiesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Update label on Course Summary panel
				changingLabel.setText("Four Studies");
				
				// Populate text area on course summary panel with student's four studies courses
				textArea_courseSummary.setText(studies.toString());
				
				// Change card
				setCardLayoutView("course summary");
			}
		});
		studiesButton.setBackground(new Color(249, 240, 107));
		studiesButton.setFont(new Font("Dialog", Font.BOLD, 18));
		studiesButton.setBounds(12, 190, 739, 100);
		DegreeProgress.add(studiesButton);
		
		majorCoreButton = new JButton("Major - Core"); // Major Core button
		majorCoreButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Update label on Course Summary panel
				changingLabel.setText("Major Core");
				
				// Populate text area on course summary panel with student's major core courses
				textArea_courseSummary.setText(majorCore.toString());
				
				// Change card
				setCardLayoutView("course summary");
			}
		});
		majorCoreButton.setBackground(new Color(255, 190, 111));
		majorCoreButton.setFont(new Font("Dialog", Font.BOLD, 18));
		majorCoreButton.setBounds(12, 339, 739, 100);
		DegreeProgress.add(majorCoreButton);
		
		majorElectivesButton = new JButton("Major - Electives"); // Major Electives button
		majorElectivesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Update label on Course Summary panel
				changingLabel.setText("Major Electives");
				
				// Populate text area on course summary panel with student's major elective courses
				textArea_courseSummary.setText(majorElec.toString());
				
				// Change card
				setCardLayoutView("course summary");
			}
		});
		majorElectivesButton.setBackground(new Color(246, 97, 81));
		majorElectivesButton.setFont(new Font("Dialog", Font.BOLD, 18));
		majorElectivesButton.setBounds(12, 484, 739, 100);
		DegreeProgress.add(majorElectivesButton);
		
		// Degree Progress 'tab' traversal policy
		DegreeProgress.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				liberalArtsCoreButton, studiesButton, majorCoreButton, majorElectivesButton, backBtn_degreeProgress, nextBtn_degreeProgress}));
		
		//---------------------------------------------------------------------------------------------------------------------Course Summary panel
		JPanel CourseSummary = new JPanel();
		MainPanel.add(CourseSummary, "course summary");
		CourseSummary.setLayout(null);
		
		JLabel courseSummaryLabel = new JLabel("Course Summary - "); // Page Title label
		courseSummaryLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		courseSummaryLabel.setBounds(205, 0, 202, 22);
		CourseSummary.add(courseSummaryLabel);
		
		JButton backBtn_courseSummary = new JButton("Back"); // Back button
		backBtn_courseSummary.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{	
				// Clear text in text area
				textArea_courseSummary.setText(null);
				
				// Change card
				setCardLayoutView("degree progress");
			}
		});
		backBtn_courseSummary.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_courseSummary.setBounds(12, 610, 117, 25);
		CourseSummary.add(backBtn_courseSummary);
		
		JScrollPane scrollPane_2_1 = new JScrollPane(); // Scroll pane
		scrollPane_2_1.setBounds(12, 40, 739, 558);
		CourseSummary.add(scrollPane_2_1);
		
		textArea_courseSummary = new JTextArea(); // Course Summary text area
		scrollPane_2_1.setViewportView(textArea_courseSummary);
		
		changingLabel = new JLabel(""); // Changing label - updates based on which button was clicked to get to this panel
		changingLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		changingLabel.setBounds(407, 4, 315, 15);
		CourseSummary.add(changingLabel);
		
		//---------------------------------------------------------------------------------------------------------------------Course Selection panel
		JPanel Courses = new JPanel();
		MainPanel.add(Courses, "courses");
		Courses.setLayout(null);
		
		JLabel coursesLabel = new JLabel("Courses"); // Page Title label
		coursesLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		coursesLabel.setBounds(335, 0, 202, 22);
		Courses.add(coursesLabel);
		
		JScrollPane scrollPane_inProgress = new JScrollPane(); // In Progress Courses scroll pane
		scrollPane_inProgress.setBounds(402, 59, 155, 539);
		Courses.add(scrollPane_inProgress);
		
		textArea_inProgress = new JTextArea(); // In Progress Courses text area
		textArea_inProgress.setFont(new Font("Dialog", Font.PLAIN, 16));
		textArea_inProgress.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// Selecting line of text based on user mouse position
				int mousePosition = textArea_inProgress.viewToModel2D(e.getPoint());
				
				try 
				{
					int line = textArea_inProgress.getLineOfOffset(mousePosition);
					int lineStart = textArea_inProgress.getLineStartOffset(line);
					int lineEnd = textArea_inProgress.getLineEndOffset(line);
					textArea_inProgress.select(lineStart, lineEnd);
				}
				catch (Exception ex) 
				{
					System.out.println("oops");
				}
			}
		});
		textArea_inProgress.setEditable(false);
		scrollPane_inProgress.setViewportView(textArea_inProgress);
		
		JScrollPane scrollPane_complete = new JScrollPane(); // Completed Courses scroll pane
		scrollPane_complete.setBounds(600, 59, 155, 539);
		Courses.add(scrollPane_complete);
		
		textArea_complete = new JTextArea(); // Completed Courses text area
		textArea_complete.setFont(new Font("Dialog", Font.PLAIN, 16));
		textArea_complete.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// Selecting line of text based on user mouse position
				int mousePosition = textArea_complete.viewToModel2D(e.getPoint());
				
				try 
				{
					int line = textArea_complete.getLineOfOffset(mousePosition);
					int lineStart = textArea_complete.getLineStartOffset(line);
					int lineEnd = textArea_complete.getLineEndOffset(line);
					textArea_complete.select(lineStart, lineEnd);
				}
				catch (Exception ex) 
				{
					System.out.println("oops");
				}
			}
		});
		textArea_complete.setEditable(false);
		scrollPane_complete.setViewportView(textArea_complete);
		
		JScrollPane scrollPane_registered = new JScrollPane(); // Registered Courses scroll pane
		scrollPane_registered.setBounds(204, 59, 155, 539);
		Courses.add(scrollPane_registered);
		
		textArea_registered = new JTextArea(); // Registered Courses text area
		textArea_registered.setFont(new Font("Dialog", Font.PLAIN, 16));
		textArea_registered.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// Selecting line of text based on user mouse position
				int mousePosition = textArea_registered.viewToModel2D(e.getPoint());
				
				try 
				{
					int line = textArea_registered.getLineOfOffset(mousePosition);
					int lineStart = textArea_registered.getLineStartOffset(line);
					int lineEnd = textArea_registered.getLineEndOffset(line);
					textArea_registered.select(lineStart, lineEnd);
				}
				catch (Exception ex) 
				{
					System.out.println("oops");
				}
			}
		});
		textArea_registered.setEditable(false);
		scrollPane_registered.setViewportView(textArea_registered);
		
		JScrollPane scrollPane_available = new JScrollPane(); // Available Courses scroll pane
		scrollPane_available.setBounds(8, 59, 155, 539);
		Courses.add(scrollPane_available);
		
		textArea_available = new JTextArea(); // Available Courses text area
		textArea_available.setFont(new Font("Dialog", Font.PLAIN, 16));
		textArea_available.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// Selecting line of text based on user mouse position
				int mousePosition = textArea_available.viewToModel2D(e.getPoint());
				
				try 
				{
					int line = textArea_available.getLineOfOffset(mousePosition);
					int lineStart = textArea_available.getLineStartOffset(line);
					int lineEnd = textArea_available.getLineEndOffset(line);
					textArea_available.select(lineStart, lineEnd);
				}
				catch (Exception ex) 
				{
					System.out.println("oops");
				}
			}
		});
		textArea_available.setEditable(false);
		scrollPane_available.setViewportView(textArea_available);
		
		JComboBox filterComboBox = new JComboBox(); // Course Filter combo box
		filterComboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// Getting filter option from combo box		
				Object selected = filterComboBox.getSelectedItem();
				
				
				// Sorting available courses
				if(selected == "CS") 
				{
					textArea_available.setText(csCourses);
				}
				else if(selected == "SWE") 
				{
					textArea_available.setText(seCourses);
				}
				else if(selected == "Major Core") 
				{
					if(studentMajor == "Computer Science") 
					{
						textArea_available.setText(csMajor);
					}
					else if(studentMajor == "Software Engineering") 
					{
						textArea_available.setText(seCore);
					}
					else 
					{
						textArea_available.setText(null);
					}
				}
				else if(selected == "Major Electives") 
				{
					if(studentMajor == "Computer Science") 
					{
						textArea_available.setText(csElec);
					}
					else if(studentMajor == "Software Engineering") 
					{
						textArea_available.setText(seElec);
					}
					else 
					{
						textArea_available.setText(null);
					}
				}
				else if(selected == "Community") 
				{
					textArea_available.setText(comm);
				}
				else if(selected == "Nation") 
				{
					textArea_available.setText(nati);
				}
				else if(selected == "Self") 
				{
					textArea_available.setText(self);
				}
				else if(selected == "World") 
				{
					textArea_available.setText(world);
				}
				else if(selected == "Liberal Arts Core") 
				{
					textArea_available.setText(libA);
				}
				else 
				{
					textArea_available.setText(allCourses);
				}
				textArea_available.setCaretPosition(0);
			}
		});
		filterComboBox.setModel(new DefaultComboBoxModel(new String[] 
				{"None", "Major Core", "Major Electives", "Liberal Arts Core", 
						"CSC", "SWE", "Community", "Nation", "Self", "World"}));
		filterComboBox.setBounds(297, 612, 260, 22);
		Courses.add(filterComboBox);
		
		JLabel availableCoursesLabel = new JLabel("     Available"); // Available label
		availableCoursesLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		availableCoursesLabel.setBounds(8, 32, 130, 15);
		Courses.add(availableCoursesLabel);
		
		JButton btn_regToInProg = new JButton(">"); // Registered Courses -> In Progress Courses button
		btn_regToInProg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textArea_registered.getSelectedText() != null) 
				{
					String text = textArea_registered.getSelectedText().strip();
					textArea_registered.replaceSelection(null);
					textArea_inProgress.append(text + "\n");	
				}
			}
		});
		btn_regToInProg.setFont(new Font("Dialog", Font.BOLD, 7));
		btn_regToInProg.setBounds(360, 264, 40, 25);
		Courses.add(btn_regToInProg);
		
		JButton btn_inProgToReg = new JButton("<"); // Registered Courses <- In Progress Courses button
		btn_inProgToReg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textArea_inProgress.getSelectedText() != null) 
				{
					String text = textArea_inProgress.getSelectedText().strip();
					textArea_inProgress.replaceSelection(null);
					textArea_registered.append(text + "\n");	
				}
			}
		});
		btn_inProgToReg.setFont(new Font("Dialog", Font.BOLD, 7));
		btn_inProgToReg.setBounds(360, 301, 40, 25);
		Courses.add(btn_inProgToReg);
		
		JButton btn_availableToReg = new JButton(">"); // Available Courses -> Registered Courses button
		btn_availableToReg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textArea_available.getSelectedText() != null) 
				{
					String text = textArea_available.getSelectedText().strip();
					textArea_available.replaceSelection(null);
					textArea_registered.append(text + "\n");	
				}
			}
		});
		btn_availableToReg.setFont(new Font("Dialog", Font.BOLD, 7));
		btn_availableToReg.setBounds(163, 264, 40, 25);
		Courses.add(btn_availableToReg);
		
		JLabel lblNewLabel = new JLabel("  Registered"); // Registered label
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(223, 32, 130, 15);
		Courses.add(lblNewLabel);
		
		JLabel lblInProgress = new JLabel("  In Progress"); // In Progress label
		lblInProgress.setFont(new Font("Dialog", Font.BOLD, 16));
		lblInProgress.setBounds(417, 32, 130, 15);
		Courses.add(lblInProgress);
		
		JLabel lblNewLabel_1 = new JLabel("   Complete"); // Complete label
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(621, 32, 130, 15);
		Courses.add(lblNewLabel_1);
		
		JButton btn_inProgToComplete = new JButton(">"); // In Progress Courses -> Completed Courses button
		btn_inProgToComplete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textArea_inProgress.getSelectedText() != null) 
				{
					String text = textArea_inProgress.getSelectedText();
					textArea_inProgress.replaceSelection(null);
					textArea_complete.append(text);	
				}
			}
		});
		btn_inProgToComplete.setFont(new Font("Dialog", Font.BOLD, 7));
		btn_inProgToComplete.setBounds(558, 266, 40, 25);
		Courses.add(btn_inProgToComplete);
		
		JButton btn_completeToInProg = new JButton("<"); // In Progress Courses <- Completed Courses button
		btn_completeToInProg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textArea_complete.getSelectedText() != null) 
				{
					String text = textArea_complete.getSelectedText();
					textArea_complete.replaceSelection(null);
					textArea_inProgress.append(text);	
				}
			}
		});
		btn_completeToInProg.setFont(new Font("Dialog", Font.BOLD, 7));
		btn_completeToInProg.setBounds(558, 301, 40, 25);
		Courses.add(btn_completeToInProg);
		
		JButton btnSubmitRecord = new JButton("Submit Record"); // Submit Record button
		btnSubmitRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{	
				// StringBuilder declarations
				StringBuilder fileName = new StringBuilder();
				StringBuilder fileText = new StringBuilder();
				
				// get course info from lists
				completedCourses = textArea_complete.getText();
				coursesInProgress = textArea_inProgress.getText();
				coursesRegistered = textArea_registered.getText();
				
				// Creating filename
				fileName.append(studentLastName)
				.append("_")
				.append(studentFirstName)
				.append("_");
				
				if(studentMajor == "Computer Science") 
				{
					fileName.append("CSC");
				}
				else if(studentMajor == "Software Engineering") 
				{
					fileName.append("SWE");
				}
				else if(studentMajor == "Management Information Systems") 
				{
					fileName.append("MIS");
				}
				else if(studentMajor == "Mathematics")
				{
					fileName.append("MATH");
				}
				fileName.append(".txt");
				
				// File declaration
				File studentFile = new File(fileName.toString());
				
				// Creating text for output to file
				fileText.append("Last Name: ")
				.append(studentLastName)
				.append("\n")
				.append("First Name: ")
				.append(studentFirstName)
				.append("\n")
				.append("Catalog Year: ")
				.append(studentCatalogYear)
				.append("\n")
				.append("Major: ")
				.append(studentMajor)
				.append("\n\n")
				.append("Registered Courses:\n")
				.append(coursesRegistered)
				.append("\n")
				.append("Courses In Progress:\n")
				.append(coursesInProgress)
				.append("\n")
				.append("Completed Courses:\n")
				.append(completedCourses);
				// System.out.print(fileText); // testing
				
				// Writing to file
				// Pop-up for confirmation
				if(newOrUpdate == "update") // Displays different message for updating a record & creating new record
				{
					confirmation = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to submit this record?",
						"Degree Audit: Confirm Sumbission",JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
				}
				else 
				{
					confirmation = JOptionPane.showConfirmDialog(null, 
							"Are you sure you want to create a new record?",
							"Degree Audit: Confirm Sumbission",JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
				}
				
				System.out.print(confirmation);
					
				// If confirmed, write changes to file, clear student info, & change card to start
				if(confirmation == 0) 
				{
					try 
					{
						FileWriter writer = new FileWriter(studentFile);
						writer.write(fileText.toString());
						writer.close();
					}
					catch (Exception ex) 
					{
						JOptionPane.showMessageDialog(null, 
							"Error: A problem was encountered writing to the file.",
							"Degree Audit: Error",JOptionPane.PLAIN_MESSAGE);
					}
				
					// Clear student info variables
					clearStudent();
					
					// Pop-up confirmation of record creation
					JOptionPane.showMessageDialog(null, 
							"A new record has been created for the student.",
							"Degree Audit: Record Created",JOptionPane.PLAIN_MESSAGE);
					
					// Change card
					setCardLayoutView("start");
				}
			}
		});
		btnSubmitRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSubmitRecord.setBounds(587, 610, 164, 25);
		Courses.add(btnSubmitRecord);
		
		JButton btn_regToAvailable = new JButton("<"); // Available Courses <- Registered Courses button
		btn_regToAvailable.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textArea_registered.getSelectedText() != null) 
				{
					String text = textArea_registered.getSelectedText().strip();
					textArea_registered.replaceSelection(null);
					textArea_available.append("\n" + text);
				}
			}
		});
		btn_regToAvailable.setFont(new Font("Dialog", Font.BOLD, 7));
		btn_regToAvailable.setBounds(163, 299, 40, 25);
		Courses.add(btn_regToAvailable);
		
		JLabel lblFilter = new JLabel("Filter by:"); // Filter label
		lblFilter.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFilter.setBounds(214, 615, 85, 15);
		Courses.add(lblFilter);
		
		JButton backBtn_courses = new JButton("Back"); // Back button
		backBtn_courses.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("degree progress");
			}
		});
		backBtn_courses.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_courses.setBounds(8, 611, 117, 25);
		Courses.add(backBtn_courses);
		
		//---------------------------------------------------------------------------------------------------------------------Delete Record panel
		JPanel DeleteRecord = new JPanel();
		DeleteRecord.setLayout(null);
		MainPanel.add(DeleteRecord, "delete record");
		
		JLabel title_DeleteRecord = new JLabel("Delete Record"); // Panel Title label
		title_DeleteRecord.setBounds(306, 0, 260, 15);
		DeleteRecord.add(title_DeleteRecord);
		
		JLabel firstNameLabel_deleteRecord = new JLabel("First Name:"); // First Name label
		firstNameLabel_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel_deleteRecord.setBounds(12, 65, 114, 15);
		DeleteRecord.add(firstNameLabel_deleteRecord);
		
		firstNameField_delteRecord = new JTextField(); // First Name field
		firstNameField_delteRecord.setFont(new Font("Dialog", Font.PLAIN, 16));
		firstNameField_delteRecord.setColumns(10);
		firstNameField_delteRecord.setBounds(130, 63, 204, 25);
		DeleteRecord.add(firstNameField_delteRecord);
		
		JLabel lastNameLabel_deleteRecord = new JLabel("Last Name:"); // Last Name label
		lastNameLabel_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		lastNameLabel_deleteRecord.setBounds(12, 100, 114, 15);
		DeleteRecord.add(lastNameLabel_deleteRecord);
		
		lastNameField_deleteRecord = new JTextField(); // Last Name field
		lastNameField_deleteRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastNameField_deleteRecord.setColumns(10);
		lastNameField_deleteRecord.setBounds(130, 98, 204, 25);
		DeleteRecord.add(lastNameField_deleteRecord);
		
		yearField_deleteRecord = new JTextField(); // Catalog Year text field
		yearField_deleteRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		yearField_deleteRecord.setColumns(10);
		yearField_deleteRecord.setBounds(156, 133, 178, 25);
		DeleteRecord.add(yearField_deleteRecord);
		
		JLabel catalogYearLabel_deleteRecord = new JLabel("Catalog Year:"); // Catalog Year label
		catalogYearLabel_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		catalogYearLabel_deleteRecord.setBounds(12, 137, 133, 15);
		DeleteRecord.add(catalogYearLabel_deleteRecord);
		
		JLabel majorLabel_deleteRecord = new JLabel("Major:"); // Major label
		majorLabel_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		majorLabel_deleteRecord.setBounds(12, 208, 59, 15);
		DeleteRecord.add(majorLabel_deleteRecord);
		
		JCheckBox CSmajorCheckBox_deleteRecord = new JCheckBox("CS"); // CS Major check-box
		CSmajorCheckBox_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		CSmajorCheckBox_deleteRecord.setBounds(85, 205, 60, 23);
		DeleteRecord.add(CSmajorCheckBox_deleteRecord);
		
		JCheckBox SWEmajorCheckBox_deleteRecord = new JCheckBox("SWE"); // SWE Major check-box
		SWEmajorCheckBox_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		SWEmajorCheckBox_deleteRecord.setBounds(149, 205, 64, 23);
		DeleteRecord.add(SWEmajorCheckBox_deleteRecord);
		
		JCheckBox MISmajorCheckBox_deleteRecord = new JCheckBox("MIS"); // MIS Major check-boc
		MISmajorCheckBox_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		MISmajorCheckBox_deleteRecord.setEnabled(false);
		MISmajorCheckBox_deleteRecord.setBounds(220, 205, 60, 23);
		DeleteRecord.add(MISmajorCheckBox_deleteRecord);
		
		JCheckBox mathMajorCheckBox_deleteRecord = new JCheckBox("MATH"); // Math major check-box
		mathMajorCheckBox_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		mathMajorCheckBox_deleteRecord.setEnabled(false);
		mathMajorCheckBox_deleteRecord.setBounds(290, 205, 76, 23);
		DeleteRecord.add(mathMajorCheckBox_deleteRecord);
		
		JButton deleteBtn_deleteRecord = new JButton("Delete Record"); // Delete Record button
		deleteBtn_deleteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Check for no student loaded
				if(!studentFirstName.isEmpty())
				{
					// Pop-up to ask for confirmation
					confirmation = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to delete this record?",
						"Degree Audit: Delete Record",JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
			
					// Delete record
					if(confirmation == 0) 
					{
						// Delete file
						studentRecord.delete();
					
						// Clear student info
						clearStudent();
						
						// Pop-up confirmation of deletion
						JOptionPane.showMessageDialog(null, 
								"The student's record has been deleted.",
								"Degree Audit: Record Deleted",JOptionPane.PLAIN_MESSAGE);
						
						// Change card
						setCardLayoutView("start");
					}
				}
				else // No student loaded - can't delete record 
				{
					// Error message pop-up
					JOptionPane.showMessageDialog(null, 
							"Error: No student record has been loaded.",
							"Degree Audit: Record Not Found",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		deleteBtn_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		deleteBtn_deleteRecord.setBounds(590, 610, 161, 25);
		DeleteRecord.add(deleteBtn_deleteRecord);
		
		JButton backBtn_deleteRecord = new JButton("Back"); // Back Button
		backBtn_deleteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Clear student info
				clearStudent();
				
				// Change card
				setCardLayoutView("start");
			}
		});
		backBtn_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_deleteRecord.setBounds(12, 610, 117, 25);
		DeleteRecord.add(backBtn_deleteRecord);
		
		JButton loadStudentButton_deleteRecord = new JButton("Load Student Record"); // Load Student button
		loadStudentButton_deleteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Load Student
				loadStudent();
				
				// Fill text areas
				firstNameField_delteRecord.setText(studentFirstName);
				lastNameField_deleteRecord.setText(studentLastName);
				yearField_deleteRecord.setText(studentCatalogYear);
				
				// Fill major check-boxes
				if(studentMajor == "Computer Science") 
				{
					CSmajorCheckBox_deleteRecord.doClick();
				}
				else if(studentMajor == "Software Engineering")
				{
					SWEmajorCheckBox_deleteRecord.doClick();
				}
				else if(studentMajor == "Management Information Systems") 
				{
					MISmajorCheckBox_deleteRecord.doClick();
				}
				else if(studentMajor == "Mathematics") 
				{
					mathMajorCheckBox_deleteRecord.doClick();
				}
			}
		});
		loadStudentButton_deleteRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		loadStudentButton_deleteRecord.setBounds(234, 611, 260, 25);
		DeleteRecord.add(loadStudentButton_deleteRecord);
		MainPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{StartScreen, UpdateStudentRecord, CreateStudentRecord, UpdateMajor, stdntRecordLabel, firstNameTextField_updateRecord, firstNameLabel, lastNameLabel, lastNameTextField_updateRecord, catalogYearLabel, yearTextField_updateRecord, majorLabel, CSmajorCheckBox_updateRecord, SWEmajorCheckBox_updateRecord, MISmajorCheckBox_updateRecord, mathMajorCheckBox_updateRecord, nextButton_updateRecord, loadStudentButton_updateRecord, firstNameLabel_1, firstNameField_createRecord, lastNameLabel_1, lastNameField_createRecord, yearField_createRecord, catalogYearLabel_1, lblCreateStudentRecord, majorLabel_1, CSmajorCheckBox_createRecord, SWEmajorCheckBox_createRecord, MISmajorCheckBox_createRecord, mathMajorCheckBox_createRecord, nextBtn_createRecord, backButton_updateRecord, backBtn_createRecord, updateMajorLabel, backBtn_updateMajor, nextBtn_updateMajor, changeToLabel, CSmajorCheckBox_updateMajor, SWEmajorCheckBox_updateMajor, MISmajorCheckBox_updateMajor, mathMajorCheckBox_updateMajor, firstNameLabel_1_1, lastNameLabel_1_1, firstNameField_updateMajor, lastNameField_updateMajor, currentMajorLabel, currentMajorField, loadBtn_updateMajor, DegreeProgress, degreeProgressLabel, backBtn_degreeProgress, nextBtn_degreeProgress, liberalArtsCoreButton, studiesButton, majorCoreButton, majorElectivesButton, CourseSummary, courseSummaryLabel, backBtn_courseSummary, scrollPane_2_1, textArea_courseSummary, changingLabel, Courses, scrollPane_available, scrollPane_registered, scrollPane_complete, scrollPane_inProgress, coursesLabel, filterComboBox, availableCoursesLabel, btn_regToInProg, btn_inProgToReg, lblNewLabel, lblInProgress, lblNewLabel_1, btn_inProgToComplete, btn_completeToInProg, btnSubmitRecord, btn_availableToReg, btn_regToAvailable, lblFilter, backBtn_courses, textArea_available, textArea_registered, textArea_inProgress, textArea_complete, DeleteRecord, title_DeleteRecord, firstNameLabel_deleteRecord, firstNameField_delteRecord, lastNameLabel_deleteRecord, lastNameField_deleteRecord, yearField_deleteRecord, catalogYearLabel_deleteRecord, majorLabel_deleteRecord, CSmajorCheckBox_deleteRecord, SWEmajorCheckBox_deleteRecord, MISmajorCheckBox_deleteRecord, mathMajorCheckBox_deleteRecord, deleteBtn_deleteRecord, backBtn_deleteRecord, loadStudentButton_deleteRecord}));
	}
	
	//---------------------------------------------------------------------------------------------------------------------Other Functions
	
	// Function to change card of card layout - used to navigate between each panel
	private void setCardLayoutView(String card) 
	{
		cardLayout.show(MainPanel, card);
	}
	
	// Function to reset student variables
	private void clearStudent()
	{
		// Clear student variables
		studentFirstName = "";
		studentLastName = "";
		studentCatalogYear = "";
		studentMajor = "";
		coursesRegistered = "";
		coursesInProgress = "";
		completedCourses = "";
	}
	
	// Function to load student info from text file - this works but there's probably a much simpler way to do it
	private void loadStudent() 
	{
		
		try 
		{
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // Opens file explorer
			int returnValue = jfc.showOpenDialog(null); 
			
			if (returnValue == JFileChooser.APPROVE_OPTION) // Check for valid file - if invalid, file explorer closes & nothing happens
			{
				studentRecord = jfc.getSelectedFile(); // User selected file
				Path filePath = studentRecord.toPath(); // Gets file path
				String fileText = Files.readString(filePath); // Read data from file as a string
				String strLines[] = fileText.split("\n"); // Splitting text by line
				ArrayList<String> lines = new ArrayList<String>(Arrays.asList(strLines)); // Converting to array list to be able to remove items
				
				// Getting last name
				String line0[] = lines.get(0).toString().split(" ");
				studentLastName = line0[2];
				
				// Getting first name
				String line1[] = lines.get(1).toString().split(" ");
				studentFirstName = line1[2];
				
				// Getting Catalog Year
				String line2[] = lines.get(2).toString().split(" ");
				studentCatalogYear = line2[2];
				
				// Getting Major
				String line3[] = lines.get(3).toString().split(" ");
				
				if(line3[1].equals("Computer")) // Just checks the first word to make it simpler
				{
					studentMajor = "Computer Science";
				}
				else if(line3[1].equals("Software")) 
				{
					studentMajor = "Software Engineering";
				}
				else if(line3[1].equals("Management")) 
				{
					studentMajor = "Management Information Systems";
				}
				else if(line3[1].equals("Mathematics")) 
				{
					studentMajor = "Mathematics";
				}
				else 
				{
					studentMajor = "";
				}
				
				// Removing lines to get to courses
				lines.remove(0);
				lines.remove(0);
				lines.remove(0);
				lines.remove(0);
				lines.remove(0);
				lines.remove(0);
				
				// Getting Courses
				StringBuilder courses = new StringBuilder();
				Iterator<String> iterator = lines.iterator(); // Used to iterate over courses - this was needed to remove lines while iterating through them
				int section = 0; // Used to indicate which section of courses are being collected
				
				while(iterator.hasNext())
				{
				    String line = iterator.next();

				    switch (section)
				    {
				        case 0: // Getting courses in progress
				            if(line.equals("Courses In Progress:")) // If this string has been reached, all registered courses have been retrieved
				            {
				                coursesRegistered = courses.toString(); // Cast StringBuilder to string and set coursesRegisterd variable
				                courses = new StringBuilder(); // Empty courses for next set
				                section = 1; // Move to next section
				            }
				            else if(line.equals("")) // Accounts for blank line
				            {
				            	break;
				            }
				            else
				            {
				                courses.append(line).append("\n"); // Add current line to courses
				            }
				            break;

				        case 1: // Getting courses registered
				            if(line.equals("Completed Courses:")) // If this string has been reached, all in-progress courses have been retrieved
				            {
				                coursesInProgress = courses.toString(); // Cast StringBuilder to string and set coursesInProgress variable
				                courses = new StringBuilder(); // Empty courses for next set
				                section = 2; // Move to next section
				            }
				            else if(line.equals("")) // Accounts for blank line
				            {
				            	break;
				            }
				            else 
				            {
				                courses.append(line).append("\n"); // Add current line to courses
				            }
				            break;

				        case 2: // Getting completed courses
				            if (!line.isEmpty()) // All that's left are completed courses
				            {
				            	courses.append(line).append("\n");
				            }
				            completedCourses = courses.toString();
				            break;
				    }
				}
			}
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			
			// Error message pop-up
			JOptionPane.showMessageDialog(null, 
					"Error: A problem was encountered loading the file.",
					"Degree Audit: Error",JOptionPane.PLAIN_MESSAGE);
		}
	}
}
