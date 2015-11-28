package io.dwi.archerycounter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.communication.IOnItemFocusChangedListener;
import org.eazegraph.lib.models.PieModel;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Random;

import io.dwi.archerycounter.logic.shotcount.api.ShotCountManager;
import io.dwi.archerycounter.logic.shotcount.impl.ShotCountManagerImpl;
import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;
import io.dwi.archerycounter.logic.trainingday.api.TrainingDayManager;
import io.dwi.archerycounter.logic.trainingday.impl.TrainingDayManagerImpl;
import io.dwi.archerycounter.logic.trainingday.to.TrainingDayCto;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * interface.
 */
public class TrainingDayStatisticsFragment extends Fragment {
    private UiDataManager uiDataManager;
    public static final String ARG_TRAINING_DAY_INDEX = "trainingDayIndex";
    private Toolbar toolbar;
    private PieChart pieChart;
    public TrainingDayStatisticsFragment() {
        uiDataManager = UiDataManager.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (pieChart != null)
            pieChart.startAnimation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int trainingDayIndex = args.getInt(ARG_TRAINING_DAY_INDEX);
        final View view;
        if(trainingDayIndex == 0){
            view = inflater.inflate(R.layout.fragment_training_day_statistics_new, container, false);
            TextView field = (TextView) view.findViewById(R.id.newDayText);
            field.setClickable(true);
            field.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("lolol","clicked");
                }
            });
        }
        else {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_training_day_statistics, container, false);

            int desiredAmount = 150;
            int summedShots = 0;
            pieChart = (PieChart) view.findViewById(R.id.piechart);
            final FloatingActionButton incrementFab = (FloatingActionButton) view.findViewById(R.id.incrementFab);
            final FloatingActionButton decrementFab = (FloatingActionButton) view.findViewById(R.id.decrementFab);
            final ListView listView = (ListView) view.findViewById(R.id.categoryListView);

            toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

            uiDataManager.setTrainingDayById(trainingDayIndex);
            TrainingDayCto trainingDay = uiDataManager.getCurrentTrainingDay();
            toolbar.setTitle(trainingDay.getDate().toString());
            final List<ShotCountEto> shotCounts = trainingDay.getShotCounts();
            if (shotCounts != null) {
                Random random = new Random();
                for (ShotCountEto shotCount : shotCounts) {
                    pieChart.addPieSlice(new PieModel(shotCount.getDistance().toString(), (int) (((double) shotCount.getAmount())), Color.rgb(random.nextInt(), random.nextInt(), random.nextInt())));
                    summedShots += shotCount.getAmount();
                }
            }
            final PieModel remainingSlice = new PieModel("Remaining", (int) ((double) (Math.max(desiredAmount - summedShots, 0))), Color.parseColor("#666666"));
            pieChart.addPieSlice(remainingSlice);

            pieChart.setOnItemFocusChangedListener(new IOnItemFocusChangedListener() {
                @Override
                public void onItemFocusChanged(int _Position) {
                    if (uiDataManager.getCurrentTrainingDay().getShotCounts().size() > _Position) {
                        Log.d("PieChart", "Position: " + _Position);
                        incrementFab.setBackgroundTintList(ColorStateList.valueOf(pieChart.getData().get(_Position).getColor()));
                        incrementFab.setEnabled(true);
                        decrementFab.setBackgroundTintList(ColorStateList.valueOf(pieChart.getData().get(_Position).getColor()));
                        decrementFab.setEnabled(true);
                        uiDataManager.setSelectedShotCount(uiDataManager.getCurrentTrainingDay().getShotCounts().get(_Position));
                    } else {
                        Log.d("PieChart", "Position: " + _Position);
                        incrementFab.setBackgroundTintList(ColorStateList.valueOf(pieChart.getData().get(_Position).getColor()));
                        incrementFab.setEnabled(false);
                        decrementFab.setBackgroundTintList(ColorStateList.valueOf(pieChart.getData().get(_Position).getColor()));
                        decrementFab.setEnabled(false);
                        uiDataManager.setSelectedShotCount(null);
                    }
                }
            });
            listView.setAdapter(new IncrementShotCountView(getContext(), R.layout.shot_count_layout, shotCounts, pieChart));
            incrementFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uiDataManager.addShots(1);
                    remainingSlice.setValue(remainingSlice.getValue() - 1);
                    pieChart.getData().get(pieChart.getCurrentItem()).setValue(uiDataManager.getSelectedShotCount().getAmount());
                    pieChart.update();
                    listView.invalidateViews();
                }
            });

            decrementFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uiDataManager.removeShots(1);
                    pieChart.getData().get(pieChart.getCurrentItem()).setValue(uiDataManager.getSelectedShotCount().getAmount());
                    remainingSlice.setValue(remainingSlice.getValue() + 1);
                    pieChart.update();
                    listView.invalidateViews();
                }
            });

            pieChart.startAnimation();
        }
        return view;
    }

}