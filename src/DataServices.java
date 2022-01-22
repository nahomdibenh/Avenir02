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

    //keep all file i/o objects contained to the class to prevent accidental leaks
    private static File users = new File("src/users.txt");;
    private static File temp = new File("src/temp.txt");
    private static File problemAreas = new File("src/problem.txt");
    private static Scanner sc;
    private static PrintWriter out;
    private static PrintWriter tempOut;
    private static StringBuffer inputBuffer;

    //create files if they do not already exist
    private static void createFiles() throws IOException{
        users.createNewFile();
        problemAreas.createNewFile();
        temp.createNewFile();
    }

    //specify file reading and writing tools to interact with a specific file
    private static void initialize(File file) throws IOException{
        createFiles();
        //appends the information
        out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        tempOut = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
        //reset inputBuffer of previous data
        inputBuffer = new StringBuffer();

    }

    //ALL USER FILE METHODS

    //CREATING New Users
    //writing new users is done on signup and therefore only requires updating sign up fields
    //structure for funder
    //id // name // email // funder // url // [post_id, post_id2...] // upvotedpost_id, ...
    public static void writeNewFunder(Funder user) throws IOException{
        initialize(users);
        String outString = user.toString();
        out.append(outString + "\n");
        out.close();
    }

    //structure for individual
    //id // name // email // funder // profession // skills // currentProject // post1_id, ... // upvotedPst_id, ... // problemAreas_id, ...
    public static void writeNewIndividual(Individual user) throws IOException{
        initialize(users);
        String outString = user.toString();
        out.append(outString + "\n");
        out.close();
    }

    //convert users in the File to Real Instances
    //update the total user count on login for creating accurate and unique user ids
    public static ArrayList<User> getUsers() throws IOException{
        initialize(users);
        ArrayList<User> usersList = new ArrayList<User>();
        sc = new Scanner(users);
        //continue to read input if a line exists
        while (sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            int id = Integer.parseInt(temp[0]);
            String name = temp[1];
            String password = temp[2];
            //any text stored as null should be
            String email = temp[3].equals("null") ? null : temp[3];
            Boolean funder = temp[4].equals("true");

            if (funder){
                String url = temp[5].equals("null") ? null : temp[5];

                //Replace arrayList.toString() with null if it is null or empty
                String postIds = temp[6].equals("null") ? null : temp[6].equals("[]") ? null : 
                                //trim off the brackets of a string the is permitted
                                temp[6].substring(1, temp[6].length() - 1);
                String upvotedPosts = temp[7].equals("null") ? null : temp[7].equals("[]") ? null : 
                                temp[7].substring(1, temp[7].length() - 1);

                usersList.add(new Funder(url, name, id, email, password, postIds, upvotedPosts));
            }
            else {
                String profession = temp[5].equals("null") ? null : temp[5];
                String skills = temp[6].equals("null") ? null : temp[6];
                String currentProjecString = temp[7].equals("null") ? null : temp[7];

                String postIds = temp[8].equals("null") ? null : temp[8].equals("[]") ? null : 
                                temp[8].substring(1, temp[8].length() - 1);
                String upvotedPosts = temp[9].equals("null") ? null : temp[9].equals("[]") ? null : 
                                temp[9].substring(1, temp[9].length() - 1);
                String problemAreaIds = temp[10].equals("null") ? null : temp[10].equals("[]") ? null : 
                                temp[10].substring(1, temp[10].length() - 1);

                usersList.add(new Individual(name, id, email, profession, skills, password, currentProjecString, postIds, upvotedPosts, problemAreaIds));
            }
        }
        // the number of total users stored in the text always be equal the actual total users
        User.totalUsers = usersList.size();
        return usersList;
    }

    //update userData that's been updated in its instance using the toString method for formatting
    public static void updateUser(User user) throws IOException{
        initialize(users);
        sc = new Scanner(users);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            //identify the line in the text file with the user that matches the user who wants to be updates
            if (line.charAt(0) == user.userId + '0'){
                line = user.toString();
            }
            //add the line to the inputBuffer after its been updated / if it did not meet the update condition
            inputBuffer.append(line);
            inputBuffer.append("\n");
        }
        //write the buffer data using the file writer to the temo file
        tempOut.write(inputBuffer.toString());
        //close the filewriter since we will not be writing to it anymore
        tempOut.close();
        temp.renameTo(users);
    }


    //PROBLEM AREA FILE METHODS
    //id // userId // name // rootCause // impact // nextStep // problemStatement // [contentIds, ...]
    public static void createProblemAreas(ProblemArea problem) throws IOException{
        initialize(problemAreas);
        String outString = problem.toString();
        out.append(outString + "\n");
        out.close();
    }

    //update the content list in a problem area
    public static void updateProblemAreas(ProblemArea problem) throws IOException{
        initialize(problemAreas);
        sc = new Scanner(problemAreas);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String temp = line.split(" // ")[0];
            if (temp.equals(problem.getProblemId())){
                line = problem.toString();
            }
            inputBuffer.append(line);
            inputBuffer.append("\n");
        }
        tempOut.write(inputBuffer.toString());
        tempOut.close();
        temp.renameTo(problemAreas);
    }

    //get all problem areas and set the number of total problem areas
    public static ArrayList<ProblemArea> getProblemAreas() throws IOException{
        initialize(problemAreas);
        ArrayList<ProblemArea> problemAreasList = new ArrayList<>();
        sc = new Scanner(problemAreas);
        while(sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            String problemId = temp[0].equals("null") ? null : temp[0];
            int userId = Integer.parseInt(temp[1]);
            String name  = temp[2].equals("null") ? null : temp[2];
            String rootCause = temp[3].equals("null") ? null : temp[3];
            String impact = temp[4].equals("null") ? null : temp[4];
            String nextStep = temp[5].equals("null") ? null : temp[5].replace("????;", "\n");
            String problemStatement = temp[6].equals("null") ? null : temp[6].replace("????;", "\n");
            String contentIds = temp[7].equals("[]") ? null : temp[7].substring(1, temp[7].length() - 1);;

            problemAreasList.add(new ProblemArea(problemId, userId, name, rootCause, impact, nextStep, problemStatement, contentIds));
        }
        ProblemArea.problemAreasCount = problemAreasList.size();
        return problemAreasList;
    }
}