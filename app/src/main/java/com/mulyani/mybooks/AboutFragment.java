package com.mulyani.mybooks;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mulyani.mybooks.View.Admin.InputCerpenActivity;
import com.mulyani.mybooks.View.Autentikasi.AutentikasiActivity;


/**
 * A simple {@link Fragment} subclass.
 */

// Displaying fragment about
public class AboutFragment extends Fragment {


    private FirebaseAuth firebaseAuth;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_about, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        Button tambah = view.findViewById(R.id.btn_tambah);
        tambah.setOnClickListener(v -> startActivity(new Intent(v.getContext(), InputCerpenActivity.class)));

        Button signout = view.findViewById(R.id.btn_signout);
        signout.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getContext(), AutentikasiActivity.class));
        });
        return view;
    }

}
