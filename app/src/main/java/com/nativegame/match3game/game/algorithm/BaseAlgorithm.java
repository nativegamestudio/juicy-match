package com.nativegame.match3game.game.algorithm;

import com.nativegame.match3game.game.algorithm.special.finder.SpecialTileFinderManager;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.match3game.game.layer.tile.TileSystem;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Entity;

public abstract class BaseAlgorithm extends Entity implements Algorithm {

    protected final Tile[][] mTiles;
    protected final int mTotalRow;
    protected final int mTotalCol;

    protected final SpecialTileFinderManager mSpecialTileFinder;

    protected BaseAlgorithm(Engine engine, TileSystem tileSystem) {
        super(engine);
        mTiles = tileSystem.getChild();
        mTotalRow = tileSystem.getTotalRow();
        mTotalCol = tileSystem.getTotalColumn();
        mSpecialTileFinder = new SpecialTileFinderManager(engine);
    }

}
