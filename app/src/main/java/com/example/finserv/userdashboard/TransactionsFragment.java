package com.example.finserv.userdashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finserv.R;
import com.example.finserv.helperclass.Constants;
import com.example.finserv.helperclass.ToastIntentClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionsFragment extends Fragment {

    RecyclerView recyclerView;
    SharedPreferences sp;
    String trans_id;
    ArrayList<TransactionList> arrayList;
    TransactionAdapter transactionAdapter;
    FloatingActionButton add_transaction_fbtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransactionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionsFragment newInstance(String param1, String param2) {
        TransactionsFragment fragment = new TransactionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        sp = this.getActivity().getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);

        trans_id = sp.getString(Constants.ID,"");

        recyclerView = view.findViewById(R.id.transaction_fragment_recycler);
        add_transaction_fbtn = view.findViewById(R.id.add_transaction_fbtn_transaction_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setTransactions();

        add_transaction_fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ToastIntentClass(getActivity(), AddTransactionActivity.class);
            }
        });

        return view;
    }

    public void setTransactions(){
        arrayList = new ArrayList<>();

//        int id = 1, amt = 200;
//        String date = "4/20/2022";

        TransactionList list1 = new TransactionList();
        list1.setId("1");
        list1.setAmount("153");
        list1.setDate("4/20/2022");
        list1.setType("debit");
        list1.setDesc("Grocery");
        list1.setAccount("Cash");

        arrayList.add(list1);

        TransactionList list2 = new TransactionList();
        list2.setId("2");
        list2.setAmount("309");
        list2.setDate("4/23/2022");
        list2.setType("credit");
        list2.setDesc("Salary");
        list2.setAccount("Cash");

        arrayList.add(list2);

        TransactionList list3 = new TransactionList();
        list3.setId("3");
        list3.setAmount("78");
        list3.setDate("4/23/2022");
        list3.setType("credit");
        list3.setDesc("Cashback");
        list3.setAccount("Cash");

        arrayList.add(list3);

        TransactionList list4 = new TransactionList();
        list4.setId("1");
        list4.setAmount("55");
        list4.setDate("4/24/2022");
        list4.setType("debit");
        list4.setDesc("Gasoline");
        list4.setAccount("Cash");

        arrayList.add(list4);

        TransactionList list5 = new TransactionList();
        list5.setId("2");
        list5.setAmount("18");
        list5.setDate("4/25/2022");
        list5.setType("debit");
        list5.setDesc("Food");
        list5.setAccount("Cash");

        arrayList.add(list5);


//        for(int i = 0; i<5; i++){
//            TransactionList list = new TransactionList();
//            list.setId(String.valueOf(id));
//            list.setAmount(String.valueOf(amt));
//            list.setDate(date);
//            list.setDesc("Expense");
//            list.setAccount("Cash");
//            id = id + 1;
//            amt = amt + 50;
//
//            arrayList.add(list);
//        }

        transactionAdapter = new TransactionAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(transactionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}