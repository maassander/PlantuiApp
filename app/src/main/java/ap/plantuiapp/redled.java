package ap.plantuiapp;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class redled extends ActionBarActivity implements View.OnClickListener {

    private SeekBar SeekbarIntensityRed;
    private TextView IntensityRed;
    Button Save;
     int ValueRed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redled);
        Save = (Button)findViewById(R.id.ButtonSaveRedValue);
        Save.setOnClickListener(this);
        SeekbarIntensityRed = (SeekBar)findViewById(R.id.SeekBarIntensityRed);
        IntensityRed = (TextView)findViewById(R.id.IntensityRedLed);
        SeekbarIntensityRed.setMax(255);

        SeekbarIntensityRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                IntensityRed.setText(progress + "");
                ValueRed = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Save.setOnClickListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_redled, menu);
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
            final AlertDialog.Builder alert = new AlertDialog.Builder(redled.this);
            alert.setMessage("Meer tekst hierin...");
            alert.setTitle("Help");
            alert.setIcon(R.drawable.help);
            alert.create();
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.ButtonSaveRedValue:
                SaveValue();
                break;
        }
    }
    public void SaveValue()
    {
        //try catch for exception handling
        FileOutputStream fosred = null;
        try {

            fosred = openFileOutput("ValueRed.txt", Context.MODE_PRIVATE);
            fosred.write(ValueRed);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //not caring if there is an exception or not this is running anyway.
            if(fosred!= null){
                try {
                    fosred.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}

    }
}
