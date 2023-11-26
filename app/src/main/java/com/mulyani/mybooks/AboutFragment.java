package com.mulyani.mybooks;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mulyani.mybooks.View.Admin.InputBookActivity;
import com.mulyani.mybooks.View.Authentication.AuthenticationActivity;


/**
 * A simple {@link Fragment} subclass.
 */

// Displaying fragment about
public class AboutFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    public static AboutFragment newInstance() {
        return new AboutFragment();
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
        Button addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), InputBookActivity.class)));

        Button signOutButton = view.findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getContext(), AuthenticationActivity.class));
        });
        return view;
    }

}
