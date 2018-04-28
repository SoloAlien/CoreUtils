package alien.com.httputil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by Alien on 2018-04-20.
 */

public class FileUploadUtil {
    /**
     * Multiple files upload(we define MultipartBody.Part upload key name's "files")
     * @param fileList
     * @return
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> fileList){
        ArrayList<MultipartBody.Part> MultipartBodyParts=new ArrayList<>(fileList.size());
        for (File file:fileList){
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // MultipartBody.Part key:=====>files
            MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
            MultipartBodyParts.add(body);
        }
        return MultipartBodyParts;
    }

    /**
     * Single File upload(we define MultipartBody.Part upload key name's "file")
     * @param file
     * @return
     */
    public static MultipartBody.Part fileToMultiBodyPart(File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part key:=====>file
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return part;
    }

}
