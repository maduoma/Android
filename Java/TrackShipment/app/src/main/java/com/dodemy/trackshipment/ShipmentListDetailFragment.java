package com.dodemy.trackshipment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dodemy.trackshipment.entity.Item;
import com.dodemy.trackshipment.entity.Shipment;
import com.dodemy.trackshipment.entity.Shipments;
import com.dodemy.trackshipment.http.HttpHandler;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;


import java.util.List;

/**
 * Detail of the shipment.
 */
public class ShipmentListDetailFragment extends Fragment {
    private int position;
    protected ItemsAdapter adapter;
    private View rootView;
    private Shipments shipments;
    private Shipment shipment;

    public static ShipmentListDetailFragment newInstance(int position) {
        ShipmentListDetailFragment detail = new ShipmentListDetailFragment();
        detail.setArguments(position);
        return detail;
    }

    public ShipmentListDetailFragment() {
    }

    public void setArguments(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shipment_detail, container, false);


        shipments = Shipments.getInstance();
        shipment = shipments.getShipmentsList().get(position);

        TextView id = (TextView) rootView.findViewById(R.id.SD_ID);
        id.setText(shipment.getId() + "");

        TextView status = (TextView) rootView.findViewById(R.id.SD_status);
        status.setText(shipment.getStatus());

        TextView created = (TextView) rootView.findViewById(R.id.SD_created_time);
        created.setText(shipment.getStatus_created_time());

        TextView packed = (TextView) rootView.findViewById(R.id.SD_pack_time);
        if (shipment.getStatus_packed_time() == null) packed.setText("-");
        else packed.setText(shipment.getStatus_packed_time());

        TextView sending = (TextView) rootView.findViewById(R.id.SD_sending_time);
        if (shipment.getStatus_sending_time() == null) sending.setText("-");
        else sending.setText(shipment.getStatus_sending_time());

        TextView received = (TextView) rootView.findViewById(R.id.SD_received_time);
        if (shipment.getStatus_received_time() == null) received.setText("-");
        else received.setText(shipment.getStatus_received_time());

        TextView type = (TextView) rootView.findViewById(R.id.SD_type);
        type.setText(shipment.getType());

        TextView courier_name = (TextView) rootView.findViewById(R.id.SD_courier_name);
        courier_name.setText(shipment.getCourier_name());

        TextView courier_address = (TextView) rootView.findViewById(R.id.SD_courier_address);
        courier_address.setText(shipment.getCourier_address());

        TextView receiver_name = (TextView) rootView.findViewById(R.id.SD_receiver_name);
        receiver_name.setText(shipment.getReceive_name());

        TextView receiver_address = (TextView) rootView.findViewById(R.id.SD_receiver_address);
        receiver_address.setText(shipment.getReceive_address());

        TextView total_weight = (TextView) rootView.findViewById(R.id.SD_total_weight);
        total_weight.setText(shipment.getTotal_weight() + "");

        TextView total_cost = (TextView) rootView.findViewById(R.id.SD_total_cost);
        total_cost.setText(shipment.getTotal_cost() + "");


        List<Item> items = shipment.getItem();
        adapter = new ItemsAdapter(getActivity(), R.layout.atom_row_item, items);

        ListView list = (ListView) rootView.findViewById(R.id.listview_items);
        list.setAdapter(adapter);

        Button delete = (Button) rootView.findViewById(R.id.shipment_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                final ShipmentConstant constant = ShipmentConstant.getInstance();
                                final AlertDialog.Builder dDialog = new AlertDialog.Builder(getActivity());

                                new HttpHandler() {
                                    @Override
                                    public HttpUriRequest getHttpRequestMethod() {
                                        HttpDelete delete = new HttpDelete("http://track-trace.tk:8080/shipments/" + shipment.getId());
                                        delete.setHeader("Authorization", constant.getAccessToken());

                                        shipments.addDeleteID(shipment.getId());

                                        return delete;
                                    }

                                    @Override
                                    public void onResponse(String result) {
                                        Log.d("Log Response", result);

                                        dDialog.setTitle("Delete Shipment");
                                        dDialog.setIcon(android.R.drawable.ic_dialog_info);
                                        dDialog.setMessage("Status : deleted");
                                        dDialog.setPositiveButton("Close", null);
                                        dDialog.show();

                                        getFragmentManager().beginTransaction()
                                                .replace(R.id.container, ShipmentListFragment.newInstance())
                                                .commit();
                                    }
                                }.execute();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
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
                            .replace(R.id.container, ShipmentListFragment.newInstance())
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    private class ItemsAdapter extends ArrayAdapter<Item> {
        private View rootView;
        private List<Item> items;
        private Context context;

        public ItemsAdapter(Context context, int resource, List<Item> items) {
            super(context, resource, items);
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            rootView = convertView;

            if (rootView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rootView = inflater.inflate(R.layout.atom_row_item, null);
            }

            Item item = items.get(position);
            if (item != null) {
                TextView name = (TextView) rootView.findViewById(R.id.item_name);
                TextView weight = (TextView) rootView.findViewById(R.id.item_weight);
                TextView quantity = (TextView) rootView.findViewById(R.id.item_qtn);

                if (name != null) {
                    name.setText(item.getName());
                }
                if (weight != null) {
                    weight.setText(String.valueOf(item.getWeight()));
                }
                if (quantity != null) {
                    quantity.setText(String.valueOf(item.getQuantity()));
                }
            }
            return rootView;
        }
    }
}
