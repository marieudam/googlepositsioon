package Main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    Button nupp;
    Stage aken;

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

    public static String[] kasutajaSisendid() {

    Scanner scan = new Scanner(System.in);
    System.out.println("Sisesta märksõna:");
    String keyword = scan.next();
    Scanner scan2 = new Scanner(System.in);
    System.out.println("Sisesta domeeninimi:");
    String domeen = scan2.next();
    String[] kogum = {keyword, domeen};
    return kogum;
    }

    public static int positsiooniTagastus(String[] sisend) throws Exception {
        final Document dokument = Jsoup.connect("https://google.ee/search?q=" + sisend[0] + "&num=25").userAgent(USER_AGENT).get();

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

            if (url.equals(sisend[1]) & tulemus == 0) {
                loplikUrl = sisend[1];
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
        aken.setTitle("Märksõna positsiooni leidja");

        GridPane raamistik = new GridPane();
        raamistik.setPadding(new Insets(10, 10, 10, 10));
        raamistik.setVgap(10);
        raamistik.setHgap(10);

        //Märksõna tekst
        Label tekst1 = new Label("Sisesta märksõna:");
        GridPane.setConstraints(tekst1, 0, 0);

        //Märksõna lahter
        TextField lahter1 = new TextField();
        lahter1.setPromptText("Märksõna");
        GridPane.setConstraints(lahter1, 1, 0);

        //Domeeninime tekst
        Label tekst2 = new Label("Sisesta domeeninimi:");
        GridPane.setConstraints(tekst2, 0, 1);

        //Domeeninime lahter
        TextField lahter2 = new TextField();
        lahter2.setPromptText("Domeeninimi");
        GridPane.setConstraints(lahter2, 1, 1);

        //Märksõna lahter
        Label tulemus = new Label("");
        GridPane.setConstraints(tulemus, 0, 4);

        //Otsingu nupp
        nupp = new Button();
        nupp.setText("Otsi");
        //See klass tegeleb nupuvajutuse eventidega
        //nupp.setOnAction(e -> System.out.println("Test"));
        nupp.setOnAction(event -> {
            String marksona = lahter1.getText();
            //System.out.println(marksona);
            String domeeninimi = lahter2.getText();
            //System.out.println(domeeninimi);
            String [] kogum2 = {marksona, domeeninimi};
            try {
                tulemus.setText("Aedes on positsioonil " + String.valueOf(positsiooniTagastus(kogum2)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(kogum2);
            //tulemus.setText("Test");
        });
        GridPane.setConstraints(nupp, 1, 3);




        raamistik.getChildren().addAll(tekst1, lahter1, tekst2, lahter2, nupp, tulemus);

        Scene stseen = new Scene(raamistik, 400, 300);
        aken.setScene(stseen);
        aken.show();
    }

    //@Override
    //public void handle(ActionEvent event) {
        //if(event.getSource()==nupp){
            //System.out.println("Test");
        //}

    //}
}


//public class Main{
//    public static void main(String args[]) {
//        System.out.println("Google'i otsitulemuse positsiooni leidmine");
//        System.out.println("Autor: Marie");
//        System.out.println("Aasta: 2016");
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Sisesta märksõna, mida otsid:");
//        String input1 = sc.nextLine();
//        System.out.println("Sisesta piirkond, kust otsid:");
//        String input2 = sc.nextLine();
//        // Programm guugeldab sisestatud parameetritele vastavaid tulemusi
//        System.out.println("Sisesta domeen:");
//        String input3 = sc.nextLine();
//        // Programm otsib Google'i tulemustest sobiva tulemuse välja ning kuvab selle positsiooni
//
//    }


