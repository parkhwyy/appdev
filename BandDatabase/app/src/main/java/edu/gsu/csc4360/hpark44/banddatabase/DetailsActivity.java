package edu.gsu.csc4360.hpark44.banddatabase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {
    public static String EXTRA_BAND_ID = "bandId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.details_fragment_container);

        if (fragment == null) {
            fragment = new DetailsFragment();
            int bandId = getIntent().getIntExtra(EXTRA_BAND_ID, 1);
            fragment = DetailsFragment.newInstance(bandId);
            fragmentManager.beginTransaction()
                    .add(R.id.details_fragment_container, fragment)
                    .commit();
        }
    }
}
