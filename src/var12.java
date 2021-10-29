import java.io.*;
import java.util.Scanner;

public class var12 {
    public static void main(String[] args) throws IOException {
        DataOutputStream dout = null;
        DataInputStream din = null;
        DataOutputStream dout2 = null;
        DataInputStream din2 = null;
        File res;
        Scanner sc= new Scanner(System.in);
        try {
            //создание src файла и его заполнение
            File file = new File("src.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            dout = new DataOutputStream(new FileOutputStream(file));
            System.out.println("count:");
            int count = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < count; i++){
                String line = sc.nextLine();
                dout.writeUTF(line);
            }

            //создание результирующего файла
            res = new File("result.txt");
            dout2 = new DataOutputStream(new FileOutputStream(res));
            din = new DataInputStream(new FileInputStream(file));

            /*String str = din.readUTF();
            int leng = str.length();
            if(leng % 2 == 0){
                dout2.writeUTF(str);
            }*/

            for(int i =0; i<count;i++){
                String sl = din.readUTF();
                int cs = sl.length();
                if(cs % 2 ==0) {
                    dout2.writeUTF(sl);
                }
            }

            din2 = new DataInputStream(new FileInputStream(res));
            for(int i = 0; i < count; i++){
                String str = din2.readUTF();
                System.out.print(str+"\n");
            }

        }
        catch (EOFException e) {

        }
        catch (IOException ioe) {
            System.out.println("Ошибка создания файла. " + ioe);
        }

        finally {
            dout.flush();
            dout.close();
            din.close();
            dout2.flush();
            dout2.close();
            din2.close();
        }
    }
}

