package com.dodemy.trackshipment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * AddSenderInfoFragment add sender's information to the shipment.
 */

public class AddSenderInfoFragment extends Fragment {
    private static final String LIST_KEY = "list_key";
    private View rootView;
    private ArrayList<AtomItem> items;

    public static AddSenderInfoFragment newInstance(ArrayList<AtomItem> list) {
        AddSenderInfoFragment send = new AddSenderInfoFragment();
        send.setArguments(list);
        Bundle bundle = new Bundle();
        bundle.putSerializable(LIST_KEY, list);
        send.setArguments(bundle);
        return send;
    }

    public AddSenderInfoFragment() {
    }

    public void setArguments(ArrayList<AtomItem> list) {
        items = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_sender_info, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        rootView.findViewById(R.id.cancel_btn_sender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddNewItemFragment.newInstance())
                        .commit();
            }
        });

        final EditText name = (EditText) rootView.findViewById(R.id.sender_name);
        final EditText address = (EditText) rootView.findViewById(R.id.sender_Address);
        final EditText city = (EditText) rootView.findViewById(R.id.sender_City);
        final EditText zip = (EditText) rootView.findViewById(R.id.sender_Zip);
        final EditText country = (EditText) rootView.findViewById(R.id.sender_Country);

        rootView.findViewById(R.id.sender_next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] sender = {name.getText().toString(), address.getText().toString() + " " + city.getText().toString() + " " + zip.getText().toString() + " " + country.getText().toString()};
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddReceiverInfoFragment.newInstance(items, sender))
                        .commit();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, AddNewItemFragment.newInstance())
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }
}
