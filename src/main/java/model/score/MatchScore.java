package model.score;

import enums.PointsForDisplay;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScore {

    private int[] sets;

    private int[] games;

    private int[] points;

    private boolean isTieBreak;

    private boolean isDeuce;

    private int[] tieBreakPoints;

    public MatchScore() {
        this.sets = new int[]{0, 0};
        this.games = new int[]{0, 0};
        this.points = new int[]{0, 0};
        this.isTieBreak = false;
        this.isDeuce = false;
        this.tieBreakPoints = new int[]{0, 0};
    }

    public String getPointsForDisplay(int index) {
        if (index == 0 || index == 1) {
            return PointsForDisplay.fromPointsToDisplayPoints(points[index]).getDisplayValue();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addGame(int playerIndex) {
        games[playerIndex]++;
    }

    public void addSet(int playerIndex) {
        sets[playerIndex]++;
    }
}
