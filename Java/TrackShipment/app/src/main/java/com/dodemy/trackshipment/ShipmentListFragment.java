package com.dodemy.trackshipment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dodemy.trackshipment.entity.Item;
import com.dodemy.trackshipment.entity.Shipment;
import com.dodemy.trackshipment.entity.Shipments;
import com.dodemy.trackshipment.http.HttpHandler;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all shipments
 */
public class ShipmentListFragment extends Fragment {
    private View rootView;
    private Shipments shipments;
    private ListView list;
    private DataListAdapter adapter;

    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;

    enum Direction {LEFT, RIGHT;}

    public static ShipmentListFragment newInstance() {
        ShipmentListFragment list = new ShipmentListFragment();

        return list;
    }

    public ShipmentListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shipment_list, container,
                false);
        final ShipmentListFragment slf = this;

        shipments = Shipments.getInstance();

        new HttpHandler() {
            @Override
            public HttpUriRequest getHttpRequestMethod() {
                ShipmentConstant shipment = ShipmentConstant.getInstance();

                HttpGet httpGet = new HttpGet("http://track-trace.tk:8080/shipments");

                httpGet.setHeader("Authorization", shipment.getAccessToken());
                httpGet.setHeader("Accept", "application/xml");
                httpGet.setHeader("Cache-Control", "no-cache");
                return httpGet;
            }

            @Override
            public void onResponse(String result) {
                Log.d("Log Response", result);

                if (result.contains("Not Found")) {
                    nofify("Not Found");
                } else if (result.contains("Unauthorized")) {
                    nofify("Please LOG IN");
                } else if (result.contains("shipments")) {
                    shipments.setShipmentsList(new ArrayList<Shipment>());
                    String splits[] = result.split("(?=<)|(?<=>)");
                    String t = "";
                    Shipment shipment = null;
                    Item item = null;
                    for (int i = 0; i < splits.length; i++) {
                        t += splits[i] + "\n";
                        if (splits[i].contains("<shipment") && !splits[i].contains("shipments")) {
                            shipment = new Shipment();
                            String[] txt = splits[i].split("id=");
                            shipment.setId(Integer.parseInt(txt[1].substring(1, txt[1].length() - 2)));
                        } else if (splits[i].contains("<status>")) {
                            shipment.setStatus(splits[i + 1]);
                        } else if (splits[i].contains("<status_created_time>")) {
                            shipment.setStatus_created_time(splits[i + 1]);
                        } else if (splits[i].contains("<status_packed_time>")) {
                            shipment.setStatus_packed_time(splits[i + 1]);
                        } else if (splits[i].contains("<status_sending_time>")) {
                            shipment.setStatus_sending_time(splits[i + 1]);
                        } else if (splits[i].contains("<status_received_time>")) {
                            shipment.setStatus_received_time(splits[i + 1]);
                        } else if (splits[i].contains("<type>")) {
                            shipment.setType(splits[i + 1]);
                        } else if (splits[i].contains("<courier_name>")) {
                            shipment.setCourier_name(splits[i + 1]);
                        } else if (splits[i].contains("<courier_address>")) {
                            shipment.setCourier_address(splits[i + 1]);
                        } else if (splits[i].contains("<receive_name>")) {
                            shipment.setReceive_name(splits[i + 1]);
                        } else if (splits[i].contains("<receive_address>")) {
                            shipment.setReceive_address(splits[i + 1]);
                        } else if (splits[i].contains("<total_weight>")) {
                            shipment.setTotal_weight(Float.parseFloat(splits[i + 1]));
                        } else if (splits[i].contains("<total_cost>")) {
                            shipment.setTotal_cost(Float.parseFloat(splits[i + 1]));
                        } else if (splits[i].contains("<item") && !splits[i].contains("<items")) {
                            item = new Item();
                            String[] txt = splits[i].split("id=");
                            item.setId(Long.parseLong(txt[1].substring(1, txt[1].length() - 2)));
                        } else if (splits[i].contains("<name>")) {
                            item.setName(splits[i + 1]);
                        } else if (splits[i].contains("<weight")) {
                            item.setWeight(Float.parseFloat(splits[i + 1]));
                        } else if (splits[i].contains("<quantity")) {
                            item.setQuantity(Integer.parseInt(splits[i + 1]));
                        } else if (splits[i].contains("</item")) {
                            shipment.addItem(item);
                        } else if (splits[i].contains("</shipment>")) {
                            shipments.addShipment(shipment);
                        } else if (splits[i].contains("</shipments>")) {
                            list = (ListView) rootView.findViewById(R.id.listview_shipments);

                            ArrayList<String> sId = new ArrayList<String>();
                            ArrayList<String> sStatus = new ArrayList<String>();

                            List<Long> deleteId = shipments.getDeleteID();
                            List<Shipment> slist = new ArrayList<Shipment>();

                            for (Shipment s : shipments.getShipmentsList()) {
                                slist.add(s);
                            }

                            for (Shipment s : slist) {

                                if (!deleteId.contains(s.getId())) {
                                    sId.add(s.getId() + "");
                                    sStatus.add(s.getStatus());
                                } else {
                                    shipments.getShipmentsList().remove(s);
                                }

                            }
                            String[] shipment_id = sId.toArray(new String[sId.size()]);
                            String[] shipment_status = sStatus.toArray(new String[sStatus.size()]);

                            adapter = new DataListAdapter(slf.getActivity(), shipment_id, shipment_status);
                            list.setAdapter(adapter);
                        }
                    }
//                    Log.d("result ; ",t);

                }

            }
        }.execute();

        return rootView;
    }

    protected boolean onLongListItemClick(View v, int pos, long id) {
        Log.i("OnLong", "onLongListItemClick id=" + pos);
        return true;
    }


    public void nofify(String message) {
        final AlertDialog.Builder dDialog = new AlertDialog.Builder(this.getActivity());
        dDialog.setTitle("WARNING");
        dDialog.setIcon(android.R.drawable.btn_star_big_on);
        dDialog.setMessage(message);
        dDialog.setPositiveButton("Close", null);
        dDialog.show();
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
                            .replace(R.id.container, MainActivity.PlaceholderFragment.newInstance(0))
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    private class DataListAdapter extends BaseAdapter {
        String[] ID, status;
        Context context;

        DataListAdapter() {
            ID = null;
            status = null;
        }

        public DataListAdapter(Context context, String[] text, String[] text1) {
            ID = text;
            status = text1;
            this.context = context;
        }

        public int getCount() {
            return ID.length;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View row = convertView;
            final FragmentManager fragmentManager = getFragmentManager();

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.atom_row_shipment, null);
            }

            TextView id, status;

            id = (TextView) row.findViewById(R.id.shipment_id);
            status = (TextView) row.findViewById(R.id.shipment_status);
            id.setText("Shipment's ID : " + ID[position]);
            status.setText("Status : " + this.status[position]);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, ShipmentListDetailFragment.newInstance(position))
                            .commit();
                }
            });

            return (row);
        }
    }
}


