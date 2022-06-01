package com.example.finserv.userdashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finserv.R;
import com.example.finserv.helperclass.Constants;
import com.example.finserv.helperclass.ToastIntentClass;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    Context context;
    ArrayList<TransactionList> transactionListArrayList;
    View view;
    SharedPreferences sp;
    int iPosition;


    public TransactionAdapter(FragmentActivity activity, ArrayList<TransactionList> arrayList) {

        context = activity;
        transactionListArrayList = arrayList;
        sp = context.getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public TransactionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.custom_transaction_view_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyViewHolder holder, final int position) {
        holder.date.setText("" + transactionListArrayList.get(position).getDate());
        holder.amt.setText("$" + transactionListArrayList.get(position).getAmount());
        holder.desp.setText("" + transactionListArrayList.get(position).getDesc());
        holder.acc.setText("" + transactionListArrayList.get(position).getAccount());

        if(transactionListArrayList.get(position).type.equals("debit")){
            holder.amt.setTextColor(Color.parseColor("#ff0000"));
        }else{
            holder.amt.setTextColor(Color.parseColor("#00ff00"));
        }

        holder.editTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ToastIntentClass(context,EditTransactionActivity.class);
            }
        });
//
//        holder.delivered.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iPosition = position;
//                if (new ConnectionDetector(context).isConnectingToInternet()) {
//                    //Toast.makeText(this, "Internet Connected", Toast.LENGTH_SHORT).show();
//                    new deliveredOrder().execute();
//                } else {
//                    new ConnectionDetector(context).connectiondetect();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return transactionListArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, amt, desp, acc;
        Button edit, delete;
        ImageView editTransaction;
//        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.transaction_date);
            amt = itemView.findViewById(R.id.transaction_amt);
            desp = itemView.findViewById(R.id.transaction_desp);
            acc = itemView.findViewById(R.id.transaction_acc);
            editTransaction = itemView.findViewById(R.id.transaction_edit_dash);

//            cardView = itemView.findViewById(R.id.pendingquerylist_layout);

        }
    }


}
