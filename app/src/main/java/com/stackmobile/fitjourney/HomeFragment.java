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
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    private ImageView imageRegistro, imageLembrete, imageFotos, imageLocais;
    private TextView textRegistro, textLembrete, textFotos, textLocais;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageRegistro = view.findViewById(R.id.imageRegistro);
        imageLembrete = view.findViewById(R.id.imageLembrete);
        imageFotos = view.findViewById(R.id.imageFotos);
        imageLocais = view.findViewById(R.id.imageLocais);

        textRegistro = view.findViewById(R.id.textRegistro);
        textLembrete = view.findViewById(R.id.textLembrete);
        textFotos = view.findViewById(R.id.textFotos);
        textLocais = view.findViewById(R.id.textLocais);

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
                } else if (id == R.id.imageLocais || id == R.id.textLocais) {
                    // Adicione o fragmento de locais aqui quando estiver pronto
                }
                if (fragment != null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        };

        imageRegistro.setOnClickListener(listener);
        textRegistro.setOnClickListener(listener);

        imageLembrete.setOnClickListener(listener);
        textLembrete.setOnClickListener(listener);

        imageFotos.setOnClickListener(listener);
        textFotos.setOnClickListener(listener);

        imageLocais.setOnClickListener(listener);
        textLocais.setOnClickListener(listener);

        return view;
    }
}
