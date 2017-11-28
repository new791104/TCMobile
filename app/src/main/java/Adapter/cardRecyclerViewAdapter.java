package Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Global.GV;
import Objects.HRV;
import Objects.SPO2;
import Objects.dataTable;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PreviewLineChartView;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/11/27.
 */

public class cardRecyclerViewAdapter extends RecyclerView.Adapter<cardRecyclerViewAdapter.cardViewHolder>{

    private boolean[] isDrawed;
    private Context cContext;


    public static class cardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textView_dataField;
        LineChartView lineChartView;
        PreviewLineChartView linechartPreView;

        cardViewHolder(View itemView) {
            super(itemView);
            Log.e("deb", "###cardViewHolder###");
            cv = itemView.findViewById(R.id.cv);
            textView_dataField = itemView.findViewById(R.id.dataField_textView);
            lineChartView = itemView.findViewById(R.id.lineChart);
            linechartPreView = itemView.findViewById(R.id.lineChart_preview);
        }
    }


    /* constructor */
    public cardRecyclerViewAdapter(Context context) {
        Log.e("deb", "###cardRecyclerViewAdapter###");
        isDrawed = new boolean[GV.tablelist.getDataFields().length];
        Arrays.fill(isDrawed, false);
        Log.e("deb", "isDrawed: " + Arrays.toString(isDrawed));
        cContext = context;
    }

    @Override
    public cardRecyclerViewAdapter.cardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("deb", "###onCreateViewHolder###");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_card, parent, false);

        return new cardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(cardRecyclerViewAdapter.cardViewHolder holder, int position) {
        /*
        VERY IMPORTANT: 不同 position 的 View 要用自己的區域變數，要不然會重覆予值!
         */
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();

        LineChartData previewData;
        LineChartData lineData;
        Axis axisY = null;
        Axis axisX = null;

        boolean hasLines = true;
        boolean hasPoints = true;
        ValueShape shape = ValueShape.CIRCLE;
        boolean isFilled = false;
        boolean hasLabels = false;
        boolean isCubic = false;
        boolean hasLabelForSelected = false;

        Log.e("deb", "position: " + position);
        Log.e("deb", "isDrawed: " + Arrays.toString(isDrawed));

        if (!isDrawed[position]) {
            Log.e("deb", "position: " + position + ", isDrawed.");
            isDrawed[position] = true;
            holder.textView_dataField.setText(GV.tablelist.getDataFields()[position]);

            holder.lineChartView.setInteractive(true);
            holder.lineChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
            holder.lineChartView.setOnValueTouchListener(new ValueTouchListener());

            if (GV.tablelist.getType().equals("HRV")) {
                setXAxis_hrv(values, axisValues, axisX, axisY, position);
            } else if (GV.tablelist.getType().equals("SPO2")) {
                setXAxis_spo2();
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLOR_GREEN);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);

            List<Line> lines = new ArrayList<Line>();
            lines.add(line);

            axisY = new Axis().setHasLines(true);
            axisY.setName(GV.tablelist.getType());
            axisX = new Axis(axisValues).setHasLines(true);
            axisX.setMaxLabelChars(4);
            axisX.setName("time");

            lineData = new LineChartData(lines);
            lineData.setAxisXBottom(axisX);
            lineData.setAxisYLeft(axisY);
            lineData.setBaseValue(Float.NEGATIVE_INFINITY);
            holder.lineChartView.setLineChartData(lineData);

            //previewXY(holder.lineChartView, holder.linechartPreView);
            // Better to not modify viewport of any chart directly so create a copy.
            Viewport tempViewport = new Viewport(holder.lineChartView.getMaximumViewport());
            // Make temp viewport smaller.
            float dx = tempViewport.width() / 4;
            holder.linechartPreView.setCurrentViewportWithAnimation(tempViewport);
            holder.linechartPreView.setZoomType(ZoomType.HORIZONTAL);
            holder.linechartPreView.setPreviewColor(ChartUtils.pickColor());
            ChartUtils.nextColor();

            previewData = new LineChartData(lineData);
            previewData.getLines().get(0).setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
            holder.linechartPreView.setLineChartData(previewData);
            ViewportListener viewport = new ViewportListener();
            viewport.setChartView(holder.lineChartView);
            holder.linechartPreView.setViewportChangeListener(viewport);

        }
    }

    private void previewXY(LineChartView chart, LineChartView previewChart) {
        // Better to not modify viewport of any chart directly so create a copy.
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        // Make temp viewport smaller.
        float dx = tempViewport.width() / 4;
        float dy = tempViewport.height() / 4;
        tempViewport.inset(dx, dy);
        previewChart.setCurrentViewportWithAnimation(tempViewport);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return GV.tablelist.getDataFields().length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // TODO 整理程式碼
    private void setXAxis_hrv(List<PointValue> values, List<AxisValue> axisValues, Axis axisX, Axis axisY, int position) {
        dataTable<HRV> dt = GV.hrvTable.getDatatable().get(0);

        for (int i = 0; i < dt.getArrayList().size(); i++) {

            switch (position) {
                case 0:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getRR()));
                    break;
                case 1:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getSD()));
                    break;
                case 2:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getTP()));
                    break;
                case 3:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getVL()));
                    break;
                case 4:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getLF()));
                    break;
                case 5:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getHF()));
                    break;
                case 6:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getLFP()));
                    break;
                case 7:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getHFP()));
                    break;
                case 8:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getVAR()));
                    break;
                case 9:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getLHR()));
                    break;
                case 10:
                    values.add(new PointValue(i, (float) dt.getArrayList().get(i).getWL()));
                    break;
            }
            axisValues.add(new AxisValue(i).setLabel(dt.getArrayList().get(i).getTimestamp()));
        }
    }

    private void setXAxis_spo2() {

    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(cContext, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
    /**
     * Viewport listener for preview chart(lower one). in {@link #onViewportChanged(Viewport)} method change
     * viewport of upper chart.
     */
    private class ViewportListener implements ViewportChangeListener {
        private cardRecyclerViewAdapter.cardViewHolder holder;
        private LineChartView lineChartView;

        @Override
        public void onViewportChanged(Viewport newViewport) {
            // don't use animation, it is unnecessary when using preview chart.
            lineChartView.setCurrentViewport(newViewport);
        }

        public void setChartView(LineChartView lineChartView) {
            this.lineChartView = lineChartView;
        }

    }
}
