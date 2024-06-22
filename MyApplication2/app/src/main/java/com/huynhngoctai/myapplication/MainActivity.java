package com.huynhngoctai.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.huynhngoctai.myapplication.databinding.ActivityMainBinding;

import org.mariadb.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private DbClass dbClass;
    private Connection conn;
    private ResultSet resultSet;
    private String str;
    private List<Data> data;
    private Adt dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        data = new ArrayList<>();
        dataAdapter = new Adt(this, data);
        binding.listView.setAdapter(dataAdapter);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.connect.setOnClickListener(this);
        binding.showData.setOnClickListener(this);
        dbClass = new DbClass();
        binding.connect.setVisibility(View.VISIBLE);
        binding.listView.setVisibility(View.GONE);
        binding.showData.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.connect) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                boolean success = false;
                try {
                    conn = (Connection) dbClass.Connect();
                    if (conn == null) {
                        str = "Connect Database Failed!";
                        Log.e("SUCCESS", "Success 2");
                    } else {
                        str = "Connect Database Success!";
                        Log.e("ERROR", str + "2");
                        success = true;
                    }
                } catch (Exception e) {
                    str = e.getMessage();
                    Log.e("ERROR", e.getMessage() + "3");
                }
                boolean finalSuccess = success;

                runOnUiThread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        str = e.getMessage();
                        Log.e("ERROR", str + "4");
                    }
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    if (finalSuccess) {
                        binding.connect.setVisibility(View.GONE);
                        binding.listView.setVisibility(View.VISIBLE);
                        binding.showData.setVisibility(View.VISIBLE);
                    }
                });
            });
        } else if (v.getId() == R.id.showData) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                try {
                    conn = (Connection) dbClass.Connect();
                    if (conn != null) {
                        PreparedStatement stm = conn.prepareStatement("SELECT * FROM b8_27_eb_ed_f1_a5");
                        resultSet = stm.executeQuery();
                        data.clear();
                        while (resultSet.next()) {
                            Data model = new Data(
                                    resultSet.getInt("ID"),
                                    resultSet.getString("DateTime"),
                                    resultSet.getLong("Voltage"),
                                    resultSet.getLong("Current"),
                                    resultSet.getLong("Power"),
                                    resultSet.getLong("Energy"),
                                    resultSet.getLong("Frequency"),
                                    resultSet.getLong("PowerFactor")
                            );
                            data.add(model);
                        }
                        str = "Data fetched successfully";
                    } else {
                        str = "Failed to connect to the database.";
                    }
                } catch (Exception e) {
                    str = "Error fetching data: " + e.getMessage();
                    Log.e("ERROR", str, e);
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        Log.e("ERROR", "Error closing database resources", e);
                    }
                }

                runOnUiThread(() -> {
                    dataAdapter.notifyDataSetChanged();
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                });
            });
        }
    }
}