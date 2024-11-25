package com.example.manager_users.common.enums;

import lombok.Getter;

@Getter
public enum MessageResource {
    NOT_FOUND_CODE("EN006", null),
    NOT_ALLOW_CHANGE("EN007", null),
    NOT_ALLOW_DELETE("EN012", null),
    NOT_EMPTY("WF020", null),
    DUPLICATE_CODE("EN005", null),
    RESOURCE_LOCKED_CODE("EN009", null),
    MISS_DATA_REQUIRED_CODE("EN010", null),
    CONFIG_SCHEDULING_NOT_FOUND("EN011", null),
    SETTING_MISMATCH("EN014", null),
    INVALID_REFRESH_TOKEN("INVALID_REFRESH_TOKEN", null),
    PASSWORD_NOT_MATCH("PASSWORD_NOT_MATCH", null),
    SAVE_SUCCESSFULLY("IS001", null),
    PUBLISH_SUCCESSFULLY("IS003", null),
    GENERATE_SUCCESSFULLY("IS004", null),
    GENERATE_FAILURE("IS0010", null),
    RESET_SUCCESSFULLY("IS005", null),
    SEND_MAIL_SUCCESSFULLY("IS006", null),
    SET_MAIL_SUCCESSFULLY("IS007", null),
    CANCEL_SUCCESSFULLY("IS009", null),
    DELETE_SUCCESSFULLY("IS002", null),
    UNSUPPORTED_MEDIA_TYPE("WF012", null),
    IMPORT_DATA_SUCCESSFULLY("IP001", null),
    IMPORT_DATA_FAILED("IP002", null),
    OBJECT_AVAILABLE("IP003", null),
    OBJECT_IS_NOT_AVAILABLE("IP004", null),
    UPLOAD_FILE_FAILURE("IP005", null),
    READ_FILE_FAILURE("IP008", null),
    UPDATE_IS_ACTIVE_SUCCESSFULLY("IP009", null),
    SUBMIT_SUCCESSFULLY("IP010", null),
    UPLOAD_FILE_SUCCESSFULLY("IP006", null),
    DOWNLOAD_FILE_FAILURE("IP007", null),
    DOWNLOAD_FILE_SUCCESSFULLY("IP012", null),
    EXPORT_DATA_FAILED("EP001", null),
    UNAVAILABLE_PORTS("WF013", null),
    OUT_OF_RANGE("WF014", null),
    ALREADY_IN_USE("WF003", null),
    INVALID_TIER_VALUE("WF015", null),
    NOT_ALLOWED_UPDATE_DEFAULT_VERSION("WF016", null),
    UNAVAILABLE_FUEL_GRADES("WF017", null),
    UNAVAILABLE_PORT_GROUPS("WF018", null),
    UNAVAILABLE_FREQUENCIES("WF019", null),
    REGISTER_SUCCESS("IS008", null),
    INVITATION_IN_VALID("EN008", null),
    OFFER_INVALID("EN013", null),
    SAVE_FAILURE("IS011", null),
    UNAVAILABLE_DUE_DATE_CATEGORY("WF021", null),
    UNAVAILABLE_DUE_DATE("WF022", null),
    INVALID_TYPE("WF023", null),
    NO_PERMISSION("WF024", null),
    NO_DATA("WF025", null),
    EXECUTION_ERROR("EN001", null),
    ACTIVE_TENDER_PERIOD_ERROR("WF031", null),
    CAN_NOT_CHANGE_DATA("WF035", null),
    PUBLISHED_VERSION("WF026", null),
    PREVENT_UPDATE_CONSOLIDATE_R1("WF027", null),
    PREVENT_UPDATE_CONSOLIDATE_R2("WF028", null),
    PREVENT_SEND_MAIL_R1("WF029", null),
    PREVENT_NEW_OFFER_SHEET_R2("WF030", null),
    CAN_NOT_CREATE("WF032", null),
    NOT_APPROVED("WF033", null),
    CAN_NOT_ALLOW_CREATE_APPROVAL("WF036", null),
    TENDER_PERIOD_COMPLETED("WF034",null),
    VALID_RANGE_TENDER_PERIOD("WF037",null),
    VALID_PORT_GROUP_MISSING_PIC("WF038",null),
    INVALID_HEADERS("INVALID_HEADERS", null),
    INVALID_FILE_SIZE("INVALID_FILE_SIZE", null),
    INVALID_PORT_SETTING_ID("INVALID_PORT_SETTING_ID", null),
    SYNC_DATA_FAILURE("WF039", null)
    ;

    private final String code;
    private final String[] params;

    MessageResource(String code, String params) {
        this.code = code;
        this.params = params != null ? new String[]{params} : null;
    }
}

