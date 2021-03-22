package com.ramotion.paperonboarding.examples.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class PaperOnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_custom);

        final PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(R.id.onboardingRootView), getDataForOnboarding(), getApplicationContext());

        final TextView skipTextView = findViewById(R.id.skipText);
        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Skip text clicked", Toast.LENGTH_SHORT).show();

            }
        });

        final TextView nextTextView = findViewById(R.id.nextText);
        nextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine.toggleContent(false);
            }
        });

        final TextView finishTextView = findViewById(R.id.finishText);
        finishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Finish text clicked", Toast.LENGTH_SHORT).show();
            }
        });

        engine.setOnChangeListener(new PaperOnboardingOnChangeListener() {
            @Override
            public void onPageChanged(int oldElementIndex, int newElementIndex) {
                Toast.makeText(getApplicationContext(), "Swiped from " + oldElementIndex + " to " + newElementIndex, Toast.LENGTH_SHORT).show();
                if (newElementIndex == 0 || newElementIndex+1 != engine.getSize()) {
                    nextTextView.setVisibility(View.VISIBLE);
                } else {
                    nextTextView.setVisibility(View.GONE);
                }
                if(newElementIndex+1 == engine.getSize()) {
                    finishTextView.setVisibility(View.VISIBLE);
                    skipTextView.setVisibility(View.GONE);
                } else {
                    finishTextView.setVisibility(View.GONE);
                    skipTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                // Probably here will be your exit action
                //Toast.makeText(getApplicationContext(), "Swiped out right", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Just example data for Onboarding
    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        // prepare data
        PaperOnboardingPage scr1 = new PaperOnboardingPage("01", "컨트롤이 쉬워진\n마사지 리모컨을\n사용해보세요",
                Color.parseColor("#ECF0F3"), R.drawable.gif_splash_screen1, R.drawable.bg_selected_indicator, true);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("02", "컨트롤이 쉬워진\n마사지 리모컨을\n사용해보세요",
                Color.parseColor("#ECF0F3"), R.drawable.gif_splash_screen2, R.drawable.bg_selected_indicator, true);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("03", "컨트롤이 쉬워진\n마사지 리모컨을\n사용해보세요",
                Color.parseColor("#ECF0F3"), R.drawable.gif_splash_screen3, R.drawable.bg_selected_indicator, true);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        return elements;
    }
}
