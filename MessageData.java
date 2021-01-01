public class MessageData {
   public String[][] sender;
   public String[][] receiver;

   public MessageData(){
      sender = new String[100][32];
      receiver = new String[100][32];
   }

   public void storeData(int batch, String data){
      String names = data.substring(4,192);
      if(data.charAt(0)=='S'){
         String[] temp = names.split("', '");
         sender[batch] = temp;
      }
      else if(data.charAt(0)=='R') {
         receiver[batch] = names.split("', '");
      }
   }
}
