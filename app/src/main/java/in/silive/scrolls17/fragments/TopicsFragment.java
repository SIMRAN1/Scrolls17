package in.silive.scrolls17.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls17.activities.SecondActivity;
import in.silive.scrolls17.adapters.DomainsAdapter;
import in.silive.scrolls17.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TopicsFragment extends Fragment implements View.OnClickListener {
View view;
    private RecyclerView rvDomains;
    private GridLayoutManager layoutManager;
    private String[] domains;
    private DomainsAdapter adapter;

    static   TopicsFragment fragment;
    private String[] imagesArray;

    public static TopicsFragment getInstance(){
        if (fragment == null)
            fragment = new TopicsFragment();
        return fragment;
    }

    public TopicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.topics, container, false);
        rvDomains = (RecyclerView)view.findViewById(R.id.rvtopics);
        rvDomains.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getContext(),1);

        rvDomains.setLayoutManager(layoutManager);
        domains = getActivity().getResources().getStringArray(R.array.domain_array);
        imagesArray =  getActivity().getResources().getStringArray(R.array.topic_img_array);

        adapter = new DomainsAdapter(getContext(),domains,imagesArray, (SecondActivity)getActivity());

        rvDomains.setAdapter(adapter);
        return view;
    }


    public void openBottomSheet(View v){

    }

    @Override
    public void onClick(View view) {

    }
}
