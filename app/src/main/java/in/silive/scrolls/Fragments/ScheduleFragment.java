package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;
import in.silive.scrolls.Adapters.ScheduleAdapter;
import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment implements VerticalStepperForm {
    public static String TAG = "ScheduleFragment";
    RecyclerView rvSchedule;
    ScheduleAdapter adapter;
    String dates[], labels[];
    LinearLayoutManager linearLayoutManager;
    private VerticalStepperFormLayout verticalStepperForm;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        rvSchedule = (RecyclerView) view.findViewById(R.id.rvSchedule);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dates = getContext().getResources().getStringArray(R.array.schedule_dates);
        labels = getContext().getResources().getStringArray(R.array.schedule_labels);
        adapter = new ScheduleAdapter(getContext(), dates, labels);
        rvSchedule.setLayoutManager(linearLayoutManager);
        rvSchedule.setAdapter(adapter);


        return view;
    }

    @Override
    public View createStepContentView(int stepNumber) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view =  inflater.inflate(R.layout.item_schedule, null, false);
       TextView tvDate = (TextView)view.findViewById(R.id.tvDate);
        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvDate.setText(dates[stepNumber]);
  tvTitle.setText(labels[stepNumber]);
        return view;
    }

    @Override
    public void onStepOpening(int stepNumber) {

    }

    @Override
    public void sendData() {

    }
}

