package pl.com.great.nysa.it.image.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;


@Builder
@Data
public class Image {
    private String id;
    private String gridFsFileId;
}


