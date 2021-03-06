package basketball.stats.processor.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import basketball.stats.models.EntityChunk;

public class BoxScore {

    public static void boxScore(final Map<String, EntityChunk> data, double divisor) {
        final EntityChunkProcessor proc = new EntityChunkProcessor(data);
        final List<String> keySet = new ArrayList<String>(data.keySet());
        Collections.sort(keySet);

        System.out
                .println("NAME______________MIN___PTS___OR____REB___AST___STL___BLK___TO____FG_________3PT________FT_________FG%___3P%___FT%___F_____+/____DA____DC____AFG___OFG________O3PT_______FG%___3P%___");
        for (final String key : keySet) {
            final double minutes = (proc.sum(key, "TOTAL_TIME") / 60.0) / divisor;
            final double points = proc.sum(key, "POINTS") / divisor;
            final double offensiveRebounds = proc.count(key, "OFFENSIVE_REBOUNDS") / divisor;
            final double totalRebounds = (offensiveRebounds + proc.count(key, "DEFENSIVE_REBOUNDS")) / divisor;
            final double assists = proc.count(key, "ASSISTS") / divisor;
            final double steals = proc.count(key, "STEALS") / divisor;
            final double blocks = proc.count(key, "BLOCKS") / divisor;
            final double turnovers = proc.count(key, "TURNOVERS") / divisor;

            final double fieldGoalsMade = (proc.count(key, "MADE_TWO_POINT_SHOTS") + proc.count(key, "MADE_THREE_POINT_SHOTS")
                    + proc.count(key, "MADE_ASSISTED_TWO_POINT_SHOTS") + proc.count(key, "MADE_ASSISTED_THREE_POINT_SHOTS"))
                    / divisor;
            final double fieldGoalsAttempted = (fieldGoalsMade + proc.count(key, "MISSED_TWO_POINT_SHOTS") + proc.count(key, "MISSED_THREE_POINT_SHOTS")
                    + proc.count(key, "MISSED_BLOCKED_TWO_POINT_SHOTS") + proc.count(key, "MISSED_BLOCKED_THREE_POINT_SHOTS"))
                    / divisor;
            final String fieldGoals = fieldGoalsMade + "/" + fieldGoalsAttempted;
            final double fieldGoalPercentage = fieldGoalsMade / fieldGoalsAttempted * 100;

            final double threesMade = (proc.count(key, "MADE_THREE_POINT_SHOTS") + proc.count(key, "MADE_ASSISTED_THREE_POINT_SHOTS")) / divisor;
            final double threesAttempted = (threesMade + proc.count(key, "MISSED_THREE_POINT_SHOTS") + proc.count(key, "MISSED_BLOCKED_THREE_POINT_SHOTS")) / divisor;
            final String threePointers = threesMade + "/" + threesAttempted;
            final double threePointPercentage = threesMade / threesAttempted * 100;

            final double freeThrowsMade = proc.count(key, "MADE_FREE_THROWS") / divisor;
            final double freeThrowsAttempted = (freeThrowsMade + proc.count(key, "MISSED_FREE_THROWS")) / divisor;
            final String freeThrows = freeThrowsMade + "/" + freeThrowsAttempted;
            final double freeThrowPercentage = freeThrowsMade / freeThrowsAttempted * 100;

            final double fouls = proc.count(key, "FOULS") / divisor;
            final double plusMinus = proc.sum(key, "TOTAL_DIFF") / divisor;
            final double deflectionsSelf = proc.count(key, "DEFLECTIONS_SELF") / divisor;
            final double deflectionsCaused = proc.count(key, "DEFLECTIONS_CAUSED") / divisor;

            final double assistedFieldGoals = (proc.count(key, "MADE_ASSISTED_TWO_POINT_SHOTS") + proc.count(key, "MADE_ASSISTED_THREE_POINT_SHOTS")) / divisor;
            final double opponentFieldGoalsMade = (proc.count(key, "OPPONENT_MADE_TWO_POINT_SHOTS") + proc.count(key, "OPPONENT_MADE_THREE_POINT_SHOTS")) / divisor;
            final double opponentFieldGoalsAttempted = (opponentFieldGoalsMade + proc.count(key, "OPPONENT_MISSED_TWO_POINT_SHOTS") + proc.count(key,
                    "OPPONENT_MISSED_THREE_POINT_SHOTS")) / divisor;
            final String opponentFieldGoals = opponentFieldGoalsMade + "/" + opponentFieldGoalsAttempted;
            final double opponentFieldGoalPercentage = opponentFieldGoalsMade / opponentFieldGoalsAttempted * 100;

            final double opponentThreesMade = proc.count(key, "OPPONENT_MADE_THREE_POINT_SHOTS") / divisor;
            final double opponentThreesAttempted = opponentThreesMade + proc.count(key, "OPPONENT_MISSED_THREE_POINT_SHOTS") / divisor;
            final String opponentThreePointers = opponentThreesMade + "/" + opponentThreesAttempted;
            final double opponentThreePointPercentage = opponentThreesMade / opponentThreesAttempted * 100;

            final StringBuilder builder = new StringBuilder("%-18s") // name
                    .append("%-6.1f") // minutes
                    .append("%-6.1f") // points
                    .append("%-6.1f") // offensive rebounds
                    .append("%-6.1f") // total rebounds
                    .append("%-6.1f") // assists
                    .append("%-6.1f") // steals
                    .append("%-6.1f") // blocks
                    .append("%-6.1f") // turnovers
                    .append("%-11s") // field goals made / attempted
                    .append("%-11s") // threes made / attempted
                    .append("%-11s") // free throws made / attempted
                    .append("%-6.1f") // fg %
                    .append("%-6.1f") // 3p %
                    .append("%-6.1f") // ft %
                    .append("%-6.1f") // fouls
                    .append("%-6.1f") // +/-
                    .append("%-6.1f") // deflections self
                    .append("%-6.1f") // deflections caused
                    .append("%-6.1f") // assisted fg
                    .append("%-11s") // opponent fgm / a
                    .append("%-11s") // opponent 3pm / a
                    .append("%-6.1f") // opp fg%
                    .append("%-6.1f"); // opp 3p%
            final String result = String.format(builder.toString(), key, minutes, points, offensiveRebounds, totalRebounds, assists, steals, blocks, turnovers,
                    fieldGoals, threePointers, freeThrows, fieldGoalPercentage, threePointPercentage, freeThrowPercentage, fouls, plusMinus, deflectionsSelf,
                    deflectionsCaused, assistedFieldGoals, opponentFieldGoals, opponentThreePointers, opponentFieldGoalPercentage, opponentThreePointPercentage);

            // final String result = String.format(
            // "%-18s%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-11s%-11s%-11s%6.1f%6.1f%6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-11s%-11s", key, points);
            System.out.println(result);
        }

    }
    // final int points = ProcessingUtils.getPoints(madeShots, madeAssistedShots);
    // final int rebounds = offensiveRebounds + defensiveRebounds;
    // final int differential = startDifferential < endDifferential ? Math.abs(startDifferential - endDifferential) : endDifferential - startDifferential;
    // final String fieldGoals = String.format("%5.1f", ProcessingUtils.getMadeShots(madeShots, madeAssistedShots) / divisor) + "/"
    // + String.format("%-5.1f", ProcessingUtils.getAttemptedShots(madeShots, madeAssistedShots, missedShots) / divisor);
    // final String threePointers = String.format("%5.1f", ProcessingUtils.getThrees(madeShots, madeAssistedShots) / divisor) + "/"
    // + String.format("%-5.1f", ProcessingUtils.getAttemptedThrees(madeShots, madeAssistedShots, missedShots) / divisor);
    // final String freeThrows = String.format("%5.1f", ProcessingUtils.getFreeThrows(madeShots) / divisor) + "/"
    // + String.format("%-5.1f", ProcessingUtils.getAttemptedFreeThrows(madeShots, missedShots) / divisor);
    //
    // final double fieldGoalPercentage = (ProcessingUtils.getMadeShots(madeShots, madeAssistedShots) * 1.0)
    // / ProcessingUtils.getAttemptedShots(madeShots, madeAssistedShots, missedShots) * 100;
    // final double threePointPercentage = (ProcessingUtils.getThrees(madeShots, madeAssistedShots) * 1.0)
    // / ProcessingUtils.getAttemptedThrees(madeShots, madeAssistedShots, missedShots) * 100;
    // final double freeThrowPercentage = (ProcessingUtils.getFreeThrows(madeShots) * 1.0) / ProcessingUtils.getAttemptedFreeThrows(madeShots, missedShots) *
    // 100;
    //
    // final String opponentFieldGoals = ProcessingUtils.getMadeShots(opponentMadeShots) / divisor + "/"
    // + ProcessingUtils.getAttemptedShots(opponentMadeShots, Collections.<String> emptyList(), opponentMissedShots) / divisor;
    // final String opponentThreePointers = ProcessingUtils.getThrees(opponentMadeShots) / divisor + "/"
    // + ProcessingUtils.getAttemptedThrees(opponentMadeShots, Collections.<String> emptyList(), opponentMissedShots) / divisor;
    // final int fouls = nonShootingFouls + offensiveFouls + shootingFouls;
    // final double minutes = this.endTime.getMinutes() + (this.endTime.getSeconds() / 60.0);
    // final String result =
    // String.format("%-18s%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-11s%-11s%-11s%6.1f%6.1f%6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-11s%-11s",
    // new Object[] { player == null ? "" : player.toString(), minutes / divisor, points / divisor, rebounds / divisor, assists / divisor, steals / divisor,
    // blocks / divisor, turnovers / divisor, fieldGoals, threePointers, freeThrows, fieldGoalPercentage, threePointPercentage, freeThrowPercentage,
    // offensiveRebounds / divisor, fouls / divisor, differential / divisor, deflectionsAccident / divisor, deflectionsCaused / divisor,
    // madeAssistedShots.size() / divisor, opponentFieldGoals, opponentThreePointers });
    // return result;
}
