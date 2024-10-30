package ofofo.data.models;


public class Diary {
    private String userName;
    private String password;
    private boolean isLocked = false;

    public Diary() {

    }
    public Diary(String userName) {
        this.userName = userName;
    }

    public Diary(String userName, String loginPassword) {
        this.userName = userName;
        this.password = loginPassword;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lockDiary(){
        isLocked = true;
    }

    public void unlockDiary() {
        if(password == null) {
            isLocked = false;
        }
        else {
            throw new RuntimeException("Provide password to unlock the diary");
        }
    }
    public void unlockDiary(String password) {
        if(!password.equals(this.password)) {
            throw new RuntimeException("Incorrect password");
        }
        isLocked = false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String loginPassword) {
        this.password = loginPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Diary{" + "userName: " + userName + ", loginPassword: " + password + '}';
    }
}
