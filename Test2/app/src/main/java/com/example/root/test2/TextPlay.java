package com.example.root.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_play);
        Button enter = (Button) findViewById(R.id.button);
        final TextView display = (TextView) findViewById(R.id.textView);
        final EditText input = (EditText) findViewById(R.id.editText);
        final ToggleButton tg = (ToggleButton) findViewById(R.id.toggleButton);
        tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tg.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = input.getText().toString();
                display.setText(check);
                if(check.contentEquals("left")){
                    display.setGravity(Gravity.LEFT);
                }else if(check.contentEquals("center")){
                    display.setGravity((Gravity.CENTER));
                }else if(check.contentEquals("right")){
                    display.setGravity((Gravity.RIGHT));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_play, menu);
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
