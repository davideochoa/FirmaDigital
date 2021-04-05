/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebafirmadigital;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class JFPrincipal extends javax.swing.JFrame {
    JDesktopPane dp = new JDesktopPane();
    JIFGenerarLlaves JIFGLL = new JIFGenerarLlaves();
    JIFFirmarArchivo JIFFA = new JIFFirmarArchivo();
    JIFVerificar JIFV = new JIFVerificar();
    JIFGenerarDocCertificado JIFGDC = new JIFGenerarDocCertificado();
    JIFProcesoFirmadoDigital JIFPFD = new JIFProcesoFirmadoDigital();
    Cifrado cifrado = new Cifrado();
    public JFPrincipal() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        dp.add(JIFGLL);
        dp.add(JIFFA);
        dp.add(JIFV);
        dp.add(JIFGDC);
        dp.add(JIFPFD);
        dp.add(cifrado);
        
        this.setContentPane(dp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.pack();
        /*
        Provider[] proveedores = Security.getProviders();
        int x = 0;
        while(x < proveedores.length){
            System.out.println(proveedores[x].getName());
            System.out.println(proveedores[x].getInfo());
            System.out.println(proveedores[x].getVersion());
            
            if (proveedores) {
                Enumeration propiedades = listaProv[i].propertyNames();
                while (propiedades.hasMoreElements()) {
                  String clave = (String) propiedades.nextElement();
                  String valor = listaProv[i].getProperty(clave);
                  System.out.println("  " + clave + " = " + valor);
                }
              }
            
            System.out.println("-----------------------");
            x++;
        }
        */
    /*boolean listarProps=true;    
    System.out.println("------------------------------------");
    System.out.println("Proveedores instalados en su sistema");
    System.out.println("------------------------------------");
    Provider[] listaProv = Security.getProviders();
    for (int i = 0; i < listaProv.length; i++) {
      System.out.println("Núm. proveedor : "    + (i + 1));
      System.out.println("Nombre         : "    + listaProv[i].getName());
      System.out.println("Versión        : "    + listaProv[i].getVersion());
      System.out.println("Información    :\n  " + listaProv[i].getInfo());
      System.out.println("Propiedades    :");
      if (listarProps) {
        Enumeration propiedades = listaProv[i].propertyNames();
        while (propiedades.hasMoreElements()) {
          String clave = (String) propiedades.nextElement();
          String valor = listaProv[i].getProperty(clave);
          System.out.println("  " + clave + " = " + valor);
        }
      }
      System.out.println("------------------------------------");
      System.out.println();
    }*/
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JMBar = new javax.swing.JMenuBar();
        JMSistema = new javax.swing.JMenu();
        JMIGenerarLlaves = new javax.swing.JMenuItem();
        JMIFirmarArchivo = new javax.swing.JMenuItem();
        JMIVerificarFrima = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prueba de Concepto de Firma Digital");

        JMSistema.setText("Sistema");

        JMIGenerarLlaves.setText("Generar llaves");
        JMIGenerarLlaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIGenerarLlavesActionPerformed(evt);
            }
        });
        JMSistema.add(JMIGenerarLlaves);

        JMIFirmarArchivo.setText("Firmar Archivo");
        JMIFirmarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIFirmarArchivoActionPerformed(evt);
            }
        });
        JMSistema.add(JMIFirmarArchivo);

        JMIVerificarFrima.setText("VerificarFirma");
        JMIVerificarFrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIVerificarFrimaActionPerformed(evt);
            }
        });
        JMSistema.add(JMIVerificarFrima);

        jMenuItem1.setText("Proceso de Firmado");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        JMSistema.add(jMenuItem1);

        jMenuItem2.setText("Encriptado");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        JMSistema.add(jMenuItem2);

        JMBar.add(JMSistema);

        setJMenuBar(JMBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMIGenerarLlavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIGenerarLlavesActionPerformed
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = JIFGLL.getSize();
        
        int anchoPantalla = pantalla.width;
        int altoPantalla = pantalla.height;        
        int anchoVentana = ventana.width;
        int altoVentana = ventana.height;
        
        JIFGLL.setLocation((anchoPantalla-anchoVentana)/2,((altoPantalla-altoVentana) / 2)-20);
        JIFGLL.setVisible(true);
    }//GEN-LAST:event_JMIGenerarLlavesActionPerformed

    private void JMIFirmarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIFirmarArchivoActionPerformed
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = JIFFA.getSize();
        
        int anchoPantalla = pantalla.width;
        int altoPantalla = pantalla.height;        
        int anchoVentana = ventana.width;
        int altoVentana = ventana.height;
        
        JIFFA.setLocation((anchoPantalla-anchoVentana)/2,((altoPantalla-altoVentana) / 2)-20);
        JIFFA.setVisible(true);
    }//GEN-LAST:event_JMIFirmarArchivoActionPerformed

    private void JMIVerificarFrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIVerificarFrimaActionPerformed
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = JIFV.getSize();
        
        int anchoPantalla = pantalla.width;
        int altoPantalla = pantalla.height;        
        int anchoVentana = ventana.width;
        int altoVentana = ventana.height;
        
        JIFV.setLocation((anchoPantalla-anchoVentana)/2,((altoPantalla-altoVentana) / 2)-20);        
        JIFV.setVisible(true);
    }//GEN-LAST:event_JMIVerificarFrimaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(JIFPFD == null)
            JIFPFD = new JIFProcesoFirmadoDigital();
        
        JIFPFD.setVisible(true);
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = JIFFA.getSize();

        int anchoPantalla = pantalla.width;
        int altoPantalla = pantalla.height;        
        int anchoVentana = ventana.width;
        int altoVentana = ventana.height;
        
        /*System.out.println("anchoPantalla:"+anchoPantalla+ " altoPantalla:"+altoPantalla);
        System.out.println("anchoVentana:"+anchoVentana+ " altoVentana:"+altoVentana);*/

        JIFPFD.setLocation((anchoPantalla-anchoVentana)/2,((altoPantalla-altoVentana) / 2)-180); 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(cifrado == null)
            cifrado = new Cifrado();
        
        cifrado.setVisible(true);
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = JIFFA.getSize();

        int anchoPantalla = pantalla.width;
        int altoPantalla = pantalla.height;        
        int anchoVentana = ventana.width;
        int altoVentana = ventana.height;
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar JMBar;
    private javax.swing.JMenuItem JMIFirmarArchivo;
    private javax.swing.JMenuItem JMIGenerarLlaves;
    private javax.swing.JMenuItem JMIVerificarFrima;
    private javax.swing.JMenu JMSistema;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
