package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    //업로드한 파일을 굳이 두 가지로 나누는 이유는 분명히 있다.
    //고객이 업로드한 파일명으로 서버 내부에 파일을 저장하면 안됨
    //why? 서로 다른 고객이 같은 파일명으로 업로드하면, 서버 내부에서 충돌이 남
    //따라서 서버 내부에서 별도의 파일명으로 저장해 관리한다.
    private String uploadFileName; //고객이 업로드한 파일명
    private String storeFileName;  //서버 내부에서 관리하는 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
