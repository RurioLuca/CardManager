package studyjam.auron.com.spesa.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;
import studyjam.auron.com.spesa.ActivityHome;
import studyjam.auron.com.spesa.R;
import studyjam.auron.com.spesa.database.Card;
import studyjam.auron.com.spesa.fragment.adapter.CardListAdapter;
import studyjam.auron.com.spesa.ActivityAddCard;
import studyjam.auron.com.spesa.utils.Costant;
import studyjam.auron.com.spesa.utils.ItemSpaceGrid;

/**
 * Created by luca on 4/22/16.
 */
public class Fragment_CardList extends Fragment {

    private RecyclerView recyclerView;
    private CardListAdapter cardListAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<Card> cardList;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardlist, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.card_list);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        recyclerView.setHasFixedSize(false);


        cardList = null;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(getResources().getInteger(R.integer.colum_num), getResources().getConfiguration().orientation);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManager.setReverseLayout(false);
        staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            staggeredGridLayoutManager.setSpanCount(getResources().getInteger(R.integer.colum_num));
            recyclerView.addItemDecoration(new ItemSpaceGrid(spacingInPixels, getResources().getInteger(R.integer.colum_num)));
        } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            staggeredGridLayoutManager.setSpanCount(getResources().getInteger(R.integer.colum_num) * 2);
            recyclerView.addItemDecoration(new ItemSpaceGrid(spacingInPixels, getResources().getInteger(R.integer.colum_num) * 2));
        }
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        cardListAdapter = new CardListAdapter(cardList, getActivity());

        cardListAdapter.setCallbackClick(new CardListAdapter.CallbackClick() {
            @Override
            public void onClick(int pos, Card cardList) {

                Bundle bundle = new Bundle();
                bundle.putInt(Costant.CARD_ID, cardList.getId());

                Intent intent = new Intent(getActivity(), ActivityHome.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        recyclerView.setAdapter(cardListAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ActivityAddCard.class));
               /* getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, Fragment.instantiate(getActivity(), FragmetAddCard.class.getName()))
                        .addToBackStack("FragmetAddCard")
                        .commit();*/
            }
        });
    }


    @Override
    public void onResume() {

        super.onResume();
        Realm realm = Realm.getDefaultInstance();
        cardList = realm.where(Card.class).findAll();
        cardListAdapter.setCardList(cardList);
        cardListAdapter.notifyDataSetChanged();

    }
}
