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
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCapabilities;
import javax.swing.JPopupMenu;
import org.yourorghere.connection.Connect;
import game.*;



/**
 *
 * @author keyalisth
 */
public class TelaBatalha extends javax.swing.JFrame {
    private  int sinal, x, y, indexVencedor;
    static Jogador[] jogadores = new Jogador[2];
    static Tabuleiro[] tabs = new Tabuleiro[2];
    private String log, placar;
    private boolean end = false;
    private int myIndex;
    private int oponentIndex;
    
    static {
        // When using a GLCanvas, we have to set the Popup-Menues to be HeavyWeight,
        // so they display properly on top of the GLCanvas
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    }
    
    private Animator animator1;
    private Animator animator2;
    
    /**
     * Creates new form TelaBatalha
     */
    public TelaBatalha(int myIndex, int oponentIndex) {
        initComponents();
        setTitle("Batalha Naval");
        this.myIndex=myIndex;
        this.oponentIndex = oponentIndex;
        try {
            jogadores = (Jogador[]) Connect.getInputStream().readObject();
            tabs = (Tabuleiro[]) Connect.getInputStream().readObject();
            sinal = (int) Connect.getInputStream().readInt();
        } catch (IOException ex) {
            Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
        }

                
        username1.setEditable(false);
        username2.setEditable(false);
        turntxt.setEditable(false);
        if(sinal==myIndex){
            turntxt.setText("Turno: "+jogadores[myIndex].getNome());
        }else{
            turntxt.setText("Turno: "+jogadores[oponentIndex].getNome());
        }
        canvasP1.addGLEventListener(new GLRenderer(jogadores[myIndex],false));
        canvasP2.addGLEventListener(new GLRenderer(jogadores[oponentIndex],true));
          
        username1.setText(jogadores[myIndex].getNome());
        username2.setText(jogadores[oponentIndex].getNome());
        
        animator1 = new Animator(canvasP1);
        animator2 = new Animator(canvasP2);
        
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {

                    public void run() {
                        animator1.stop();
                        animator2.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        animator1.start();
        animator2.start();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvasP1 = new javax.media.opengl.GLCanvas(createGLCapabilites());
        canvasP2 = new javax.media.opengl.GLCanvas(createGLCapabilites());
        turntxt = new javax.swing.JTextField();
        username1 = new javax.swing.JTextField();
        username2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        canvasP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvasP2MouseClicked(evt);
            }
        });

        turntxt.setText("TURNO:");
        turntxt.setEditable(false);
        turntxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turntxtActionPerformed(evt);
            }
        });

        username1.setText(" ");
        username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(username1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(turntxt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(username2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(canvasP1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(canvasP2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(turntxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(canvasP1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(canvasP2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void turntxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turntxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turntxtActionPerformed

    private void username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username1ActionPerformed

    private void canvasP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasP2MouseClicked
        Point p = evt.getPoint();
        int i = p.x/40;
        int j = p.y/40;
         int[] coord = new int[2];
         

        if(sinal==myIndex){
            try {
                coord[0]=i;
                coord[1]=j;
                
                
                
                if(jogadores[oponentIndex].getTab(i, j)=='B' ||jogadores[oponentIndex].getTab(i,j)=='~'){
                    System.out.println("pegando o Placar:");
                    try {
                        placar = (String)Connect.getInputStream().readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(placar);
                    Connect.getOutputStream().writeObject(coord);
                    try {
                        System.out.println("Obtendo Log da sua Jogada: ");
                        log = (String)Connect.getInputStream().readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(log); 
                    

                    jogadores[myIndex].atacar(jogadores[oponentIndex], i, j);
                    sinal = (int) Connect.getInputStream().readInt();
                    if(sinal==myIndex){
                        turntxt.setText("Turno: "+jogadores[myIndex].getNome());
                        System.out.println("Proximo a jogar �: " +jogadores[myIndex].getNome());
                    }else{
                        turntxt.setText("Turno: "+jogadores[oponentIndex].getNome());
                        System.out.println("Proximo a jogar �: " +jogadores[oponentIndex].getNome());
                    }
                }
                
            } catch (IOException ex) {
                Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if(!hasShips(jogadores[oponentIndex])){
            turntxt.setText("Vit�ria de " + jogadores[myIndex].getNome());
            Connect.closeConnection();
        }

    }//GEN-LAST:event_canvasP2MouseClicked

    private boolean hasShips(Jogador j){
        for(int k=0;k<10;k++){
            for(int l=0;l<10;l++){
                if(j.getTab(k, l)=='B'){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if(!hasShips(jogadores[myIndex])){
            turntxt.setText("Vit�ria de " + jogadores[oponentIndex].getNome());
            Connect.closeConnection();
        }else{
            if(sinal==myIndex){
                turntxt.setText("Turno: "+jogadores[myIndex].getNome());
            }else{
                turntxt.setText("Turno: "+jogadores[oponentIndex].getNome());
            }
            if(sinal == oponentIndex){
                try {
                    try {
                        System.out.println("pegando o Placar:");

                        placar = (String)Connect.getInputStream().readObject();
                        System.out.println(placar);

                        System.out.println("pegando log da jogada do oponente:");
                        log = (String)Connect.getInputStream().readObject();
                        System.out.println(log);
                    } catch (IOException ex) {
                        Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(log);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
                }
                String delims = "[ \".,?!]+";
                String[] tokens = log.split(delims);        
                int x = tokens[0].charAt(0)-48;
                int y = tokens[1].charAt(0)-48;
                System.out.println("oponente fez ataque em "+x+","+y);
                jogadores[oponentIndex].atacar(jogadores[myIndex], x, y);
                try {
                    sinal = (int) Connect.getInputStream().readInt();
                    if(sinal==myIndex){
                        turntxt.setText("Turno: "+jogadores[myIndex].getNome());
                        System.out.println("Proximo a jogar �: " +jogadores[myIndex].getNome());
                    }else{
                        turntxt.setText("Turno: "+jogadores[oponentIndex].getNome());
                        System.out.println("Proximo a jogar �: " +jogadores[oponentIndex].getNome());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TelaBatalha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(!hasShips(jogadores[myIndex])){
                turntxt.setText("Vit�ria de " + jogadores[oponentIndex].getNome());
                Connect.closeConnection();
            }
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private GLCapabilities createGLCapabilites() {
        
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setHardwareAccelerated(true);

        // try to enable 2x anti aliasing - should be supported on most hardware
        capabilities.setNumSamples(2);
        capabilities.setSampleBuffers(true);
        
        return capabilities;
    }        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.media.opengl.GLCanvas canvasP1;
    private javax.media.opengl.GLCanvas canvasP2;
    private javax.swing.JTextField turntxt;
    private javax.swing.JTextField username1;
    private javax.swing.JTextField username2;
    // End of variables declaration//GEN-END:variables
}
