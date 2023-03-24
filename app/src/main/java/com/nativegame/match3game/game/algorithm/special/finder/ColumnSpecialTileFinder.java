package com.nativegame.match3game.game.algorithm.special.finder;

import com.nativegame.match3game.algorithm.TileState;
import com.nativegame.match3game.algorithm.TileType;
import com.nativegame.match3game.game.layer.tile.SpecialType;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.nattyengine.engine.Engine;

public class ColumnSpecialTileFinder extends TripleSpecialTileFinder {

    public ColumnSpecialTileFinder(Engine engine) {
        super(engine);
    }

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void findSpecialTile(Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 3; j++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable() || tiles[i][j].getTileState() != TileState.MATCH) {
                    continue;
                }

                // Check is tile type match the next three column
                TileType type = tiles[i][j].getTileType();
                if (type == tiles[i][j + 1].getTileType()
                        && type == tiles[i][j + 2].getTileType()
                        && type == tiles[i][j + 3].getTileType()) {

                    // Check the tile player select
                    if (tiles[i][j + 2].isSelect()) {
                        setPositionXFactors(-2, -1, 1);
                        upgrade(tiles, i, j + 2);
                    } else {
                        setPositionXFactors(-1, 1, 2);
                        upgrade(tiles, i, j + 1);   // Default is left one
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
        // Left shape:  Right shape:
        // 0 X 0 0      0 0 X 0

        Tile targetTile = tiles[row][col];
        // We make sure it is not already special tile
        if (targetTile.getSpecialType() != SpecialType.NONE) {
            return;
        }
        // Set target tile special type
        targetTile.setSpecialType(SpecialType.COLUMN_SPECIAL_TILE);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
    }
    //========================================================

}
