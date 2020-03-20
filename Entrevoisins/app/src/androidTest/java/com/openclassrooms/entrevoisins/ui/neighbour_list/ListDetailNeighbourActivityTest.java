package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.ViewAction;
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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)

public class ListDetailNeighbourActivityTest {
    private ListDetailNeighbourActivity mDetailActivity;
    private NeighbourApiService mServices;

    @Rule
    public ActivityTestRule<ListDetailNeighbourActivity> mDetailActivityRule =
            new ActivityTestRule(ListDetailNeighbourActivity.class);

    @Before
    public void setUp() {
      mDetailActivity = mDetailActivityRule.getActivity();
         assertThat(mDetailActivity, notNullValue());

         mServices = DI.getNewInstanceApiService();
         assertThat(mServices, notNullValue());
     }

    // Test 1
    /*test vérifiant que lorsqu’on clique sur un élément de la liste,
    l’écran de détails est bien lancé ;*/
    @Test
    public void click_item_to_ListDetailNeighbourActivity(){

        String ITEM_NAME= "Caroline";

        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.activity_detail_neighbour)).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.person_name),isDisplayed())).check(matches(withText(ITEM_NAME)));
    }

     //Test 2
    /*test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant le nom de
    l’utilisateur en question est bien rempli ;*/
    @Test
    public void listDetailNeighbourActivity_name_isDisplay(){
        onView((allOf(withId(R.id.person_name),isDisplayed())))
                .check(matches(withText(mServices.getNeighbours().get(0).getName())));

    }
}