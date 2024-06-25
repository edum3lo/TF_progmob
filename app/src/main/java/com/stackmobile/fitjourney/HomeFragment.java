package com.stackmobile.fitjourney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private ImageView imageRegistro, imageLembrete, imageFotos;
    private TextView textRegistro, textLembrete, textFotos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageRegistro = view.findViewById(R.id.imageRegistro);
        imageLembrete = view.findViewById(R.id.imageLembrete);
        imageFotos = view.findViewById(R.id.imageFotos);

        textRegistro = view.findViewById(R.id.textRegistro);
        textLembrete = view.findViewById(R.id.textLembrete);
        textFotos = view.findViewById(R.id.textFotos);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                int id = v.getId();
                if (id == R.id.imageRegistro || id == R.id.textRegistro) {
                    fragment = new RegistroFragment();
                } else if (id == R.id.imageLembrete || id == R.id.textLembrete) {
                    fragment = new LembretesFragment();
                } else if (id == R.id.imageFotos || id == R.id.textFotos) {
                    fragment = new FotosFragment();
                }
                if (fragment != null) {
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        };

        imageRegistro.setOnClickListener(listener);
        textRegistro.setOnClickListener(listener);
        imageLembrete.setOnClickListener(listener);
        textLembrete.setOnClickListener(listener);
        imageFotos.setOnClickListener(listener);
        textFotos.setOnClickListener(listener);

        return view;
    }
}
