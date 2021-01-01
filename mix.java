import java.io.*;
import java.util.Scanner;

public class mix {
   public static String target[];
   public static String users[];
   public static double tContRec[];
   public static double tNoContRec[];
   public static MessageData messageData;
   public static int targConCount = 0;
   public static int targNoConCount = 0;
   public static double v[];
   private static FileWriter csv;

   public static void initUser() {
      target = new String[]{"a0", "b0", "c0", "d0", "e0", "f0", "g0",
          "h0", "i0", "j0", "k0", "l0", "m0", "n0", "o0",
          "p0", "q0", "r0", "s0", "t0", "u0", "v0", "w0",
          "x0", "y0", "z0"};
      users = new String[]{
          "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9",
          "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9",
          "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9",
          "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9",
          "e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9",
          "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9",
          "g0", "g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8", "g9",
          "h0", "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9",
          "i0", "i1", "i2", "i3", "i4", "i5", "i6", "i7", "i8", "i9",
          "j0", "j1", "j2", "j3", "j4", "j5", "j6", "j7", "j8", "j9",
          "k0", "k1", "k2", "k3", "k4", "k5", "k6", "k7", "k8", "k9",
          "l0", "l1", "l2", "l3", "l4", "l5", "l6", "l7", "l8", "l9",
          "m0", "m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8", "m9",
          "n0", "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
          "o0", "o1", "o2", "o3", "o4", "o5", "o6", "o7", "o8", "o9",
          "p0", "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9",
          "q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9",
          "r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9",
          "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9",
          "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9",
          "u0", "u1", "u2", "u3", "u4", "u5", "u6", "u7", "u8", "u9",
          "v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9",
          "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9",
          "x0", "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9",
          "y0", "y1", "y2", "y3", "y4", "y5", "y6", "y7", "y8", "y9",
          "z0", "z1", "z2", "z3", "z4", "z5", "z6", "z7", "z8", "z9"
      };
      tContRec = new double[260];
      tNoContRec = new double[260];
      v = new double[260];
   }

   public static void saveData(String target, int a, int b) throws Exception {
      if(target.equals("z0")){
         csv.append(target + "," + users[a] + "," + users[b]);
      } else {
         csv.append(target + "," + users[a] + "," + users[b] + "\n");
      }
   }

   public static int findIndex(String name) {
      for (int i = 0; i < 260; i++) {
         if (name.equals(users[i])) {
            return i;
         }
      }
      return -1;
   }

   public static void targetContribute(int batch) {
      for (int y = 0; y < 32; y++) {
         int index = findIndex(messageData.receiver[batch][y]);
         tContRec[index] += 1.0 / 32.0; // set 1/b
      }
   }

   public static void targetNoContribute(int batch) {
      for (int y = 0; y < 32; y++) {
         int index = findIndex(messageData.receiver[batch][y]);
         tNoContRec[index] += 1.0 / 32.0; // set 1/b
      }
   }

   public static void calculate(String target) {
      // get requirements data
      for (int i = 0; i < 100; i++) {
         for (int x = 0; x < 32; x++) {
            if (messageData.sender[i][x].equals(target)) {
               targetContribute(i);
               targConCount++;
               continue;
            } else if (x == 31) {
               targetNoContribute(i);
               targNoConCount++;
            }
         }
      }

      //start calculating
      for (int i = 0; i < 260; i++) {
         v[i] = (32.0 * (tContRec[i] / Double.valueOf(targConCount))) - (31.0 * (tNoContRec[i] / Double.valueOf(targNoConCount)));
      }
   }

   public static void decideFriend(String target) throws Exception {
      double max = 0.0;
      int firstIndex = -1;
      int secondIndex = -1;
      for (int i = 0; i < 260; i++) {
         if (v[i] > max) {
            max = v[i];
            firstIndex = i;
         }
      }
      max = 0.0;
      for (int i = 0; i < 260; i++) {
         if (v[i] > max && i != firstIndex) {
            max = v[i];
            secondIndex = i;
         }
      }
      saveData(target, firstIndex, secondIndex);
   }

   public static void main(String args[]) throws Exception {
      initUser();
      messageData = new MessageData();

      File fp = new File("target-rounds");
      Scanner myReader = new Scanner(fp);
      int batch = 0;
      while (myReader.hasNext()) {
         messageData.storeData(batch, myReader.nextLine());
         messageData.storeData(batch, myReader.nextLine());
         batch++;
      }
      myReader.close();
      csv = new FileWriter("results.csv");
      for (int i = 0; i < target.length; i++) {
         for (int x = 0; x < 260; x++) {
            tContRec[x] = 0.0;
            tNoContRec[x] = 0.0;
            v[x] = 0.0;
         }

         calculate(target[i]);      // start calculating for the specific target
         decideFriend(target[i]);   // start deciding who is friend
      }
      csv.close();
   }
}
