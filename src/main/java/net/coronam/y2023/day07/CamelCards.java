package net.coronam.y2023.day07;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.max;

public class CamelCards {

    public enum HandType {
        HIGH_CARD(1),
        ONE_PAIR(2),
        TWO_PAIR(3),
        THREE_OF_A_KIND(4),
        FULL_HOUSE(5),
        FOUR_OF_A_KIND(6),
        FIVE_OF_A_KIND(7);

        private final int rank;

        HandType(int rank) {
            this.rank = rank;
        }

        public int getRank() {
            return rank;
        }
    }

    record Card(String value) {
        private static final Map<String, Integer> strengths = new HashMap<>();

        static {
            var cards = Arrays.stream("A,K,Q,J,T,9,8,7,6,5,4,3,2".split(","))
                    .toList()
                    .reversed();

            for (int i = 0; i < cards.size(); i++) {
                strengths.put(cards.get(i), i);
            }
        }

        public Integer getStrength() {
            return strengths.get(this.value);
        }
    }

    class Hand {

        private final ImmutableList<Card> cards;
        private final HandType type;

        public Hand(List<Card> cards) {
            this.cards = ImmutableList.copyOf(cards);
            this.type = determineHandtype(cards);
        }

        public List<Card> getCards() {
            return cards;
        }

        public HandType getType() {
            return type;
        }

        static HandType determineHandtype(List<Card> cards) {
            var cardCounts = cards.stream().collect(Collectors.groupingBy(c -> c.value, Collectors.counting()));
            var maxCount = max(cardCounts.values());

            if (maxCount == 5) {
                return HandType.FIVE_OF_A_KIND;
            } else if (maxCount == 4) {
                return HandType.FOUR_OF_A_KIND;
            } else if (maxCount == 3 && cardCounts.size() == 2) {
                return HandType.FULL_HOUSE;
            } else if (maxCount == 3) {
                return HandType.THREE_OF_A_KIND;
            } else if (maxCount == 2 && cardCounts.size() == 3) {
                return HandType.TWO_PAIR;
            } else if (maxCount == 2 && cardCounts.size() == 4) {
                return HandType.ONE_PAIR;
            } else {
                return HandType.HIGH_CARD;
            }
        }
    }
}
