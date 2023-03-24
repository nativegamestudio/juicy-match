package com.nativegame.match3game.game.algorithm.special.combine;

import com.nativegame.match3game.algorithm.TileState;
import com.nativegame.match3game.game.effect.flash.ColumnFlashEffectSystem;
import com.nativegame.match3game.game.effect.flash.RowFlashEffectSystem;
import com.nativegame.match3game.game.layer.tile.SpecialType;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.nattyengine.engine.Engine;

public class DoubleRowColumnCombineHandler extends BaseSpecialCombineHandler {

    private final RowFlashEffectSystem mRowFlashEffectSystem;
    private final ColumnFlashEffectSystem mColumnFlashEffectSystem;

    public DoubleRowColumnCombineHandler(Engine engine) {
        super(engine);
        mRowFlashEffectSystem = new RowFlashEffectSystem(engine, 1);
        mColumnFlashEffectSystem = new ColumnFlashEffectSystem(engine, 1);
    }

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public long getStartDelay() {
        return 0;
    }

    @Override
    public boolean checkSpecialCombine(Tile[][] tiles, Tile tileA, Tile tileB, int row, int col) {
        // Check are both tiles row or column special tile
        if ((tileA.getSpecialType() == SpecialType.ROW_SPECIAL_TILE
                || tileA.getSpecialType() == SpecialType.COLUMN_SPECIAL_TILE)
                && (tileB.getSpecialType() == SpecialType.ROW_SPECIAL_TILE
                || tileB.getSpecialType() == SpecialType.COLUMN_SPECIAL_TILE)) {
            // We make sure the origin special tiles not being detected
            tileA.setTileState(TileState.MATCH);
            tileB.setTileState(TileState.MATCH);
            handleSpecialCombine(tiles, tileA, tileB, row, col);
            return true;
        }

        return false;
    }

    @Override
    protected void playTileEffect(Tile tileA, Tile tileB) {
        super.playTileEffect(tileA, tileB);
        mRowFlashEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
        mColumnFlashEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void handleSpecialCombine(Tile[][] tiles, Tile tileA, Tile tileB, int row, int col) {
        int targetRow = tileA.getRow();
        int targetCol = tileA.getColumn();

        // Pop row tile
        for (int j = 0; j < col; j++) {
            Tile t = tiles[targetRow][j];
            // We make sure not pop multiple times
            if (t.getTileState() == TileState.IDLE) {
                t.popTile();
            }
        }

        // Pop column tile
        for (int i = 0; i < row; i++) {
            Tile t = tiles[i][targetCol];
            // We make sure not pop multiple times
            if (t.getTileState() == TileState.IDLE) {
                t.popTile();
            }
        }

        playTileEffect(tileA, tileB);
    }
    //========================================================

}
