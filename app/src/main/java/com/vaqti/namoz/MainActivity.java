package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends Activity {

    private final int green = Color.rgb(8, 49, 39);
    private final int gold = Color.rgb(238, 195, 102);
    private final int background = Color.rgb(7, 23, 22);
    private final int white = Color.WHITE;

    private Spinner citySpinner;
    private LinearLayout prayerContainer;
    private TextView cityTitle;
    private TextView dateText;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable dailyRefresh = new Runnable() {
        @Override
        public void run() {
            if (citySpinner != null
                    && citySpinner.getSelectedItemPosition() >= 0) {
                updateTimes(citySpinner.getSelectedItemPosition());
            }
            handler.postDelayed(this, 60000);
        }
    };

    private final City[] cities = {
        new City("Душанбе", 38.5598, 68.7870),
        new City("Хуҷанд", 40.2833, 69.6333),
        new City("Бохтар", 37.8364, 68.7803),
        new City("Кӯлоб", 37.9146, 69.7845),
        new City("Панҷакент", 39.4952, 67.6093),
        new City("Исфара", 40.1265, 70.6253),
        new City("Истаравшан", 39.9109, 69.0065),
        new City("Хоруғ", 37.4897, 71.5530),
        new City("Турсунзода", 38.5127, 68.2316),
        new City("Ваҳдат", 38.5550, 69.0135),
        new City("Ҳисор", 38.5250, 68.5510),
        new City("Норак", 38.3892, 69.3227),
        new City("Данғара", 38.0950, 69.3400),
        new City("Ёвон", 38.3141, 69.0378),
        new City("Фархор", 37.4922, 69.4030),
        new City("Шаҳритус", 37.2621, 68.1385),
        new City("Мурғоб", 38.1702, 73.9667),
        new City("Рашт", 39.0287, 70.3745)
    };

    private final String[] names = {
        "Бомдод",
        "Тулӯи офтоб",
        "Пешин",
        "Аср (Ҳанафӣ)",
        "Шом",
        "Хуфтан"
    };

    private final String[] icons = {
        "🌙", "🌅", "☀️", "🌤️", "🌇", "🌙"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(green);
        getWindow().setNavigationBarColor(background);

        buildScreen();

        if (citySpinner.getSelectedItemPosition() >= 0) {
            updateTimes(citySpinner.getSelectedItemPosition());
        }

        handler.postDelayed(dailyRefresh, 60000);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(dailyRefresh);
        super.onDestroy();
    }

    private void buildScreen() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(background);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(18), dp(18), dp(24));
        scroll.addView(root);

        TextView logo = text("☾", 48, gold, true);
        logo.setGravity(Gravity.CENTER);
        root.addView(logo, match());

        TextView heading = text("ВАҚТИ НАМОЗ", 27, white, true);
        heading.setGravity(Gravity.CENTER);
        root.addView(heading, match());

        TextView subtitle = text(
            "Вақти намоз дар Тоҷикистон", 15, gold, false
        );
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, dp(5), 0, dp(20));
        root.addView(subtitle, match());

        TextView label = text(
            "📍  ШАҲРРО ИНТИХОБ КУН", 14, gold, true
        );
        label.setPadding(dp(4), dp(8), 0, dp(8));
        root.addView(label, match());

        citySpinner = new Spinner(this);

        String[] cityNames = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            cityNames[i] = cities[i].name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            cityNames
        );
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        );
        citySpinner.setAdapter(adapter);

        LinearLayout cityBox = new LinearLayout(this);
        cityBox.setPadding(dp(10), 0, dp(10), 0);
        cityBox.setBackground(roundBox(green, 16));
        cityBox.addView(citySpinner,
            new LinearLayout.LayoutParams(-1, dp(54)));
        root.addView(cityBox, match());

        cityTitle = text("", 21, white, true);
        cityTitle.setPadding(dp(3), dp(22), 0, dp(4));
        root.addView(cityTitle, match());

        dateText = text("", 13, 0xFFB9C8C2, false);
        dateText.setPadding(dp(3), 0, 0, dp(15));
        root.addView(dateText, match());

        TextView title = text("ВАҚТҲОИ НАМОЗ", 15, gold, true);
        title.setPadding(dp(3), dp(5), 0, dp(12));
        root.addView(title, match());

        prayerContainer = new LinearLayout(this);
        prayerContainer.setOrientation(LinearLayout.VERTICAL);
        root.addView(prayerContainer, match());

        // Қисми функсияҳои иловагӣ
        TextView featureTitle = text(
            "ФУНКСИЯҲОИ БАРНОМА", 16, gold, true
        );
        featureTitle.setPadding(dp(3), dp(22), 0, dp(12));
        root.addView(featureTitle, match());

        addFeatureRow(root,
            "🧭  Қибланамо", QiblaActivity.class,
            "📿  Тасбеҳ", TasbihActivity.class);

        addFeatureRow(root,
            "🌙  Тақвими ҳиҷрӣ", HijriCalendarActivity.class,
            "🤲  Дуоҳо", DuaActivity.class);

        addFeatureRow(root,
            "📖  Қуръони карим", QuranActivity.class,
            "🕌  Масҷидҳо", MosqueActivity.class);

        TextView note = text(
            "Вақтҳо ҳисобӣ мебошанд. Барои дақиқӣ бо тақвими "
                + "масҷиди маҳаллӣ муқоиса кунед.",
            12, 0xFF9AAFA7, false
        );
        note.setGravity(Gravity.CENTER);
        note.setPadding(dp(8), dp(22), dp(8), dp(8));
        root.addView(note, match());

        citySpinner.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id
                ) {
                    updateTimes(position);
                }

                @Override
                public void onNothingSelected(
                    AdapterView<?> parent
                ) {
                }
            }
        );

        setContentView(scroll);
    }

    private void addFeatureRow(
        LinearLayout root,
        String firstTitle,
        Class<?> firstActivity,
        String secondTitle,
        Class<?> secondActivity
    ) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams rowParams =
            new LinearLayout.LayoutParams(-1, -2);
        rowParams.bottomMargin = dp(10);
        root.addView(row, rowParams);

        row.addView(
            featureButton(firstTitle, firstActivity),
            new LinearLayout.LayoutParams(0, dp(64), 1)
        );

        View space = new View(this);
        row.addView(space,
            new LinearLayout.LayoutParams(dp(10), 1));

        row.addView(
            featureButton(secondTitle, secondActivity),
            new LinearLayout.LayoutParams(0, dp(64), 1)
        );
    }

    private TextView featureButton(
        String title,
        Class<?> activityClass
    ) {
        TextView button = text(title, 14, white, true);
        button.setGravity(Gravity.CENTER);
        button.setPadding(dp(5), dp(6), dp(5), dp(6));
        button.setBackground(roundBox(green, 16));
        button.setClickable(true);
        button.setFocusable(true);

        button.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(
                    MainActivity.this, activityClass
                );
                startActivity(intent);
            } catch (Exception e) {
                android.widget.Toast.makeText(
                    MainActivity.this,
                    "Экран кушода нашуд.",
                    android.widget.Toast.LENGTH_SHORT
                ).show();
            }
        });

        return button;
    }

    private void updateTimes(int position) {
        if (position < 0 || position >= cities.length
                || prayerContainer == null) {
            return;
        }

        City city = cities[position];

        Calendar cal = Calendar.getInstance(
            TimeZone.getTimeZone("Asia/Dushanbe")
        );

        Date now = cal.getTime();

        cityTitle.setText("📍 " + city.name);

        dateText.setText(new SimpleDateFormat(
            "EEEE, dd MMMM yyyy", new Locale("tg")
        ).format(now));

        double[] times = calculate(
            city.latitude,
            city.longitude,
            cal.get(Calendar.DAY_OF_YEAR)
        );

        prayerContainer.removeAllViews();

        for (int i = 0; i < names.length; i++) {
            addCard(icons[i], names[i], formatTime(times[i]));
        }
    }

    private void addCard(
        String icon, String name, String time
    ) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(15), dp(15), dp(15), dp(15));
        card.setBackground(roundBox(green, 18));

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(10);
        prayerContainer.addView(card, params);

        TextView symbol = text(icon, 26, gold, false);
        card.addView(symbol,
            new LinearLayout.LayoutParams(dp(45), -2));

        TextView prayer = text(name, 16, white, true);
        card.addView(prayer,
            new LinearLayout.LayoutParams(0, -2, 1));

        TextView timeView = text(time, 22, gold, true);
        card.addView(timeView);
    }

    private double[] calculate(
        double latitude, double longitude, int day
    ) {
        double g = 2 * Math.PI / 365.0 * (day - 1);

        double eq = 229.18 * (
            0.000075 + 0.001868 * Math.cos(g)
            - 0.032077 * Math.sin(g)
            - 0.014615 * Math.cos(2 * g)
            - 0.040849 * Math.sin(2 * g)
        );

        double dec = 0.006918
            - 0.399912 * Math.cos(g)
            + 0.070257 * Math.sin(g)
            - 0.006758 * Math.cos(2 * g)
            + 0.000907 * Math.sin(2 * g)
            - 0.002697 * Math.cos(3 * g)
            + 0.001480 * Math.sin(3 * g);

        double lat = Math.toRadians(latitude);
        double noon = 720 - 4 * longitude - eq + 300;

        double fajr = noon - hourAngle(lat, dec, -18);
        double sunrise = noon - hourAngle(lat, dec, -0.833);
        double dhuhr = noon + 2;

        double asrAngle = Math.toDegrees(Math.atan(
            1.0 / (2.0 + Math.tan(Math.abs(lat - dec)))
        ));

        double asr = noon + hourAngle(lat, dec, asrAngle);
        double sunset = noon + hourAngle(lat, dec, -0.833);
        double isha = noon + hourAngle(lat, dec, -17);

        return new double[] {
            fajr, sunrise, dhuhr, asr, sunset, isha
        };
    }

    private double hourAngle(
        double lat, double dec, double altitude
    ) {
        double h = Math.toRadians(altitude);

        double numerator = Math.sin(h)
            - Math.sin(lat) * Math.sin(dec);
        double denominator = Math.cos(lat) * Math.cos(dec);

        double c = numerator / denominator;
        c = Math.max(-1, Math.min(1, c));

        return Math.toDegrees(Math.acos(c)) * 4;
    }

    private String formatTime(double value) {
        int total = ((int) Math.round(value) % 1440 + 1440) % 1440;
        return String.format(
            Locale.US, "%02d:%02d", total / 60, total % 60
        );
    }

    private TextView text(
        String value, int size, int color, boolean bold
    ) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);

        if (bold) {
            t.setTypeface(null, Typeface.BOLD);
        }

        return t;
    }

    private GradientDrawable roundBox(
        int color, int radius
    ) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    private int dp(int value) {
        return (int) (value * getResources()
            .getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout.LayoutParams match() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private static class City {
        final String name;
        final double latitude;
        final double longitude;

        City(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
                }
