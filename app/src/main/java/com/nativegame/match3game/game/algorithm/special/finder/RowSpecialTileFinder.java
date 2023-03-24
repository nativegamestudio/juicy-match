package com.nativegame.match3game.game.algorithm.special.finder;

import com.nativegame.match3game.algorithm.TileState;
import com.nativegame.match3game.algorithm.TileType;
import com.nativegame.match3game.game.layer.tile.SpecialType;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.nattyengine.engine.Engine;

public class RowSpecialTileFinder extends TripleSpecialTileFinder {

    public RowSpecialTileFinder(Engine engine) {
        super(engine);
    }

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void findSpecialTile(Tile[][] tiles, int row, int col) {
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row - 3; i++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable() || tiles[i][j].getTileState() != TileState.MATCH) {
                    continue;
                }

                // Check is tile type match the next three row
                TileType type = tiles[i][j].getTileType();
                if (type == tiles[i + 1][j].getTileType()
                        && type == tiles[i + 2][j].getTileType()
                        && type == tiles[i + 3][j].getTileType()) {

                    // Check the tile player select
                    if (tiles[i + 2][j].isSelect()) {
                        setPositionYFactors(-2, -1, 1);
                        upgrade(tiles, i + 2, j);
                    } else {
                        setPositionYFactors(-1, 1, 2);
                        upgrade(tiles, i + 1, j);   // Default is upper one
                    }
                }
            }
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void upgrade(Tile[][] tiles, int row, int col) {
        // Up shape:  Down shape:
        // 0          0
        // X          0
        // 0          X
        // 0          0

        Tile targetTile = tiles[row][col];
        // We make sure it is not already special tile
        if (targetTile.getSpecialType() != SpecialType.NONE) {
            return;
        }
        // Set target tile special type
        targetTile.setSpecialType(SpecialType.ROW_SPECIAL_TILE);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
    }
    //========================================================

}
