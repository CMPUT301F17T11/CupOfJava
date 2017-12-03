package com.cmput301f17t11.cupofjava.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Controllers.SocialRequestHandler;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FollowingTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FollowingTab extends Fragment {
    private String userName;
    //Button addButton;

    private OnFragmentInteractionListener mListener;

    public FollowingTab() {}

    /**
     * The system calls this when creating this fragment.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.userName = getArguments().getString("userName");
        }

    }

    /**
     * The system calls this when it's time for the fragment to draw its user interface for the first time.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_following_tab, container, false);

        Button addButton = (Button)v.findViewById(R.id.add_to_following_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Find User").setMessage("Enter Username to Search For");

                final EditText input = new EditText(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(layoutParams);
                builder.setView(input);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SocialRequestHandler.sendFollowRequest(userName,
                                        input.getText().toString());

                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.putExtra("userName", userName);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });


                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void addUserButton(View view){
        Intent intent = getActivity().getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
