package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListDetailNeighbourActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void favorite_button_click_success() {

        ViewInteraction floatingActionButton = onView(allOf(withId(R.id.fab_favorites),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(allOf(withId(R.id.fab_favorites),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        floatingActionButton2.perform(click());
    }

    @Test
    public void Navigate_up_toolbar_display_success() {

        ViewInteraction appCompatImageButton = onView(allOf(withContentDescription("Navigate up"),
                childAtPosition(allOf(withId(R.id.widget_toolbar), childAtPosition(allOf(withId(R.id.collapsing_toolbar),
                        withContentDescription("Caroline")), 1)), 1), isDisplayed()));
        appCompatImageButton.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
