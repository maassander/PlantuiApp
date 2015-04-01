package ap.plantuiapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class timermenu extends ActionBarActivity {

    private String[] monthsArray = {};
    private ListView monthsListView;

    private ListAdapter listAdapter;

    private ArrayAdapter arrayAdapter;
    private EditText textboxTime1;
    private EditText textboxTime2;
    private EditText textboxTime3;
    private EditText textboxTime4;
    private String checkTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timermenu);

        monthsListView = (ListView) findViewById(R.id.list_ShowSchedules);
        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        String test = " efzefzef    ";
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthsArray);
        monthsListView.setAdapter(arrayAdapter);


        textboxTime1 = (EditText) findViewById(R.id.textbox_time1);
        textboxTime2 = (EditText) findViewById(R.id.textbox_time2);
        textboxTime3 = (EditText) findViewById(R.id.textbox_time3);
        textboxTime4 = (EditText) findViewById(R.id.textbox_time4);

        findViewById(R.id.button_SetSchedule).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                checkTime = textboxTime1.getText().toString();
                if (!isValidEmail(checkTime)) {
                    textboxTime1.setError("Invalid time, please use format 23:59");
                }
                checkTime = textboxTime2.getText().toString();
                if (!isValidEmail(checkTime)) {
                    textboxTime2.setError("Invalid time, please use format 23:59");
                }
                checkTime = textboxTime3.getText().toString();
                if (!isValidEmail(checkTime)) {
                    textboxTime3.setError("Invalid time, please use format 23:59");
                }
                checkTime = textboxTime4.getText().toString();
                if (!isValidEmail(checkTime)) {
                    textboxTime4.setError("Invalid time, please use format 23:59");
                }


            }
        });
    }

    private boolean isValidEmail(String time) {
        String time_pattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(time_pattern);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
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
}
