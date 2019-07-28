package edu.gsu.csc4360.hpark44.banddatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DetailsFragment extends Fragment {

    private Band mBand;
    public static DetailsFragment newInstance(int bandId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("bandId", bandId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the band ID from the intent that started DetailsActivity
        int bandId = 1;
        if (getArguments() != null) {
            bandId = getArguments().getInt("bandId");
        }

        mBand = BandDatabase.getInstance(getContext()).getBand(bandId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.bandName);
        nameTextView.setText(mBand.getName());

        TextView descriptionTextView = (TextView) view.findViewById(R.id.bandDescription);
        descriptionTextView.setText(mBand.getDescription());

        return view;
    }


    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
        // Required empty public constructor
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
