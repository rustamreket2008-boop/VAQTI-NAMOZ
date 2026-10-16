
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QiblaActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private TextView compassText;
    private TextView directionText;
    private CompassView compassView;

    private final int bg = Color.rgb(7, 23, 22);
    private final int green = Color.rgb(8, 65, 49);
    private final int gold = Color.rgb(238, 195, 102);
    private final int white = Color.WHITE;

    // Координатаи Каъба
    private final double kaabaLat = 21.4225;
    private final double kaabaLon = 39.8262;

    // Координатаи Душанбе
    private final double userLat = 38.5598;
    private final double userLon = 68.7870;

    private float azimuth = 0;
    private double qiblaBearing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);

        rotationSensor = sensorManager.getDefaultSensor(
                Sensor.TYPE_ROTATION_VECTOR);

        qiblaBearing = calculateQibla(userLat, userLon);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(22), dp(24), dp(22), dp(24));
        root.setBackgroundColor(bg);

        TextView back = new TextView(this);
        back.setText("‹  Бозгашт");
        back.setTextColor(gold);
        back.setTextSize(18);
        back.setGravity(Gravity.CENTER_VERTICAL);
        back.setOnClickListener(v -> finish());
        root.addView(back, params(-1, dp(45)));

        TextView icon = new TextView(this);
        icon.setText("🧭");
        icon.setTextSize(48);
        icon.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams ip = params(-1, dp(85));
        ip.topMargin = dp(20);
        root.addView(icon, ip);

        TextView title = new TextView(this);
        title.setText("ҚИБЛАНАМО");
        title.setTextColor(white);
        title.setTextSize(26);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, params(-1, dp(45)));

        TextView subtitle = new TextView(this);
        subtitle.setText("Самти Каъба");
        subtitle.setTextColor(gold);
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle, params(-1, dp(35)));

        compassView = new CompassView(this);
        LinearLayout.LayoutParams cp = params(dp(280), dp(280));
        cp.gravity = Gravity.CENTER;
        cp.topMargin = dp(20);
        root.addView(compassView, cp);

        compassText = new TextView(this);
        compassText.setText("Самт: --°");
        compassText.setTextColor(gold);
        compassText.setTextSize(22);
        compassText.setTypeface(null, Typeface.BOLD);
        compassText.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams bp = params(-1, dp(55));
        bp.topMargin = dp(18);
        root.addView(compassText, bp);

        directionText = new TextView(this);
        directionText.setText("Интизорӣ ба сенсори телефон...");
        directionText.setTextColor(white);
        directionText.setTextSize(14);
        directionText.setGravity(Gravity.CENTER);
        root.addView(directionText, params(-1, dp(45)));

        TextView note = new TextView(this);
        note.setText(
                "Телефонро ҳамвор нигоҳ дор ва аз ашёи оҳанин дур кун.");
        note.setTextColor(0xFFB5C9C0);
        note.setTextSize(12);
        note.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams np = params(-1, dp(50));
        np.topMargin = dp(15);
        root.addView(note, np);

        setContentView(root);

        if (rotationSensor == null) {
            directionText.setText(
                    "Сенсори компас дар ин телефон дастрас нест.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rotationSensor != null) {
            sensorManager.registerListener(
                    this, rotationSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ROTATION_VECTOR) {
            return;
        }

        float[] rotationMatrix = new float[9];
        float[] orientation = new float[3];

        SensorManager.getRotationMatrixFromVector(
                rotationMatrix, event.values);
        SensorManager.getOrientation(rotationMatrix, orientation);

        azimuth = (float) Math.toDegrees(orientation[0]);
        azimuth = (azimuth + 360) % 360;

        float relative = (float) qiblaBearing - azimuth;
        relative = (relative + 360) % 360;

        compassView.setDirections(azimuth, (float) qiblaBearing);
        compassText.setText(
                String.format(java.util.Locale.US,
                        "Самти қибла: %.0f°", qiblaBearing));

        directionText.setText(
                String.format(java.util.Locale.US,
                        "Телефонро ба самти %.0f° гардон", relative));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    private double calculateQibla(double lat, double lon) {
        double lat1 = Math.toRadians(lat);
        double lat2 = Math.toRadians(kaabaLat);
        double deltaLon = Math.toRadians(kaabaLon - lon);

        double y = Math.sin(deltaLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2)
                - Math.sin(lat1) * Math.cos(lat2)
                * Math.cos(deltaLon);

        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    private class CompassView extends android.view.View {
        private final android.graphics.Paint paint =
                new android.graphics.Paint(3);
        private float heading;
        private float bearing;

        CompassView(Context context) {
            super(context);
        }

        void setDirections(float h, float b) {
            heading = h;
            bearing = b;
            invalidate();
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);

            float cx = getWidth() / 2f;
            float cy = getHeight() / 2f;
            float radius = Math.min(cx, cy) * 0.88f;

            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setColor(green);
            canvas.drawCircle(cx, cy, radius, paint);

            paint.setStyle(android.graphics.Paint.Style.STROKE);
            paint.setStrokeWidth(dp(3));
            paint.setColor(gold);
            canvas.drawCircle(cx, cy, radius, paint);

            paint.setTextAlign(android.graphics.Paint.Align.CENTER);
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setTextSize(dp(18));
            paint.setTypeface(Typeface.DEFAULT_BOLD);

            String[] labels = {"Ш", "ШВ", "Ш", "Ҷ"};
            String[] cardinal = {"N", "E", "S", "W"};

            for (int i = 0; i < 4; i++) {
                double angle = Math.toRadians(i * 90 - heading - 90);
                float x = cx + (float) Math.cos(angle) * radius * 0.78f;
                float y = cy + (float) Math.sin(angle) * radius * 0.78f;
                paint.setColor(i == 0 ? gold : white);
                canvas.drawText(cardinal[i], x, y + dp(6), paint);
            }

            canvas.save();
            canvas.rotate(-heading, cx, cy);

            paint.setColor(white);
            paint.setStrokeWidth(dp(3));
            canvas.drawLine(cx, cy - radius * 0.65f,
                    cx, cy + radius * 0.45f, paint);

            paint.setColor(gold);
            android.graphics.Path needle = new android.graphics.Path();
            needle.moveTo(cx, cy - radius * 0.72f);
            needle.lineTo(cx - dp(13), cy);
            needle.lineTo(cx + dp(13), cy);
            needle.close();
            canvas.drawPath(needle, paint);

            canvas.restore();

            canvas.save();
            canvas.rotate((float) bearing - heading, cx, cy);
            paint.setColor(0xFF64E6A0);
            paint.setStrokeWidth(dp(4));
            canvas.drawLine(cx, cy, cx, cy - radius * 0.55f, paint);
            canvas.drawCircle(cx, cy, dp(7), paint);
            canvas.restore();

            paint.setColor(gold);
            paint.setTextSize(dp(13));
            canvas.drawText("КАЪБА", cx, cy + radius * 0.52f, paint);
        }
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout.LayoutParams params(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }
                         }
      
