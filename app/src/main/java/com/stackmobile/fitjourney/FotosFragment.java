package com.stackmobile.fitjourney;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FotosFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSIONS = 100;
    private static final String TAG = "FotosFragment";
    private Button buttonTakePhoto;
    private GridView gridViewGallery;
    private TextView textViewNoPhotos;
    private ArrayList<String> imagePathList;
    private ImageAdapter imageAdapter;
    private String currentPhotoPath;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fotos, container, false);

        buttonTakePhoto = view.findViewById(R.id.buttonTakePhoto);
        gridViewGallery = view.findViewById(R.id.gridViewGallery);
        textViewNoPhotos = view.findViewById(R.id.textViewVisitor);

        imagePathList = new ArrayList<>();
        imageAdapter = new ImageAdapter(getContext(), imagePathList);
        gridViewGallery.setAdapter(imageAdapter);

        buttonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAndRequestPermissions()) {
                    dispatchTakePictureIntent();
                }
            }
        });

        loadImagesFromGallery();

        return view;
    }

    private boolean checkAndRequestPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        int writePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);

        ArrayList<String> listPermissionsNeeded = new ArrayList<>();

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (readPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e(TAG, "Erro ao criar arquivo da foto", ex);
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(), "com.stackmobile.fitjourney.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            galleryAddPic();
            loadImagesFromGallery();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    private void loadImagesFromGallery() {
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File[] imageFiles = storageDir.listFiles();
        imagePathList.clear();

        if (imageFiles != null && imageFiles.length > 0) {
            textViewNoPhotos.setVisibility(View.GONE);
            gridViewGallery.setVisibility(View.VISIBLE);
            for (File imageFile : imageFiles) {
                imagePathList.add(imageFile.getAbsolutePath());
            }
        } else {
            textViewNoPhotos.setVisibility(View.VISIBLE);
            gridViewGallery.setVisibility(View.GONE);
        }

        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(getContext(), "Permissões necessárias não concedidas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
