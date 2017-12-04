package com.cmput301f17t11.cupofjava.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.cmput301f17t11.cupofjava.R;

public class ProfileTab extends Fragment {

    private String userName;


    private OnFragmentInteractionListener mListener;

    public ProfileTab() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowingTab.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowingTab newInstance(String param1, String param2) {
        FollowingTab fragment = new FollowingTab();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        TextView userNameText = (TextView) view.findViewById(R.id.username);
        userNameText.setText(userName);


        TextView habitTipTitle = (TextView) view.findViewById(R.id.habit_tip_title);
        TextView habitTipBody = (TextView) view.findViewById(R.id.habit_tip_body);
        Random rand = new Random();
        int randomNumber = rand.nextInt(8) + 1;

        String[] habitArrayTitle = getContext().getResources().getStringArray(R.array.habitHelpTitle);
        String[] habitArrayBody = getContext().getResources().getStringArray(R.array.habitHelpBody);
        String habitTitleString = habitArrayTitle[randomNumber];
        String habitBodyString = habitArrayBody[randomNumber];

        habitTipTitle.setText(habitTitleString);
        habitTipBody.setText(habitBodyString);

        Button logoutButton = (Button) view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserLoginActivity.class);
                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_LONG).show();
                startActivity(intent);
        }
        });


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
