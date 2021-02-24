package com.dodemy.tictactoemvvm;

//import android.support.test.rule.ActivityTestRule;
import androidx.test.rule.ActivityTestRule;

import com.dodemy.tictactoemvvm.model.Player;
import com.dodemy.tictactoemvvm.view.GameActivity;

import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaDialogInteractions.clickDialogPositiveButton;

/**
 * Created by husaynhakeem on 9/5/17.
 */
public class GameEndDialogShould {

    @Rule
    public ActivityTestRule<GameActivity> activityRule = new ActivityTestRule<>(GameActivity.class);

    @Test
    public void display_winner_when_game_ends() throws Exception {
        givenGameEnded();

        assertDisplayed(R.id.tv_winner);
    }

    @Test
    public void display_begin_dialog_when_done_clicked() throws Exception {
        givenGameEnded();

        clickDialogPositiveButton();

        assertNotExist(R.id.tv_winner);
        assertDisplayed(R.id.et_player1);
    }

    private void givenGameEnded() {
        activityRule.getActivity().onGameWinnerChanged(new Player("husaynhakeem", "x"));
    }
}