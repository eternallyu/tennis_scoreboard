package service;

import model.entity.Match;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {

    public static final OngoingMatchesService ONGOING_MATCHES_SERVICE = new OngoingMatchesService();

    private static final Map<UUID, Match> ongoingMatches;

    static {
        ongoingMatches = new ConcurrentHashMap<>();
    }

    public UUID add(Match match) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, match);
        return uuid;
    }

    public Match get(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public void remove(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
}
