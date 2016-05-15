package studyjam.auron.com.spesa.fragment.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import studyjam.auron.com.spesa.fragment.FragmentDettaglio;
import studyjam.auron.com.spesa.fragment.FragmentMovimenti;
import studyjam.auron.com.spesa.utils.Costant;

/**
 * Created by luca on 4/21/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private int id;

    public PagerAdapter(FragmentManager fm, int id) {
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Costant.CARD_ID, id);
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new FragmentDettaglio();
                break;
            case 1:
                frag = new FragmentMovimenti();
                break;
        }
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Dettaglio Carta";
            case 1:
                return "Movimenti";
            default:
                return "error";

        }

    }


}
