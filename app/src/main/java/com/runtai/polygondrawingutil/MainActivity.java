package com.runtai.polygondrawingutil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends AppCompatActivity implements OnSeekBarChangeListener {

    private float maxCornerRadius;

    private DemoView demoView;
    private SeekBar cornerRadiusSeekBar, rotationSeekBar, scaleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maxCornerRadius = getResources().getDimension(R.dimen.max_corner_radius);

        setContentView(R.layout.activity_main);

        cornerRadiusSeekBar = (SeekBar) findViewById(R.id.corner_radius_seek_bar);
        rotationSeekBar = (SeekBar) findViewById(R.id.rotation_seek_bar);
        scaleSeekBar = (SeekBar) findViewById(R.id.scale_seek_bar);

        cornerRadiusSeekBar.setOnSeekBarChangeListener(this);
        rotationSeekBar.setOnSeekBarChangeListener(this);
        scaleSeekBar.setOnSeekBarChangeListener(this);

        demoView = (DemoView) findViewById(R.id.demo_view);
        updateCornerRadius(cornerRadiusSeekBar.getProgress());
        updateRotation(rotationSeekBar.getProgress());
        updateScale(scaleSeekBar.getProgress());
    }

    public void reduceSideCount(@NonNull final View view) {
        demoView.setNumberOfSides(Math.max(3, demoView.getNumberOfSides() - 1));
    }

    public void increaseSideCount(@NonNull final View view) {
        demoView.setNumberOfSides(demoView.getNumberOfSides() + 1);
    }

    @Override
    public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.corner_radius_seek_bar:
                updateCornerRadius(progress);
                break;
            case R.id.rotation_seek_bar:
                updateRotation(progress);
                break;
            case R.id.scale_seek_bar:
                updateScale(progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(final SeekBar seekBar) {
        // This method intentionally left blank.
    }

    @Override
    public void onStopTrackingTouch(final SeekBar seekBar) {
        // This method intentionally left blank.
    }

    private void updateCornerRadius(final float cornerRadius) {
        demoView.setCornerRadius((cornerRadius / cornerRadiusSeekBar.getMax()) * maxCornerRadius);
    }

    private void updateRotation(final float rotation) {
        demoView.setPolygonRotation(rotation * 360 / rotationSeekBar.getMax());
    }

    private void updateScale(final float scale) {
        demoView.setScale(scale / scaleSeekBar.getMax());
    }

}
