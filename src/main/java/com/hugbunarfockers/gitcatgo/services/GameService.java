package com.hugbunarfockers.gitcatgo.services;

import com.hugbunarfockers.gitcatgo.entities.*;

public class GameService implements IGameService
{
    private GameBoard ticTacToe;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int count = 0;

	public GameService()
	{
		ticTacToe = null;
		player1 = null;
		player2 = null;
		currentPlayer = player1;
	}

	public GameService(GameBoard board, Player p1, Player p2)
    {
        ticTacToe = board;
        player1 = p1;
        player2 = p2;
        currentPlayer = player1;
        fillBoard();
    }

    public void resetGame()
    {
        ticTacToe = new GameBoard();
        fillBoard();
        count = 0;
        currentPlayer = player1;
    }

    public char[][] getBoard()
    {
        if(ticTacToe != null)
        {
            return ticTacToe.getBoard();
        }

        return null;
    }

    public boolean checkIfOccupied(char input)
    {
        input--;
        int row = ((input-48)/3);
        int col = (input%3);
        char[][] board = ticTacToe.getBoard();
        char cell = board[row][col];
        if(cell == 'x' || cell == 'o')
        {
            return true;
        }
        return false;
    }

	public void setBoard(GameBoard board)
	{
		ticTacToe = board;
		fillBoard();
	}

	public void setPlayer1(Player p1)
	{
		player1 = p1;
        currentPlayer = player1;
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public void setPlayer2(Player p2)
	{
		player2 = p2;
	}

	public Player getPlayer2()
	{
		return player2;
	}

    public void setBoardValue(int x, int y, char player)
    {
        ticTacToe.setBoardValue(x, y, player);
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void changeCurrentPlayer()
    {
        if(currentPlayer == player1)
        {
            currentPlayer = player2;
        }
        else
        {
            currentPlayer = player1;
        }
    }

    public boolean isBoardFull()
    {
        return count == 9;
    }

    public boolean checkWinner()
    {
        char arr[][] = getBoard();
        int boardSize = ticTacToe.getSize();

        for(int i = 0; i < boardSize; i++)
        {
            if(((arr[i][0] == arr[i][1]) && (arr[i][1] == arr[i][2])) || ((arr  [0][i] == arr[1][i]) && (arr[1][i] == arr[2][i])))
            {
                setWinner();
                return true;
            }
        }

        if(((arr[0][0] == arr[1][1]) && (arr[1][1] == arr[2][2])) || ((arr[1][1] == arr[0][2]) && (arr[0][2] == arr[2][0])))
        {
            setWinner();
            return true;
        }

        return false;
    }

    public boolean makeMove(char input)
    {
        if(!validateInput(input))
        {
            return false;
        }

        input--;
        int row = ((input-48)/3);
        int column = (input%3);

        if(currentPlayer == player1)
        {
            setBoardValue(row, column, 'x');
        }
        else
        {
            setBoardValue(row, column, 'o');
        }
        count++;

        return true;
    }

    public Player getWinner()
    {
        if(ticTacToe.getWinner() == 1)
        {
            return player1;
        }
        else if(ticTacToe.getWinner() == 2)
        {
            return player2;
        }
        else
        {
            return null;
        }
    }

    private boolean validateInput(char input)
    {
        int inputValue = Character.getNumericValue(input);

        if(inputValue < 1 || inputValue > 9 || checkIfOccupied(input))
        {
            return false;
        }

        return true;
    }

    private void setWinner()
    {
        if(getCurrentPlayer() == player1)
        {
            ticTacToe.setWinner(1);
        }
        else
        {
            ticTacToe.setWinner(2);
        }
    }

    private void fillBoard()
    {
        int boardSize = ticTacToe.getSize();
        char value = 49;

        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                setBoardValue(i, j, value);
                value++;
            }
        }
    }
}
