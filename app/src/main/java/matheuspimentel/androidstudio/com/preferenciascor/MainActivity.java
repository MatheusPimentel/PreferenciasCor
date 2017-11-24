package matheuspimentel.androidstudio.com.preferenciascor;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RelativeLayout relativeLayout;
    private Button button;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        relativeLayout = findViewById(R.id.relativeLayout);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idRadioEscolhido = radioGroup.getCheckedRadioButtonId();

                if (idRadioEscolhido > 0) {
                    radioButton = findViewById(idRadioEscolhido);
                    String cor = radioButton.getText().toString();

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("cor", cor);
                    editor.commit();

                    montarCor(cor);
                } else {
                    Toast.makeText(MainActivity.this, "Escolha uma opção", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //recuperar os dados
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (sharedPreferences.contains("cor")) {
            String corRecuperada = sharedPreferences.getString("cor", "azul");
            montarCor(corRecuperada);
        }
    }

    private void montarCor (String cor) {

        switch (cor) {
            case "azul":
                radioButton = findViewById(R.id.azul);
                relativeLayout.setBackgroundColor(radioButton.getCurrentTextColor());
                break;
            case "laranja":
                radioButton = findViewById(R.id.laranja);
                relativeLayout.setBackgroundColor(radioButton.getCurrentTextColor());
                break;
            case "vermelho":
                radioButton = findViewById(R.id.vermelho);
                relativeLayout.setBackgroundColor(radioButton.getCurrentTextColor());
                break;
        }
    }
}
