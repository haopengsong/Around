package com.example.haopengsong.grabevent;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private ListView mListView;
    OnItemSelectListener mCallback;
    OnCommentSelectListener mCallback2;
    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mListView = (ListView) view.findViewById(R.id.event_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getEventNames());

        // Assign adapter to ListView.
        mListView.setAdapter(adapter);

         /*
        Comm step 2:
        */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.onItemSelected(i); //in CommentFragment
                //mCallback2.OnCommentSelected(i);
                OnCommentSelected(i);
            }
        });

        return view;
    }


    // Container Activity must implement this interface
    public interface OnItemSelectListener {
        public void onItemSelected(int position);
    }

    /*
    Comm step 1: capture the click activity, store it to mCallback(MainActivity)
                 mCallback is a interface which has MainActivity inside

                 在其中一个fragment里面加interface，在mainactivity里面implement这个interface；
                 在activity里面写interface；
                 在fragmentA里面override时调用MainActivity里另外一个fragmentB与之对应的方法;
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemSelectListener) context;
            //mCallback2 = (OnCommentSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }

    private String[] getEventNames() {
        String[] names = {
                "Event1", "Event2", "Event3",
                "Event4", "Event5", "Event6",
                "Event7", "Event8", "Event9",
                "Event10", "Event11", "Event12"};
        return names;

    }


    public void OnCommentSelected(int position){
        for (int i = 0; i < mListView.getChildCount(); i++){
            if (position == i) {
                mListView.getChildAt(i).setBackgroundColor(Color.CYAN);
            } else {
                mListView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }
}
