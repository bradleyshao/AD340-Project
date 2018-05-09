package com.example.yitongshao.ad340v2;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class validateAct {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateSecondActivity() {
        // check that the button is there
        onView(withId(R.id.send)).perform(click());
        onView(withId(R.id.textView))
                .check(matches(withText(("Started"))));
        pressBack();
        onView(withId(R.id.send))
                .check(matches(withText(("Start new activity"))));
    }
}