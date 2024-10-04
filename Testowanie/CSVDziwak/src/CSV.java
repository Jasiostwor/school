import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CSV {

    private final String FILE_PATH = "src/5TPob.csv";
    private final String OUTPUT_FILE_NAME = "JH-ZADANIE.csv";
    private boolean inMembersSection = false;

    List<User> members = new ArrayList<>();

    public CSV() {
        readFile();
        members.sort(Comparator.comparing(User::getImie));
        writeFile();
    }

    private void readFile(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if(line.replace("\0", "").contains("Imie")){
                    inMembersSection = true;
                    continue;
                }

                if(inMembersSection){
                    if(line.replace("\0", "").contains("3. Dzia")){
                        inMembersSection = false;
                        break;
                    } else {
                        String columns[] = line.replace("\0", "").split("\t");
                        if(columns.length >= 6){
                            String name = columns[0].trim();
                            String[] nameParts = name.split(" ");
                            String firstName = nameParts[1].isEmpty() ?  " --- " : nameParts[0];
                            String lastName = nameParts[1].isEmpty() ? nameParts[0] : nameParts[1];

                            String durationString = columns[3].trim();

                            User member = new User(firstName, lastName, durationString);
                            members.add(member);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            bw.write("Nazwisko,Imię,Status,Czas_uczestnictwa");
            bw.newLine();
            for(User member : members){
                String lastName = member.getNazwisko();
                String firstName = member.getImie();
                String certificateStatus = member.getStatus();
                String courseDuration = member.getDlugosc();
                bw.write(lastName + "," + firstName + "," + certificateStatus + "," + courseDuration);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showSortedData(){
        System.out.println("Dane zostaną zapisane w pliku " + OUTPUT_FILE_NAME + " jako:");
        System.out.println("Nazwisko,Imię,Status,Czas_uczestnictwa");
        for(User member : members){
            System.out.println(member.getNazwisko() + "," + member.getImie() + "," + member.getStatus() + "," +  member.getDlugosc());
        }
    }
}