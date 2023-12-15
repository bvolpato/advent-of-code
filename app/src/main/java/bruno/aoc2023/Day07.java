package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 {

    //static final char[] order = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
    // part 2 now
    static final char[] order = {'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'};
    static final Map<Character, Integer> charOrder = new HashMap<>();

    static {
        for (int i = 0; i < order.length; i++) {
            charOrder.put(order[i], i);
        }
    }

    static enum HandType {
        FIVE(1), FOUR(2), FULL(3), THREE(4), TWO_PAIR(5), ONE_PAIR(6), HIGH(7);

        int order;

        HandType(int order) {
            this.order = order;
        }
    }

    static class Hand {
        HandType type;
        char card;
        char second;
        String original;
        int bet;

        public Hand(HandType type, char card) {
            this.type = type;
            this.card = card;
        }

        public Hand(HandType type, char card, char second) {
            this.type = type;
            this.card = card;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Hand{" +
                    "type=" + type +
                    ", card=" + card +
                    ", second=" + second +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getContent(Day08.class.getResourceAsStream("/day07.txt"));
        System.out.println("Clipboard content: " + clipboard);

        List<String> listLines = MyStringUtils.asListLines(clipboard);

        List<Hand> hands = new ArrayList<>();


        for (String line : listLines) {
            String handStr = line.split(" ")[0];
            int bet = Integer.valueOf(line.split(" ")[1]);

            System.out.println("----------");
            System.out.println(handStr);
            Hand parseHand = parseHand(handStr);
            parseHand.original = handStr;
            parseHand.bet = bet;
            System.out.println(handStr + ", " + bet + " ---> " + parseHand);
            hands.add(parseHand);
        }


        System.out.println("------- sorting --------------");

        Collections.sort(hands, (a, b) -> {

            if (a.type.order < b.type.order) {
                return 1;
            } else if (a.type.order > b.type.order) {
                return -1;
            }

            for (int i = 0; i < 5; i++) {
                Integer vala = charOrder.get(a.original.charAt(i));
                Integer valb = charOrder.get(b.original.charAt(i));
                if (vala > valb) {
                    return -1;
                } else if (vala < valb) {
                    return 1;
                }
            }

            return -1;

        });

        long sum = 0;

        for (int i = 0; i < hands.size(); i++) {
            System.out.println(hands.get(i).original + ": " + hands.get(i) + ", " + hands.get(i).bet + " * " + (i + 1));
            sum += (i + 1) * hands.get(i).bet;
        }

        System.out.println(sum);
    }


    public static Hand parseHand(String cards) {

        for (char card : order) {
            if (cards.replaceAll("[^" + card + "J]", "").length() == 5) {
                return new Hand(HandType.FIVE, card);
            }
        }

        for (char card : order) {
            if (cards.replaceAll("[^" + card + "J]", "").length() == 4) {
                return new Hand(HandType.FOUR, card);
            }
        }

        for (char card : order) {
            if (cards.replaceAll("[^" + card + "J]", "").length() == 3) {

                String leftover = cards.replaceAll("" + card, "").replaceAll("J", "");
                if (leftover.charAt(0) == leftover.charAt(1)) {
                    return new Hand(HandType.FULL, card, leftover.charAt(0));
                }

                return new Hand(HandType.THREE, card);
            }
        }


        for (char card : order) {
            String leftover = cards.replaceAll("[^" + card + "J]", "");
            if (leftover.length() == 2) {
                String ignoring = cards.replaceAll("" + card, "").replaceAll("J", "");
                for (char second : order) {
                    if (second == card) {
                        continue;
                    }

                    System.out.println("Checking if " + ignoring + " has double " + second);
                    if (ignoring.replaceAll("" + second, "").length() == 1) {
                        return new Hand(HandType.TWO_PAIR, card, second);
                    }
                }

                return new Hand(HandType.ONE_PAIR, card);
            }
        }


        char max = '2';
        for (char card : order) {
            if (cards.indexOf(card) != -1 && charOrder.get(card) < charOrder.get(max)) {
                max = card;
            }
        }

        return new Hand(HandType.HIGH, max);
    }
}

