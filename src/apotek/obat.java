/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apotek;

/**
 *
 * @author user
 */
import koneksi.koneksi;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class obat extends javax.swing.JFrame {
//membuat objek    
    private DefaultTableModel model;
    
    //deklarasi variabel
    String kode_obat, kode_kategori, obat;
    int stok_obat, harga;
    /**
     * Creates new form obat
     */
    public obat() {
        initComponents();
         model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tbobat.setModel(model);
        model.addColumn("KODE OBAT ");
        model.addColumn("KODE KATEGORI");
        model.addColumn("OBAT");
        model.addColumn("STOK OBAT");
        model.addColumn("HARGA");
       
        
        //fungsi ambil data
        getDataObat();
    }
//fungsi membaca data kategori
    public void getDataObat(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksi.getKoneksi().createStatement();
            
            //perintah sql untuk membaca data dari tabel gaji        
            String sql = "SELECT * FROM tbobat";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[5];
                obj[0]=res.getString("kode_obat");
                obj[1]=res.getString("kode_kategori");
                obj[2]=res.getString("obat");
                obj[3]=res.getString("stok_obat");
                obj[4]=res.getString("harga");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     public void loadDataObat(){
        //mengambil data dari textbox dan menyimpan nilainya pada variabel
        kode_obat= tkdobat.getText();
        kode_kategori = tkdkategori.getText();
        obat = tobat.getText();
        stok_obat = Integer.parseInt(tstok.getText());
        harga = Integer.parseInt(tharga.getText());
    }
    
    public void dataSelect(){
        //deklarasi variabel
        int i = tbobat.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        tkdobat.setText(""+model.getValueAt(i,0));
        tkdkategori.setText(""+model.getValueAt(i,1));
        tobat.setText(""+model.getValueAt(i,2));
        tstok.setText(""+model.getValueAt(i,3));
        tharga.setText(""+model.getValueAt(i,4));
        
    }
    
    public void reset(){
        kode_obat = "";
        kode_kategori = "";
        obat = "";
        stok_obat = 0;
        harga = 0;
       
        tkdobat.setText(kode_obat);
        tkdkategori.setText(kode_kategori);
        tobat.setText(obat);
        tstok.setText("");
        tharga.setText("");
    }
    
    public void simpanDataObat(){
         //panggil fungsi load data
        loadDataObat();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksi.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO tbobat(kode_obat,kode_kategori,obat, stok_obat, harga )"
                            + "VALUES('"+ kode_obat +"','"+ kode_kategori +"','"+ obat +"','"+ stok_obat +"','"+ harga +"')";
            PreparedStatement p = (PreparedStatement) koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataObat();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void rubahDataObat(){
          //panggil fungsi load data
        loadDataObat();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksi.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String sql  =   "UPDATE tbobat SET kode_kategori  = '"+ kode_kategori +"',"
                            + "obat = '"+ obat +"',"  
                            + "stok_obat  = '"+ stok_obat +"',"
                            + "harga  = '"+ harga +"'"
                            +"WHERE kode_obat = '"+ kode_obat +"'";
            PreparedStatement p = (PreparedStatement) koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataObat();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void hapusDataObat(){
        //panggil fungsi ambil data
        loadDataObat(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA"+ kode_obat +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksi.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM tbobat WHERE kode_obat ='"+ kode_obat +"'";
                PreparedStatement p =(PreparedStatement)koneksi.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataObat();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "KENANGAN BERSAMANYA BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
     }
    public void dataProduk(){   
        try{
            //tes koneksi
            Statement stat = (Statement) koneksi.getKoneksi().createStatement();
           
            //perintah sql untuk membaca data dari tabel produk
            String sql = "SELECT * FROM tbkategori WHERE kode_kategori = '"+ tkdkategori.getText() +"'";
            ResultSet res = stat.executeQuery(sql);
                        
            //baca data dan tampilkan pada text produk dan harga
            while(res.next()){
                //membuat obyek berjenis array
               tobat.setText(res.getString("obat"));
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tkdkategori = new javax.swing.JTextField();
        tobat = new javax.swing.JTextField();
        tstok = new javax.swing.JTextField();
        cmdsimpan = new javax.swing.JButton();
        cmdubah = new javax.swing.JButton();
        cmdhapus = new javax.swing.JButton();
        cmdkeluar = new javax.swing.JButton();
        cmdreset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbobat = new javax.swing.JTable();
        tkdobat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tharga = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tkdkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tkdkategoriKeyReleased(evt);
            }
        });

        cmdsimpan.setText("SIMPAN");
        cmdsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsimpanActionPerformed(evt);
            }
        });

        cmdubah.setText("UBAH");
        cmdubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdubahActionPerformed(evt);
            }
        });

        cmdhapus.setText("HAPUS");
        cmdhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdhapusActionPerformed(evt);
            }
        });

        cmdkeluar.setText("KELUAR");
        cmdkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdkeluarActionPerformed(evt);
            }
        });

        cmdreset.setText("RESET");
        cmdreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdresetActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setText("MASTER DATA OBAT");

        jLabel3.setText("KODE OBAT");

        jLabel4.setText("KODE KATEGORI");

        jLabel5.setText("OBAT");

        jLabel6.setText("STOK OBAT");

        tbobat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbobat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbobatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbobat);

        jLabel8.setText("HARGA");

        tharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tkdkategori)
                                    .addComponent(tobat)
                                    .addComponent(tkdobat)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(tstok, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tharga, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmdsimpan)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmdubah)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmdhapus)))
                                .addGap(18, 18, 18)
                                .addComponent(cmdreset)
                                .addGap(18, 18, 18)
                                .addComponent(cmdkeluar)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(245, 245, 245))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tkdobat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tkdkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tobat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdsimpan)
                    .addComponent(cmdubah)
                    .addComponent(cmdhapus)
                    .addComponent(cmdreset)
                    .addComponent(cmdkeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void thargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thargaActionPerformed

    private void cmdsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsimpanActionPerformed
        simpanDataObat();
    }//GEN-LAST:event_cmdsimpanActionPerformed

    private void cmdubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdubahActionPerformed
        rubahDataObat();        
    }//GEN-LAST:event_cmdubahActionPerformed

    private void cmdhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdhapusActionPerformed
hapusDataObat();        // TODO add your handling code here:
    }//GEN-LAST:event_cmdhapusActionPerformed

    private void cmdresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdresetActionPerformed
reset();        // TODO add your handling code here:
    }//GEN-LAST:event_cmdresetActionPerformed

    private void cmdkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdkeluarActionPerformed
      // TODO add your handling code here:
       this.dispose();
        new MenuUtama().setVisible(true);
    }//GEN-LAST:event_cmdkeluarActionPerformed

    private void tbobatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbobatMouseClicked
        // TODO add your handling code here
        dataSelect();
    }//GEN-LAST:event_tbobatMouseClicked

    private void tkdkategoriKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkdkategoriKeyReleased
        // TODO add your handling code here:
        dataProduk();
    }//GEN-LAST:event_tkdkategoriKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new obat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdhapus;
    private javax.swing.JButton cmdkeluar;
    private javax.swing.JButton cmdreset;
    private javax.swing.JButton cmdsimpan;
    private javax.swing.JButton cmdubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbobat;
    private javax.swing.JTextField tharga;
    private javax.swing.JTextField tkdkategori;
    private javax.swing.JTextField tkdobat;
    private javax.swing.JTextField tobat;
    private javax.swing.JTextField tstok;
    // End of variables declaration//GEN-END:variables
}
