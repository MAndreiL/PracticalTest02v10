package ro.pub.cs.systems.eim.practicaltest02v10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;


public class PracticalTest02v10 extends AppCompatActivity {

    // Declarați elementele UI
    private EditText nameEditText;
    private Button getButton;
    private ImageView pokemonIcon;
    private TextView abilitiesTextView;
    private TextView typesTextView;
    private Button nextButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activează EdgeToEdge
        EdgeToEdge.enable(this);
        // Setează layout-ul personalizat
        setContentView(R.layout.activity_practical_test02v10_main);

        // Adaugă padding pentru sistemul de bare
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inițializare elemente UI
        nameEditText = findViewById(R.id.name);
        getButton = findViewById(R.id.get);
        pokemonIcon = findViewById(R.id.pokemonIcon);
        abilitiesTextView = findViewById(R.id.abilities);
        typesTextView = findViewById(R.id.types);
        nextButton = findViewById(R.id.next);

        // Configurare acțiuni pentru butoane
        configureGetButton();
        configureNextButton();
    }

    private void configureGetButton() {
        getButton.setOnClickListener(view -> {
            // Preia numele pokemonului
            String pokemonName = nameEditText.getText().toString().trim();
            if (pokemonName.isEmpty()) {
                nameEditText.setError("Please enter a Pokemon name!");
                return;
            }

            // Trimitere cerere către server
            new Thread(() -> {
                String jsonResponse = PokemonApiClient.getPokemonData(pokemonName);
                // Logăm răspunsul pentru a vedea ce primim de la server
                Log.d("PokemonInfo", "Response: " + jsonResponse);
                if (jsonResponse.startsWith("Error")) {
                    runOnUiThread(() -> Toast.makeText(this, jsonResponse, Toast.LENGTH_SHORT).show());
                } else {
                    Pokemon pokemon = Pokemon.fromJson(jsonResponse);
                    if (pokemon != null) {
                        // Logăm datele parsează pentru a verifica corectitudinea acestora
                        Log.d("PokemonInfo", "Parsed Pokemon: " + pokemon.getName() + ", " + pokemon.getAbilities() + ", " + pokemon.getTypes());
                        runOnUiThread(() -> {
                            abilitiesTextView.setText("Abilities: " + pokemon.getAbilities());
                            typesTextView.setText("Types: " + pokemon.getTypes());
                            Glide.with(this)
                                    .load(pokemon.getImageUrl())
                                    .into(pokemonIcon);
                        });
                    } else {
                        runOnUiThread(() -> Toast.makeText(this, "Error parsing data", Toast.LENGTH_SHORT).show());
                    }
                }
            }).start();
        });
    }

    private void configureNextButton() {
        nextButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SubscribeActivity.class);
            startActivity(intent);
        });
    }
}
