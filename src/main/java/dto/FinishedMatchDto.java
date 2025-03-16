package dto;

import lombok.Builder;

@Builder
public record FinishedMatchDto(Long id, String playerOne, String playerTwo, String winner) {
}
