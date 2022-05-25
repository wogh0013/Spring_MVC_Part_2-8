package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;                     //id
    private String itemName;             //아이템명
    private UploadFile attachFile;       //첨부 파일(1개) -> 업로드, 다운로드
    private List<UploadFile> imageFiles; //이미지 파일(여러 개) -> 업로드한 이미지를 웹 브라우저에서 확인
}
