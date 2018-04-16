package alien.com.entity;

import java.io.File;

import okhttp3.MultipartBody;

/**
 * Created by Alien on 2018-04-16.
 */

public class UserFile {
    private String decription;
    private MultipartBody.Part file;

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public MultipartBody.Part getFile() {
        return file;
    }

    public void setFile(MultipartBody.Part file) {
        this.file = file;
    }
}
