package com.teahel.tneed.account.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-04
 */
@Data
public class ServerInfoV2DTO extends ServerInfoV2DO{

    /**
     * 图片
     */
    private MultipartFile image;

}
