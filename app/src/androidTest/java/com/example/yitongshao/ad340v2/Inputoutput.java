package com.example.yitongshao.ad340v2;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class Inputoutput {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void activityLaunch() {
        Espresso.onView(ViewMatchers.withId(R.id.editText)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.one)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void textInputOutput() {
        Espresso.onView(ViewMatchers.withId(R.id.text)).perform(ViewActions.typeText("This is a test."));
        Espresso.onView(ViewMatchers.withId(R.id.editText)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.withText("This is a test.")));
    }
}