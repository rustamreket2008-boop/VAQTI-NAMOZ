
package com.vaqti.namoz;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;

public class TasbihActivity extends Activity {

    private int count = 0;
    private int target = 33;

    private final int background = Color.rgb(7, 23, 22);
    private final int green = Color.rgb(8, 65, 49);
    private final int gold = Color.rgb(238, 195, 102);
    private final int white = Color.WHITE;

    private TextView countText;
    private TextView targetText;
    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(green);
        getWindow().setNavigationBarColor(background);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(22), dp(24), dp(22), dp(24));
        root.setBackgroundColor(background);

        TextView back = new TextView(this);
        back.setText("‹  Бозгашт");
        back.setTextSize(18);
        back.setTextColor(gold);
        back.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        back.setOnClickListener(v -> finish());
        root.addView(back, match());

        TextView icon = new TextView(this);
        icon.setText("☾");
        icon.setTextSize(48);
        icon.setTextColor(gold);
        icon.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams iconParams = match();
        iconParams.topMargin = dp(20);
        root.addView(icon, iconParams);

        TextView heading = new TextView(this);
        heading.setText("ТАСБЕҲИ ЭЛЕКТРОНӢ");
        heading.setTextSize(23);
        heading.setTextColor(white);
        heading.setTypeface(null, Typeface.BOLD);
        heading.setGravity(Gravity.CENTER);
        root.addView(heading, match());

        TextView subtitle = new TextView(this);
        subtitle.setText("Зикр кун ва ҳисобатро нигоҳ дор");
        subtitle.setTextSize(14);
        subtitle.setTextColor(0xFFB5C9C0);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams subParams = match();
        subParams.topMargin = dp(8);
        root.addView(subtitle, subParams);

        targetText = new TextView(this);
        targetText.setText("Ҳадаф: 33");
        targetText.setTextSize(17);
        targetText.setTextColor(gold);
        targetText.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams targetParams = match();
        targetParams.topMargin = dp(28);
        root.addView(targetText, targetParams);

        countText = new TextView(this);
        countText.setText("0");
        countText.setTextSize(58);
        countText.setTextColor(white);
        countText.setTypeface(null, Typeface.BOLD);
        countText.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams countParams = match();
        countParams.topMargin = dp(15);
        root.addView(countText, countParams);

        Button zikrButton = new Button(this);
        zikrButton.setText("ЗИКР  +1");
        zikrButton.setTextSize(24);
        zikrButton.setTextColor(background);
        zikrButton.setTypeface(null, Typeface.BOLD);
        zikrButton.setBackground(roundBox(gold, 100));

        LinearLayout.LayoutParams zikrParams =
                new LinearLayout.LayoutParams(dp(220), dp(220));
        zikrParams.gravity = Gravity.CENTER;
        zikrParams.topMargin = dp(24);
        root.addView(zikrButton, zikrParams);

        messageText = new TextView(this);
        messageText.setText("Оғоз кун!");
        messageText.setTextSize(15);
        messageText.setTextColor(gold);
        messageText.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams msgParams = match();
        msgParams.topMargin = dp(15);
        root.addView(messageText, msgParams);

        LinearLayout targets = new LinearLayout(this);
        targets.setGravity(Gravity.CENTER);
        targets.setOrientation(LinearLayout.HORIZONTAL);

        Button b33 = makeButton("33");
        Button b100 = makeButton("100");
        Button b1000 = makeButton("1000");

        targets.addView(b33, buttonParams());
        targets.addView(b100, buttonParams());
        targets.addView(b1000, buttonParams());

        LinearLayout.LayoutParams targetsParams = match();
        targetsParams.topMargin = dp(20);
        root.addView(targets, targetsParams);

        Button resetButton = makeButton("⟲  Аз нав оғоз кардан");
        LinearLayout.LayoutParams resetParams =
                new LinearLayout.LayoutParams(-1, dp(52));
        resetParams.topMargin = dp(18);
        root.addView(resetButton, resetParams);

        zikrButton.setOnClickListener(v -> {
            count++;
            updateCounter();

            if (count == target) {
                messageText.setText("Офарин! Ҳадаф иҷро шуд 💚");
            }
        });

        b33.setOnClickListener(v -> setTarget(33));
        b100.setOnClickListener(v -> setTarget(100));
        b1000.setOnClickListener(v -> setTarget(1000));

        resetButton.setOnClickListener(v -> {
            count = 0;
            updateCounter();
            messageText.setText("Ҳисоб аз нав оғоз шуд");
        });

        setContentView(root);
    }

    private void setTarget(int value) {
        target = value;
        count = 0;
        updateCounter();
        messageText.setText("Ҳадафи нав интихоб шуд");
    }

    private void updateCounter() {
        countText.setText(String.valueOf(count));
        targetText.setText("Ҳадаф: " + target);

        if (count >= target) {
            messageText.setText("Офарин! Ҳадаф иҷро шуд 💚");
        }
    }

    private Button makeButton(String label) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextColor(gold);
        button.setTextSize(15);
        button.setAllCaps(false);
        button.setBackground(roundBox(green, 14));
        return button;
    }

    private LinearLayout.LayoutParams buttonParams() {
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(0, dp(50), 1);
        p.setMargins(dp(4), 0, dp(4), 0);
        return p;
    }

    private GradientDrawable roundBox(int color, int radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(color);
        shape.setCornerRadius(dp(radius));
        return shape;
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout.LayoutParams match() {
        return new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
                                }
          
