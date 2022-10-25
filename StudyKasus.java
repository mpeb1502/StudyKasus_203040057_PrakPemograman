// Febrian Fauzan Rachman
// 203040057
// Praktikum Pemograman 2
// Study Kasus Pertemuan 7

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudyKasus extends JFrame {

  public StudyKasus() {
    // Mengatur warna background
    getContentPane().setBackground(Color.cyan);

    // Fungsi JOptionPane untuk EXIT
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        int exit = JOptionPane.showConfirmDialog(null,
            "Apakah ingin keluar?", "EXIT",
            JOptionPane.YES_NO_OPTION);

        if (exit == JOptionPane.YES_OPTION) {
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
          setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
      }
    });

    // Halaman Awal
    JLabel label = new JLabel("Mohon Mengisi Biodata Anda");
    label.setBounds(60, 5, 350, 30);

    JLabel labelInput = new JLabel("Nama :");
    labelInput.setBounds(15, 20, 350, 30);

    JTextField textField = new JTextField();
    textField.setBounds(15, 50, 350, 30);

    JLabel labelInput2 = new JLabel("Nomor HP :");
    labelInput2.setBounds(15, 80, 350, 30);

    JTextField textField2 = new JTextField();
    textField2.setBounds(15, 110, 350, 30);

    JLabel labelInput3 = new JLabel("Alamat :");
    labelInput3.setBounds(15, 140, 350, 30);

    JTextField textField3 = new JTextField();
    textField3.setBounds(15, 170, 350, 30);

    // Radio button
    JLabel labelRadio = new JLabel("Jenis Kelamin :");
    labelRadio.setBounds(15, 200, 350, 30);
    JRadioButton radioButton1 = new JRadioButton("Laki-Laki");
    radioButton1.setBounds(15, 225, 350, 30);
    JRadioButton radioButton2 = new JRadioButton("Perempuan");
    radioButton2.setBounds(15, 250, 350, 30);

    ButtonGroup bg = new ButtonGroup();
    bg.add(radioButton1);
    bg.add(radioButton2);

    // Membuat JFrame and JTable
    JFrame frame = new JFrame();
    JTable table = new JTable();

    // Membuat table model dan set Column
    Object[] columns = { "Nama", "Nomor HP", "Alamat", "Jenis kelamin" };
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columns);

    // set the nmodel ke table
    table.setModel(model);

    // Untuk Mengubah JTable Background Color, Font Size, Font Color, Row Height
    table.setBackground(Color.black);
    table.setForeground(Color.CYAN);
    Font font = new Font("", 2, 17);
    table.setFont(font);
    table.setRowHeight(20);

    // tombol button Tamabah,Hapus,Ubah
    JButton btnTambah = new JButton("Tambah");
    JButton btnHapus = new JButton("Hapus");
    JButton btnUbah = new JButton("Ubah");

    btnTambah.setBounds(15, 310, 100, 25);
    btnUbah.setBounds(140, 310, 100, 25);
    btnHapus.setBounds(260, 310, 100, 25);

    // Untuk membuat JScrollPane
    JScrollPane pane = new JScrollPane(table);
    pane.setBounds(15, 350, 500, 200);
    frame.setLayout(null);
    frame.add(pane);

    // memasukan JTextFields ke jframe
    frame.add(textField);
    frame.add(textField2);
    frame.add(textField3);

    // memasukan Jbutton ke jframe
    frame.add(btnTambah);
    frame.add(btnHapus);
    frame.add(btnUbah);

    // membuat arrat dengan 4 row
    Object[] row = new Object[4];

    // Fungsi button ADD
    btnTambah.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        String jenisKelamin = " ";
        if (radioButton1.isSelected()) {
          jenisKelamin = radioButton1.getText();
        }
        if (radioButton2.isSelected()) {
          jenisKelamin = radioButton2.getText();
        }
        String nama = textField.getText();
        String nomortlp = textField2.getText();
        String alamat = textField3.getText();
        if (nama.trim().isEmpty() || nomortlp.trim().isEmpty() || alamat.trim().isEmpty()) {
          JOptionPane.showMessageDialog(StudyKasus.this, "Form tidak boleh ada yang kosong!", "Warning",
              JOptionPane.WARNING_MESSAGE);
        } else {
          int confirmation = JOptionPane.showConfirmDialog(StudyKasus.this,
              "Masukan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
          if (confirmation == JOptionPane.YES_OPTION) {
            row[0] = textField.getText();
            row[1] = textField2.getText();
            row[2] = textField3.getText();
            row[3] = jenisKelamin;
            model.addRow(row);
            textField.setText(null);
            textField2.setText(null);
            textField3.setText(null);
          } else {
            JOptionPane.showMessageDialog(StudyKasus.this, "Anda tidak memasukan data");
          }
        }
      }
    });

    // Fungsi button Hapus
    btnHapus.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        // i = the index of the selected row
        int i = table.getSelectedRow();
        if (i >= 0) {
          // remove a row from jtable
          model.removeRow(i);
        } else {
          System.out.println("Hapus Error");
        }
        textField.setText(null);
        textField2.setText(null);
        textField3.setText(null);
      }
    });

    // Fungsi selected row data
    table.addMouseListener(new MouseAdapter() {

      public void mouseClicked(MouseEvent e) {

        // i = the index of the selected row
        int i = table.getSelectedRow();

        textField.setText(model.getValueAt(i, 0).toString());
        textField2.setText(model.getValueAt(i, 1).toString());
        textField3.setText(model.getValueAt(i, 2).toString());
      }
    });

    // Fungsi button Ubah
    btnUbah.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        // i = the index of the selected row
        int i = table.getSelectedRow();

        if (i >= 0) {
          model.setValueAt(textField.getText(), i, 0);
          model.setValueAt(textField2.getText(), i, 1);
          model.setValueAt(textField3.getText(), i, 2);
        } else {
          System.out.println("Ubah Error");
        }
      }
    });

    this.add(textField);
    this.add(textField2);
    this.add(textField3);
    this.add(labelRadio);
    this.add(radioButton1);
    this.add(radioButton2);
    this.add(labelInput);
    this.add(labelInput2);
    this.add(labelInput3);
    this.add(label);
    this.add(btnTambah);
    this.add(btnHapus);
    this.add(btnUbah);
    this.add(pane);

    this.setSize(600, 700);
    this.setLayout(null);

  }

  // Febrian Fauzan Rachman
  // 203040057
  // Praktikum Pemograman 2
  // Study Kasus Pertemuan 7

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {

      public void run() {

        StudyKasus t = new StudyKasus();
        t.setVisible(true);
      }
    });
  }
}
