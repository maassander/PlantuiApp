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


public class blueled extends ActionBarActivity implements View.OnClickListener{

    SeekBar SeekbarIntensityBlue;
    Button Save;
    TextView IntensityBlue;
    int ValueBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blueled);
        Save = (Button)findViewById(R.id.ButtonSaveBlueValue);
        Save.setOnClickListener(this);
        SeekbarIntensityBlue = (SeekBar)findViewById(R.id.SeekBarIntensityBlue);
        IntensityBlue = (TextView)findViewById(R.id.IntensityBlueLed);
        SeekbarIntensityBlue.setMax(255);
        SeekbarIntensityBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                IntensityBlue.setText(progress + "");
                ValueBlue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blueled, menu);
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
            final AlertDialog.Builder alert = new AlertDialog.Builder(blueled.this);
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
        switch (v.getId())
        {
            case R.id.ButtonSaveBlueValue:
                SaveBlueValue();
                break;
        }
    }
    public void SaveBlueValue()
    {
        FileOutputStream fosblue= null;
        try {
            fosblue = openFileOutput("ValueBlue.txt", Context.MODE_PRIVATE);
            fosblue.write(ValueBlue);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fosblue.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
