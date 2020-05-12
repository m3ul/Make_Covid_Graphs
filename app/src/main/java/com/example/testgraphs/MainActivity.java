package com.example.testgraphs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LineChart mLineChart;
    private BarChart mLineChart2;
    FirebaseDatabase mFirebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference mReference=mFirebaseDatabase.getReference("cases_time_series");
    private Integer i =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLineChart = (LineChart) findViewById(R.id.chart);
        mLineChart2 = (BarChart) findViewById(R.id.chart2);




//        YourData[] dataObjects = {new YourData(1,2),
//                new YourData(2,3),new YourData(3,4),
//                new YourData(5,6),new YourData(6,7),
//                new YourData(8,9),new YourData(10,20)};
//
//        List<Entry> entries = new ArrayList<Entry>();
//
//        for (YourData data : dataObjects) {
//            // turn your data into Entry objects
//            entries.add(new Entry(data.getValueX(), data.getValueY()));
//        }
//
//        LineDataSet dataSet = new LineDataSet(entries, "Label");
//
//        LineData lineData = new LineData(dataSet);
//        mLineChart.setData(lineData);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataVals=new ArrayList<Entry>();
                ArrayList dataVals2=new ArrayList();
                ArrayList dateVals=new ArrayList();



                for(DataSnapshot mDataSnapshot:dataSnapshot.getChildren()){
                    YourData data=mDataSnapshot.getValue(YourData.class);
                    dataVals.add(new Entry(i,Integer.parseInt(data.getDailyconfirmed())));
                    dataVals2.add(new BarEntry(i,Integer.parseInt(data.getDailyconfirmed())));
                    dateVals.add(data.getDate());
                    i++;
                }
//                mLineChart.setGridBackgroundColor(R.color.colorAccent);
//                mLineChart2.setGridBackgroundColor(R.color.colorAccent);
                LineDataSet dataSet = new LineDataSet(dataVals, "Daily Confirmed");
                setDatasetProperties(dataSet);
                LineData lineData = new LineData(dataSet);

                mLineChart.setData(lineData);
                BarDataSet dataSet2 = new BarDataSet(dataVals2, "Total Confirmed");
                setBarDatasetProperties(dataSet2);
                BarData lineData2 = new BarData(dataSet2);
                setChartProperties(mLineChart2, dateVals);
                mLineChart2.setData(lineData2);
//                mLineChart.invalidate();
//                mLineChart2.invalidate();

                mLineChart.animateX(2000);
                mLineChart2.animateX(2000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setChartProperties(BarChart mChart, final ArrayList dateVals){

        //mChart.setDrawBorders(true);
        //mChart.setBorderColor(Color.rgb(228,63,90));
        mChart.setBackgroundColor(Color.rgb(247,243,247));
        mChart.getXAxis().setTextColor(Color.BLACK);
        mChart.getLegend().setTextColor(Color.BLACK);
//        mChart.getBarData().setValueTextColor(Color.WHITE);
        mChart.getAxisLeft().setTextColor(Color.BLACK);
        mChart.getAxisRight().setTextColor(Color.BLACK);
        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().setValueFormatter(new IAxisValueFormatter(){

                                                @Override
                                                public String getFormattedValue(float value, AxisBase axis) {
                                                    if(value>=0){
                                                        if(value<=dateVals.size()-1){
                                                            return (String)dateVals.get((int)value);
                                                        }
                                                        return " ";
                                                    }
                                                    return " ";
                                                }
                                            }

        );


        //mChart.setGridBackgroundColor(Color.rgb(247,243,247));




    }
    public void setDatasetProperties(LineDataSet linedataset){
        linedataset.setCircleColor(Color.rgb(228,63,90));
        linedataset.isDrawCirclesEnabled();
//        linedataset.setFillColor(R.color.colorAccent);
        linedataset.isDrawCircleHoleEnabled();
        linedataset.setColor(Color.rgb(228,63,90));




    }
    public void setBarDatasetProperties(BarDataSet bardataset){
        bardataset.setColor(Color.rgb(228,63,90));
        bardataset.setValueTextSize(12);

    }
    private ArrayList getXAxisValues() {
        ArrayList xAxis = new ArrayList();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }




}
