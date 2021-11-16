package com.example.mystats;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;
    ArrayList<String> monthArrayList = new ArrayList<>();
    ArrayList<String> yAxis = new ArrayList<>();

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();
        getMonth();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Graph for yearly expenses");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(10f);
        barChart.getDescription().setEnabled(false);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(monthArrayList));
        barChart.getAxisRight().setValueFormatter(new IndexAxisValueFormatter(yAxis));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getXAxis().setLabelCount(12);
        //barChart.getXAxis().setLabelRotationAngle(-45);
        barChart.animateY(2000);
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int x = barChart.getBarData().getDataSetForEntry(e).getEntryIndex((BarEntry) e);
                int y = x+1;
                String month = monthArrayList.get(y);
                String amount1 = String.valueOf(barEntriesArrayList.get(x));
                int strlen = amount1.length();
                String amount = "Expenses are:-" + amount1.substring(16,strlen)+"₹";
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                View nview = LayoutInflater.from(MainActivity.this).inflate(R.layout.montly_stats,null);
                TextView month_txt = nview.findViewById(R.id.month);
                TextView expense_txt = nview.findViewById(R.id.expense);
                month_txt.setText(month);
                expense_txt.setText(amount);
                builder.setView(nview);
                alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 821));
        barEntriesArrayList.add(new BarEntry(2f, 513));
        barEntriesArrayList.add(new BarEntry(3f, 554));
        barEntriesArrayList.add(new BarEntry(4f, 778));
        barEntriesArrayList.add(new BarEntry(5f, 659));
        barEntriesArrayList.add(new BarEntry(6f, 511));
        barEntriesArrayList.add(new BarEntry(7f, 242));
        barEntriesArrayList.add(new BarEntry(8f, 420));
        barEntriesArrayList.add(new BarEntry(9f, 641));
        barEntriesArrayList.add(new BarEntry(10f, 388));
        barEntriesArrayList.add(new BarEntry(11f, 710));
        barEntriesArrayList.add(new BarEntry(12f, 512));
    }
    private ArrayList<String> getMonth() {
        monthArrayList.add("");
        monthArrayList.add("JAN");
        monthArrayList.add("FEB");
        monthArrayList.add("MAR");
        monthArrayList.add("APR");
        monthArrayList.add("MAY");
        monthArrayList.add("JUN");
        monthArrayList.add("JUL");
        monthArrayList.add("AUG");
        monthArrayList.add("SEP");
        monthArrayList.add("OCT");
        monthArrayList.add("NOV");
        monthArrayList.add("DEC");
        return monthArrayList;
    }
    private ArrayList<String> getYAxisValues() {
        yAxis.add("");
        yAxis.add("");
        yAxis.add("");
        yAxis.add("");
        yAxis.add("");
        return yAxis;
    }
}