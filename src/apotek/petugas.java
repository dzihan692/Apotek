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

public class petugas extends javax.swing.JFrame {
//membuat objek    
    private DefaultTableModel model;
    
    //deklarasi variabel
    String Id_petugas, Nm_petugas, Alamat, No_telp;
 
    /**
     * Creates new form Petugas
     */
    public petugas() {
        initComponents();
        //membuat obyek
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tbpetugas.setModel(model);
        model.addColumn("ID PETUGAS");
        model.addColumn("NAMA PETUGAS");
        model.addColumn("ALAMAT");
        model.addColumn("NO TELP");
       
        
        //fungsi ambil data
        getDataPetugas();
    }
    
      //fungsi membaca data kategori
    public void getDataPetugas(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksi.getKoneksi().createStatement();
            
            //perintah sql untuk membaca data dari tabel gaji        
            String sql = "SELECT * FROM tbpetugas";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[4];
                obj[0]=res.getString("Id_petugas");
                obj[1]=res.getString("Nm_petugas");
                obj[2]=res.getString("Alamat");
                obj[3]=res.getString("No_telp");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
 public void loadDataPetugas(){
        //mengambil data dari textbox dan menyimpan nilainya pada variabel
        Id_petugas= tidpetugas.getText();
        Nm_petugas = tnmpetugas.getText();
        Alamat = talamat.getText();
        No_telp = tnotelp.getText();
    }
    
    public void dataSelect(){
        //deklarasi variabel
        int i = tbpetugas.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        tidpetugas.setText(""+model.getValueAt(i,0));
        tnmpetugas.setText(""+model.getValueAt(i,1));
        talamat.setText(""+model.getValueAt(i,2));
        tnotelp.setText(""+model.getValueAt(i,3));
    }
    
    public void reset(){
        Id_petugas = "";
        Nm_petugas = "";
        Alamat = "";
        No_telp = "";
       
        tidpetugas.setText(Id_petugas);
        tnmpetugas.setText(Nm_petugas);
        talamat.setText(Alamat);
        tnotelp.setText(No_telp);
    }
    
    public void simpanDataPetugas(){
         //panggil fungsi load data
        loadDataPetugas();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksi.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO tbpetugas(Id_petugas, Nm_petugas, Alamat, No_telp )"
                            + "VALUES('"+ Id_petugas +"','"+ Nm_petugas +"','"+ Alamat +"','"+ No_telp +"')";
            PreparedStatement p = (PreparedStatement) koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataPetugas();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void rubahDataPetugas(){
          //panggil fungsi load data
        loadDataPetugas();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksi.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String sql  =   "UPDATE tbpetugas SET Nm_petugas  = '"+ Nm_petugas +"',"
                            + "Alamat = '"+ Alamat +"',"  
                            + "No_telp  = '"+ No_telp +"'"
                            +"WHERE Id_petugas = '"+ Id_petugas +"'";
            PreparedStatement p = (PreparedStatement) koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataPetugas();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void hapusDataPetugas(){
        //panggil fungsi ambil data
        loadDataPetugas(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA"+ Id_petugas +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksi.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM tbpetugas WHERE Id_petugas='"+ Id_petugas +"'";
                PreparedStatement p =(PreparedStatement)koneksi.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataPetugas();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "KENANGAN BERSAMANYA BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpetugas = new javax.swing.JTable();
        tidpetugas = new javax.swing.JTextField();
        tnmpetugas = new javax.swing.JTextField();
        talamat = new javax.swing.JTextField();
        tnotelp = new javax.swing.JTextField();
        cmdsimpan = new javax.swing.JButton();
        cmdubah = new javax.swing.JButton();
        cmdhapus = new javax.swing.JButton();
        cmdkeluar = new javax.swing.JButton();
        cmdreset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setText("MASTER DATA PETUGAS");

        jLabel3.setText("ID PETUGAS");

        jLabel4.setText("NAMA PETUGAS");

        jLabel6.setText("ALAMAT");

        jLabel7.setText("NO.TELP");

        tbpetugas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbpetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbpetugas);

        tidpetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidpetugasActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(cmdsimpan)
                                .addGap(18, 18, 18)
                                .addComponent(cmdubah)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cmdhapus)
                                .addGap(18, 18, 18)
                                .addComponent(cmdreset)
                                .addGap(18, 18, 18)
                                .addComponent(cmdkeluar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tidpetugas, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tnmpetugas, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(talamat, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tnotelp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))))
                .addContainerGap(245, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tidpetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tnmpetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(talamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tnotelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdsimpan)
                    .addComponent(cmdhapus)
                    .addComponent(cmdubah)
                    .addComponent(cmdkeluar)
                    .addComponent(cmdreset))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdkeluarActionPerformed
        
         this.dispose();
        new MenuUtama().setVisible(true);
    }//GEN-LAST:event_cmdkeluarActionPerformed

    private void cmdsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsimpanActionPerformed

        simpanDataPetugas();
    }//GEN-LAST:event_cmdsimpanActionPerformed

    private void cmdubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdubahActionPerformed
       rubahDataPetugas();
    }//GEN-LAST:event_cmdubahActionPerformed

    private void cmdresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdresetActionPerformed
        reset();
    }//GEN-LAST:event_cmdresetActionPerformed

    private void cmdhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdhapusActionPerformed
       hapusDataPetugas();
    }//GEN-LAST:event_cmdhapusActionPerformed

    private void tidpetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidpetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidpetugasActionPerformed

    private void tbpetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpetugasMouseClicked
        // TODO add your handling code here:
        dataSelect();
    }//GEN-LAST:event_tbpetugasMouseClicked

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
            java.util.logging.Logger.getLogger(petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new petugas().setVisible(true);
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField talamat;
    private javax.swing.JTable tbpetugas;
    private javax.swing.JTextField tidpetugas;
    private javax.swing.JTextField tnmpetugas;
    private javax.swing.JTextField tnotelp;
    // End of variables declaration//GEN-END:variables
}
