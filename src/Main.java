import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        War wordWar = new War();
        boolean keepGoing = true;

        do {
            wordWar.loadAmmo();
            System.out.println(wordWar.battle());
            System.out.println("Finish fighting? Y/N : ");
            if (s.next().equalsIgnoreCase("Y")) keepGoing = false;
        } while (keepGoing);

        System.out.println(wordWar.end());
        s.close();
    }

    public static class War {
        private Army sideA;
        private Army sideB;
        private int totalScore;

        public War() {
            sideA = new Army();
            sideB = new Army();
        }

        public String battle() {
            String a_shot = sideA.shoot();
            String b_shot = sideB.shoot();

            int a_points = 0;
            int b_points = 0;

            for(int i = 0; i < a_shot.length(); i++) {
                a_points += (int) a_shot.charAt(i);
            }

            for(int i = 0; i < b_shot.length(); i++) {
                b_points += (int) b_shot.charAt(i);
            }

            if (a_points > b_points) {
                totalScore--;
                return "Side A wins the battle!";
            } else if (b_points > a_points) {
                totalScore++;
                return "Side B wins the battle!";
            } else return "The battle resulted in a tie";
        }

        public void loadAmmo() {
            System.out.println("Loading Side A with : ");
            sideA.loadAmmo(s.next());

            System.out.println("Loading Side B with : ");
            sideB.loadAmmo(s.next());
        }

        public String end() {
            if (totalScore < 0) return "Side A wins the war!";
            else if (totalScore > 0) return "Side B wins the war!";
            else return "The war resulted in a tie";
        }

    }

    public static class Army {
        private List<String> ammunition;

        public Army() {
            ammunition = new ArrayList<String>();
        }

        public void loadAmmo(String ammo) {
            ammunition.add(ammo);
        }

        public String shoot() {
            String shot = ammunition.get(ammunition.size() - 1);
            ammunition.remove(ammunition.size() - 1);
            return shot;
        }
    }
}