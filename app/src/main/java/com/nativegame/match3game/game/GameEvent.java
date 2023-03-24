package com.nativegame.match3game.game;

import com.nativegame.nattyengine.engine.event.Event;

public enum GameEvent implements Event {
    // Event of player action
    PLAYER_SWAP,
    PLAYER_SCORE,
    PLAYER_COLLECT,
    PLAYER_USE_BOOSTER,

    // Event of booster
    ADD_BOOSTER,
    REMOVE_BOOSTER,

    // Event of algorithm
    ADD_COMBO,
    STOP_COMBO,

    // Event of game lifecycle
    START_GAME,
    PULSE_GAME,
    SHAKE_GAME,
    ADD_BONUS,
    BONUS_TIME_END,
    BONUS_TIME_SKIP,
    GAME_WIN,
    GAME_OVER
}
