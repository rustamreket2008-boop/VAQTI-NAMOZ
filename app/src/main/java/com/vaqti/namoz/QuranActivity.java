
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class QuranActivity extends Activity {

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
        title.setText("📖 ҚУРЪОНИ КАРИМ");
        title.setTextColor(white);
        title.setTextSize(24);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, dp(15), 0, dp(20));
        content.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Сураҳои кӯтоҳ");
        subtitle.setTextColor(gold);
        subtitle.setTextSize(18);
        content.addView(subtitle);

        addSurah(content, "Сураи Фотиҳа — 1",
                "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ\n"
                + "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ\n"
                + "الرَّحْمَٰنِ الرَّحِيمِ\n"
                + "مَالِكِ يَوْمِ الدِّينِ\n"
                + "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ\n"
                + "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ\n"
                + "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ "
                + "غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
                "Маънои умумӣ: Ҳамду сано барои Аллоҳ, "
                + "Парвардигори ҷаҳониён. Мо танҳо Туро "
                + "ибодат мекунем ва аз Ту ёрӣ мехоҳем. "
                + "Моро ба роҳи рост ҳидоят кун.");

        addSurah(content, "Сураи Ихлос — 112",
                "قُلْ هُوَ اللَّهُ أَحَدٌ\n"
                + "اللَّهُ الصَّمَدُ\n"
                + "لَمْ يَلِدْ وَلَمْ يُولَدْ\n"
                + "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",
                "Маънои умумӣ: Бигӯ, Ӯ Аллоҳ ягона аст. "
                + "Аллоҳ бениёз аст. На зодааст ва на "
                + "зода шудааст ва ҳеҷ кас ҳамтои Ӯ нест.");

        addSurah(content, "Сураи Фалақ — 113",
                "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ\n"
                + "مِن شَرِّ مَا خَلَقَ\n"
                + "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ\n"
                + "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ\n"
                + "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ",
                "Маънои умумӣ: Ба Парвардигори субҳ "
                + "аз шарри офаридаҳо ва ҳасад паноҳ мебарам.");

        addSurah(content, "Сураи Нос — 114",
                "قُلْ أَعُوذُ بِرَبِّ النَّاسِ\n"
                + "مَلِكِ النَّاسِ\n"
                + "إِلَٰهِ النَّاسِ\n"
                + "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ\n"
                + "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ\n"
                + "مِنَ الْجِنَّةِ وَالنَّاسِ",
                "Маънои умумӣ: Ба Парвардигор, Подшоҳ "
                + "ва Маъбуди мардум аз васвасаи бад паноҳ мебарам.");

        TextView note = new TextView(this);
        note.setText("Эзоҳ: барои омӯзиш матни арабиро "
                + "бо нусхаи боэътимоди Қуръон муқоиса кунед.");
        note.setTextColor(gold);
        note.setTextSize(13);
        note.setPadding(0, dp(15), 0, dp(20));
        content.addView(note);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(content);
        setContentView(scroll);
    }

    private void addSurah(LinearLayout root, String heading,
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
        h.setTextSize(19);
        h.setTypeface(null, Typeface.BOLD);
        card.addView(h);

        TextView a = new TextView(this);
        a.setText(arabic);
        a.setTextColor(white);
        a.setTextSize(24);
        a.setGravity(Gravity.CENTER);
        a.setTextDirection(TextView.TEXT_DIRECTION_RTL);
        a.setLineSpacing(dp(10), 1f);
        a.setPadding(0, dp(18), 0, dp(18));
        card.addView(a);

        TextView m = new TextView(this);
        m.setText(meaning);
        m.setTextColor(Color.rgb(205, 220, 212));
        m.setTextSize(15);
        card.addView(m);
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }
                        }
