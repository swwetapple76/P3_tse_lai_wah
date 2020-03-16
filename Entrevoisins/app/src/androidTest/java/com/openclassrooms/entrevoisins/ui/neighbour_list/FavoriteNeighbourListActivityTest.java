package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class FavoriteNeighbourListActivityTest {
    private ListNeighbourActivity mActivity;
    private NeighbourApiService mServices;
    private List<Neighbour> mFavoriteNeighbourList = new ArrayList<>();
    boolean favoriteNeighbour;
    Neighbour neighbour;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        mServices = DI.getNewInstanceApiService();
        assertThat(mServices, notNullValue());
        mFavoriteNeighbourList = new ArrayList<>();
        for (Neighbour n : mServices.getNeighbours()) {
            if (n.isFavorites())
                mFavoriteNeighbourList.add(n);
        }

    }

    // Test 1
    @Test
    public void click_item_to_ListDetailNeighbourActivity(){

        String ITEM_NAME= "Caroline";

        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.activity_detail_neighbour)).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.person_name),isDisplayed())).check(matches(withText(ITEM_NAME)));
    }

    // Test 4
    @Test
    public void favoriteNeighbourList_should_show_only_favouriteList() {

        onView(withContentDescription("Favorites")).perform(click());

        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(1));
    }

}
