package studyjam.auron.com.spesa.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by luca on 4/22/16.
 */
@RealmClass
public class MovimentiDB extends RealmObject {

    @PrimaryKey
    private int id;

    private int cardId;

    private double movimento;

    private String date;

    private double saldo;

    private String causa;


    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getMovimento() {
        return movimento;
    }

    public void setMovimento(double movimento) {
        this.movimento = movimento;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }
}
