package com.nativegame.match3game.game.algorithm.special.handler;

import com.nativegame.match3game.algorithm.TileState;
import com.nativegame.match3game.asset.Textures;
import com.nativegame.match3game.game.GameEvent;
import com.nativegame.match3game.game.effect.flash.ExplosionBeamEffectSystem;
import com.nativegame.match3game.game.effect.flash.ExplosionFlashEffectSystem;
import com.nativegame.match3game.game.layer.Layer;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.particles.ParticleSystem;

public class ExplosionSpecialTileHandler extends BaseSpecialTileHandler {

    private static final int GLITTER_NUM = 8;

    private final ExplosionFlashEffectSystem mFlashEffectSystem;
    private final ExplosionBeamEffectSystem mBeamEffectSystem;
    private final ParticleSystem mGlitterParticleSystem;

    public ExplosionSpecialTileHandler(Engine engine) {
        super(engine);
        mFlashEffectSystem = new ExplosionFlashEffectSystem(engine, 1);
        mBeamEffectSystem = new ExplosionBeamEffectSystem(engine, 8);
        mGlitterParticleSystem = new ParticleSystem(engine, Textures.GLITTER, GLITTER_NUM)
                .setDuration(600)
                .setSpeedWithAngle(1500, 2500)
                .setInitialRotation(0, 360)
                .setRotationSpeed(-360, 360)
                .setAlpha(255, 0, 200)
                .setScale(1.2f, 0.5f, 200)
                .setLayer(Layer.EFFECT_LAYER);
    }

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void handleSpecialTile(Tile[][] tiles, Tile tile, int row, int col) {
        int targetRow = tile.getRow();
        int targetCol = tile.getColumn();
        // Pop 3 X 3 tiles around
        for (int i = targetRow - 1; i <= targetRow + 1; i++) {
            for (int j = targetCol - 1; j <= targetCol + 1; j++) {
                // We make sure the index not out of bound
                if (i < 0 || i > row - 1 || j < 0 || j > col - 1) {
                    continue;
                }
                Tile t = tiles[i][j];
                // We make sure not pop the tile multiple time
                if (t.getTileState() == TileState.IDLE) {
                    t.popTile();
                }
            }
        }

        playTileEffect(tile);
    }

    @Override
    protected void playTileEffect(Tile tile) {
        super.playTileEffect(tile);
        mGlitterParticleSystem.oneShot(tile.getCenterX(), tile.getCenterY(), GLITTER_NUM);
        mFlashEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
        mBeamEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
        mEngine.dispatchEvent(GameEvent.PULSE_GAME);
    }
    //========================================================

}
