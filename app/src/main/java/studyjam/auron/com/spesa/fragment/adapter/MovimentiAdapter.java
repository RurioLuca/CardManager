package studyjam.auron.com.spesa.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import studyjam.auron.com.spesa.R;
import studyjam.auron.com.spesa.database.MovimentiDB;

/**
 * Created by luca on 4/27/16.
 */
public class MovimentiAdapter extends RecyclerView.Adapter<MovimentiAdapter.ViewHolder> {

    private List<MovimentiDB> movimentiDBList;
    private Context context;

    public MovimentiAdapter(List<MovimentiDB> movimentiDBList, Context context) {
        this.movimentiDBList = movimentiDBList;
        this.context = context;
    }

    public void setMovimentiDBList(List<MovimentiDB> movimentiDBList) {
        this.movimentiDBList = movimentiDBList;
    }

    @Override
    public MovimentiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.data.setText(movimentiDBList.get(position).getDate());
        if (movimentiDBList.get(position).getMovimento() > 0) {
            holder.operation.setImageResource(R.drawable.ic_add_circle_green_500_48dp);
            holder.somma.setText("+ " + movimentiDBList.get(position).getMovimento());
        } else {
            holder.operation.setImageResource(R.drawable.ic_remove_circle_red_600_48dp);
            holder.somma.setText("" + movimentiDBList.get(position).getMovimento());
        }
    }

    @Override
    public int getItemCount() {
        return movimentiDBList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView somma;
        private TextView data;
        private ImageView operation;

        public ViewHolder(View itemView) {

            super(itemView);
            somma = (TextView) itemView.findViewById(R.id.somma);
            data = (TextView) itemView.findViewById(R.id.data);
            operation = (ImageView) itemView.findViewById(R.id.operation);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
