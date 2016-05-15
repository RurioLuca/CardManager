package studyjam.auron.com.spesa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import studyjam.auron.com.spesa.database.MovimentiDB;
import studyjam.auron.com.spesa.utils.Costant;

/**
 * Created by luca on 4/29/16.
 */
public class ActivityAddMovimento extends AppCompatActivity implements CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private CalendarDatePickerDialogFragment calendarDatePickerDialogFragment;
    private Button save;
    private TextView peaker;
    private EditText edt_causa, edt_movimento;
    private Spinner type;
    private int nextId = 0, cardId;
    private List<String> types;
    private List<MovimentiDB> movimentiDBs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovimento);
        save = (Button) findViewById(R.id.save);
        peaker = (TextView) findViewById(R.id.peaker);
        edt_causa = (EditText) findViewById(R.id.causa);
        edt_movimento = (EditText) findViewById(R.id.somma);
        type = (Spinner) findViewById(R.id.type);
        nextId = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        calendarDatePickerDialogFragment = CalendarDatePickerDialogFragment
                .newInstance(this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));

        Bundle bundle = getIntent().getExtras();

        cardId = bundle.getInt(Costant.CARD_ID);
        Realm realm = Realm.getDefaultInstance();

        movimentiDBs = realm.where(MovimentiDB.class).equalTo("cardId", cardId).findAllSorted("id", Sort.DESCENDING);

        if (!movimentiDBs.isEmpty() && movimentiDBs != null) {
            nextId = movimentiDBs.get(0).getId() + 1;
        }


        peaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarDatePickerDialogFragment.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
            }
        });

        types = new ArrayList<>();

        types.add(getString(R.string.entrata));

        types.add(getString(R.string.uscita));


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ActivityAddMovimento.this,
                R.layout.spinner_item, types);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        type.setAdapter(dataAdapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                double num = type.getSelectedItemPosition() == 0 ? 1 : -1;
                double mov = Double.parseDouble(edt_movimento.getText().toString().trim()) * num;
                double saldo = movimentiDBs.get(0).getSaldo() + mov;

                MovimentiDB movimentiDB = new MovimentiDB();
                movimentiDB.setId(nextId);
                movimentiDB.setCardId(cardId);
                movimentiDB.setMovimento(mov);
                movimentiDB.setSaldo(saldo);
                movimentiDB.setCausa(edt_causa.getText().toString());
                movimentiDB.setDate(peaker.getText().toString());
                realm.copyToRealm(movimentiDB);
                realm.commitTransaction();
                finish();
            }
        });

    }


    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

        peaker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

    }

    private void validForm(){
        boolean valid=true;
        if(TextUtils.isEmpty(edt_movimento.getText())) {
         edt_movimento.setError(getString(R.string.campovuoto));
            valid = false;
        }


    }
}
