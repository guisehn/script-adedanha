/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.guisehn.telas;

import com.guisehn.db.TemasProvider;
import com.guisehn.teclado.Captura;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 *
 * @author guilhermesehn
 */
public final class TelaPrincipal extends javax.swing.JFrame {

    private final Captura captura;
    private final DefaultComboBoxModel temasModel;
    private final TelaListagemTemas telaListagemTemas;
    private final TelaListagemPalavras telaListagemPalavras;
    private boolean ligado = false;
    
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            JOptionPane.showMessageDialog(null, "Erro na captura de teclas:" + ex.getMessage());
            System.exit(0);
        }
        
        captura = new Captura();
        
        telaListagemTemas = new TelaListagemTemas();
        telaListagemTemas.setTelaPrincipal(this);
        
        telaListagemPalavras = new TelaListagemPalavras();
        
        temasModel = new DefaultComboBoxModel();
        ddlTemaSelecionado.setModel(temasModel);
        
        atualizarTemas();
    }
    
    public void atualizarTemas() {
        temasModel.removeAllElements();
        
        List<String> temas = TemasProvider.buscarTemas();
        for (String tema : temas) {
            temasModel.addElement(tema);
        }
        
        if (temas.size() > 0) {
            temasModel.setSelectedItem(temas.get(0));
            ddlTemaSelecionadoActionPerformed(null);            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        ddlTemaSelecionado = new javax.swing.JComboBox();
        lblTema = new javax.swing.JLabel();
        btnToggle = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuItemCadastroTemas = new javax.swing.JMenuItem();
        menuItemPalavras = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Script Adedanha do Liberalismo");

        ddlTemaSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddlTemaSelecionadoActionPerformed(evt);
            }
        });

        lblTema.setText("Tema:");

        btnToggle.setText("Desligado");
        btnToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleActionPerformed(evt);
            }
        });

        menuCadastro.setText("Cadastro");

        menuItemCadastroTemas.setText("Temas");
        menuItemCadastroTemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroTemasActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemCadastroTemas);

        menuItemPalavras.setText("Palavras");
        menuItemPalavras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPalavrasActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemPalavras);

        jMenuBar1.add(menuCadastro);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ddlTemaSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTema))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddlTemaSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnToggle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCadastroTemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroTemasActionPerformed
        telaListagemTemas.atualizar();
        telaListagemTemas.setVisible(true);
    }//GEN-LAST:event_menuItemCadastroTemasActionPerformed

    private void menuItemPalavrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPalavrasActionPerformed
        telaListagemPalavras.atualizarTemas();
        telaListagemPalavras.setVisible(true);
    }//GEN-LAST:event_menuItemPalavrasActionPerformed

    private void btnToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleActionPerformed
        ligado = !ligado;
        
        if (ligado) {
            btnToggle.setText("Ligado");
            GlobalScreen.getInstance().addNativeKeyListener(captura);
        } else {
            btnToggle.setText("Desligado");
            captura.desligar();
            GlobalScreen.getInstance().removeNativeKeyListener(captura);
        }
    }//GEN-LAST:event_btnToggleActionPerformed

    private void ddlTemaSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddlTemaSelecionadoActionPerformed
        String temaSelecionado = (String)ddlTemaSelecionado.getSelectedItem();
        if (temaSelecionado != null) {
            captura.setTema(temaSelecionado);
        }
    }//GEN-LAST:event_ddlTemaSelecionadoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnToggle;
    private javax.swing.JComboBox ddlTemaSelecionado;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblTema;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuItemCadastroTemas;
    private javax.swing.JMenuItem menuItemPalavras;
    // End of variables declaration//GEN-END:variables
}
