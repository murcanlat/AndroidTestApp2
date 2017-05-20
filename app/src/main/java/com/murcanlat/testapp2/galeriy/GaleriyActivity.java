package com.murcanlat.testapp2.galeriy;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.murcanlat.testapp2.R;
import com.murcanlat.testapp2.camera.AndroidCameraApi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 010574 on 20.05.2017.
 */

public class GaleriyActivity extends AppCompatActivity {

    private final String image_titles[] = {
        "img1"
    };

    private final Integer image_ids[] = {
            R.drawable.img1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galeriy_view1);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<CreateList> createLists = prepareData();
        MyAdapter adapter = new MyAdapter(getApplicationContext(), createLists);
        recyclerView.setAdapter(adapter);


    }
    private ArrayList<CreateList> prepareData(){

        String path = Environment.getExternalStorageDirectory().getPath()      +
                AndroidCameraApi.TEST_APP2;
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();

        ArrayList<CreateList> theimage = new ArrayList<>();
        try {
        for(int i = 0; i< files.length; i++){
            CreateList createList = new CreateList();
            createList.setImage_title(files[i].getCanonicalPath());
            createList.setImageFile(files[i]);
            theimage.add(createList);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return theimage;
    }
}