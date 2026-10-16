
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HijriCalendarActivity extends Activity {

    private final int bg = Color.rgb(7, 23, 22);
    private final int gold = Color.rgb(238, 195, 102);
    private final int white = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(24), dp(22), dp(24));
        root.setBackgroundColor(bg);

        TextView back = new TextView(this);
        back.setText("‹  Бозгашт");
        back.setTextColor(gold);
        back.setTextSize(18);
        back.setOnClickListener(v -> finish());
        root.addView(back, params(-1, dp(45)));

        TextView moon = new TextView(this);
        moon.setText("☾");
        moon.setTextSize(54);
        moon.setTextColor(gold);
        moon.setGravity(Gravity.CENTER);
        root.addView(moon, params(-1, dp(90)));

        TextView title = new TextView(this);
        title.setText("ТАҚВИМИ ҲИҶРӢ");
        title.setTextColor(white);
        title.setTextSize(25);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, params(-1, dp(55)));

        TextView subtitle = new TextView(this);
        subtitle.setText("Санаи имрӯз");
        subtitle.setTextColor(gold);
        subtitle.setTextSize(17);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle, params(-1, dp(45)));

        Calendar now = Calendar.getInstance();

        SimpleDateFormat hijriFormat;
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            hijriFormat = new SimpleDateFormat(
                    "d MMMM yyyy G",
                    new Locale("ar", "SA")
            );
        } else {
            hijriFormat = null;
        }

        TextView hijriDate = new TextView(this);
        hijriDate.setTextSize(23);
        hijriDate.setTextColor(white);
        hijriDate.setGravity(Gravity.CENTER);
        hijriDate.setTypeface(null, Typeface.BOLD);

        if (hijriFormat != null) {
            hijriDate.setText(hijriFormat.format(new Date()));
        } else {
            hijriDate.setText(
                    "Тақвими ҳиҷрӣ дар ин телефон дастрас нест"
            );
        }

        LinearLayout.LayoutParams dateParams = params(-1, dp(100));
        dateParams.topMargin = dp(20);
        root.addView(hijriDate, dateParams);

        TextView gregorian = new TextView(this);
        gregorian.setText(
                "Санаи мелодӣ: " +
                new SimpleDateFormat(
                        "dd MMMM yyyy",
                        new Locale("tg")
                ).format(new Date())
        );
        gregorian.setTextColor(0xFFB5C9C0);
        gregorian.setTextSize(16);
        gregorian.setGravity(Gravity.CENTER);
        root.addView(gregorian, params(-1, dp(55)));

        TextView note = new TextView(this);
        note.setText(
                "Эзоҳ: санаи ҳиҷрӣ вобаста ба мушоҳидаи ҳилол " +
                "метавонад як рӯз фарқ кунад."
        );
        note.setTextColor(gold);
        note.setTextSize(14);
        note.setGravity(Gravity.CENTER);
        note.setPadding(dp(8), dp(12), dp(8), dp(12));

        LinearLayout.LayoutParams noteParams = params(-1, -2);
        noteParams.topMargin = dp(30);
        root.addView(note, noteParams);

        setContentView(root);
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout.LayoutParams params(int w, int h) {
        return new LinearLayout.LayoutParams(
                w,
                h == -2 ? ViewGroup.LayoutParams.WRAP_CONTENT : h
        );
    }
  }
          
