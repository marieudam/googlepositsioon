package Main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.sun.javafx.util.Utils.contains;

public class Main extends Application {
    Paring otsing = new Paring();
    Marksona[] tulemused;

    //Vaja on sisestada reaalne brauseri kasutajaagent, muidu annab Google errori 403 - Forbidden Request
    //Kasutajaagenti läheb vaja meetodis positsiooniTagastus
    //String USER_AGENT on final - st selle väärtus ei muutu programmi jooksul
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0";

    //Järgmises meetodis on HTMLi parsimiseks kasutatud Java library Jsoup
    //Koodi kirjutamisel võtsin eeskuju postitusest MPH WEBi blogis aadressil
    //http://mph-web.de/web-scraping-with-java-top-10-google-search-results/
    public static int positsiooniTagastus(String sisend, String domeen) throws IOException {
        //Loon ühenduse Jsoupi library'ga
        //Tulemuseks on Google'i otsitulemuste HTML kood
        final Document dokument = Jsoup.connect("https://google.ee/search?q=" + sisend + "&num=1000").userAgent(USER_AGENT).get();

        int loendur = 0;
        int loplikPositsioon = 0;
        for (Element result : dokument.select("h3.r a")) {

            String url = result.attr("href");
            loendur++;

            if (contains(url, domeen) & loplikPositsioon == 0) {
                loplikPositsioon = loendur;
            }
        }
        return loplikPositsioon;
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//
//
        Stage aken = primaryStage;
        aken.setTitle("Märksõna positsiooni kontrollija");

        GridPane raamistik = new GridPane();
        raamistik.setPadding(new Insets(20, 20, 20, 20));
        raamistik.setVgap(20);
        raamistik.setHgap(20);
//
        Scene stseen = new Scene(raamistik, 600, 400);
        aken.setScene(stseen);
        aken.show();
//
//        //Programmi pealkiri
        Text pealkiri = new Text("Märksõna positsiooni leidja Google'i otsingus");
        pealkiri.setFont(Font.font ("Verdana", 14));
        pealkiri.setFill(Color.DARKBLUE);
        raamistik.setConstraints(pealkiri, 0, 0);
        raamistik.setColumnSpan(pealkiri, 2);
        raamistik.setHalignment(pealkiri, HPos.LEFT);
//
//        //Domeeninime tekst
        Label tekst2 = new Label("Domeeninimi");
        raamistik.setConstraints(tekst2, 0, 1);
//
//        //Domeeninime lahter
        TextField lahter2 = new TextField();
        lahter2.setPromptText("domeen.ee");
        raamistik.setConstraints(lahter2, 0, 2);
//
//        //Märksõnade arvu tekst
        Label tekst3 = new Label("Märksõnade arv");
        raamistik.setConstraints(tekst3, 1, 1);
//
//        //Märksõnade arvu lahter
        TextField lahter3 = new TextField();
        lahter3.setPromptText("nt 6");
        raamistik.setConstraints(lahter3, 1, 2);
        lahter3.setPrefWidth(128);

       GridPane raamistik2 = new GridPane();

        Label tekstMarksona = new Label("Sisesta märksõnad");

//        //Märksõna sisestamise lahter
//        TextField marksonaLahter = new TextField();
//
//        //Teine märksõna sisestamise lahter
//        TextField marksonaLahter2 = new TextField();

        raamistik2.setConstraints(tekstMarksona, 0, 0);

        raamistik2.getChildren().add(tekstMarksona);
        Scene stseen2 = new Scene(raamistik2, 600, 400);



        // Raamistik 3
        Label tekstTulemus = new Label("TULEMUS");
        GridPane raamistik3 = new GridPane();
        raamistik3.getChildren().add(tekstTulemus);
        Scene stseen3 = new Scene(raamistik3, 600, 400);

        //Valmis nupp
        Button nupp = new Button();
        nupp.setText("VALMIS");
        raamistik.setColumnSpan(nupp, 2);
        raamistik.setHalignment(nupp, HPos.CENTER);
        nupp.setPrefWidth(328);
        nupp.setOnAction(event -> {


            otsing.setDomeen(lahter2.getText());
            otsing.setMarksonu(Integer.parseInt(lahter3.getText()));
            tulemused = new Marksona[otsing.getMarksonu()];


            for (int i = 0; i < tulemused.length; i++){
                tulemused[i] = new Marksona();
                tulemused[i].setNupp(new TextField());
                raamistik2.setConstraints(tulemused[i].getNupp(), 0, i);
                raamistik2.getChildren().add(tulemused[i].getNupp());

            }


            primaryStage.setScene(stseen2);
        });

        GridPane.setConstraints(nupp, 0, 3);


        raamistik.getChildren().addAll(pealkiri, tekst2, lahter2, lahter3, tekst3, nupp);

        // Otsi nupp
        Button nupp2 = new Button();
        nupp2.setText("OTSI");
        nupp2.setOnAction(event -> {
            for (int i = 0; i < tulemused.length; i++){
                tulemused[i].lahtriSisuSoneks();

                try {
                    int asukoht = positsiooniTagastus(tulemused[i].getMarksonaString(), otsing.getDomeen());
                    tulemused[i].setMarksonaVaartus(asukoht);
                    tulemused[i].tulemuseVormistamine();
                    raamistik3.setConstraints(tulemused[i].getTulemus(), 0, i+1);
                    raamistik3.getChildren().add(tulemused[i].getTulemus());
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            primaryStage.setScene(stseen3);
        });

        GridPane.setConstraints(nupp2, 1, 0);

        raamistik2.getChildren().add(nupp2);

//        String[] testkogum = {"veebiportaal", "aedes.ee"};
//        System.out.println("Positsioon: "+ positsiooniTagastus(testkogum));
//        Paring otsing2 = new Paring();
//        Marksona[] tulemused2 = new Marksona[10];
//        System.out.println(tulemused2);
//        tulemused2[1] = new Marksona();
//        tulemused2[1].setMarksonaString("test");
//        System.out.println(tulemused2[1].getMarksonaString());
    }

}
