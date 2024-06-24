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

        // Temporarily disable intents to avoid ActivityNotFoundException
        /*
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (v.getId()) {
                    case R.id.imageRegistro:
                    case R.id.textRegistro:
                        intent = new Intent(getActivity(), RegistroActivity.class);
                        break;
                    case R.id.imageLembrete:
                    case R.id.textLembrete:
                        intent = new Intent(getActivity(), LembretesActivity.class);
                        break;
                    case R.id.imageFotos:
                    case R.id.textFotos:
                        intent = new Intent(getActivity(), FotosActivity.class);
                        break;
                    case R.id.imageLocais:
                    case R.id.textLocais:
                        intent = new Intent(getActivity(), LocaisActivity.class);
                        break;
                }
                if (intent != null) {
                    startActivity(intent);
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
        */

        return view;
    }
}
