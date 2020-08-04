package com.dodemy.trackshipment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dodemy.trackshipment.http.HttpHandler;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AddReceiverInfoFragment add receiver's information to the shipment.
 */
public class AddReceiverInfoFragment extends Fragment {
    private static final String LIST_KEY = "list_key";
    private static final String SENDER_KEY = "sender_key";
    private View rootView;
    private ArrayList<AtomItem> items;
    private String[] sender;

    public AddReceiverInfoFragment() {
    }

    public static AddReceiverInfoFragment newInstance(ArrayList<AtomItem> list, String[] sender) {
        AddReceiverInfoFragment receiver = new AddReceiverInfoFragment();
        receiver.setArguments(list, sender);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(LIST_KEY, list);
//        bundle.putSerializable(SENDER_KEY,sender);
//        receiver.setArguments(bundle);
        return receiver;
    }

    public void setArguments(ArrayList<AtomItem> list, String[] sender) {
        this.items = list;
        this.sender = sender;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_receiver_info, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        rootView.findViewById(R.id.cancel_btn_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Shipment is canceled!", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddNewItemFragment.newInstance())
                        .commit();
            }
        });

        final EditText name = (EditText) rootView.findViewById(R.id.receiver_name);
        final EditText address = (EditText) rootView.findViewById(R.id.receiver_Address);
        final EditText city = (EditText) rootView.findViewById(R.id.receiver_City);
        final EditText zip = (EditText) rootView.findViewById(R.id.receiver_Zip);
        final EditText country = (EditText) rootView.findViewById(R.id.receiver_Country);

        final Spinner spin = (Spinner) rootView.findViewById(R.id.spinnerType_created);

        final List<String> type = new ArrayList<String>();
        type.add("common");
        type.add("EMS");

        ArrayAdapter<String> arrAd = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                type);

        spin.setAdapter(arrAd);

        rootView.findViewById(R.id.finish_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] receiver = {name.getText().toString(), address.getText().toString() + " " + city.getText().toString() + " " + zip.getText().toString() + " " + country.getText().toString()};

                final ShipmentConstant shipment = ShipmentConstant.getInstance();
                shipment.createShipmentXML(items, sender, receiver, String.valueOf(spin.getSelectedItem()));

                final AlertDialog.Builder dDialog = new AlertDialog.Builder(getActivity());

                new HttpHandler() {
                    @Override
                    public HttpUriRequest getHttpRequestMethod() {
                        HttpPost httpPost = new HttpPost("http://track-trace.tk:8080/shipments/");
                        httpPost.setHeader("Authorization", shipment.getAccessToken());
                        httpPost.setHeader("Content-Type", "application/xml");

                        try {

                            StringEntity se = new StringEntity(shipment.getXml());
                            httpPost.setEntity(se);

                            Log.d("XMLLLL : ", shipment.getXml());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return httpPost;
                    }

                    @Override
                    public void onResponse(String result) {
                        Log.d("Log Response", result);

                        dDialog.setTitle("Create Shipment");
                        dDialog.setIcon(android.R.drawable.ic_dialog_info);
                        dDialog.setMessage("Status : created");
                        dDialog.setPositiveButton("Close", null);
                        dDialog.show();
                    }
                }.execute();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddNewItemFragment.newInstance())
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
                            .replace(R.id.container, AddSenderInfoFragment.newInstance(items))
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }
}
