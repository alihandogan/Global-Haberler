
package News_alihan;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class Yapilacaklar extends javax.swing.JFrame {

    
    public Yapilacaklar() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTreePost = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTreePost.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreePost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreePostMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTreePost);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Id", "Id", "Title", "Body"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Paylaşımları Getir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Seçilen Öğe Hakkında Bilgi :");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1791, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Butona basılınca yapılacak işlemler
        
        //http isteği yapılıyor.
        Apache http = new Apache("https://jsonplaceholder.typicode.com/posts");
        String gelenJson = null;
        try {
            //json değeri gelenJson değişkenine aktarıldı.
            gelenJson = http.getRequest();
        } catch (IOException ex) {
            Logger.getLogger(Yapilacaklar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObjectMapper mapper = new ObjectMapper();
        //jtree ye en parent ekleme
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Paylaşımlar");
        DefaultTableModel tableModel = (DefaultTableModel)jTable1.getModel();
        DefaultMutableTreeNode userIdTreeNode =null;
        Post[] postlar = null;
        try {
            postlar = mapper.readValue(gelenJson, Post[].class);
        } catch (IOException ex) {
            Logger.getLogger(Yapilacaklar.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Post singlePost:postlar){
           
            userIdTreeNode = new DefaultMutableTreeNode("User Id :"+singlePost.getUserId());
            root.add(userIdTreeNode);
            userIdTreeNode.add(new DefaultMutableTreeNode("Id :"+singlePost.getId()));
            userIdTreeNode.add(new DefaultMutableTreeNode("Title :"+singlePost.getTitle()));
            userIdTreeNode.add(new DefaultMutableTreeNode("Body :"+singlePost.getBody()));
            
            tableModel.addRow(new Object[]{singlePost.getUserId(),singlePost.getId(),singlePost.getTitle(),singlePost.getBody()});
        }
        
        
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        jTreePost.setModel(treeModel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel tabelModel = (DefaultTableModel)jTable1.getModel();
        
        int selectedRowIndex = jTable1.getSelectedRow();
        int selectedColumn = jTable1.getSelectedColumn();
        
        jLabel2.setText(tabelModel.getValueAt(selectedRowIndex, selectedColumn).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTreePostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreePostMouseClicked
        // TODO add your handling code here:
        DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)jTreePost.getSelectionPath().getLastPathComponent();
        String gelenDeger = selectionNode.getUserObject().toString();
        jLabel2.setText(gelenDeger);
    }//GEN-LAST:event_jTreePostMouseClicked

   
    public static void main(String args[]) throws IOException {
        
        
        
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Yapilacaklar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTreePost;
    // End of variables declaration//GEN-END:variables
}
