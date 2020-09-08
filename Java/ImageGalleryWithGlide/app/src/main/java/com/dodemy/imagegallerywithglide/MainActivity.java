package com.dodemy.imagegallerywithglide;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList imageUrlList = prepareData();
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), imageUrlList);
        recyclerView.setAdapter(dataAdapter);
    }

    private ArrayList prepareData() {

        String imageUrls[] = {
                "https://i.pinimg.com/736x/7a/14/64/7a146463281f6610fcab4a48e71492d7--fancy-cars-cool-cars.jpg",
                "https://i.pinimg.com/564x/32/cd/dc/32cddc2e3d5fc8b41dae8edbebae48ae--google-search-future-car.jpg",
                "https://i.ytimg.com/vi/gp3ZKiwZMvg/hqdefault.jpg",
                "https://i.pinimg.com/736x/27/d5/32/27d532f9037f8dafa44405f516119082--mazda-furai-sportcars.jpg",
                "http://i2.cdn.turner.com/money/dam/assets/140421122255-2014-ny-auto-show-2015-mclaren-650s-1280x720.jpg",
                "http://thenewswheel.com/wp-content/uploads/2017/03/10-Famous-New-Yorkers-with-Incredibly-Cool-Cars-Feature.jpg",
                "http://beverlyhillsmagazine.com/wp-content/uploads/Ferrrari-LaFerrari-1.jpeg",
                "https://i.pinimg.com/736x/51/22/24/512224b7565c4adf4cd66bba6184bf78--most-expensive-expensive-cars.jpg",
                "http://beverlyhillsmagazine.com/wp-content/uploads/Bentley-Aston-Martin-Dream-Cars-Maybach-Doge-Charger-Cool-Cars-Race-Car-Magazine-VIP-Style-cars-Supercars-Beverly-Hills-Magazine-3-e1492551913276.jpg",
                "https://lh6.ggpht.com/78tnPMNVWns7NsmTjO3C9QQz-jRxLIjL1yE8VtJC_Epv24r4AKjW4e14BojEKbcMPCdr%3Dh310",
                "https://s.aolcdn.com/dims-global/dims3/GLOB/legacy_thumbnail/916x515/quality/95/https://s.blogcdn.com/slideshows/images/slides/400/662/5/S4006625/slug/l/01-2017-chevrolet-corvette-gs-fd-1-1.jpg",
                "https://s-media-cache-ak0.pinimg.com/originals/80/69/92/806992aaf90fcb40065a600ae485002a.jpg"};

        ArrayList imageUrlList = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(imageUrls[i]);
            imageUrlList.add(imageUrl);
        }
        Log.d("MainActivity", "List count: " + imageUrlList.size());
        return imageUrlList;
    }
}
