package example.code.assignment1;

public class User {

    private String mEmail;
    private String mPassword;
    private String mId;

    public String getmEmail() {

        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {

        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public User(String id, String email, String password) {
        this.mId = id;
        this.mEmail = email;
        this.mPassword = password;
    }
}
