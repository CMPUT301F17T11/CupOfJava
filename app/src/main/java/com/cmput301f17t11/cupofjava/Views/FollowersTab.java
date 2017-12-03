package com.cmput301f17t11.cupofjava.Views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cmput301f17t11.cupofjava.Controllers.SocialRequestHandler;
import com.cmput301f17t11.cupofjava.Models.User;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FollowersTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowersTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowersTab extends Fragment {

    private String userName;

    private OnFragmentInteractionListener mListener;

    public FollowersTab() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowersTab.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowersTab newInstance(String param1, String param2) {
        FollowersTab fragment = new FollowersTab();
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
        View v = inflater.inflate(R.layout.fragment_followers_tab, container, false);


        ListView listView = (ListView)v.findViewById(R.id.followers_list_view);

        User user = SocialRequestHandler.getUser(userName);
        ArrayList<String> followerList =  user.getFollowerList();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.habit_list_item, followerList);
        listView.setAdapter(arrayAdapter);


        //TODO alert dialog opening up on click to give option to remove follower

        return v;

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
