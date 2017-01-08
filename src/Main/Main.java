package Main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.scene.control.*;
import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    public static int positsiooniTagastus(String kasutajaMarksona, String domeen) throws IOException {
        //Loon ühenduse Jsoupi library'ga
        //Tulemuseks (dokument) on Google'i otsitulemuste HTML kood
        final Document dokument = Jsoup.connect("https://google.ee/search?q=" + kasutajaMarksona + "&num=100").userAgent(USER_AGENT).get();

        int loendur = 0;
        int positsiooniTagastus = 0;
        //Kogu HTMList saan kätte ühe otsingutulemuse URLi ja pealkirja
        for (Element tulemus : dokument.select("h3.r > a")) {

            String url = tulemus.attr("href");
            loendur++;

            if (contains(url, domeen)) {
                positsiooniTagastus = loendur;
                break;
            }
        }
        return positsiooniTagastus;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Aken
        Stage aken = primaryStage;
        aken.setTitle("Märksõna positsiooni kontrollija");

        //Raamistik1
        GridPane raamistik = new GridPane();
        raamistik.setPadding(new Insets(20, 20, 20, 20));
        raamistik.setVgap(20);
        raamistik.setHgap(20);

        Scene stseen = new Scene(raamistik, 370, 400);
        aken.setScene(stseen);
        aken.show();

        //Programmi pealkiri
        Label pealkiri = new Label("Märksõna positsiooni leidja Google'i otsingus");
        pealkiri.setFont(Font.font ("Verdana", 14));
        pealkiri.setTextFill(Color.DARKBLUE);
        raamistik.setConstraints(pealkiri, 0, 0);
        raamistik.setColumnSpan(pealkiri, 2);
        raamistik.setHalignment(pealkiri, HPos.LEFT);

        //Domeeninime tekst
        Label tekst = new Label("Domeeninimi");
        raamistik.setConstraints(tekst, 0, 1);

        //Domeeninime lahter
        TextField lahter = new TextField();
        lahter.setPromptText("domeen.ee");
        raamistik.setConstraints(lahter, 0, 2);

        //Märksõnade arvu tekst
        Label tekst2 = new Label("Märksõnade arv");
        raamistik.setConstraints(tekst2, 1, 1);

        //Märksõnade arvu lahter
        TextField lahter2 = new TextField();
        lahter2.setPromptText("nt 6");
        raamistik.setConstraints(lahter2, 1, 2);
        lahter2.setPrefWidth(128);

        //Raamistik2
        GridPane raamistik2 = new GridPane();
        raamistik2.setPadding(new Insets(20, 20, 20, 20));
        raamistik2.setVgap(20);
        raamistik2.setHgap(20);

        //Märksõnade sisestamise tekst
        Label tekstMarksona = new Label("Sisesta märksõnad");

        raamistik2.setConstraints(tekstMarksona, 0, 0);

        raamistik2.getChildren().add(tekstMarksona);
        Scene stseen2 = new Scene(raamistik2, 400, 400);

        // Raamistik 3
        GridPane raamistik3 = new GridPane();

        //Tulemuste pealkiri
        Label tekstTulemus = new Label("Tulemused");
        tekstTulemus.setFont(new Font("Verdana", 14));
        tekstTulemus.setTextFill(Color.DARKBLUE);

        raamistik3.setPadding(new Insets(20, 20, 20, 20));
        raamistik3.setVgap(20);
        raamistik3.getChildren().add(tekstTulemus);
        Scene stseen3 = new Scene(raamistik3, 400, 400);

        //Valmis nupp (raamistik1)
        Button nupp = new Button();
        nupp.setText("VALMIS");
        raamistik.setColumnSpan(nupp, 2);
        raamistik.setHalignment(nupp, HPos.CENTER);
        nupp.setPrefWidth(328);

        //Sündmus, mis käivitub nupule vajutades
        nupp.setOnAction(event -> {
            //Moodustub String domeen
            otsing.setDomeen(lahter.getText());
            //Moodustub int marksonu
            otsing.setMarksonu(Integer.parseInt(lahter2.getText()));
            //Returnib märksõnade arvu int'i -> ütleb, mitu elementi listis on
            tulemused = new Marksona[otsing.getMarksonu()];


            for (int i = 0; i < tulemused.length; i++){
                //Esmalt teen iga tulemuse jaoks uue Märksõna-tüüpi listi
                tulemused[i] = new Marksona();
                //ja lisan iga märksõna jaoks uue lahtri
                tulemused[i].setLahter(new TextField());
                raamistik2.setConstraints(tulemused[i].getLahter(), 0, i+1);
                raamistik2.getChildren().add(tulemused[i].getLahter());
                }

                primaryStage.setScene(stseen2);

        });

        //Nupu VALMIS asukoht
        GridPane.setConstraints(nupp, 0, 3);

        raamistik.getChildren().addAll(pealkiri, tekst, lahter, lahter2, tekst2, nupp);

        //Otsi nupp (raamistik 2)
        Button nupp2 = new Button();
        nupp2.setText("OTSI");
        nupp2.setPrefWidth(170);

        //Sündmus, mis käivitub nupule vajutades
        nupp2.setOnAction(event -> {
            for (int i = 0; i < tulemused.length; i++){
                //Rakendan igale tulemusele meetodi, mis võtab lahtrist märksõna
                tulemused[i].lahtriSisuSoneks();
                try {
                    int asukoht = positsiooniTagastus(tulemused[i].getMarksonaString(), otsing.getDomeen());
                    //Annan tulemusele kohal i väärtuseks asukoha (väärtus, mis positsioonil märksõna Google'is on)
                    tulemused[i].setMarksonaPositsioon(asukoht);
                    tulemused[i].tulemuseVormistamine();
                    raamistik3.setConstraints(tulemused[i].getTulemus(), 0, i+1);
                    raamistik3.getChildren().add(tulemused[i].getTulemus());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            primaryStage.setScene(stseen3);

        });

        GridPane.setConstraints(nupp2, 1, 1);
        raamistik2.getChildren().add(nupp2);

    }
}
