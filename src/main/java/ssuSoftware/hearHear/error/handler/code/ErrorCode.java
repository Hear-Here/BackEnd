package ssuSoftware.hearHear.error.handler.code;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NULL(000, "no content"), /* 400 */
    // auth
    TOKEN_INVALID(400, "형식에 맞지 않은 토큰이 입력되었습니다. 헤더를 확인하세요."), TOKEN_EMPTY(400, "헤더에 토큰이 존재하지 않습니다."), TOKEN_BLANK(400,
            "빈 토큰이 입력되었습니다."), TOKEN_INVALID_KEY(
            400,
            "토큰이 비정상적으로 생성되었습니다. 접근을 거부합니다."), TOKEN_ERROR(400, "토큰 자체에 문제가 있습니다."),

    // Product
    PRICE_INVALID(400, "금액 입력이 잘못 되었습니다."),

    INVALID_POST_CODE(400, "주문중에 옳지 못한 pcode 가 입력되었습니다."),
    /* 401 */
    NO_AUTHENTICATION_ACCESS(401, "인증하지 않은 사용자의 접근입니다."),

    /* 403 */
    NO_AUTHORIZATION_ACCESS(403, "허가되지 않은 사용자의 접근입니다."),
    PAYMENT_RESULT_SENDER_IS_NOT_IAMPORT(403, "올바르지 못한 외부 결제 모듈로의 접근입니다."),

    /* 404 */
    // auth
    USER_NOT_FOUND(404, "회원을 찾을 수 없습니다."), EMAIL_NOT_FOUND(404, "입력한 이메일을 찾을 수 없습니다."),

    // product
    PRODUCT_NOT_FOUND(404, "상품을 찾을 수 없습니다."),

    // factory
    FACTORY_NOT_FOUND(404, "업체를 찾을 수 없습니다."),

    // dib
    DIB_NOT_FOUND(404, "찜을 찾을 수 없습니다."),

    // review
    REVIEW_NOT_FOUND(404, "리뷰를 찾을 수 없습니다."),

    // category
    ITEM_NOT_FOUND(404, "item 을 찾을 수 없습니다."), KIND_NOT_FOUND(404, "kind 를 찾을 수 없습니다."), OPTIONS_NOT_FOUND(404,
            "options 를 찾을 수 없습니다."),
    // honeytip
    HONEYTIP_NOT_FOUND(404, "꿀팁을 찾을 수 없습니다."), STEP_NOT_FOUND(404, "꿀팁의 스텝을 찾을 수 없습니다."),

    // File
    FILE_EXTENSION_NOT_FOUND(404, "확장자를 얻을 수 없습니다."),

    PAYMENT_NOT_FOUND(404, "해당하는 결제 상태가 없습니다."),
    ORDER_NOT_FOUND(404, "해당하는 주문을 찾을 수 없습니다."),

    /* 409 */
    DUPLICATED_USER_FOUND(409, "중복되는 oauthId 혹은 email이 존재합니다."),

    /* 412 */
    TOKEN_EXPIRED(412, "유효기간이 지난 토큰이 입력되었습니다."), NO_PROVIDER_ACCESS(412, "제공되지 않는 OAUTH PROVIDER 로의 접근입니다."),

    /* 500 */
    TOKEN_INTERNAL_ERROR(500, "서버 내부 토큰 처리과정 오류입니다. 서버팀에 문의하세요."),

    FILE_INTERNAL_ERROR(500, "byte input stream 변환 중 에러"),
    INVALID_USER_FOUND(500, "User, 내부 데이터에 오류가 있습니다. database 를 확인해주세요."),
    MANIPULATED_UID(500, "결제 과정에서 에러가 발생했습니다. merchantUid 에러"),
    PAYMENT_FAILURE(500, "결제를 성공하지 못했습니다.");

    private final int status;
    private final String detail;

    public boolean hasError() {
        return this != NULL;
    }

    public Detail getAsBody() {
        return new Detail(status, detail);
    }

    @Getter
    @AllArgsConstructor
    public class Detail {
        private int status;
        private String detail;
    }
}

