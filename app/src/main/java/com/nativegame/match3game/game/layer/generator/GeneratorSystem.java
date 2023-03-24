package com.nativegame.match3game.game.layer.generator;

import com.nativegame.match3game.game.layer.LayerSystem;
import com.nativegame.match3game.game.layer.tile.TileResetter;
import com.nativegame.match3game.level.Level;
import com.nativegame.nattyengine.engine.Engine;

import java.util.ArrayList;
import java.util.List;

public class GeneratorSystem extends LayerSystem<Generator> {

    private final Generator[][] mGenerators;

    private final List<TileResetter> mResetters = new ArrayList<>();

    public GeneratorSystem(Engine engine) {
        super(engine);
        mGenerators = new Generator[mTotalRow][mTotalCol];
        init(Level.LEVEL_DATA.getGenerator().toCharArray());
    }

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public List<TileResetter> getResetters() {
        return mResetters;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Generator[][] getChild() {
        return mGenerators;
    }

    @Override
    public Generator getChildAt(int row, int col) {
        return mGenerators[row][col];
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == '0') {
                    // We skip the empty layer
                    continue;
                }
                Generator generator = GeneratorInitializer.getGenerator(mEngine, c);
                generator.setPosition(i, j);
                generator.addToGame();
                mGenerators[i][j] = generator;
                mResetters.add(generator.getResetter());
            }
        }
    }
    //========================================================

}
