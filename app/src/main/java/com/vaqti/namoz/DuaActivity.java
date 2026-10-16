
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DuaActivity extends Activity {

    private final int bg = Color.rgb(7, 23, 22);
    private final int gold = Color.rgb(238, 195, 102);
    private final int white = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(8, 65, 49));
        getWindow().setNavigationBarColor(bg);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(20), dp(20), dp(20), dp(25));
        content.setBackgroundColor(bg);

        TextView back = new TextView(this);
        back.setText("‹  Бозгашт");
        back.setTextColor(gold);
        back.setTextSize(18);
        back.setPadding(0, dp(8), 0, dp(18));
        back.setOnClickListener(v -> finish());
        content.addView(back);

        TextView title = new TextView(this);
        title.setText("🤲 ДУОҲОИ ҲАРРӮЗА");
        title.setTextColor(white);
        title.setTextSize(24);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, dp(15), 0, dp(20));
        content.addView(title);

        addDua(content,
                "1. Дуои субҳ",
                "اللَّهُمَّ بِكَ أَصْبَحْنَا وَبِكَ أَمْسَيْنَا",
                "Маъно: Худоё, бо ёрии Ту субҳ кардем "
                + "ва бо ёрии Ту шом мекунем.");

        addDua(content,
                "2. Пеш аз хӯрок",
                "بِسْمِ اللَّهِ",
                "Маъно: Ба номи Аллоҳ.");

        addDua(content,
                "3. Баъд аз хӯрок",
                "الْحَمْدُ لِلَّهِ",
                "Маъно: Ҳамду сано барои Аллоҳ аст.");

        addDua(content,
                "4. Пеш аз хоб",
                "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا",
                "Маъно: Худоё, ба номи Ту мехобам "
                + "ва бедор мешавам.");

        addDua(content,
                "5. Дуои бахшиш",
                "أَسْتَغْفِرُ اللَّهَ",
                "Маъно: Аз Аллоҳ бахшиш мехоҳам.");

        addDua(content,
                "6. Дуои дониш",
                "رَبِّ زِدْنِي عِلْمًا",
                "Маъно: Парвардигоро, дониши маро зиёд кун.");

        ScrollView scroll = new ScrollView(this);
        scroll.addView(content);
        setContentView(scroll);
    }

    private void addDua(LinearLayout root, String heading,
                        String arabic, String meaning) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(15), dp(16), dp(15));
        card.setBackgroundColor(Color.rgb(15, 43, 36));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(14);
        root.addView(card, cardParams);

        TextView h = new TextView(this);
        h.setText(heading);
        h.setTextColor(gold);
        h.setTextSize(18);
        h.setTypeface(null, Typeface.BOLD);
        card.addView(h);

        TextView a = new TextView(this);
        a.setText(arabic);
        a.setTextColor(white);
        a.setTextSize(24);
        a.setGravity(Gravity.CENTER);
        a.setTextDirection(TextView.TEXT_DIRECTION_RTL);
        a.setPadding(0, dp(18), 0, dp(18));
        card.addView(a);

        TextView m = new TextView(this);
        m.setText(meaning);
        m.setTextColor(Color.rgb(205, 220, 212));
        m.setTextSize(16);
        card.addView(m);
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }
                        }
          
