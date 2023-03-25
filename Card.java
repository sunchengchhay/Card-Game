import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Card {
    static boolean drawCard = false;
    //method check 2 card win
    public static String check2CardWin(int sumPlayer , int sumPc){
        if(sumPc == 9 || sumPlayer == 9){
            if(sumPc > sumPlayer) return "PC win";
            else if(sumPc < sumPlayer) return "User win";
            else return "Draw";
        }

        if(sumPc == 8 || sumPlayer == 8) {
            if(sumPc > sumPlayer) return "PC win";
            else if(sumPc < sumPlayer) return "User win";
            else return "Draw";
        }

        drawCard = true;
        return "";
    }
    //method check 3 card win
    public static String check3CardWin(int sumPlayer , int sumPc , ArrayList<Integer>player, ArrayList<Integer>pc){
        int countPc = 0 , countPlayer = 0;
        //if player 3 card ,all are JQK
        for(int user : player){
            if( user > 10 && user <= 13 ) countPc++;
            if(countPc == 3) return "Player win";
        }

        //if pc 3 card, all are JQK
        for(int user : pc){
            if(user > 10 && user <= 13 ) countPlayer++;
            if(countPlayer == 3) return "PC win";
        }

        //if player and pc both have 3 JQK
        if(countPlayer == countPc) return "Draw";

        if(sumPlayer > sumPc) return "Player win";
        else if(sumPlayer < sumPc ) return "PC win";
        return "Draw";
    }

    //method pc draw a card
    public static boolean checkPcDraw(int sum3Pc){
        int random = (int)(Math.random()*100);
        if(sum3Pc <= 3) return true;
        else if(sum3Pc == 4 && random <= 80) return true;
        else if(sum3Pc == 5 && random <= 40) return true;
        return sum3Pc == 6 && random <= 10;
    }
    
    //method sum card number
    public static int sum( ArrayList<Integer>user ){
        int sum = 0;
        for (Integer cardNumber : user) {
            if (cardNumber < 10)
                sum += cardNumber;
        }

        if(sum >= 10){
            sum = sum % 10;
        }
        return sum;
    }
    //main method
    public static void main(String[] args) {
        ArrayList<Integer> player = new ArrayList<>();
        ArrayList<Integer> pc = new ArrayList<>();
        ArrayList<Integer> desk = new ArrayList<>();
        String[] card = new String[]{"","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        Scanner input = new Scanner(System.in);

        //add 52 cards to the desk
        for(int i = 1 ; i <= 13 ; i++)
            for(int j = 0 ; j < 4 ; j++)
                desk.add(i);

        //shuffle card 3 times
        for(int i = 0 ; i < 3 ; i++)
            Collections.shuffle(desk);

        //Distribute card to player and pc
        for(int i = 0 ; i < 4 ; i++){
            if( i % 2 == 0) pc.add(desk.get(i));
            else player.add(desk.get(i));
        }

        // remove card from desk because it was distributed to player and pc
        for(int i = 0 ; i < 4 ; i++)
            desk.remove(i);

        //sum of 2 card number
        int sum2Player = sum(player);
        int sum2Pc = sum(pc);

        //print player card
        System.out.print("Your Card : ");
        for (Integer user : player) {
            System.out.print(card[user] + " ");
        }
        System.out.println();

        //store 2 card winner
        String winner2Card = check2CardWin(sum2Player,sum2Pc);

        //ask user and pc for draw a card
        if(drawCard){
            System.out.println("Do you want to draw a card ? (y/n)");
            char answer = input.next().charAt(0);
            input.close();

            // user want to draw a card
            if(answer == 'y'){
                player.add(desk.get(desk.size()-1));
                desk.remove(desk.size()-1);
            }

            //check if pc draw a card
            if(checkPcDraw(sum2Pc)){
                pc.add(desk.get(desk.size()-1));
                desk.remove(desk.size()-1);
            }

            //sum of 3 card
            int sum3Pc = sum(pc);
            int sum3Player = sum(player);
            String winner3Card = check3CardWin(sum3Player,sum3Pc,player,pc);

            //print player card
            System.out.print("Your Card : ");
            for (Integer user : player) {
                System.out.print(card[user] + " ");
            }
            System.out.println();

            //print pc card
            System.out.print("PC card : ");
            for (Integer user : pc) {
                System.out.print(card[user] + " ");
            }

            //print 3 card winner
            System.out.println();
            System.out.println(winner3Card);
        }

        //print 2 cards winner
        else {
            System.out.print("PC card : ");
            for (Integer integer : pc) {
                System.out.print(card[integer] + " ");
            }
            System.out.println();
            System.out.print(winner2Card);
        }
    }
}