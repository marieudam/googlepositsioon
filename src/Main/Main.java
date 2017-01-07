package Main;

import javafx.geometry.HPos;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static java.awt.Color.black;
import static java.awt.SystemColor.text;

public class Main extends Application {
    Button nupp;
    Button nupp2;
    Stage aken;

    String domeeninimi = "";

    //@Override
    //public void start(Stage primaryStage) throws Exception {
    //aken = primaryStage;
    //aken.setTitle("Märksõna positsiooni leidja");

    //GridPane raamistik = new GridPane();
    //raamistik.setPadding(new Insets(10, 10, 10, 10));
    //raamistik.setVgap(10);
    //raamistik.setHgap(10);

    //Vaja on sisestada reaalne brauseri kasutajaagent, muidu Google annab errori 403 - Forbidden Request
    //String USER_AGENT on final - st selle väärtus ei muutu programmi jooksul
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0";

    //Järgmises meetodis on HTMLi parsimiseks kasutatud Java library Jsoup
    //Koodi kirjutamisel võtsin eeskuju postitusest MPH WEBi blogis
    //aadressil http://mph-web.de/web-scraping-with-java-top-10-google-search-results/
    public static int positsiooniTagastus(String[] sisend) throws Exception {
        final Document dokument = Jsoup.connect("https://google.ee/search?q=" + sisend[0] + "&num=1000").userAgent(USER_AGENT).get();

        //Traverse the results
        int count = 0;
        int tulemus = 0;
        //String domeen = "http://www.aedes.ee/";
        String loplikUrl = "";
        for (Element result : dokument.select("h3.r a")) {

            final String title = result.text();
            String url = result.attr("href");
            count = count + 1;

            //Prindin vastuse välja
            //System.out.println(title + " -> " + url);

            if (url.equals("http://www." + sisend[1] + "/") & tulemus == 0) {
                loplikUrl = "http://www." + sisend[1] + "/";
                //System.out.println(title + " -> " + url);
                //System.out.println(sisend[0] + ": " + count);
                tulemus = count;
            }
        }
        //if (loplikUrl != sisend[1]) {
            //System.out.println("Tulemust ei leitud.");
        //}
        return tulemus;
    }
    public void testnupp(int a, GridPane raam){
        TextField sone = new TextField();
        raam.setConstraints(sone, 0, a);

    }
    public void testnupp2(int a, GridPane raam){
        TextField sone2 = new TextField();
        raam.setConstraints(sone2, 0, a);

    }

    //public static void main(String[] args) throws Exception {
    public static void main(String[] args) throws Exception {
        launch(args);
        //String[] testkogum = {"aedes", "http://www.aedes.ee/"};
        //System.out.println(positsiooniTagastus(testkogum));
        //String[] testkogum2 = {"aedes", "http://www.aedes.ee/"};
        ////nimekiri teha lõpmatu funktsiooniga - while loop (sama funktsioon, mis juba olemas)
        //String[][] nimekiri = {testkogum, testkogum2};
        //for (String[] element : nimekiri) {
            //positsiooniTagastus(element);

        }
    @Override
    public void start(Stage primaryStage) throws Exception {


        aken = primaryStage;
        aken.setTitle("Märksõna positsiooni kontrollija");

        GridPane raamistik = new GridPane();
        raamistik.setPadding(new Insets(20, 20, 20, 20));
        raamistik.setVgap(20);
        raamistik.setHgap(20);

        Scene stseen = new Scene(raamistik, 600, 400);
        aken.setScene(stseen);
        aken.show();

        //Märksõna tekst
        //Label tekst1 = new Label("Sisesta märksõna:");
        //GridPane.setConstraints(tekst1, 0, 0);

        //Märksõna lahter
        //TextField lahter1 = new TextField();
        //lahter1.setPromptText("märksõna");
        //GridPane.setConstraints(lahter1, 0, 1);

        //Programmi pealkiri
        Text pealkiri = new Text("Märksõna positsiooni leidja Google'i otsingus");
        pealkiri.setFont(Font.font ("Verdana", 14));
        pealkiri.setFill(Color.DARKBLUE);
        raamistik.setConstraints(pealkiri, 0, 0);
        raamistik.setColumnSpan(pealkiri, 2);
        raamistik.setHalignment(pealkiri, HPos.LEFT);

        //Domeeninime tekst
        Label tekst2 = new Label("Domeeninimi");
        raamistik.setConstraints(tekst2, 0, 1);

        //Domeeninime lahter
        TextField lahter2 = new TextField();
        lahter2.setPromptText("domeen.ee");
        raamistik.setConstraints(lahter2, 0, 2);

        //Märksõnade arvu tekst
        Label tekst3 = new Label("Märksõnade arv");
        raamistik.setConstraints(tekst3, 1, 1);

        //Märksõnade arvu lahter
        TextField lahter3 = new TextField();
        lahter3.setPromptText("nt 6");
        raamistik.setConstraints(lahter3, 1, 2);
        lahter3.setPrefWidth(128);

        //Tulemuse lahter
        //Label tulemus = new Label("");
        //GridPane.setConstraints(tulemus, 0, 4);

        //Otsingu nupp
        //nupp = new Button();
        //nupp.setText("OTSI");
        //See klass tegeleb nupuvajutuse eventidega
        //nupp.setOnAction(e -> System.out.println("Test"));
        //nupp.setOnAction(event -> {
            //String marksona = lahter1.getText();
            //System.out.println(marksona);
            //String domeeninimi = lahter2.getText();
            //System.out.println(domeeninimi);
            //String otsinguarv = lahter3.getText();
            //String [] kogum2 = {marksona, domeeninimi};
            //try {
                //tulemus.setText("Aedes on positsioonil " + String.valueOf(positsiooniTagastus(kogum2)));
            //} catch (Exception e) {
                //e.printStackTrace();
            //}
            //System.out.println(kogum2);
            //tulemus.setText("Test");
        //});


//        int marknumber = 0;
//        TextField[] test = new TextField[marknumber];
//        System.out.println(test);
//        for (int i = 1; i< marknumber; i++){
//
//            test[i]= new TextField();
//            System.out.println(i);
//        }
//
        GridPane raamistik2 = new GridPane();
//        for ( int i = 1; i< marknumber; i++){
//
//            raamistik2.setConstraints(test[i], 0, i);
//            raamistik2.getChildren().add(test[i]);
//            System.out.println(i);
//        }

        //Märsõna sisestamise tekst
        Label tekstMarksona = new Label("Sisesta märksõnad");

        //Märksõna sisestamise lahter
        TextField marksonaLahter = new TextField();

        //Teine märksõna sisestamise lahter
        TextField marksonaLahter2 = new TextField();

        raamistik2.setConstraints(tekstMarksona, 0, 0);

//        raamistik2.setConstraints(marksonaLahter, 0, 1);
//        raamistik2.setConstraints(marksonaLahter2, 0, 2);
        raamistik2.getChildren().addAll(tekstMarksona, marksonaLahter, marksonaLahter2);
        Scene stseen2 = new Scene(raamistik2, 600, 400);

        Label tekstTulemus = new Label("TULEMUS");
        GridPane raamistik3 = new GridPane();
        raamistik3.getChildren().add(tekstTulemus);
        Scene stseen3 = new Scene(raamistik3, 600, 400);

        // Valmis nupp
        nupp = new Button();
        nupp.setText("VALMIS");
        raamistik.setColumnSpan(nupp, 2);
        raamistik.setHalignment(nupp, HPos.CENTER);
        nupp.setPrefWidth(328);
        nupp.setOnAction(event -> {
            String domeen = lahter2.getText();
            domeeninimi = domeen;
            String lahtriteArv = lahter3.getText();
            int testInt = Integer.parseInt(lahtriteArv);
            int marknumber = testInt;
            TextField[] test = new TextField[marknumber];
            for (int i = 1; i< marknumber; i++){

                test[i]= new TextField();
                //System.out.println(i);
            }

            for ( int i = 1; i< marknumber; i++){

                raamistik2.setConstraints(test[i], 0, i);
                raamistik2.getChildren().add(test[i]);
            }
            //marknumber = lahtriteArv;
            //System.out.println(lahtriteArv);
            primaryStage.setScene(stseen2);
                });

        GridPane.setConstraints(nupp, 0, 3);


        raamistik.getChildren().addAll(pealkiri, tekst2, lahter2, lahter3, tekst3, nupp);

        // Otsi nupp
        nupp2 = new Button();
        nupp2.setText("OTSI");
        nupp2.setOnAction(event -> {
            int arv = 0;
            String marksona1 = marksonaLahter.getText();
            String marksona2 = marksonaLahter2.getText();
            String [] marksonad = {marksona1, marksona2};
            String [] kogum2 = {marksonad[arv], domeeninimi};
            //System.out.println(domeeninimi);
            //try {
                //System.out.println("Aedes on positsioonil " + String.valueOf(positsiooniTagastus(kogum2)));
            //} catch (Exception e) {
            //e.printStackTrace();
            //}
            for (String marksona : marksonad) {
                try {
                    System.out.println(marksona + " " + String.valueOf(positsiooniTagastus(kogum2)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //for (String element : kogum2) {
                    //arv = arv + 1;
                //}
            }

            primaryStage.setScene(stseen3);
        });

        GridPane.setConstraints(nupp2, 1, 0);

        raamistik2.getChildren().add(nupp2);

    }

}




