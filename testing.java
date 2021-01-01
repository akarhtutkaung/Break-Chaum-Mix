import java.io.File;
import java.util.Scanner;

public class testing {
   public static void main(String args[]) {

      if (args.length < 2) {
         System.out.println("Usage: java testing [f1] [f2]");
         System.exit(0);
      }

      String[][] users1 = new String[26][2];
      String[][] users2 = new String[26][2];

      try {
         // read file 1
         File fp1 = new File(args[0]);
         Scanner myReader1 = new Scanner(fp1);
         int count = 0;
         while (myReader1.hasNext()) {
            String data = myReader1.nextLine();
            String[] tmp = data.split(",");
            users1[count][0] = tmp[1];
            users1[count][1] = tmp[2];
            count++;
         }
         myReader1.close();

         // read file 2
         File fp2 = new File(args[1]);
         Scanner myReader2 = new Scanner(fp2);
         count = 0;
         while (myReader2.hasNext()) {
            String data = myReader2.nextLine();
            String[] tmp = data.split(",");
            users2[count][0] = tmp[1];
            users2[count][1] = tmp[2];
            count++;
         }
         myReader2.close();
         // compare
         int correct = 0;
         int total = 20;
         for (int i = 0; i < 10; i++) {
            if (users1[i][0].equals(users2[i][0]) || users1[i][0].equals(users2[i][1])) {
               correct++;
            }
            if (users1[i][1].equals(users2[i][0]) || users1[i][1].equals(users2[i][1])) {
               correct++;
            }
         }
         double finCal = Double.valueOf(correct) / Double.valueOf(total);
         System.out.println("Correctness: " + finCal * 100 + "%");
      } catch (Exception e) {
         System.out.println("[!] Error: " + e);
      }
   }
}
