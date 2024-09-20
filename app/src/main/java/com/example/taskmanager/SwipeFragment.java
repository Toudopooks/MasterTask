package com.example.taskmanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SwipeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla la vista para el fragmento (usa un layout con fondo azul, por ejemplo)
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Añadir el gesto de deslizar hacia arriba
        view.setOnTouchListener(new View.OnTouchListener() {
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Store the Y position where the touch started
                        startY = event.getY();
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Get the Y position where the touch ended
                        float endY = event.getY();

                        // Detect if it's a swipe up gesture (startY > endY)
                        if (startY - endY > 200) {  // Adjust this value for sensitivity
                            // The user swiped up, now animate the fragment upwards
                            v.animate()
                                    .translationY(-v.getHeight())  // Move the fragment up by its height
                                    .setDuration(300)  // Animation duration in milliseconds
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            // After animation ends, remove the fragment
                                            requireActivity().getSupportFragmentManager().beginTransaction()
                                                    .remove(SwipeFragment.this)
                                                    .commit();
                                        }
                                    })
                                    .start();
                        }
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}