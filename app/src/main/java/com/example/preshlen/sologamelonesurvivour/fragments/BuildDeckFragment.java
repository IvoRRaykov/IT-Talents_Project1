package com.example.preshlen.sologamelonesurvivour.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.front.HomeActivity;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

/**
 * Created by Preshlen on 3/13/2016.
 */
public class BuildDeckFragment extends ListFragment implements AdapterView.OnItemClickListener {
    ArrayAdapter adapter;
    TextView remainingQuestions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.build_deck_list_fragment_layout, container, false);
        remainingQuestions = (TextView) view.findViewById(R.id.remaining_qeustions);
        remainingQuestions.setText(String.valueOf(UserManager.getDeckFreeSpaceLeft()));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1);
        adapter.addAll(UserManager.getAllQuestions());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserManager.addQuestionToDeck((String) adapter.getItem(position));
        adapter.remove(adapter.getItem(position));
        adapter.notifyDataSetChanged();
        if (UserManager.isDeckFull()) {
            if (getActivity() instanceof HomeActivity) {
                ((HomeActivity) getActivity()).onBackPressed();
            }
        }
        remainingQuestions.setText(String.valueOf(UserManager.getDeckFreeSpaceLeft()));
    }
}
