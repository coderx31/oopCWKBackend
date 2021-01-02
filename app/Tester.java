import models.FootballClub;
import services.LeagueManager;
import services.PremierLeagueManager;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
//        System.out.println("Menu");
//        System.out.println("Press A to add");
//        System.out.println("Press D to Delete");
//        System.out.println("Press V to View");
//        System.out.println("Press G to Start Server");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please Enter Your Select: ");
//        String select = scanner.nextLine();
//
//
//        switch (select){
//            case "A":
//                // add method
//                break;
//            case "D":
//                // delete method
//                break;
//            case "V":
//                // view method
//                break;
//            case "G":
//                // start server
//                break;
//            default:
//                System.out.println("Something went wrong!");
//        }
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        premierLeagueManager.loadFromFile();

        for (FootballClub footballClub : premierLeagueManager.getFootballClubs()){
            System.out.println(footballClub.getClubName());
        }
    }
}
