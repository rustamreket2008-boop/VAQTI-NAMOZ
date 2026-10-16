
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MosqueActivity extends Activity {

    private final int bg = Color.rgb(7, 23, 22);
    private final int green = Color.rgb(8, 65, 49);
    private final int gold = Color.rgb(238, 195, 102);
    private final int white = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(green);
        getWindow().setNavigationBarColor(bg);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(24), dp(22), dp(24));
        root.setBackgroundColor(bg);

        TextView back = new TextView(this);
        back.setText("‹  Бозгашт");
        back.setTextColor(gold);
        back.setTextSize(18);
        back.setOnClickListener(v -> finish());
        root.addView(back);

        TextView icon = new TextView(this);
        icon.setText("🕌");
        icon.setTextSize(54);
        icon.setGravity(Gravity.CENTER);
        root.addView(icon, params(-1, dp(110)));

        TextView title = new TextView(this);
        title.setText("ҶУСТУҶӮИ МАСҶИДҲО");
        title.setTextColor(white);
        title.setTextSize(23);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, params(-1, dp(55)));

        TextView description = new TextView(this);
        description.setText(
                "Масҷидҳоро дар наздикии худ ё дар Тоҷикистон ёбед."
        );
        description.setTextColor(0xFFB5C9C0);
        description.setTextSize(16);
        description.setGravity(Gravity.CENTER);
        description.setPadding(0, dp(10), 0, dp(20));
        root.addView(description, params(-1, -2));

        Button nearby = makeButton("📍  Масҷидҳои наздик");
        root.addView(nearby, params(-1, dp(58)));

        Button tajikistan = makeButton("🇹🇯  Масҷидҳо дар Тоҷикистон");
        LinearLayout.LayoutParams p = params(-1, dp(58));
        p.topMargin = dp(14);
        root.addView(tajikistan, p);

        TextView note = new TextView(this);
        note.setText(
                "Харита дар Google Maps ё барномаи харитаи телефони "
                + "шумо кушода мешавад."
        );
        note.setTextColor(gold);
        note.setTextSize(13);
        note.setGravity(Gravity.CENTER);
        note.setPadding(0, dp(25), 0, dp(10));
        root.addView(note, params(-1, -2));

        nearby.setOnClickListener(v -> openMaps(
                "mosques near me", true));

        tajikistan.setOnClickListener(v -> openMaps(
                "mosques in Tajikistan", false));

        setContentView(root);
    }

    private void openMaps(String query, boolean nearby) {
        Uri uri;

        if (nearby) {
            uri = Uri.parse(
                    "geo:0,0?q=" + Uri.encode(query));
        } else {
            uri = Uri.parse(
                    "geo:38.5598,68.7870?q="
                    + Uri.encode(query));
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Intent webIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://www.google.com/maps/search/?api=1&query="
                            + Uri.encode(query)
                    )
            );
            startActivity(webIntent);
        }
    }

    private Button makeButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextSize(16);
        b.setTextColor(white);
        b.setAllCaps(false);
        b.setBackgroundColor(green);
        return b;
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout.LayoutParams params(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }
  }
          
