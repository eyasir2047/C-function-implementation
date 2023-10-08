
abstract class dbDriver {
    abstract public void load(int studentID);
    abstract public void load(String examDate);
    abstract public void load();
    abstract public void save(Student data);
    abstract public void closeDatabase();
}