/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.Control;

import br.edu.ifro.Model.Historico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nath
 */
public class calcController implements Initializable {
    
    private Label label;
    @FXML
    private Label lblResultado;
    @FXML
    private Label lblResult;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btnMult;
    @FXML
    private TextField txtNumero1;
    @FXML
    private Button btnSub;
    @FXML
    private Button btnSoma;
    @FXML
    private TextField txtNumero2;
    @FXML
    private Label lblNumero2;
    @FXML
    private Label lblNumero1;
    @FXML
    private Pane btnAbreH;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void limpar(ActionEvent event) {
        txtNumero1.setText("");
        txtNumero2.setText("");
        lblResult.setText("");

    }

    @FXML
    private void div(ActionEvent event) {
        Double num1 = Double.parseDouble(txtNumero1.getText());
        Double num2 = Double.parseDouble(txtNumero2.getText());
        Double resultado = num1  / num2;

     lblResult.setText(resultado.toString());
    }

    @FXML
    private void mult(ActionEvent event) {
        Double num1 = Double.parseDouble(txtNumero1.getText());
        Double num2 = Double.parseDouble(txtNumero2.getText());
        Double resultado = num1  * num2;

     lblResult.setText(resultado.toString());
    }

    @FXML
    private void subtracao(ActionEvent event) {
        Double num1 = Double.parseDouble(txtNumero1.getText());
        Double num2 = Double.parseDouble(txtNumero2.getText());
        Double resultado = num1  - num2;

     lblResult.setText(resultado.toString());
    }

    @FXML
    private void soma(ActionEvent event) {
        Double num1 = Double.parseDouble(txtNumero1.getText());
        Double num2 = Double.parseDouble(txtNumero2.getText());
        Double resultado = num1  + num2;

     lblResult.setText(resultado.toString());
    }
    
    private void Salvar(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();

        Historico hist = new Historico();
        hist.setPrimeiroValor(txtNumero1.getText());   
        hist.setSegundoValor(txtNumero2.getText());
        hist.setOperador(btnDiv.getText());
        hist.setOperador(btnMult.getText());
        hist.setOperador(btnSoma.getText());
        hist.setOperador(btnSub.getText());
        hist.setResultado(lblResult.getText());
     

        em.getTransaction().begin(); 
        em.persist(hist);
        em.getTransaction().commit();
        
    }
       @FXML
    private void abrirHist(MouseEvent event) {
                try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/View/ListarCalculos.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),600,430);
            Stage stage = new Stage();
            stage.setTitle("Histórico");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();            
        }
        catch(IOException e){

}

}
    }
