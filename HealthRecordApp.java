import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class HealthRecordApp extends JFrame {

    private JTextField txtId, txtName, txtAge;
    private JTextArea txtConditions;
    private JRadioButton rbMale, rbFemale;
    private JComboBox<String> cbBloodGroup;
    private DefaultListModel<StudentHealthRecord> listModel;
    private JList<StudentHealthRecord> recordList;

    private final String FILE_NAME = "records.dat";

    public HealthRecordApp() {
        setTitle("Student Health Records");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // -------- Form Panel (GridLayout) --------
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtId = new JTextField();
        txtName = new JTextField();
        txtAge = new JTextField();
        txtConditions = new JTextArea(3, 20);

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        cbBloodGroup = new JComboBox<>(new String[]{
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        });

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(txtAge);

        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout());
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Blood Group:"));
        formPanel.add(cbBloodGroup);
        formPanel.add(new JLabel("Conditions:"));
        formPanel.add(new JScrollPane(txtConditions));

        add(formPanel, BorderLayout.CENTER);

        // -------- Buttons Panel --------

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnAdd = new JButton("Add Record");
        JButton btnView = new JButton("View All");
        JButton btnSearch = new JButton("Search by ID");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnView);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnClear);

        add(buttonPanel, BorderLayout.SOUTH);

        // -------- List Panel --------
        listModel = new DefaultListModel<>();
        recordList = new JList<>(listModel);
        add(new JScrollPane(recordList), BorderLayout.EAST);

        // -------- Button Actions --------
        btnAdd.addActionListener(e -> addRecord());
        btnView.addActionListener(e -> loadRecords());
        btnSearch.addActionListener(e -> searchRecord());
        btnClear.addActionListener(e -> clearFields());

        setVisible(true);
    }

    // ---------- Add Record ----------
    private void addRecord() {
        try {
            String gender = rbMale.isSelected() ? "Male" : "Female";

            StudentHealthRecord record = new StudentHealthRecord(
                    txtId.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtAge.getText()),
                    gender,
                    cbBloodGroup.getSelectedItem().toString(),
                    txtConditions.getText()
            );

            ArrayList<StudentHealthRecord> records = loadFromFile();
            records.add(record);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(records);
            oos.close();

            JOptionPane.showMessageDialog(this, "Record Saved Successfully!");
            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving record!");
        }
    }

    // ---------- Load Records ----------
    private void loadRecords() {
        listModel.clear();
        ArrayList<StudentHealthRecord> records = loadFromFile();
        for (StudentHealthRecord r : records) {
            listModel.addElement(r);
        }
    }

    // ---------- Search Record ----------
    private void searchRecord() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        ArrayList<StudentHealthRecord> records = loadFromFile();

        for (StudentHealthRecord r : records) {
            if (r.getStudentId().equals(id)) {
                JOptionPane.showMessageDialog(this, r.detailedRecord());
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Record Not Found!");
    }

    // ---------- Clear Fields ----------
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtConditions.setText("");
        rbMale.setSelected(false);
        rbFemale.setSelected(false);
        cbBloodGroup.setSelectedIndex(0);
    }

    // ---------- File Loader ----------
    private ArrayList<StudentHealthRecord> loadFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            ArrayList<StudentHealthRecord> records =
                    (ArrayList<StudentHealthRecord>) ois.readObject();
            ois.close();
            return records;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        new HealthRecordApp();
    }
}
