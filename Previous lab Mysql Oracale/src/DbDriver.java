public interface DbDriver {
    void connect(String url,String username,String password);
    void executeQuery(String query);

    void closeConnection();


}
