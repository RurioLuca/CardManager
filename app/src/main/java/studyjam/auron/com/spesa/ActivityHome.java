package studyjam.auron.com.spesa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.realm.Realm;
import movile.com.creditcardguide.view.CreditCardView;
import studyjam.auron.com.spesa.database.Card;
import studyjam.auron.com.spesa.fragment.adapter.PagerAdapter;
import studyjam.auron.com.spesa.utils.CodeUtils;
import studyjam.auron.com.spesa.utils.Costant;

/**
 * Created by luca on 4/21/16.
 */
public class ActivityHome extends AppCompatActivity {

    private Card card;
    private CreditCardView creditCardView;
    private FloatingActionButton floatingActionButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int cardId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        creditCardView = (CreditCardView) findViewById(R.id.creditCardView);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        Bundle bundle = getIntent().getExtras();
        cardId = bundle.getInt(Costant.CARD_ID);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        collapsingToolbarLayout.setTitleEnabled(false);
        Realm realm = Realm.getDefaultInstance();
        card = realm.where(Card.class).equalTo("id", cardId).findFirst();

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), cardId);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.gray_hard_text), getResources().getColor(R.color.white));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        creditCardView.chooseFlag(CodeUtils.getTypeCard(card.getType()));
        creditCardView.setTextExpDate(card.getScadenza());
        creditCardView.setTextNumber(card.getNumber());
        creditCardView.setTextOwner(card.getProprietario());
        creditCardView.setTextCVV(card.getSecurityCode());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt(Costant.CARD_ID, cardId);
                Intent intent = new Intent(ActivityHome.this, ActivityAddMovimento.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else if (tab.getPosition() == 0) {
                    floatingActionButton.setVisibility(View.GONE);
                }
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    floatingActionButton.setVisibility(View.GONE);
                } else if (tab.getPosition() == 0) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else if (tab.getPosition() == 0) {
                    floatingActionButton.setVisibility(View.GONE);
                }
            }
        });
    }


}
