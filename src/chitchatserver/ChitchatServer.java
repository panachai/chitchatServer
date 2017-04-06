package chitchatserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ChitchatServer extends javax.swing.JFrame {

    private DefaultTableModel model;
    private Server server;

    private DatagramSocket ds;
    private DatagramPacket dpIn, dpOut;
    private byte[] buffer;
    private InetAddress client;
    int clientPort;
    private String msgIn, msgOut;

    private ArrayList<InetAddress> userip; //IP นะ
    private ArrayList<Integer> portArray;

    public ChitchatServer() {
        initComponents();

        server = new Server();
        model = (DefaultTableModel) tbUser.getModel();

        userip = new ArrayList();
        portArray = new ArrayList();

        btStop.setEnabled(false);
        btClear.setEnabled(false);

        //show table status
        tbUser.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        btStart = new javax.swing.JButton();
        btStop = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUser = new javax.swing.JTable();
        btClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PORT : ");

        tfPort.setText("8888");

        btStart.setText("START");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btStop.setText("STOP");
        btStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStopActionPerformed(evt);
            }
        });

        lbStatus.setText("Status");

        tbUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Status"
            }
        ));
        jScrollPane1.setViewportView(tbUser);

        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btStart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btStop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btClear))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btStart)
                    .addComponent(btStop)
                    .addComponent(lbStatus)
                    .addComponent(btClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        server.start();

        tfPort.setEnabled(false);

        btStart.setEnabled(false);
        btStop.setEnabled(true);

        btClear.setEnabled(true);

        //show table status
        tbUser.setEnabled(true);
    }//GEN-LAST:event_btStartActionPerformed

    private void btStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStopActionPerformed
        server.interrupt();
        tfPort.setEnabled(true);

        btStart.setEnabled(true);
        btStop.setEnabled(false);

        btClear.setEnabled(false);

        //show table status
        tbUser.setEnabled(false);

    }//GEN-LAST:event_btStopActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        model.setRowCount(0);
    }//GEN-LAST:event_btClearActionPerformed

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
            java.util.logging.Logger.getLogger(ChitchatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChitchatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChitchatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChitchatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChitchatServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClear;
    private javax.swing.JButton btStart;
    private javax.swing.JButton btStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTable tbUser;
    private javax.swing.JTextField tfPort;
    // End of variables declaration//GEN-END:variables

    class Server extends Thread {

        public void run() {
            try {
                create();

                while (true) {
                    sleep(1);

                    //step 4 receive packet
                    ds.receive(dpIn);

                    //step 5 extract message (แปลง byte เป็น String)
                    msgIn = new String(dpIn.getData(), 0, dpIn.getLength());
                    System.out.println("from client " + msgIn);

                    //step 6 prepair packet for client
                    client = dpIn.getAddress();
                    clientPort = dpIn.getPort();

                    if (msgIn.equals("login")) {
                        addIP(client, clientPort);
                    } else if (msgIn.equals("logout")) {
                        deleteIP(client, clientPort);
                    } else {
                        sendEveryUser(msgIn);

                        //show in tb *new
                        model.addRow(new Object[]{client, msgIn});
                    }

                    //check client
//                    System.out.println("Check User have : " + checkUser(client.toString()));
                    //check id new or old
//                    if (checkUser(client, clientPort)) {
//                        System.out.println("old id");
//                    } else {
//                        System.out.println("new id");
//                    }
                }

            } catch (SocketException se) {
                System.out.println("Socket Error : " + se);
                System.exit(-1);
            } catch (IOException ioe) {
                System.out.println("IO error : " + ioe);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
                //step 8 close
                ds.close();

            }
        }

        public void addIP(InetAddress username, int ports) {
            //userip.add(username);
            //portArray.add(ports);

            System.out.println("useradd : " + userip.add(username));
            System.out.println("portadd : " + portArray.add(ports));

            System.out.println("useradd 2 : " + username);
            System.out.println("portadd 2 : " + ports);
        }

        public void deleteIP(InetAddress username, int ports) {

            int usersize = userip.size();
            for (int i = 0; i < usersize; i++) {
                if (portArray.get(i).equals(ports)) {
                    userip.remove(i);
                    portArray.remove(i);
                }
            }
        }

        public void create() {
            try {  //step 1 open port
                ds = new DatagramSocket(8888);

                //step 2 create buffer
                buffer = new byte[256];

                //setp 3 create datagram packet (เพื่อรับข้อมูล)
                dpIn = new DatagramPacket(buffer, buffer.length);
                lbStatus.setText("Running..");

            } catch (SocketException se) {
                System.out.println("Socket Error : " + se);
                System.exit(-1);
            }

        }

        /*
        public boolean checkUser(InetAddress username, int ports) {
            int usersize = userip.size();
            for (int i = 0; i < usersize; i++) {
                if (userip.get(i).equals(username)) {
                    return true;
                }
            }
            //ในกรณี Username ไม่มีใน Array ให้ add เข้า
            userip.add(username);
            portArray.add(ports);
            return false;
        }
         */
        public void sendEveryUser(String msg) {
            int usersize = userip.size();

            msgOut = msg;
            for (int i = 0; i < usersize; i++) {
//InetAddress
                try {

                    System.out.println("userip " + i + " : " + userip.get(i));
                    System.out.println("port " + i + " : " + portArray.get(i));

                    InetAddress clientSend = userip.get(i);
                    int clientPortSend = portArray.get(i);

                    dpOut = new DatagramPacket(msgOut.getBytes(), msgOut.length(), clientSend, clientPortSend);

                    //step 7 sent packet
                    ds.send(dpOut);
                    System.out.println("Send in for (sendEveryUser) : " + i + " : " + clientSend + "\n port : " + clientPortSend);

                } catch (IOException ioe) {
                    System.out.println("IO error : " + ioe);
                }

            }
        }

    }
}
