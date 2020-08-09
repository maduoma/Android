package com.dodemy.trackshipment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * AddNewItemFragment create new Item to the shipment.
 *
 * @author
 */
public class AddNewItemFragment extends Fragment {
    protected AtomItemListAdapter adapter;
    private View rootView;
    private ListView atomItemsListView;

    public static AddNewItemFragment newInstance() {
        AddNewItemFragment addNew = new AddNewItemFragment();
        return addNew;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_list_view, container, false);

        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupListViewAdapter();

        setupAddItemButton();

        return rootView;
    }

    private void setupListViewAdapter() {
        adapter = new AtomItemListAdapter(getActivity(), R.layout.atom_create_list_item, new ArrayList<AtomItem>());
        atomItemsListView = (ListView) rootView.findViewById(R.id.EnterItems_atomItemsList);
        atomItemsListView.setAdapter(adapter);
    }

    private void setupAddItemButton() {
        rootView.findViewById(R.id.EnterItems_addAtomItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.insert(new AtomItem("", 0, 0), 0);
            }
        });

        final FragmentManager fragmentManager = getFragmentManager();

        final Button next = (Button) rootView.findViewById(R.id.Next_addAtomItem);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<AtomItem> items = new ArrayList<AtomItem>();
                for (int i = 0; i < atomItemsListView.getCount(); i++) {
                    items.add(adapter.getItem(i));
                }
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddSenderInfoFragment.newInstance(items))
                        .commit();
            }
        });
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


    private class AtomItemListAdapter extends ArrayAdapter<AtomItem> {

        protected final String LOG_TAG = AtomItemListAdapter.class.getSimpleName();

        private List<AtomItem> items;
        private int layoutResourceId;
        private Context context;
        private AtomCreateHolder holder;

        public AtomItemListAdapter(Context context, int layoutResourceId, List<AtomItem> items) {
            super(context, layoutResourceId, items);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new AtomCreateHolder();
            holder.atomItem = items.get(position);
            holder.removeItemButton = (ImageButton) row.findViewById(R.id.atomItem_removePay);
            holder.removeItemButton.setTag(holder.atomItem);
            holder.removeItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AtomItem itemToRemove = (AtomItem) v.getTag();
                    adapter.remove(itemToRemove);
                }
            });

            holder.name = (TextView) row.findViewById(R.id.atomItem_name);
            setNameTextChangeListener(holder);
            holder.weight = (TextView) row.findViewById(R.id.atomItem_weight);
            setWeightTextListeners(holder);
            holder.qtn = (TextView) row.findViewById(R.id.atomItem_qtn);
            setQtnTextListeners(holder);


            row.setTag(holder);

            setupItem(holder);

            return row;
        }

        private void setupItem(final AtomCreateHolder holder) {
            holder.name.setText(holder.atomItem.getName());
            holder.weight.setText(String.valueOf(holder.atomItem.getWeight()));
            holder.qtn.setText(String.valueOf(holder.atomItem.getQuantity()));
        }

        public class AtomCreateHolder {
            AtomItem atomItem;
            TextView name;
            TextView weight;
            TextView qtn;
            ImageButton removeItemButton;
        }

        private void setNameTextChangeListener(final AtomCreateHolder holder) {
            holder.name.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    holder.atomItem.setName(s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        private void setWeightTextListeners(final AtomCreateHolder holder) {
            holder.weight.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        holder.atomItem.setWeight(Double.parseDouble(s.toString()));
                    } catch (NumberFormatException e) {
                        Log.e(LOG_TAG, "error reading double value: " + s.toString());
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        private void setQtnTextListeners(final AtomCreateHolder holder) {
            holder.qtn.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        holder.atomItem.setQuantity(Integer.parseInt(s.toString()));
                    } catch (NumberFormatException e) {
                        Log.e(LOG_TAG, "error reading int value: " + s.toString());
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

    }
}
