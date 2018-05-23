package com.example.haopengsong.grabevent;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    private GridView mGridView;
    OnCommentSelectListener mCallback;
    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //创建一个view
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        //找到相应的grid view
        mGridView = (GridView) view.findViewById(R.id.comment_grid);

        //设置adapter
        mGridView.setAdapter(new EventAdapter(getActivity()));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.OnCommentSelected(position); //in EventFragment
                onItemSelected(position);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) { //将context引进到fragment里
        super.onAttach(context);
        try {
            mCallback = (OnCommentSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }

    /*
    Comm step 3:
    */
    // Change background color if the item is selected
    public void onItemSelected(int position){
        for (int i = 0; i < mGridView.getChildCount(); i++){
            if (position == i) {
                mGridView.getChildAt(i).setBackgroundColor(Color.YELLOW);
            } else {
                mGridView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }

}
