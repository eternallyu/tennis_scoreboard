package util;

import exception.BadRequestException;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class Validation {

    public boolean validPlayerName(String name) {
        return !name.isBlank() && name.chars().allMatch(c -> Character.isLetter(c) || c == '_' || c == '-' || c == ' ') && name.length() <= 20;
    }

    public static UUID checkUuidOfMatch(String uuid) {
        UUID uuidOfMatch = UUID.fromString(uuid);

        if (uuidOfMatch.toString().isBlank()) {
            throw new BadRequestException("Invalid UUID.");
        }

        return uuidOfMatch;
    }
}
