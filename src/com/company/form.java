
package com.company;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.sql.*;
import java.util.Vector;

public class form extends JFrame{

    static PreparedStatement query;
    static Connection con;
    static ResultSet rs;
    static int totalCount = 0;
    static String[][] data = new String[100][5];
    Savings s = new Savings();

    public form() {

        initComponents();
    }

    private void initComponents() {
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setTitle("Compund Interest");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the customer number");
        contentPane.add(label1, "cell 0 0");

        //---- textField1 ----
        textField1.setColumns(20);
        contentPane.add(textField1, "cell 2 0");

        //---- label2 ----
        label2.setText("Enter the customer name");
        contentPane.add(label2, "cell 0 1");
        contentPane.add(textField2, "cell 2 1");

        //---- label3 ----
        label3.setText("Enter the initial deposite");
        contentPane.add(label3, "cell 0 2");
        contentPane.add(textField3, "cell 2 2");

        //---- label4 ----
        label4.setText("Enter the number of years ");
        contentPane.add(label4, "cell 0 3");
        contentPane.add(textField4, "cell 2 3");

        //---- label5 ----
        label5.setText("Choose the type of savings");
        contentPane.add(label5, "cell 0 4");
        contentPane.add(comboBox1, "cell 2 4");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 5");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2, "cell 2 5");

        //---- button1 ----
        button1.setText("Add");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        contentPane.add(button1, "cell 0 6");

        //---- button2 ----
        button2.setText("Edit");
        button2.addActionListener(e -> {
            try {
                button2ActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        contentPane.add(button2, "cell 0 6");

        //---- button3 ----
        button3.setText("Delete");
        button3.addActionListener(e -> {
            try {
                button3ActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        contentPane.add(button3, "cell 0 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void button1ActionPerformed(ActionEvent e) throws SQLException, ClassNotFoundException
    {
        if(textField1.getText().isBlank()||textField2.getText().isBlank() ||textField3.getText().isBlank() ||textField4.getText().isBlank())
        {
            JOptionPane.showMessageDialog(null,"Field is empty");
        }
        else if (textField1.getText().isBlank() && textField2.getText().isBlank()&& textField3.getText().isBlank()&& textField4.getText().isBlank())
        {
            JOptionPane.showMessageDialog(null,"Both Fields are empty");
        }
        else {
            connectMethod();
            s.setCustno(Integer.parseInt(textField1.getText()));
            s.setCustname(textField2.getText());
            s.setNyears(Integer.parseInt(textField3.getText()));
            s.setCdep(Double.parseDouble(textField4.getText()));
            s.setSavtype(comboBox1.getSelectedItem().toString());

            if (rs.isBeforeFirst())
            {
                //res.isBeforeFirst() is true if the cursor

                JOptionPane.showMessageDialog(null, "The catcode you are trying to enter already exists ");

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField1.requestFocus();

                return;
            }
        }


            query = con.prepareStatement("INSERT into savingstable values(?,?,?,?,?)");
            query.setString(1, String.valueOf(s.getCustno()));
            query.setString(2, s.getCustname());
            query.setString(3, String.valueOf(s.getNyears()));
            query.setString(4, String.valueOf(s.getCdep()));
            query.setString(5, s.getSavtype());

            query.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Added");

            updateTable();
        }


    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void connectMethod() throws SQLException, ClassNotFoundException{
        //LOAD DRIVER
        Class.forName("com.mysql.cj.jdbc.Driver");

        //connection to database
        con = DriverManager.getConnection("jdbc:mysql://localhost/savings", "root", "");
    }

    public void addTable(){
        //  add(table1);
        String[] cols = {"Number", "Name", "Deposit", "Years", "Type of Savings"};

        DefaultTableModel model = new DefaultTableModel(data, cols);
        table1.setModel(model);

        comboBox1.addItem("Savings-Deluxe");
        comboBox1.addItem("Savings-Regular");
        //cbList.addActionListener(this);
    }

    public void updateTable() throws SQLException, ClassNotFoundException {
        int c;

        connectMethod();

        query = con.prepareStatement("Select * from savingstable");

        rs = query.executeQuery();

        ResultSetMetaData rmd = rs.getMetaData();
        c = rmd.getColumnCount();
        DefaultTableModel df = (DefaultTableModel) table1.getModel();
        df.setRowCount(0);

        while(rs.next()) {
            Vector v2 = new Vector();

            for(int a =1;a<=c;a++){
                v2.add(rs.getString("custno"));
                v2.add(rs.getString("custname"));
                v2.add(rs.getString("nyears"));
                v2.add(rs.getString("cdep"));
                v2.add(rs.getString("savtype"));
            }
            df.addRow(v2);
        }
    }

    private void button2ActionPerformed(ActionEvent e) throws SQLException, ClassNotFoundException {
        if(textField1.getText().isBlank()||textField2.getText().isBlank()||textField3.getText().isBlank() || textField4.getText().isBlank())
        {
            JOptionPane.showMessageDialog(null,"Field is empty");
        }
        else
        {
            connectMethod();
            s.setCustno(Integer.parseInt(textField1.getText()));
            s.setCustname(textField2.getText());
            s.setNyears(Integer.parseInt(textField3.getText()));
            s.setCdep(Double.parseDouble(textField4.getText()));
            s.setSavtype(comboBox1.getSelectedItem().toString());
            String no = textField1.getText();

            query = con.prepareStatement("update savingstable set custno = ?, custname = ?, cdep = ?, nyears = ?, savtype = ? where custno = ?");
            query.setString(1,String.valueOf(s.getCustno()));
            query.setString(2,s.getCustname());
            query.setString(3, String.valueOf(s.getNyears()));
            query.setString(4, String.valueOf(s.getCdep()));
            query.setString(5,s.getSavtype());
            query.setString(6,no);

            query.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record updated");
            updateTable();
        }
    }

    private void button3ActionPerformed(ActionEvent e) throws SQLException, ClassNotFoundException {
        connectMethod();
        s.setCustno(Integer.parseInt(textField1.getText()));

        String no = textField1.getText();

        query = con.prepareStatement("Select * from savingstable where custno = ?");
        query.setString(1, String.valueOf(s.getCustno()));
        rs = query.executeQuery();

        if (!rs.isBeforeFirst()) {
            JOptionPane.showMessageDialog(null, "No Data");
            return;
        }

        int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete this record?", "Delete",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            query = con.prepareStatement("delete from savingstable where custno = ?");
            query.setString(1, String.valueOf(s.getCustno()));

            query.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record Deleted");

            updateTable();
        }
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        form fd = new form();

        fd.addTable();
        fd.setVisible(true);
    }
}
