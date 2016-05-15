package studyjam.auron.com.spesa.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by luca on 4/21/16.
 */
@RealmClass
public class Card extends RealmObject {

    @PrimaryKey
    private int id;
    private String number;
    private double saldo;
    private String scadenza;
    private String proprietario;
    private String securityCode;
    private String type;


    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
