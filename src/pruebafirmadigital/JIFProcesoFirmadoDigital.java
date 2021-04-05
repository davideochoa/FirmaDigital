/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebafirmadigital;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author usuario
 */
public class JIFProcesoFirmadoDigital extends JInternalFrame {
    PrivateKey llavePrivada = null;
    PublicKey llavePublica = null;
    
    File File_Documento = null;
    File File_FirmaDigital = null;
    
    byte[] firmaDigital;    
    
    String cadenaOriginalFirma = "||";
    String cadenaOriginalFirmaBase64 = "";
    
    FileFilter filtro1_docx = new FileNameExtensionFilter("Archivos docx", "docx");
    FileFilter filtro1_FirmaDigital = new FileNameExtensionFilter("Archivos Firma Digital", "Firma_Digital");
    FileFilter filtro1_llavePublica = new FileNameExtensionFilter("Archivos Llave Publica", "Llave_Publica");
    FileFilter filtro1_llavePrivada = new FileNameExtensionFilter("Archivos Llave Privada", "Llave_Privada");
    public JIFProcesoFirmadoDigital() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        JPArchivo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        JTFFolio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JDCFecha = new com.toedter.calendar.JDateChooser();
        JBBuscarLlaveprivadaArchivo = new javax.swing.JButton();
        JCBLlavePrivadaCargadaArchivo = new javax.swing.JCheckBox();
        JBModificarFirmarDocumento4Archivo = new javax.swing.JButton();
        JBBuscarDocumentoArchivo = new javax.swing.JButton();
        JCBDocCargadoArchivo = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAOutput = new javax.swing.JTextArea();
        JPGenerarLlaves = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JTFNombreLlaves = new javax.swing.JTextField();
        JBGenerarLlaves = new javax.swing.JButton();
        JPEmisor = new javax.swing.JPanel();
        JBBuscarDocumento2FirmarEmisor = new javax.swing.JButton();
        JBBuscarLlavePrivadaEmisor = new javax.swing.JButton();
        JCBDocumentoCargado = new javax.swing.JCheckBox();
        JCBLlavePrivadaCargadaEmisor = new javax.swing.JCheckBox();
        JBFirmarDocumentoConLlavePrivada = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        JBBuscarDocumentoEmisor = new javax.swing.JButton();
        JCBDocumentoEmisorCargado = new javax.swing.JCheckBox();
        JBBuscarFirmaDigitalEmisor = new javax.swing.JButton();
        JBBuscarLlavePublicaEmisor = new javax.swing.JButton();
        JCBFirmaDigitalCargadaArchivo = new javax.swing.JCheckBox();
        JCBLlavePublicaCargadaArchivo = new javax.swing.JCheckBox();
        JBVerificarFirmaDigital = new javax.swing.JButton();

        setTitle("Proceso de Firmado de Oficios y Archivos");
        setMinimumSize(new java.awt.Dimension(10, 34));

        JPArchivo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "  Entidad Archivo  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setText("Folio:");

        JTFFolio.setMinimumSize(new java.awt.Dimension(20, 20));

        jLabel5.setText("Fecha:");

        JBBuscarLlaveprivadaArchivo.setText("Buscar Llave Privada");
        JBBuscarLlaveprivadaArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarLlaveprivadaArchivoActionPerformed(evt);
            }
        });

        JCBLlavePrivadaCargadaArchivo.setText("Llave Cargada");
        JCBLlavePrivadaCargadaArchivo.setEnabled(false);

        JBModificarFirmarDocumento4Archivo.setText("Modificar y Firmar Documento con Llave Privada de Archivo");
        JBModificarFirmarDocumento4Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBModificarFirmarDocumento4ArchivoActionPerformed(evt);
            }
        });

        JBBuscarDocumentoArchivo.setText("Buscar Documento Firmado");
        JBBuscarDocumentoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarDocumentoArchivoActionPerformed(evt);
            }
        });

        JCBDocCargadoArchivo.setText("Documento Cargado");
        JCBDocCargadoArchivo.setEnabled(false);

        javax.swing.GroupLayout JPArchivoLayout = new javax.swing.GroupLayout(JPArchivo);
        JPArchivo.setLayout(JPArchivoLayout);
        JPArchivoLayout.setHorizontalGroup(
            JPArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPArchivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPArchivoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTFFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDCFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JPArchivoLayout.createSequentialGroup()
                        .addComponent(JBBuscarDocumentoArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCBDocCargadoArchivo))
                    .addGroup(JPArchivoLayout.createSequentialGroup()
                        .addComponent(JBBuscarLlaveprivadaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCBLlavePrivadaCargadaArchivo))
                    .addComponent(JBModificarFirmarDocumento4Archivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPArchivoLayout.setVerticalGroup(
            JPArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPArchivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarDocumentoArchivo)
                    .addComponent(JCBDocCargadoArchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(JTFFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarLlaveprivadaArchivo)
                    .addComponent(JCBLlavePrivadaCargadaArchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBModificarFirmarDocumento4Archivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "  Proceso  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JTAOutput.setColumns(20);
        JTAOutput.setRows(5);
        jScrollPane1.setViewportView(JTAOutput);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        JPGenerarLlaves.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "  Generador de LLaves Publica y Privada  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Nombre:");

        JBGenerarLlaves.setText("Generar Llaves");
        JBGenerarLlaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBGenerarLlavesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPGenerarLlavesLayout = new javax.swing.GroupLayout(JPGenerarLlaves);
        JPGenerarLlaves.setLayout(JPGenerarLlavesLayout);
        JPGenerarLlavesLayout.setHorizontalGroup(
            JPGenerarLlavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPGenerarLlavesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTFNombreLlaves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBGenerarLlaves)
                .addGap(6, 6, 6))
        );
        JPGenerarLlavesLayout.setVerticalGroup(
            JPGenerarLlavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPGenerarLlavesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPGenerarLlavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JTFNombreLlaves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBGenerarLlaves))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPEmisor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "  Entidad Emisora  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JBBuscarDocumento2FirmarEmisor.setText("Buscar Documento a Firmar");
        JBBuscarDocumento2FirmarEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarDocumento2FirmarEmisorActionPerformed(evt);
            }
        });

        JBBuscarLlavePrivadaEmisor.setText("Buscar Llave Privada");
        JBBuscarLlavePrivadaEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarLlavePrivadaEmisorActionPerformed(evt);
            }
        });

        JCBDocumentoCargado.setText("Documento Cargado");
        JCBDocumentoCargado.setEnabled(false);

        JCBLlavePrivadaCargadaEmisor.setText("Llave Privada Cargada");
        JCBLlavePrivadaCargadaEmisor.setEnabled(false);

        JBFirmarDocumentoConLlavePrivada.setText("Firmar Documento con Llave Privada Emisor");
        JBFirmarDocumentoConLlavePrivada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBFirmarDocumentoConLlavePrivadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPEmisorLayout = new javax.swing.GroupLayout(JPEmisor);
        JPEmisor.setLayout(JPEmisorLayout);
        JPEmisorLayout.setHorizontalGroup(
            JPEmisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPEmisorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPEmisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPEmisorLayout.createSequentialGroup()
                        .addGroup(JPEmisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JBBuscarLlavePrivadaEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBBuscarDocumento2FirmarEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPEmisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JCBDocumentoCargado)
                            .addComponent(JCBLlavePrivadaCargadaEmisor))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JBFirmarDocumentoConLlavePrivada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        JPEmisorLayout.setVerticalGroup(
            JPEmisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPEmisorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPEmisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarDocumento2FirmarEmisor)
                    .addComponent(JCBDocumentoCargado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPEmisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarLlavePrivadaEmisor)
                    .addComponent(JCBLlavePrivadaCargadaEmisor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBFirmarDocumentoConLlavePrivada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "  Verificar Firma Digital  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JBBuscarDocumentoEmisor.setText("Buscar Documento Firmado");
        JBBuscarDocumentoEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarDocumentoEmisorActionPerformed(evt);
            }
        });

        JCBDocumentoEmisorCargado.setText("Documento Cargado");
        JCBDocumentoEmisorCargado.setEnabled(false);

        JBBuscarFirmaDigitalEmisor.setText("Buscar Firma Digital");
        JBBuscarFirmaDigitalEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarFirmaDigitalEmisorActionPerformed(evt);
            }
        });

        JBBuscarLlavePublicaEmisor.setText("Buscar Llave Publica");
        JBBuscarLlavePublicaEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarLlavePublicaEmisorActionPerformed(evt);
            }
        });

        JCBFirmaDigitalCargadaArchivo.setText("Firma Cargada");
        JCBFirmaDigitalCargadaArchivo.setEnabled(false);

        JCBLlavePublicaCargadaArchivo.setText("Llave Cargada");
        JCBLlavePublicaCargadaArchivo.setEnabled(false);
        JCBLlavePublicaCargadaArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBLlavePublicaCargadaArchivoActionPerformed(evt);
            }
        });

        JBVerificarFirmaDigital.setText("Verificar Firma Digital >< Documento Usando Llave Publica");
        JBVerificarFirmaDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVerificarFirmaDigitalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JBVerificarFirmaDigital)
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JBBuscarDocumentoEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBBuscarFirmaDigitalEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBBuscarLlavePublicaEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JCBDocumentoEmisorCargado)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JCBFirmaDigitalCargadaArchivo)
                                    .addComponent(JCBLlavePublicaCargadaArchivo))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarDocumentoEmisor)
                    .addComponent(JCBDocumentoEmisorCargado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarFirmaDigitalEmisor)
                    .addComponent(JCBFirmaDigitalCargadaArchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBBuscarLlavePublicaEmisor)
                    .addComponent(JCBLlavePublicaCargadaArchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBVerificarFirmaDigital)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JPGenerarLlaves, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 344, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JPGenerarLlaves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JPEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JPArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        JPArchivo.getAccessibleContext().setAccessibleName("Entidad Verificadora");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBGenerarLlavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBGenerarLlavesActionPerformed
        try {
            JTAOutput.setText("");
            String nombreLlaves = JTFNombreLlaves.getText();

            JTAOutput.append("Creando KeyPairGenerator\n");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA","SUN");

            JTAOutput.append("Creando SecureRandom\n");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG","SUN");
            keyGen.initialize(1024, random);

            JTAOutput.append("Creando KeyPair\n");
            KeyPair pair = keyGen.generateKeyPair();
            llavePrivada = pair.getPrivate();
            llavePublica = pair.getPublic();

            JTAOutput.append("Creando "+nombreLlaves+"_KeyPublic\n");
            /* save the public key in a file */
            byte[] key = llavePublica.getEncoded();
            FileOutputStream keyfos = new FileOutputStream("./Llaves/"+nombreLlaves+"_KeyPublic.Llave_Publica");
            keyfos.write(key);
            keyfos.close();

            JTAOutput.append("Creando "+nombreLlaves+"_KeyPrivate\n");
            /* save the private key in a file */
            byte[] keypriv = llavePrivada.getEncoded();
            FileOutputStream keyprivfos = new FileOutputStream("./Llaves/"+nombreLlaves+"_KeyPrivate.Llave_Privada");
            keyprivfos.write(keypriv);
            keyprivfos.close();
            
            JTAOutput.append("Llaves "+nombreLlaves+" privada y publica creada con exito\n");
            JTAOutput.append("***************************************************************\n");
        } 
        catch (NoSuchAlgorithmException ex) {
            MensajeError(ex.getMessage(),"ERROR AL GENERAR LLAVES PRIVADA Y PUBLICA");
        }
        catch (NoSuchProviderException ex) {
            MensajeError(ex.getMessage(),"ERROR AL GENERAR LLAVES PRIVADA Y PUBLICA");
        }
        catch (FileNotFoundException ex) {
            MensajeError(ex.getMessage(),"ERROR AL GENERAR LLAVES PRIVADA Y PUBLICA");
        }
        catch (IOException ex) {
            MensajeError(ex.getMessage(),"ERROR AL GENERAR LLAVES PRIVADA Y PUBLICA");
        }
    }//GEN-LAST:event_JBGenerarLlavesActionPerformed
    
    private void JBBuscarDocumento2FirmarEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarDocumento2FirmarEmisorActionPerformed
        File_Documento = JFileChoserBuscar(filtro1_docx);
        JCBDocumentoCargado.setSelected(true);
    }//GEN-LAST:event_JBBuscarDocumento2FirmarEmisorActionPerformed
    
    private void JBBuscarLlavePrivadaEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarLlavePrivadaEmisorActionPerformed
        File archivo = JFileChoserBuscar(filtro1_llavePrivada);
        
        llavePrivada = null;
        
        if(archivo != null){            
            llavePrivada = cargarLlavePrivada(archivo);
            if(llavePrivada != null)
                JCBLlavePrivadaCargadaEmisor.setSelected(true);            
            else
                JCBLlavePrivadaCargadaEmisor.setSelected(false);            
        }
        else{
            JCBLlavePrivadaCargadaEmisor.setSelected(false);
            llavePrivada = null;
        }
    }//GEN-LAST:event_JBBuscarLlavePrivadaEmisorActionPerformed
    
    /*
    archivo.getAbsolutePath():D:\NetBeansProjects\PruebaFirmaDigital\OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx
    archivo.getCanonicalPath():D:\NetBeansProjects\PruebaFirmaDigital\OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx
    archivo.getName():OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx
    archivo.getParent():D:\NetBeansProjects\PruebaFirmaDigital
    archivo.getPath():D:\NetBeansProjects\PruebaFirmaDigital\OFICIO BAJA 191219 18 EQUIPOS DPD - copia.docx
    */

    private void JBFirmarDocumentoConLlavePrivadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBFirmarDocumentoConLlavePrivadaActionPerformed
        if(JCBDocumentoCargado.isSelected() == true && 
            JCBLlavePrivadaCargadaEmisor.isSelected() == true){            
            try {
                InputStream in = new FileInputStream(File_Documento);                
                OutputStream out = new FileOutputStream(new File("./Docs/"+File_Documento.getName()));
                
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }

                in.close();
                out.close();
                
                File_Documento = new File("./Docs/"+File_Documento.getName());                
            } catch (IOException ex){
                MensajeError(ex.getMessage(),"ERROR AL COPIAR DOCUMENTO");
                MensajeError(ex.getLocalizedMessage(),"ERROR AL COPIAR DOCUMENTO");
                MensajeError(ex.toString(),"ERROR AL COPIAR DOCUMENTO");
                JCBDocumentoCargado.setSelected(false);
            } 
            
            firmarDocumento(File_Documento);
           /* setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Task_FirmarDocumentoEmisor task = new Task_FirmarDocumentoEmisor();
            task.addPropertyChangeListener(task);
            task.execute(); */
        }//fin if
    }//GEN-LAST:event_JBFirmarDocumentoConLlavePrivadaActionPerformed

    private void JBBuscarLlaveprivadaArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarLlaveprivadaArchivoActionPerformed
        File archivo = JFileChoserBuscar(filtro1_llavePrivada);
        llavePrivada = null;
        if(archivo != null){
            llavePrivada = cargarLlavePrivada(archivo);
            if(llavePrivada != null)
                JCBLlavePrivadaCargadaArchivo.setSelected(true);   
            else
                JCBLlavePrivadaCargadaArchivo.setSelected(false);   
        }
    }//GEN-LAST:event_JBBuscarLlaveprivadaArchivoActionPerformed

    private void JBBuscarLlavePublicaEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarLlavePublicaEmisorActionPerformed
        File archivo = JFileChoserBuscar(filtro1_llavePublica);
        
        if(archivo != null ){  
            llavePublica = null;
            llavePublica = cargarLlavePublica(archivo);
            if(llavePublica != null)
                JCBLlavePublicaCargadaArchivo.setSelected(true);   
            else
                JCBLlavePublicaCargadaArchivo.setSelected(false);   
        }
    }//GEN-LAST:event_JBBuscarLlavePublicaEmisorActionPerformed

    private void JBVerificarFirmaDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVerificarFirmaDigitalActionPerformed

        try {
            Signature sig = Signature.getInstance("SHA256withDSA", "SUN");
            sig.initVerify(llavePublica);
            
            BufferedInputStream bufin = new BufferedInputStream(new FileInputStream(File_Documento));
            
            byte buffer[] = new byte[bufin.available()];
            //System.out.println("bufin.available():"+bufin.available());
            int len = bufin.read(buffer);            
            bufin.close();
            
            //System.out.println("len:"+len);
            sig.update(buffer);            
            
            boolean verifies = sig.verify(firmaDigital);
            
            if(verifies == true)
                JOptionPane.showMessageDialog(this,"VALIDA firma digital contra documento",                                                                                                
                                                "VERIFICAR DOCUMENTO",
                                                JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this,"RECHAZO de firma digital contra documento",                                                                                                
                                                "VERIFICAR DOCUMENTO",
                                                JOptionPane.INFORMATION_MESSAGE);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_JBVerificarFirmaDigitalActionPerformed

    private void JCBLlavePublicaCargadaArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBLlavePublicaCargadaArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCBLlavePublicaCargadaArchivoActionPerformed

    private void JBBuscarDocumentoEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarDocumentoEmisorActionPerformed
        File_Documento = JFileChoserBuscar(filtro1_docx);
        
        if(File_Documento != null)            
            JCBDocumentoEmisorCargado.setSelected(true);                
        else
            JCBDocumentoEmisorCargado.setSelected(false);
    }//GEN-LAST:event_JBBuscarDocumentoEmisorActionPerformed

    private void JBBuscarFirmaDigitalEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarFirmaDigitalEmisorActionPerformed
        File_FirmaDigital = JFileChoserBuscar(filtro1_FirmaDigital);

        if(File_FirmaDigital != null){
            FileInputStream sigfis = null;
            try {
                sigfis = new FileInputStream(File_FirmaDigital);
                firmaDigital = new byte[sigfis.available()];
                sigfis.read(firmaDigital);
                sigfis.close();
                
                JCBFirmaDigitalCargadaArchivo.setSelected(true);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "ERROR AL CARGAR FIRMA DIGITAL",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                JCBFirmaDigitalCargadaArchivo.setSelected(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "ERROR AL CARGAR FIRMA DIGITAL",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                JCBFirmaDigitalCargadaArchivo.setSelected(false);
            }
        }    
    }//GEN-LAST:event_JBBuscarFirmaDigitalEmisorActionPerformed

    private void JBModificarFirmarDocumento4ArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBModificarFirmarDocumento4ArchivoActionPerformed
        int valor = Integer.parseInt(JTFFolio.getText());
        Date fechaOficio = JDCFecha.getDate();
        SimpleDateFormat SDT_Dia_Oficio = new SimpleDateFormat("dd");
        SimpleDateFormat SDT_Mes_Oficio = new SimpleDateFormat("MMMMM");
        SimpleDateFormat SDT_Año_Oficio = new SimpleDateFormat("yyyy");
        String cadenaFecha = SDT_Dia_Oficio.format(fechaOficio)+" de "+
                SDT_Mes_Oficio.format(fechaOficio)+" del "+
                SDT_Año_Oficio.format(fechaOficio);

        if(valor > 0 && fechaOficio != null &&
                JCBDocCargadoArchivo.isSelected() == true &&
                JCBLlavePrivadaCargadaArchivo.isSelected() == true){            
            try {
                //ABRIR DOCUMENTO
                FileInputStream is = new FileInputStream(File_Documento);
                XWPFDocument docx = new XWPFDocument(is);

                //MODIFICAR CAMPOS DE ARCHIVO
                for (XWPFParagraph paragraph: docx.getParagraphs()){
                    for (XWPFRun run : paragraph.getRuns()) {
                        if (run.toString().contains("$«FECHA_OFICIO»$")) {
                            run.setText(run.toString().replace("$«FECHA_OFICIO»$", cadenaFecha), 0);
                        }
                        if (run.toString().contains("$«NUMERO_OFICIO»$")) {
                            run.setText(run.toString().replace("$«NUMERO_OFICIO»$", valor+""), 0);
                        }
                    }
                }

                is.close();
                File_Documento = new File("./DocsArchivo/OFICIO Num "+valor+" "+File_Documento.getName());
                //GUARDAR DOCUMENTO
                OutputStream out = new FileOutputStream(File_Documento);
                docx.write(out);
                out.close();

                //FIRMAR DOCUMENTO
                firmarDocumento(File_Documento);
                
                JTAOutput.append("Generando codigo QR\n");            
                /*6*/
                // ********************************* QR ******************************
                // Encode URL in QR format
                Writer writer = new QRCodeWriter();

                int qr_image_width = 200;
                int qr_image_height = 200;
                String IMAGE_FORMAT = "png";

                Map hintMap = new HashMap();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
                hintMap.put(EncodeHintType.MARGIN, -5);
                hintMap.put(EncodeHintType.CHARACTER_SET, "utf-8");                

                //*********** QR CADENA ORIGINAL *****************
                //BitMatrix matrix = writer.encode(certificado, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
                BitMatrix matrixCO = writer.encode(cadenaOriginalFirma, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height,hintMap);

                // Create buffered image to draw to
                BufferedImage imageCO = new BufferedImage(qr_image_width,qr_image_height, BufferedImage.TYPE_INT_RGB);

                // Iterate through the matrix and draw the pixels to the image
                for (int y = 0; y < qr_image_height; y++) {
                    for (int x = 0; x < qr_image_width; x++) {
                        int grayValue = (matrixCO.get(x, y) ? 0 : 1) & 0xff;
                        imageCO.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                    }
                }

                ByteArrayOutputStream BAOSCO = new ByteArrayOutputStream();
                ImageIO.write(imageCO, "png", BAOSCO);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
                InputStream ISImageCO = new ByteArrayInputStream(BAOSCO.toByteArray());
                //setProgress(60);

                /*7*/
                JTAOutput.append("Convirtiendo codigo QR a imagen\n");            
                //BitMatrix matrix = writer.encode(certificado, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
                BitMatrix matrix = writer.encode(cadenaOriginalFirmaBase64, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);


                // Create buffered image to draw to
                BufferedImage image = new BufferedImage(qr_image_width,qr_image_height, BufferedImage.TYPE_INT_RGB);

                // Iterate through the matrix and draw the pixels to the image
                for (int y = 0; y < qr_image_height; y++) {
                    for (int x = 0; x < qr_image_width; x++) {
                        int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                        image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                    }
                }

                ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
                ImageIO.write(image, "png", BAOS);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
                InputStream ISImage = new ByteArrayInputStream(BAOS.toByteArray());
                //setProgress(70);

                //********************************* QR *******************************  
                JTAOutput.append("Modificando oficio para agregar cadena de firma y codigo QR\n");
                /*8*/
                //MODIFICAR DOCUMENTO
                is = new FileInputStream(File_Documento);
                XWPFDocument docx2 = new XWPFDocument(is);

                XWPFParagraph paragraph2 = docx2.createParagraph();
                paragraph2.setPageBreak(true); 

                // AGREGAR Al CONTENIDO
                //1.- CADENA ORIGINAL PARSEADA DE FIRMA DIGITAL
                //2.- CADENA EN BASE64 de FIRMA DIGITAL
                //3.- AGREGAR CODIGO QR DE FIRMA DIGITAL CADENA ORIGINAL
                XWPFRun run2 = paragraph2.createRun(); 
                run2.setFontFamily("Arial");
                run2.setFontSize(8);
                run2.addBreak();
                run2.setText("FIRMA DIGITAL OFICIO NUM:"+valor);
                run2.addBreak();
                run2.setText("----------------------------------------------------------------------------------------");
                run2.addBreak();
                run2.setText(cadenaOriginalFirma);
                run2.addBreak();
                run2.setText("----------------------------------------------------------------------------------------");                                
                run2.addBreak();
                run2.addPicture(ISImageCO, XWPFDocument.PICTURE_TYPE_JPEG,"1", Units.toEMU(qr_image_width), Units.toEMU(qr_image_height));

                /*create table
                XWPFTable table = docx2.createTable();

                XWPFTableRow tableRowTwo = table.createRow();
                tableRowTwo.getCell(0).setText(cadenaOriginalFirma);
                tableRowTwo.getCell(1).setText("col two, row two");*/

                //GUARDAR DOCUMENTO
                out = new FileOutputStream(new File("./DocsArchivo/OFICIO Num "+valor+" FIRMADO "+File_Documento.getName()));
                docx2.write(out);
                is.close();
                out.close();
                //setProgress(80);

                JTAOutput.append("Convirtiendo Oficio temporal de docx a PDF\n");
                /*9*/
                Process process = new ProcessBuilder(
                            "./config/OfficeToPDF.exe",
                            "./DocsArchivo/OFICIO Num "+valor+" FIRMADO "+File_Documento.getName(),
                            "./DocsArchivo/OFICIO Num "+valor+" FIRMADO "+File_Documento.getName()+".PDF").start();
                process.waitFor();
                //setProgress(90);

                JTAOutput.append("Eliminando docx temporal\n");
                /*10*/
                //BORRAR ARCHIVO TEMPORAL
                File archivo = new File("./DocsArchivo/OFICIO Num "+valor+" FIRMADO "+File_Documento.getName());//+".docx");
                archivo.delete();
                
                
                //CONVERTIR A PDF
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriterException ex) {
                Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(JIFProcesoFirmadoDigital.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_JBModificarFirmarDocumento4ArchivoActionPerformed

    private void JBBuscarDocumentoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarDocumentoArchivoActionPerformed
        File_Documento = JFileChoserBuscar(filtro1_docx);
        
        if(File_Documento != null)            
            JCBDocCargadoArchivo.setSelected(true);                
        else
            JCBDocCargadoArchivo.setSelected(false);
    }//GEN-LAST:event_JBBuscarDocumentoArchivoActionPerformed
    
    private void firmarDocumento(File documento){
        try{
            JTAOutput.setText("");
            JTAOutput.append("Inicializando firma digital usando la llave privada\n");
            Signature dsa = Signature.getInstance("SHA256withDSA", "SUN");
            dsa.initSign(llavePrivada);

            JTAOutput.append("Creando buffer con el contenido del documento\n");
            BufferedInputStream bufin = new BufferedInputStream(new FileInputStream(documento));
            byte buffer[] = new byte[bufin.available()];
            int len = bufin.read(buffer);
            bufin.close();

            JTAOutput.append("Creando firma digital con el contenido del documento\n");            
            dsa.update(buffer);
            firmaDigital = dsa.sign();

            JTAOutput.append("Generando cadena de la firma digital\n");            
            int pos = 0;
            cadenaOriginalFirma = "||";
            while(pos < firmaDigital.length){
                //System.out.print(realSig[pos]);
                cadenaOriginalFirma += Byte.toString(firmaDigital[pos]);
                cadenaOriginalFirma +="|";
                pos++;
            }
            cadenaOriginalFirma += "|";

            JTAOutput.append("Guardando firma digital en archivo\n");
            FileOutputStream sigfos = new FileOutputStream(documento.getPath().replaceAll(".docx","")+".Firma_Digital");
            
            /* save the signature in a file */
            //System.out.println("File_Documento.getName():"+File_Documento.getName());
            //FileOutputStream sigfos = new FileOutputStream("./Docs/"+File_Documento.getName().replaceAll(".docx","")+".Firma_Digital");
            //FileOutputStream sigfos = new FileOutputStream("./Docs/"+nombreDocumento.replaceAll(".docx","")+".FirmaDigital");
            sigfos.write(firmaDigital);
            sigfos.close();

            JTAOutput.append("Convirtiendo cadena de la firma digital a base64\n");
            cadenaOriginalFirmaBase64 = new String(Base64.getEncoder().encode(cadenaOriginalFirma.getBytes()), "UTF-8");            
            
            JTAOutput.append("Se creo las firma digital del archivo "+File_Documento.getName()+"\n");
            JTAOutput.append("******************************************************************************\n");
        } catch (FileNotFoundException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CREAR FIRMA DIGITAL DEL DOCUMENTO");
        } catch (NoSuchAlgorithmException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CREAR FIRMA DIGITAL DEL DOCUMENTO");
        } catch (NoSuchProviderException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CREAR FIRMA DIGITAL DEL DOCUMENTO");
        } catch (InvalidKeyException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CREAR FIRMA DIGITAL DEL DOCUMENTO");
        } catch (IOException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CREAR FIRMA DIGITAL DEL DOCUMENTO");
        } catch (SignatureException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CREAR FIRMA DIGITAL DEL DOCUMENTO");
        }
    }//fin firmarDocumento()
    
    private PrivateKey cargarLlavePrivada(File path){
        PrivateKey llavePrivada = null;
        
        try {         
            FileInputStream keyfis = new FileInputStream(path);
            byte[] encKey = new byte[keyfis.available()];
            keyfis.read(encKey);
            keyfis.close();

            PKCS8EncodedKeySpec pubKeySpec = new PKCS8EncodedKeySpec(encKey);
            KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
            llavePrivada = keyFactory.generatePrivate(pubKeySpec);
            
        } catch (FileNotFoundException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
            JCBLlavePrivadaCargadaEmisor.setSelected(false);
            llavePrivada = null;
        } catch (IOException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
            JCBLlavePrivadaCargadaEmisor.setSelected(false);
            llavePrivada = null;
        } catch (NoSuchAlgorithmException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
            JCBLlavePrivadaCargadaEmisor.setSelected(false);
            llavePrivada = null;
        } catch (NoSuchProviderException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
            JCBLlavePrivadaCargadaEmisor.setSelected(false);
            llavePrivada = null;
        } catch (InvalidKeySpecException ex) {
            MensajeError(ex.getMessage(),"ERROR AL CARGAR LLAVE PRIVADA");
            JCBLlavePrivadaCargadaEmisor.setSelected(false);
            llavePrivada = null;
        }
        
        return llavePrivada;
    }
    
    private PublicKey cargarLlavePublica(File path){
        PublicKey llavePublica = null;        
        try {
            FileInputStream keyfis = new FileInputStream(path);
            byte[] encKey = new byte[keyfis.available()];
            keyfis.read(encKey);
            keyfis.close();

            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
            KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
            llavePublica = keyFactory.generatePublic(pubKeySpec);

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
        
        return llavePublica;
    }

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
    private javax.swing.JButton JBBuscarDocumento2FirmarEmisor;
    private javax.swing.JButton JBBuscarDocumentoArchivo;
    private javax.swing.JButton JBBuscarDocumentoEmisor;
    private javax.swing.JButton JBBuscarFirmaDigitalEmisor;
    private javax.swing.JButton JBBuscarLlavePrivadaEmisor;
    private javax.swing.JButton JBBuscarLlavePublicaEmisor;
    private javax.swing.JButton JBBuscarLlaveprivadaArchivo;
    private javax.swing.JButton JBFirmarDocumentoConLlavePrivada;
    private javax.swing.JButton JBGenerarLlaves;
    private javax.swing.JButton JBModificarFirmarDocumento4Archivo;
    private javax.swing.JButton JBVerificarFirmaDigital;
    private javax.swing.JCheckBox JCBDocCargadoArchivo;
    private javax.swing.JCheckBox JCBDocumentoCargado;
    private javax.swing.JCheckBox JCBDocumentoEmisorCargado;
    private javax.swing.JCheckBox JCBFirmaDigitalCargadaArchivo;
    private javax.swing.JCheckBox JCBLlavePrivadaCargadaArchivo;
    private javax.swing.JCheckBox JCBLlavePrivadaCargadaEmisor;
    private javax.swing.JCheckBox JCBLlavePublicaCargadaArchivo;
    private com.toedter.calendar.JDateChooser JDCFecha;
    private javax.swing.JPanel JPArchivo;
    private javax.swing.JPanel JPEmisor;
    private javax.swing.JPanel JPGenerarLlaves;
    private javax.swing.JTextArea JTAOutput;
    private javax.swing.JTextField JTFFolio;
    private javax.swing.JTextField JTFNombreLlaves;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
