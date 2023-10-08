import java.util.ArrayList;
import java.util.List;

public class Student implements LibraryUser{

    private  int studentId;
    private  String name;

    private List<LibraryItem> ls;

    public Student(int studentId, String name, List<LibraryItem> ls) {
        this.studentId = studentId;
        this.name = name;
        this.ls = new ArrayList<>();
    }

    @Override
    public void borrowItem(LibraryItem item) {

        if(item!=null){
            ls.add(item);
            System.out.println(name+" has borrowed: "+
                    item.getTitle());
        }else{
            System.out.println("Invalid item");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {

        if(item!=null && ls.contains(item)){
            ls.remove(item);
            System.out.println(name+ "has returned: "+
                    item.getTitle());
        }
        else{
            System.out.println( name+" did not have this item checked out");
        }
    }

    @Override
    public void viewCheckedOutItems() {// which item has borrowed

        if(ls.isEmpty()) {
            System.out.println("No items");
        }else{
            for(LibraryItem item: ls){
                System.out.println(item.getTitle()+" ");
            }
        }
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<LibraryItem> getLs() {
        return ls;
    }
}
