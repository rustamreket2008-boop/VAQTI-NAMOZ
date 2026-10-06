package com.example.namaz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.*;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity {

    LinearLayout main;
    TextView cityTitle, dateText;

    String[] cities = {
            "Душанбе",
            "Хуҷанд",
            "Бохтар",
            "Кӯлоб",
            "Истаравшан",
            "Турсунзода",
            "Панҷакент",
            "Ҳисор",
            "Ваҳдат",
            "Роғун",
            "Норак",
            "Исфара",
            "Конибодом",
            "Варзоб",
            "Рашт",
            "Файзобод",
            "Данғара",
            "Восеъ",
            "Мастчоҳ",
            "Ашт"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(24, 35, 24, 30);

        GradientDrawable background = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        Color.rgb(10, 35, 55),
                        Color.rgb(20, 90, 80)
                }
        );
        main.setBackground(background);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(main);

        // Сарлавҳа
        TextView title = new TextView(this);
        title.setText("🕌 ВАҚТИ НАМОЗ");
        title.setTextColor(Color.WHITE);
        title.setTextSize(28);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 10, 0, 25);
        main.addView(title);

        // Шаҳр
        cityTitle = new TextView(this);
        cityTitle.setText("📍 Душанбе");
        cityTitle.setTextColor(Color.WHITE);
        cityTitle.setTextSize(22);
        cityTitle.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        cityTitle.setGravity(Gravity.CENTER);
        cityTitle.setPadding(0, 10, 0, 10);
        main.addView(cityTitle);

        // Интихоби шаҳр
        Spinner spinner = new Spinner(this);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        cities
                );

        spinner.setAdapter(adapter);

        main.addView(spinner);

        spinner.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                        cityTitle.setText("📍 " + cities[position]);
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent) {
                    }
                }
        );

        // Сана
        dateText = new TextView(this);
        dateText.setText("📅 Имрӯз — Тоҷикистон");
        dateText.setTextColor(Color.WHITE);
        dateText.setTextSize(17);
        dateText.setGravity(Gravity.CENTER);
        dateText.setPadding(0, 25, 0, 20);
        main.addView(dateText);

        // Вақтҳои намоз
        addPrayer("🌅 Бомдод", "05:10");
        addPrayer("☀️ Пешин", "12:30");
        addPrayer("🌤 Аср", "16:00");
        addPrayer("🌇 Шом", "18:20");
        addPrayer("🌙 Хуфтан", "19:45");

        TextView footer = new TextView(this);
        footer.setText("🇹🇯 Тоҷикистон • Вақти намоз");
        footer.setTextColor(Color.LTGRAY);
        footer.setTextSize(14);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, 30, 0, 10);

        main.addView(footer);

        setContentView(scroll);
    }

    private void addPrayer(String name, String time) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(25, 20, 25, 20);

        GradientDrawable cardBg = new GradientDrawable();
        cardBg.setColor(Color.argb(70, 255, 255, 255));
        cardBg.setCornerRadius(30);

        card.setBackground(cardBg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, 8, 0, 8);
        card.setLayoutParams(params);

        TextView prayerName = new TextView(this);
        prayerName.setText(name);
        prayerName.setTextColor(Color.WHITE);
        prayerName.setTextSize(19);
        prayerName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView prayerTime = new TextView(this);
        prayerTime.setText(time);
        prayerTime.setTextColor(Color.WHITE);
        prayerTime.setTextSize(22);
        prayerTime.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        prayerTime.setGravity(Gravity.RIGHT);

        LinearLayout.LayoutParams nameParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                );

        card.addView(prayerName, nameParams);
        card.addView(prayerTime);

        main.addView(card);
    }
}

Муҳим: сатри аввали код — "package com.example.namaz;" — бояд ба номи package-и лоиҳаи ту мувофиқ бошад. Агар номи package-и ту дигар бошад, скриншот фирист ё номашро навис, ман ҳамон сатрро дуруст мекунам.
