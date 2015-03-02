package basketball.stats.chunker;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import basketball.stats.enums.EventDetail;
import basketball.stats.enums.EventType;
import basketball.stats.enums.Players;
import basketball.stats.enums.Possession;
import basketball.stats.models.EntityChunk;
import basketball.stats.models.LogEntry;
import basketball.stats.models.TimeDivision;

public abstract class PlayerStatsEntityChunker implements EntityChunker {

    final Set<Players> currentPlayers = new HashSet<>();

    @Override
    public void beforeProcessing(Map<String, EntityChunk> data) {
        data.put("OPPONENT", new EntityChunk());
        data.put("MULTIPLE", new EntityChunk());
        data.put("UNGUARDED", new EntityChunk());
        data.put("TEAM", new EntityChunk());
    }

    @Override
    public void afterProcessing(Map<String, EntityChunk> data) {
        for (Entry<String, EntityChunk> entry : data.entrySet()) {
            if (entry.getValue().getValue("START_TIME") != null) {
                final int seconds = Integer.parseInt(entry.getValue().getValue("END_TIME").get(0));
                final int endSeconds = (seconds / 60) * 60 + 60;
                entry.getValue().setValue("END_TIME", Integer.toString(endSeconds));
                updateTotalTime(entry.getValue());
            }
            if (entry.getValue().getValue("START_DIFF") != null) {
                updateScoreDifferential(entry.getValue());
            }
        }
    }

    @Override
    public void startValid(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data) {
        for (final Players player : currentPlayers) {
            final EntityChunk chunk = data.get(player.name());
            if (chunk == null) {
                final EntityChunk newChunk = new EntityChunk();
                // ADD time? differential? not sure
                data.put(player.name(), newChunk);
                newChunk.setValue("START_TIME", Integer.toString(entry.getGameTime().getTotalSeconds()));
                newChunk.setValue("START_DIFF", Integer.toString(entry.getTeamScore() - entry.getOpponentScore()));

                // ensure start of time division stuff
            } else {
                // send end time, differential
                if (chunk.getValue("START_TIME") == null) {
                    chunk.setValue("START_TIME", Integer.toString(entry.getGameTime().getTotalSeconds()));
                }
                if (chunk.getValue("START_DIFF") == null) {
                    chunk.setValue("START_DIFF", Integer.toString(entry.getTeamScore() - entry.getOpponentScore()));
                }
                chunk.setValue("END_DIFF", Integer.toString(entry.getTeamScore() - entry.getOpponentScore()));
                chunk.setValue("END_TIME", Integer.toString(entry.getGameTime().getTotalSeconds()));
            }
        }
    }

    @Override
    public void process(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void preValidation(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data) {
        if (entry.getDetail() == EventDetail.SUBSTITUTION_IN) {
            currentPlayers.add(entry.getPlayers());
        } else if (entry.getDetail() == EventDetail.SUBSTITUTION_OUT) {
            currentPlayers.remove(entry.getPlayers());

            final EntityChunk chunk = data.get(entry.getPlayers().name());
            if (chunk == null) {
                return;
            }

            if (chunk.getValue("START_TIME") != null) {
                if (entry.getGameTime().compareTo(timeDivision.getEndTime()) > 0) {
                    chunk.setValue("END_TIME", Integer.toString(timeDivision.getEndTime().getTotalSeconds()));
                } else {
                    chunk.setValue("END_TIME", Integer.toString(entry.getGameTime().getTotalSeconds()));
                }
                updateTotalTime(chunk);
            }

            if (chunk.getValue("START_DIFF") != null) {
                chunk.setValue("END_DIFF", Integer.toString(entry.getTeamScore() - entry.getOpponentScore()));
                updateScoreDifferential(chunk);
            }
        }
    }

    @Override
    public void postValidation(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data) {
        final EntityChunk chunk;
        if (entry.getPlayers() != null) {
            chunk = data.get(entry.getPlayers().name());
        } else {
            chunk = null;
        }

        final EntityChunk team = data.get("TEAM");
        final EntityChunk opponent = data.get("OPPONENT");
        final EntityChunk unguarded = data.get("UNGUARDED");
        final EntityChunk multiple = data.get("MULTIPLE");
        final String detail = entry.getDetail() == null ? null : entry.getDetail().name();
        if (entry.getPossession() == Possession.OFFENSE) {
            if (entry.getType() == EventType.MADE_SHOT) {
                if (detail.contains("THREE")) {
                    chunk.addValue("MADE_THREE_POINT_SHOTS", detail);
                    chunk.addValue("POINTS", "3");
                    team.addValue("MADE_THREE_POINT_SHOTS", detail);
                    team.addValue("POINTS", "3");
                } else if (detail.contains("FREE")) {
                    chunk.addValue("MADE_FREE_THROWS", detail);
                    chunk.addValue("POINTS", "1");
                    team.addValue("MADE_FREE_THROWS", detail);
                    team.addValue("POINTS", "1");
                } else {
                    chunk.addValue("MADE_TWO_POINT_SHOTS", detail);
                    chunk.addValue("POINTS", "2");
                    team.addValue("MADE_TWO_POINT_SHOTS", detail);
                    team.addValue("POINTS", "2");
                }
            } else if (entry.getType() == EventType.MADE_ASSISTED_SHOT) {
                if (detail.contains("THREE")) {
                    chunk.addValue("MADE_ASSISTED_THREE_POINT_SHOTS", detail);
                    chunk.addValue("POINTS", "3");
                    team.addValue("MADE_ASSISTED_THREE_POINT_SHOTS", detail);
                    team.addValue("POINTS", "3");
                } else {
                    chunk.addValue("MADE_ASSISTED_TWO_POINT_SHOTS", detail);
                    chunk.addValue("POINTS", "2");
                    team.addValue("MADE_ASSISTED_TWO_POINT_SHOTS", detail);
                    team.addValue("POINTS", "2");
                }
            } else if (entry.getType() == EventType.MISSED_SHOT) {
                if (detail.contains("THREE")) {
                    chunk.addValue("MISSED_THREE_POINT_SHOTS", detail);
                    team.addValue("MISSED_THREE_POINT_SHOTS", detail);
                } else if (detail.contains("FREE")) {
                    chunk.addValue("MISSED_FREE_THROWS", detail);
                    team.addValue("MISSED_FREE_THROWS", detail);
                } else {
                    chunk.addValue("MISSED_TWO_POINT_SHOTS", detail);
                    team.addValue("MISSED_TWO_POINT_SHOTS", detail);
                }
            } else if (entry.getType() == EventType.ASSIST) {
                chunk.addValue("ASSISTS", "1");
                team.addValue("ASSISTS", "1");
            } else if (entry.getType() == EventType.TURNOVER) {
                chunk.addValue("TURNOVERS", "1");
                team.addValue("TURNOVERS", "1");
                if (entry.getDetail() == EventDetail.TURNOVER_STOLEN) {
                    opponent.addValue("STEALS", "1");
                }
            } else if (entry.getType() == EventType.REBOUND) {
                chunk.addValue("OFFENSIVE_REBOUNDS", "1");
                team.addValue("OFFENSIVE_REBOUNDS", "1");
            } else if (entry.getType() == EventType.BLOCK) {
                if (detail.contains("THREE")) {
                    chunk.addValue("MISSED_BLOCKED_THREE_POINT_SHOTS", detail);
                    team.addValue("MISSED_BLOCKED_THREE_POINT_SHOTS", detail);
                } else {
                    chunk.addValue("MISSED_BLOCKED_TWO_POINT_SHOTS", detail);
                    team.addValue("MISSED_BLOCKED_TWO_POINT_SHOTS", detail);
                }
                opponent.addValue("BLOCKS", "1");
            } else if (entry.getType() == EventType.DEFLECTION) {
                chunk.addValue("DEFLECTIONS_SELF", "1");
                team.addValue("DEFLECTIONS_SELF", "1");
                opponent.addValue("DEFLECTIONS_CAUSED", "1");
            } else if (entry.getType() == EventType.FOUL) {
                if (entry.getPlayers() == null) {
                    opponent.addValue("FOULS", "1");
                    return;
                }
                chunk.addValue("TURNOVERS", "1");
                team.addValue("TURNOVERS", "1");
                chunk.addValue("FOULS", "1");
                team.addValue("FOULS", "1");
            } else if (entry.getType() == EventType.SHOOTING_FOUL) {
                opponent.addValue("FOULS", "1");
                opponent.addValue("SHOOTING_FOULS", "1");
            }
        } else if (entry.getPossession() == Possession.DEFENSE) {
            if (entry.getType() == EventType.MADE_SHOT) {
                if (entry.getPlayers() == null) {
                    if (detail.contains("THREE")) {
                        unguarded.addValue("MADE_THREE_POINT_SHOTS", detail);
                        unguarded.addValue("POINTS", "3");
                        opponent.addValue("MADE_THREE_POINT_SHOTS", detail);
                        opponent.addValue("POINTS", "3");
                    } else if (detail.contains("FREE")) {
                        opponent.addValue("MADE_FREE_THROWS", detail);
                        opponent.addValue("POINTS", "1");
                    } else {
                        unguarded.addValue("MADE_TWO_POINT_SHOTS", detail);
                        unguarded.addValue("POINTS", "2");
                        opponent.addValue("MADE_TWO_POINT_SHOTS", detail);
                        opponent.addValue("POINTS", "2");
                    }
                    return;
                }

                if (entry.getPlayers() == Players.MULTIPLE) {
                    if (detail.contains("THREE")) {
                        multiple.addValue("MADE_THREE_POINT_SHOTS", detail);
                        multiple.addValue("POINTS", "3");
                        opponent.addValue("MADE_THREE_POINT_SHOTS", detail);
                        opponent.addValue("POINTS", "3");
                    } else if (detail.contains("FREE")) {
                        opponent.addValue("MADE_FREE_THROWS", detail);
                        opponent.addValue("POINTS", "1");
                    } else {
                        multiple.addValue("MADE_TWO_POINT_SHOTS", detail);
                        multiple.addValue("POINTS", "2");
                        opponent.addValue("MADE_TWO_POINT_SHOTS", detail);
                        opponent.addValue("POINTS", "2");
                    }
                    return;
                }

                if (detail.contains("THREE")) {
                    chunk.addValue("OPPONENT_MADE_THREE_POINT_SHOTS", detail);
                    chunk.addValue("POINTS_GIVEN", "3");
                    team.addValue("OPPONENT_MADE_THREE_POINT_SHOTS", detail);
                    team.addValue("POINTS_GIVEN", "3");
                    opponent.addValue("MADE_THREE_POINT_SHOTS", detail);
                    opponent.addValue("POINTS", "3");
                } else if (detail.contains("FREE")) {
                    opponent.addValue("MADE_FREE_THROWS", detail);
                    opponent.addValue("POINTS", "1");
                } else {
                    chunk.addValue("OPPONENT_MADE_TWO_POINT_SHOTS", detail);
                    chunk.addValue("POINTS_GIVEN", "2");
                    team.addValue("OPPONENT_MADE_TWO_POINT_SHOTS", detail);
                    team.addValue("POINTS_GIVEN", "2");
                    opponent.addValue("MADE_TWO_POINT_SHOTS", detail);
                    opponent.addValue("POINTS", "2");
                }
            } else if (entry.getType() == EventType.MISSED_SHOT) {
                if (entry.getPlayers() == null) {
                    if (detail.contains("THREE")) {
                        unguarded.addValue("MISSED_THREE_POINT_SHOTS", detail);
                        opponent.addValue("MISSED_THREE_POINT_SHOTS", detail);
                    } else if (detail.contains("FREE")) {
                        opponent.addValue("MISSED_FREE_THROWS", detail);
                    } else {
                        unguarded.addValue("MISSED_TWO_POINT_SHOTS", detail);
                        opponent.addValue("MISSED_TWO_POINT_SHOTS", detail);
                    }
                    return;
                }

                if (entry.getPlayers() == Players.MULTIPLE) {
                    if (detail.contains("THREE")) {
                        multiple.addValue("MISSED_THREE_POINT_SHOTS", detail);
                        opponent.addValue("MISSED_THREE_POINT_SHOTS", detail);
                    } else if (detail.contains("FREE")) {
                        opponent.addValue("MISSED_FREE_THROWS", detail);
                    } else {
                        multiple.addValue("MISSED_TWO_POINT_SHOTS", detail);
                        opponent.addValue("MISSED_TWO_POINT_SHOTS", detail);
                    }
                    return;
                }

                if (detail.contains("THREE")) {
                    chunk.addValue("OPPONENT_MISSED_THREE_POINT_SHOTS", detail);
                    team.addValue("OPPONENT_MISSED_THREE_POINT_SHOTS", detail);
                    opponent.addValue("MISSED_THREE_POINT_SHOTS", detail);
                } else if (detail.contains("FREE")) {
                    opponent.addValue("MISSED_FREE_THROWS", detail);
                } else {
                    chunk.addValue("OPPONENT_MISSED_TWO_POINT_SHOTS", detail);
                    team.addValue("OPPONENT_MISSED_TWO_POINT_SHOTS", detail);
                    opponent.addValue("MISSED_TWO_POINT_SHOTS", detail);
                }
            } else if (entry.getType() == EventType.REBOUND) {
                chunk.addValue("DEFENSIVE_REBOUNDS", "1");
                team.addValue("DEFENSIVE_REBOUNDS", "1");
            } else if (entry.getType() == EventType.BLOCK) {
                if (detail.contains("THREE")) {
                    chunk.addValue("OPPONENT_MISSED_THREE_POINT_SHOTS", detail);
                    team.addValue("OPPONENT_MISSED_THREE_POINT_SHOTS", detail);
                    opponent.addValue("MISSED_BLOCKED_THREE_POINT_SHOTS", detail);
                } else {
                    chunk.addValue("OPPONENT_MISSED_TWO_POINT_SHOTS", detail);
                    team.addValue("OPPONENT_MISSED_TWO_POINT_SHOTS", detail);
                    opponent.addValue("MISSED_BLOCKED_TWO_POINT_SHOTS", detail);
                }
                chunk.addValue("BLOCKS", detail);
                team.addValue("BLOCKS", detail);
            } else if (entry.getType() == EventType.STEAL) {
                chunk.addValue("STEALS", "1");
                team.addValue("STEALS", "1");
                opponent.addValue("TURNOVERS", "1");
            } else if (entry.getType() == EventType.DEFLECTION) {
                chunk.addValue("DEFLECTIONS_CAUSED", "1");
                team.addValue("DEFLECTIONS_CAUSED", "1");
                opponent.addValue("DEFLECTIONS_SELF", "1");
            } else if (entry.getType() == EventType.TURNOVER) {
                opponent.addValue("TURNOVERS", "1");
            } else if (entry.getType() == EventType.SHOOTING_FOUL) {
                chunk.addValue("SHOOTING_FOULS", "1");
                team.addValue("SHOOTING_FOULS", "1");
                chunk.addValue("FOULS", "1");
                team.addValue("FOULS", "1");
            } else if (entry.getType() == EventType.FOUL) {
                if (entry.getPlayers() == null) {
                    opponent.addValue("TURNOVERS", "1");
                    return;
                }

                chunk.addValue("FOULS", "1");
                team.addValue("FOULS", "1");
            }

        }
    }

    @Override
    public void endValid(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data) {
    }

    private void updateScoreDifferential(EntityChunk chunk) {
        final int startTime = Integer.parseInt(chunk.getValue("START_DIFF").get(0));
        final int endTime = Integer.parseInt(chunk.getValue("END_DIFF").get(0));
        chunk.addValue("TOTAL_DIFF", Integer.toString(endTime - startTime));
        chunk.clearKey("START_DIFF");
        chunk.clearKey("END_DIFF");
    }

    private void updateTotalTime(final EntityChunk chunk) {
        final int startTime = Integer.parseInt(chunk.getValue("START_TIME").get(0));
        final int endTime = Integer.parseInt(chunk.getValue("END_TIME").get(0));
        if (chunk.getValue("TOTAL_TIME") != null) {
            final int oldTotalTime = Integer.parseInt(chunk.getValue("TOTAL_TIME").get(0));
            chunk.setValue("TOTAL_TIME", Integer.toString(oldTotalTime + (endTime - startTime)));
            chunk.clearKey("START_TIME");
            chunk.clearKey("END_TIME");
        } else {
            chunk.setValue("TOTAL_TIME", Integer.toString(endTime - startTime));
            chunk.clearKey("START_TIME");
            chunk.clearKey("END_TIME");
        }
    }
}
