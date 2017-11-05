package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    ArrayList<String> contacts = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("Wattsapp - Contacts");

        ListView userListView = (ListView) findViewById(R.id.contacts);

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("username", contacts.get(i));
                startActivity(intent);
            }
        });

        contacts.clear();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
        userListView.setAdapter(arrayAdapter);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                // must not throw an exception here
                if (e == null) {
                    // Do nothing if null is result of query
                    if (objects.size() > 0) {
                        for (ParseUser user : objects) {
                            contacts.add(user.getUsername());
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }
}
