/**
 * import all the required packages
 */

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

class Mongo
{
    FileWriter fwrite;
    File file;
    Scanner sc= new Scanner(System.in);
    JSONArray employeeList = new JSONArray();
    /**constructor is used to create a empty json file
    * param filename of String type
    creates a new file
    */
    Mongo(String name)
    {
        try
        {
            this.file=new File(name);
            this.fwrite=new FileWriter(name);
            if(this.file.exists())
            {
                JSONObject json=new JSONObject();
                this.fwrite = new FileWriter(name);
                this.fwrite.write(json.toJSONString());
                this.fwrite.close();
                System.out.println("Successful creation");
            }
            else
            {
                System.out.println("File already exists");
            }
        }
        catch(Exception e)
        {
            System.out.println("Creation exception");
        }
    }

    /**
     * creates a single key value pair
     * @return boolean
     */
    public boolean insertOne()
    {
        String id=this.UUID();
        String name=sc.next();
        JSONObject employeeObject = new JSONObject();
        JSONObject employee1= new JSONObject();
        employeeObject.put("employee", employee1);
        //Add employees to list

        this.employeeList.add(employeeObject);
        //Write JSON file
        try
        {
            //We can write any JSONArray or JSONObject instance to the file
            FileWriter fwrite=new FileWriter(this.file,true);
            fwrite.write(this.employeeList.toJSONString());
            fwrite.flush();

            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public void find()
    {
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String str;
            while((str=br.readLine())!=null)
            {
                System.out.println(str);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error occured");
        }
    }

    /**
     * to generate unique ids for identification
     * @return id
     */
    public String UUID()
    {
        UUID uuid=UUID.randomUUID();
        String str=String.valueOf(uuid);
        return str;
    }

    /**
     * deletes the entire file
     * @return boolean value whether the file is deleted or not
     */
    public boolean delete()
    {
        if (file.delete()) {
            System.out.println("File deleted successfully");
            return true;
        }
        else {
            System.out.println("Failed to delete the file");
            return false;
        }
    }
    /**this method is used to insert in a son file returns true on success
     * returns boolean value to verify the success of operation
     */
    @SuppressWarnings("unchecked")
    public boolean insert() throws IOException
    {
        String id=this.UUID();
        System.out.println("Enter first name");
        String firstname=sc.next();
        String lastname=sc.next();
        int age=sc.nextInt();
        String role=sc.next();
        String Manager=sc.next();
        int Experience=sc.nextInt();
        //First Employee
        JSONObject employee1= new JSONObject();
        employee1.put("id",id);
        employee1.put("firstName", firstname);
        employee1.put("lastName", lastname);
        employee1.put("age",age);
        employee1.put("Role",role);
        employee1.put("Manager",Manager);
        employee1.put("Experience",Experience);
        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employee1);
        //Add employees to list

        this.employeeList.add(employeeObject);
        //Write JSON file
        try
        {
            //We can write any JSONArray or JSONObject instance to the file
            FileWriter fwrite=new FileWriter(this.file,true);
            fwrite.write(this.employeeList.toJSONString());
            fwrite.flush();

            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param name of the feild ,value and operator
     * prints all the objects that meets the given condition
     */
    public void findAll(String name, String value)
    {

    }

    //public void
}
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Enter file name");
            Scanner sc = new Scanner(System.in);
            String filename = sc.next();
            Mongo mobj = new Mongo(filename);
            System.out.println("Add content");
            // here the schema is employee with contents- FirstName, Lastname, Age, Role, Manager, Experience
            mobj.insert();
            mobj.insert();
            mobj.insertOne();
            mobj.find();
            mobj.findAll("age",String.valueOf(21));
            mobj.delete();
        }
        catch(Exception e)
        {
            System.out.println("Exception caught");
        }
    }
}