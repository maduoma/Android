package com.dodemy.cryptocoinmarket;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.cryptocoinmarket.retrofit.info.Info;
import com.dodemy.cryptocoinmarket.retrofit.listings.CryptoList;
import com.dodemy.cryptocoinmarket.retrofit.listings.Datum;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;
    private APIInterface apiInterface;
    private List<Datum> cryptoList = null;


    /* The IDE does not know what potential effects your subscription can have when it's not disposed, so it treats it as potentially unsafe.
       For example, your Single may contain a network call, which could cause a memory leak if your Activity is abandoned during its execution.
       A convenient way to manage a large amount of Disposables is to use a CompositeDisposable;
       just create a new CompositeDisposable instance variable in your enclosing class, then add all your Disposables to the CompositeDisposable
    */
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        getCoinList();
    }


    private void initRecyclerView() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        // Lookup the recyclerview in activity layout
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        // Initialize data
        cryptoList = new ArrayList<>();
        // Create adapter passing in the sample user data
        adapter = new RecyclerViewAdapter(cryptoList);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("XXXX", "You clicked " + adapter.getItem(position).getName() + " on nr " + position);
                //Toast.makeText(MainActivity.this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CoinPageActivity.class);
                intent.putExtra("coin", adapter.getItem(position));
                intent.putExtra("icon", adapter.getCryptoListIcons().get(adapter.getItem(position).getSymbol()).getLogo());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d("XXXX", "You LONG clicked " + adapter.getItem(position).getName() + " on nr " + position);
                Toast.makeText(MainActivity.this, "You LONG clicked " + adapter.getItem(position).getName() + " on nr " + position, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getCoinList() {
        //IDE is satisfied that the Disposable is being managed.
        compositeDisposable.add(apiInterface.getMarketPairsLatest("100")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CryptoList>() {
                    @Override
                    public void onSuccess(CryptoList list) {
                        cryptoList.clear();
                        cryptoList.addAll(list.getData());
                        updateLogoList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                        Log.d("XXXX", e.getLocalizedMessage());
                    }
                }));
    }

    private void updateLogoList() {
        String SEPARATOR = ",";
        StringBuilder csvBuilder = new StringBuilder();
        for (Datum datumx : cryptoList) {
            csvBuilder.append(datumx.getSymbol());
            csvBuilder.append(SEPARATOR);
        }
        String csv = csvBuilder.toString();
        csv = csv.substring(0, csv.length() - SEPARATOR.length()); //Remove last comma

        final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        compositeDisposable.add(apiInterface.getCryptocurrencyInfo(csv)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Info>() {
                    @Override
                    public void onSuccess(Info coinInfo) {
                        adapter.getCryptoListIcons().clear();
                        adapter.getCryptoListIcons().putAll(coinInfo.getData());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "getCryptocurrencyInfo onFailure", Toast.LENGTH_SHORT).show();
                        Log.d("XXXX", e.getLocalizedMessage());
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Placed wherever we'd like to dispose our Disposables (i.e. in onDestroy()).
        compositeDisposable.dispose();
    }
}
