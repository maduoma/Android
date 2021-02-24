package com.dodemy.tictactoemvvm.game;


import com.dodemy.tictactoemvvm.model.Cell;
import com.dodemy.tictactoemvvm.model.Game;
import com.dodemy.tictactoemvvm.model.Player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameHorizontalCellsShould {

    private Game game;
    private Player player;
    private Player anotherPlayer;

    @Before
    public void setUp() throws Exception {
        game = new Game("Husayn", "Yasin");
        player = game.player1;
        anotherPlayer = game.player2;
    }

    @Test
    public void returnTrueIfHasThreeSameHorizontalCellsAtRow1() throws Exception {
        Cell cell = new Cell(player);
        game.cells[0][0] = cell;
        game.cells[0][1] = cell;
        game.cells[0][2] = cell;
        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();
        assertTrue(hasThreeSameHorizontalCells);
    }

    @Test
    public void returnTrueIfHasThreeSameHorizontalCellsAtRow2() throws Exception {
        Cell cell = new Cell(player);
        game.cells[1][0] = cell;
        game.cells[1][1] = cell;
        game.cells[1][2] = cell;
        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();
        assertTrue(hasThreeSameHorizontalCells);
    }

    @Test
    public void returnTrueIfHasThreeSameHorizontalCellsAtRow3() throws Exception {
        Cell cell = new Cell(player);
        game.cells[2][0] = cell;
        game.cells[2][1] = cell;
        game.cells[2][2] = cell;
        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();
        assertTrue(hasThreeSameHorizontalCells);
    }

    @Test
    public void returnFalseIfDoesNotHaveThreeSameHorizontalCells() throws Exception {
        Cell cell = new Cell(player);
        Cell anotherCell = new Cell(anotherPlayer);
        game.cells[0][0] = cell;
        game.cells[0][1] = cell;
        game.cells[0][2] = anotherCell;
        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();
        assertFalse(hasThreeSameHorizontalCells);
    }
}
