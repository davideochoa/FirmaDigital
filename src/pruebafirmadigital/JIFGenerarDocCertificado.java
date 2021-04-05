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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class JIFGenerarDocCertificado extends JInternalFrame implements PropertyChangeListener{
    String cadenaOriginal = "||";
    byte[] buffer;
    PrivateKey privateKey;
    public JIFGenerarDocCertificado() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBLlavePrivada = new javax.swing.JButton();
        JLLlavePrivada = new javax.swing.JLabel();
        JBArchivo = new javax.swing.JButton();
        JLArchivo = new javax.swing.JLabel();
        JLArchivoCertificado = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setClosable(true);
        setTitle("GENERAR DOC CERTIFICADO");

        JBLlavePrivada.setText("LLAVE PRIVADA");
        JBLlavePrivada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBLlavePrivadaActionPerformed(evt);
            }
        });

        JLLlavePrivada.setText("LLAVE PRIVADA:");

        JBArchivo.setText("ARCHIVO");
        JBArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBArchivoActionPerformed(evt);
            }
        });

        JLArchivo.setText("ARCHIVO:");

        JLArchivoCertificado.setText("ARCHIVO CON CERTIFICADO:");

        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLArchivoCertificado)
                            .addComponent(JLArchivo)
                            .addComponent(JBArchivo)
                            .addComponent(JLLlavePrivada)
                            .addComponent(JBLlavePrivada)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(374, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(JBLlavePrivada)
                .addGap(18, 18, 18)
                .addComponent(JLLlavePrivada)
                .addGap(45, 45, 45)
                .addComponent(JBArchivo)
                .addGap(18, 18, 18)
                .addComponent(JLArchivo)
                .addGap(52, 52, 52)
                .addComponent(JLArchivoCertificado)
                .addGap(28, 28, 28)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBLlavePrivadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBLlavePrivadaActionPerformed
        JFileChooser Llave = new JFileChooser();
        
        Llave.setFileSelectionMode( JFileChooser.FILES_ONLY ); 
        int seleccion = Llave.showOpenDialog( this );

        if( seleccion == JFileChooser.APPROVE_OPTION ){
            FileInputStream keyfis = null;
            try {                
                JLLlavePrivada.setText("LLAVE PRIVADA: "+Llave.getSelectedFile().getAbsoluteFile());
                keyfis = new FileInputStream(Llave.getSelectedFile().getAbsoluteFile());
                byte[] encKey = new byte[keyfis.available()];
                keyfis.read(encKey);
                keyfis.close();
                PKCS8EncodedKeySpec pubKeySpec = new PKCS8EncodedKeySpec(encKey);
                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
                privateKey = keyFactory.generatePrivate(pubKeySpec);
                keyfis.close();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "ERROR AL CARGAR LLAVE PRIVADA",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
                
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "ERROR AL CARGAR LLAVE PRIVADA",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "ERROR AL CARGAR LLAVE PRIVADA",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchProviderException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "ERROR AL CARGAR LLAVE PRIVADA",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "ERROR AL CARGAR LLAVE PRIVADA",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_JBLlavePrivadaActionPerformed

    private void JBArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBArchivoActionPerformed
        JFileChooser Archivo = new JFileChooser();
        
        Archivo.setFileSelectionMode( JFileChooser.FILES_ONLY ); 
        int seleccion = Archivo.showOpenDialog( this );

        if( seleccion == JFileChooser.APPROVE_OPTION ){
            try {
                JLArchivo.setText("ARCHIVO: "+Archivo.getSelectedFile().getAbsoluteFile());
                
                File origen = new File(Archivo.getSelectedFile().getAbsoluteFile()+"");
                File destino = new File("./Docs/"+Archivo.getSelectedFile().getName());
                try {
                    InputStream in = new FileInputStream(origen);
                    OutputStream out = new FileOutputStream(destino);

                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                    }

                    in.close();
                    out.close();
                } catch (IOException ioe){
                        ioe.printStackTrace();
                }
                
                //************* CREAR CERTIFICADO ***************************************
                Signature dsa = Signature.getInstance("SHA256withDSA", "SUN");
                dsa.initSign(privateKey);
                
                FileInputStream fis = new FileInputStream("./Docs/"+Archivo.getSelectedFile().getName());  
                BufferedInputStream bufin = new BufferedInputStream(fis);
                
                buffer = new byte[bufin.available()];
                int len = bufin.read(buffer);
                dsa.update(buffer);
                
                bufin.close();
                
                /*Task task = new Task();
                task.addPropertyChangeListener(this);
                task.execute();*/
                
                byte[] firma = dsa.sign();
                
                int pos = 0;                
                String cadenaOriginalFirma = "||";                
                while(pos < firma.length){
                    //System.out.print(realSig[pos]);
                    cadenaOriginalFirma += Byte.toString(firma[pos]);
                    cadenaOriginalFirma +="|";
                    pos++;
                }
                cadenaOriginalFirma += "|";
                //System.out.println("cadena Certificadol:"+cadenaCertificado);
                
                String cadenaOriginalBase64 = new String(Base64.getEncoder().encode(buffer), "UTF-8");
                String firmaBase64 = new String(Base64.getEncoder().encode(firma), "UTF-8");
                
                /* save the signature in a file */
                //FileOutputStream sigfos = new FileOutputStream("./claves/"+Archivo.getSelectedFile().getName()+"_CERTIFICADO");
                //sigfos.write(realSig);
                //sigfos.close();
                
                //*********************** QR *******************
                // Encode URL in QR format
                Writer writer = new QRCodeWriter();
                
                int qr_image_width = 150;
                int qr_image_height = 150;
                String IMAGE_FORMAT = "png";
                
                Map hintMap = new HashMap();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
                hintMap.put(EncodeHintType.MARGIN, -1);
                
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
                
                //BitMatrix matrix = writer.encode(certificado, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
                BitMatrix matrix = writer.encode(firmaBase64, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
                

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
                
                //*****************   OPEN   ********************/
                InputStream is = new FileInputStream(new File("./claves/Certificado.docx"));
                //InputStream is = new FileInputStream(new File(Archivo.getSelectedFile().getAbsoluteFile()+""));
                XWPFDocument docx = new XWPFDocument(is);
                
                /*
                for (XWPFParagraph paragraph: docx.getParagraphs()){
                    for (XWPFRun run : paragraph.getRuns()) {
                        if (run.toString().contains("${NUMEROOFICIO}")) {
                            run.setText(run.toString().replace("${NUMEROOFICIO}", "myValue"), 0);
                        }
                    }
                }
                */
                //******************* WRITE *********************
                OutputStream out = new FileOutputStream(new File("./Docs/CERTIFICADO"+Archivo.getSelectedFile().getName()));
                XWPFParagraph paragraph = docx.createParagraph();
                //paragraph.setPageBreak(true); 
                
                XWPFRun run = paragraph.createRun(); 
                run.setFontFamily("Arial");
                run.setFontSize(8);
                run.setText("CADENA ORIGINAL DIGITAL OFICIO Num: xxxxx/xx");
                run.addBreak();
                run.setText("----------------------------------------------------------------------------------------");
                run.addBreak();
                run.setText(cadenaOriginalBase64);
                run.addBreak();
                run.setText("----------------------------------------------------------------------------------------");                
                run.addBreak();
                run.setText("CERTIFICADO DE FIRMA DIGITAL OFICIO Num: xxxxx/xx");
                run.addBreak();
                run.setText("----------------------------------------------------------------------------------------");
                run.addBreak();
                run.setText(firmaBase64);
                run.addBreak();
                run.setText("----------------------------------------------------------------------------------------");
                run.addBreak();
                run.addPicture(ISImageCO, XWPFDocument.PICTURE_TYPE_JPEG,"1", Units.toEMU(qr_image_width), Units.toEMU(qr_image_height));
                
                docx.write(out);
                
                out.close();
                is.close();
                
                /******************    DOCX to PDF    **********
                FileOutputStream outPDF = new FileOutputStream(new File("./Docs/Certificado Oficio Numero xxxx - xx "+Archivo.getSelectedFile().getName()+".PDF"));
                PdfOptions options = PdfOptions.create().fontEncoding( "windows-1252" );                
                PdfConverter.getInstance().convert( docx, outPDF, options );
                outPDF.close(); */
                /*
                Document doc = new Document("./Docs/CERTIFICADO"+Archivo.getSelectedFile().getName());
                doc.save("./Docs/Certificado Oficio Numero xxxx - xx "+Archivo.getSelectedFile().getName()+".PDF");
                */
                
                Process process;
                process = new ProcessBuilder(
                        "./claves/OfficeToPDF.exe",
                        "./Docs/CERTIFICADO"+Archivo.getSelectedFile().getName(),
                        "./Docs/Certificado Oficio Numero xxxx - xx "+Archivo.getSelectedFile().getName()+".PDF").start();
                process.waitFor();

                System.out.println("Result of Office processing: " + process.exitValue());              
                
                
                JOptionPane.showMessageDialog(this,"Se creo el certificado: "+Archivo.getSelectedFile().getName()+"_CERTIFICADO");
            } 
            catch (NoSuchAlgorithmException ex) {
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "NoSuchAlgorithmException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);                
            } 
            catch (NoSuchProviderException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "NoSuchProviderException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (InvalidKeyException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "InvalidKeyException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "FileNotFoundException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "IOException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (SignatureException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "SignatureException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(JIFFirmarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriterException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "WriterException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
            } catch (InvalidFormatException ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "InvalidFormatException",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                                                ex.getMessage(),
                                                "Exception",                                                                                                
                                                JOptionPane.ERROR_MESSAGE);
            }        
    }//GEN-LAST:event_JBArchivoActionPerformed
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("evt.getPropertyName():"+evt.getPropertyName());
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            jProgressBar1.setValue(progress);
        } 
    }
    
    public class Project {

	private final String name;

	public Project(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
    
    class Task extends SwingWorker{
        @Override
        protected Object doInBackground() throws Exception {
            int pos = 0;           
            setProgress(0);
            cadenaOriginal = "||";                
            while(pos < buffer.length){
                //System.out.print(realSig[pos]);
                cadenaOriginal += Byte.toString(buffer[pos]);
                cadenaOriginal +="|";                
                pos++;
                int progress = (pos*100)/buffer.length;
                //System.out.println("pos:"+pos+" buffer.length:"+buffer.length+" progress:"+progress);
                setProgress(progress);
            }
            cadenaOriginal += "|";
            return null;            
        }
    }  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBArchivo;
    private javax.swing.JButton JBLlavePrivada;
    private javax.swing.JLabel JLArchivo;
    private javax.swing.JLabel JLArchivoCertificado;
    private javax.swing.JLabel JLLlavePrivada;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
