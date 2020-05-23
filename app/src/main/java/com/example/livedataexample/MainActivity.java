package com.example.livedataexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Runnable updater;
    MutableLiveData<ArrayList<MyListData>> items = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        items = new MutableLiveData<>();

        final MyListAdapter adapter = new MyListAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        items.observe(this, new Observer<ArrayList<MyListData>>() {
            @Override
            public void onChanged(ArrayList<MyListData> myListData) {
                adapter.setData(myListData);
            }
        });
        addItems();


        /*
        * start updating list items after 3 second on first launch
        * */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                * Update item of list in every half second in the background
                * */
                updateItem(items);
            }
        },3000);
    }

    private void addItems() {
        ArrayList<MyListData> list = new ArrayList<MyListData>();
        for (int i = 0; i < 100; i++) {
            MyListData myListData = new MyListData("Description "+i, R.drawable.ic_verified_user_black_24dp);
            list.add(myListData);
        }
        items.setValue(list);
    }

    void updateItem(final MutableLiveData<ArrayList<MyListData>> list) {
        final Handler timerHandler = new Handler();

        updater = new Runnable() {
            @Override
            public void run() {
                // get the arraylist from the live data for updating
                ArrayList<MyListData> updatedList = items.getValue();

                // get objects from the list to update
                MyListData updateItem = list.getValue().get(0);
                updateItem.setDescription("Description "+new Random().nextInt(80 - 5) + 65);
                updatedList.add(updateItem);
                MyListData updateItem1 = list.getValue().get(1);
                updateItem1.setDescription("Description "+new Random().nextInt(80 - 5) + 65);
                updatedList.add(updateItem1);
                MyListData updateItem2 = list.getValue().get(2);
                updateItem2.setDescription("Description "+new Random().nextInt(80 - 5) + 65);
                updatedList.add(updateItem2);
                MyListData updateItem3 = list.getValue().get(3);
                updateItem3.setDescription("Description "+new Random().nextInt(80 - 5) + 65);
                updatedList.add(updateItem3);
                MyListData updateItem4 = list.getValue().get(4);
                updateItem4.setDescription("Description "+new Random().nextInt(80 - 5) + 65);
                updatedList.add(updateItem4);
                MyListData updateItem5 = list.getValue().get(5);
                updateItem5.setDescription("Description "+new Random().nextInt(80 - 5) + 65);
                updatedList.add(updateItem5);
                MyListData updateItem6 = list.getValue().get(6);
                updateItem6.setDescription("Description "+new Random().nextInt(80 - 5) + 65);
                updatedList.add(updateItem6);
                // update the live data with updated item and set it to your live data
                items.setValue(updatedList);
                // call postdelay for updating in every half second
                timerHandler.postDelayed(updater,500);
            }
        };
        timerHandler.post(updater);
    }
}
