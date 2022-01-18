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

    static Scanner sc;
    static BufferedReader in;
    static PrintWriter out;
    static StringBuffer inputBuffer = new StringBuffer();

    static void createFiles() throws IOException{
        File users = new File("users.txt");
        users.createNewFile();
    }

    static void initializeUser() throws IOException{
        createFiles();
        in = new BufferedReader(new FileReader("users.txt"));
        //appends the information
        out = new PrintWriter(new BufferedWriter(new FileWriter("users.txt", true)));
    }

    //structure for funder
    //id // name // email // funder // post_id, post_id2...
    static void writeNewFunder(Funder user) throws IOException{
        initializeUser();
        String outString = user.getUserId() + " // " + user.getName() + " // " + user.getPassword() + " // " + user.getEmail() + " // " + user.getFunder() + " // " + 
                        user.getUrl();
        //add posts here when complete
        out.append(outString + "\n");
        out.close();
    }

    //structure for funder
    //id // name // email // funder // profession // skills // currentProject // interestedPost_id1, .... // starredPost_id, ... // upvotedPst_id, ... // problemAreas_id, ...
    static void writeNewIndividual(Individual user) throws IOException{
        initializeUser();
        String outString = user.getUserId() + " // " + user.getName() + " // " + user.getPassword() + " // " + user.getEmail() + " // " + 
                        user.getFunder() + " // " + user.getProfession() + " // " + user.getSkills();
        //add when complete
        out.append(outString + "\n");
        out.close();
    }

    static ArrayList<User> getUsers() throws IOException{
        ArrayList<User> users = new ArrayList<User>();
        sc = new Scanner(new File("users.txt"));
        while (sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            int id = Integer.parseInt(temp[0]);
            String name = temp[1];
            String password = temp[2];
            String email = temp[3];
            Boolean funder = temp[4].equals("true");

            if (funder){
                String url = temp[5];
                users.add(new Funder(url, name, id, email, password));
            }
            else {
                String profession = temp[5];
                String skills = temp[6];
                users.add(new Individual(name, id, email, profession, skills, password));
            }
        }
        User.totalUsers = users.size();
        return users;
    }
}
