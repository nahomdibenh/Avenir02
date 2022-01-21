import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataServices {

    private static File users;
    private static File temp;
    private static File problemAreas;
    private static Scanner sc;
    private static BufferedReader in;
    private static PrintWriter out;
    private static PrintWriter tempOut;
    private static StringBuffer inputBuffer;

    private static void createFiles() throws IOException{
        users = new File("users.txt");
        users.createNewFile();

        temp = new File("temp.txt");
        temp.createNewFile();
    }

    private static void initializeUser() throws IOException{
        createFiles();
        in = new BufferedReader(new FileReader("users.txt"));
        //appends the information
        out = new PrintWriter(new BufferedWriter(new FileWriter("users.txt", true)));
        tempOut = new PrintWriter(new BufferedWriter(new FileWriter("temp.txt")));
        //reset inputBuffer of previous data
        inputBuffer = new StringBuffer();

    }

    //writing new users is done on signup and therefore only requires updating sign up fields
    //structure for funder
    //id // name // email // funder // post_id, post_id2...
    public static void writeNewFunder(Funder user) throws IOException{
        initializeUser();
        String outString = user.getUserId() + " // " + user.getName() + " // " + user.getPassword() + " // " + user.getEmail() + " // " + user.getFunder() + " // " + 
                        user.getUrl();
        //add posts here when complete
        out.append(outString + "\n");
        out.close();
    }

    //structure for funder
    //id // name // email // funder // profession // skills // currentProject // interestedPost_id1, .... // starredPost_id, ... // upvotedPst_id, ... // problemAreas_id, ...
    public static void writeNewIndividual(Individual user) throws IOException{
        initializeUser();
        String outString = user.getUserId() + " // " + user.getName() + " // " + user.getPassword() + " // " + user.getEmail() + " // " + 
                        user.getFunder() + " // " + user.getProfession() + " // " + user.getSkills();
        //add when complete
        out.append(outString + "\n");
        out.close();
    }

    public static ArrayList<User> getUsers() throws IOException{
        ArrayList<User> users = new ArrayList<User>();
        sc = new Scanner(new File("users.txt"));
        while (sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            int id = Integer.parseInt(temp[0]);
            String name = temp[1];
            String password = temp[2];
            String email = temp[3].equals("null") ? null : temp[3];
            Boolean funder = temp[4].equals("true");

            if (funder){
                String url = temp[5].equals("null") ? null : temp[5];
                users.add(new Funder(url, name, id, email, password));
            }
            else {
                String profession = temp[5].equals("null") ? null : temp[5];
                String skills = temp[6].equals("null") ? null : temp[6];
                //problem areas
                users.add(new Individual(name, id, email, profession, skills, password));
            }
        }
        User.totalUsers = users.size();
        return users;
    }

    public static void createProblemAreas(ProblemArea problem){

    }

    public static void updateProblemAreas(Individual user, ProblemArea problem) throws IOException{
        initializeUser();
        sc = new Scanner(new File("users.txt"));
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] temp;
            if (line.charAt(0) == user.userId + '0'){
                temp = line.split(" // ");
                //if this is the first problem area being created
                if (user.getProblemAreas().size() == 1){
                    line += " // " + "{" + problem.problemId + "}";
                }
                else{
                    String newArea = temp[temp.length - 1];
                    System.out.println(newArea);
                    //remove the bracket
                    newArea = newArea.substring(0, newArea.length() - 1);
                    newArea += ", " + problem.problemId + "}";
                    System.out.println(newArea);

                    line = line.substring(0, line.length() - newArea.length() + 1);
                    System.out.println(line);
                    line += " // " + newArea;
                }
            }
            inputBuffer.append(line);
            inputBuffer.append("\n");
        }
        tempOut.write(inputBuffer.toString());
        users.delete();
        tempOut.flush();
        tempOut.close();
        temp.renameTo(new File("users.txt"));
    }

}
