package com.example.neil.espressotest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.octo.android.robospice.sample.retrofit.R;
import com.octo.android.robospice.sample.retrofit.SampleSpiceActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldTest {

    private RobospiceIdlingResource idlingResource;

    /***
     * IMPORTANT NOTE: Screen must be on to run the tests.a
     */
    @Rule
    public ActivityTestRule<SampleSpiceActivity> mainActivityActivityTestRule = new ActivityTestRule<SampleSpiceActivity>(SampleSpiceActivity.class);

    @Before
    public void registerIntentServiceIdlingResource() {
        idlingResource = new RobospiceIdlingResource(mainActivityActivityTestRule.getActivity());
        Espresso.registerIdlingResources(idlingResource);
        onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click());
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void afterNetworkRequestText()
    {
        onView(ViewMatchers.withId(R.id.textview_lorem_ipsum)).check(matches(withText("RoboSpice contributors :\n\n\tstephanenicolas\t(668)\n\trciovati\t(99)\n\tnkeskinov\t(22)\n\tseva-ask\t(10)\n\tweefbellington\t(9)\n\tjeromevdl\t(5)\n\tsoftwaremaverick\t(2)\n\tgkylafas\t(2)\n\tpdegand\t(2)\n\tTannerPerrien\t(2)\n\tricharth\t(2)\n\tshenwill\t(2)\n\tlionleaf\t(1)\n\tagrebnev\t(1)\n\tflorianmski\t(1)\n\tdgyuri\t(1)\n\tjmeiss\t(1)\n\tjorgevila\t(1)\n\tMgamerz\t(1)\n\tintrications\t(1)\n\tmikegr\t(1)\n\tanton-pogonets\t(1)\n\tlukesleeman\t(1)\n\tsplatte\t(1)\n")));
    }

    @Test
    public void beforeNetworkRequestTextIsNotDisplayed()
    {
        onView(withText("RoboSpice contributors :")).check(doesNotExist());
    }
}
