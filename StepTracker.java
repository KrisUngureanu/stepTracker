import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class StepTracker {
    HashMap<Integer, MonthData> monthdata = new HashMap<Integer, MonthData>();
    int goal = 1000;

    StepTracker() {
        for (Integer i = 1; i < 13; i++) {

            MonthData monthdataObj = new MonthData();
            this.monthdata.put(i, monthdataObj);
        }

    }

    public static void printMenu() {
        System.out.println("Menu");
        System.out.println("1. Enter the number of steps for a specific day.");
        System.out.println("2. Print statistics for a specific month.");
        System.out.println("3. Change the step count goal per day.");
        System.out.println("4. Exit the application");
    }
    public void printStatistic(Integer month){
        Converter converter = new Converter();
        HashMap<Integer, MonthData> monthdata = this.monthdata;
        MonthData mnth = monthdata.get(month);
        HashMap<Integer, Integer> mas = mnth.mas;
        Integer generalSteps = 0;
        Integer maxSteps = 0;
        Double averageSteps = 0.0;
        ArrayList<Integer> seriaDay = new ArrayList<>();
        for (Integer day : mas.keySet()) {
            Integer steps = mas.get(day);
            if (steps > maxSteps) {
                maxSteps = steps;
            }
            if (steps >= this.goal) {
                seriaDay.add(day);
            }
           generalSteps += steps;
        }
        if (seriaDay.size() > 0) {
            
            String seriaText = "";
            for(Integer d: seriaDay){
                if (seriaText.length() == 0) {
                    seriaText = "day "+ Integer.toString(d);
                } else {
                    seriaText = seriaText + ", day "+ Integer.toString(d);
                }
                
            }
            System.out.println("The best seria: " + seriaText);
        }
        averageSteps = generalSteps.doubleValue() / 30;
        Integer disstance = converter.calcDisstance(generalSteps);
        Double ccal = converter.clasCalories(generalSteps);
        System.out.println("Distance covered  " + disstance);
        System.out.println("Burned kilocalories " + ccal);
        
        System.out.println("Total number of steps for the month " + generalSteps);
        System.out.println("Maximum number of steps taken in a month " + maxSteps);
    }
    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void setSteps(int numMonth, int numDay, int numSteps) {
        MonthData monthData = this.monthdata.get(numMonth);
        monthData.setDayAndStep(numDay, numSteps);

    }

    public void aboutSteps() {

        HashMap<Integer, MonthData> monthdata = this.monthdata;
        for (Integer month : monthdata.keySet()) {
            String mesName = "";
            switch (month) {
                case 1:
                    mesName = "January";
                    break;
                case 2:
                    mesName = "February";
                    break;
                case 3:
                    mesName = "March";
                    break;
                case 4:
                    mesName = "April";
                    break;
                case 5:
                    mesName = "May";
                    break;
                case 6:
                    mesName = "June";
                    break;
                case 7:
                    mesName = "July";
                    break;
                case 8:
                    mesName = "August";
                    break;
                case 9:
                    mesName = "September";
                    break;
                case 10:
                    mesName = "October";
                    break;
                case 11:
                    mesName = "November";
                    break;
                case 12:
                    mesName = "December";
                    break;
                default:
                    mesName = "Invalid month";
                    break;
            }
            System.out.println(mesName + ":");
            MonthData mnth = monthdata.get(month);
            HashMap<Integer, Integer> mas = mnth.mas;
            for (Integer day : mas.keySet()) {
                Integer steps = mas.get(day);
                System.out.println("Day " + day + " steps " + steps);
            }
        }

    }

    public void aboutStepsByDefMonth(Integer month) {

        HashMap<Integer, MonthData> monthdata = this.monthdata;
        String text = "";
        String mesName = "";
        switch (month) {
            case 1:
                mesName = "January";
                break;
            case 2:
                mesName = "February";
                break;
            case 3:
                mesName = "March";
                break;
            case 4:
                mesName = "April";
                break;
            case 5:
                mesName = "May";
                break;
            case 6:
                mesName = "June";
                break;
            case 7:
                mesName = "July";
                break;
            case 8:
                mesName = "August";
                break;
            case 9:
                mesName = "September";
                break;
            case 10:
                mesName = "October";
                break;
            case 11:
                mesName = "November";
                break;
            case 12:
                mesName = "December";
                break;
            default:
                mesName = "Invalid month";
                break;
        }
        System.out.println(mesName + ":");
        MonthData mnth = monthdata.get(month);
        HashMap<Integer, Integer> mas = mnth.mas;
        for (Integer day : mas.keySet()) {
            Integer steps = mas.get(day);
            if (text.length() > 0) {
                text = text + ", day "  + day + " steps " + steps;
            } else {
                text = "Day " + day + " steps " + steps;
            }
        }
        System.out.println(text);
    }

    public class MonthData {
        HashMap<Integer, Integer> mas = new HashMap<>();

        // 30 дней по 0 шагов
        MonthData() {
            for (int i = 1; i < 31; i++) {
                this.mas.put(i, 0);
            }
        }

        public void setDayAndStep(int day, int step) {
            HashMap mas = this.mas;
            this.mas.put(day, step);
        }
    }
    public class Converter {
        public  Integer calcDisstance(Integer steps){
            Integer disstance = steps * 75;
            return disstance;
        }
        public  Double clasCalories(Integer steps){
            Double ccal = (steps.doubleValue() * 50) / 1000;
            return ccal;
        }
        
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        StepTracker steptrack = new StepTracker();
        int userInput = scanner.nextInt();
        int numMonth;
        int numDay;
        int numSteps;
        while (userInput != 0) {
            // обработка разных случаев
            if (userInput == 4) {
                break;
            } else if (userInput == 1) {
                System.out.println("Enter the month.");
                numMonth = scanner.nextInt();
                System.out.println("Enter the day.");
                numDay = scanner.nextInt();
                System.out.println("Enter the number of steps.");
                numSteps = scanner.nextInt();
                steptrack.setSteps(numMonth, numDay, numSteps);
                
            } else if(userInput == 2){
                System.out.println("Enter the month.");
                numMonth = scanner.nextInt();
                steptrack.aboutStepsByDefMonth(numMonth);
                steptrack.printStatistic(numMonth);
            } else if(userInput == 3){
                System.out.println("Enter the goal.");
                Integer goal = scanner.nextInt();
                steptrack.setGoal(goal);
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Program completed");
    }
}
