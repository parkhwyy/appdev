package edu.csc4360.project2.wineinventory;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeaderFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;

    public HeaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_header, container, false);
        final RatingBar ratingBar = rootView.findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener (new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar,
                                        float rating, boolean fromUser) {
                // Get rating and show Toast with rating.
                String myRating = ("My Rating: ") + String.valueOf(ratingBar.getRating());
                Toast.makeText(getContext(), myRating, Toast.LENGTH_SHORT).show();
            }
        });

        // Return the View for the fragment's UI.
        return rootView;
    }

    public static HeaderFragment newInstance() {
        return new HeaderFragment();
    }

}