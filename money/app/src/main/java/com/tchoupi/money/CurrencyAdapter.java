package com.tchoupi.money;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> implements View.OnClickListener {

    private List<Currency> currencies;

    CurrencyAdapter(List<Currency> currencies){
        this.currencies = currencies;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rootItem:
                Context context = v.getContext();
                Currency currency = (Currency) v.getTag();
                Intent intent = new Intent(context, CurrentConvertActivity.class);
                intent.putExtra("currency", currency);
                context.startActivity(intent);
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView flag;
        final TextView symbol;
        final TextView rate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flagImageView);
            symbol = itemView.findViewById(R.id.symbolTextView);
            rate = itemView.findViewById(R.id.rateTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_currency, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Currency currency = currencies.get(position);
        holder.flag.setImageResource(currency.getFlag());
        holder.symbol.setText(currency.getSymbol());
        holder.rate.setText(currency.getRate() + "");

        holder.itemView.setTag(currency);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

}
