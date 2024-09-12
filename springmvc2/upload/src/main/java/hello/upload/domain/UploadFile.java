package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName;
    private String storeFileName;

    // 유저 a 랑 유저비가 같은 이름의 각각 다른 파일을 업로할때 uuid 와같은걸로 storeFileName 을 만들어서 관리하기 위함이있다


    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
