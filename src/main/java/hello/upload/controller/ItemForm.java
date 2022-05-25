package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//상품 저장용 폼
@Data
public class ItemForm {

    private long itemId;
    private String itemName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles; //이미지 다중 업로드를 위해 List 사용
}
