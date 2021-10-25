import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class FormFileBased {
    JFrame f1;
    JLabel lbl_Reg_No,lbl_Name,lbl_Gender,lbl_Discipline,lbl_Subject,lbl_Address;
    JTextField txt_Reg_No,txt_Name;
    JTextArea txtArea_Address;
    JRadioButton r_btnMale, r_btnFemale;
    ButtonGroup btn_group;
    JButton btn_Save,btn_View,btn_Exit;
    JCheckBox sub_1,sub_2,sub_3,sub_4,sub_5, sub_6;
    JTable data_Table;
    JScrollPane scrollPane;
    public void initGUI(){
        f1=new JFrame("Form");
        f1.setBackground(Color.lightGray);
        lbl_Reg_No= new JLabel("Reg No");
        //lbl_Reg_No.setFont();
        lbl_Name= new JLabel("Name");
        lbl_Gender= new JLabel("Gender");
        lbl_Discipline= new JLabel("Discipline");
        lbl_Subject= new JLabel("Subject");
        txt_Reg_No=new JTextField(14);
        txt_Name=new JTextField(14);
        r_btnMale=new JRadioButton("Male",true);
        r_btnFemale=new JRadioButton("Female");
        btn_group=new ButtonGroup();
        btn_group.add(r_btnMale);
        btn_group.add(r_btnFemale);
        // Text Area
        lbl_Address=new JLabel("Address");
        txtArea_Address=new JTextArea(2,0);
        txtArea_Address.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        btn_Save=new JButton("Save");
        btn_View=new JButton("View");
        btn_Exit=new JButton("Exit");
        // panel for gender radio buttons
        JPanel panel01= new JPanel();
        GridLayout lay_out1=new GridLayout(1,2);
        panel01.setLayout(lay_out1);
        panel01.add(r_btnMale);
        panel01.add(r_btnFemale);
        //Passing of Array to JComboBox
        String[] Courses={"BSCS","BSIT","MCS","MIT"};
        JComboBox<String> choices=new JComboBox<>(Courses);
        // CheckBoxes
        sub_1=new JCheckBox("MPL");
        sub_2=new JCheckBox("OOP");
        sub_3=new JCheckBox("OS");
        sub_4=new JCheckBox("DSA");
        sub_5=new JCheckBox("TBW");
        sub_6=new JCheckBox("VP");
        // Panel for Checkboxes
        JPanel panel02= new JPanel();
        GridLayout lay_out2=new GridLayout(3,2);
        panel02.setLayout(lay_out2);
        panel02.add(sub_1);
        panel02.add(sub_2);
        panel02.add(sub_3);
        panel02.add(sub_4);
        panel02.add(sub_5);
        panel02.add(sub_6);
        // panel for exit and view button
        JPanel panel03= new JPanel();
        GridLayout lay_out3=new GridLayout(1,2,5,5);
        panel03.setLayout(lay_out3);
        panel03.add(btn_View);
        panel03.add(btn_Exit);
        // Table and scroll panel
        // Table initialization
        data_Table=new JTable();
        scrollPane=new JScrollPane(data_Table);
        data_Table.setPreferredScrollableViewportSize(new Dimension(350,150));
        // Layout
        GridBagLayout lay_out=new GridBagLayout();
        Container container= f1.getContentPane();
        container.setLayout(lay_out);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);
        // First Row
        gbc.gridx=0;
        gbc.gridy=0;
        f1.add(lbl_Reg_No,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        f1.add(txt_Reg_No,gbc);
        // Second Row
        gbc.gridx=0;
        gbc.gridy=1;
        f1.add(lbl_Name,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        f1.add(txt_Name,gbc);
        // Third Row
        gbc.gridx=0;
        gbc.gridy=2;
        f1.add(lbl_Gender,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        f1.add(panel01,gbc);
        // Forth Row
        gbc.gridx=0;
        gbc.gridy=3;
        f1.add(lbl_Address,gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        f1.add(txtArea_Address,gbc);
        // Fifth Row
        gbc.gridx=0;
        gbc.gridy=4;
        f1.add(lbl_Discipline,gbc);
        gbc.gridx=1;
        gbc.gridy=4;
        f1.add(choices,gbc);
        // Sixth Row
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=1;
        f1.add(lbl_Subject,gbc);
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.gridwidth=1;
        f1.add(panel02,gbc);
        // Seventh Row
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.gridwidth=1;
        f1.add(btn_Save,gbc);
        gbc.gridx=1;
        gbc.gridy=6;
        gbc.gridwidth=1;
        f1.add(panel03,gbc);

        f1.pack();
        f1.setVisible(true);
        // Save button action
        btn_Save.addActionListener(e -> {
            String name = txt_Name.getText();
            String regNo = txt_Reg_No.getText();
            String gender;
            String subject="";
            if (r_btnFemale.isSelected()) {
                gender="Female";
            }
            else
                gender="Male";
            if(sub_1.isSelected())
                subject+="MPL,";
            if(sub_2.isSelected())
                subject+="OOP,";
            if(sub_3.isSelected())
                subject+="OS,";
            if(sub_4.isSelected())
                subject+="DSA,";
            if(sub_5.isSelected())
                subject+="TBW,";
            if(sub_6.isSelected())
                subject+="VP";
            if(subject.trim().length()==0)
                subject+="Nothing Select";

            if(!name.equals("") && !regNo.equals("")){
                try{
                    // File
                    File file=new File("D:\\New folder\\Student Information.txt")   ;
                    FileWriter filewriter =new FileWriter(file,true);
                    BufferedWriter br=new BufferedWriter(filewriter);
                    br.write(regNo+"/"+name+"/"+gender+"/"+choices.getSelectedItem()+"/"+subject+"/"+txtArea_Address.getText());
                    br.newLine();
                    br.close();
                }catch(Exception a){System.out.println("Error!");}
            }
            else {
                JOptionPane.showMessageDialog(null,"Enter Data first");
            }
            txt_Reg_No.setText("");
            txt_Name.setText("");
            txtArea_Address.setText("");
            btn_group.clearSelection();
            sub_1.setSelected(false);
            sub_2.setSelected(false);
            sub_3.setSelected(false);
            sub_4.setSelected(false);
            sub_5.setSelected(false);
            sub_6.setSelected(false);
            choices.setSelectedIndex(0);
        });
        // View button
        btn_View.addActionListener(e -> {
            String filePath="D:\\New folder\\Student Information.txt";
            File file=new File(filePath);
            try {
                // Delete all table row
                ((DefaultTableModel)data_Table.getModel()).setNumRows(0);

                BufferedReader br=new BufferedReader(new FileReader(file));
                // get the first line
                // get the columns name from the first line
                // get columns name to the J table model
                String[] column_Name={"Reg No","Name","Gender","Course","Subjects","Address"};
                DefaultTableModel model=(DefaultTableModel)data_Table.getModel();
                model.setColumnIdentifiers(column_Name);
                // get line from txt file
                Object[] tableLines=br.lines().toArray();
                // Extract data from lines
                // set data to J table model
                for (Object tableLine : tableLines) {
                    String line = tableLine.toString().trim();
                    String[] dataRow = line.split("/");
                    model.addRow(dataRow);
                }
                gbc.gridx=0;
                gbc.gridy=7;
                gbc.gridwidth=2;
                f1.add(scrollPane, gbc);
                f1.pack();
                f1.setVisible(true);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        // Exit button action
        btn_Exit.addActionListener(e -> System.exit(0));
    }
    public static void main(String[] args) {
        FormFileBased ob=new FormFileBased();
        ob.initGUI();
    }
}

