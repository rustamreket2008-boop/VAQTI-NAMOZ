package com.vaqti.namoz;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private TextView nextPrayerName;
    private TextView nextPrayerTime;
    private TextView countdown;

    private final Handler handler = new Handler();

    private String city = "Душанбе";

    private final String[] prayerNames = {
            "Бомдод",
            "Пешин",
            "Аср",
            "Шом",
            "Хуфтан"
    };

    private final String[] prayerTimes = {
            "05:10",
            "12:30",
            "15:45",
            "18:05",
            "19:30"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(7, 16, 31));
        getWindow().setNavigationBarColor(Color.rgb(7, 16, 31));

        createInterface();
    }

    private void createInterface() {

        ScrollView scrollView = new ScrollView(this);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(18, 20, 18, 30);

        GradientDrawable background = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        Color.rgb(7, 16, 31),
                        Color.rgb(10, 27, 45),
                        Color.rgb(7, 16, 31)
                }
        );

        mainLayout.setBackground(background);

        scrollView.addView(mainLayout);

        createHeader();
        createDate();
        createNextPrayer();
        createPrayerTimes();
        createButtons();

        setContentView(scrollView);
    }

    private void createHeader() {

        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);

        TextView title = new TextView(this);
        title.setText("ВАҚТИ НАМОЗ");
        title.setTextColor(Color.WHITE);
        title.setTextSize(26);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView subtitle = new TextView(this);
        subtitle.setText("Тоҷикистон 🇹🇯");
        subtitle.setTextColor(Color.rgb(130, 190, 185));
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.RIGHT);

        header.addView(title, new LinearLayout.LayoutParams(
                0, 80, 1
        ));

        header.addView(subtitle, new LinearLayout.LayoutParams(
                120, 80
        ));

        mainLayout.addView(header);
    }

    private void createDate() {

        TextView date = new TextView(this);

        SimpleDateFormat format =
                new SimpleDateFormat(
                        "dd.MM.yyyy",
                        Locale.US
                );

        date.setText(
                "Имрӯз • " +
                format.format(new Date())
        );

        date.setTextColor(Color.rgb(165, 180, 195));
        date.setTextSize(15);
        date.setGravity(Gravity.CENTER);

        mainLayout.addView(date, new LinearLayout.LayoutParams(
                -1, 45
        ));
    }

    private void createNextPrayer() {

        MaterialCardView card = new MaterialCardView(this);

        card.setRadius(35);
        card.setCardElevation(10);
        card.setCardBackgroundColor(
                Color.rgb(19, 80, 77)
        );

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER);
        content.setPadding(25, 25, 25, 25);

        TextView label = new TextView(this);
        label.setText("НАМОЗИ НАВБАТӢ");
        label.setTextColor(
                Color.rgb(180, 230, 220)
        );
        label.setTextSize(13);
        label.setGravity(Gravity.CENTER);

        nextPrayerName = new TextView(this);
        nextPrayerName.setText("Пешин");
        nextPrayerName.setTextColor(Color.WHITE);
        nextPrayerName.setTextSize(32);
        nextPrayerName.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );
        nextPrayerName.setGravity(Gravity.CENTER);

        nextPrayerTime = new TextView(this);
        nextPrayerTime.setText("12:30");
        nextPrayerTime.setTextColor(Color.WHITE);
        nextPrayerTime.setTextSize(22);
        nextPrayerTime.setGravity(Gravity.CENTER);

        countdown = new TextView(this);
        countdown.setText("Вақти намоз наздик аст");
        countdown.setTextColor(
                Color.rgb(190, 235, 225)
        );
        countdown.setTextSize(14);
        countdown.setGravity(Gravity.CENTER);

        content.addView(label);
        content.addView(nextPrayerName);
        content.addView(nextPrayerTime);
        content.addView(countdown);

        card.addView(content);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        220
                );

        params.setMargins(0, 18, 0, 20);

        mainLayout.addView(card, params);
    }

    private void createPrayerTimes() {

        TextView heading = new TextView(this);

        heading.setText("Вақтҳои намоз");
        heading.setTextColor(Color.WHITE);
        heading.setTextSize(21);
        heading.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        mainLayout.addView(heading,
                new LinearLayout.LayoutParams(
                        -1,
                        55
                ));

        addPrayerCard(
                "🌅",
                "Бомдод",
                prayerTimes[0]
        );

        addPrayerCard(
                "☀️",
                "Пешин",
                prayerTimes[1]
        );

        addPrayerCard(
                "🌤",
                "Аср",
                prayerTimes[2]
        );

        addPrayerCard(
                "🌇",
                "Шом",
                prayerTimes[3]
        );

        addPrayerCard(
                "🌙",
                "Хуфтан",
                prayerTimes[4]
        );
    }

    private void addPrayerCard(
            String icon,
            String name,
            String time
    ) {

        MaterialCardView card =
                new MaterialCardView(this);

        card.setRadius(25);
        card.setCardElevation(4);
        card.setCardBackgroundColor(
                Color.rgb(17, 29, 47)
        );

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        row.setPadding(
                20,
                8,
                20,
                8
        );

        TextView iconText =
                new TextView(this);

        iconText.setText(icon);
        iconText.setTextSize(25);

        TextView nameText =
                new TextView(this);

        nameText.setText(name);
        nameText.setTextColor(Color.WHITE);
        nameText.setTextSize(18);

        TextView timeText =
                new TextView(this);

        timeText.setText(time);
        timeText.setTextColor(
                Color.rgb(90, 220, 190)
        );
        timeText.setTextSize(20);
        timeText.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );
        timeText.setGravity(
                Gravity.CENTER
        );

        row.addView(
                iconText,
                new LinearLayout.LayoutParams(
                        55,
                        -1
                )
        );

        row.addView(
                nameText,
                new LinearLayout.LayoutParams(
                        0,
                        -1,
                        1
                )
        );

        row.addView(
                timeText,
                new LinearLayout.LayoutParams(
                        100,
                        -1
                )
        );

        card.addView(row);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        78
                );

        params.setMargins(
                0,
                6,
                0,
                6
        );

        mainLayout.addView(card, params);
    }

    private void createButtons() {

        TextView cityButton =
                createButton("📍  Шаҳр: " + city);

        cityButton.setOnClickListener(
                v -> showCities()
        );

        mainLayout.addView(
                cityButton,
                buttonParams()
        );

        TextView settingsButton =
                createButton("⚙️  Танзимот");

        settingsButton.setOnClickListener(
                v -> Toast.makeText(
                        this,
                        "Танзимот дар версияи оянда илова мешавад",
                        Toast.LENGTH_SHORT
                ).show()
        );

        mainLayout.addView(
                settingsButton,
                buttonParams()
        );
    }

    private TextView createButton(String text) {

        TextView button =
                new TextView(this);

        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setTextSize(17);
        button.setGravity(Gravity.CENTER);

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.rgb(20, 35, 55)
        );

        drawable.setCornerRadius(25);

        button.setBackground(drawable);

        return button;
    }

    private LinearLayout.LayoutParams buttonParams() {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        65
                );

        params.setMargins(
                0,
                8,
                0,
                8
        );

        return params;
    }

    private void showCities() {

        final String[] cities = {
                "Душанбе",
                "Хуҷанд",
                "Бохтар",
                "Кӯлоб",
                "Истаравшан",
                "Турсунзода",
                "Панҷакент",
                "Ваҳдат",
                "Ҳисор"
        };

        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Шаҳри худро интихоб кунед")
                        .setItems(
                                cities,
                                (d, which) -> {

                                    city = cities[which];

                                    Toast.makeText(
                                            this,
                                            "Шаҳр интихоб шуд: "
                                                    + city,
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                        )
                        .create();

        dialog.show();
    }
                                 }
