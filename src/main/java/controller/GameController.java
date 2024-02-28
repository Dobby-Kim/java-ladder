package controller;

import domain.*;
import domain.ladder.Ladder;
import domain.ladder.attirbute.Height;
import domain.player.Players;
import util.RandomDirectionGenerator;
import view.OutputView;

import java.util.Map;
import java.util.stream.IntStream;

import static util.InputRetryHelper.inputRetryHelper;
import static util.UserMessage.*;
import static view.InputView.input;
import static view.InputView.inputNames;


public class GameController {
    public void execute() {
        Players players = inputRetryHelper(() -> new Players(new Names(inputNames(PLAYER_INPUT_PROMPT))));
        int numberOfPlayers = players.getPlayerCount();
        Prizes prizes = inputRetryHelper(() -> new Prizes(inputNames(PRIZE_INPUT_PROMPT), numberOfPlayers));
        Height height = inputRetryHelper(() -> new Height(input(HEIGHT_INPUT_PROMPT)));
        Ladder ladder = new Ladder(height, numberOfPlayers, new RandomDirectionGenerator());
        GameBoard gameBoard = new GameBoard(players, ladder, prizes);

        printGeneratedGameBoard(gameBoard);
        showGeneratedResult(gameBoard);
    }

    private void printGeneratedGameBoard(GameBoard gameBoard) {
        OutputView.print(LADDER_GENERATE_RESULT_HEADER);
        OutputView.printNewLine();
        OutputView.printObjectNames(gameBoard.getPlayers()
                                             .getPlayerNames());
        IntStream.range(0, gameBoard.getLadderHeight())
                 .mapToObj(gameBoard::getDirectionsAtHorizontalIndex)
                 .forEach(OutputView::printDirections);
        OutputView.printObjectNames(gameBoard.getPrizes()
                                             .getValue());
    }

    private void showGeneratedResult(GameBoard gameBoard) {
        boolean repeatFlag = true;
        while (repeatFlag) {
            OutputView.printNewLine();
            Name targetName = inputRetryHelper(() -> new Name(input(SEARCH_PLAYER_PROMPT)));
            repeatFlag = showResultAndDetermineRepeat(gameBoard, targetName);
        }
    }

    private boolean showResultAndDetermineRepeat(GameBoard gameBoard, Name targetName) {
        OutputView.print(GAME_RESULT_HEADER);
        if (targetName.isAll()) {
            Map<Name, Prize> searchResults = gameBoard.searchAllPlayerResult();
            searchResults.forEach((name, prize) -> OutputView.printAllResults(name.toString(), prize.toString()));
            return false;
        }
        OutputView.print(gameBoard.searchOnePlayerResult(targetName));
        return true;
    }
}
