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

    private static File users = new File("src/users.txt");;
    private static File temp = new File("src/temp.txt");
    private static File posts = new File("src/posts.txt");
    private static File problemAreas = new File("src/problem.txt");
    private static File content = new File("src/content.txt");
    private static File links = new File("src/links.txt");
    private static Scanner sc;
    private static PrintWriter out;
    private static PrintWriter tempOut;
    private static StringBuffer inputBuffer;

    private static void createFiles() throws IOException{
        users.createNewFile();
        posts.createNewFile();
        problemAreas.createNewFile();
        content.createNewFile();
        links.createNewFile();
        temp.createNewFile();
    }

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

    //structure for funder
    //id // name // email // funder // profession // skills // currentProject // post1_id, ... // upvotedPst_id, ... // problemAreas_id, ...
    public static void writeNewIndividual(Individual user) throws IOException{
        initialize(users);
        String outString = user.toString();
        out.append(outString + "\n");
        out.close();
    }

    
    public static ArrayList<User> getUsers() throws IOException{
        initialize(users);
        ArrayList<User> usersList = new ArrayList<User>();
        sc = new Scanner(users);
        while (sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            int id = Integer.parseInt(temp[0]);
            String name = temp[1];
            String password = temp[2];
            String email = temp[3].equals("null") ? null : temp[3];
            Boolean funder = temp[4].equals("true");

            if (funder){
                String url = temp[5].equals("null") ? null : temp[5];

                String postIds = temp[6].equals("null") ? null : temp[6].equals("[]") ? null : 
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
        User.totalUsers = usersList.size();
        return usersList;
    }

    //update problem areas, upvoted posts, user content, user current project
    public static void updateUser(User user) throws IOException{
        initialize(users);
        sc = new Scanner(users);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.charAt(0) == user.userId + '0'){
                line = user.toString();
            }
            inputBuffer.append(line);
            inputBuffer.append("\n");
        }
        tempOut.write(inputBuffer.toString());
        users.delete();
        tempOut.flush();
        tempOut.close();
        temp.renameTo(users);
        users = new File("src/users.txt");
        temp = new File("src/temp.txt");
    }

    //POST FILE METHODS
    //id // userId // title // problemArea // numUpvotes // prize // details // desiredSkills
    public static void createPost(Post post) throws IOException{
        initialize(posts);
        String outString = post.toString();
        out.append(outString + "\n");
        out.close();

    }

    //update the number of upvotes a post has
    public static void updatePost(Post post){

    }

    //get all posts and initialize them
    public static ArrayList<Post> getPosts() throws IOException{
        initialize(posts);
        ArrayList<Post> postList = new ArrayList<Post>();
        sc = new Scanner(posts);
        while(sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            String postId = temp[0].equals("null") ? null : temp[0];
            int userId = Integer.parseInt(temp[1]);
            String title  = temp[2].equals("null") ? null : temp[2];
            String problemArea = temp[3].equals("null") ? null : temp[3];
            int numUpvotes = Integer.parseInt(temp[4]);
            int prize = Integer.parseInt(temp[5]);
            String details = temp[6].equals("null") ? null : temp[6].replace("????;", "\n");
            String desiredSkills = temp[7].equals("null") ? null : temp[7];

            postList.add(new Post(postId, userId, title, problemArea, numUpvotes, prize, details, desiredSkills));
        }
        return postList;

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
    public static void updateProblemAreas(ProblemArea problem, UserContent contnet) throws IOException{
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
        users.delete();
        tempOut.flush();
        tempOut.close();
        temp.renameTo(problemAreas);
    }

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

    //CONTENT METHODS
    //id // problemAreaId // article // title // body
    public static void createArticle(Article article) throws IOException{
        initialize(content);
        String outString = article.toString();
        out.append(outString + "\n");
        out.close();
    }

    //id // problemAreaId // article // title // [linkId1, ...]
    public static void createResource(Resource resource) throws IOException{
        initialize(content);
        String outString = resource.toString();
        out.append(outString + "\n");
        out.close();
    }

    public static void updateResources(Resource resource) throws IOException{
        initialize(content);
        sc = new Scanner(content);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String temp = line.split(" // ")[0];
            if (temp.equals(resource.getContentId())){
                line = resource.toString();
            }
            inputBuffer.append(line);
            inputBuffer.append("\n");
        }
        tempOut.write(inputBuffer.toString());
        users.delete();
        tempOut.flush();
        tempOut.close();
        temp.renameTo(content);
    }

    public static ArrayList<UserContent> getContent() throws IOException{
        initialize(content);
        ArrayList<UserContent> contentList = new ArrayList<>();
        sc = new Scanner(content);
        while(sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            String contentId = temp[0].equals("null") ? null : temp[0];
            String problemAreaId = temp[1].equals("null") ? null : temp[1];
            Boolean article  = temp[2].equals("true");
            String title = temp[3].equals("null") ? null : temp[3];
            if (article){
                String body = temp[4].equals("null") ? null : temp[4].replace("????;", "\n");
                contentList.add(new Article(contentId, problemAreaId, article, title, body));
            }
            else{
                String linkIds = temp[4].equals("[]") ? null : temp[4].substring(1, temp[4].length() - 1);
                contentList.add(new Resource(contentId, problemAreaId, article, title, linkIds));
            }

        }
        UserContent.totalContent = contentList.size();
        return contentList;
    }

    //id // url // description
    public static void createLink(Link link) throws IOException{
        initialize(links);
        String outString = link.toString();
        out.append(outString + "\n");
        out.close();
    }

    public static ArrayList<Link> getLinks() throws IOException{
        initialize(links);
        ArrayList<Link> linkList = new ArrayList<>();
        sc = new Scanner(links);
        while(sc.hasNextLine()){
            String[] temp = sc.nextLine().split(" // ");
            String linkId = temp[0].equals("null") ? null : temp[0];
            String url = temp[1].equals("null") ? null : temp[1];
            String description = temp[2].equals("null") ? null : temp[2].replace("????;", "\n");
            linkList.add(new Link(linkId, url, description));

        }
        return linkList;
    }

}
