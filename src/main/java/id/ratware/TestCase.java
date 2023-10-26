package id.ratware;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ratware.entity.CarEntity;
import id.ratware.pojo.typicode.TypicodeResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/***********************************************************************
 * Module:  id.ratware.TestCase
 * Author:  Ratwareid
 * Created: 25/10/2023
 * Info:  If You dont know me ? Just type ratwareid in google.
 ***********************************************************************/
public class TestCase {

    public static void main(String[] args){
        testCase1();
        final String input2 =   "21090022002346,FCD5D9D34A6F\n" +
                                "21090022002838,FCD5D9D34C5B\n" +
                                "21090022002965,FCD5D9D34CDA\n" +
                                "21090022003748,FCD5D9D34FE9\n" +
                                "21090022003798,FCD5D9D3501B";
        testCase2(input2);
        String queryTest3 = "select a.*,b.alamat,c.alamat as cluster\n" +
                            "from user a\n" +
                            "left join alamat b on a.cid = b.cid\n" +
                            "left join cluster c on a.cluster_id = c.cid";
        System.out.println("Case 3 in url : https://www.db-fiddle.com/f/9T1DkMFkyXMgEQiw8PZrPS/15");
        System.out.println("or Query : \n"+queryTest3);

        int[] deretA = {5, 8, 11, 14, 17};
        int[] deretB = {5, 8, 13, 20, 29};
        int[] deretC = {1, 4, 9, 16, 25, 36};
        testCase4(deretA,deretB,deretC);

        ArrayList<Character> hurufAtoY = new ArrayList<>();
        for (char huruf = 'A'; huruf <= 'Y'; huruf++) {
            hurufAtoY.add(huruf);
        }
        testCase5(hurufAtoY,5);
        testCase5(1,7);
        testCase6();
    }

    private static void testCase1(){
        System.out.println("Case 1:");
        CarEntity mobilA = new CarEntity("A", "Merah", "Toyota",120, 300);
        CarEntity mobilB = new CarEntity("B", "Biru","Honda", 130, 350);
        CarEntity mobilC = new CarEntity("C", "Grey", "Suzuki",110, 250);

        // Menampilkan informasi masing-masing mobil
        System.out.println(mobilA.getInfo());
        System.out.println(mobilB.getInfo());
        System.out.println(mobilC.getInfo());
    }

    private static void testCase2(String input){
        System.out.println("\nCase 2:");
        System.out.println("input:\n"+input);
        String[] datas = input.split("\n");
        StringBuilder output = new StringBuilder("");

        for (String data : datas) {
            String[] line = data.split(",");
            if (line.length != 2){
                System.err.println("Format Data tidak valid");
                continue;
            }
            String sn = line[0];
            String macMalformed = line[1];
            if (macMalformed.length() % 2 != 0){
                System.err.println("Mac tidak valid");
                continue;
            }
            output.append(sn).append(",");
            int count = 0;
            for (int i = 0; i < macMalformed.length(); i++) {
                if (count == 2){
                    output.append(":");
                    count = 0;
                }
                output.append(macMalformed.charAt(i));
                count++;
            }
            output.append("\n");
        }

        System.out.println("output:\n"+output.toString());
    }

    public static void testCase4(int[]... derets){
        System.out.println("\nCase 4:");
        for (int[] deret : derets) {
            if (deret.length < 3){
                System.err.println("Jumlah deret kurang dari 3");
                continue;
            }

            int a = deret[0];
            int b = deret[1];
            int c = deret[2];
            int r = b-a;
            int div = c-b-r;
            int sizeLeft = 10 - deret.length;
            ArrayList<Integer> allNumber = IntStream.of(deret).boxed().collect(Collectors.toCollection(ArrayList::new));
            for (int i=1;i<=sizeLeft;i++){
                int previous = allNumber.get(allNumber.size()-1);
                int nextNumber = previous + (r * i) + div;
                allNumber.add(nextNumber);
            }
            System.out.println(allNumber);
        }
    }


    public static void testCase5(Object data, int height){
        System.out.println("\nCase 5: ");
        if (data instanceof Integer){
            List<List<Integer>> dataLine = new ArrayList<>();
            for (int i = 0; i < height; i++) {
                // Mencetak spasi sebelum karakter tengah
                for (int j = 0; j < height - i -1; j++) {
                    System.out.print(" ");
                }
                ArrayList<Integer> currentLine = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i){
                        currentLine.add((Integer) data);
                        System.out.print(data+" ");
                    }else{
                        ArrayList<Integer> previousLine = (ArrayList<Integer>) dataLine.get(i-1);
                        int sum = previousLine.get(j-1) + previousLine.get(j);
                        currentLine.add(sum);
                        System.out.print(sum+" ");
                    }
                }
                dataLine.add(currentLine);
                System.out.println(); // buat baris baru
            }
        }else if (data instanceof ArrayList){
            ArrayList<Character> list = (ArrayList<Character>) data;

            int jeda = 0;
            int pos = 0;
            for (int i = 0; i < height; i++) {
                // Mencetak spasi sebelum karakter tengah
                for (int j = 0; j < height - i -1; j++) {
                    System.out.print("  ");
                }

                for (int j = 0; j <= i + jeda; j++) {
                    char huruf = (char) (list.get(pos));
                    pos++;
                    System.out.print(huruf+" ");
                }

                System.out.println(); // buat baris baru
                jeda++;
            }
        }
    }

    public static void testCase6(){
        try {
            String url = "https://jsonplaceholder.typicode.com/users/2";
            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                TypicodeResponse typicodeResponse = objectMapper.readValue(response.toString(),TypicodeResponse.class);

                System.out.println("\nCase 6:");
                System.out.println("User Data");
                System.out.println("Name: "+typicodeResponse.getName());
                System.out.println("Website: "+typicodeResponse.getWebsite());
                System.out.println("Email: "+typicodeResponse.getEmail());
                System.out.println("Phone: "+typicodeResponse.getPhone());
            } else {
                System.out.println("Permintaan GET gagal. Kode respons: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
