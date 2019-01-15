
package Internet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Internet extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        TextField tfime = new TextField();
        tfime.setStyle("-fx-background-color:white;-fx-font-weight:bold;-fx-text-fill:blue; -fx-border-color:lightblue");
        TextField tfprezime = new TextField();
        tfprezime.setStyle("-fx-background-color:white;-fx-font-weight:bold;-fx-text-fill:blue; -fx-border-color:lightblue");
        TextField tfadresa = new TextField();
        tfadresa.setStyle("-fx-background-color:white;-fx-font-weight:bold;-fx-text-fill:blue; -fx-border-color:lightblue");
        ChoiceBox<String> cbbrzina = new ChoiceBox<>();
        cbbrzina.getItems().addAll("2 Mbit", "5 Mbit", "10 Mbit", "20 Mbit", "50 Mbit", "100 Mbit");
        cbbrzina.setStyle("-fx-border-color:yellow");
        ChoiceBox<String> cbprotok = new ChoiceBox<>();
        cbprotok.getItems().addAll("1 GB", "5 GB", "10 GB", "100 GB", "flat");
        cbprotok.setStyle("-fx-border-color:yellow");
        ChoiceBox<String> cbugovor = new ChoiceBox<>();
        cbugovor.getItems().addAll("1 godina", "2 godine");
        cbugovor.setStyle("-fx-border-color:yellow");
        Button b1 = new Button("Dodaj");
        b1.setStyle("-fx-text-fill:blue; -fx-font-weight:bold; -fx-border-color:blue");
        Button b2 = new Button("Obrisi");
        b2.setStyle("-fx-text-fill:blue; -fx-font-weight:bold; -fx-border-color:blue");
        Button b3 = new Button("Pregled");
        b3.setStyle("-fx-text-fill:blue; -fx-font-weight:bold; -fx-border-color:blue");
        TextArea ta = new TextArea();
        ta.setEditable(false);

        b1.setOnAction((ActionEvent event) -> {
            Klijent k = new Klijent();
            k.setIme(tfime.getText());
            k.setPrezime(tfprezime.getText());
            k.setAdresa(tfadresa.getText());
            k.setBrzina(cbbrzina.getValue());
            k.setProtok(cbprotok.getValue());
            k.setUgovor(cbugovor.getValue());
            Random r = new Random();
            String uuid = String.valueOf(r.nextInt(10000));
            k.setUuid(uuid);
            try {
                
                   k.Dodaj(); 
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Internet.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        b2.setOnAction((ActionEvent event) -> {
            Klijent k = new Klijent();
            TextInputDialog dialog = new TextInputDialog("uuid");
            dialog.setTitle("Obrisi");
            dialog.setContentText("Unesite uuid");
            Optional<String> result = dialog.showAndWait();
            String res = result.orElse(null);
            if (result.isPresent()) {
                try {
                    k.Obrisi(res);
                    ta.clear();
                    List<Klijent> lista = Klijent.Pregled();
                    for (Klijent lista1 : lista) {
                        ta.appendText(lista1.toString());
                        ta.appendText("\n");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Internet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        b3.setOnAction((ActionEvent event) -> {
            try {

                List<Klijent> lista = Klijent.Pregled();
                for (Klijent lista1 : lista) {
                    ta.appendText(lista1.toString());
                    ta.appendText("\n");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Internet.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color:beige;-fx-font-weight:bolder;-fx-font-color:blue");
        root.setHgap(20);
        root.setVgap(10);
        root.addRow(0, new Label("Ime:"), tfime);
        root.addRow(0, new Label("Prezime:"), tfprezime);
        root.addRow(0, new Label("Adresa:"), tfadresa);
        root.addRow(2, new Label("Brzina"), cbbrzina);
        root.addRow(2, new Label("Protok"), cbprotok);
        root.addRow(2, new Label("Duzina ugovora"), cbugovor);
        root.addRow(3, b1);
        root.addRow(4, b2);
        root.addRow(5, b3);
        root.add(ta, 0, 6, 6, 1);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Internet porudzbina");
        stage.show();
    }
    
    

    public static void main(String[] args) {
        Application.launch(args);

    }

}
