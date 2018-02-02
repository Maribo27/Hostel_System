package by.tc.hostel_system.controller.command;

public enum CommandType {
    LOGIN,
    LOGOUT,
    REGISTER,

    SHOW_USERS,
    SHOW_REQUESTS,
    SHOW_USER_REQUESTS,
    SHOW_HOSTELS,

    DELETE_USER,
    CANCEL_REQUEST,

    ADD_REQUEST,
    SHOW_AVAILABLE_HOSTELS,

    GET_CITIES,

    APPROVE_REQUEST,
    CHANGE_USER_DATA,
    CHANGE_PASSWORD,
    CHANGE_LOCALE,
    UNLOCK,
    BLOCK,
    GET_REASONS
}