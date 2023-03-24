package com.nativegame.match3game.game.swap;

import com.nativegame.match3game.asset.Sounds;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Entity;

public class SwapModifier extends Entity {

    private SwapListener mListener;
    private Tile mTileA;
    private Tile mTileB;

    public SwapModifier(Engine engine) {
        super(engine);
    }

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public SwapListener getListener() {
        return mListener;
    }

    public void setListener(SwapListener listener) {
        mListener = listener;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mTileA.swapTile(elapsedMillis);
        mTileB.swapTile(elapsedMillis);
        // Remove after swap finished
        if (!mTileA.isMoving() && !mTileB.isMoving()) {
            if (mListener != null) {
                mListener.onSwap(mTileA, mTileB);
            }
            removeFromGame();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(Tile tileA, Tile tileB) {
        mTileA = tileA;
        mTileB = tileB;
        Sounds.TILE_SWAP.play();
        addToGame();
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface SwapListener {

        void onSwap(Tile tileA, Tile tileB);

    }
    //========================================================

}
