package studyjam.auron.com.spesa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import studyjam.auron.com.spesa.R;
import studyjam.auron.com.spesa.database.MovimentiDB;
import studyjam.auron.com.spesa.fragment.adapter.MovimentiAdapter;
import studyjam.auron.com.spesa.utils.Costant;
import studyjam.auron.com.spesa.utils.ItemSpace;

/**
 * Created by luca on 4/21/16.
 */
public class FragmentMovimenti extends Fragment {

    private RecyclerView recyclerView;
    private MovimentiAdapter movimentiAdapter;
    private List<MovimentiDB> movimentiDBList;

    private int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movimentilist, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        Bundle bundle = getArguments();
        id = bundle.getInt(Costant.CARD_ID);
        movimentiDBList = new ArrayList<>();
        recyclerView.setHasFixedSize(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new ItemSpace(getResources().getDimensionPixelSize(R.dimen.space)));

        movimentiAdapter = new MovimentiAdapter(movimentiDBList, getActivity());
        recyclerView.setAdapter(movimentiAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        Realm realm = Realm.getDefaultInstance();
        movimentiDBList = realm.where(MovimentiDB.class).equalTo("cardId", id).findAllSorted("id", Sort.DESCENDING);
        movimentiAdapter.setMovimentiDBList(movimentiDBList);
        movimentiAdapter.notifyDataSetChanged();
    }
}
