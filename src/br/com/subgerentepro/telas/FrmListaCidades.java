package br.com.subgerentepro.telas;

import br.com.subgerentepro.bo.CidadeBO;
import br.com.subgerentepro.dao.CidadeDAO;
import br.com.subgerentepro.dto.CidadeDTO;
import br.com.subgerentepro.exceptions.PersistenciaException;
import br.com.subgerentepro.jbdc.ConexaoUtil;
import static br.com.subgerentepro.telas.TelaEmpresas.btnBairroBusca;
import static br.com.subgerentepro.telas.TelaEmpresas.txtCidade;
import static br.com.subgerentepro.telas.TelaEmpresas.txtCodCidade;
import br.com.subgerentepro.metodosstatics.MetodoStaticosUtil;
import static br.com.subgerentepro.telas.TelaEmpresas.txtBairro;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DaTorres
 */
public class FrmListaCidades extends javax.swing.JInternalFrame {

    CidadeDTO cidadeDTO = new CidadeDTO();
    CidadeDAO cidadeDAO = new CidadeDAO();
    CidadeBO cidadeBO = new CidadeBO();

    private static FrmListaCidades instance = null;

    public static FrmListaCidades getInstance() {

        if (instance == null) {

            instance = new FrmListaCidades();

        }

        return instance;
    }

    public static boolean isOpen() {

        return instance != null;
    }

    public FrmListaCidades() {
        initComponents();
        txtBuscar.requestFocus();
        txtFiltroIDEstado.setText(TelaEmpresas.txtCodEstado.getText());
        txtFiltroIDEstado.setEnabled(false);

        ConexaoUtil conecta = new ConexaoUtil();
        boolean recebeConexao = conecta.getInstance().ConexaoVerificaEstado();
        if (recebeConexao == true) {

            try {

                int numeroLinhas = tabela.getRowCount();

                if (numeroLinhas > 0) {

                    while (tabela.getModel().getRowCount() > 0) {
                        ((DefaultTableModel) tabela.getModel()).removeRow(0);

                    }

                    CampoPesquisar();

                } else {
                    addRowJTable();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "" + "\n Camada GUI:\n"
                    + "Sem Conectividade: Verifica \n a Conexao entre a aplica????o e o Banco Hospedado "
                    + "", "Mensagem Informativa", 0, new ImageIcon(getClass().getResource("/br/com/subgerentepro/imagens/info.png")));

        }

    }

    private void desabilitarCampos() {
        txtBuscar.setEnabled(false);
    }

    private void desabilitarTodosBotoes() {
        btnPesquisar.setEnabled(false);
    }
private void aoReceberFocoBtnPesquisar(){
        Font fonteLlbInfoInicio = new Font("Tahoma", Font.BOLD, 22);//label informativo 
        lblLinhaInformativa.setText("");
        lblLinhaInformativa.setForeground(Color.BLUE);
        lblLinhaInformativa.setText("Pressione -->[ENTER]<-- Procurar -->[MYSQL]<--");
    
    }

private void aoReceberFocoTxtBuscar() {
        Font fonteLlbInfoInicio = new Font("Tahoma", Font.BOLD, 22);//label informativo 
        lblLinhaInformativa.setText("");
        lblLinhaInformativa.setForeground(Color.BLUE);
        lblLinhaInformativa.setText("Digite o Registro Procurado -->[ENTER]<--");
    }
    
    public void addRowJTable() {

        ConexaoUtil conecta = new ConexaoUtil();
        boolean recebeConexao = conecta.getInstance().ConexaoVerificaEstado();

        if (recebeConexao == true) {

            DefaultTableModel model = (DefaultTableModel) tabela.getModel();

            ArrayList<CidadeDTO> list;

            try {

                int filtro = Integer.parseInt(txtFiltroIDEstado.getText());
                String pesquisa = txtBuscar.getText();

                list = (ArrayList<CidadeDTO>) cidadeDAO.filtrarCidadesPesqRapida(pesquisa, filtro);

                Object rowData[] = new Object[3];//s??o 03 colunas 
                for (int i = 0; i < list.size(); i++) {
                    rowData[0] = list.get(i).getCodCidadeDto();
                    rowData[1] = list.get(i).getNomeCidadeDto();
                    rowData[2] = list.get(i).getSiglaEstadoRecuperarTable();
                    model.addRow(rowData);
                }

                tabela.setModel(model);

                /**
                 * Coluna ID posi????o[0] vetor
                 */
                tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(380);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(100);

            } catch (PersistenciaException ex) {
                JOptionPane.showMessageDialog(null, "Erro:M??todo addRowTable() FrmListaCidades\n"
                        + "Contactar Analista de Sistemas pelo Email: sisvenda2011@gmail.com\n"
                        + "Respons??vel pelo Projeto Tonis Alberto Torres Ferreira\n" + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(this, "" + "\n Camada GUI:\n"
                    + "Sem Conectividade: \n Entre ?? APLICA????O e o BANCO DE DADOS HOSPEDADO"
                    + "", "Mensagem Informativa", 0, new ImageIcon(getClass().getResource("/br/com/subgerentepro/imagens/info.png")));
            desabilitarCampos();
            desabilitarTodosBotoes();
        }

    }

    private void CampoPesquisar() {

        String pesquisar = MetodoStaticosUtil.removerAcentosCaixAlta(txtBuscar.getText());
        int filtroPesquisa = Integer.parseInt(txtFiltroIDEstado.getText());

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

        ArrayList<CidadeDTO> list;

        try {

            list = (ArrayList<CidadeDTO>) cidadeDAO.filtrarCidadesPesqRapida(pesquisar, filtroPesquisa);

            Object rowData[] = new Object[3];//s??o 04 colunas 
            for (int i = 0; i < list.size(); i++) {
                rowData[0] = list.get(i).getCodCidadeDto();
                rowData[1] = list.get(i).getNomeCidadeDto().toString();
                rowData[2] = list.get(i).getSiglaEstadoRecuperarTable().toString();
                model.addRow(rowData);
            }

            tabela.setModel(model);

            /**
             * Coluna ID posi????o[0] vetor
             */
            tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(380);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);

            personalizandoBarraInfoPesquisaTermino();

        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(null, "Erro:M??todo addRowTable() FormListaCidades \n"
                    + "Contactar Analista de Sistemas pelo Email: sisvenda2011@gmail.com\n"
                    + "Respons??vel pelo Projeto Tonis Alberto Torres Ferreira\n" + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelCabecalho = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        lblIDEstadoFiltro = new javax.swing.JLabel();
        txtFiltroIDEstado = new javax.swing.JTextField();
        lblLinhaInformativa = new javax.swing.JLabel();
        progressBarraPesquisa = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setClosable(true);

        painelCabecalho.setBackground(new java.awt.Color(255, 255, 255));
        painelCabecalho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.setBackground(new java.awt.Color(255, 255, 204));
        txtBuscar.setOpaque(false);
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        painelCabecalho.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botoes/forms/pesquisar.png"))); // NOI18N
        btnPesquisar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnPesquisarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnPesquisarFocusLost(evt);
            }
        });
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        btnPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPesquisarKeyPressed(evt);
            }
        });
        painelCabecalho.add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 32, 30));

        lblIDEstadoFiltro.setText("Filtro-->>");
        painelCabecalho.add(lblIDEstadoFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        txtFiltroIDEstado.setBackground(new java.awt.Color(255, 255, 153));
        txtFiltroIDEstado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFiltroIDEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFiltroIDEstado.setOpaque(false);
        painelCabecalho.add(txtFiltroIDEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 30, 30));

        lblLinhaInformativa.setText("Linha Informativa");
        painelCabecalho.add(lblLinhaInformativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, -1));
        painelCabecalho.add(progressBarraPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 50, -1));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "CIDADES", "UF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setMinWidth(80);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabela.getColumnModel().getColumn(0).setMaxWidth(80);
            tabela.getColumnModel().getColumn(1).setMinWidth(160);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(160);
            tabela.getColumnModel().getColumn(1).setMaxWidth(160);
            tabela.getColumnModel().getColumn(2).setMinWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelCabecalho, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased


    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int linha = tabela.getSelectedRow();

        txtCodCidade.setText(tabela.getValueAt(linha, 0).toString());
        txtCidade.setText(tabela.getValueAt(linha, 1).toString());
        btnBairroBusca.setEnabled(true);
        btnBairroBusca.requestFocus();
        txtBairro.setEnabled(true);
        this.dispose();

    }//GEN-LAST:event_tabelaMouseClicked

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        txtBuscar.setBackground(Color.YELLOW);
aoReceberFocoTxtBuscar();

    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        txtBuscar.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        // 1?? IREMOS SETAR TODOS OS VALORES DOS CAMPOS DO FORMUL??RIO
        //  JOptionPane.showMessageDialog(this, "flag: " + flag);
        ConexaoUtil conecta = new ConexaoUtil();
        boolean recebeConexao = conecta.getInstance().ConexaoVerificaEstado();
        if (recebeConexao == true) {

            try {

                int numeroLinhas = tabela.getRowCount();

                if (numeroLinhas > 0) {

                    while (tabela.getModel().getRowCount() > 0) {
                        ((DefaultTableModel) tabela.getModel()).removeRow(0);

                    }

                    CampoPesquisar();

                } else {
                    addRowJTable();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "" + "\n Camada GUI:\n"
                    + "Sem Conectividade: Verifica \n a Conexao entre a aplica????o e o Banco Hospedado "
                    + "", "Mensagem Informativa", 0, new ImageIcon(getClass().getResource("/br/com/subgerentepro/imagens/info.png")));

        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {

            txtBuscar.setBackground(Color.YELLOW);
            //----------------------------------//
            //informe sobre inicio da pesquisa //
            //--------------------------------//
            personalizandoBarraInfoPesquisaInicio();

            btnPesquisar.requestFocus();
            btnPesquisar.setBackground(Color.YELLOW);
            txtBuscar.setBackground(Color.WHITE);
        }


    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnPesquisarFocusLost
        btnPesquisar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnPesquisarFocusLost

    private void btnPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {

            txtBuscar.requestFocus();
            // 1?? IREMOS SETAR TODOS OS VALORES DOS CAMPOS DO FORMUL??RIO
            //  JOptionPane.showMessageDialog(this, "flag: " + flag);
            ConexaoUtil conecta = new ConexaoUtil();
            boolean recebeConexao = conecta.getInstance().ConexaoVerificaEstado();
            if (recebeConexao == true) {

                try {

                    int numeroLinhas = tabela.getRowCount();

                    if (numeroLinhas > 0) {

                        while (tabela.getModel().getRowCount() > 0) {
                            ((DefaultTableModel) tabela.getModel()).removeRow(0);

                        }

                        CampoPesquisar();

                    } else {
                        addRowJTable();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "" + "\n Camada GUI:\n"
                        + "Sem Conectividade: Verifica \n a Conexao entre a aplica????o e o Banco Hospedado "
                        + "", "Mensagem Informativa", 0, new ImageIcon(getClass().getResource("/br/com/subgerentepro/imagens/info.png")));

            }
        }
    }//GEN-LAST:event_btnPesquisarKeyPressed

    private void btnPesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnPesquisarFocusGained
aoReceberFocoBtnPesquisar();
    }//GEN-LAST:event_btnPesquisarFocusGained

    private void personalizandoBarraInfoPesquisaInicio() {
        Font fonteLlbInfoInicio = new Font("Tahoma", Font.BOLD, 22);//label informativo 
        lblLinhaInformativa.setForeground(Color.BLUE);
        lblLinhaInformativa.setText("Pesquisar Presione ENTER");
    }

    private void personalizandoBarraInfoPesquisaTermino() {
        Font fonteLlbInfoInicio = new Font("Tahoma", Font.BOLD, 22);//label informativo 
        lblLinhaInformativa.setForeground(Color.ORANGE);
        lblLinhaInformativa.setText("Pesquisa Terminada.");
        txtBuscar.requestFocus();
        txtBuscar.setBackground(Color.CYAN);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIDEstadoFiltro;
    private javax.swing.JLabel lblLinhaInformativa;
    private javax.swing.JPanel painelCabecalho;
    private javax.swing.JProgressBar progressBarraPesquisa;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFiltroIDEstado;
    // End of variables declaration//GEN-END:variables

}
