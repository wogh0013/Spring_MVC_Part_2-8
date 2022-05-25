package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//멀티파트 파일을 서버에 저장하는 역할
@Component
public class FileStore {

    @Value("${file.dir}")
    public String fileDir;

    //filename을 파라미터로 받아 FullPath를 반환
    public String getFullPath(String filename){
        return fileDir + filename;
    }

    //이미지 파일은 여러 개니까 List로 담아둔다.
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }


    //파일 저장 - MultipartFile을 가지고 실제 파일을 저장한 후, 우리가 만든 UploadFile로 반환
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename(); //img.png
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }

    //'서버 내부에서 관리할 파일명'을 만드는 메서드 - 충돌 방지를 위해 유일한 이름으로(by UUID)
    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString(); //"qwe-qwe-123-qwe-qqw"
        String ext = extractExt(originalFilename); //png
        return uuid + "." + ext;
    }

    //확장자 추출 메서드 - 서버 내부에서 관리하는 파일명에도 붙여준다.
    //ex) 고객이 a.png 로 저장
    //    서버 내부는 xxx-xxx-xxx-xxx-xxx.png 로 저장
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
