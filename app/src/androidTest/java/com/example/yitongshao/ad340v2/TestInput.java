package com.example.yitongshao.ad340v2;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TestInput {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp(){
        String INPUT =  "Yo";
        onView(withId(R.id.editText)).perform(typeText(INPUT));
        onView(withId(R.id.send)).perform(click());
    }

    //test if second activity is displayed
    @Test
    public void sendInputActivity() throws Exception{
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }

}
