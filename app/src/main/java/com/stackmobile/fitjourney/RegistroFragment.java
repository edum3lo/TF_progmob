package com.stackmobile.fitjourney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegistroFragment extends Fragment {

    private EditText editTextActivity, editTextWater, editTextSleep, editTextMeals;
    private Button buttonSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        editTextActivity = view.findViewById(R.id.editTextActivity);
        editTextWater = view.findViewById(R.id.editTextWater);
        editTextSleep = view.findViewById(R.id.editTextSleep);
        editTextMeals = view.findViewById(R.id.editTextMeals);
        buttonSave = view.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activity = editTextActivity.getText().toString();
                String water = editTextWater.getText().toString();
                String sleep = editTextSleep.getText().toString();
                String meals = editTextMeals.getText().toString();

                if (activity.isEmpty() || water.isEmpty() || sleep.isEmpty() || meals.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Salvar os dados no banco de dados ou enviar para o servidor
                    Toast.makeText(getActivity(), "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
