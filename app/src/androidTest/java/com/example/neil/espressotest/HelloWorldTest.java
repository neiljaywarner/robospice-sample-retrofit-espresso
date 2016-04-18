package com.example.neil.espressotest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.octo.android.robospice.sample.retrofit.R;
import com.octo.android.robospice.sample.retrofit.SampleSpiceActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldTest {

    @Rule
    public ActivityTestRule<SampleSpiceActivity> mainActivityActivityTestRule = new ActivityTestRule<SampleSpiceActivity>(SampleSpiceActivity.class);

    @Test
    public void helloWorld_test()
    {
        onView(ViewMatchers.withId(R.id.textview_lorem_ipsum)).check(matches(withText("RoboSpice contributors :")));

    }
}
