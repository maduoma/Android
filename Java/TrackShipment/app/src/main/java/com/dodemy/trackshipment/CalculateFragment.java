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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dodemy.trackshipment.http.HttpHandler;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CalculateFragment checks shipment's cost.
 */
public class CalculateFragment extends Fragment {
    private View rootView;
    private ArrayList<AtomItem> items;
    protected CalculateAdapter adapter;
    protected ListView list;
    private int count = 0;

    public static CalculateFragment newInstance() {
        CalculateFragment calculate = new CalculateFragment();
        return calculate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_calculate, container,
                false);

        items = new ArrayList<AtomItem>();

        adapter = new CalculateAdapter(getActivity(), R.layout.atom_calculate_list_item, items);

        list = (ListView) rootView.findViewById(R.id.cal_listView);
        list.setAdapter(adapter);

        final EditText weight = (EditText) rootView.findViewById(R.id.weight_edit_cal);
        final EditText qtn = (EditText) rootView.findViewById(R.id.qtn_edit_cal);
        final AlertDialog.Builder dDialog = new AlertDialog.Builder(getActivity());
        final Spinner spin = (Spinner) rootView.findViewById(R.id.spinnerType);

        final List<String> type = new ArrayList<String>();
        type.add("common");
        type.add("EMS");

        ArrayAdapter<String> arrAd = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                type);

        spin.setAdapter(arrAd);

        Button btn = (Button) rootView.findViewById(R.id.add_item_btn_cal);
        btn.setOnClickListener(new View.OnClickListener() {
            double w;
            int q;

            @Override
            public void onClick(View v) {
                count = list.getCount() + 1;
                w = Double.parseDouble(weight.getText().toString());
                q = Integer.parseInt(qtn.getText().toString());
                adapter.insert(new AtomItem(String.valueOf(count), w, q), 0);

            }
        });

        Button cal_btn = (Button) rootView.findViewById(R.id.calculate_cal_btn);
        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<AtomItem> items = new ArrayList<AtomItem>();
                for (int i = 0; i < list.getCount(); i++) {
                    items.add(adapter.getItem(i));
                }
                String t = String.valueOf(spin.getSelectedItem());

                final ShipmentConstant shipment = ShipmentConstant.getInstance();
                shipment.calculateXML(items, t);

                new HttpHandler() {
                    @Override
                    public HttpUriRequest getHttpRequestMethod() {
                        HttpPost httpPost = new HttpPost("http://track-trace.tk:8080/shipments/calculate");
                        //httpPost.setHeader("Authorization",shipment.getAccessToken());
                        httpPost.setHeader("Content-Type", "application/xml");
                        httpPost.setHeader("Accept", "application/xml");

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
                        String cost, weight;
                        TextView w = (TextView) rootView.findViewById(R.id.cal_total_weight);
                        TextView c = (TextView) rootView.findViewById(R.id.cal_total_cost);

                        if (result.contains("<total_weight>") && result.contains("<total_cost>")) {
                            weight = result.substring(result.indexOf("<total_weight>") + 14, result.indexOf("</total_weight>"));
                            cost = result.substring(result.indexOf("<total_cost>") + 12, result.indexOf("</total_cost>"));

                            w.setText(weight);
                            c.setText(cost);

                        }
                    }
                }.execute();

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
                            .replace(R.id.container, MainActivity.PlaceholderFragment.newInstance(0))
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    private class CalculateAdapter extends ArrayAdapter<AtomItem> {

        private List<AtomItem> items;
        private Context context;

        public CalculateAdapter(Context context, int resource, List<AtomItem> items) {
            super(context, resource, items);
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.atom_calculate_list_item, null);
            }

            AtomItem item = items.get(position);
            if (item != null) {
                TextView name = (TextView) view.findViewById(R.id.name_cal);
                TextView weight = (TextView) view.findViewById(R.id.weight_cal);
                TextView quantity = (TextView) view.findViewById(R.id.qtn_cal);
                ImageButton btn = (ImageButton) view.findViewById(R.id.remove_cal);
                btn.setTag(item);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AtomItem itemToRemove = (AtomItem) v.getTag();
                        adapter.remove(itemToRemove);

                        count = list.getCount();
                        for (int i = 0; i < list.getCount(); ++i) {
                            adapter.getItem(i).setName(String.valueOf(count));
                            count--;
                        }
                    }
                });

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
            return view;
        }
    }
}
