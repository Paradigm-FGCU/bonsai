package org.srge.bonsai;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class CardSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    public static final String ARG_SECTION_NUMBER = "section_number";

    public CardSectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card_dummy, container, false);
        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
        dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}