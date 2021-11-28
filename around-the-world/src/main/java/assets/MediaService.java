package assets;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MediaService {

    public MediaService() {
    }

    private String saveImage(MultipartFile imageFile) {
        String folder = "./uploads/";
        String fileName = UUID.randomUUID()+ imageFile.getOriginalFilename();
        try{
            byte[] bytes = imageFile.getBytes();
            Path filePath = Paths.get(folder + fileName);
            Files.write(filePath, bytes);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "/uploads/"+fileName;
    }

    public List<String> getImagesPaths(MultipartFile[] files) {
        ArrayList<String> imagesPaths = new ArrayList<String>();
        try {
            for(MultipartFile file : files){
                imagesPaths.add(saveImage(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imagesPaths;
    }
}
