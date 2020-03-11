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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void add_button_click_success() {
        ViewInteraction imageButton = onView(allOf(withId(R.id.add_button),
                childAtPosition(allOf(withId(R.id.fragment_detail_root_view),
                        childAtPosition(withId(R.id.main_content), 0)), 0), isDisplayed()));
                imageButton.check(matches(isDisplayed()));}

    @Test
    public void add_neighbour_into_list_success() {
        ViewInteraction viewGroup = onView(allOf(childAtPosition(allOf(withId(R.id.list_neighbours),
                withParent(withId(R.id.container))),0),isDisplayed()));viewGroup.check(matches(isDisplayed()));}

     @Test
     public void favorite_tab_click_success(){

        ViewInteraction actionBar$Tab = onView(allOf(withContentDescription("Favorites"),
                childAtPosition(childAtPosition(withId(R.id.tabs),0),1),isDisplayed()));
        actionBar$Tab.check(matches(isDisplayed()));}

      @Test
      public void favorite_tab_display_success(){

        ViewInteraction textView = onView(allOf(withText("FAVORITES"), childAtPosition(allOf(withContentDescription("Favorites"),
                childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class), 1)), 0), isDisplayed()));
        textView.check(matches(withText("FAVORITES")));}

    @Test
    public void link_to_ListDetailNeighbourActivity_success() {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
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
