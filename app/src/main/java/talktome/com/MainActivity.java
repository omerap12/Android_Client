package talktome.com;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // login button handler
        Button btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(v -> {
            //need to validate the user name + password
            Intent i = new Intent(this, ContactsChatActivity.class);
            startActivity(i);
        });
    }
}