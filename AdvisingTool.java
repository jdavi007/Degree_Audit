import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class AdvisingTool extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel MainPanel;
	private CardLayout cardLayout;
	private JTextField firstNameTextField_updateRecord;
	private JTextField lastNameTextField_updateRecord;
	private JTextField yearTextField_updateRecord;
	private JTextField firstNameField_createRecord;
	private JTextField lastNameField_createRecord;
	private JTextField yearField_createRecord;
	private JTextField firstNameField_updateMajor;
	private JTextField lastNameField_updateMajor;
	private JTextField currentMajorField;
	private String lastScreen = "";
	private JLabel changingLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvisingTool frame = new AdvisingTool();
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
	public AdvisingTool() {
		setResizable(false);
		setBounds(new Rectangle(800, 800, 0, 0));
		setTitle("Degree Audit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		cardLayout = new CardLayout(0,0);
		
		
		MainPanel = new JPanel();
		MainPanel.setBounds(12, 10, 668, 545);
		contentPane.add(MainPanel);
		MainPanel.setLayout(cardLayout);
		
		JPanel StartScreen = new JPanel();
		MainPanel.add(StartScreen, "start");
		
		
		
		JButton updateStudentRecordBtn = new JButton("Update Student Record");
		updateStudentRecordBtn.setBounds(34, 23, 600, 70);
		updateStudentRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("update student record");
			}
		});
		StartScreen.setLayout(null);
		updateStudentRecordBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		StartScreen.add(updateStudentRecordBtn);
		
		
		JButton createStudentRecordBtn = new JButton("Create Student Record");
		createStudentRecordBtn.setBounds(34, 159, 600, 70);
		createStudentRecordBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		createStudentRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setCardLayoutView("create student record");
			}
		});
		StartScreen.add(createStudentRecordBtn);
		
		
		
		JButton updateMajorBtn = new JButton("Update Major");
		updateMajorBtn.setBounds(34, 295, 600, 70);
		updateMajorBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		updateMajorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("update major");
			}
		});
		StartScreen.add(updateMajorBtn);
		
		
		
		JButton deleteStudentRecordBtn = new JButton("Delete Student Record");
		deleteStudentRecordBtn.setBounds(34, 431, 600, 70);
		deleteStudentRecordBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		deleteStudentRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		StartScreen.add(deleteStudentRecordBtn);
		StartScreen.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{updateStudentRecordBtn, createStudentRecordBtn, updateMajorBtn, deleteStudentRecordBtn}));
		
		JPanel UpdateStudentRecord = new JPanel();
		MainPanel.add(UpdateStudentRecord, "update student record");
		UpdateStudentRecord.setLayout(null);
		
		JLabel stdntRecordLabel = new JLabel("Update Student Record");
		stdntRecordLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		stdntRecordLabel.setBounds(203, 0, 260, 15);
		UpdateStudentRecord.add(stdntRecordLabel);
		
		firstNameTextField_updateRecord = new JTextField();
		firstNameTextField_updateRecord.setFont(new Font("Dialog", Font.PLAIN, 16));
		firstNameTextField_updateRecord.setBounds(130, 63, 204, 25);
		UpdateStudentRecord.add(firstNameTextField_updateRecord);
		firstNameTextField_updateRecord.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel.setBounds(12, 65, 114, 15);
		UpdateStudentRecord.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lastNameLabel.setBounds(12, 100, 114, 15);
		UpdateStudentRecord.add(lastNameLabel);
		
		lastNameTextField_updateRecord = new JTextField();
		lastNameTextField_updateRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastNameTextField_updateRecord.setColumns(10);
		lastNameTextField_updateRecord.setBounds(130, 98, 204, 25);
		UpdateStudentRecord.add(lastNameTextField_updateRecord);
		
		JLabel catalogYearLabel = new JLabel("Catalog Year:");
		catalogYearLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		catalogYearLabel.setBounds(12, 137, 133, 15);
		UpdateStudentRecord.add(catalogYearLabel);
		
		yearTextField_updateRecord = new JTextField();
		yearTextField_updateRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		yearTextField_updateRecord.setColumns(10);
		yearTextField_updateRecord.setBounds(156, 133, 178, 25);
		UpdateStudentRecord.add(yearTextField_updateRecord);
		
		JLabel majorLabel = new JLabel("Major:");
		majorLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		majorLabel.setBounds(12, 208, 59, 15);
		UpdateStudentRecord.add(majorLabel);
		
		JCheckBox CSmajorCheckBox_updateRecord = new JCheckBox("CS");
		CSmajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		CSmajorCheckBox_updateRecord.setBounds(85, 205, 60, 23);
		UpdateStudentRecord.add(CSmajorCheckBox_updateRecord);
		
		JCheckBox SWEmajorCheckBox_updateRecord = new JCheckBox("SWE");
		SWEmajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		SWEmajorCheckBox_updateRecord.setBounds(149, 205, 64, 23);
		UpdateStudentRecord.add(SWEmajorCheckBox_updateRecord);
		
		JCheckBox MISmajorCheckBox_updateRecord = new JCheckBox("MIS");
		MISmajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		MISmajorCheckBox_updateRecord.setBounds(220, 205, 60, 23);
		UpdateStudentRecord.add(MISmajorCheckBox_updateRecord);
		
		JCheckBox mathMajorCheckBox_updateRecord = new JCheckBox("MATH");
		mathMajorCheckBox_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		mathMajorCheckBox_updateRecord.setBounds(290, 205, 76, 23);
		UpdateStudentRecord.add(mathMajorCheckBox_updateRecord);
		
		JButton nextButton_updateRecord = new JButton("Next");
		nextButton_updateRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lastScreen = "update student record";
				setCardLayoutView("degree progress");
			}
		});
		nextButton_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		nextButton_updateRecord.setBounds(539, 508, 117, 25);
		UpdateStudentRecord.add(nextButton_updateRecord);
		
		JButton loadStudentButton_updateRecord = new JButton("Load Student Record");
		loadStudentButton_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		loadStudentButton_updateRecord.setBounds(203, 508, 260, 25);
		UpdateStudentRecord.add(loadStudentButton_updateRecord);
		
		JButton backButton_updateRecord = new JButton("Back");
		backButton_updateRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("start");
			}
		});
		backButton_updateRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		backButton_updateRecord.setBounds(12, 509, 117, 25);
		UpdateStudentRecord.add(backButton_updateRecord);
		UpdateStudentRecord.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]
				{firstNameTextField_updateRecord, lastNameTextField_updateRecord, yearTextField_updateRecord, 
						CSmajorCheckBox_updateRecord, SWEmajorCheckBox_updateRecord, MISmajorCheckBox_updateRecord, 
						mathMajorCheckBox_updateRecord, backButton_updateRecord, loadStudentButton_updateRecord, nextButton_updateRecord}));
		
		JPanel CreateStudentRecord = new JPanel();
		MainPanel.add(CreateStudentRecord, "create student record");
		CreateStudentRecord.setLayout(null);
		
		JLabel firstNameLabel_1 = new JLabel("First Name:");
		firstNameLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel_1.setBounds(12, 65, 114, 15);
		CreateStudentRecord.add(firstNameLabel_1);
		
		firstNameField_createRecord = new JTextField();
		firstNameField_createRecord.setFont(new Font("Dialog", Font.PLAIN, 16));
		firstNameField_createRecord.setColumns(10);
		firstNameField_createRecord.setBounds(130, 63, 204, 25);
		CreateStudentRecord.add(firstNameField_createRecord);
		
		JLabel lastNameLabel_1 = new JLabel("Last Name:");
		lastNameLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lastNameLabel_1.setBounds(12, 100, 114, 15);
		CreateStudentRecord.add(lastNameLabel_1);
		
		lastNameField_createRecord = new JTextField();
		lastNameField_createRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastNameField_createRecord.setColumns(10);
		lastNameField_createRecord.setBounds(130, 98, 204, 25);
		CreateStudentRecord.add(lastNameField_createRecord);
		
		yearField_createRecord = new JTextField();
		yearField_createRecord.setFont(new Font("Dialog", Font.PLAIN, 14));
		yearField_createRecord.setColumns(10);
		yearField_createRecord.setBounds(156, 133, 178, 25);
		CreateStudentRecord.add(yearField_createRecord);
		
		JLabel catalogYearLabel_1 = new JLabel("Catalog Year:");
		catalogYearLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		catalogYearLabel_1.setBounds(12, 137, 133, 15);
		CreateStudentRecord.add(catalogYearLabel_1);
		
		JLabel lblCreateStudentRecord = new JLabel("Create Student Record");
		lblCreateStudentRecord.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCreateStudentRecord.setBounds(209, 0, 260, 15);
		CreateStudentRecord.add(lblCreateStudentRecord);
		
		JLabel majorLabel_1 = new JLabel("Major:");
		majorLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		majorLabel_1.setBounds(12, 208, 59, 15);
		CreateStudentRecord.add(majorLabel_1);
		
		JCheckBox CSmajorCheckBox_createRecord = new JCheckBox("CS");
		CSmajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		CSmajorCheckBox_createRecord.setBounds(85, 205, 60, 23);
		CreateStudentRecord.add(CSmajorCheckBox_createRecord);
		
		JCheckBox SWEmajorCheckBox_createRecord = new JCheckBox("SWE");
		SWEmajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		SWEmajorCheckBox_createRecord.setBounds(149, 205, 64, 23);
		CreateStudentRecord.add(SWEmajorCheckBox_createRecord);
		
		JCheckBox MISmajorCheckBox_createRecord = new JCheckBox("MIS");
		MISmajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		MISmajorCheckBox_createRecord.setBounds(220, 205, 60, 23);
		CreateStudentRecord.add(MISmajorCheckBox_createRecord);
		
		JCheckBox mathMajorCheckBox_createRecord = new JCheckBox("MATH");
		mathMajorCheckBox_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		mathMajorCheckBox_createRecord.setBounds(290, 205, 76, 23);
		CreateStudentRecord.add(mathMajorCheckBox_createRecord);
		
		JButton nextBtn_createRecord = new JButton("Next");
		nextBtn_createRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lastScreen = "create student record";
				setCardLayoutView("degree progress");
			}
		});
		nextBtn_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_createRecord.setBounds(539, 508, 117, 25);
		CreateStudentRecord.add(nextBtn_createRecord);
		
		JButton backBtn_createRecord = new JButton("Back");
		backBtn_createRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("start");
			}
		});
		backBtn_createRecord.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_createRecord.setBounds(12, 509, 117, 25);
		CreateStudentRecord.add(backBtn_createRecord);
		CreateStudentRecord.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{firstNameField_createRecord, lastNameField_createRecord, yearField_createRecord, 
						CSmajorCheckBox_createRecord, SWEmajorCheckBox_createRecord, MISmajorCheckBox_createRecord,
						mathMajorCheckBox_createRecord, backBtn_createRecord, nextBtn_createRecord}));
		
		JPanel UpdateMajor = new JPanel();
		MainPanel.add(UpdateMajor, "update major");
		UpdateMajor.setLayout(null);
		
		JLabel updateMajorLabel = new JLabel("Update Major");
		updateMajorLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		updateMajorLabel.setBounds(262, 0, 260, 15);
		UpdateMajor.add(updateMajorLabel);
		
		JButton backBtn_updateMajor = new JButton("Back");
		backBtn_updateMajor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("start");
			}
		});
		backBtn_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_updateMajor.setBounds(12, 509, 117, 25);
		UpdateMajor.add(backBtn_updateMajor);
		
		JButton nextBtn_updateMajor = new JButton("Next");
		nextBtn_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_updateMajor.setBounds(539, 508, 117, 25);
		UpdateMajor.add(nextBtn_updateMajor);
		
		JLabel changeToLabel = new JLabel("Change to:");
		changeToLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		changeToLabel.setBounds(12, 208, 97, 15);
		UpdateMajor.add(changeToLabel);
		
		JCheckBox CSmajorCheckBox_updateMajor = new JCheckBox("CS");
		CSmajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		CSmajorCheckBox_updateMajor.setBounds(156, 204, 60, 23);
		UpdateMajor.add(CSmajorCheckBox_updateMajor);
		
		JCheckBox SWEmajorCheckBox_updateMajor = new JCheckBox("SWE");
		SWEmajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		SWEmajorCheckBox_updateMajor.setBounds(220, 204, 64, 23);
		UpdateMajor.add(SWEmajorCheckBox_updateMajor);
		
		JCheckBox MISmajorCheckBox_updateMajor = new JCheckBox("MIS");
		MISmajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		MISmajorCheckBox_updateMajor.setBounds(291, 204, 60, 23);
		UpdateMajor.add(MISmajorCheckBox_updateMajor);
		
		JCheckBox mathMajorCheckBox_updateMajor = new JCheckBox("MATH");
		mathMajorCheckBox_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		mathMajorCheckBox_updateMajor.setBounds(361, 204, 76, 23);
		UpdateMajor.add(mathMajorCheckBox_updateMajor);
		
		JLabel firstNameLabel_1_1 = new JLabel("First Name:");
		firstNameLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel_1_1.setBounds(12, 65, 114, 15);
		UpdateMajor.add(firstNameLabel_1_1);
		
		JLabel lastNameLabel_1_1 = new JLabel("Last Name:");
		lastNameLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lastNameLabel_1_1.setBounds(12, 100, 114, 15);
		UpdateMajor.add(lastNameLabel_1_1);
		
		firstNameField_updateMajor = new JTextField();
		firstNameField_updateMajor.setFont(new Font("Dialog", Font.PLAIN, 16));
		firstNameField_updateMajor.setColumns(10);
		firstNameField_updateMajor.setBounds(130, 63, 204, 25);
		UpdateMajor.add(firstNameField_updateMajor);
		
		lastNameField_updateMajor = new JTextField();
		lastNameField_updateMajor.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastNameField_updateMajor.setColumns(10);
		lastNameField_updateMajor.setBounds(130, 98, 204, 25);
		UpdateMajor.add(lastNameField_updateMajor);
		
		JLabel currentMajorLabel = new JLabel("Current Major:");
		currentMajorLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		currentMajorLabel.setBounds(12, 137, 133, 15);
		UpdateMajor.add(currentMajorLabel);
		
		currentMajorField = new JTextField();
		currentMajorField.setFont(new Font("Dialog", Font.PLAIN, 14));
		currentMajorField.setColumns(10);
		currentMajorField.setBounds(156, 133, 178, 25);
		UpdateMajor.add(currentMajorField);
		
		JButton loadBtn_updateMajor = new JButton("Load Student Record");
		loadBtn_updateMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		loadBtn_updateMajor.setBounds(209, 510, 260, 25);
		UpdateMajor.add(loadBtn_updateMajor);
		UpdateMajor.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				firstNameField_updateMajor, lastNameField_updateMajor, currentMajorField, 
				CSmajorCheckBox_updateMajor, SWEmajorCheckBox_updateMajor, MISmajorCheckBox_updateMajor, 
				mathMajorCheckBox_updateMajor, backBtn_updateMajor, loadBtn_updateMajor, nextBtn_updateMajor}));
		
		cardLayout.show(MainPanel, "start");
		
		JPanel DegreeProgress = new JPanel();
		MainPanel.add(DegreeProgress, "degree progress");
		DegreeProgress.setLayout(null);
		
		JLabel degreeProgressLabel = new JLabel("Degree Progress");
		degreeProgressLabel.setBounds(244, 0, 169, 22);
		degreeProgressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		DegreeProgress.add(degreeProgressLabel);
		
		JButton backBtn_degreeProgress = new JButton("Back");
		backBtn_degreeProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(lastScreen == "create student record") 
				{
					setCardLayoutView("create student record");
				}
				else
				{
					setCardLayoutView("update student record");
				}
			}
		});
		backBtn_degreeProgress.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_degreeProgress.setBounds(12, 508, 117, 25);
		DegreeProgress.add(backBtn_degreeProgress);
		
		JButton nextBtn_degreeProgress = new JButton("Next");
		nextBtn_degreeProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("completed courses");
			}
		});
		nextBtn_degreeProgress.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_degreeProgress.setBounds(539, 507, 117, 25);
		DegreeProgress.add(nextBtn_degreeProgress);
		
		JButton liberalArtsCoreButton = new JButton("Liberal Arts Core");
		liberalArtsCoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				changingLabel.setText("Liberal Arts Core");
				setCardLayoutView("course summary");
			}
		});
		liberalArtsCoreButton.setBackground(new Color(143, 240, 164));
		liberalArtsCoreButton.setFont(new Font("Dialog", Font.BOLD, 16));
		liberalArtsCoreButton.setBounds(156, 65, 330, 35);
		DegreeProgress.add(liberalArtsCoreButton);
		
		JButton studiesButton = new JButton("Studies");
		studiesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				changingLabel.setText("Four Studies");
				setCardLayoutView("course summary");
			}
		});
		studiesButton.setBackground(new Color(249, 240, 107));
		studiesButton.setFont(new Font("Dialog", Font.BOLD, 16));
		studiesButton.setBounds(156, 130, 330, 35);
		DegreeProgress.add(studiesButton);
		
		JButton majorCoreButton = new JButton("Major - Core");
		majorCoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				changingLabel.setText("Major Core");
				setCardLayoutView("course summary");
			}
		});
		majorCoreButton.setBackground(new Color(255, 190, 111));
		majorCoreButton.setFont(new Font("Dialog", Font.BOLD, 16));
		majorCoreButton.setBounds(156, 196, 330, 35);
		DegreeProgress.add(majorCoreButton);
		
		JButton majorElectivesButton = new JButton("Major - Electives");
		majorElectivesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				changingLabel.setText("Major Electives");
				setCardLayoutView("course summary");
			}
		});
		majorElectivesButton.setBackground(new Color(246, 97, 81));
		majorElectivesButton.setFont(new Font("Dialog", Font.BOLD, 16));
		majorElectivesButton.setBounds(156, 260, 330, 35);
		DegreeProgress.add(majorElectivesButton);
		DegreeProgress.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				liberalArtsCoreButton, studiesButton, majorCoreButton, majorElectivesButton, backBtn_degreeProgress, nextBtn_degreeProgress}));
		
		JPanel CompletedCourses = new JPanel();
		MainPanel.add(CompletedCourses, "completed courses");
		CompletedCourses.setLayout(null);
		
		JLabel completedCoursesLabel = new JLabel("Completed Courses");
		completedCoursesLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		completedCoursesLabel.setBounds(226, 0, 202, 22);
		CompletedCourses.add(completedCoursesLabel);
		
		JButton backBtn_completedCourses = new JButton("Back");
		backBtn_completedCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("degree progress");
			}
		});
		backBtn_completedCourses.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_completedCourses.setBounds(12, 508, 117, 25);
		CompletedCourses.add(backBtn_completedCourses);
		
		JButton nextBtn_completedCourses = new JButton("Next");
		nextBtn_completedCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("courses in progress");
			}
		});
		nextBtn_completedCourses.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_completedCourses.setBounds(539, 507, 117, 25);
		CompletedCourses.add(nextBtn_completedCourses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 40, 644, 439);
		CompletedCourses.add(scrollPane);
		
		JTextArea textArea_completedCourses = new JTextArea();
		scrollPane.setViewportView(textArea_completedCourses);
		CompletedCourses.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				backBtn_completedCourses, nextBtn_completedCourses, textArea_completedCourses}));
		
		JPanel CoursesInProgress = new JPanel();
		CoursesInProgress.setLayout(null);
		MainPanel.add(CoursesInProgress, "courses in progress");
		
		JLabel coursesInProgressLabel = new JLabel("Courses In Progress");
		coursesInProgressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		coursesInProgressLabel.setBounds(221, 0, 224, 22);
		CoursesInProgress.add(coursesInProgressLabel);
		
		JButton backBtn_inProgress = new JButton("Back");
		backBtn_inProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("completed courses");
			}
		});
		backBtn_inProgress.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_inProgress.setBounds(12, 508, 117, 25);
		CoursesInProgress.add(backBtn_inProgress);
		
		JButton nextBtn_inProgress = new JButton("Next");
		nextBtn_inProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("courses registered");
			}
		});
		nextBtn_inProgress.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_inProgress.setBounds(539, 507, 117, 25);
		CoursesInProgress.add(nextBtn_inProgress);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 40, 644, 439);
		CoursesInProgress.add(scrollPane_1);
		
		JTextArea textArea_inProgress = new JTextArea();
		scrollPane_1.setViewportView(textArea_inProgress);
		CoursesInProgress.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				textArea_inProgress, backBtn_inProgress, nextBtn_inProgress}));
		
		JPanel CoursesRegistered = new JPanel();
		CoursesRegistered.setLayout(null);
		MainPanel.add(CoursesRegistered, "courses registered");
		
		JLabel registeredCoursesLabel = new JLabel("Courses Registered");
		registeredCoursesLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		registeredCoursesLabel.setBounds(226, 0, 202, 22);
		CoursesRegistered.add(registeredCoursesLabel);
		
		JButton backBtn_regCourses = new JButton("Back");
		backBtn_regCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("courses in progress");
			}
		});
		backBtn_regCourses.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_regCourses.setBounds(12, 508, 117, 25);
		CoursesRegistered.add(backBtn_regCourses);
		
		JButton nextBtn_regCourses = new JButton("Next");
		nextBtn_regCourses.setFont(new Font("Dialog", Font.BOLD, 16));
		nextBtn_regCourses.setBounds(539, 507, 117, 25);
		CoursesRegistered.add(nextBtn_regCourses);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 40, 644, 439);
		CoursesRegistered.add(scrollPane_2);
		
		JTextArea textArea_regCourses = new JTextArea();
		scrollPane_2.setViewportView(textArea_regCourses);
		CoursesRegistered.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				textArea_regCourses, backBtn_regCourses, nextBtn_regCourses}));
		
		JPanel CourseSummary = new JPanel();
		MainPanel.add(CourseSummary, "course summary");
		CourseSummary.setLayout(null);
		
		JLabel courseSummaryLabel = new JLabel("Course Summary - ");
		courseSummaryLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		courseSummaryLabel.setBounds(139, 0, 202, 22);
		CourseSummary.add(courseSummaryLabel);
		
		JButton backBtn_courseSummary = new JButton("Back");
		backBtn_courseSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setCardLayoutView("degree progress");
			}
		});
		backBtn_courseSummary.setFont(new Font("Dialog", Font.BOLD, 16));
		backBtn_courseSummary.setBounds(12, 508, 117, 25);
		CourseSummary.add(backBtn_courseSummary);
		
		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setBounds(12, 40, 644, 439);
		CourseSummary.add(scrollPane_2_1);
		
		JTextArea textArea_courseSummary = new JTextArea();
		scrollPane_2_1.setViewportView(textArea_courseSummary);
		
		changingLabel = new JLabel("");
		changingLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		changingLabel.setBounds(330, 4, 315, 15);
		CourseSummary.add(changingLabel);
		MainPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				StartScreen, UpdateStudentRecord, CreateStudentRecord, UpdateMajor, stdntRecordLabel, 
				firstNameTextField_updateRecord, firstNameLabel, lastNameLabel, lastNameTextField_updateRecord, 
				catalogYearLabel, yearTextField_updateRecord, majorLabel, CSmajorCheckBox_updateRecord, 
				SWEmajorCheckBox_updateRecord, MISmajorCheckBox_updateRecord, mathMajorCheckBox_updateRecord, 
				nextButton_updateRecord, loadStudentButton_updateRecord, firstNameLabel_1, firstNameField_createRecord, 
				lastNameLabel_1, lastNameField_createRecord, yearField_createRecord, catalogYearLabel_1, lblCreateStudentRecord, 
				majorLabel_1, CSmajorCheckBox_createRecord, SWEmajorCheckBox_createRecord, MISmajorCheckBox_createRecord, 
				mathMajorCheckBox_createRecord, nextBtn_createRecord, backButton_updateRecord, backBtn_createRecord, 
				updateMajorLabel, backBtn_updateMajor, nextBtn_updateMajor, changeToLabel, CSmajorCheckBox_updateMajor, 
				SWEmajorCheckBox_updateMajor, MISmajorCheckBox_updateMajor, mathMajorCheckBox_updateMajor, 
				firstNameLabel_1_1, lastNameLabel_1_1, firstNameField_updateMajor, lastNameField_updateMajor, currentMajorLabel, 
				currentMajorField, loadBtn_updateMajor, DegreeProgress, degreeProgressLabel, backBtn_degreeProgress, 
				nextBtn_degreeProgress, liberalArtsCoreButton, studiesButton, majorCoreButton, majorElectivesButton, 
				CompletedCourses, completedCoursesLabel, backBtn_completedCourses, nextBtn_completedCourses, scrollPane, 
				CoursesInProgress, coursesInProgressLabel, backBtn_inProgress, nextBtn_inProgress, scrollPane_1, CoursesRegistered, 
				registeredCoursesLabel, backBtn_regCourses, nextBtn_regCourses, scrollPane_2, CourseSummary, textArea_inProgress, 
				textArea_completedCourses, textArea_regCourses, courseSummaryLabel, backBtn_courseSummary, scrollPane_2_1, 
				textArea_courseSummary, changingLabel}));
	}
	
	
	private void setCardLayoutView(String card) 
	{
		cardLayout.show(MainPanel, card);
	}
}
