package com.hergal.studentmnagementapp.ui.filiere;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hergal.studentmnagementapp.R;

public class FiliereDetailFragment extends Fragment {

    // Define keys for fragment arguments
    private static final String ARG_CODE = "code";
    private static final String ARG_INTITULE = "intitule";
    private static final String ARG_CORDONNATEUR = "cordonnateur";
    private static final String ARG_DATE_CREATION = "dateCreation";
    private static final String ARG_DESCRIPTION = "description";

    // Constructor
    public FiliereDetailFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of the fragment with filière details
    public static FiliereDetailFragment newInstance(String code, String intitule, String cordonnateur, String dateCreation, String description) {
        FiliereDetailFragment fragment = new FiliereDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODE, code);
        args.putString(ARG_INTITULE, intitule);
        args.putString(ARG_CORDONNATEUR, cordonnateur);
        args.putString(ARG_DATE_CREATION, dateCreation);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_detail_filiere, container, false);

        // Find TextViews in the layout
        TextView codeTextView = view.findViewById(R.id.CodeFiliere);
        TextView intituleTextView = view.findViewById(R.id.IntituleFiliere);
        TextView cordonnateurTextView = view.findViewById(R.id.CordonnateurValue);
        TextView dateCreationTextView = view.findViewById(R.id.DateCreationValue);
        TextView descriptionTextView = view.findViewById(R.id.DescriptionValue);

        // Retrieve filière details from arguments
        Bundle args = getArguments();
        if (args != null) {
            String code = args.getString(ARG_CODE, "");
            String intitule = args.getString(ARG_INTITULE, "");
            String cordonnateur = args.getString(ARG_CORDONNATEUR, "");
            String dateCreation = args.getString(ARG_DATE_CREATION, "");
            String description = args.getString(ARG_DESCRIPTION, "");

            // Set TextViews with filière details
            codeTextView.setText(code);
            intituleTextView.setText(intitule);
            cordonnateurTextView.setText(cordonnateur);
            dateCreationTextView.setText(dateCreation);
            descriptionTextView.setText(description);
        }

        return view;
    }
}
