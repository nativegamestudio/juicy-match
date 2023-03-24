package com.nativegame.match3game.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nativegame.match3game.R;
import com.nativegame.match3game.asset.Sounds;
import com.nativegame.match3game.game.JuicyMatch;
import com.nativegame.nattyengine.Game;
import com.nativegame.nattyengine.ui.GameButton;
import com.nativegame.nattyengine.ui.GameFragment;
import com.nativegame.nattyengine.ui.GameView;

public class MyGameFragment extends GameFragment implements View.OnClickListener {

    private Game mGame;

    public MyGameFragment() {
        // Required empty public constructor
    }

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GameButton btnPause = (GameButton) view.findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);
    }

    @Override
    protected void onLayoutCreated(View view) {
        mGame = new JuicyMatch(getGameActivity(), (GameView) view.findViewById(R.id.game_view));
        mGame.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGame.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGame.stop();
    }

    @Override
    public boolean onBackPressed() {
        mGame.pause();
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_pause) {
            mGame.pause();
            Sounds.BUTTON_CLICK.play();
        }
    }
    //========================================================

}
