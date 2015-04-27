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


public class greenled extends ActionBarActivity implements View.OnClickListener{
    SeekBar SeekbarIntensityGreen;
    Button Save;
    TextView IntensityGreen;
    int ValueGreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenled);
        Save = (Button) findViewById(R.id.ButtonSaveGreenValue);
        Save.setOnClickListener(this);
        SeekbarIntensityGreen = (SeekBar)findViewById(R.id.SeekBarIntensityGreen);
        IntensityGreen = (TextView)findViewById(R.id.IntensityGreenLed);
        SeekbarIntensityGreen.setMax(255);

        SeekbarIntensityGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                IntensityGreen.setText(progress + "");
                ValueGreen = progress;
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
        getMenuInflater().inflate(R.menu.menu_greenled, menu);
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
            final AlertDialog.Builder alert = new AlertDialog.Builder(greenled.this);
            alert.setMessage("Need help at led screen?\n" +
                    "\n" +
                    "What can you see from led screen?\n" +
                    "\t- Visual values of lights\n" +
                    "\n" +
                    "What can you set up from led screen?\n" +
                    "\t- Set the led light intensity from 0% to 100% (0 shutdown, 255 at max power)\n" +
                    "\n" +
                    "Still having problems?\n" +
                    "Go to plantui.com/support/application/led\n" +
                    "\n" +
                    "Or send feedback or support request directly to us from\n" +
                    "plantui.com/support");
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
            case R.id.ButtonSaveGreenValue:
                SaveGreenValue();
                break;
        }
    }
    public void SaveGreenValue()
    {
        FileOutputStream fosgreen = null;
        try {
            fosgreen = openFileOutput("ValueGreen.txt", Context.MODE_PRIVATE);
            fosgreen.write(ValueGreen);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fosgreen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
