/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebafirmadigital;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Cifrado extends javax.swing.JInternalFrame {
    PublicKey llavePublica = null;  
    PrivateKey llavePrivada = null;
    
    byte[] encryptedMessage = null;
    byte[] decryptedMessage = null;
    
    FileFilter filtro1_docx = new FileNameExtensionFilter("Archivos docx", "docx");
    FileFilter filtro1_FirmaDigital = new FileNameExtensionFilter("Archivos Firma Digital", "Firma_Digital");
    FileFilter filtro1_llavePublica = new FileNameExtensionFilter("Archivos Llave Publica", "Llave_Publica");
    FileFilter filtro1_llavePrivada = new FileNameExtensionFilter("Archivos Llave Privada", "Llave_Privada");
    public Cifrado() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBBuscarLlavePublica = new javax.swing.JButton();
        JBBuscarLlavePrivada = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTArea_Texto2Cifrar = new javax.swing.JTextArea();
        JCBox_LlavePublica = new javax.swing.JCheckBox();
        JCBox_LlavePrivada = new javax.swing.JCheckBox();
        JBEncriptar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTArea_TextoDesencriptado = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTArea_TextoEncriptado = new javax.swing.JTextArea();
        JB_Desencriptar = new javax.swing.JButton();

        JBBuscarLlavePublica.setText("Buscar Llave Publica");
        JBBuscarLlavePublica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarLlavePublicaActionPerformed(evt);
            }
        });

        JBBuscarLlavePrivada.setText("Buscar Llave Privada");
        JBBuscarLlavePrivada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarLlavePrivadaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Texto a Encriptar"));

        JTArea_Texto2Cifrar.setColumns(20);
        JTArea_Texto2Cifrar.setRows(5);
        jScrollPane1.setViewportView(JTArea_Texto2Cifrar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        JCBox_LlavePublica.setText("Llave Publica Cargada");
        JCBox_LlavePublica.setEnabled(false);

        JCBox_LlavePrivada.setText("Llave Privada Cargada");
        JCBox_LlavePrivada.setEnabled(false);

        JBEncriptar.setText("Encriptar usando Llave Privada");
        JBEncriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBEncriptarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Texto a Desencriptado"));

        JTArea_TextoDesencriptado.setColumns(20);
        JTArea_TextoDesencriptado.setRows(5);
        jScrollPane2.setViewportView(JTArea_TextoDesencriptado);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Texto a Encriptado"));

        JTArea_TextoEncriptado.setColumns(20);
        JTArea_TextoEncriptado.setRows(5);
        JTArea_TextoEncriptado.setEnabled(false);
        jScrollPane3.setViewportView(JTArea_TextoEncriptado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        JB_Desencriptar.setText("Desencriptar usando Llave Publica");
        JB_Desencriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_DesencriptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JBBuscarLlavePrivada)
                                .addGap(18, 18, 18)
                                .addComponent(JCBox_LlavePrivada))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JBBuscarLlavePublica)
                                .addGap(18, 18, 18)
                                .addComponent(JCBox_LlavePublica))
                            .addComponent(JBEncriptar)
                            .addComponent(JB_Desencriptar))
                        .addGap(0, 270, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarLlavePublica)
                    .addComponent(JCBox_LlavePublica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarLlavePrivada)
                    .addComponent(JCBox_LlavePrivada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBEncriptar)
                .addGap(13, 13, 13)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JB_Desencriptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBBuscarLlavePublicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarLlavePublicaActionPerformed
        File fileLlavePublica = JFileChoserBuscar(filtro1_llavePublica);
         
        if(fileLlavePublica != null){
            try {
                FileInputStream keyfis = new FileInputStream(fileLlavePublica);
                byte[] encKey = new byte[keyfis.available()];
                keyfis.read(encKey);
                keyfis.close();

                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA","SunRsaSign");
                llavePublica = keyFactory.generatePublic(pubKeySpec);
                
                JCBox_LlavePublica.setSelected(true);
            } catch (FileNotFoundException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PUBLICA");
                llavePublica = null;
            } catch (IOException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PUBLICA");
                llavePublica = null;
            } catch (NoSuchAlgorithmException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PUBLICA");
                llavePublica = null;
            } catch (NoSuchProviderException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PUBLICA");
                llavePublica = null;
            } catch (InvalidKeySpecException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PUBLICA");
                llavePublica = null;
            } 
        }
    }//GEN-LAST:event_JBBuscarLlavePublicaActionPerformed

    private void JBBuscarLlavePrivadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarLlavePrivadaActionPerformed
        File file = JFileChoserBuscar(filtro1_llavePrivada);
         
        if(file != null){
            try {         
                FileInputStream keyfis = new FileInputStream(file);
                byte[] encKey = new byte[keyfis.available()];
                keyfis.read(encKey);
                keyfis.close();

                PKCS8EncodedKeySpec pubKeySpec = new PKCS8EncodedKeySpec(encKey);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA","SunRsaSign");
                llavePrivada = keyFactory.generatePrivate(pubKeySpec);
                
                JCBox_LlavePrivada.setSelected(true);
            } catch (FileNotFoundException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
                JCBox_LlavePrivada.setSelected(false);
                llavePrivada = null;
            } catch (IOException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
                JCBox_LlavePrivada.setSelected(false);
                llavePrivada = null;
            } catch (NoSuchAlgorithmException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
                JCBox_LlavePrivada.setSelected(false);
                llavePrivada = null;
            } catch (NoSuchProviderException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
                JCBox_LlavePrivada.setSelected(false);
                llavePrivada = null;
            } catch (InvalidKeySpecException ex) {
                MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
                JCBox_LlavePrivada.setSelected(false);
                llavePrivada = null;
            }
        }
    }//GEN-LAST:event_JBBuscarLlavePrivadaActionPerformed

    private void JBEncriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBEncriptarActionPerformed
        try {
            //Cipher encrypt=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            Cipher encrypt=Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, llavePublica);
            encryptedMessage = encrypt.doFinal(JTArea_Texto2Cifrar.getText().getBytes());   
            
            JTArea_TextoEncriptado.setText(new String (new String(Base64.getEncoder().encode(encryptedMessage), "UTF-8")));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JBEncriptarActionPerformed

    private void JB_DesencriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_DesencriptarActionPerformed
        try {
            //Cipher decrypt=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            Cipher decrypt=Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, llavePrivada);
            
            decryptedMessage = decrypt.doFinal(encryptedMessage);
            
            JTArea_TextoDesencriptado.setText(new String(decryptedMessage));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JB_DesencriptarActionPerformed

    private File JFileChoserBuscar(FileFilter filtro){
        File archivo = null;
        JFileChooser JFChooser = new JFileChooser();
        JFChooser.setCurrentDirectory(new File("./"));
        JFChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );         
        JFChooser.setFileFilter(filtro);
        int seleccion = JFChooser.showOpenDialog( this );
        
        if(seleccion == JFileChooser.APPROVE_OPTION){
            archivo = JFChooser.getSelectedFile();
                
            /*archivo.getAbsolutePath():D:\NetBeansProjects\PruebaFirmaDigital\OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx
            archivo.getCanonicalPath():D:\NetBeansProjects\PruebaFirmaDigital\OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx
            archivo.getName():OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx
            archivo.getParent():D:\NetBeansProjects\PruebaFirmaDigital
            archivo.getPath():D:\NetBeansProjects\PruebaFirmaDigital\OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx

            System.out.println("archivo.getAbsolutePath():"+archivo.getAbsolutePath());
            System.out.println("archivo.getCanonicalPath():"+archivo.getCanonicalPath());
            System.out.println("archivo.getName():"+archivo.getName());
            System.out.println("archivo.getParent():"+archivo.getParent());
            System.out.println("archivo.getPath():"+archivo.getPath());*/            
        }
        else{
            archivo = null;
        }
        return archivo;
    }

    private void MensajeError(String mensajeException,String mensaje){
        JOptionPane.showMessageDialog(this,mensajeException,mensaje,JOptionPane.ERROR_MESSAGE);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBBuscarLlavePrivada;
    private javax.swing.JButton JBBuscarLlavePublica;
    private javax.swing.JButton JBEncriptar;
    private javax.swing.JButton JB_Desencriptar;
    private javax.swing.JCheckBox JCBox_LlavePrivada;
    private javax.swing.JCheckBox JCBox_LlavePublica;
    private javax.swing.JTextArea JTArea_Texto2Cifrar;
    private javax.swing.JTextArea JTArea_TextoDesencriptado;
    private javax.swing.JTextArea JTArea_TextoEncriptado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
