package Main;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.lang.*;
/**
 * Created by User on 07.01.2017.
 */
public class Marksona {
    private String marksonaString;
    private int marksonaVaartus;
    private TextField nupp;
    private Text tulemus;

    public void tulemuseVormistamine(){
        this.tulemus = new Text();
        this.tulemus.setText(this.marksonaString+": "+Integer.toString(this.marksonaVaartus));
    }

    public void lahtriSisuSoneks(){
        this.marksonaString = this.nupp.getText();
    }

    public Text getTulemus() {
        return tulemus;
    }

    public void setTulemus(Text tulemus) {
        this.tulemus = tulemus;
    }


    public TextField getNupp() {
        return nupp;
    }

    public void setNupp(TextField nupp) {
        this.nupp = nupp;
    }

    public String getMarksonaString() {
        return marksonaString;
    }

    public int getMarksonaVaartus() {
        return marksonaVaartus;
    }

    public void setMarksonaString(String marksonaString) {
        this.marksonaString = marksonaString;
    }

    public void setMarksonaVaartus(int marksonaVaartus) {
        this.marksonaVaartus = marksonaVaartus;
    }
}
