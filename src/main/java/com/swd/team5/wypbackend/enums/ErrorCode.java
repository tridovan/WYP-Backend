package com.swd.team5.wypbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_KEY(800, "Error key is invalid 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ROLE(999, "Invalid role 😏😏😏", HttpStatus.BAD_REQUEST),
    INVALID_ROLE_AT_SERVER(999, "Invalid role 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR),
    EXISTED_USERNAME(998,"Existed username, choose another username 😏😏😏", HttpStatus.BAD_REQUEST ),
    USER_NOT_EXISTED(997, "User not found 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_USERNAME(996, "Username must be at least 3 characters 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_PASSWORD(995, "Password must be at least 8 characters 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_EMAIL(994, "Invalid email 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_PHONE(993, "The phone number must have 10 digits 😏😏😏", HttpStatus.BAD_REQUEST ),
    UNCATEGOZIZED(992, "Unexpected error in server 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR ),
    EXISTED_PERMISSION(991, "Existed permission 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_PERMISSION_NAME(990, "Invalid permission name 😏😏😏", HttpStatus.BAD_REQUEST ),
    EXISTED_ROLE(989, "Existed role 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_USER_AND_PASSWORD(988, "Username and password can not be blank 😏😏😏", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(987,"User is unauthenticated 😏😏😏" ,HttpStatus.UNAUTHORIZED ),
    UNAUTHORIZED(986,"User is unauthorized 😏😏😏", HttpStatus.FORBIDDEN),
    JWT_ERROR(985, "can not generate JWT 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_SECRET_KEY(984, "Can not create Secret Key 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(983,"Invalid token 😏😏😏" , HttpStatus.BAD_REQUEST),
    // 👉 Thêm lỗi PRODUCT_NOT_FOUND
    PRODUCT_NOT_FOUND(982, "Product not found 😏😏😏", HttpStatus.BAD_REQUEST),

    // 👉 Thêm lỗi BRAND_NOT_FOUND
    BRAND_NOT_FOUND(981, "Brand not found 😏😏😏", HttpStatus.BAD_REQUEST),

    // 👉 Thêm lỗi EXISTED_PRODUCT_NAME
    EXISTED_PRODUCT_NAME(980, "Existed product name 😏😏😏", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(979, "Not found user 😏😏😏", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(978, "Not found cart 😏😏😏", HttpStatus.BAD_REQUEST),
    ORDER_NOT_EXISTED(977, "Not found order 😏😏😏", HttpStatus.BAD_REQUEST),
    ORDER_DETAIL_NOT_EXISTED(976, "Not found detail order 😏😏😏", HttpStatus.BAD_REQUEST),
    ORDER_STATUS_NOT_EXISTED(975, "Not found status order 😏😏😏", HttpStatus.BAD_REQUEST),
    ORDER_STATUS_ALREADY_EXISTED(974, "Already status order 😏😏😏", HttpStatus.BAD_REQUEST),
    CUSTOMIZATION_NOT_EXISTED(973, "Not found customization 😏😏😏", HttpStatus.BAD_REQUEST),
    CART_ITEM_NOT_EXISTED(972, "Cart item not existed 😏😏😏", HttpStatus.BAD_REQUEST),

    // Promotion errors
    PROMOTION_NOT_FOUND(968, "Promotion not found 😏😏😏", HttpStatus.BAD_REQUEST),

    FILE_ERROR(971,"Error at file upload 😏😏😏" , HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND(970,"Email not found 😏😏😏" , HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(969,"You have not permission to access this feature ĐỒ NGỐC À 😏😏😏" , HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    private HttpStatus status;

}
