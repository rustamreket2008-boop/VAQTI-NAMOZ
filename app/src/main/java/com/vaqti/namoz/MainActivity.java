
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private final int GREEN = Color.rgb(7, 91, 67);
    private final int DARK_GREEN = Color.rgb(4, 58, 43);
    private final int GOLD = Color.rgb(234, 196, 93);
    private final int BG = Color.rgb(242, 247, 243);
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(DARK_GREEN);
        getWindow().setNavigationBarColor(DARK_GREEN);
        getWindow().getDecorView().setSystemUiVisibility(0);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(BG);

        android.widget.ScrollView scroll = new android.widget.ScrollView(this);
        scroll.setFillViewport(true);
        scroll.addView(root);

        setContentView(scroll);
        createScreen();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius * getResources().getDisplayMetrics().density);
        return d;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private LinearLayout.LayoutParams params(
            int width, int height) {
        return new LinearLayout.LayoutParams(width, height);
    }

    private void createScreen() {
        int pad = (int)(18 * getResources().getDisplayMetrics().density);

        // Сарлавҳаи сабзу тиллоӣ
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.VERTICAL);
        header.setGravity(Gravity.CENTER);
        header.setPadding(pad, pad * 2, pad, pad * 2);
        header.setBackground(shape(GREEN, 28));

        TextView logo = text("☪", 65, GOLD, true);
        header.addView(logo, params(-1, -2));

        TextView title = text("ВАҚТИ НАМОЗ", 27, Color.WHITE, true);
        header.addView(title, params(-1, -2));

        TextView subtitle = text(
                "Тоҷикистон • تقويم الصلاة", 14, GOLD, false);
        header.addView(subtitle, params(-1, -2));

        TextView badge = text(
                "✦  НАМОЗ — НУРИ ДИЛ  ✦", 12, Color.WHITE, true);
        LinearLayout.LayoutParams bp = params(-1, -2);
        bp.topMargin = pad;
        header.addView(badge, bp);

        LinearLayout.LayoutParams hp = params(-1, -2);
        hp.setMargins(pad, pad, pad, 0);
        root.addView(header, hp);

        // Интихоби шаҳр
        TextView cityTitle = text(
                "📍  Шаҳри худро интихоб кунед", 17, GREEN, true);
        cityTitle.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams ctp = params(-1, -2);
        ctp.setMargins(pad, pad * 2, pad, 8);
        root.addView(cityTitle, ctp);

        Spinner cities = new Spinner(this);
        String[] cityNames = {
                "Душанбе", "Хуҷанд", "Бохтар", "Кӯлоб",
                "Панҷакент", "Истаравшан", "Исфара",
                "Хоруғ", "Турсунзода", "Ваҳдат"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                cityNames
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cities.setAdapter(adapter);
        cities.setBackground(shape(Color.WHITE, 16));

        LinearLayout.LayoutParams sp = params(-1, 52);
        sp.setMargins(pad, 0, pad, pad);
        root.addView(cities, sp);

        // Карти сана
        LinearLayout dateCard = new LinearLayout(this);
        dateCard.setOrientation(LinearLayout.VERTICAL);
        dateCard.setGravity(Gravity.CENTER);
        dateCard.setPadding(pad, pad, pad, pad);
        dateCard.setBackground(shape(DARK_GREEN, 20));

        dateCard.addView(text(
                "ВАҚТҲОИ НАМОЗ", 18, GOLD, true),
                params(-1, -2));

        dateCard.addView(text(
                "Ҷадвали намоз барои шаҳри интихобшуда",
                13, Color.WHITE, false),
                params(-1, -2));

        LinearLayout.LayoutParams dp = params(-1, -2);
        dp.setMargins(pad, 0, pad, pad);
        root.addView(dateCard, dp);

        // Вақтҳои намоз
        addPrayer("🌅", "Бомдод", "--:--");
        addPrayer("☀️", "Пешин", "--:--");
        addPrayer("🌤️", "Аср", "--:--");
        addPrayer("🌇", "Шом", "--:--");
        addPrayer("🌙", "Хуфтан", "--:--");

        TextView note = text(
                "Вақтҳо ҳоло намоишӣ ҳастанд.\n" +
                "Барои гирифтани вақтҳои воқеӣ манбаи маълумот лозим аст.",
                12, Color.DKGRAY, false);

        note.setPadding(pad, pad, pad, pad);
        root.addView(note, params(-1, -2));

        TextView footer = text(
                "© ВАҚТИ НАМОЗ • ТОҶИКИСТОН",
                12, GREEN, true);
        footer.setPadding(0, pad, 0, pad * 2);
        root.addView(footer, params(-1, -2));
    }

    private void addPrayer(String icon, String name, String time) {
        int pad = (int)(14 * getResources().getDisplayMetrics().density);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(pad, pad, pad, pad);
        card.setBackground(shape(Color.WHITE, 18));

        TextView symbol = text(icon, 26, GREEN, false);
        card.addView(symbol, params(55, 48));

        TextView prayerName = text(name, 17, GREEN, true);
        prayerName.setGravity(Gravity.CENTER_VERTICAL);
        card.addView(prayerName,
                new LinearLayout.LayoutParams(0, -2, 1));

        TextView prayerTime = text(time, 20, GREEN, true);
        card.addView(prayerTime, params(85, 48));

        LinearLayout.LayoutParams cp = params(-1, -2);
        int margin = (int)(7 * getResources().getDisplayMetrics().density);
        cp.setMargins(margin * 2, margin, margin * 2, margin);
        root.addView(card, cp);
    }
            }
