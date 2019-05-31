/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCapabilities;
import javax.swing.JPopupMenu;
import org.yourorghere.connection.Connect;
import game.*;
import javax.swing.JFrame;



/**
 *
 * @author keyalisth
 */
public class TelaMapa extends JFrame {
    private TelaBatalha te;
    private Jogador  jogador;
    private ObjectOutputStream ostream;
    
    
    static {
        // When using a GLCanvas, we have to set the Popup-Menues to be HeavyWeight,
        // so they display properly on top of the GLCanvas
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    }
    
    private Animator animator;

    
    
    /**
     * Creates new form TelaMapa
     */
    public TelaMapa(Jogador j ) {
        initComponents();
        
        jogador = j;
        System.out.println("tentando criar essa merda");
        setTitle("Batalha Naval");
        labelDesc.setText("Escolha "+Jogador.MAX_BARCOS+" Posi��es para seus barcos");
        canvasEscolha.addGLEventListener(new GLRenderer(jogador,false));
        animator = new Animator(canvasEscolha);
                System.out.println("kct");
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        animator.start();
                System.out.println("WHYYYY");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvasEscolha = new javax.media.opengl.GLCanvas(createGLCapabilites());
        jButton1 = new javax.swing.JButton();
        labelDesc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        canvasEscolha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvasEscolhaMouseClicked(evt);
            }
        });

        jButton1.setText("PRONTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        labelDesc.setText("   ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(canvasEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDesc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(canvasEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jogador.getNumBarcos()>=Jogador.MAX_BARCOS){
            ostream = Connect.getOutputStream();
            try {
                ostream.writeObject(jogador); // escreve o objeto na stream
                ostream.flush();
                
                int myIndex = Connect.getInputStream().readInt();
                int oponentIndex;
                if (myIndex == 0) oponentIndex = 1;
                else oponentIndex = 0;               
                
                te = new TelaBatalha(myIndex, oponentIndex);
                te.setLocationRelativeTo(null);
                te.setVisible(true);          
                this.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(TelaMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void canvasEscolhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasEscolhaMouseClicked
        Point p = evt.getPoint();
        int i = p.x/40;
        int j = p.y/40;
        if(jogador.getNumBarcos()<Jogador.MAX_BARCOS){
            jogador.posicionarBarco(i, j);
            jogador.printTab();
        }

        
    }//GEN-LAST:event_canvasEscolhaMouseClicked

    
    private GLCapabilities createGLCapabilites() {
        
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setHardwareAccelerated(true);

        // try to enable 2x anti aliasing - should be supported on most hardware
        capabilities.setNumSamples(2);
        capabilities.setSampleBuffers(true);
        
        return capabilities;
    }    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.media.opengl.GLCanvas canvasEscolha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel labelDesc;
    // End of variables declaration//GEN-END:variables
}
