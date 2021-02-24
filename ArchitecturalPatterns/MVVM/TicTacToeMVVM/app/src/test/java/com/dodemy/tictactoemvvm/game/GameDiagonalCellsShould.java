package com.dodemy.tictactoemvvm.game;


import com.dodemy.tictactoemvvm.model.Cell;
import com.dodemy.tictactoemvvm.model.Game;
import com.dodemy.tictactoemvvm.model.Player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameDiagonalCellsShould {

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
    public void returnTrueIfHasThreeSameDiagonalCellsFromLeft() throws Exception {
        Cell cell = new Cell(player);
        game.cells[0][0] = cell;
        game.cells[1][1] = cell;
        game.cells[2][2] = cell;
        boolean hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells();
        assertTrue(hasThreeSameDiagonalCells);
    }

    @Test
    public void returnTrueIfHasThreeSameDiagonalCellsFromRight() throws Exception {
        Cell cell = new Cell(player);
        game.cells[0][2] = cell;
        game.cells[1][1] = cell;
        game.cells[2][0] = cell;
        boolean hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells();
        assertTrue(hasThreeSameDiagonalCells);
    }

    @Test
    public void returnFalseIfDoesNotHaveThreeSameDiagonalCells() throws Exception {
        Cell cell = new Cell(player);
        Cell anotherCell = new Cell(anotherPlayer);
        game.cells[0][2] = cell;
        game.cells[1][1] = cell;
        game.cells[2][0] = anotherCell;
        boolean hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells();
        assertFalse(hasThreeSameDiagonalCells);
    }
}
