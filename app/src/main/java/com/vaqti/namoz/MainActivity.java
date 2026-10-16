
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

    int green = Color.rgb(8, 78, 58);
    int gold = Color.rgb(222, 181, 94);
    int cream = Color.rgb(247, 246, 239);

    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(green);
        getWindow().setNavigationBarColor(green);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(cream);

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 22, 20, 24);
        scroll.addView(main);

        TextView title = text("🕌  ВАҚТИ НАМОЗ", 27, Color.WHITE, true);
        title.setGravity(Gravity.CENTER);

        TextView subtitle = text("ТОҶИКИСТОН  •  ИБОДАТИ ОРОМ", 12, gold, true);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 10, 0, 0);

        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.VERTICAL);
        header.setPadding(18, 28, 18, 28);
        header.setBackground(shape(green, 26));
        header.addView(title);
        header.addView(subtitle);
        main.addView(header);

        addGap(22);

        TextView cityTitle = text("📍  Шаҳри худро интихоб кунед", 17, green, true);
        main.addView(cityTitle);

        String[] cities = {
            "Душанбе", "Хуҷанд", "Бохтар", "Кӯлоб",
            "Панҷакент", "Истаравшан", "Исфара",
            "Хоруғ", "Турсунзода", "Ваҳдат"
        };

        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(
            -1, 55);
        sp.setMargins(0, 12, 0, 0);
        spinner.setPadding(12, 0, 12, 0);
        spinner.setBackground(shape(Color.WHITE, 16));
        main.addView(spinner, sp);

        addGap(24);

        TextView section = text("Вақтҳои намоз", 23, green, true);
        main.addView(section);

        TextView date = text(
            "Вақтҳои дақиқ баъд пайваст карда мешаванд",
            13, Color.DKGRAY, false);
        date.setPadding(0, 7, 0, 16);
        main.addView(date);

        String[] names = {
            "Бомдод", "Пешин", "Аср", "Шом", "Хуфтан"
        };

        String[] icons = {"🌄", "☀️", "🌤️", "🌇", "🌙"};

        for (int i = 0; i < names.length; i++) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.HORIZONTAL);
            card.setGravity(Gravity.CENTER_VERTICAL);
            card.setPadding(18, 18, 18, 18);
            card.setBackground(shape(Color.WHITE, 20));
            card.setElevation(3);

            LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, -2);
            params.setMargins(0, 0, 0, 12);
            main.addView(card, params);

            TextView icon = text(icons[i], 26, green, false);
            card.addView(icon);

            TextView name = text(names[i], 18, green, true);
            LinearLayout.LayoutParams np =
                new LinearLayout.LayoutParams(0, -2, 1);
            np.setMargins(15, 0, 0, 0);
            card.addView(name, np);

            TextView time = text("--:--", 20, green, true);
            card.addView(time);
        }

        addGap(12);

        TextView reminder = text(
            "🤲  Аллоҳ ибодатҳои шуморо қабул кунад",
            16, green, true);
        reminder.setGravity(Gravity.CENTER);
        reminder.setPadding(8, 22, 8, 22);
        reminder.setBackground(shape(Color.WHITE, 20));
        main.addView(reminder);

        TextView footer = text(
            "VAQTI-NAMOZ  •  Тоҷикистон",
            12, Color.GRAY, false);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, 22, 0, 4);
        main.addView(footer);

        setContentView(scroll);
    }

    TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(null, Typeface.BOLD);
        return t;
    }

    GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    void addGap(int height) {
        android.view.View v = new android.view.View(this);
        main.addView(v, new LinearLayout.LayoutParams(-1, height));
    }
}
