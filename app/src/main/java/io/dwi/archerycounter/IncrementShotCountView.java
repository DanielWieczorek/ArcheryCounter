package io.dwi.archerycounter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;

/**
 * Created by llllllllllll on 11/9/2015.
 */
public class IncrementShotCountView extends ArrayAdapter<ShotCountEto> {

    private PieChart pieChart;

    public IncrementShotCountView(Context context, int textViewResourceId, PieChart pieChart) {
        super(context, textViewResourceId);
        this.pieChart = pieChart;
    }

    public IncrementShotCountView(Context context, int resource, List<ShotCountEto> items, PieChart pieChart) {
        super(context, resource, items);
        this.pieChart = pieChart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.shot_count_layout, null);
        }

        final ShotCountEto p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.shotCountCategory);
            TextView tt2 = (TextView) v.findViewById(R.id.shotCountAmount);

            if (tt1 != null) {
                tt1.setText(p.getDistance().toString());
            }

            if (tt2 != null) {
                tt2.setText(p.getAmount()+"");
            }
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (PieModel model: pieChart.getData()) {
                    if (model.getLegendLabel().equals(p.getDistance().toString())){
                        pieChart.setCurrentItem(pieChart.getData().indexOf(model));
                    }

                }
            }
        });
        return v;
    }

}

