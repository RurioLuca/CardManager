package studyjam.auron.com.spesa.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import studyjam.auron.com.spesa.R;
import studyjam.auron.com.spesa.database.Card;

/**
 * Created by luca on 4/22/16.
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {
    private List<Card> cardList;
    private Context context;
    private CallbackClick callbackClick;


    public CardListAdapter(List<Card> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }


    public void setCallbackClick(CallbackClick callbackClick) {
        this.callbackClick = callbackClick;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public CardListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(cardList.get(position).getProprietario());
        holder.txtData.setText(cardList.get(position).getScadenza());
        holder.type.setText(cardList.get(position).getType());
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public interface CallbackClick {

        void onClick(int pos, Card cardList);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtName;
        private TextView txtData;
        private TextView type;
        private CardView cardView;

        public ViewHolder(View item) {
            super(item);

            cardView = (CardView) item.findViewById(R.id.card_view);
            txtName = (TextView) item.findViewById(R.id.name);
            txtData = (TextView) item.findViewById(R.id.date);
            type = (TextView) item.findViewById(R.id.type);
            cardView.setOnClickListener(this);
        }


        @Override
        public void onClick(View item) {

            if (callbackClick != null)
                callbackClick.onClick(getAdapterPosition(), cardList.get(getAdapterPosition()));
        }


    }
}
