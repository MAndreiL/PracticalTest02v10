package ro.pub.cs.systems.eim.practicaltest02v10;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SubscribeActivity extends AppCompatActivity {

    private EditText topicNameEditText;
    private Button subscribeButton;
    private Button unsubscribeButton;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        // Inițializează elementele UI
        topicNameEditText = findViewById(R.id.topicNameEditText);
        subscribeButton = findViewById(R.id.subscribeButton);
        unsubscribeButton = findViewById(R.id.unsubscribeButton);
        statusTextView = findViewById(R.id.statusTextView);

        // Setează comportamentul butoanelor (poți completa funcționalitatea mai târziu)
        subscribeButton.setOnClickListener(v -> statusTextView.setText("Subscribed to: " + topicNameEditText.getText().toString()));
        unsubscribeButton.setOnClickListener(v -> statusTextView.setText("Unsubscribed from: " + topicNameEditText.getText().toString()));
    }
}