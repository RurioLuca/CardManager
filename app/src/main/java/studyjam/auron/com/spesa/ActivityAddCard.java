package studyjam.auron.com.spesa;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import movile.com.creditcardguide.model.IssuerCode;
import movile.com.creditcardguide.view.CreditCardView;
import studyjam.auron.com.spesa.database.Card;
import studyjam.auron.com.spesa.database.MovimentiDB;
import studyjam.auron.com.spesa.utils.CodeUtils;
import studyjam.auron.com.spesa.utils.Costant;
import studyjam.auron.com.spesa.utils.OnSwipeTouchListener;

/**
 * Created by luca on 4/22/16.
 */
public class ActivityAddCard extends AppCompatActivity {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private CreditCardView creditCardView;
    private EditText edt_name;
    private EditText edt_number;
    private EditText edt_scadenza;
    private EditText edt_cvc;
    private EditText saldo;
    private Button save;
    private Spinner typeCard;
    private List<String> types;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private int dataCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Aggiungi Carta");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setTitleEnabled(false);
        setSupportActionBar(toolbar);
        creditCardView = (CreditCardView) findViewById(R.id.creditCardView);
        edt_name = (EditText) findViewById(R.id.name);
        edt_scadenza = (EditText) findViewById(R.id.date);
        edt_number = (EditText) findViewById(R.id.card_number);
        edt_cvc = (EditText) findViewById(R.id.cvc);
        saldo = (EditText) findViewById(R.id.saldo);
        typeCard = (Spinner) findViewById(R.id.type);
        save = (Button) findViewById(R.id.bt_save);

        creditCardView.chooseFlag(IssuerCode.PAYPAL);
        creditCardView.setTextExpDate("00/00");
        creditCardView.setTextNumber("0000 0000 0000 0000");
        creditCardView.setTextOwner("Name Surname");
        creditCardView.setTextCVV("000");
        dataCount = 0;
        types = new ArrayList<>();
        types.add(Costant.AMEX);
        types.add(Costant.AURA);
        types.add(Costant.VISAELECTRON);
        types.add(Costant.DINERS);
        types.add(Costant.ELO);
        types.add(Costant.HIPERCARD);
        types.add(Costant.MASTERCARD);
        types.add(Costant.NUBANK);
        types.add(Costant.PAYPAL);
        types.add(Costant.VISACREDITO);
        types.add(Costant.OTHER);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ActivityAddCard.this,
                R.layout.spinner_item, types);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        typeCard.setAdapter(dataAdapter);

        typeCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                creditCardView.chooseFlag(CodeUtils.getTypeCard(types.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });

        edt_scadenza.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                edt_scadenza.setError(null);
                if (!creditCardView.isShowingFront())
                    creditCardView.flipToFront();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              String date = s.toString();
              /*    if (dataCount > date.length() && date.contains("/")) {
                    date = date.substring(0, 1);
                    edt_scadenza.setText(date);
                    edt_scadenza.setSelection(date.length());
                }

                if (date.length() == 2 && !date.contains("/")) {
                    if (Integer.parseInt(date) <= 12) {
                        edt_scadenza.setError(getString(R.string.meseerrato));
                        return;
                    }
                    date = date + "/";
                    edt_scadenza.setText(date);
                    edt_scadenza.setSelection(date.length());
                }
*/
                creditCardView.setTextExpDate(date);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });


        edt_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!creditCardView.isShowingFront())
                    creditCardView.flipToFront();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              String number = s.toString();
 /*
                if (number.length() >= 19) {
                    number = number.substring(0, 18);
                    edt_number.setText(number);
                    return;
                }

                if (number.length() == 4 || number.length() == 9 || number.length() == 14) {
                    number = number + " ";
                    edt_number.setText(number);
                    edt_number.setSelection(number.length());
                }*/
                creditCardView.setTextNumber(number);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!creditCardView.isShowingFront())
                    creditCardView.flipToFront();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                creditCardView.setTextOwner(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edt_cvc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (creditCardView.isShowingFront())
                    creditCardView.flipToBack();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              String cvc = s.toString();
             /*   if (cvc.length() == 3) {
                   return;
                }*/
                creditCardView.setTextCVV(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        creditCardView.setOnTouchListener(new OnSwipeTouchListener(ActivityAddCard.this, new OnSwipeTouchListener.Swiped() {
            @Override
            public void onSwipeRight() {

                if (!creditCardView.isShowingFront())
                    creditCardView.flipToFront();
            }


            @Override
            public void onSwipeLeft() {
                if (creditCardView.isShowingFront())
                    creditCardView.flipToBack();
            }

            @Override
            public void onSwipeTop() {

            }

            @Override
            public void onSwipeBottom() {

            }
        }) {


        });
    }


    private void addCard() {

        Realm realm = Realm.getDefaultInstance();
        int nextID = 0;
        int nextID2 = 0;
        if (realm.where(Card.class).max("id") != null) {
            nextID = (realm.where(Card.class).max("id").intValue()) + 1;
        }
        if (realm.where(MovimentiDB.class).max("id") != null) {
            nextID2 = (realm.where(MovimentiDB.class).max("id").intValue()) + 1;
        }

        realm.beginTransaction();
        Card card = new Card();
        card.setId(nextID);
        card.setNumber(edt_number.getText().toString());
        card.setScadenza(edt_scadenza.getText().toString());
        card.setProprietario(edt_name.getText().toString());
        card.setSecurityCode(edt_cvc.getText().toString());
        card.setType(typeCard.getSelectedItem().toString());
        realm.copyToRealm(card);
        realm.commitTransaction();

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String sdate = format.format(new Date());
        realm.beginTransaction();

        MovimentiDB movimentiDB = new MovimentiDB();
        movimentiDB.setId(nextID2);
        movimentiDB.setCardId(nextID);
        movimentiDB.setMovimento(Double.parseDouble(saldo.getText().toString()));
        movimentiDB.setDate(sdate);
        movimentiDB.setCausa(getResources().getString(R.string.saldoiniziale));
        movimentiDB.setSaldo(Double.parseDouble(saldo.getText().toString()));
        realm.copyToRealm(movimentiDB);
        realm.commitTransaction();
        realm.close();
        this.finish();

    }

}
