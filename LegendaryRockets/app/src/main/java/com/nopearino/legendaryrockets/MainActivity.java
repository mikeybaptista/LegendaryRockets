package com.nopearino.legendaryrockets;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nopearino.legendaryrockets.helper.DatabaseHelper;
import com.nopearino.legendaryrockets.model.Rockets;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    // Database Helper
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());

        // Criar rockets

        if(db.obterRockets().size()==0) {
            Rockets rocket1 = db.criarRockets("Badaboom", "Bandit");
            Rockets rocket2 = db.criarRockets("Bunny", "Tediore");
            Rockets rocket3 = db.criarRockets("Mongol", "Vladof");
            Rockets rocket4 = db.criarRockets("Norfleet", "Maliwan");
            Rockets rocket5 = db.criarRockets("Nukem", "Torgue");
            Rockets rocket6 = db.criarRockets("Pyrophobia", "Maliwan");
        }

        Log.e("Contagem de rockets", "Nº de rockets: " + db.obterRockets().size());

        // Obter todos os rockets
        Log.d("Obter artigos", "Obter todos os artigos");
        List<Rockets> rockets = db.obterRockets();
        for (Rockets rocket : rockets) {
            Log.d("Rocket", rocket.getNome());
        }

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        RocketAdapter adapter = new RocketAdapter(rockets,this);
        rv.setAdapter(adapter);

        // Fechar a conexão à base de dados
        db.fecharBD();
    }
}
