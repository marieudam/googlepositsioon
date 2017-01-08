package Main;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.lang.*;

public class Marksona {
    private String marksonaString;
    private int marksonaPositsioon;
    private TextField lahter;
    private Text tulemus;

    public void tulemuseVormistamine(){
        this.tulemus = new Text();
        this.tulemus.setText(this.marksonaString+":\t"+Integer.toString(this.marksonaPositsioon));
    }

    public void lahtriSisuSoneks(){
        this.marksonaString = this.lahter.getText();
    }

    public Text getTulemus() {
        return tulemus;
    }

    public void setTulemus(Text tulemus) {
        this.tulemus = tulemus;
    }

    public TextField getLahter() {
        return lahter;
    }

    public void setLahter(TextField lahter) {
        this.lahter = lahter;
    }

    public String getMarksonaString() {
        return marksonaString;
    }

    public int getMarksonaPositsioon() {
        return marksonaPositsioon;
    }

    public void setMarksonaString(String marksonaString) {
        this.marksonaString = marksonaString;
    }

    public void setMarksonaPositsioon(int marksonaPositsioon) {
        this.marksonaPositsioon = marksonaPositsioon;
    }
}
