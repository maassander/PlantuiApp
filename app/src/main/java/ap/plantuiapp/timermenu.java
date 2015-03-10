package ap.plantuiapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class timermenu extends ActionBarActivity {

    private String[] monthsArray = { "Mooie plantjes", "Grote plantjes", "Groene plantjes", "Groenere plantjes"};

    private ListView monthsListView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timerMenu);

        monthsListView = (ListView) findViewById(R.id.listView2);
        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
       arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthsArray);
        monthsListView.setAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToTimerActivity(View view) {
        Intent intent = new Intent(timermenu.this, Timer.class);
        startActivity(intent);
    }
}
