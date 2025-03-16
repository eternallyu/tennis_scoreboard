package model.entity;

import jakarta.persistence.*;
import lombok.*;
import model.score.MatchScore;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "Matches")
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Player1")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "Player2")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "Winner")
    private Player winner;

    @Transient
    private MatchScore matchScore = new MatchScore();

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Match(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public String getPlayer1Name() {
        return player1.getName();
    }

    public String getPlayer2Name() {
        return player2.getName();
    }
}
