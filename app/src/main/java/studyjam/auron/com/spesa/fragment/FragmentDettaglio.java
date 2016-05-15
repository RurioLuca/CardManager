package studyjam.auron.com.spesa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import studyjam.auron.com.spesa.R;
import studyjam.auron.com.spesa.database.MovimentiDB;
import studyjam.auron.com.spesa.utils.Costant;

/**
 * Created by luca on 4/21/16.
 */
public class FragmentDettaglio extends Fragment {


    private List<MovimentiDB> movimentiDBs;
    private TextView txt_saldo;
    private TextView txt_saldoal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);


        txt_saldo = (TextView) view.findViewById(R.id.saldo);
        txt_saldoal = (TextView) view.findViewById(R.id.saldoal);
        Bundle bundle = getArguments();
        int id = bundle.getInt(Costant.CARD_ID);

        Realm realm = Realm.getDefaultInstance();

        movimentiDBs = realm.where(MovimentiDB.class).equalTo("cardId", id).findAllSorted("id", Sort.DESCENDING);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (!movimentiDBs.isEmpty()) {
            txt_saldo.setText("" + movimentiDBs.get(0).getSaldo());
            txt_saldoal.setText(getResources().getString(R.string.saldoal, movimentiDBs.get(0).getDate()));
        }
    }
}
