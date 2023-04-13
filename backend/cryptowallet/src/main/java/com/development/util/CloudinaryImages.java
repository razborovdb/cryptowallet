package com.development.util;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.development.env.EnvironmentVariable;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CloudinaryImages {
    private ObjectMapper objectMapper = new ObjectMapper();

    private String cloudinaryName = EnvironmentVariable.CLOUDINARY_NAME;

    private String cloudinaryApi = EnvironmentVariable.CLOUDINARY_API_KEY;

    private String cloudinarySecret = EnvironmentVariable.CLOUDINARY_API_SECRET;

    public Cloudinary getCloudinary() {

        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryName,
                "api_key", cloudinaryApi,
                "api_secret", cloudinarySecret,
                "secure", true));


    }

}
